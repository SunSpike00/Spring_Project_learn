package com.co.kr.domain;

import lombok.Builder;
import lombok.Data;

@Data
@Builder(builderMethodName = "bk_builder")
public class BookFileDomain {

	//도서 정보 업로드 파일
	private Integer bkId;
	private String mbId;
	private String bkOriginalFileName;
	private String bkNewFileName; //동일 이름 업로드 될 경우
	private String bkFilePath;
	private Integer bkFileSize;
	
}
