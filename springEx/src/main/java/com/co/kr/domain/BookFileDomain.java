package com.co.kr.domain;

import lombok.Builder;
import lombok.Data;

@Data
@Builder(builderMethodName = "builder")
public class BookFileDomain {

	//도서 정보 업로드 파일
	private Integer bkSeq;
	private String mbId;
	private String upbkOriginalFileName;
	private String upbkNewFileName; //동일 이름 업로드 될 경우
	private String upbkFilePath;
	private Integer upbkFileSize;
	
}
