package com.ty.dto;

import com.ty.Enum.ClassMode;
import com.ty.Enum.Course;

public class EnquiryDto {

	private String name;

	private Long phone;

	private ClassMode classMode;

	private Course course;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getPhone() {
		return phone;
	}

	public void setPhone(Long phone) {
		this.phone = phone;
	}

	public ClassMode getClassMode() {
		return classMode;
	}

	public void setClassMode(ClassMode classMode) {
		this.classMode = classMode;
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

}
