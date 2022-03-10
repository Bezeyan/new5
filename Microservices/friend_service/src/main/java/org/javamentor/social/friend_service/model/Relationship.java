package org.javamentor.social.friend_service.model;

public enum Relationship {

    WAIT("WAIT"),
    ACCEPTED("ACCEPTED");

    private final String text;

    Relationship(final String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return text;
    }
}
