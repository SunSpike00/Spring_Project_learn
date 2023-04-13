package com.co.kr.domain;

import lombok.Builder;
import lombok.Data;

@Data
@Builder(builderMethodName= "builder")
public class BookContentDomain {
	
	//도서 정보 작성 내용
	private Integer bkid;
	private String mbId;
	private String bkTitle;
	private String bkGenre;
	private String bkContent;
}
