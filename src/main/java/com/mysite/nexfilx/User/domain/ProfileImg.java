package com.mysite.nexfilx.User.domain;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ProfileImg {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true)
    private Long id;

    private String imageUrl;

    public ProfileImg(ProfileImg profileImg) {
        id = profileImg.getId();
        imageUrl = profileImg.getImageUrl();
    }

}
