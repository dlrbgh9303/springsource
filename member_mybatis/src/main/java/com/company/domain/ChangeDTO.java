package com.company.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ChangeDTO {
	private String userid;
	private String password; 			//현재 비밀번호
	private String new_password;		
	private String confirm_password;	//변경할 비밀번호
}
