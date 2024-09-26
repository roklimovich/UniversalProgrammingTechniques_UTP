package pjwstk.edu.pl.s27619.repositories;

import pjwstk.edu.pl.s27619.dtos.GroupDTO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GroupRepository implements IGroupRepository {
    private static final String URL = "jdbc:h2:~/IdeaProjects/assignment-10/test";
    private static final String USER_NAME = "roklimovich";
    private static final String PASSWORD = "rokl";
    private Connection connection;

    public GroupRepository() {
        try {
            connection = DriverManager.getConnection(URL, USER_NAME, PASSWORD);
            connection.setAutoCommit(false);
        } catch (SQLException e) {
            System.out.println("Can't connect to database (group) \n" + e.getMessage());
        }
    }

    /**
     * Method finds groups by given name
     *
     * @param name string variable which contains name of group
     * @return list of groups
     */
    @Override
    public List<GroupDTO> findByName(String name) {
        List<GroupDTO> groupDTOList = new ArrayList<>();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT GROUP_ID, GROUP_NAME, GROUP_DESCRIPTION " +
                            "FROM GROUPS " +
                            "WHERE GROUP_NAME LIKE ?"
            );
            preparedStatement.setString(1, name + "%");

            ResultSet resultSet = preparedStatement.executeQuery();

            int id;
            String groupName;
            String groupDescription;

            while (resultSet.next()) {
                id = resultSet.getInt("GROUP_ID");
                groupName = resultSet.getString("GROUP_NAME");
                groupDescription = resultSet.getString("GROUP_DESCRIPTION");
                groupDTOList.add(new GroupDTO(id, groupName, groupDescription));
            }
        } catch (SQLException e) {
            System.out.println("Can't find groups by name \n" + e.getMessage());
        }

        return groupDTOList;
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
     * Method adds groups to database
     *
     * @param dto object of type GroupDTO which should be added to database
     */
    @Override
    public void add(GroupDTO dto) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "INSERT INTO GROUPS (GROUP_ID, GROUP_NAME, GROUP_DESCRIPTION)" +
                            "VALUES (?, ?, ?)"
            );
            preparedStatement.setInt(1, dto.getId());
            preparedStatement.setString(2, dto.getName());
            preparedStatement.setString(3, dto.getDescription());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Can't insert groups to database \n" + e.getMessage());
        }
    }

    /**
     * Method updates groups in database
     *
     * @param dto object of type GroupDTO which should be updated in database
     */
    @Override
    public void update(GroupDTO dto) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "UPDATE GROUPS " +
                            "SET GROUP_NAME = ?, GROUP_DESCRIPTION = ?" +
                            "WHERE GROUP_ID = ?"
            );
            preparedStatement.setString(1, dto.getName());
            preparedStatement.setString(2, dto.getDescription());
            preparedStatement.setInt(3, dto.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Can't update groups in database \n" + e.getMessage());
        }
    }

    /**
     * Method add or update groups in database
     *
     * @param dto object of type GroupDTO which should be updated or added in database
     */
    @Override
    public void addOrUpdate(GroupDTO dto) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "MERGE INTO GROUPS USING (VALUES (?, ?, ?)) " +
                            "ON GROUPS.GROUP_ID = ? " +
                            "WHEN MATCHED THEN UPDATE SET " +
                            "GROUP_NAME = ?, GROUP_DESCRIPTION = ? " +
                            "WHEN NOT MATCHED THEN INSERT (GROUP_ID, GROUP_NAME, GROUP_DESCRIPTION) " +
                            "VALUES (?, ?, ?)"
            );

            preparedStatement.setInt(1, dto.getId());
            preparedStatement.setString(2, dto.getName());
            preparedStatement.setString(3, dto.getDescription());
            preparedStatement.setInt(4, dto.getId());
            preparedStatement.setString(5, dto.getName());
            preparedStatement.setString(6, dto.getDescription());
            preparedStatement.setInt(7, dto.getId());
            preparedStatement.setString(8, dto.getName());
            preparedStatement.setString(9, dto.getDescription());

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Can't add or update group \n" + e.getMessage());
        }
    }

    /**
     * Method delete group in database
     *
     * @param dto object of type GroupDTO which should be deleted
     */
    @Override
    public void delete(GroupDTO dto) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "DELETE FROM GROUPS " +
                            "WHERE GROUP_ID = ? "
            );
            preparedStatement.setInt(1, dto.getId());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Can't delete group \n" + e.getMessage());
        }
    }

    /**
     * Method finds groups in database by given id
     *
     * @param id variable of type int, which contains info about id
     * @return object of type GroupDTO
     */
    @Override
    public GroupDTO findById(int id) {
        GroupDTO groupDTO = null;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT GROUP_NAME, GROUP_DESCRIPTION " +
                            "FROM GROUPS WHERE GROUP_ID = ?"
            );

            preparedStatement.setInt(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();
            String groupName;
            String groupDescription;

            if (resultSet.next()) {
                groupName = resultSet.getString("GROUP_NAME");
                groupDescription = resultSet.getString("GROUP_DESCRIPTION");
                groupDTO = new GroupDTO(id, groupName, groupDescription);
            }

        } catch (SQLException e) {
            System.out.println("Can't find group by ID \n" + e.getMessage());
        }

        return groupDTO;
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
     * Method count groups in database
     *
     * @return object of type int with info, about how much groups there is
     */
    @Override
    public int getCount() {
        int counter = 0;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT count(1) FROM GROUPS"
            );

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                counter = resultSet.getInt(1);
            }
        } catch (SQLException e) {
            System.out.println("Can't get counter from database \n" + e.getMessage());
        }

        return counter;
    }

    /**
     * Method checks if given groups is already exists in database or not
     *
     * @param dto object of type GroupDTO which should be checked
     * @return true, if exists, and false, if not
     */
    @Override
    public boolean exists(GroupDTO dto) {
        boolean exists = false;

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT count(1) FROM GROUPS " +
                            "WHERE GROUP_ID = ? AND GROUP_NAME = ? AND GROUP_DESCRIPTION = ?"
            );
            preparedStatement.setInt(1, dto.getId());
            preparedStatement.setString(2, dto.getName());
            preparedStatement.setString(3, dto.getDescription());

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next() && resultSet.getInt(1) > 0) {
                exists = true;
            }
        } catch (SQLException e) {
            System.out.println("Can't check exists group or not \n" + e.getMessage());
        }
        return exists;
    }

}
