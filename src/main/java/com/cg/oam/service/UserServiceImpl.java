package com.cg.oam.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.oam.exception.OrderNotFoundException;
import com.cg.oam.exception.UserException;
import com.cg.oam.model.UserModel;
import com.cg.oam.repository.IUserRepository;



@Service
public class UserServiceImpl implements IUserService {
	
	@Autowired
	private IUserRepository userRepo;

	@Autowired
	private EMParser parser;
	
	public UserServiceImpl() {
		
	}
	
	/**
	 * @param userRepo
	 * @param parser
	 */
	public UserServiceImpl(IUserRepository userRepo) {
		super();
		this.userRepo = userRepo;
		this.parser = new EMParser();
	}

	
	public IUserRepository getUserRepo() {
		return userRepo;
	}

	public void setUserRepo(IUserRepository userRepo) {
		this.userRepo = userRepo;
	}

	public EMParser getParser() {
		return parser;
	}

	public void setParser(EMParser parser) {
		this.parser = parser;
	}

	@Override
	public UserModel add(UserModel user) throws UserException {
		if(user !=null) {
			if(userRepo.existsById(user.getCustomerId())) {
				throw new UserException("User "+user.getCustomerId()+" is already Exists");
			}if(!user.getPassword().matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&-+=()])(?=\\S+$).{8,}$")) {
				System.out.println(user.getPassword().matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&-+=()])(?=\\S+$).{8,}$")+" "+user.getPassword());
				throw new UserException("Password should contain upper case, Lower case, Special charecter, numbers and length minimum 8");
			}
			else {
				user=parser.parse(userRepo.save(parser.parse(user)));
			}
		}
		return user;
	}

	@Override
	public UserModel save(UserModel user) throws UserException {
		UserModel old=parser.parse(userRepo.findById(user.getCustomerId()).orElse(null));
		if(old == null) {
			throw new UserException("No user with Id "+user.getCustomerId()+" is present");
		}else if(!user.getPassword().matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&-+=()])(?=\\S+$).{8,}$")) {
			throw new UserException("Password should contain upper case, Lower case, Special charecter, numbers and length minimum 8");
		}else {
			user=parser.parse(userRepo.save(parser.parse(user)));
		}
		return user;
	}

	@Override
	public List<UserModel> findAll() {
		return userRepo.findAll().stream().map(parser::parse).collect(Collectors.toList());
	}

	@Override
	public void deleteById(Long customerId) {
		userRepo.deleteById(customerId);
		
	}

	@Override
	public UserModel findById(Long customerId) throws UserException {
		if (!userRepo.existsById(customerId))
			throw new UserException("No User  found for the given id");
		return parser.parse(userRepo.findById(customerId).get());
	}

	@Override
	public boolean existsById(Long customerId) {
		return userRepo.existsById(customerId);
	}

	@Override
	public UserModel signIn(UserModel user) throws UserException {
		if(userRepo.findById(user.getCustomerId()).orElse(null).getPassword()==user.getPassword()) {
			return user;
		}else {
			throw new UserException("Invalid password / user");
		}
	}

}