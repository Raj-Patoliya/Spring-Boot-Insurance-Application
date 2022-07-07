package net.javaguides.springboot.Service;

import java.util.List;

import net.javaguides.springboot.Model.User;

public interface UserService {
	List<User> getAllUsers();
	void saveUser(User user);
	User getUserById(long id);
	User getUserLoginAuth(String email,String password);
	void changeStatus(long id,String status);

}
