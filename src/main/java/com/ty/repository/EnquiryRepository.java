package com.ty.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ty.Entity.Enquiry;
//import java.util.List;
import java.util.Optional;

public interface EnquiryRepository extends JpaRepository<Enquiry, Integer> {

	Optional<Enquiry> findByEmail(String email);
}
