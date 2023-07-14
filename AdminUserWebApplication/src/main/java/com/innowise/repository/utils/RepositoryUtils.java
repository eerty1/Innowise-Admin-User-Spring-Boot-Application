package com.innowise.repository.utils;

import com.innowise.model.Role;
import com.innowise.model.User;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

import static com.innowise.repository.utils.TableColumns.*;

@Component
public class RepositoryUtils {

    public User mapRowToUser(ResultSet resultSet, int numRows) throws SQLException {
        return new User(
                resultSet.getLong(ID.getTableColumnValue()),
                resultSet.getString(USERNAME.getTableColumnValue()),
                resultSet.getString(PASSWORD.getTableColumnValue()),
                Role.valueOf(resultSet.getString(ROLE.getTableColumnValue())),
                resultSet.getString(FULL_NAME.getTableColumnValue()),
                resultSet.getString(POSITION.getTableColumnValue()),
                resultSet.getString(DEPARTMENT.getTableColumnValue()),
                resultSet.getString(ADDRESS.getTableColumnValue()),
                resultSet.getString(PHONE_NUMBER.getTableColumnValue())
        );
    }

    public PreparedStatementSetter registrationPreparedStatement(User user) {
        return preparedStatement -> {
            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, user.getRole().name());
            preparedStatement.setString(4, user.getFullName());
            preparedStatement.setString(5, user.getPosition());
            preparedStatement.setString(6, user.getDepartment());
            preparedStatement.setString(7, user.getAddress());
            preparedStatement.setString(8, user.getPhoneNumber());
        };
    }

    public PreparedStatementSetter updatePreparedStatement(User user) {
        return preparedStatement -> {
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
        };
    }
}
