package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Maria
 */
public class DbUtils {

    private static final String MYSQLURL = "jdbc:mysql://localhost:3306/school_project"
            + "?zeroDateTimeBehavior=CONVERT_TO_NULL"
            + "&useUnicode=true"
            + "&useJDBCCompliantTimezoneShift=true"
            + "&useLegacyDatetimeCode=false"
            + "&serverTimezone=UTC"
            + "&allowPublicKeyRetrieval=true"
            + "&useSSL=false";

    private static String username = "~~~";
    private static String password = "***";

    public static Connection getConnection() throws SQLException {
        Connection con = null;
        con = DriverManager.getConnection(MYSQLURL, username, password);
        return con;
    }
}
