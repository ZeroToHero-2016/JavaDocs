package ro.teamnet.zth.web;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;

public class InfoHttpServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String headers;
        resp.setContentType("text/html");
        resp.getWriter().write("<table border='1'" + ">" );
        Enumeration e = req.getHeaderNames();
        while (e.hasMoreElements()) {
            headers = (String) e.nextElement();
            if (headers != null) {
                resp.getWriter().write("<tr><td align=center><b>" + headers + "</td>");
                resp.getWriter().write("<td align=center>" + req.getHeader(headers)
                        + "</td></tr>");
            }
        }
        resp.getWriter().write("</TABLE><BR>");

        resp.getWriter().write("Http Method: " + req.getMethod() + "<br>");

        resp.getWriter().write("QueryString: " + req.getQueryString() + "<br>");

        Cookie[] cookies = req.getCookies();

        resp.getWriter().write("<table border='1'" + ">" );

        for (int i = 0; i < cookies.length; i++) {

            String name = cookies[i].getName();
            String value = cookies[i].getValue();
            resp.getWriter().write("<tr><td align=center><b>" + name + "</td>");
            resp.getWriter().write("<td align=center>" + value + "</td></tr>");
            resp.getWriter().write("</TABLE><BR>");
        }

        String parameter;
        e = req.getParameterNames();
        resp.getWriter().write("<table border='1'" + ">" );
        while (e.hasMoreElements()) {
            parameter = (String) e.nextElement();
            if (parameter != null) {
                resp.getWriter().write("<tr><td align=center><b>" + parameter + "</td>");
                resp.getWriter().write("<td align=center>" + req.getParameter(parameter)
                        + "</td></tr>");
            }
        }

    }
}
