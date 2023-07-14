package com.innowise.repository;

import com.innowise.model.User;
import com.innowise.repository.utils.RepositoryUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class JdbcUserRepository implements UserRepository {
    private final JdbcTemplate jdbcTemplate;
    private final RepositoryUtils repositoryUtils;
    private final String selectAllFromUserPoolOrderByIdAscendingQuery;
    private final String selectAllFromUserPoolWhereIdQuery;
    private final String selectAllFromUserPoolWhereUsernameQuery;
    private final String insertNewUserIntoUserPoolQuery;
    private final String updateUserWhereIdQuery;
    private final String deleteUserWhereIdQuery;
    private final String noSuchUserMessage;

    @Autowired
    public JdbcUserRepository(JdbcTemplate jdbcTemplate,
                              RepositoryUtils repositoryUtils,
                              @Value("${crud-queries.select-all-from-user-pool-order-by-id-ascending}") String selectAllFromUserPoolOrderByIdAscendingQuery,
                              @Value("${crud-queries.select-all-from-user-pool-where-id}") String selectAllFromUserPoolWhereIdQuery,
                              @Value("${crud-queries.select-all-from-user-pool-where-username}") String selectAllFromUserPoolWhereUsernameQuery,
                              @Value("${crud-queries.insert-new-user-into-user-pool}") String insertNewUserIntoUserPoolQuery,
                              @Value("${crud-queries.update-user-where-id}") String updateUserWhereIdQuery,
                              @Value("${crud-queries.delete-user-where-id}") String deleteUserWhereIdQuery,
                              @Value("${response-messages.failure.no-such-user}") String noSuchUserMessage)
    {
        this.jdbcTemplate = jdbcTemplate;
        this.repositoryUtils = repositoryUtils;
        this.selectAllFromUserPoolOrderByIdAscendingQuery = selectAllFromUserPoolOrderByIdAscendingQuery;
        this.selectAllFromUserPoolWhereIdQuery = selectAllFromUserPoolWhereIdQuery;
        this.selectAllFromUserPoolWhereUsernameQuery = selectAllFromUserPoolWhereUsernameQuery;
        this.insertNewUserIntoUserPoolQuery = insertNewUserIntoUserPoolQuery;
        this.updateUserWhereIdQuery = updateUserWhereIdQuery;
        this.deleteUserWhereIdQuery = deleteUserWhereIdQuery;
        this.noSuchUserMessage = noSuchUserMessage;
    }

    @Override
    @Transactional
    public List<User> findAll() {
        return jdbcTemplate.query(selectAllFromUserPoolOrderByIdAscendingQuery, repositoryUtils::mapRowToUser);
    }

    @Override
    @Transactional
    public User findById(Long id) {
        return jdbcTemplate.queryForObject(selectAllFromUserPoolWhereIdQuery, repositoryUtils::mapRowToUser, id);
    }

    @Override
    @Transactional
    public User findByUsername(String username) {
        try {
            return jdbcTemplate.queryForObject(selectAllFromUserPoolWhereUsernameQuery, repositoryUtils::mapRowToUser, username);
        } catch (DataAccessException e) {
            throw new BadCredentialsException(noSuchUserMessage);
        }
    }

    @Override
    @Transactional
    public User save(User user) {
        jdbcTemplate.update(insertNewUserIntoUserPoolQuery, repositoryUtils.registrationPreparedStatement(user));
        return user;
    }

    @Override
    @Transactional
    public User update(User user) {
        jdbcTemplate.update(updateUserWhereIdQuery, repositoryUtils.updatePreparedStatement(user));
        return user;
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        jdbcTemplate.update(deleteUserWhereIdQuery, id);
    }
}
