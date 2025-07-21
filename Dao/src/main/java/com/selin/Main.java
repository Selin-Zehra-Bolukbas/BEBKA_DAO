package com.selin;


import com.selin.config.DataBaseConfig;
import com.selin.config.DataBaseConnectorConfig;
import com.selin.dao.UserDAO;
import com.selin.dao.UserDAOImp;
import com.selin.user.User;

import java.sql.Connection;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        try {
            DataBaseConnectorConfig.setConnection();
            Connection connection = DataBaseConnectorConfig.getConnection();
            UserDAOImp userDAO = new UserDAOImp(connection);
            /* // kullanıcılar eklendi
            userDAO.createtable();

            User user = new User(1,"Selin","selin@gmail.com");
            userDAO.save(user);

            User user2 = new User(2,"Mehmet","mehmet@gmail.com");
            userDAO.save(user2);

            User user3 = new User(3,"Fatma","fatma@gmail.com");
            userDAO.save(user3);*/


            List<User> users = userDAO.findAll();

            if (users.isEmpty()) {
                System.out.println("Hiç kullanıcı bulunamadı.");
            } else {
                for (User u : users) {
                    System.out.println(u);
                }
            }






        }catch (Exception e) {
            e.printStackTrace();
        }

    }
}