package com.ty.dto;

import com.ty.Enum.ClassMode;
import com.ty.Enum.Course;

public class FilterDto {
	
private ClassMode classMode;
	
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

	private Course course;
}
