package org.javamentor.social.friend_service.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHandlerAdvice {
    @ExceptionHandler({RelationshipAlreadyExistException.class})
    public ResponseEntity<String> handleRelationshipAlreadyExistException(RelationshipAlreadyExistException ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
    }

    @ExceptionHandler({RelationshipDontExistException.class})
    public ResponseEntity<String> handleRelationshipDontExistException(RelationshipDontExistException ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
    }
}