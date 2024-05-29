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

     private DataBaseConnectorSingleton() throws IOException, SQLException {
         Properties properties = new Properties();
         // Используем try-with-resources для автоматического закрытия потока после использования
         try (InputStream input = Files.newInputStream(Paths.get("C:/JAVA/ServletHW/src/main/resources/db.properties"))) {
             properties.load(input);

             String url = properties.getProperty("db.url");
             String user = properties.getProperty("db.user");
             String password = properties.getProperty("db.password");
             String driver = properties.getProperty("db.driver");

             // Проверяем, не равно ли driver нулю
             if (driver == null) {
                 throw new IllegalArgumentException("Database driver is not specified in the properties file.");
             }

             // Проверяем, не равно ли url нулю
             if (url == null) {
                 throw new IllegalArgumentException("Database URL is not specified in the properties file.");
             }

             // Загружаем драйвер
             try {
                 Class.forName(driver);
             } catch (ClassNotFoundException e) {
                 throw new RuntimeException("Failed to load database driver.", e);
             }

             // Устанавливаем соединение
             connection = DriverManager.getConnection(url, user, password);
         }
     }

     public Connection getConnection() {
         return connection;
     }

     public static synchronized DataBaseConnectorSingleton getInstance() throws IOException, SQLException {
         if (instance == null || instance.getConnection().isClosed()) {
             instance = new DataBaseConnectorSingleton();
         }
         return instance;
     }

     // Метод для явного закрытия соединения
     public void closeConnection() throws SQLException {
         if (connection != null && !connection.isClosed()) {
             connection.close();
         }
     }
 }