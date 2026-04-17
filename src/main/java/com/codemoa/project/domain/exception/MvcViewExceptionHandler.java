package com.codemoa.project.domain.exception;

import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

/**
 * Thymeleaf({@code @Controller}) 화면에서의 예외 응답.
 * {@link GlobalExceptionHandler}는 {@code @RestController} 전용이라 JSON API와 충돌하지 않는다.
 */
@ControllerAdvice
@Order
public class MvcViewExceptionHandler {

	@ExceptionHandler(IllegalArgumentException.class)
	public ModelAndView handleIllegalArgument(IllegalArgumentException ex) {
		ModelAndView mav = new ModelAndView("error/404");
		mav.addObject("status", 404);
		mav.addObject("error", "Not Found");
		mav.addObject("message", ex.getMessage());
		mav.setStatus(HttpStatus.NOT_FOUND);
		return mav;
	}

	@ExceptionHandler(IllegalStateException.class)
	public ModelAndView handleIllegalState(IllegalStateException ex) {
		ModelAndView mav = new ModelAndView("error/403");
		mav.addObject("status", 403);
		mav.addObject("error", "Forbidden");
		mav.addObject("message", ex.getMessage());
		mav.setStatus(HttpStatus.FORBIDDEN);
		return mav;
	}
}
