package com.chainsys.intership.chainsysuniversity.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.chainsys.intership.chainsysuniversity.dao.CourseDAO;
import com.chainsys.intership.chainsysuniversity.model.Course;
import com.chainsys.intership.chainsysuniversity.service.Services;

@RestController
@RequestMapping("/api")
public class CourseController {

	@Autowired
	Services services;
	
	@Autowired
	CourseDAO courseDAO;
	
	@PostMapping("/addcourse")
	public String addCourse(@RequestParam("courseCode") String courseCode,
			@RequestParam("courseName") String courseName) {

		Course course=new Course();
		course.setCode(courseCode.trim());
		course.setName(courseName.trim());
		String message=services.addCourse(course);
		return message;
	}
	
	@GetMapping("/listcourse")
	public List<Course> listCourse() {
		
		List<Course> courseList=courseDAO.listCourse();
		return courseList;
	}

}
