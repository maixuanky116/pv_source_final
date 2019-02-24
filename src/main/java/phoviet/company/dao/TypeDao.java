package phoviet.company.dao;

import org.springframework.data.repository.CrudRepository;

import phoviet.company.entity.Type;

public interface TypeDao extends CrudRepository<Type,Integer>{
	Type findByName(String name);
	Type findById(int userId);
	
}
