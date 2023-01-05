package com.project.management.projectmanagement.service;

import java.util.Date;
import java.util.List;

import com.project.management.projectmanagement.model.User;
import com.project.management.projectmanagement.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private BCryptPasswordEncoder bcryptEncoder;
	
	public List<User> getAllUsers() {
		return userRepository.findAllByOrderByDisplayNameAsc();
	}
	
	
	public List<User> getAllActiveUsers() {
		return userRepository.findAllByActiveOrderByDisplayNameAsc(1);
	}
	
	public User getByUsername(String username) {
		return userRepository.findByUsername(username);
	}

	public User getById(Long id) {
		return userRepository.findById(id).get();
	}
	public User addNew(User user) {
		user.setPassword( bcryptEncoder.encode(user.getPassword()) );
		user.setCreatedDate( new Date() );
		user.setLastModifiedDate( user.getCreatedDate() );
		user.setActive(1);
		return userRepository.save(user);
	}
	
	public User update(User user) {
		user.setLastModifiedDate( new Date() );
		return userRepository.save( user );
	}
	public User save(User member) {
		return userRepository.save( member );
	}
	public boolean hasUsage(User user) {
//		return issueService.getCountByMember(user) > 0;
		return false;
	}
	public void delete(User user) {
		userRepository.delete(user);
	}
	
	public void delete(Long id) {
		userRepository.deleteById(id);
	}
}
