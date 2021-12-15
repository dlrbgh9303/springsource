package com.company.domain;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class ReplyPageDTO {
	private int replyCnt;
	private List<ReplyDTO> list;
}
