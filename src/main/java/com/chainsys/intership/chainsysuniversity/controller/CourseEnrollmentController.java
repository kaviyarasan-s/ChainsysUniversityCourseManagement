package com.chainsys.intership.chainsysuniversity.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.apache.commons.mail.EmailException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.chainsys.intership.chainsysuniversity.model.Course;
import com.chainsys.intership.chainsysuniversity.model.CourseEnrollment;
import com.chainsys.intership.chainsysuniversity.model.User;
import com.chainsys.intership.chainsysuniversity.service.Services;

@RestController("/api")
public class CourseEnrollmentController {

	@Autowired
	Services services;
	
	@GetMapping("/displaycourseswithstatus")
	public List<CourseEnrollment> displayEnrolledCourseById(@RequestParam("userId") int userId,@RequestParam("status") String status)
	{
		CourseEnrollment courseEnrollment=new CourseEnrollment();
		User user=new User();
		user.setId(userId);
		courseEnrollment.setUser(user);
		courseEnrollment.setStatus(status.trim().toLowerCase());
		List<CourseEnrollment> courseList=services.displayUserCoursesById(courseEnrollment);
		return courseList;
	}
	
	@GetMapping("/displaycourseswithoutstatus")
	public List<CourseEnrollment> displayEnrolledCourseById(@RequestParam("userId") int userId)
	{
		CourseEnrollment courseEnrollment=new CourseEnrollment();
		User user=new User();
		user.setId(userId);
		courseEnrollment.setUser(user);		
		List<CourseEnrollment> courseList=services.displayUserCoursesById(courseEnrollment);
		return courseList;
	}
	
	@PostMapping("/enrollcourse")
	public String courseEnrollment(@RequestParam("userId") int userId,
			@RequestParam("courseId") int courseId) {

		CourseEnrollment courseEnrollment = new CourseEnrollment();
		Course course = new Course();
		course.setId(courseId);
		User user = new User();
		user.setId(userId);		
		courseEnrollment.setCourse(course);
		courseEnrollment.setUser(user);
		LocalDateTime courseStartDate=LocalDateTime.now();
		courseEnrollment.setStartDate(courseStartDate);
		courseEnrollment.setEndDate(null);
		courseEnrollment.setStatus("enrolled");		
		String message=services.courseEnrollment(courseEnrollment);
		return message;
	}
	

	@PostMapping("/completecourse")
	public String courseComplete(@RequestParam("userId") int userId,
			@RequestParam("courseId") int courseId) throws EmailException {

		CourseEnrollment courseEnrollment = new CourseEnrollment();
		Course course = new Course();
		course.setId(courseId);
		User user = new User();
		user.setId(userId);		
		courseEnrollment.setCourse(course);
		courseEnrollment.setUser(user);
		LocalDateTime courseEndDate=LocalDateTime.now();		
		courseEnrollment.setEndDate(courseEndDate);
		courseEnrollment.setStatus("completed");		
		String message=services.courseComplete(courseEnrollment);
		return message;
	}
	
	

}
