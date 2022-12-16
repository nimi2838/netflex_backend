package com.mysite.nexfilx.User.domain;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ProfileName {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    private User user;

    private String nickname;

    private String img;

    public ProfileName(ProfileName profileName) {
        id = profileName.getId();
        user = profileName.getUser();
        nickname = profileName.getNickname();
        img = profileName.getImg();

    }

}
