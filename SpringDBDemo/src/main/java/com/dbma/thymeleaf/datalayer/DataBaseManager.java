package com.dbma.thymeleaf.datalayer;

//import com.dbma.thymeleaf.model.*;
import com.dbma.thymeleaf.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class DataBaseManager {
    JdbcTemplate template;

    @Autowired
    public DataBaseManager(DataSource dataSource) {
        this.template = new JdbcTemplate(dataSource);
    }

    public void createUser(User user) {
        String query = "INSERT INTO users VALUES(?, ?, ?, ?, ?, ?)";
        template.update(query,user.getId(), user.getFirstName(), user.getLastName(), user.getAge(),
                user.getEmail(), user.getPassword());
    }

    public User getUserById(String id) {
        String query = "SELECT * FROM users WHERE id = ?";
        return template.queryForObject(query, new UserRowMapper(), id);
    }

    public List<User> searchUserByFirstName(String firstName) {
        String query = "SELECT * FROM users WHERE first_name = ?";
        return template.query(query, new UserRowMapper(), firstName);
    }

}
