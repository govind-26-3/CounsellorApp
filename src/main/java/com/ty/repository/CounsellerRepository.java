package com.ty.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ty.Entity.Counseller;
//import com.ty.Entity.Enquiry;

public interface CounsellerRepository  extends JpaRepository<Counseller, Integer> {

	Optional<Counseller> findByEmail(String Email);
}
