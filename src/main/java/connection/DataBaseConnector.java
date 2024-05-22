package connection;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DataBaseConnector {
    private final Connection connection;
    private static DataBaseConnector instance;


    // Настройка данных из db.properties
    private DataBaseConnector() throws IOException, SQLException {
        Properties properties = new Properties();
        InputStream input = Files.newInputStream(Paths.get("db.properties"));

        properties.load(input);

        String url = properties.getProperty("db.url");
        String user = properties.getProperty("db.user");
        String password = properties.getProperty("db.password");

       connection= DriverManager.getConnection(url,user,password);
    }

    public Connection getConnection() {
        return connection;
    }

    // Singleton реализация
    public static DataBaseConnector getInstance() throws SQLException, IOException {
        if (instance == null || instance.getConnection().isClosed()) {
            instance = new DataBaseConnector();
        }
        return instance;
    }
}
