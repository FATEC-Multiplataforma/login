package br.com.fatec.pokemon.controller.advice;

import br.com.fatec.pokemon.controller.dto.response.ErrorResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.http.HttpStatus.*;

@RestControllerAdvice
public class RestExceptionHandler {
    private static final Logger LOG = LoggerFactory.getLogger(RestExceptionHandler.class);

    @ResponseBody
    @ResponseStatus(INTERNAL_SERVER_ERROR)
    @ExceptionHandler({RuntimeException.class, Exception.class})
    public ErrorResponse handleInternalServerError(
            Exception exception,
            HttpServletRequest request) {
        LOG.error("Erro não mapeado: {}", exception);
        return new ErrorResponse(
                LocalDateTime.now(),
                request.getServletPath(),
                INTERNAL_SERVER_ERROR.value(),
                INTERNAL_SERVER_ERROR.getReasonPhrase(),
                exception.getMessage());
    }

    @ResponseBody
    @ResponseStatus(UNAUTHORIZED)
    @ExceptionHandler(BadCredentialsException.class)
    public ErrorResponse handleUnauthorized(
            BadCredentialsException exception,
            HttpServletRequest request) {
        System.out.println("HANDLE ERROR ON UNAUTHORIZED");
        return new ErrorResponse(
                LocalDateTime.now(),
                request.getServletPath(),
                UNAUTHORIZED.value(),
                UNAUTHORIZED.getReasonPhrase(),
                exception.getMessage());
    }

    @ResponseBody
    @ResponseStatus(UNAUTHORIZED)
    @ExceptionHandler(InternalAuthenticationServiceException.class)
    public ErrorResponse handleUnauthorized(
            InternalAuthenticationServiceException exception,
            HttpServletRequest request) {
        System.out.println("HANDLE ERROR ON UNAUTHORIZED");
        return new ErrorResponse(
                LocalDateTime.now(),
                request.getServletPath(),
                UNAUTHORIZED.value(),
                UNAUTHORIZED.getReasonPhrase(),
                exception.getMessage());
    }


    @ResponseBody
    @ResponseStatus(BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ErrorResponse handleValidationException(
            MethodArgumentNotValidException ex,
            HttpServletRequest request) {
        List<String> errors = new ArrayList<>();
        ex.getBindingResult().getAllErrors()
                .forEach(v -> errors.add(v.getDefaultMessage()));
        return new ErrorResponse(
                LocalDateTime.now(),
                request.getServletPath(),
                BAD_REQUEST.value(),
                BAD_REQUEST.getReasonPhrase(),
                errors.toString());
    }

}
