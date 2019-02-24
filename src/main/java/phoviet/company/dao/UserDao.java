package phoviet.company.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import phoviet.company.entity.User;


public interface UserDao extends CrudRepository<User,Integer>{
	User findByUsername(String username);
	User findByPassword(String password);
	User findByUsernameAndPassword(String username, String password);
	User findById(int userId);
	
	/*@Query(value = "SELECT e FROM User e WHERE e.username =" + "?1" + "AND e.password =" + "?2")
	User findUsernamePass(String username, String password);*/
}
