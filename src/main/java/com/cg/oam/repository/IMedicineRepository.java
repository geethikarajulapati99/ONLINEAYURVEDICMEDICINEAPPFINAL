package com.cg.oam.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.cg.oam.entity.Medicine;
public interface IMedicineRepository extends JpaRepository<Medicine, String> {

}

