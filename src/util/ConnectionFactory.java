package util;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by Lcrit_Z on 2017/5/6.
 */
public class ConnectionFactory {
    private static Connection conn = null;

    public static synchronized Connection getConn() throws SQLException{
        if (conn==null){
            JDBCPool pool = new JDBCPool();
            conn = pool.getConnection();
            return conn;
        }
        return conn;
    }

}
