package com.apexon.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.util.StringUtils;

@Entity
@Table(name = "users", schema = "aa")
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long userId;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(nullable = false)
    @JsonIgnore
    private String password;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "contact")
    private String contact;

    @Column(name = "refresh_token")
    @JsonIgnore
    private String refreshToken;

    public String getName() {
        return StringUtils.isEmpty(this.lastName) ? this.firstName : this.firstName + " " + this.lastName;
    }
}
