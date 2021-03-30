package com.cg.oam.repository;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cg.oam.entity.Medicine;
import com.cg.oam.exception.MedicineNotFoundException;

public interface IMedicineRepository extends JpaRepository<Medicine, String> {

}