package com.ty.service;

import org.springframework.http.ResponseEntity;

import com.ty.Entity.Enquiry;
import com.ty.Enum.ClassMode;
import com.ty.dto.FilterDto;

public interface EnquiryService {

	ResponseEntity<?> addEnquiry(Integer cid, Enquiry enquiry);

	ResponseEntity<?> updateClassMode(Integer eid, ClassMode classMode);

	ResponseEntity<?> updateFees(Integer eid, Double fees);

	ResponseEntity<?> deleteEnquiry( Integer eid);

	ResponseEntity<?> getEnquiry(Integer eid);

	ResponseEntity<?> getEnquiries(Integer cid);

	ResponseEntity<?> filter(FilterDto dto);

	ResponseEntity<?> getEnquiryPage(Integer pageNumber);
}
