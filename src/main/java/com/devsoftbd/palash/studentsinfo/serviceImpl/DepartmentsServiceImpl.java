package com.devsoftbd.palash.studentsinfo.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.devsoftbd.palash.studentsinfo.model.DepartmentsModel;
import com.devsoftbd.palash.studentsinfo.repository.DepartmentsRepository;

@Service
public class DepartmentsServiceImpl {

	@Autowired
	private DepartmentsRepository departmentRepo;
	
	
	public List<DepartmentsModel> getList(){
		return departmentRepo.findAll();
	}
}
