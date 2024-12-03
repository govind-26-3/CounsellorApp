package com.ty.service;

import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Service;

import com.ty.Entity.Counseller;
import com.ty.Enum.Status;
import com.ty.dto.LoginRequest;

public interface CounsellerService {

	ResponseEntity<?> register(Counseller counseller);

	ResponseEntity<?> login(LoginRequest request);	

	ResponseEntity<?> updateStatus(Integer cid, Status status);

	ResponseEntity<?> updatePhone(Integer cid, Long Phone);

	ResponseEntity<?> getCounseller(Integer cid);

	ResponseEntity<?> deleteCounseller(Integer cid);

}
 