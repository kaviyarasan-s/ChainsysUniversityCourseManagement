package com.chainsys.intership.chainsysuniversity.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chainsys.intership.chainsysuniversity.dao.CourseDAO;
import com.chainsys.intership.chainsysuniversity.dao.CourseEnrollmentDAO;
import com.chainsys.intership.chainsysuniversity.model.Course;
import com.chainsys.intership.chainsysuniversity.model.CourseEnrollment;

@Service
public class Services {
	@Autowired
	CourseDAO courseDAO;
	@Autowired
	CourseEnrollmentDAO courseEnrollmentDAO;

	public String addCourse(Course course) {
		int courseInsertResult = courseDAO.addCourse(course);
		String courseInsertMessage = null;
		if (courseInsertResult > 0)
			courseInsertMessage = "Course added successfully.";
		else
			courseInsertMessage = "Course added failed.";

		return courseInsertMessage;
	}

	public String courseEnrollment(CourseEnrollment courseEnrollment) {
		int courseEnrolledResult = courseEnrollmentDAO
				.courseEnrollment(courseEnrollment);
		String courseEnrolledMessage = null;
		if (courseEnrolledResult > 0)
			courseEnrolledMessage = "Course Enrolled successfully.";
		else
			courseEnrolledMessage = "Course Enrollment failed.";

		return courseEnrolledMessage;
	}

	public List<CourseEnrollment> displayUserCoursesById(
			CourseEnrollment courseEnrollment) {

		List<CourseEnrollment> courseEnrollmentList = null;
		String status = courseEnrollment.getStatus();
		if (status == null) {

			courseEnrollmentList = courseEnrollmentDAO
					.getUserCourseIdWithOutStatus(courseEnrollment);
		} else {

			courseEnrollmentList = courseEnrollmentDAO
					.getUserCourseIdWithStatus(courseEnrollment);
		}

		for (CourseEnrollment courseEnrollmentDetail : courseEnrollmentList) {
			Course course = courseDAO
					.selectCourseDetailsById(courseEnrollmentDetail.getCourse());
			courseEnrollmentDetail.setCourse(course);
		}

		return courseEnrollmentList;
	}

	public String courseComplete(CourseEnrollment courseEnrollment) {
		int courseCompletedResult = courseEnrollmentDAO
				.courseComplete(courseEnrollment);
		String courseCompletedMessage = null;
		if (courseCompletedResult > 0) {

			courseCompletedMessage = "Course Completed successfully.";
		} else
			courseCompletedMessage = "Course Not completed";

		return courseCompletedMessage;
	}
}
