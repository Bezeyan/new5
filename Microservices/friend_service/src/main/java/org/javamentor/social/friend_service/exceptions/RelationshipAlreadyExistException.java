package org.javamentor.social.friend_service.exceptions;

public class RelationshipAlreadyExistException extends RuntimeException{
    public RelationshipAlreadyExistException(String message) { super(message); }

    public RelationshipAlreadyExistException(String message, Throwable cause){ super(message, cause);}
}
