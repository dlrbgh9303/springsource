package com.company.domain;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data 
@AllArgsConstructor
@NoArgsConstructor

public class SpUser {
	private String userid;
	private String email;
	private boolean enabled;
	private String password;

	// ROLE 정보 담기
	private List<SpUserAuthority> authorities;
	

}
