package tangzq.service;

import java.util.List;

import tangzq.model.User;

public interface UserServiceI {

	public User getUserById(String id);

	List<User> getAll();

	List<User> getAll2();

	List<User> getAll3();

    int insert(User record);

    int updateUser(User record);
}
