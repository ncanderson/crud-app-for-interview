package nate.anderson.dao;

import java.util.List;
import java.time.LocalDate;
import java.util.ArrayList;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import nate.anderson.model.User;

@Component
//@Transactional
public class JDBCUserDAO implements UserDAO {
	
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	public JDBCUserDAO(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
		
	@Override
	public User getUserByCredentials(String username, String password) {
		
		String sqlQuery = "SELECT * " +
						  "FROM users " +
						  "WHERE username = ? AND password =?";
		
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlQuery, username, password);
		
		return mapResultsToUser(results).get(0);
	}
	
	public boolean validUser(String username, String password) {
		
		String sqlQuery = "SELECT * " +
						  "FROM users " +
						  "WHERE UPPER(username) = ?";
		
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlQuery, username.toUpperCase());
		
		if (results.next()) {
			String storedPassword = results.getString("password");
			return password.equals(storedPassword);
		}
		else {
			return false;
		}
	}
	
	public User getUserById(int id) {
		
		String sqlQuery = "SELECT * " +
				  		  "FROM users " +
				  		  "WHERE user_id = ?";

		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlQuery, id);

		return mapResultsToUser(results).get(0);
	}

	@Override
	public void createNewUser(User user) {

		String sqlQuery = "INSERT INTO users (username, password, email, created_at, updated_at) " +
					      "VALUES (?, ?, ?, NOW(), NOW())";
		
		jdbcTemplate.update(sqlQuery, user.getUsername(), user.getPassword(), user.getEmail());
		
	}
	
	@Override
	public void updateUserInformation(User user) {
		
		String sqlQuery = "UPDATE users " +
						  "SET username = ?, " +
						      "password = ?, " +
						      "email = ?, "  +
						      "updated_at = NOW() " +
						   "WHERE user_id = ?";

		jdbcTemplate.update(sqlQuery, user.getUsername(), user.getPassword(), user.getEmail(), user.getUserId());
	}

	
	private List<User> mapResultsToUser(SqlRowSet results) {
		List<User> userList = new ArrayList<User>();
		 
		while (results.next()) {
			User user = new User();
			user.setUserId(results.getInt("user_id"));
			user.setUsername(results.getString("username"));
			user.setPassword(results.getString("password"));
			user.setEmail(results.getString("email"));
			user.setCreatedAt(results.getDate("created_at").toLocalDate());
			user.setUpdatedAt(results.getDate("updated_at").toLocalDate());				
			userList.add(user);
		}
	
		return userList;
	}
		
}











