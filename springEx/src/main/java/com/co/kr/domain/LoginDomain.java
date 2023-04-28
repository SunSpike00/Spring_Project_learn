package com.co.kr.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder(builderMethodName="builder")
public class LoginDomain {

	private Integer mbSeq;
	private String mbId;
	private String mbPw;
	private String mbLevel;		// 기본 값 2
	private String mbIp;
	private String mbUse;		// 기본 값 Y
	private String mbCreateAt;
	private String mbUpdateAt;
	
}