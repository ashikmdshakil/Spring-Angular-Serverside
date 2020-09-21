package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.StudentRole;

public interface StudentRoleRepo extends JpaRepository<StudentRole, Integer> {
	public boolean deleteByStudent_Id(int id);
}
