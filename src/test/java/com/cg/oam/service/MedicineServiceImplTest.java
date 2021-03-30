package com.cg.oam.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import com.cg.oam.entity.Company;
import com.cg.oam.entity.Medicine;
import com.cg.oam.model.MedicineModel;
import com.cg.oam.repository.IMedicineRepository;

@ExtendWith(MockitoExtension.class)
class MedicineServiceImplTest {
	@Mock
	private IMedicineRepository medicineRepository;

	
	@InjectMocks
	private MedicineServiceImpl medicineServiceImpl;
	
	@InjectMocks
	private EMParser parser;

	@Test
	@DisplayName("delete medicine by id")
	void testDeleteById() {
		String medicineId="O1";
		medicineServiceImpl.deleteById(medicineId);
		verify(medicineRepository,times(1)).deleteById(medicineId);

	}
	
	@Test
	@DisplayName("get medicine by id")
	void testFindById() {
		Medicine testdata = new Medicine("M01","paracetmol",56,LocalDate.parse("2021-03-22"),LocalDate.parse("2022-09-09"),12,Company.Divis,"HERBAL");
		MedicineModel expected = new MedicineModel("M01","paracetmol",56,LocalDate.parse("2021-03-22"),LocalDate.parse("2022-09-09"),"HERBAL","Divis");
		
		
		Mockito.when(medicineRepository.findById(testdata.getMedicineId())).thenReturn(Optional.of(testdata));
		
		MedicineModel actual = medicineServiceImpl.findById(testdata.getMedicineId());
		assertEquals(expected,actual);
		
	}
	
	@Test
	@DisplayName("get by id return null")
	void testGetByIdNull() {		
		
		Mockito.when(medicineRepository.findById("M01")).thenReturn(Optional.empty());
		
		MedicineModel actual = medicineServiceImpl.findById("M01");
		assertNull(actual);
	}
	
	@Test
	@DisplayName("get medicine by id : id not exists")
	void testFindByIdNotExists() {
		
		Medicine testdata = new Medicine("M01","paracetmol",56,LocalDate.parse("2021-03-22"),LocalDate.parse("2022-09-09"),11,Company.Divis,"HERBAL");
		MedicineModel expected = new MedicineModel("M01","paracetmol",56,LocalDate.parse("2021-03-22"),LocalDate.parse("2022-09-09"),"HERBAL","Divis");
		
		Mockito.when(medicineRepository.findById(testdata.getMedicineId())).thenReturn(Optional.empty());
		
		MedicineModel actual=medicineServiceImpl.findById(testdata.getMedicineId());
		assertNotEquals(expected,actual);
		
	}
	@Test
	@DisplayName("find all the medicine:no medicine is available")
	void testFindAll() {
		List<Medicine> testdata = Arrays.asList(new Medicine[] {
				new Medicine("M01","paracetmol",56,LocalDate.parse("2021-03-22"),LocalDate.parse("2022-09-09"),12,Company.Divis,"HERBAL"),
				new Medicine("M02","paracetmol",56,LocalDate.parse("2021-03-22"),LocalDate.parse("2022-09-09"),12,Company.Divis,"HERBAL")
		
		
		});
		Mockito.when(medicineRepository.findAll()).thenReturn(testdata);
		
		List<MedicineModel> expected = Arrays.asList(new MedicineModel[] {
		new MedicineModel("M01","paracetmol",56,LocalDate.parse("2021-03-22"),LocalDate.parse("2022-09-09"),"HERBAL","Divis")
		});
		List<MedicineModel>actual = medicineServiceImpl.findAll();
		assertNotEquals(expected,actual);
		
	}
	@Test
	@DisplayName("find all the medicines")
	void testFindAll1() {
		List<Medicine> testdata = Arrays.asList(new Medicine[] {
				new Medicine("M01","paracetmol",56,LocalDate.parse("2021-03-22"),LocalDate.parse("2022-09-09"),12,Company.Divis,"HERBAL"),
				new Medicine("M02","paracetmol",56,LocalDate.parse("2021-03-22"),LocalDate.parse("2022-09-09"),12,Company.Divis,"HERBAL")
		
		});
				Mockito.when(medicineRepository.findAll()).thenReturn(testdata);
				
				List<MedicineModel> expected = Arrays.asList(new MedicineModel[] {
						new MedicineModel("M01","paracetmol",56,LocalDate.parse("2021-03-22"),LocalDate.parse("2022-09-09"),"HERBAL","Divis"),
						new MedicineModel("M02","paracetmol",56,LocalDate.parse("2021-03-22"),LocalDate.parse("2022-09-09"),"HERBAL","Divis")
				
				});
				List<MedicineModel>actual = medicineServiceImpl.findAll();
				assertEquals(expected,actual);
	}
	@Test
	@DisplayName("Check Whether the id exists or not")
	void testExistsById() {

		Medicine testdata = new Medicine("M01","paracetmol",56,LocalDate.parse("2021-03-22"),LocalDate.parse("2022-09-09"),12,Company.Divis,"HERBAL");
		boolean expected=true;
		Mockito.when(medicineRepository.existsById(testdata.getMedicineId())).thenReturn(expected);
		boolean actual= medicineServiceImpl.existsById(testdata.getMedicineId());
		assertEquals(expected,actual);
		
	}
}
