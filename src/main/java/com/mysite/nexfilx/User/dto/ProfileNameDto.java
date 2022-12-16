package com.mysite.nexfilx.User.dto;

import com.mysite.nexfilx.User.domain.ProfileName;
import com.mysite.nexfilx.User.domain.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProfileNameDto {

    private Long id;


    private String nickname;
    private String img;

    public ProfileNameDto(ProfileName profileName) {
        this.id = profileName.getId();
        this.nickname = profileName.getNickname();
        this.img = profileName.getImg();
    }
}
