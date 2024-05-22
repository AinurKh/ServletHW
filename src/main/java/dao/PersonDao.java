package dao;

import connection.DataBaseConnectorSingleton;
import entity.PersonBuilder;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PersonDao {

    public void addPerson(PersonBuilder personBuilder)  {
        String sql = "insert into person (name, age) values (?, ?)";

        try(Connection connection = DataBaseConnectorSingleton.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql)){

            preparedStatement.setString(1, personBuilder.getName());
            preparedStatement.setInt(2, personBuilder.getAge());
            preparedStatement.executeUpdate();

        }catch (SQLException | IOException e){
            e.printStackTrace();
        }
    }

    public List<PersonBuilder> getPeople() {
        String sql = "select * from person";
        List<PersonBuilder> peopleList = new ArrayList<>();

        try(Connection connection = DataBaseConnectorSingleton.getInstance().getConnection();
            Statement statement = connection.prepareStatement(sql)){

            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                PersonBuilder personBuilder = new PersonBuilder.Builder()
                        .setId(resultSet.getInt("id"))
                        .setName(resultSet.getString("name"))
                        .setAge(resultSet.getInt("age"))
                        .build();

                peopleList.add(personBuilder);
            }

        }catch (SQLException | IOException e){
            e.printStackTrace();
        }
        return peopleList;
    }



    public Optional<PersonBuilder> getPersonById(int id) throws SQLException, IOException {
        String sql = "select * from person where id = ?";

        try(Connection connection = DataBaseConnectorSingleton.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql)){

            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()){
                PersonBuilder personBuilder = new PersonBuilder.Builder()
                        .setId(resultSet.getInt("id"))
                        .setName(resultSet.getString("name"))
                        .setAge(resultSet.getInt("age"))
                        .build();

                return Optional.ofNullable(personBuilder);
            }

        }
        return null;
    }

    public void updatePerson(PersonBuilder personBuilder) throws SQLException, IOException {
        String sql = "update person set name = ?, age = ? where id = ?";

        try(Connection connection = DataBaseConnectorSingleton.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql)){

            preparedStatement.setString(1, personBuilder.getName());
            preparedStatement.setInt(2, personBuilder.getAge());
            preparedStatement.setInt(3, personBuilder.getId());

            preparedStatement.executeUpdate();
        }
    }

    public void deletePerson(int id) throws SQLException, IOException {
        String sql = "delete from person where id = ?";

        try(Connection connection= DataBaseConnectorSingleton.getInstance().getConnection();
            PreparedStatement preparedStatement=connection.prepareStatement(sql)){

            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        }
    }

}
