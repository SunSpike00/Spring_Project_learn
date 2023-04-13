package com.co.kr.code;

import lombok.Getter;

@Getter
public enum Table {

	MEMBER("member"),
	FILES("files"),
	BOARD("board"),
	
	// 추가 테이블
	BOOK_BOARD("book_board"),
	GENRE("genre"),
	BOOK_FILES("book_files");
	
	//db 추가 테이블 작성
	
	private String table;

	Table(String table){
		this.table = table;
	}
	
}