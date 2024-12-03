package com.ty.service;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
//import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ty.Entity.Counseller;
import com.ty.Entity.Enquiry;
import com.ty.Enum.ClassMode;
import com.ty.dto.EnquiryDto;
import com.ty.dto.FilterDto;
import com.ty.exception.CounsellorNotFound;
import com.ty.repository.CounsellerRepository;
import com.ty.repository.EnquiryRepository;
import com.ty.responsestructure.ResponseStructure;

@Service
public class EnquiryImplService implements EnquiryService {

	@Autowired
	private EnquiryRepository enquiryRepo;

	@Autowired
	private CounsellerRepository counRepo;

	@Override
	public ResponseEntity<?> addEnquiry(Integer cid, Enquiry enquiry) {
		// TODO Auto-generated method stub
		Counseller c = counRepo.findById(cid).orElseThrow(() -> new CounsellorNotFound("Counsellor not found"));
		Optional<Enquiry> e = enquiryRepo.findByEmail(enquiry.getEmail());

		if (e.isPresent()) {
			ResponseStructure<String> rs = new ResponseStructure<>();
			rs.setMessage("Enquiry alresdy present");
			rs.setStatusCode(HttpStatus.BAD_REQUEST.value());
			rs.setData(enquiry.getEmail());

			return new ResponseEntity<ResponseStructure<String>>(rs, HttpStatus.BAD_REQUEST);
		} else {
			enquiry.setCounseller(c);
			Enquiry newEnquiry = enquiryRepo.save(enquiry);

			EnquiryDto dto = new EnquiryDto();
			BeanUtils.copyProperties(c, dto);
			ResponseStructure<Enquiry> rs = new ResponseStructure<>();
			rs.setMessage("Enquiry Added Successfully");
			rs.setStatusCode(HttpStatus.CREATED.value());
			rs.setData(newEnquiry);

			return new ResponseEntity<ResponseStructure<Enquiry>>(rs, HttpStatus.CREATED);

		}
	}

	@Override
	public ResponseEntity<?> updateClassMode(Integer eid, ClassMode classMode) {
		// TODO Auto-generated method stub
		Enquiry e = enquiryRepo.findById(eid).orElseThrow(() -> new RuntimeException("Enquiry Does not Exist"));
		e.setClassMode(classMode);
		Enquiry newClassMode = enquiryRepo.save(e);
		ResponseStructure<Enquiry> rs = new ResponseStructure<>();
		rs.setStatusCode(HttpStatus.CREATED.value());
		rs.setMessage("Classmode Updated ");
		rs.setData(newClassMode);

		return new ResponseEntity<ResponseStructure<Enquiry>>(rs, HttpStatus.CREATED);

	}

	@Override
	public ResponseEntity<?> updateFees(Integer eid, Double fees) {
		// TODO Auto-generated method stub
		Enquiry e = enquiryRepo.findById(eid).orElseThrow(() -> new RuntimeException("Enquiry Does not Esist"));
		e.setFees(fees);
		Enquiry newClassMode = enquiryRepo.save(e);
		ResponseStructure<Enquiry> rs = new ResponseStructure<>();
		rs.setStatusCode(HttpStatus.CREATED.value());
		rs.setMessage("Fees Updated ");
		rs.setData(newClassMode);

		return new ResponseEntity<ResponseStructure<Enquiry>>(rs, HttpStatus.CREATED);

	}

	@Override
	public ResponseEntity<?> deleteEnquiry(Integer eid) {

		Enquiry e = enquiryRepo.findById(eid).orElseThrow(() -> new RuntimeException("Enquiry Does not Esist"));

		enquiryRepo.deleteById(eid);
		ResponseStructure<Enquiry> rs = new ResponseStructure<>();
		rs.setStatusCode(HttpStatus.OK.value());
		rs.setMessage("Enquiry Deleted");
//		rs.setData(e.get);

		return new ResponseEntity<ResponseStructure<Enquiry>>(rs, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<?> getEnquiry(Integer eid) {
		// TODO Auto-generated method stub
		Enquiry e = enquiryRepo.findById(eid).orElseThrow(() -> new RuntimeException("Enquiry Does not Esist"));

		ResponseStructure<Enquiry> rs = new ResponseStructure<>();
		rs.setStatusCode(HttpStatus.OK.value());
		rs.setMessage("Enquiry Fetched");
		rs.setData(e);

		return new ResponseEntity<ResponseStructure<Enquiry>>(rs, HttpStatus.OK);

	}

	@Override
	public ResponseEntity<?> getEnquiries(Integer cid) {
		// TODO Auto-generated method stub
		Counseller counsellor = counRepo.findById(cid)
				.orElseThrow(() -> new CounsellorNotFound("COunsellor does not exist"));
		List<Enquiry> enquiries = counsellor.getEnquiries();

		ResponseStructure<List<Enquiry>> rs = new ResponseStructure<>();
		rs.setStatusCode(HttpStatus.OK.value());
		rs.setMessage("All Enquiries fetch associated with Counsellor id : " + cid);
		rs.setData(enquiries);

		return new ResponseEntity<ResponseStructure<List<Enquiry>>>(rs, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<?> filter(FilterDto dto) {

		Enquiry enq = new Enquiry();
		BeanUtils.copyProperties(dto, enq);

		Example<Enquiry> e = Example.of(enq);

		List<Enquiry> all = enquiryRepo.findAll(e);

		ResponseStructure<List<Enquiry>> rs = new ResponseStructure<>();
		rs.setStatusCode(HttpStatus.OK.value());
		rs.setMessage("All Enquiries fetch ");
		rs.setData(all);

		return new ResponseEntity<ResponseStructure<List<Enquiry>>>(rs, HttpStatus.OK);

	}

	@Override
	public ResponseEntity<?> getEnquiryPage(Integer pageNumber) {
		// TODO Auto-generated method stub
		Pageable page = PageRequest.of(pageNumber-1, 3);
		
		Page<Enquiry> e = enquiryRepo.findAll(page);
//	return e.toList();
	    return ResponseEntity.ok(e);
	}

}
