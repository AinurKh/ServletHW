package connection;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;


public class DataBaseConnectorSingletonTest {

  @Test
  void testConnection() throws SQLException, IOException {
    DataBaseConnectorSingleton dataBaseConnectorSingleton = DataBaseConnectorSingleton.getInstance();

    try {
      Connection connection = dataBaseConnectorSingleton.getConnection();
      assertNotNull(connection,"Connection should not be null");
      assertFalse(connection.isClosed(),"Connection should be open");
    }
    catch (SQLException e) {
      fail(e.getMessage());
    }
  }

  @Test
  void testSingleton() throws SQLException, IOException {
    DataBaseConnectorSingleton dataBaseConnectorSingleton = DataBaseConnectorSingleton.getInstance();
    DataBaseConnectorSingleton dataBaseConnectorSingleton2 = DataBaseConnectorSingleton.getInstance();

    assertEquals(dataBaseConnectorSingleton,dataBaseConnectorSingleton2,"Must be the same");
  }

  @Test
  void testReconnectingAfterClose() throws SQLException, IOException {
    Connection connection = DataBaseConnectorSingleton.getInstance().getConnection();
    connection.close();

    assertTrue(connection.isClosed(), "Connection should be closed");

    connection=DataBaseConnectorSingleton.getInstance().getConnection();
    assertFalse(connection.isClosed(), "Connection should be open");

  }

}
