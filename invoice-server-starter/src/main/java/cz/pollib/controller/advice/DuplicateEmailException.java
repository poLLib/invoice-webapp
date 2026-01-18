package cz.pollib.controller.advice;

public class DuplicateEmailException extends RuntimeException {
    public DuplicateEmailException() {
        super("duplicateEmailException");
    }
}
