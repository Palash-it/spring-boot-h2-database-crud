package com.devsoftbd.palash.studentsinfo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "students", uniqueConstraints = {@UniqueConstraint(columnNames = "roll")})
public class StudentsModel {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "student_id")
	private Integer studentId;
	@NotNull(message = "Student name can 't be empty")
	private String name;
	@NotNull(message = "Student roll can 't be empty")
	@Column(name = "roll",unique = true)
	private String roll;
	private String gender;
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "department_id")
	private DepartmentsModel department;

	public StudentsModel() {
	}

	public Integer getStudentId() {
		return studentId;
	}

	public void setStudentId(Integer studentId) {
		this.studentId = studentId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRoll() {
		return roll;
	}

	public void setRoll(String roll) {
		this.roll = roll;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public DepartmentsModel getDepartment() {
		return department;
	}

	public void setDepartment(DepartmentsModel department) {
		this.department = department;
	}

	@Override
	public String toString() {
		return "StudentsModel [studentId=" + studentId + ", name=" + name + ", roll=" + roll + ", gender=" + gender
				+ ", department=" + department + "]";
	}

}
