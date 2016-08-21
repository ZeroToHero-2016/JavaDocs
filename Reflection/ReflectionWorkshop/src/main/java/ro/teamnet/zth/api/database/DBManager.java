package ro.teamnet.zth.api.database;

import java.sql.*;

public class DBManager {

    public static final String CONNECTION_STRING = "jdbc:oracle:thin:@" + DBProperties.IP + ":" + DBProperties.PORT + ":xe";

    private DBManager() throws UnsupportedOperationException{
    }

/*    private static void registerDriver(){
        try {
            Class.forName(DBProperties.DRIVER_CLASS);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }*/

    private static void registerDriver() throws ClassNotFoundException {
        try {
            Class.forName(DBProperties.DRIVER_CLASS);
        }
        catch (ClassNotFoundException e) {
            System.out.println("Error: unable to load driver class!");
            System.exit(1);
        }
    }

    public static Connection getConnection() throws ClassNotFoundException, SQLException {
        try {
            registerDriver();
            return DriverManager.getConnection(CONNECTION_STRING, DBProperties.USER, DBProperties.PASS);
        }
         catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static boolean checkConnection(Connection connection){
        String query = "select 1 from dual";
        try{
            Statement st = connection.createStatement();
            return st.execute(query);
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return false;

    }


}
