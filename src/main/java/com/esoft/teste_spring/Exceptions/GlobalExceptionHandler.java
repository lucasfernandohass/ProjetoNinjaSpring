package com.esoft.teste_spring.Exceptions;

import java.util.Date;
import java.util.LinkedHashMap;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NaoEncontradoException.class)
    public ResponseEntity<Object> NaoEncontradoExceprionHndler(NaoEncontradoException ex) {
        LinkedHashMap<String, Object> body = new LinkedHashMap<>();
        body.put("message", ex.getMessage());
        body.put("timespan", new Date());
        body.put("error", "Item não encontrado");
        body.put("status", HttpStatus.NOT_FOUND.value());

        return new ResponseEntity(body, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(DeleteNegadoException.class)
    public ResponseEntity<Object> DeleteNegadoExceprionHndler(DeleteNegadoException ex) {
        LinkedHashMap<String, Object> body = new LinkedHashMap<>();
        body.put("message", ex.getMessage());
        body.put("timespan", new Date());
        body.put("error", "Item não pode ser removido");
        body.put("status", HttpStatus.METHOD_NOT_ALLOWED.value());

        return new ResponseEntity(body, HttpStatus.METHOD_NOT_ALLOWED);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> GlobalExceptionsHandler(Exception ex) {

        LinkedHashMap<String, Object> body = new LinkedHashMap<>();
        body.put("message", ex.getMessage());
        body.put("timespan", new Date());
        body.put("error", "Erro interno do Servidor!");
        body.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());

        return new ResponseEntity(body, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
