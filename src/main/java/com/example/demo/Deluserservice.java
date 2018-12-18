package com.example.demo;


import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

@Service
@Transactional
public class Deluserservice {

	 	
	 private final RepoDeluser RepoDeluser12;
		
		public Deluserservice(RepoDeluser RepoDeluser12) {
			this.RepoDeluser12=RepoDeluser12;
		}
		
			
		public List<User> showAllUsers(){
			List<User> users = new ArrayList<User>();
			for(User user : RepoDeluser12.findAll()) {
				users.add(user);
			}
			
			return users;
		}
		
		public void deleteMyUser(int id) {
			RepoDeluser12.deleteById(id);
		}
	 
	 
	 
	 
	 
}
