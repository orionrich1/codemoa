//재용
package com.codemoa.project.domain.information.controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
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

import com.codemoa.project.domain.information.entity.Lecture;
import com.codemoa.project.domain.information.service.InformationService;


import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class InformationController {
	
	private static final String DEFAULT_PATH = "src/main/resources/static/informationfiles/";

	@Autowired
	private InformationService informationService;
	
	@GetMapping("/information")
	public String informationMain() {
		return "views/information/informationMain";
	}
	
	@GetMapping("/information/lectureList")
	public String informationLectureList(Model model,
			@RequestParam(value = "pageNum", required = false, defaultValue = "1") int pageNum,
			@RequestParam(value = "type", required = false, defaultValue = "null") String type,
			@RequestParam(value = "keyword", required = false, defaultValue = "null") String keyword) {
		
		model.addAllAttributes(informationService.lectureList(pageNum, type, keyword));
		
		return "views/information/informationLectureList";
	}
	
	@GetMapping("/information/lectureDetail")
	public String informationLectureDetail(Model model, 
			@RequestParam(value = "no") int no,
			@RequestParam(value = "pageNum", required = false, defaultValue = "1") int pageNum,
			@RequestParam(value = "type", required = false, defaultValue = "null") String type,
			@RequestParam(value = "keyword", required = false, defaultValue = "null") String keyword) {
		
		model.addAttribute("pageNum", pageNum);
		model.addAttribute(informationService.getLecture(no));
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

		boolean searchOption = type.equals("null") || keyword.equals("null") ? false : true;
		model.addAttribute("searchOption", searchOption);
		if (searchOption) {
			model.addAttribute("type", type);
			model.addAttribute("keyword", keyword);
		}

		return "views/information/informationLectureUpdateForm";
	}
	
	@PostMapping("/information/lectureUpdate")
	public String updateLecture(Lecture lecture, RedirectAttributes reAttrs, HttpServletResponse response, PrintWriter out,
			@RequestParam(value = "pageNum", required = false, defaultValue = "1") int pageNum,
			@RequestParam(value = "type", required = false, defaultValue = "null") String type,
			@RequestParam(value = "keyword", required = false, defaultValue = "null") String keyword) {

		// 비밀번호 맞는지 확인
		
		informationService.updateLecture(lecture);

		boolean searchOption = type.equals("null") || keyword.equals("null") ? false : true;
		if (searchOption) {
			reAttrs.addAttribute("type", type);
			reAttrs.addAttribute("keyword", keyword);
		}
		// return "redirect:boardList?pageNum=" + pageNum;
		reAttrs.addAttribute("pageNum", pageNum);
		reAttrs.addFlashAttribute("test1", "1회성 파라미터");
		return "redirect:/information/lectureList";
	}

	@PostMapping("/information/lectureDelete")
	public String deleteBoard(@RequestParam("no") int no, RedirectAttributes reAttrs, @RequestParam(value="pass", required = false) String pass,
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
		return "redirect:/information/lectureList";
	}
	
	@GetMapping("/information/lectureAdd")
	public String addLecture() {
		return "views/information/informationLectureWriteForm";
	}
	
	// 게시 글 쓰기 폼으로 부터 글 등록 요청을 받는 메서드
	@PostMapping("/information/lectureWrite")
	public String addLecture(Lecture lecture, @RequestParam(value = "addFile", required = false) MultipartFile multipartFile)
			throws IOException { // 커맨드 객체 (도메인 객체와 이름이 같아야)

		System.out.println("originName : " + multipartFile.getOriginalFilename());  // originName : 다운로드.jpg
		System.out.println("name : " + multipartFile.getName());					// name : addFile (뷰 writeForm의 name)
		// 업로된 파일이 있으면
		if (multipartFile != null && !multipartFile.isEmpty()) {
			// File 클래스는 파일과 디렉터리를 다루기 위한 클래스
			File parent = new File(DEFAULT_PATH);

			// 파일 업로드 위치에 폴더가 존재하지 않으면 폴더 생성
			if (!parent.isDirectory() && !parent.exists()) {
				parent.mkdirs();
			}

			UUID uid = UUID.randomUUID();
			String extension = StringUtils.getFilenameExtension(multipartFile.getOriginalFilename());  // 다운로드.jpg 의 jpg
			String saveName = uid.toString() + "." + extension;        // 994be78c-4c01-47ef-a678-0371093f6736.jpg 의 앞부분

			File file = new File(parent.getAbsolutePath(), saveName);
			// File 객체를 이용해 파일이 저장될 절대 경로 출력
			log.info("file abs path : " + file.getAbsolutePath());
// file abs path : D:\SpringBootStudy10\springbootclass-bbs05\src\main\resources\static\files\103d6f4d-16e6-4d6a-9a9d-d536be6e7ae1.jpg
			log.info("file path : " + file.getPath());
// file path : D:\SpringBootStudy10\springbootclass-bbs05\src\main\resources\static\files\103d6f4d-16e6-4d6a-9a9d-d536be6e7ae1.jpg
			
			// 업로드 되는 파일을 static/files 폴더에 복사한다.
			multipartFile.transferTo(file);

			// 업로드된 파일 이름을 게시글의 첨부 파일로 설정한다.
			lecture.setFile1(saveName);

		} else {
			// 파일이 업로드 되지 않으면 콘솔에 로그 출력
			log.info("No file uploaded - 파일이 업로드 되지 않음");

		}

		informationService.addLecture(lecture);
		// 게시글 쓰기가 완료되면 게시글 리스트로 리다이렉트 시킨다.
		
		// 리다이렉트 : 같은 글이 계속 들어가지 않게
		return "redirect:/information/lectureList";
	}
	
	
	
	
	@GetMapping("/information/bookDetail")
	public String informationBookDetail(Model model, 
			@RequestParam(value = "no") int no,
			@RequestParam(value = "isCount", defaultValue = "false") boolean isCount) {
		
		model.addAttribute(informationService.getLecture(no));
		return "views/information/informationBookDetail";
	}
	
	@GetMapping("/information/contestDetail")
	public String informationContestDetail(Model model, 
			@RequestParam(value = "no") int no,
			@RequestParam(value = "isCount", defaultValue = "false") boolean isCount) {
		
		model.addAttribute(informationService.getLecture(no));
		return "views/information/informationContestDetail";
	}
	
	@GetMapping("/information/list")
	public String informationList() {
		return "views/information/informationList";
	}
	
	
}
