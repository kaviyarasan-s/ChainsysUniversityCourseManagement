package com.chainsys.intership.chainsysuniversity.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.chainsys.intership.chainsysuniversity.model.Course;
import com.chainsys.intership.chainsysuniversity.model.CourseEnrollment;

@Repository
public class CourseEnrollmentDAO {

	@Autowired
	JdbcTemplate jdbcTemplate;

	public int courseEnrollment(CourseEnrollment courseEnrollment) {

		String query = "INSERT INTO course_enrollment(user_id,course_id,start_date,end_date,status) VALUES(?,?,?,?,?)";
		Object[] parameters = new Object[] {
				courseEnrollment.getUser().getId(),
				courseEnrollment.getCourse().getId(),
				courseEnrollment.getStartDate(), courseEnrollment.getEndDate(),
				courseEnrollment.getStatus() };
		int courseEnrollmentResult = jdbcTemplate.update(query, parameters);
		return courseEnrollmentResult;

	}

	public int courseComplete(CourseEnrollment courseEnrollment) {

		String query = "UPDATE course_enrollment set end_date=?,status=? WHERE user_id=?";
		Object[] parameters = new Object[] { courseEnrollment.getEndDate(),
				courseEnrollment.getStatus(),
				courseEnrollment.getUser().getId() };
		int courseCompleteResult = jdbcTemplate.update(query, parameters);
		return courseCompleteResult;

	}

	public List<CourseEnrollment> getUserCourseIdWithStatus(
			CourseEnrollment courseEnrollment) {

		String query = "SELECT course_id,status,start_date,end_date from course_enrollment WHERE user_id=? and status=?";

		Object[] parameters = new Object[] {
				courseEnrollment.getUser().getId(),
				courseEnrollment.getStatus() };
		List<CourseEnrollment> courseEnrollmentList = jdbcTemplate.query(query,
				parameters, (resultSet, row) -> {
					CourseEnrollment courseEnrollmentDetails = courseInitialization(resultSet);
					return courseEnrollmentDetails;
				});
		return courseEnrollmentList;

	}

	public List<CourseEnrollment> getUserCourseIdWithOutStatus(
			CourseEnrollment courseEnrollment) {

		String query = "SELECT course_id,status,start_date,end_date from course_enrollment WHERE user_id=?";

		Object[] parameters = new Object[] { courseEnrollment.getUser().getId() };
		List<CourseEnrollment> courseEnrollmentList = jdbcTemplate.query(query,
				parameters, (resultSet, row) -> {
					CourseEnrollment courseEnrollmentDetails = courseInitialization(resultSet);
					return courseEnrollmentDetails;
				});
		return courseEnrollmentList;

	}
	

	public CourseEnrollment courseInitialization(ResultSet resultSet)
			throws SQLException {
		Course course = new Course();
		course.setId(resultSet.getInt("course_id"));
		CourseEnrollment courseEnrollment = new CourseEnrollment();
		courseEnrollment.setCourse(course);
		courseEnrollment.setStatus(resultSet.getString("status"));
		courseEnrollment.setStartDate(resultSet.getTimestamp("start_date").toLocalDateTime());
		courseEnrollment.setEndDate(resultSet.getTimestamp("end_date").toLocalDateTime());
		return courseEnrollment;
	}

}
