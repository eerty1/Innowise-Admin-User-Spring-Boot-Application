package com.innowise.repository;

import com.innowise.model.Role;
import com.innowise.model.User;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class JdbcUserRepository implements UserRepository {
    private final Connection connection;
    private static final String SELECT_ALL_FROM_USER_POOL_ORDER_BY_ID_ASCENDING = "select * from user_pool order by id asc";
    private static final String SELECT_ALL_FROM_USER_POOL_WHERE_ID = "select * from user_pool where id=?";
    private static final String SELECT_ALL_FROM_USER_POOL_WHERE_USERNAME = "select * from user_pool where username = ?";
    private static final String INSERT_INTO_USER_POOL_NEW_USER = "insert into user_pool " +
            "(username, password, role, full_name, position, department, address, phone_number) values(?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String UPDATE_USER_POOL_WHERE_ID = "update user_pool " +
            "set id = ?, username = ?, password = ?, role = ?, full_name = ?, position = ?, department = ?, address = ?, phone_number = ? where id = ?";
    private static final String DELETE_FROM_USER_POOL_WHERE_ID = "delete from user_pool where id = ?";
    private static final String ID = "id";
    private static final String USERNAME = "username";
    private static final String PASSWORD = "password";
    private static final String ROLE = "role";
    private static final String FULL_NAME = "full_name";
    private static final String POSITION = "position";
    private static final String DEPARTMENT = "department";
    private static final String ADDRESS = "address";
    private static final String PHONE_NUMBER = "phone_number";

    public JdbcUserRepository(DataSource dataSource) throws SQLException {
        this.connection = dataSource.getConnection();
    }

    @Override
    public List<User> findAll() {
        List<User> resultingUsers = new ArrayList<>();

        try(Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(SELECT_ALL_FROM_USER_POOL_ORDER_BY_ID_ASCENDING)) {
            while (resultSet.next()) {
                resultingUsers.add(mapRowToUser(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return resultingUsers;
    }

    @Override
    public User findById(Long id) {
        User resultingUser = null;

        try(PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_FROM_USER_POOL_WHERE_ID)) {
            preparedStatement.setLong(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    resultingUser = mapRowToUser(resultSet);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }  catch (SQLException e) {
            e.printStackTrace();
        }

        return resultingUser;
    }

    @Override
    public User findByUsername(String username) {
        User resultingUser = null;

        try(PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_FROM_USER_POOL_WHERE_USERNAME)) {
            preparedStatement.setString(1, username);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    resultingUser = mapRowToUser(resultSet);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }  catch (SQLException e) {
            e.printStackTrace();
        }

        return resultingUser;
    }

    @Override
    public User save(User user) {
        if (user != null) {
            try(PreparedStatement preparedStatement = connection.prepareStatement(INSERT_INTO_USER_POOL_NEW_USER)) {
                preparedStatement.setString(1, user.getUsername());
                preparedStatement.setString(2, user.getPassword());
                preparedStatement.setString(3, user.getRole().name());
                preparedStatement.setString(4, user.getFullName());
                preparedStatement.setString(5, user.getPosition());
                preparedStatement.setString(6, user.getDepartment());
                preparedStatement.setString(7, user.getAddress());
                preparedStatement.setString(8, user.getPhoneNumber());
                preparedStatement.executeUpdate();
                try(ResultSet resultSet = preparedStatement.getGeneratedKeys()) {
                    if (resultSet.next()) {
                        user.setId(resultSet.getLong(1));
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return user;
    }

    @Override
    public User update(User user) {
        try(PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_USER_POOL_WHERE_ID)){
            preparedStatement.setLong(1, user.getId());
            preparedStatement.setString(2, user.getUsername());
            preparedStatement.setString(3, user.getPassword());
            preparedStatement.setString(4, user.getRole().name());
            preparedStatement.setString(5, user.getFullName());
            preparedStatement.setString(6, user.getPosition());
            preparedStatement.setString(7, user.getDepartment());
            preparedStatement.setString(8, user.getAddress());
            preparedStatement.setString(9, user.getPhoneNumber());
            preparedStatement.setLong(10, user.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public void deleteById(Long id) {
        try(PreparedStatement preparedStatement = connection.prepareStatement(DELETE_FROM_USER_POOL_WHERE_ID)){
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static User mapRowToUser(ResultSet resultSet) throws SQLException {
        return new User(
                resultSet.getLong(ID),
                resultSet.getString(USERNAME),
                resultSet.getString(PASSWORD),
                Role.valueOf(resultSet.getString(ROLE)),
                resultSet.getString(FULL_NAME),
                resultSet.getString(POSITION),
                resultSet.getString(DEPARTMENT),
                resultSet.getString(ADDRESS),
                resultSet.getString(PHONE_NUMBER)
        );
    }
}
