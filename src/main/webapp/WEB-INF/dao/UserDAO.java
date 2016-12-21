package nate.anderson.dao;

import nate.anderson.model.User;

public interface UserDAO {

	public User getUserByCredentials(String username, String password);
	
	public boolean validUser(String username, String password);
	
	public User getUserById(int id);
	
	public void createNewUser(User user);
	
	public void updateUserInformation(User user);
	
}
