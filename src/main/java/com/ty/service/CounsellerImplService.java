package com.ty.service;

import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
//import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ty.Entity.Counseller;
import com.ty.Enum.Status;
import com.ty.dto.CounsellerDto;
import com.ty.dto.LoginRequest;
import com.ty.exception.CounsellorNotFound;
import com.ty.repository.CounsellerRepository;
import com.ty.responsestructure.ResponseStructure;

@Service
public class CounsellerImplService implements CounsellerService {

	@Autowired
	private CounsellerRepository counsellerRepo;

	@Override
	public ResponseEntity<?> register(Counseller counseller) {
		// TODO Auto-generated method stub
		Optional<Counseller> c = counsellerRepo.findByEmail(counseller.getEmail());

		if (c.isPresent()) {

			ResponseStructure<String> rs = new ResponseStructure<>();
			rs.setData(counseller.getEmail());
			rs.setStatusCode(HttpStatus.BAD_REQUEST.value());
			rs.setMessage("Already Registered ");

			return new ResponseEntity<ResponseStructure<String>>(rs, HttpStatus.BAD_REQUEST);

		} else {
			Counseller save = counsellerRepo.save(counseller);
			CounsellerDto dto = new CounsellerDto();

			BeanUtils.copyProperties(save, dto);

			ResponseStructure<CounsellerDto> rs = new ResponseStructure<>();
			rs.setData(dto);
			rs.setStatusCode(HttpStatus.CREATED.value());
			rs.setMessage("Registered Successfully");

			return new ResponseEntity<ResponseStructure<CounsellerDto>>(rs, HttpStatus.CREATED);
		}

	}

	@Override
	public ResponseEntity<?> login(LoginRequest request) {
		// TODO Auto-generated method stub

		Counseller c = counsellerRepo.findByEmail(request.getEmail())
				.orElseThrow(() -> new CounsellorNotFound("Counsellor is Not Registered"));

		ResponseStructure<String> rs = new ResponseStructure<>();
		rs.setData(request.getEmail());
		if (request.getPassword().equals(c.getPassword())) {

			rs.setStatusCode(HttpStatus.OK.value());
			rs.setMessage("Login Sucessfully ");
			return new ResponseEntity<ResponseStructure<String>>(rs, HttpStatus.OK);

		}

		rs.setStatusCode(HttpStatus.BAD_REQUEST.value());
		rs.setMessage("Login Failed");
		return new ResponseEntity<ResponseStructure<String>>(rs, HttpStatus.BAD_REQUEST);

	}

	@Override
	public ResponseEntity<?> updateStatus(Integer cid, Status status) {
		// TODO Auto-generated method stub
		Counseller c = counsellerRepo.findById(cid)
				.orElseThrow(() -> new CounsellorNotFound("Counsellor is Not Registered"));
		c.setStatus(status);
		Counseller newStatus = counsellerRepo.save(c);

		ResponseStructure<Status> rs = new ResponseStructure<>();
		rs.setStatusCode(HttpStatus.CREATED.value());
		rs.setMessage("Status Changed successfully");
		rs.setData(newStatus.getStatus());

		return new ResponseEntity<ResponseStructure<Status>>(rs, HttpStatus.CREATED);

	}

	@Override
	public ResponseEntity<?> updatePhone(Integer cid, Long phone) {
		// TODO Auto-generated method stub
		Counseller c = counsellerRepo.findById(cid)
				.orElseThrow(() -> new CounsellorNotFound("Counsellor is Not Registered"));
		c.setPhone(phone);
		Counseller newPhone = counsellerRepo.save(c);

		ResponseStructure<Counseller> rs = new ResponseStructure<>();
		rs.setStatusCode(HttpStatus.CREATED.value());
		rs.setMessage("Phone no. Changed successfully to "+newPhone.getPhone());
//		rs.setData(newPhone.getPhone());

		return new ResponseEntity<ResponseStructure<Counseller>>(rs, HttpStatus.CREATED);

	}

	@Override
	public ResponseEntity<?> getCounseller(Integer cid) {
		
		Counseller c = counsellerRepo.findById(cid)
				.orElseThrow(() -> new CounsellorNotFound("Counsellor is Not Registered"));
		ResponseStructure<Counseller> rs = new ResponseStructure<>();
		rs.setStatusCode(HttpStatus.OK.value());
		rs.setMessage("Counsellor Details");
		rs.setData(c);
		
		return new ResponseEntity<ResponseStructure<Counseller>>(rs, HttpStatus.OK);
		
	}

	@Override
	public ResponseEntity<?> deleteCounseller(Integer cid) {
		Counseller c = counsellerRepo.findById(cid)
				.orElseThrow(() -> new CounsellorNotFound("Counsellor is Not Registered"));
		counsellerRepo.deleteById(cid);
		ResponseStructure<Counseller> rs = new ResponseStructure<>();
		rs.setStatusCode(HttpStatus.OK.value());
		rs.setMessage("Counsellor Deleted");
		rs.setData(c);
		
		return new ResponseEntity<ResponseStructure<Counseller>>(rs, HttpStatus.OK);
		
		
	}

}
