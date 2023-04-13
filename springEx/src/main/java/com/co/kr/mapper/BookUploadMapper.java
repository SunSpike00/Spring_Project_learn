package com.co.kr.mapper;


import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import com.co.kr.domain.BookListDomain;


@Mapper
public interface BookUploadMapper {

	// 업로드 기능 명시
	
	// 목록
	public List<BookListDomain> bookList();

}
