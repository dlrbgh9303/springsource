package com.company.service;

import java.util.List;

import com.company.domain.ChangeDTO;
import com.company.domain.MemberDTO;

public interface MemberService {
	public List<MemberDTO> getList();
	public MemberDTO getRow(String userid, String password);
	public boolean updateRow(ChangeDTO changeDto);
	public boolean deleteRow(String userid, String password);
	public boolean insertRow(MemberDTO dto);
}
