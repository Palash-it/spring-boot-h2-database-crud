package com.devsoftbd.palash.studentsinfo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.devsoftbd.palash.studentsinfo.model.DepartmentsModel;

@Repository
public interface DepartmentsRepository extends JpaRepository<DepartmentsModel, Integer>{

}
