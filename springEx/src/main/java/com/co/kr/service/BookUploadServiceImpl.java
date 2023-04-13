package com.co.kr.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.co.kr.domain.BookListDomain;
import com.co.kr.mapper.BookUploadMapper;

import lombok.extern.slf4j.Slf4j;


@Slf4j
@Service
@Transactional
public class BookUploadServiceImpl implements BookUploadService  {

	// 기능 구현
	@Autowired
	BookUploadMapper bookUploadMapper;
	
	@Override
	public List<BookListDomain> bookList() {
		return bookUploadMapper.bookList();
	}
	
	
}
