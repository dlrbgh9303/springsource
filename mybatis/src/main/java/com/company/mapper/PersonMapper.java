package com.company.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.company.domain.PersonDTO;

public interface PersonMapper {
	//입력
	
	// BindingException: Parameter 'id' not found. 
	// Available parameters are [arg1, arg0, param1, param2]
	
//	@Insert("insert into person(id,name) values(#{id},#{name})")
//	public int insertPerson(@Param("id") String id, @Param("name") String name);
//	
//	@Select("select name from person where id=#{id}")
//	public String selectPerson(String id);
//	
//	@Update("update person set name=#{name} where id=#{id}")
//	public int updatePerson(@Param("id") String id, @Param("name") String name);
	
	
	
	//인터페이스 + XML 조합의 형태 : 메소드명과 XML 아이디가 일치해야한다!! 반드시!!!
	public int insertPerson(@Param("id") String id, @Param("name") String name);
	public String selectPerson(String id);
	public int updatePerson(@Param("id") String id, @Param("name") String name);
	public int deletePerson(String id);
	public List<PersonDTO> all();
}















