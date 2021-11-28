package com.company.service;

import com.company.domain.MemberDTO;

public interface MemberService {
	public boolean register(MemberDTO memberDto);
	public MemberDTO select(String userid, String password);
	public MemberDTO overlabCheck(String userid);
}
