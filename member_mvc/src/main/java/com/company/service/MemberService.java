package com.company.service;

import com.company.domain.ChangeDTO;
import com.company.domain.LoginDTO;
import com.company.domain.MemberDTO;

public interface MemberService {
	public boolean register(MemberDTO memberDto);
	public MemberDTO select(String userid, String password);
	public MemberDTO overlabCheck(String userid);
	public LoginDTO login(LoginDTO loginDto);
	public boolean changePwd(ChangeDTO changeDto);
	public boolean leave(LoginDTO leaveDto);
	
}
