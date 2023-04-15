package com.co.kr.domain;


import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
@Builder(builderMethodName = "bk_builder")
public class BookSelectDomain {

	private String genre_id;
	private String genre_name;
}
