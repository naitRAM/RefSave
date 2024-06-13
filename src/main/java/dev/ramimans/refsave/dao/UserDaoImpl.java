package dev.ramimans.refsave.dao;

import dev.ramimans.refsave.dao.mapper.UserDao;
import dev.ramimans.refsave.dto.User;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class UserDaoImpl implements UserDao {
    @Autowired
    JdbcTemplate jdbc;


    public UserDaoImpl(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    @Override
    public User readUser (String username) {
        String query = "Select * From Users Where username = ?";
        return jdbc.queryForObject(query, new UserMapper(), username);
    }

    @Override
    public void createUser (User user) {
        String query = "Insert Into Users (username, passphrase, id) Values (?, ?, ?)";
        jdbc.update(query, user.getUsername(), user.getPassword(), user.getId());
    }

    private static class UserMapper implements RowMapper<User> {

        @Override
        public User mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new User(rs.getString("username"),
                    rs.getString("passphrase"), rs.getString("id"));
        }
    }
}
