package com.example.iobackend.database.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "user_verification_tokens")
public class VerificationToken {
    private static final int EXPIRATION = 60 * 24;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String token;

    @OneToOne(targetEntity = UserModel.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "user_id")
    private UserModel user;

    private LocalDateTime expiryDate;

    public VerificationToken(String token, UserModel user) {
        this.token = token;
        this.user = user;
        calculateExpiryDate();
    }

    private void calculateExpiryDate() {
        LocalDateTime now = LocalDateTime.now();
        this.expiryDate = now.plusMinutes(EXPIRATION);
    }
}
