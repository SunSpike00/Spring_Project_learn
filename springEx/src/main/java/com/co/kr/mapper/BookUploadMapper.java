package com.co.kr.mapper;


import java.util.HashMap;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;

import com.co.kr.domain.BookContentDomain;
import com.co.kr.domain.BookFileDomain;
import com.co.kr.domain.BookListDomain;
import com.co.kr.domain.BookSelectDomain;


@Mapper
public interface BookUploadMapper {

	// 기능 명시
	
	// 목록
	public List<BookListDomain> bookList();
	//bookCluase, bookClauseEdit select 태그 문
	public List<BookSelectDomain> bookSelect();
	
	//book content insert 문
	public void bookContentUpload(BookContentDomain bookContentDomain);
	//book file insert 문
	public void bookFileUpload(BookFileDomain bookFileDomain);
	
	//book content update 문
	public void bkContentUpdate(BookContentDomain bookContentDomain);
	//book file update 문
	public void bkFileUpdate(BookFileDomain bookFileDomain);
	
	 //content delete 
	public void bkContentRemove(HashMap<String, Object> map);
	//file delete 
	public void bkFileRemove(BookFileDomain bookFileDomain);
	
	//book list one 문
	public BookListDomain bookSelectOne(HashMap<String, Object> map);
	//select one file
	public List<BookFileDomain> bookSelectOneFile(HashMap<String, Object> map);
	
}
