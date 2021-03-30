package com.cg.oam.service;
import java.util.List;
import java.util.Optional;

import com.cg.oam.entity.Medicine;
import com.cg.oam.exception.MedicineNotFoundException;
import com.cg.oam.model.MedicineModel;

public interface IMedicineService {
	MedicineModel add(MedicineModel medicineModel) throws MedicineNotFoundException;
	MedicineModel save(MedicineModel medicineModel) throws MedicineNotFoundException;
	void deleteById(String medicineId);
	MedicineModel findById(String medicineId) throws MedicineNotFoundException;
	List<MedicineModel> findAll();
	MedicineModel modify(MedicineModel medicineModel,String medicineId)throws MedicineNotFoundException;
	boolean existsById(String medicineId);
	}