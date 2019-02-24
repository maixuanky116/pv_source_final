package phoviet.company.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import phoviet.company.entity.Product;

public interface ProductDao extends CrudRepository<Product,Integer>{
	Product findByName(String name);
	Product findById(int userId);
	
	List<Product> findByType(String type);
	
	@Query(value = "SELECT * FROM pv_products ORDER BY id DESC limit 4" , nativeQuery = true)
	List<Product> listProduct();
}

