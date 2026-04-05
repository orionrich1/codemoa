package com.codemoa.project.domain.exception;

import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.servlet.http.HttpServletRequest;

/**
 * 일반 @Controller에서 @Valid 실패 시 사용자에게 메시지를 전달하고 적절한 화면으로 리다이렉트한다.
 */
@ControllerAdvice
public class MvcValidationExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public String handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
            HttpServletRequest request,
            RedirectAttributes redirectAttributes) {
        String message = ex.getBindingResult().getFieldErrors().stream()
                .findFirst()
                .map(err -> err.getDefaultMessage() != null ? err.getDefaultMessage() : err.getCode())
                .orElse("입력 값을 확인해 주세요.");
        redirectAttributes.addFlashAttribute("error", message);

        String path = request.getServletPath();
        if (path != null && path.contains("updatePass")) {
            return "redirect:/findPass";
        }
        return "redirect:/joinForm";
    }
}
