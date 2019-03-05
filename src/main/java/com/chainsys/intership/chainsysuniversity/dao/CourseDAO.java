package com.chainsys.intership.chainsysuniversity.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.chainsys.intership.chainsysuniversity.model.Course;

@Repository
public class CourseDAO {

	@Autowired
	JdbcTemplate jdbcTemplate;

	public int addCourse(Course course) {
		String query = "INSERT INTO chainsys_course(name,course_code) values(?,?)";
		Object[] parameters = new Object[] { course.getName(),course.getCode()  };
		int numberOfCourseAdded = jdbcTemplate.update(query, parameters);
		return numberOfCourseAdded;
	}

	public List<Course> listCourse() {
		String query = "SELECT id,name,course_code from chainsys_course";
		List<Course> courseList = jdbcTemplate.query(query,
				(resultSet, row) -> {
					Course course = courseInitialization(resultSet);
					return course;
				});
		return courseList;
	}

	public Course courseInitialization(ResultSet resultSet)
			throws SQLException {
		Course course = new Course();

		course.setId(resultSet.getInt("id"));
		course.setName(resultSet.getString("name"));
		course.setCode(resultSet.getString("course_code"));
		return course;
	}
	
	public Course selectCourseDetailsById(Course course)
	{
		String query = "SELECT id,name,course_code from chainsys_course WHERE id=?";
		Object[] parameters = new Object[] { course.getId() };
		Course courseList = jdbcTemplate.queryForObject(query,parameters,
				(resultSet, row) -> {
					Course courseDetails = courseInitialization(resultSet);
					return courseDetails;
				});
		return courseList;
	}
	
}
