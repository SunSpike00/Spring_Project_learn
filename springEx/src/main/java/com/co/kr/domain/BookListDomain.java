package com.co.kr.domain;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
@Builder(builderMethodName = "bk_builder")
public class BookListDomain {

	//도서 정보 목록
	private String bkId;
	private String mbId;
	private String bkTitle;
	private String bkGenre;
	private String bkContent;
	private String bkCreateAt;
	private String bkUpdateAt;

}
