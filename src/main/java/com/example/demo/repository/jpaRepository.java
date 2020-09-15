package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Student;

public interface jpaRepository extends JpaRepository<Student, Integer>{
	Student findById(int id);
	Student findByMail(String mail);

}
