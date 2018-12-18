package com.hemanshu;

import org.springframework.data.repository.CrudRepository;

public interface TeamRepo extends CrudRepository<Team, Integer> {

	
	public Team findById(int i);
}
