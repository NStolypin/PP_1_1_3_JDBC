package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import jm.task.core.jdbc.util.*;

public class UserDaoJDBCImpl implements UserDao {
    public UserDaoJDBCImpl() {

    }

    @Override
    public void createUsersTable() {
        try(Connection connected = Util.getConnection();){
            java.sql.PreparedStatement stmt = null;
            String sql = "CREATE TABLE IF NOT EXISTS USER_TABLE" +
            " (id INT UNSIGNED NOT NULL AUTO_INCREMENT," +
            " name VARCHAR(40)," +
            " lastName VARCHAR(40)," +
            " age TINYINT UNSIGNED," +
            " PRIMARY KEY (id))";
            stmt = connected.prepareStatement(sql);
            stmt.executeUpdate();
            //System.out.println("Table created if it not exists");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void dropUsersTable() {
        try(Connection connected = Util.getConnection();) {
            java.sql.PreparedStatement stmt = null;
            String sql = "DROP TABLE IF EXISTS USER_TABLE";
            stmt = connected.prepareStatement(sql);
            stmt.executeUpdate();
            //System.out.println("Table drop if it exists");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        try(Connection connected = Util.getConnection();) {
            java.sql.PreparedStatement stmt = null;
            createUsersTable();
            String sql = "INSERT INTO USER_TABLE (name, lastName, age)" +
                         " VALUES ('" + name + "', '" + lastName + "', '" + age + "')";
            stmt = connected.prepareStatement(sql);
            stmt.executeUpdate();
            System.out.printf("User с именем — %s добавлен в базу данных\n", name);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeUserById(long id) {
        try(Connection connected = Util.getConnection();) {
            java.sql.PreparedStatement stmt = null;
            createUsersTable();
            String sql = "DELETE from USER_TABLE WHERE id=" + id;
            stmt = connected.prepareStatement(sql);
            stmt.executeUpdate();
            //System.out.println("User remove");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        try(Connection connected = Util.getConnection();) {
            java.sql.PreparedStatement stmt = null;
            createUsersTable();
            String sql = "SELECT * FROM USER_TABLE";
            stmt = connected.prepareStatement(sql);
            ResultSet res = stmt.executeQuery();
            while(res.next()) {
                User user = new User(res.getString("name"),
                                     res.getString("lastName"),
                                     res.getByte("age"));
                user.setId(res.getLong("id"));
                System.out.println(user);
                users.add(user);
            }
           
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    public void cleanUsersTable() {
        try(Connection connected = Util.getConnection();) {
            java.sql.PreparedStatement stmt = null;
            createUsersTable();
            String sql = "TRUNCATE TABLE USER_TABLE";
            stmt = connected.prepareStatement(sql);
            stmt.executeUpdate();
            //System.out.println("Table truncate");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
