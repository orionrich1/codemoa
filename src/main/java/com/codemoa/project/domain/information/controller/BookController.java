package com.codemoa.project.domain.information.controller;

import java.io.IOException;
import java.sql.Timestamp;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.codemoa.project.common.service.FileStorageService;
import com.codemoa.project.domain.information.entity.Book;
import com.codemoa.project.domain.information.service.InformationService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * 2-3: InformationController에서 도서(Book) 관련 핸들러만 추출.
 */
@Slf4j
@Controller
@RequiredArgsConstructor
public class BookController {

	private static final String SUB_DIR = "information";

	private final InformationService informationService;
	private final FileStorageService fileStorageService;

	@GetMapping("/information/book")
	public String bookList(Model model,
			@RequestParam(value = "pageNum", defaultValue = "1") int pageNum,
			@RequestParam(value = "type", defaultValue = "null") String type,
			@RequestParam(value = "keyword", defaultValue = "null") String keyword,
			@RequestParam(value = "order", defaultValue = "null") String order) {
		model.addAllAttributes(informationService.bookList(pageNum, type, keyword, 8, 10, order));
		return "views/information/informationList3";
	}

	@GetMapping("/information/bookDetail")
	public String bookDetail(Model model,
			@RequestParam("no") int no,
			@RequestParam(value = "pageNum", defaultValue = "1") int pageNum,
			@RequestParam(value = "type", defaultValue = "null") String type,
			@RequestParam(value = "keyword", defaultValue = "null") String keyword,
			@RequestParam(value = "order", defaultValue = "null") String order) {
		model.addAttribute("pageNum", pageNum);
		model.addAttribute(informationService.getBook(no));
		model.addAttribute("order", order);
		model.addAttribute("type", type);
		model.addAttribute("keyword", keyword);
		return "views/information/informationBookDetail";
	}

	@GetMapping("/information/bookAdd")
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	public String bookAddForm() {
		return "views/information/informationBookWriteForm";
	}

	@PostMapping("/information/bookWrite")
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	public String addBook(Book book,
			@RequestParam(value = "addFile", required = false) MultipartFile multipartFile,
			@RequestParam("pub") String pub) throws IOException {
		book.setPubDate(Timestamp.valueOf(pub + " 00:00:00"));
		String savedFileName = fileStorageService.store(multipartFile, SUB_DIR);
		if (savedFileName != null) book.setFile1(savedFileName);
		informationService.addBook(book);
		return "redirect:/information/book";
	}

	@PostMapping("/information/bookUpdateForm")
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	public String bookUpdateForm(Model model,
			@RequestParam("no") int no,
			@RequestParam(value = "pageNum", defaultValue = "1") int pageNum) {
		model.addAttribute("book", informationService.getBook(no));
		model.addAttribute("pageNum", pageNum);
		return "views/information/informationBookUpdateForm";
	}

	@PostMapping("/information/bookUpdate")
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	public String updateBook(Book book, RedirectAttributes reAttrs,
			@RequestParam(value = "pageNum", defaultValue = "1") int pageNum,
			@RequestParam("pub") String pub,
			@RequestParam(value = "addFile", required = false) MultipartFile multipartFile) throws IOException {
		book.setPubDate(Timestamp.valueOf(pub + " 00:00:00"));
		if (multipartFile != null && !multipartFile.isEmpty()) {
			String existing = informationService.getBook(book.getBookNo()).getFile1();
			fileStorageService.delete(SUB_DIR, existing);
			String savedFileName = fileStorageService.store(multipartFile, SUB_DIR);
			if (savedFileName != null) book.setFile1(savedFileName);
		} else {
			book.setFile1(informationService.getBook(book.getBookNo()).getFile1());
		}
		informationService.updateBook(book);
		reAttrs.addAttribute("pageNum", pageNum);
		return "redirect:/information/book";
	}

	@PostMapping("/information/bookDelete")
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	public String deleteBook(@RequestParam("no") int no, RedirectAttributes reAttrs,
			@RequestParam(value = "pageNum", defaultValue = "1") int pageNum) {
		Book book = informationService.getBook(no);
		if (book != null) fileStorageService.delete(SUB_DIR, book.getFile1());
		informationService.deleteBook(no);
		reAttrs.addAttribute("pageNum", pageNum);
		return "redirect:/information/book";
	}
}
