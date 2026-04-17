package com.hospital.hospital_managment.Auth.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "refresh_tokens")
@Getter
@Setter
@ToString
public class RefreshToken {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String token;

    @Column(name = "expiry_date")
    private LocalDateTime expiryDate;

    @Column(name = "is_revoked")
    private Boolean isRevoked;

    @Column(name = "created_at")
    @CreationTimestamp
    private LocalDateTime createdAt;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;
}
