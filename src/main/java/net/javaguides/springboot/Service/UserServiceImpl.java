package net.javaguides.springboot.Service;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.javaguides.springboot.Model.User;
import net.javaguides.springboot.Repository.UserRepository;

@Service
@Transactional
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private EntityManager entityManager;
	
	@Override
	public List<User> getAllUsers() {
		return userRepository.findAll();
	}

	@Override
	public void saveUser(User user) {
		this.userRepository.save(user);
	}

	@Override
	public User getUserById(long id) {
		Optional<User> optional = userRepository.findById(id);
		User user = null;
		if(optional.isPresent())
		{
			user = optional.get();
		}
		else
		{
			throw new RuntimeException("User is not found");
		}
		return user;
	}

	@Override
	public User getUserLoginAuth(String email, String password) {
		Query query = entityManager.createQuery("select u from User u where u.email = '"+email+"'and u.password = '"+password+"'");
		User user = (User) query.getSingleResult();
		return user;
	}
	
	public void changeStatus(long id,String status)
	{
		Query query = entityManager.createQuery("update User u set u.status ='"+status+"' where u.userId = "+id);
		query.executeUpdate();
	}

}
