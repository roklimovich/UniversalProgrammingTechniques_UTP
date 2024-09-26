package pjwstk.edu.pl.s27619.repositories;

import pjwstk.edu.pl.s27619.dtos.UserDTO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserRepository implements IUserRepository {

    private static final String URL = "jdbc:h2:~/IdeaProjects/assignment-10/test";
    private static final String USER_NAME = "roklimovich";
    private static final String PASSWORD = "rokl";
    private Connection connection;

    public UserRepository() {
        try {
            connection = DriverManager.getConnection(URL, USER_NAME, PASSWORD);
            connection.setAutoCommit(false);
        } catch (SQLException e) {
            System.out.println("Can't connect to database (user) \n" + e.getMessage());
        }
    }

    /**
     * Method to get connection to database
     *
     * @return object of type Connection
     */
    @Override
    public Connection getConnection() {

        return connection;
    }

    /**
     * Method adds users to database
     *
     * @param dto object of type UserDTO which should be added to database
     */
    @Override
    public void add(UserDTO dto) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "INSERT INTO USERS (USER_ID, USER_LOGIN, USER_PASSWORD) " +
                            "VALUES ( ?, ?, ? )"
            );
            preparedStatement.setInt(1, dto.getId());
            preparedStatement.setString(2, dto.getLogin());
            preparedStatement.setString(3, dto.getPassword());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Can't add the user to data base \n" + e.getMessage());
        }
    }

    /**
     * Method updates user in database
     *
     * @param dto object of type UserDTO which should be updated in database
     */
    @Override
    public void update(UserDTO dto) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "UPDATE USERS " +
                            "SET USER_LOGIN = ?, USER_PASSWORD = ? " +
                            "WHERE USER_ID = ?"
            );

            preparedStatement.setString(1, dto.getLogin());
            preparedStatement.setString(2, dto.getPassword());
            preparedStatement.setInt(3, dto.getId());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Can't update user database \n" + e.getMessage());
        }
    }

    /**
     * Method add or update user in database
     *
     * @param dto object of type UserDTO which should be updated or added in database
     */
    @Override
    public void addOrUpdate(UserDTO dto) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "MERGE INTO USERS USING (VALUES (?, ?, ?)) " +
                            "ON USERS.USER_ID = ? " +
                            "WHEN MATCHED THEN UPDATE SET " +
                            "USER_LOGIN = ?, USER_PASSWORD = ? " +
                            "WHEN NOT MATCHED THEN INSERT (USER_ID, USER_LOGIN, USER_PASSWORD) " +
                            "VALUES (?, ?, ?)"
            );

            preparedStatement.setInt(1, dto.getId());
            preparedStatement.setString(2, dto.getLogin());
            preparedStatement.setString(3, dto.getPassword());
            preparedStatement.setInt(4, dto.getId());
            preparedStatement.setString(5, dto.getLogin());
            preparedStatement.setString(6, dto.getPassword());
            preparedStatement.setInt(7, dto.getId());
            preparedStatement.setString(8, dto.getLogin());
            preparedStatement.setString(9, dto.getPassword());

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Can't add or update user in database \n" + e.getMessage());
        }
    }

    /**
     * Method delete user in database
     *
     * @param dto object of type UserDTO which should be deleted
     */
    @Override
    public void delete(UserDTO dto) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "DELETE FROM USERS " +
                            "WHERE USER_ID = ?"
            );
            preparedStatement.setInt(1, dto.getId());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Can't delete user in database \n" + e.getMessage());
        }
    }

    /**
     * Method finds users in database by given id
     *
     * @param id variable of type int, which contains info about id
     * @return object of type UserDTO
     */
    @Override
    public UserDTO findById(int id) {
        UserDTO userDTO = null;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT USER_LOGIN, USER_PASSWORD " +
                            "FROM USERS WHERE USER_ID = ?"
            );
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            String userLogin;
            String userPassword;

            if (resultSet.next()) {
                userLogin = resultSet.getString("USER_LOGIN");
                userPassword = resultSet.getString("USER_PASSWORD");
                userDTO = new UserDTO(id, userLogin, userPassword);
            }

        } catch (SQLException e) {
            System.out.println("Can't find user by id \n" + e.getMessage());
        }

        return userDTO;
    }

    /**
     * Method begins transaction to database
     */
    @Override
    public void beginTransaction() {
        try {
            connection.setSavepoint();
        } catch (SQLException e) {
            System.out.println("Can't begin transaction \n" + e.getMessage());
        }
    }

    /**
     * Method commits transaction to database
     */
    @Override
    public void commitTransaction() {
        try {
            connection.commit();
        } catch (SQLException e) {
            System.out.println("Can't commit transaction \n" + e.getMessage());
        }
    }

    /**
     * Method rollbacks transaction to database
     */
    @Override
    public void rollbackTransaction() {
        try {
            connection.rollback();
        } catch (SQLException e) {
            System.out.println("Can't rollback transaction \n" + e.getMessage());
        }
    }

    /**
     * Method count users in database
     *
     * @return object of type int with info, about how much users there is
     */
    @Override
    public int getCount() {
        int counter = 0;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT count(1) FROM USERS"
            );

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                counter = resultSet.getInt(1);
            }
        } catch (SQLException e) {
            System.out.println("Can't get count from user database \n" + e.getMessage());
        }

        return counter;
    }

    /**
     * Method checks if given user is already exists in database or not
     *
     * @param dto object of type UserDTO which should be checked
     * @return true, if exists, and false, if not
     */
    @Override
    public boolean exists(UserDTO dto) {
        boolean exists = false;

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT count(1) FROM USERS " +
                            "WHERE USER_ID = ? AND USER_LOGIN = ? AND USER_PASSWORD = ?"
            );
            preparedStatement.setInt(1, dto.getId());
            preparedStatement.setString(2, dto.getLogin());
            preparedStatement.setString(3, dto.getPassword());

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next() && resultSet.getInt(1) > 0) {
                exists = true;
            }
        } catch (SQLException e) {
            System.out.println("Can't check exists user or not \n" + e.getMessage());
        }

        return exists;
    }

    /**
     * Method finds users in database by given username
     *
     * @param username string variable which contains name of user
     * @return list of users with given name
     */
    @Override
    public List<UserDTO> findByName(String username) {
        List<UserDTO> userDTOList = new ArrayList<>();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT USER_ID, USER_LOGIN, USER_PASSWORD " +
                            "FROM USERS " +
                            "WHERE USER_LOGIN LIKE ?"
            );

            preparedStatement.setString(1, "%" + username + "%");

            ResultSet resultSet = preparedStatement.executeQuery();

            int userId;
            String userLogin;
            String userPassword;

            while (resultSet.next()) {
                userId = resultSet.getInt("USER_ID");
                userLogin = resultSet.getString("USER_LOGIN");
                userPassword = resultSet.getString("USER_PASSWORD");
                userDTOList.add(new UserDTO(userId, userLogin, userPassword));
            }

        } catch (SQLException e) {
            System.out.println("Can't find users by name in database \n" + e.getMessage());
        }

        return userDTOList;
    }

}
