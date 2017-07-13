package ro.teamnet.zth.api.database;

import javax.swing.plaf.nimbus.State;
import java.sql.*;

/**
 * Created by Radu.Furculesteanu on 7/13/2017.
 */
public class DBManager {
    static final String CONNECTION_STRING="jdbc:oracle:thin:@" + DBProperties.IP + ":" + DBProperties.PORT + ":xe";

    private DBManager() throws UnsupportedOperationException
    {
        throw new UnsupportedOperationException();
    }

    private static void registerDriver()
    {
        try
        {
            Class.forName(DBProperties.DRIVER_CLASS);
        }
        catch (ClassNotFoundException cnfe)
        {
            cnfe.printStackTrace();
        }
    }

    public static Connection getConnection()
    {
        try
        {
            registerDriver();
            Connection con = DriverManager.getConnection(CONNECTION_STRING,DBProperties.USER,DBProperties.PASS);
            return con;
        }
        catch(SQLException sqle)
        {
            System.out.println("Unable to connect to database!");
            System.exit(1);
        }
        return null;
    }

    public static int checkConnection(Connection con) throws SQLException
    {
        int result = 0;
        Statement stmt = con.createStatement();
        String sql = "SELECT 1 FROM DUAL";
        ResultSet rs = stmt.executeQuery(sql);
        while(rs.next()) {
            result = rs.getInt(1);
        }
        stmt.close();
        return result;
    }

}
