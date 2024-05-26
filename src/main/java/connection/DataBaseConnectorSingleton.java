package connection;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DataBaseConnectorSingleton {
    private final Connection connection;
    private static DataBaseConnectorSingleton instance;


    // Настройка данных из db.properties
    private DataBaseConnectorSingleton() throws IOException, SQLException {
        Properties properties = new Properties();
        InputStream input = Files.newInputStream(Paths.get("C:/JAVA/ServletHW/src/main/resources/db.properties"));

        properties.load(input);

        String url = properties.getProperty("db.url");
        String user = properties.getProperty("db.user");
        String password = properties.getProperty("db.password");
        String driver = properties.getProperty("db.driver");
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        connection= DriverManager.getConnection(url,user,password);
    }

    public Connection getConnection() {
        return connection;
    }

    // Singleton реализация
    public static DataBaseConnectorSingleton getInstance() throws SQLException, IOException {
        if (instance == null || instance.getConnection().isClosed()) {
            instance = new DataBaseConnectorSingleton();
        }
        return instance;
    }
}
