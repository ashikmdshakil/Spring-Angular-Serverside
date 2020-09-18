package com.example.demo.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Role;
import com.example.demo.model.Student;
import com.example.demo.model.StudentRole;
import com.example.demo.repository.StudentRoleRepo;
import com.example.demo.repository.jpaRepository;

@Controller
@Scope("prototype")
public class AppController {
	@Autowired
	private jpaRepository repo;
	@Autowired
	private Student student;
	@Autowired
	private StudentRole role;
	@Autowired
	private StudentRoleRepo roleRepo;

	@RequestMapping("/")
	public String getHomePAge() {
		return "index";
	}

	@GetMapping("save")
	@CrossOrigin(origins = "*")
	@ResponseBody
	public String saveStudent(@RequestParam("password") String password ,@RequestParam("name") String name,@RequestParam("mail") String mail, @RequestParam("department") String department) {
		String message = null;
		System.out.println(name);
		System.out.println(password);
		try {
			student.setName(name);
			student.setMail(mail);
			student.setDepartment(department);
			student.setPassword(password);
			role.setRole_name("user");
			repo.save(student);
			student = null;
			student = repo.findByMail(mail);
			role.setStudent(student);
			roleRepo.save(role);
			message = "success";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			message = "wrong";
		}
		return message;
	}

	@RequestMapping("students")
	@CrossOrigin(origins = "*")
	@ResponseBody

	public List<Student> getStudents() {
		List<Student> students = repo.findAll();
		return students;
	}

	@RequestMapping("delete")
	@CrossOrigin(origins = "*")
	@ResponseBody
	public String deleteStudent(@RequestParam("id") int sid, @RequestParam("name") String name,
			@RequestParam("mail") String mail, @RequestParam("department") String department) {
		String result = null;
		try {
			student.setId(sid);
			student.setName(name);
			student.setMail(mail);
			student.setDepartment(department);
			repo.delete(student);
			result = "success";
		} catch (Exception e) {
			e.printStackTrace();
			result = "wrong";
		}
		return result;
	}
	
	@RequestMapping("update")
	@CrossOrigin(origins = "*")
	@ResponseBody
	public String updateStudent(@RequestParam("id") int sid, @RequestParam("name") String name,
			@RequestParam("mail") String mail, @RequestParam("department") String department,
			@RequestParam("password") String password) {
		String result = null;
		try {
			student.setId(sid);
			student.setName(name);
			student.setMail(mail);
			student.setDepartment(department);
			student.setPassword(password);
			repo.save(student);
			result = "success";
		} catch (Exception e) {
			e.printStackTrace();
			result = "wrong";
		}
		student = null;
		return result;
	}
	
	
	
	@RequestMapping("deleteId")
	@CrossOrigin(origins = "*")
	@ResponseBody
	public String deleteStudentById(@RequestParam("id") int sid) {
		String result = null;
		try {
			student.setId(sid);
			repo.deleteById(sid);
			System.out.println(sid);
			result = "success";
		} catch (Exception e) {
			e.printStackTrace();
			result = "wrong";
		}
		return result;
	}

	@RequestMapping("student")

	@CrossOrigin(origins = "*")

	@ResponseBody
	public Student getStudent(@RequestParam("id") int id) {
		
		student = repo.findById(id);
		return student;
	}
	
	@RequestMapping("login")
	@CrossOrigin(origins = "*")
	@ResponseBody
	  public Principal user(Principal student) {
	    return student;
	  }

}
