package nate.anderson.dao;

import nate.anderson.model.User;

public interface UserDAO {

	public User getUserByCredentials(String username, String password);
	
	public void createNewUser(User user);
	
}
