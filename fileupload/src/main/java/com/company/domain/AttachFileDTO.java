package com.company.domain;

import lombok.Setter;
import lombok.ToString;
import lombok.Getter;

@Getter
@Setter
@ToString
public class AttachFileDTO {
	private String uuid;
	private String uploadPath;
	private String fileName;
	private boolean fileType;
	
}
