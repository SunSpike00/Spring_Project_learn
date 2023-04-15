package com.co.kr.domain;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
@Builder(builderMethodName= "bk_builder")
public class BookContentDomain {
	
	//도서 정보 작성 내용
	private Integer bkId;
	private String mbId;
	private String bkTitle;
	private String bkGenre;
	private String bkContent;
}
