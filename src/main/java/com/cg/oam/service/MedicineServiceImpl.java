package com.cg.oam.service;

import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.oam.entity.Medicine;
import com.cg.oam.exception.CustomerNotFoundException;
import com.cg.oam.exception.MedicineNotFoundException;
import com.cg.oam.model.MedicineModel;
import com.cg.oam.repository.IMedicineRepository;

@Service
public class MedicineServiceImpl implements IMedicineService{
	@Autowired
	private IMedicineRepository medRepo;
	
	@Autowired
	private EMParser parser;
	
	public MedicineServiceImpl() {
		
	}
	public MedicineServiceImpl(IMedicineRepository repo) {
		super();
		this.medRepo = repo;
		this.parser=new EMParser();
	}
	
	
	/**
	 * @return the parser
	 */
	public EMParser getParser() {
		return parser;
	}
	/**
	 * @param parser the parser to set
	 */
	public void setParser(EMParser parser) {
		this.parser = parser;
	}
	@Transactional
	@Override
	public MedicineModel add(MedicineModel medicineModel) throws MedicineNotFoundException {
		
			if (medicineModel != null) {
				if (medRepo.existsById(medicineModel.getMedicineId())) {
					throw new MedicineNotFoundException("Medicine with Id " + medicineModel.getMedicineId() + " is exist already");
				}else {
					Medicine medicine = parser.parse(medicineModel);
					System.out.println("medicine -==============>"+ medicine);
					medicineModel = parser.parse(medRepo.save(parser.parse(medicineModel)));
				}
			}
			return medicineModel;
		}

	
	@Transactional
	@Override
	public MedicineModel save(MedicineModel medicineModel) throws MedicineNotFoundException {
			return medicineModel = parser.parse(medRepo.save(parser.parse(medicineModel)));
		
	}
	

	
	@Override
	public void deleteById(String medicineId) {
		medRepo.deleteById(medicineId);
		
	}
	
	@Override
	public MedicineModel findById(String medicineId) throws MedicineNotFoundException {
		if (!medRepo.existsById(medicineId))
			throw new MedicineNotFoundException("No Medicine  found for the given id");
		return parser.parse(medRepo.findById(medicineId).get());
	}

	@Override
	public List<MedicineModel> findAll() {
		return medRepo.findAll().stream().map(parser::parse).collect(Collectors.toList());
	}
	
	@Transactional
	@Override
	public MedicineModel modify(MedicineModel medicineModel, String medicineId) throws MedicineNotFoundException {
		if(medicineModel != null) {
			if(!medRepo.existsById(medicineId)) {
				throw new MedicineNotFoundException("no such id");
			}
			medicineModel = parser.parse(medRepo.save(parser.parse(medicineModel)));
		}
		return medicineModel;
	}
	@Override
	public boolean existsById(String medicineId) {
		return medRepo.existsById(medicineId);
	}


}