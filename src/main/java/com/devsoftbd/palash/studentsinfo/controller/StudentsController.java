package com.devsoftbd.palash.studentsinfo.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.devsoftbd.palash.studentsinfo.model.StudentsModel;
import com.devsoftbd.palash.studentsinfo.serviceImpl.DepartmentsServiceImpl;
import com.devsoftbd.palash.studentsinfo.serviceImpl.StudentsServiceImpl;

@RequestMapping(value = "students")
@Controller
public class StudentsController {

	@Autowired
	private DepartmentsServiceImpl deparmentService;
	@Autowired
	private StudentsServiceImpl studentService;

	@GetMapping(value = { "/add", "/" })
	public String addStudents(Model model) {
		model.addAttribute("pageTitle", "Students | Add");
		model.addAttribute("departmentsList", deparmentService.getList());
		return "students/add-student";
	}

	@PostMapping(value = "/add")
	public String addStudents(@Valid @ModelAttribute StudentsModel students, RedirectAttributes redirectAttr,
			BindingResult result) {
		String msg = "";
		if (result.hasErrors()) {
			FieldError error = null;
			for (Object obj : result.getAllErrors()) {
				error = (FieldError) obj;
				msg += error.getDefaultMessage();
			}
		}
		if (msg.length() == 0) {
			try {
				if (studentService.findByRoll(students.getRoll()) == null) {
					studentService.save(students);
					redirectAttr.addFlashAttribute("status", "success");
					redirectAttr.addFlashAttribute("message", "Student information successfully saved");
				} else {
					redirectAttr.addFlashAttribute("status", "error");
					redirectAttr.addFlashAttribute("message", "Student roll already exist");
				}
			} catch (Exception e) {
				System.out.println(e);
			}
		} else {
			redirectAttr.addFlashAttribute("status", "error");
			redirectAttr.addFlashAttribute("message", msg);
		}
		return "redirect:/students/add";
	}

	@GetMapping(value = "view")
	public String list(Model model) {
		model.addAttribute("pageTitle", "Students | View");
		model.addAttribute("studentList", studentService.getList());
		return "students/view-students";
	}

	@GetMapping(value = "/edit/{studentId}")
	public String edit(@PathVariable Integer studentId, Model model, RedirectAttributes redirectAttr) {
		try {
			if (studentId > 0) {
				StudentsModel student = studentService.findByStudentId(studentId);
				if (student != null) {
					model.addAttribute("student", student);
					model.addAttribute("departmentsList", deparmentService.getList());
				} else {
					redirectAttr.addFlashAttribute("message", "No student was matched");
					return "redirect:/students/view";
				}
			} else {
				redirectAttr.addFlashAttribute("message", "No reference was found");
				return "redirect:/students/view";
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		model.addAttribute("pageTitle", "Students | Update");
		return "students/edit-student";
	}

	@PostMapping(value = "/update")
	public String update(@Valid @ModelAttribute StudentsModel student, RedirectAttributes redirectAttr,
			BindingResult result) {
		String msg = "";
		redirectAttr.addFlashAttribute("status", "error");
		try {
			if (student != null && student.getStudentId() > 0) {
				if (result.hasErrors()) {
					FieldError error = null;
					for (Object obj : result.getAllErrors()) {
						error = (FieldError) obj;
						msg += error.getDefaultMessage();
					}
				}
				if (msg.length() == 0) {
					StudentsModel oldModel = studentService.findByStudentId(student.getStudentId());
					if (!oldModel.getRoll().equalsIgnoreCase(student.getRoll())) {
						if (studentService.findByRoll(student.getRoll()) != null) {
							redirectAttr.addFlashAttribute("message", student.getRoll() + " Roll already exist");
							return "redirect:/students/edit/" + student.getStudentId();
						}
					}
					studentService.save(student);
					redirectAttr.addFlashAttribute("status", "success");
					redirectAttr.addFlashAttribute("message", "Student Info Successfully updated");
					return "redirect:/students/view";
				} else {
					redirectAttr.addFlashAttribute("message", msg);
					return "redirect:/students/edit/" + student.getStudentId();
				}
			} else {
				redirectAttr.addFlashAttribute("message", "No reference was found");
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return "redirect:/students/view";
	}

	@GetMapping(value = "/delete/{studentId}")
	public String delete(@PathVariable Integer studentId, RedirectAttributes redirectAttr) {
		if (studentId != null) {
			try {
				studentService.deleteByStudentId(studentId);
				redirectAttr.addFlashAttribute("status", "success");
				redirectAttr.addFlashAttribute("message", "Student Information successfully deleted");
			} catch (Exception e) {
				System.out.println(e);
			}
		}
		return "redirect:/students/view";
	}
}
