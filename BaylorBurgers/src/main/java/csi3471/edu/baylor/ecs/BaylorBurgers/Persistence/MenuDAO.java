package csi3471.edu.baylor.ecs.BaylorBurgers.Persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;


public class MenuDAO {
    private static final String DB_DRIVER = "org.apache.derby.jdbc.EmbeddedDriver";
    private static final String DB_CONNECTION = "jdbc:derby:BBMenu;";
    private static final String DB_USER = "";
    private static final String DB_PASSWORD = "";

    public void connectToDatabase(){
        try {
            Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
        } catch (ClassNotFoundException e) {
            System.out.println("Where is your Derby JDBC Driver?");
            e.printStackTrace();
            return;
        }
        Connection connection = null;
        try {
            connection = DriverManager.getConnection("jdbc:derby:BBMenu;create=true", "", "");
            if (connection != null) {
                //System.out.println("You made it, take control your database now!");
            } else {
                System.out.println("Failed to make connection!");
            }
        } catch (SQLException e) {
            System.out.println("Connection Failed! Check output console");
            e.printStackTrace();
            return;
        } finally {
            if (connection != null) {
                try {
                    if (!connection.isClosed()) {
                        connection.close();
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    public void createDatabase(){
        try {
            createDbUserTable();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    private static void createDbUserTable() throws SQLException {
        Connection dbConnection = null;
        Statement statement = null;
        String createTableSQL = "CREATE TABLE DBUSER(" + "USER_ID INTEGER NOT NULL, "
                + "USERNAME VARCHAR(20) NOT NULL, " + "CREATED_BY VARCHAR(20) NOT NULL, "
                + "CREATED_DATE DATE NOT NULL, " + "PRIMARY KEY (USER_ID) " + ")";
        try {
            dbConnection = getDBConnection();
            statement = dbConnection.createStatement();
            System.out.println(createTableSQL);
            // execute the SQL stetement
            statement.execute(createTableSQL);
            System.out.println("Table \"dbuser\" is created!");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            if (statement != null) {
                statement.close();
            }
            if (dbConnection != null) {
                dbConnection.close();
            }
        }
    }
    private static Connection getDBConnection() {
        Connection dbConnection = null;
        try {
            Class.forName(DB_DRIVER);
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
        try {
            dbConnection = DriverManager.getConnection(DB_CONNECTION, DB_USER, DB_PASSWORD);
            return dbConnection;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return dbConnection;
    }
    public static void main(String[] argv) {
    	MenuDAO menuDAO = new MenuDAO();
    	menuDAO.connectToDatabase();
    	menuDAO.createDatabase();
    }
}