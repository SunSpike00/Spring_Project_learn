package com.co.kr.service;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.co.kr.domain.BookFileDomain;
import com.co.kr.domain.BookListDomain;
import com.co.kr.domain.BookSelectDomain;
import com.co.kr.vo.BookListVO;

public interface BookUploadService {

	// 기능 명시

	// 리스트 조회
	public List<BookListDomain> bookList();
	
	//select문 출력
	public List<BookSelectDomain> bookSelect();
	
	// 인서트 및 업데이트
	public int bookUpload(BookListVO bookListVO, MultipartHttpServletRequest request, HttpServletRequest httpReq);
	
	// 하나 삭제
	public void bkContentRemove(HashMap<String, Object> map);
	
	// 하나 삭제
	public void bkFileRemove(BookFileDomain bookFileDomain);
	
	// 하나 리스트 조회
	public BookListDomain bookSelectOne(HashMap<String, Object> map);
	
	// 하나 파일 리스트 조회
	public List<BookFileDomain> bookSelectOneFile(HashMap<String, Object> map);
	
}
