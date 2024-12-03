package com.ty.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.service.annotation.DeleteExchange;

import com.ty.Entity.Counseller;
import com.ty.Enum.Status;
import com.ty.dto.LoginRequest;
import com.ty.service.CounsellerImplService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;

@RestController
@RequestMapping("/counseller")
public class CounsellerController {

	@Autowired
	private CounsellerImplService counImplService;

	@PostMapping("/register")
	public ResponseEntity<?> getRegistered(@RequestBody Counseller counseller) {
		return counImplService.register(counseller);
	}

	@PostMapping("/login")
	public ResponseEntity<?> loginMethod(@RequestBody LoginRequest request) {
		// TODO: process POST request

		return counImplService.login(request);
	}
	
	@PostMapping("/update")
	public ResponseEntity<?> updateStatus(@RequestParam Integer cid,Status status) {
		//TODO: process POST request
		
		return counImplService.updateStatus(cid, status);
	}
	
	@PostMapping("/updatePhone")
	public ResponseEntity<?> updatePhone(@RequestParam Integer cid,Long phone) {
		//TODO: process POST request
		
		return counImplService.updatePhone(cid, phone);
	}
	
	@GetMapping("/getcounsellor")
	public ResponseEntity<?> getCounsellor(@RequestParam Integer cid) {
		return counImplService.getCounseller(cid);
	}
	
	@DeleteMapping("/deletecounsellor")
	public ResponseEntity<?> deleteCounsellor(@RequestParam Integer cid) {
		return counImplService.deleteCounseller(cid);
	}
	
	
	
	

}
