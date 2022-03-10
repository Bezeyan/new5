package org.javamentor.social.friend_service.exceptions;

public class RelationshipDontExistException extends RuntimeException{
    public RelationshipDontExistException(String message) { super(message); }

    public RelationshipDontExistException(String message, Throwable cause){ super(message, cause);}
}
