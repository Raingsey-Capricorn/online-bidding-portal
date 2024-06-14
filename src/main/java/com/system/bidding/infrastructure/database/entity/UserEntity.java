package com.system.bidding.infrastructure.database.entity;

import com.system.bidding.infrastructure.config.constants.SecurityConstant;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * LinkedIn : <a href="https://www.linkedin.com/in/piseth-raingsey-jr-a26308a1">Piseth Raingsey Jr.</a>
 * Owner   : pisethraingsey@yahoo.com
 * Project : BiddingSystem
 */
@Data
@Entity
@Table(name = "table_users")
@EqualsAndHashCode(callSuper = true)
public class UserEntity extends CommonEntity implements Serializable {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    private SecurityConstant.Authority role;

    @Column(name = "provider")
    @Enumerated(EnumType.STRING)
    private SecurityConstant.AuthorizationProvider provider;

}
