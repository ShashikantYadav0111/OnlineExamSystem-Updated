package com.exam.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.exam.entity.Report;
import com.exam.entity.User;
import com.exam.service.ReportService;
import com.exam.service.UserService;

@RestController
@RequestMapping("/search")
public class SearchStudents {
	
	@Autowired
	private ReportService repServ;
	
	@Autowired
	private UserService userv;
	
	@GetMapping("/{tech}/{state}/{city}/{lev}/{score}")
	public List<User> searchStudent(@PathVariable String tech,
			@PathVariable String state,
			@PathVariable String city,
			@PathVariable int lev,
			@PathVariable int score
			){

		List<Report> rlist = repServ.findAllByTech(tech);
		List<User> ulist = new ArrayList<User>();
		for(Report r:rlist) {
			if(r.getLev()==lev && r.getScore()>=score) {				
				ulist.add(r.getUser());
			}
		}
		
		List<User> result = new ArrayList<User>();
		
		for(User u:ulist) {
			if(u.getState().equalsIgnoreCase(state) && u.getCity().equalsIgnoreCase(city)) {
				result.add(u);
			}
		}
		
		
		return result;
	}
	
	@GetMapping("/tech/{tech}")
	public List<User> searchStudentByTech(@PathVariable String tech
			){

		List<Report> rlist = repServ.findAllByTech(tech);
		List<User> ulist = new ArrayList<User>();
		for(Report r:rlist) {
				ulist.add(r.getUser());
		}
		

		return ulist;
	}
	
	@GetMapping("/state/{state}")
	public List<User> searchStudentByState(@PathVariable String state){

		List<User> ulist = userv.getAllByState(state);
		return ulist;
	}

	@GetMapping("/city/{city}")
	public List<User> searchStudentByCity(@PathVariable String city){

		List<User> ulist = userv.getAllByCity(city);
		return ulist;
	}
	
	@GetMapping("/passed")
	public List<User> searchStudentByScore(){
		
		List<Report> rList  = repServ.getAll();
		List<User> ulist = new ArrayList<User>();
		for(Report r:rList) {
			if(r.getScore()>=65) {
				ulist.add(r.getUser());
			}
		}
		
		return ulist;
	}
	
	

}
