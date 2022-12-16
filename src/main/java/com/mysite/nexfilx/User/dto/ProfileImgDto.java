package com.mysite.nexfilx.User.dto;

import com.mysite.nexfilx.User.domain.ProfileImg;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProfileImgDto {
    private Long id;

    private String imageUrl;

    public ProfileImgDto(ProfileImg profileImg) {
        this.id = profileImg.getId();
        this.imageUrl = profileImg.getImageUrl();
    }
}
