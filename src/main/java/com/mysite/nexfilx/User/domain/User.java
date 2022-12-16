package com.mysite.nexfilx.User.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true)
    private Long id;

    @Column(unique = true,nullable = false)
    private String useremail;

    @Column(nullable = false)
    private String password;

    @Column(columnDefinition = "tinyint(1)", nullable = false)
    private Boolean auth;

    @Column
    private Date lastPaymentDate;

//    @OneToMany(mappedBy = "user")
//    private List<ProfileName> profileNameList;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<ProfileName> profileNameList;


    public User(User user) {
        id = user.getId();
        password = user.getPassword();
        auth = user.getAuth();
        lastPaymentDate = user.getLastPaymentDate();
        useremail = user.getUseremail();
        profileNameList = new ArrayList<>();
        user.getProfileNameList().stream()
                .forEach(profileName -> {
                    ProfileName profileName1 = new ProfileName(profileName);
                    profileNameList.add(profileName1);
                });
    }


}
