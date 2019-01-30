package com.devsoftbd.palash.studentsinfo.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.devsoftbd.palash.studentsinfo.model.StudentsModel;
import com.devsoftbd.palash.studentsinfo.repository.StudentsRepository;

@Service
public class StudentsServiceImpl {

	@Autowired
	private StudentsRepository studentRepo;

	public StudentsModel save(StudentsModel student) {
		return studentRepo.save(student);
	}

	public StudentsModel findByRoll(String roll) {
		return studentRepo.findByRoll(roll);
	}

	public StudentsModel findByStudentId(Integer studentId) {
		return studentRepo.findByStudentId(studentId);
	}

	public List<StudentsModel> getList() {
		return studentRepo.findAll();
	}

	public void deleteByStudentId(Integer studentId) {
		StudentsModel student = studentRepo.findByStudentId(studentId);
		studentRepo.delete(student);
	}
}
