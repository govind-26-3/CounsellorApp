package com.ty.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ty.Enum.ClassMode;
import com.ty.Enum.Course;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
//import lombok.Data;


@Entity
public class Enquiry {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer eid;

	private String name;

	@Column(unique = true)
	private String email;
	
	private Long phone;

	@Enumerated(EnumType.STRING)
	private Course course;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private ClassMode classMode = ClassMode.OFFLINE;

	private Double fees;

	@ManyToOne
	@JoinColumn(name = "cid")
	@JsonIgnore
	private Counseller counseller;

	public Integer getEid() {
		return eid;
	}

	public void setEid(Integer eid) {
		this.eid = eid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Long getPhone() {
		return phone;
	}

	public void setPhone(Long phone) {
		this.phone = phone;
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	public ClassMode getClassMode() {
		return classMode;
	}

	public void setClassMode(ClassMode classMode) {
		this.classMode = classMode;
	}

	public Double getFees() {
		return fees;
	}

	public void setFees(Double fees) {
		this.fees = fees;
	}

	public Counseller getCounseller() {
		return counseller;
	}

	public void setCounseller(Counseller counseller) {
		this.counseller = counseller;
	}
	

}
