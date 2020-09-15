package com.example.demo.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.model.Student;
import com.example.demo.repository.jpaRepository;
@Service
public class ApplicationUserDetailsService implements UserDetailsService{
	@Autowired
	private Student student;
	@Autowired
	private jpaRepository studentRepo;
	
	@Override
	public UserDetails loadUserByUsername(String mail) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		student = studentRepo.findByMail(mail);
		return new ApplicationUserDetails(student);
	}

}
