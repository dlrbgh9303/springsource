package com.company.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.company.domain.PersonDTO;
import com.company.mapper.PersonMapper;

@Service("person")
public class PersonServiceImpl implements PersonService {

	@Autowired
	private PersonMapper mapper;
	
	@Override
	public boolean insertPerson(String id, String name) {
		return mapper.insertPerson(id, name) > 0 ? true:false;
	}

	@Override
	public String selectPerson(String id) {
		
		return mapper.selectPerson(id);
	}

	@Override
	public boolean updatePerson(String id, String name) {
		
		return mapper.updatePerson(id, name) > 0 ? true:false;
	}

	@Override
	public boolean deletePerson(String id) {
		return mapper.deletePerson(id) > 0 ? true:false;
	}

	@Override
	public List<PersonDTO> list() {
		
		return mapper.all();
	}

}
