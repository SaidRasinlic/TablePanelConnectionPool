package javaguitableandconnectonpool;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ConnectionPool {

    private final List<Connection> availableConnections;
    private final List<Connection> usedConnections;
    private final int MAX_CONNECTIONS = 5;

    final String URL;
    final String USERNAME;
    final String PASSWORD;

    public ConnectionPool(String URL, String USERNAME, String PASSWORD) throws SQLException {
        this.usedConnections = new ArrayList();
        this.availableConnections = new ArrayList();
        this.URL = URL;
        this.USERNAME = USERNAME;
        this.PASSWORD = PASSWORD;

        for (int count = 0; count < MAX_CONNECTIONS; count++) {
            availableConnections.add(this.createConnection());
        }

    }

    Connection createConnection() throws SQLException {
        return DriverManager.getConnection(this.URL, this.USERNAME, this.PASSWORD);
    }

    public Connection getConnection() {
        if (availableConnections.isEmpty()) {
            System.out.println("All connections are Used !!");
            return null;
        } else {
            Connection con = availableConnections.remove(availableConnections.size() - 1);
            usedConnections.add(con);
            return con;
        }
    }

    public boolean releaseConnection(Connection conn) {
        if (null != conn) {
            usedConnections.remove(conn);
            availableConnections.add(conn);
            return true;
        }
        return false;
    }

    public int getFreeConnectionCount() {
        return availableConnections.size();
    }

}
/*public static void main(String[] args) throws SQLException {

        ConnectionPool pool = new ConnectionPool("jdbc:mysql://localhost/petdatabase", "root", "Saidolehan");
  
            Connection conn = pool.getConnection();


         System.out.println("Konekcija broj 1 ");
        Connection con1 = pool.getConnection();
        
        
      System.out.println("Konekcija broj 2 ");
        Connection con2 = pool.getConnection();
          
        System.out.println("Slobodnih konekcija: "+pool.getFreeConnectionCount());
        
          System.out.println("Konekcija broj 3 ");
        Connection con3 = pool.getConnection();
        
        System.out.println("Konekcija broj 4 ");
        Connection con4 = pool.getConnection();
        
          System.out.println("Konekcija broj 5 ");
        Connection con5 = pool.getConnection();
        
        
        System.out.println("Konekcija broj 6");
        Connection con6 = pool.getConnection();
        
        System.out.println("Slobodnih konekcija: "+pool.getFreeConnectionCount());
        
        
        pool.releaseConnection(con1);
        pool.releaseConnection(con2);
        pool.releaseConnection(con4);
        System.out.println("Oslobadjamo konekciju broj 1,2,4");
        System.out.println("Krajnji dostupni broj konekcija je: " + pool.getFreeConnectionCount()); 
        System.out.println(pool.getFreeConnectionCount());
        
    }*/


