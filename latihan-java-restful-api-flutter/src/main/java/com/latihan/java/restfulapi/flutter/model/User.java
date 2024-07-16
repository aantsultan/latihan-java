package com.latihan.java.restfulapi.flutter.model;

import com.latihan.java.restfulapi.flutter.helper.Gender;
import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name = "m_user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    private String fullName;
    private String email;

    @Column(unique = true)
    private String phoneNumber;

    private Date birthDate;
    private Gender gender;
    private String password;
    private Boolean promoEvents;
    private Boolean termConditions;

    @Column(unique = true)
    private String token;

    private Long tokenExpiredAt;

}
