package org.javamentor.social.login.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.Instant;

@NoArgsConstructor
@Getter
@Setter
@Entity(name = "refreshtoken")

public class RefreshToken {


    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "account_id")
    private Account account;

    @Column()
    private String refreshTokenContent;

    @Column(nullable = false)
    private Instant expiryDate;

    public RefreshToken(Account account, String refreshTokenContent, Instant expiryDate) {
        this.account = account;
        this.refreshTokenContent = refreshTokenContent;
        this.expiryDate = expiryDate;
    }

    @Override
    public String toString() {
        return "RefreshToken{" +
                "id=" + id +
                ", token='" + refreshTokenContent + '\'' +
                ", expiryDate=" + expiryDate +
                '}';
    }
}

