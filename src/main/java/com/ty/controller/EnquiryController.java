package com.ty.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ty.Entity.Enquiry;
import com.ty.Enum.ClassMode;
import com.ty.dto.FilterDto;
import com.ty.service.EnquiryImplService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;



@RestController
@RequestMapping("/enquiry")
public class EnquiryController {

	@Autowired
	private EnquiryImplService enqImlservice;
	
	
	@PostMapping("/register")
	public ResponseEntity<?> registerEnquiry(@RequestParam Integer cid,@RequestBody Enquiry enquiry) {
		//TODO: process POST request
		
		return enqImlservice.addEnquiry(cid, enquiry);
	}
	
	@PostMapping("/updatemode")
	public ResponseEntity<?> updateClassMode(@RequestParam Integer eid,ClassMode classMode) {
		//TODO: process POST request
		
		return enqImlservice.updateClassMode(eid,classMode);
	}
	@GetMapping("/fetch")
	public ResponseEntity<?> getEnquiry(@RequestParam Integer eid) {
		return enqImlservice.getEnquiry(eid);
	}
	@GetMapping("/delete")
	public ResponseEntity<?> deleteEnquiry(@RequestParam Integer eid) {
		return enqImlservice.deleteEnquiry(eid);
	}
	
	@GetMapping("/getall")
	public ResponseEntity<?> getAllEnquiriesByCid(@RequestParam Integer cid) {
		return enqImlservice.getEnquiries(cid);
	}
	
	@GetMapping("/filter")
	public ResponseEntity<?> filterAllEnquiriesBy(@RequestBody  FilterDto dto) {
		return enqImlservice.filter(dto);
	}
	
	@GetMapping("/page")
	public ResponseEntity<?> getAllEnquiries(@RequestParam  Integer pageNumber) {
		return enqImlservice.getEnquiryPage(pageNumber);
	}
	
	
	
	
}
