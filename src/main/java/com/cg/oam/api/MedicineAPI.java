package com.cg.oam.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.oam.exception.MedicineNotFoundException;
import com.cg.oam.model.MedicineModel;
import com.cg.oam.service.MedicineServiceImpl;

@RestController
@RequestMapping(path="/medicine")

public class MedicineAPI {
	@Autowired
	private MedicineServiceImpl medService;
	
	
	@GetMapping("/getallmedicines")
	public ResponseEntity<List<MedicineModel>> findAll() {
		return new ResponseEntity<>(medService.findAll(), HttpStatus.OK); 
	}
	
	@GetMapping("/getmedicine/{medicineId}")
	public ResponseEntity<MedicineModel> findById(@PathVariable("medicineId") String medicineId)throws MedicineNotFoundException {
		ResponseEntity<MedicineModel> response = null;
		System.out.println("find-by-medid");
		MedicineModel medicine = medService.findById(medicineId);
		
		if (medicine == null) {
			response = new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			response = new ResponseEntity<>(medicine, HttpStatus.OK);
		}
		return response;
	}
	
	@PostMapping("/addmedicine")
	public ResponseEntity<MedicineModel> createMedicine(@RequestBody MedicineModel medicine) throws MedicineNotFoundException {
		medicine = medService.add(medicine);
		return new ResponseEntity<>(medicine, HttpStatus.CREATED);
	}
	
	@DeleteMapping("/deletemedicine/{medicineId}")
	public ResponseEntity<Void> deleteCustomer(@PathVariable("medicineId") String medicineId) {
		ResponseEntity<Void> response = null;
		MedicineModel medicine = medService.findById(medicineId);
		if (medicine == null) {
			response = new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			medService.deleteById(medicineId);
			response = new ResponseEntity<>(HttpStatus.OK);
		}
		return response;
	}
	@PutMapping("/updatemedicine/{medicineId}")
	public ResponseEntity<MedicineModel> updateEmployee(@RequestBody MedicineModel medicine,@PathVariable("medicineId") String medicineId)throws MedicineNotFoundException {
		medicine = medService.modify(medicine,medicine.getMedicineId());
		return new ResponseEntity<>(medicine, HttpStatus.OK);
	}
}
