package nate.anderson.dao;

import java.util.List;
import java.time.LocalDate;
import java.util.ArrayList;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import nate.anderson.model.Project;
import nate.anderson.model.Task;
import nate.anderson.model.User;

@Component
public class JDBCUserDAO implements UserDAO {
	
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	public JDBCUserDAO(DataSource datasource) {
		this.jdbcTemplate = new JdbcTemplate(datasource);
	}
		
	@Override
	public User getUserByCredentials(String username, String password) {
		String sqlQuery = "SELECT * " +
						  "FROM users " +
						  "WHERE username = ? AND password =?";
		
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlQuery, username, password);
		
		return mapResultsToUser(results).get(0);
	}

	@Override
	public void createNewUser(User user) {

		String sqlQuery = "INSERT INTO users (username, password, email, created_at) " +
					      "VALUES (?, ?, ?, NOW())";
		
		jdbcTemplate.update(sqlQuery, user.getUsername(), user.getPassword(), user.getEmail());
		
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











