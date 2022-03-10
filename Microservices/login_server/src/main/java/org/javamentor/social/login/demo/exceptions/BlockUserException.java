package org.javamentor.social.login.demo.exceptions;


public class BlockUserException extends RuntimeException{

    public BlockUserException(String message) { super(message); }

    public BlockUserException(String message, Throwable cause){ super(message, cause);}
}
