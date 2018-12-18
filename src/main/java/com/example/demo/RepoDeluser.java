package com.example.demo;


import org.springframework.data.repository.CrudRepository;

public interface RepoDeluser extends CrudRepository<User, Integer> {
	
	public User findById(int id);



}