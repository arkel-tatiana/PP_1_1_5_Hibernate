package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;

import java.sql.Connection;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
        UserService userService = new UserServiceImpl();
        userService.createUsersTable();
        userService.dropUsersTable();
        userService.cleanUsersTable();
        userService.saveUser("testvv1", "testvv11", (byte) 31);
        userService.saveUser("testvv2", "testvv22", (byte) 22);
        userService.saveUser("testvv3", "testvv33", (byte) 33);
        userService.saveUser("testvv4", "testvv44", (byte) 44);
        userService.removeUserById(13);
        userService.getAllUsers();
      //  System.out.println(userService.getAllUsers().toString());
    }

}
