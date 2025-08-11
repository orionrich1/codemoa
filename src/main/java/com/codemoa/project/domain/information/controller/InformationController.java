//재용
package com.codemoa.project.domain.information.controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.codemoa.project.domain.information.entity.Book;
import com.codemoa.project.domain.information.entity.Contest;
import com.codemoa.project.domain.information.entity.Lecture;
import com.codemoa.project.domain.information.service.InformationService;


import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class InformationController {
	
	private static final String DEFAULT_PATH = "src/main/resources/static/files/information/";

	@Autowired
	private InformationService informationService;
	
	@GetMapping("/information/lecture")
	public String lectureList(Model model,
			@RequestParam(value = "pageNum", required = false, defaultValue = "1") int pageNum,
			@RequestParam(value = "type", required = false, defaultValue = "null") String type,
			@RequestParam(value = "keyword", required = false, defaultValue = "null") String keyword,
			@RequestParam(value = "order", required = false, defaultValue = "null") String order) {
		
		model.addAllAttributes(informationService.lectureList(pageNum, type, keyword, 8, 10, order));
		return "views/information/informationList";
	}
	
	@GetMapping("/information/book")
	public String bookList(Model model,
			@RequestParam(value = "pageNum", required = false, defaultValue = "1") int pageNum,
			@RequestParam(value = "type", required = false, defaultValue = "null") String type,
			@RequestParam(value = "keyword", required = false, defaultValue = "null") String keyword,
			@RequestParam(value = "order", required = false, defaultValue = "null") String order) {
		
		model.addAllAttributes(informationService.bookList(pageNum, type, keyword, 8, 10, order));
		return "views/information/informationList3";
	}
	
	@GetMapping("/information/contest")
	public String contestList(Model model,
			@RequestParam(value = "pageNum", required = false, defaultValue = "1") int pageNum,
			@RequestParam(value = "type", required = false, defaultValue = "null") String type,
			@RequestParam(value = "keyword", required = false, defaultValue = "null") String keyword,
			@RequestParam(value = "order", required = false, defaultValue = "null") String order) {
		
		model.addAllAttributes(informationService.contestList(pageNum, type, keyword, 8, 10, order));
		return "views/information/informationList2";
	}
	
	// lecture 관련
	
	@GetMapping("/information/lectureDetail")
	public String informationLectureDetail(Model model, 
			@RequestParam(value = "no") int no,
			@RequestParam(value = "pageNum", required = false, defaultValue = "1") int pageNum,
			@RequestParam(value = "type", required = false, defaultValue = "null") String type,
			@RequestParam(value = "keyword", required = false, defaultValue = "null") String keyword,
			@RequestParam(value = "order", required = false, defaultValue = "null") String order) {
		
		model.addAttribute("pageNum", pageNum);
		model.addAttribute(informationService.getLecture(no));
		model.addAttribute("order", order);
		model.addAttribute("type", type);
		model.addAttribute("keyword", keyword);
		
		return "views/information/informationLectureDetail";
	}
	
	@PostMapping("/information/lectureUpdateForm")
	public String updateLecture(Model model, HttpServletResponse response, PrintWriter out, @RequestParam("no") int no,
			@RequestParam("pass") String pass,
			@RequestParam(value = "type", required = false, defaultValue = "null") String type,
			@RequestParam(value = "keyword", required = false, defaultValue = "null") String keyword,
			@RequestParam(value = "pageNum", required = false, defaultValue = "1") int pageNum) {

		// 비밀번호 맞는지 확인
		
		Lecture lecture = informationService.getLecture(no);
		model.addAttribute("lecture", lecture);
		model.addAttribute("pageNum", pageNum);

//		boolean searchOption = type.equals("null") || keyword.equals("null") ? false : true;
//		model.addAttribute("searchOption", searchOption);
//		if (searchOption) {
//			model.addAttribute("type", type);
//			model.addAttribute("keyword", keyword);
//		}

		return "views/information/informationLectureUpdateForm";
	}
	
	@PostMapping("/information/lectureUpdate")
	public String updateLecture(Lecture lecture,
			@RequestParam(value = "addFile", required = false) MultipartFile multipartFile) throws IOException {

		// 비밀번호 맞는지 확인
		
		// 새 파일 업로드가 있는 경우에만 기존 파일 삭제 + 새 파일 저장
	    if (multipartFile != null && !multipartFile.isEmpty()) {
	        // 기존 파일명 조회
	        String existingFileName = informationService.getLecture(lecture.getRecommendNo()).getFile1();

	        // 기존 파일 삭제
	        if (existingFileName != null) {
	            File existingFile = new File(DEFAULT_PATH, existingFileName);
	            if (existingFile.exists()) {
	                existingFile.delete();
	                log.info("기존 파일 삭제됨: " + existingFile.getAbsolutePath());
	            }
	        }

	        // 새 파일 저장
	        String savedFileName = saveUploadedFile(multipartFile, DEFAULT_PATH);
	        if (savedFileName != null) {
	            lecture.setFile1(savedFileName);  // 새 파일명 설정
	        }
	    } else {
	        // 파일이 업로드되지 않은 경우에는 기존 파일명을 유지
	        String existingFileName = informationService.getLecture(lecture.getRecommendNo()).getFile1();
	        lecture.setFile1(existingFileName);
	    }
		
		informationService.updateLecture(lecture);

		return "redirect:/information/lecture";
	}

	@PostMapping("/information/lectureDelete")
	public String deleteLecture(@RequestParam("no") int no, RedirectAttributes reAttrs, @RequestParam(value="pass", required = false) String pass,
			HttpServletResponse response,
			@RequestParam(value = "pageNum", required = false, defaultValue = "1") int pageNum,
			@RequestParam(value = "type", required = false, defaultValue = "null") String type,
			@RequestParam(value = "keyword", required = false, defaultValue = "null") String keyword, PrintWriter out) {

		// 비밀번호 맞는지 확인
		
		informationService.deleteLecture(no);

		boolean searchOption = type.equals("null") || keyword.equals("null") ? false : true;
		if (searchOption) {
			reAttrs.addAttribute("type", type);
			reAttrs.addAttribute("keyword", keyword);
		}

		reAttrs.addAttribute("pageNum", pageNum);
		reAttrs.addFlashAttribute("test1", "1회성 파라미터");
		return "redirect:/information/lecture";
	}
	
	@GetMapping("/information/lectureAdd")
	public String addLecture() {
		return "views/information/informationLectureWriteForm";
	}
	
	@PostMapping("/information/lectureWrite")
	public String addLecture(Lecture lecture, @RequestParam(value = "addFile", required = false) MultipartFile multipartFile)
			throws IOException {

		System.out.println("originName : " + multipartFile.getOriginalFilename());  // originName : 다운로드.jpg
		System.out.println("name : " + multipartFile.getName());					// name : addFile (뷰 writeForm의 name)

		String savedFileName = saveUploadedFile(multipartFile, DEFAULT_PATH);
		
	    if (savedFileName != null) {
	        lecture.setFile1(savedFileName);
	    }
		
		informationService.addLecture(lecture);

		return "redirect:/information/lecture";
	}
	
	
	
	// book 관련
	
	@GetMapping("/information/bookDetail")
	public String informationBookDetail(Model model, 
			@RequestParam(value = "no") int no,
			@RequestParam(value = "pageNum", required = false, defaultValue = "1") int pageNum,
			@RequestParam(value = "type", required = false, defaultValue = "null") String type,
			@RequestParam(value = "keyword", required = false, defaultValue = "null") String keyword,
			@RequestParam(value = "order", required = false, defaultValue = "null") String order) {
		
		model.addAttribute("pageNum", pageNum);
		model.addAttribute(informationService.getBook(no));
		model.addAttribute("order", order);
		model.addAttribute("type", type);
		model.addAttribute("keyword", keyword);
		return "views/information/informationBookDetail";
	}
	
	@PostMapping("/information/bookUpdateForm")
	public String updateBook(Model model, HttpServletResponse response, PrintWriter out, @RequestParam("no") int no,
			@RequestParam("pass") String pass,
			@RequestParam(value = "type", required = false, defaultValue = "null") String type,
			@RequestParam(value = "keyword", required = false, defaultValue = "null") String keyword,
			@RequestParam(value = "pageNum", required = false, defaultValue = "1") int pageNum) {

		// 비밀번호 맞는지 확인
		
		Book book = informationService.getBook(no);
		model.addAttribute("book", book);
		model.addAttribute("pageNum", pageNum);

		return "views/information/informationBookUpdateForm";
	}
	
	@PostMapping("/information/bookUpdate")
	public String updateBook(Book book, RedirectAttributes reAttrs, HttpServletResponse response, PrintWriter out,
			@RequestParam(value = "pageNum", required = false, defaultValue = "1") int pageNum,
			@RequestParam(value = "type", required = false, defaultValue = "null") String type,
			@RequestParam(value = "keyword", required = false, defaultValue = "null") String keyword,
			@RequestParam("pub") String pub,
			@RequestParam(value = "addFile", required = false) MultipartFile multipartFile) throws IOException {

		log.info(pub);
		book.setPubDate(Timestamp.valueOf(pub + " 00:00:00"));
		
		// 비밀번호 맞는지 확인
		
		if (multipartFile != null && !multipartFile.isEmpty()) {
	        String existingFileName = informationService.getLecture(book.getBookNo()).getFile1();

	        if (existingFileName != null) {
	            File existingFile = new File(DEFAULT_PATH, existingFileName);
	            if (existingFile.exists()) {
	                existingFile.delete();
	            }
	        }

	        String savedFileName = saveUploadedFile(multipartFile, DEFAULT_PATH);
	        if (savedFileName != null) {
	            book.setFile1(savedFileName);
	        }
	    } else {
	        String existingFileName = informationService.getBook(book.getBookNo()).getFile1();
	        book.setFile1(existingFileName);
	    }
		
		informationService.updateBook(book);

		reAttrs.addAttribute("pageNum", pageNum);
		return "redirect:/information/book";
	}

	@PostMapping("/information/bookDelete")
	public String deleteBook(@RequestParam("no") int no, RedirectAttributes reAttrs, @RequestParam(value="pass", required = false) String pass,
			HttpServletResponse response,
			@RequestParam(value = "pageNum", required = false, defaultValue = "1") int pageNum,
			@RequestParam(value = "type", required = false, defaultValue = "null") String type,
			@RequestParam(value = "keyword", required = false, defaultValue = "null") String keyword, PrintWriter out) {

		// 비밀번호 맞는지 확인
		
		informationService.deleteBook(no);


		reAttrs.addAttribute("pageNum", pageNum);
		return "redirect:/information/book";
	}
	
	@GetMapping("/information/bookAdd")
	public String addBook() {
		return "views/information/informationBookWriteForm";
	}
	
	@PostMapping("/information/bookWrite")
	public String addBook(Book book, @RequestParam(value = "addFile", required = false) MultipartFile multipartFile,
			@RequestParam("pub") String pub)
			throws IOException {
	
		book.setPubDate(Timestamp.valueOf(pub + " 00:00:00"));
		

		String savedFileName = saveUploadedFile(multipartFile, DEFAULT_PATH);
		
	    if (savedFileName != null) {
	        book.setFile1(savedFileName);
	    }
		
		informationService.addBook(book);
		
		return "redirect:/information/book";
	}
	
	
	
	// contest 관련 
	
	@GetMapping("/information/contestDetail")
	public String informationContestDetail(Model model, 
			@RequestParam(value = "no") int no,
			@RequestParam(value = "pageNum", required = false, defaultValue = "1") int pageNum,
			@RequestParam(value = "type", required = false, defaultValue = "null") String type,
			@RequestParam(value = "keyword", required = false, defaultValue = "null") String keyword,
			@RequestParam(value = "order", required = false, defaultValue = "null") String order) {
		
		model.addAttribute("pageNum", pageNum);
		model.addAttribute(informationService.getContest(no));
		model.addAttribute("order", order);
		model.addAttribute("type", type);
		model.addAttribute("keyword", keyword);
		return "views/information/informationContestDetail";
	}
	
	@PostMapping("/information/contestUpdateForm")
	public String updateContest(Model model, HttpServletResponse response, PrintWriter out, @RequestParam("no") int no,
			@RequestParam("pass") String pass,
			@RequestParam(value = "type", required = false, defaultValue = "null") String type,
			@RequestParam(value = "keyword", required = false, defaultValue = "null") String keyword,
			@RequestParam(value = "pageNum", required = false, defaultValue = "1") int pageNum) {

		// 비밀번호 맞는지 확인
		
		Contest contest = informationService.getContest(no);
		model.addAttribute("contest", contest);
		model.addAttribute("pageNum", pageNum);

		return "views/information/informationContestUpdateForm";
	}
	
	@PostMapping("/information/contestUpdate")
	public String updateContest(Contest contest, RedirectAttributes reAttrs, HttpServletResponse response, PrintWriter out,
			@RequestParam(value = "pageNum", required = false, defaultValue = "1") int pageNum,
			@RequestParam(value = "type", required = false, defaultValue = "null") String type,
			@RequestParam(value = "keyword", required = false, defaultValue = "null") String keyword,
			@RequestParam(value = "addFile", required = false) MultipartFile multipartFile,
			@RequestParam("start") String start, @RequestParam("end") String end) throws IOException {

		contest.setStartDate(Timestamp.valueOf(LocalDateTime.parse(start)));
		contest.setEndDate(Timestamp.valueOf(LocalDateTime.parse(end)));
		
		// 비밀번호 맞는지 확인
		
		if (multipartFile != null && !multipartFile.isEmpty()) {
	        String existingFileName = informationService.getContest(contest.getContestNo()).getFile1();

	        if (existingFileName != null) {
	            File existingFile = new File(DEFAULT_PATH, existingFileName);
	            if (existingFile.exists()) {
	                existingFile.delete();
	            }
	        }

	        String savedFileName = saveUploadedFile(multipartFile, DEFAULT_PATH);
	        if (savedFileName != null) {
	            contest.setFile1(savedFileName);
	        }
	    } else {
	        String existingFileName = informationService.getContest(contest.getContestNo()).getFile1();
	        contest.setFile1(existingFileName);
	    }
		
		informationService.updateContest(contest);

		reAttrs.addAttribute("pageNum", pageNum);
		reAttrs.addFlashAttribute("test1", "1회성 파라미터");
		return "redirect:/information/contest";
	}

	@PostMapping("/information/contestDelete")
	public String deleteContest(@RequestParam("no") int no, RedirectAttributes reAttrs, @RequestParam(value="pass", required = false) String pass,
			HttpServletResponse response,
			@RequestParam(value = "pageNum", required = false, defaultValue = "1") int pageNum,
			@RequestParam(value = "type", required = false, defaultValue = "null") String type,
			@RequestParam(value = "keyword", required = false, defaultValue = "null") String keyword, PrintWriter out) {

		// 비밀번호 맞는지 확인
		
		informationService.deleteContest(no);


		reAttrs.addAttribute("pageNum", pageNum);
		reAttrs.addFlashAttribute("test1", "1회성 파라미터");
		return "redirect:/information/contest";
	}
	
	@GetMapping("/information/contestAdd")
	public String addContest() {
		return "views/information/informationContestWriteForm";
	}
	
	@PostMapping("/information/contestWrite")
	public String addContest(Contest contest, 
			@RequestParam(value = "addFile", required = false) MultipartFile multipartFile,
			@RequestParam("start") String start, @RequestParam("end") String end)
			throws IOException {
	    
		contest.setStartDate(Timestamp.valueOf(LocalDateTime.parse(start)));
		contest.setEndDate(Timestamp.valueOf(LocalDateTime.parse(end)));				
		
		String savedFileName = saveUploadedFile(multipartFile, DEFAULT_PATH);
		
	    if (savedFileName != null) {
	        contest.setFile1(savedFileName);
	    }

		informationService.addContest(contest);

		return "redirect:/information/contest";
	}
	
	
	
	
	private String saveUploadedFile(MultipartFile multipartFile, String uploadDir) throws IOException {
	    if (multipartFile != null && !multipartFile.isEmpty()) {
	        File parent = new File(uploadDir);

	        // 업로드 경로 디렉토리가 없으면 생성
	        if (!parent.isDirectory() && !parent.exists()) {
	            parent.mkdirs();
	        }

	        UUID uid = UUID.randomUUID();
	        String extension = StringUtils.getFilenameExtension(multipartFile.getOriginalFilename());
	        String saveName = uid.toString() + "." + extension;

	        File file = new File(parent.getAbsolutePath(), saveName);

	        multipartFile.transferTo(file); // 파일 저장

	        return saveName; // 저장된 파일 이름 반환
	    } else {
	        return null;
	    }
	}
	
}
