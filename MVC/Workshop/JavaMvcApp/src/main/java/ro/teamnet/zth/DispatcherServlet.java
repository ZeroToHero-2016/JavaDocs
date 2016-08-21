package ro.teamnet.zth;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import ro.teamnet.zth.api.annotations.MyController;
import ro.teamnet.zth.api.annotations.MyRequestMethod;
import ro.teamnet.zth.api.annotations.MyRequestParam;
import ro.teamnet.zth.appl.controller.DepartmentController;
import ro.teamnet.zth.appl.controller.EmployeeController;
import ro.teamnet.zth.fmk.AnnotationScanUtils;
import ro.teamnet.zth.fmk.MethodAttributes;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.*;

public class DispatcherServlet extends HttpServlet {
    //rol de registru
    //key : urlPath
    //valoare: informatii despre metoda care proceseaza url-ul
    HashMap<String,MethodAttributes> allowedMethods = new HashMap<String, MethodAttributes>();

    @Override
    public void init() throws ServletException {
        try {
            Iterable<Class> controllers = AnnotationScanUtils.getClasses("ro.teamnet.zth.appl.controller");
            for (Class controller : controllers){
                if (controller.isAnnotationPresent(MyController.class)){
                    MyController myCtrAnnotation = (MyController)controller.getAnnotation(MyController.class);
                    String controllerUrlPath = myCtrAnnotation.urlPath();
                    Method[] controllerMethods = controller.getMethods();
                    for (Method controllerMethod : controllerMethods){
                        if (controllerMethod.isAnnotationPresent(MyRequestMethod.class)){
                            MyRequestMethod requestMethod = controllerMethod.getAnnotation(MyRequestMethod.class);
                            String methodUrlPath = requestMethod.urlPath();
                            String urlPath = controllerUrlPath + methodUrlPath;
                            MethodAttributes methodAttributes = new MethodAttributes();
                            methodAttributes.setControllerClass(controller.getName());
                            methodAttributes.setMethodType(requestMethod.methodType());
                            methodAttributes.setMethodName(controllerMethod.getName());
                            methodAttributes.setParameterTypes(controllerMethod.getParameterTypes());
                            allowedMethods.put(urlPath,methodAttributes);
                        }
                    }
                }
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //instructiuni de delegare
        //acelasi lucru trebuie scris si in doget si in dopost => cream metoda dispatchReply
        dispatchReply("GET",req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //instructiuni de delegare
        dispatchReply("POST",req,resp);

    }

    protected void dispatchReply(String method,HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException {
        Object r;
        try {
            r = dispatch(req,resp);
            try {
                reply(r, req, resp);
            }
            catch (IOException e){
                sendExceptionError(e,req,resp);
            }
        }
        catch (Exception e){
            sendExceptionError(e,req,resp);
        }

    }

    private void sendExceptionError(Exception e, HttpServletRequest req, HttpServletResponse resp) {
    }

    private void reply(Object r, HttpServletRequest req, HttpServletResponse resp) throws IOException {
        PrintWriter out = resp.getWriter();

        ObjectMapper objectMapper = new ObjectMapper();
        String value = objectMapper.writeValueAsString(r);
        out.printf(value);

    }



    private Object dispatch(HttpServletRequest req, HttpServletResponse resp) {
        String path = req.getPathInfo();
        /*EmployeeController empController = new EmployeeController();
        DepartmentController deptController = new DepartmentController();
        if (path.startsWith("/employees")) {
            String r = empController.getAllEmployees();
            return r; //returneaza, nu intoarce nimic catre client => reply
        }
        else if (path.startsWith("/departments")) {
            String r = deptController.getAllDepartments();
            return r;
        }
        //am adaugat adnotarile si nu mai verific aici path-ul
        */


        /*Validate tip metoda: get,post,delete etc
        * modific in dispatch (pentru ca modific cheia inainte)
        * 8 -> suprascriu dodelete in httpservlet
        * pt a salva angajat: cu post...*/

        MethodAttributes methodAttributes = allowedMethods.get(path);
        if (methodAttributes == null) return "Hello";
        else{
            String controllerName = methodAttributes.getControllerClass();
            try {

                Class<?> controllerClass = Class.forName(controllerName);
                Object controllerInstance = controllerClass.newInstance();
                Method method = controllerClass.getMethod(methodAttributes.getMethodName(), methodAttributes.getParameterTypes());
                Parameter[] parameters = method.getParameters();
                List<Object> parameterValues = new ArrayList<>();
                for (Parameter parameter : parameters){
                    if (parameter.isAnnotationPresent(MyRequestParam.class)){
                        MyRequestParam annotation = parameter.getAnnotation(MyRequestParam.class);
                        String name = annotation.name();
                        String requestParamValue = req.getParameter(name);
                        Class<?> type = parameter.getType();
                        Object requestParamObj = new ObjectMapper().readValue(requestParamValue, type);
                        parameterValues.add(requestParamObj);

                    }
                }
                req.getParameter("id");
                Object result = method.invoke(controllerInstance, parameterValues.toArray());
                return result;

            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            } catch (JsonParseException e) {
                e.printStackTrace();
            } catch (JsonMappingException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return "Hello";
    }
}
