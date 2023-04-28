package com.co.kr.controller;

import java.io.IOException;
import java.nio.file.DirectoryNotEmptyException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.co.kr.code.Code;
import com.co.kr.domain.BookFileDomain;
import com.co.kr.domain.BookListDomain;
import com.co.kr.domain.BookSelectDomain;
import com.co.kr.exception.RequestException;
import com.co.kr.service.BookUploadService;
import com.co.kr.vo.BookListVO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class BookFileListController {

	@Autowired
	private BookUploadService bookUploadService;
	
	@PostMapping(value = "bookUpload")
	public ModelAndView bookUpload(BookListVO bookListVO, MultipartHttpServletRequest request, HttpServletRequest httpReq) throws IOException, ParseException {
		
		ModelAndView mav = new ModelAndView();
		int bkId = bookUploadService.bookUpload(bookListVO, request, httpReq);
		bookListVO.setBkContent(""); //초기화
		bookListVO.setBkTitle(""); //초기화
		// view setting
		mav = bkSelectOneCall(bookListVO, String.valueOf(bkId),request);
		mav.setViewName("book/bookList.html");
		return mav;
		
	}
	
	
	@GetMapping("bookDetail")
    public ModelAndView bookDetail(@ModelAttribute("bookListVO") BookListVO bookListVO, @RequestParam("bkId") String bkId, HttpServletRequest request) throws IOException {
		ModelAndView mav = new ModelAndView();
		
		// view setting
		mav = bkSelectOneCall(bookListVO, bkId, request);
		mav.setViewName("book/bookList.html");
		return mav;
	}
	
	
	public ModelAndView bkSelectOneCall(@ModelAttribute("bookListVO") BookListVO bookListVO, String bkId, HttpServletRequest request) {
		
		ModelAndView mav = new ModelAndView();
		HashMap<String, Object> map = new HashMap<String, Object>();
		HttpSession session = request.getSession();
		
		map.put("bkId", Integer.parseInt(bkId));
		BookListDomain bookListDomain = bookUploadService.bookSelectOne(map);
		System.out.println("bookListDomain"+bookListDomain);
		List<BookFileDomain> bookfileList =  bookUploadService.bookSelectOneFile(map);
		
		for (BookFileDomain list : bookfileList) {
			String path = list.getBkFilePath().replaceAll("\\\\", "/");
			list.setBkFilePath(path);
		}
		
		
		// setting
		mav.addObject("bookDetail", bookListDomain); // 수정
		mav.addObject("bkSelect", genre());	// select 태그 문 출력
		mav.addObject("genreCode",  genreSelect(bookListDomain)); // 대상 장르 코드 값으로 selected 걸기
		mav.addObject("bookFiles", bookfileList); // 수정
		session.setAttribute("bookFiles", bookfileList); // 수정

		return mav;
	}
	
	// BookListDomain이 선택한 장르 세팅
	public BookSelectDomain genreSelect(BookListDomain bookListDomain) {
		
		List<BookSelectDomain> list = bookUploadService.bookSelect();
		BookSelectDomain value = null;
		for(BookSelectDomain selectDomain : list) {
			
			if(selectDomain.getGenre_id().equals(bookListDomain.getBkGenre())) {
				value = selectDomain;
			}
		}
		return value;
	}
	
	// Select 태그 세팅
	public List<BookSelectDomain> genre() {
		return bookUploadService.bookSelect();
	}
	
	
	@GetMapping("bookEdit")
	public ModelAndView bookEdit(BookListVO bookListVO, @RequestParam("bkId") String bkId, HttpServletRequest request) throws IOException {
		
		ModelAndView mav = new ModelAndView();
		HashMap<String, Object> map = new HashMap<String, Object>();
		HttpSession session = request.getSession();
		
		map.put("bkId", Integer.parseInt(bkId));
		BookListDomain bookListDomain = bookUploadService.bookSelectOne(map);
		List<BookFileDomain> bookfileList = bookUploadService.bookSelectOneFile(map);
		
		for (BookFileDomain list : bookfileList) {
			String path = list.getBkFilePath().replaceAll("\\\\", "/");
			list.setBkFilePath(path);
		}
		
		//bookListVO setting
		bookListVO.setBkId(bookListDomain.getBkId());
		bookListVO.setBkContent(bookListDomain.getBkContent());
		bookListVO.setBkTitle(bookListDomain.getBkTitle());
		bookListVO.setIsEdit("edit");
		
		//view setting
		mav.addObject("bookDetail", bookListDomain);
		mav.addObject("bookFiles", bookfileList);
		mav.addObject("bookFileLen", bookfileList.size());
		mav.addObject("bkSelect", genre());	// select 태그 문 출력
		mav.addObject("genreCode",  genreSelect(bookListDomain)); // 대상 장르 코드 값으로 selected 걸기
		mav.setViewName("book/bookEditList.html");
		
		return mav;
	}
	

	
	@PostMapping("bookEditSave")
	public ModelAndView bookEditSave(@ModelAttribute("bookListVO") BookListVO bookListVO, MultipartHttpServletRequest request, HttpServletRequest httpReq) throws IOException {
		ModelAndView mav = new ModelAndView();
		
		//저장
		bookUploadService.bookUpload(bookListVO, request, httpReq);
		
		//setting
		mav = bkSelectOneCall(bookListVO, bookListVO.getBkId(), request);
		bookListVO.setBkContent("");
		bookListVO.setBkTitle("");
		mav.setViewName("book/bookList.html");
		return mav;
	}
	
	@SuppressWarnings("unchecked")
	@GetMapping("bookRemove")
	public ModelAndView mbRemove(@RequestParam("bkId") String bkId, HttpServletRequest request) throws IOException {
		ModelAndView mav = new ModelAndView();
		
		HttpSession session = request.getSession();
		HashMap<String, Object> map = new HashMap<String, Object>();
		List<BookFileDomain> bookFileList = null;
		if(session.getAttribute("bookFiles") != null) {						
			bookFileList = (List<BookFileDomain>) session.getAttribute("bookFiles");
		}

		map.put("bkId", Integer.parseInt(bkId));
		
		//내용삭제
		bookUploadService.bkContentRemove(map);

		for (BookFileDomain list : bookFileList) {
			list.getBkFilePath();
			Path bkFilePath = Paths.get(list.getBkFilePath());
	 
	        try {
	        	
	            // 파일 물리삭제
	            Files.deleteIfExists(bkFilePath); // notfound시 exception 발생안하고 false 처리
	            // db 삭제 
				bookUploadService.bkFileRemove(list);
				
	        } catch (DirectoryNotEmptyException e) {
							throw RequestException.fire(Code.E404, "디렉토리가 존재하지 않습니다", HttpStatus.NOT_FOUND);
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
		}

		//세션해제
		session.removeAttribute("bookFiles"); // 삭제
		mav = bkListCall();
		mav.addObject("bkSelect", genre());
		mav.setViewName("book/bookList.html");
		return mav;
	}

	public ModelAndView bkListCall() {
		ModelAndView mav = new ModelAndView();
		List<BookListDomain> items = bookUploadService.bookList();
		mav.addObject("items", items);
		return mav;
	}	
	
}
