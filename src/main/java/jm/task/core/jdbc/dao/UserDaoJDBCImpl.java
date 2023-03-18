package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {//1
        String sql = "CREATE TABLE IF NOT EXISTS users" +
                "(id int not null auto_increment," +
                "name VARCHAR(45) not null," +
                "lastName VARCHAR(50) not null," +
                "age int," +
                "PRIMARY KEY (id))";
        try (Statement statement = Util.getConnection().createStatement()) {
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
            //throw new RuntimeException(e);
        }

    }

    public void dropUsersTable() {//1
        String sql = "DROP TABLE IF EXISTS users";
        try (Statement statement = Util.getConnection().createStatement()) {
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
            //throw new RuntimeException(e);
        }
    }

    public void saveUser(String name, String lastName, byte age) {//4
        String sql = "INSERT INTO users (name, lastName, age) VALUES (?, ?, ?)";
        try (PreparedStatement preparedStatement = Util.getConnection().prepareStatement(sql)) {
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            //throw new RuntimeException(e);
        }
    }

    public void removeUserById(long id) {//5
        String sql = "DELETE FROM users where id = (?)";
        try (PreparedStatement preparedStatement = Util.getConnection().prepareStatement(sql)) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            //throw new RuntimeException(e);
        }
    }

    public List<User> getAllUsers() {
        List <User> listUser = new ArrayList<>();
        String sql = "SELECT * FROM users";
        try (Statement statement = Util.getConnection().createStatement()) {
            ResultSet resSet = statement.executeQuery(sql);
            while (resSet.next()) {
                User user = new User();
                user.setId(resSet.getLong("id"));
                user.setName(resSet.getString("name"));
                user.setLastName(resSet.getString("lastName"));
                user.setAge(resSet.getByte("age"));
                listUser.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            //throw new RuntimeException(e);
        } finally {
            return listUser;
        }
    }//6

    public void cleanUsersTable() {//3
        String sql = "TRUNCATE users";
        try (Statement statement = Util.getConnection().createStatement()) {
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
            //throw new RuntimeException(e);
        }
    }
}
