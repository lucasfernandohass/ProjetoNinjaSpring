package com.esoft.teste_spring.Exceptions;

public class DeleteNegadoException extends RuntimeException {
    public DeleteNegadoException(String mensagem) {
        super(mensagem);
    }
}
