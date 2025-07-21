package com.selin.dao;

import com.selin.user.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAOImp implements UserDAO {

    private final Connection connection;

    public UserDAOImp(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void createtable() {
        String sql="CREATE TABLE IF NOT EXISTS userss(" +
                "id SERIAL PRIMARY KEY,"+
                "name VARCHAR(50),"+
                "email VARCHAR(100))";

        try(Statement statement = connection.createStatement()) {
            statement.execute(sql);
            System.out.println("Tablo oluşturuldu");
        }catch(SQLException e) {
            throw new RuntimeException("Tablo oluşturulamadı");
        }

    }

    @Override
    public void save(User user) {
        String sql="INSERT INTO userss (name,email) VALUES (?,?)";
        try(PreparedStatement preparedstatement = connection.prepareStatement(sql)) {
            preparedstatement.setString(1,user.getName());
            preparedstatement.setString(2,user.getEmail());
            preparedstatement.executeUpdate();
            System.out.println("Kayıt tamamlandı");

        } catch (SQLException e) {
            throw new RuntimeException("Kayıt oluşturulamadı");
        }

    }

    @Override
    public List<User> findAll() {
        List<User> users = new ArrayList<>();
        String sql="SELECT * FROM userss";

        try(Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(sql);

            while(resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String email = resultSet.getString("email");

                User user = new User(id,name,email);
                users.add(user);
            }
            System.out.println("Kullanıcılar listelendi");

        }catch (SQLException e){
            throw new RuntimeException("Kullanıcılar çekilemedi");
        }
        return users;
    }

    @Override
    public void update(User user) {
        String sql="UPDATE userss SET name=?,email=? WHERE id=?";

        try (PreparedStatement preparedstatement = connection.prepareStatement(sql)) {
            preparedstatement.setString(1,user.getName());
            preparedstatement.setString(2,user.getEmail());
            preparedstatement.setInt(3,user.getId());
            preparedstatement.executeUpdate();

            System.out.println("Kullanıcı güncellendi");

        } catch (SQLException e) {
            throw new RuntimeException("Kullanıcı güncellenemedi",e);
        }

    }

    @Override
    public void delete(int id) {
        String sql="DELETE FROM userss WHERE id=?";

        try (PreparedStatement preparedstatement = connection.prepareStatement(sql)) {
            preparedstatement.setInt(1,id);
            preparedstatement.executeUpdate();

            System.out.println("Kullanıcı silindi");

        } catch (SQLException e) {
            throw new RuntimeException("Kullanıcı silinemedi",e);
        }

    }
}