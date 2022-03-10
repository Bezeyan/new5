package jm_social_project.media_storage.exception;

public class PhotoContentNotFoundException extends RuntimeException {

    public PhotoContentNotFoundException() {
    }

    public PhotoContentNotFoundException(String message) {
        super(message);
    }

    public PhotoContentNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
