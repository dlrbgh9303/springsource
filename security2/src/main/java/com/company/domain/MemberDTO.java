package com.company.domain;


import java.util.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class MemberDTO {
	private String userid;
	private String userpw;
	private String userName;
	private Date regDate;
	private Date updateDate;
	private boolean enabled;
	
	
	private List<AuthDTO> authList;
}
