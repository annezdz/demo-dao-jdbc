package db;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class DB {

    // objeto de conexao com DB do JDBC

    private static Connection conn = null;

    public static Connection getConnection() {
        if(conn == null) {
            try{
                Properties props = loadProperties();

                //dburl definida no db.properties vem para cá
                String url = props.getProperty("dburl");
                //Conectamos com banco de dados e conectar no BD é instanciar um objeto Connection
                conn = DriverManager.getConnection(url,props);
            }
            catch (SQLException e) {
                throw new DbException(e.getMessage());
            }

        }
        return conn;
    }

    public static void closeConnection() {
        try {
            if(conn != null) {
                conn.close();
            }
        }
        catch (SQLException e) {
            throw new DbException(e.getMessage());
        }

    }

    private static Properties loadProperties() {
        try(FileInputStream fs = new FileInputStream("db.properties")) {
            Properties props = new Properties();
            props.load(fs);
            return props;
        }
        catch (IOException e) {
            throw new DbException(e.getMessage());
        }
    }

    public static void closeStatement(Statement statement) {
        if(statement!= null) {
            try {
                statement.close();
            } catch (SQLException e) {
                throw new DbException(e.getMessage());
            }
        }
    }

    public static void closeResultSet(ResultSet resultSet) {
        if(resultSet!= null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                throw new DbException(e.getMessage());
            }
        }
    }
}
