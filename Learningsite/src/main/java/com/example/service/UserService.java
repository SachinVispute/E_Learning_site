package com.example.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.model.User;
import com.example.repository.UserRepository;
@Service
public class UserService 
{
	@Autowired
	private UserRepository userRepo;
	
	public void saveUser(User user)
	{
		userRepo.save(user);
	}
	
	public List<User> getAllUsers()
	{
		return userRepo.findAll();
	}
	
	public boolean emailExists(String email) 
	 {
	        return userRepo.existsByEmail(email);
	 }
	public Boolean userLogin(String email, String password) 
	{        
        Optional<User> user = userRepo.findByEmail(email);        
        if (user.isPresent()) {
            User u1 = user.get();
            if (u1.getPassword().equals(password)) {
                return true;
            }
        }
        return false;
    }
}
