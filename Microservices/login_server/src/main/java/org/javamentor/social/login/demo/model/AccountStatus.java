package org.javamentor.social.login.demo.model;

import java.time.LocalDate;

public enum AccountStatus {

    DEFAULT( null),
    SILVER(LocalDate.of( 2021, 12, 31)),
    GOLD(LocalDate.of( 2021, 12, 31));

    private LocalDate expiration;

    AccountStatus(LocalDate expiration) {
        this.expiration = expiration;
    }

    public LocalDate getExpiration() {
        return this.expiration;
    }
}
