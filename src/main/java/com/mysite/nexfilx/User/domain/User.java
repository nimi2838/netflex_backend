package com.mysite.nexfilx.User.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.security.core.annotation.AuthenticationPrincipal;

import javax.persistence.*;
<<<<<<< HEAD
=======

>>>>>>> 03da3c737a3760947dad5d4eea6d20a607a22188
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
