package com.mysite.nexfilx.User.service;

import com.mysite.nexfilx.User.dao.ImgRepository;
import com.mysite.nexfilx.User.dao.ProfileRepository;
import com.mysite.nexfilx.User.domain.ProfileImg;
import com.mysite.nexfilx.User.domain.ProfileName;
import com.mysite.nexfilx.User.domain.User;
import com.mysite.nexfilx.User.dto.ProfileImgDto;
import com.mysite.nexfilx.User.dto.ProfileNameDto;
import com.mysite.nexfilx.User.dto.UserProfileDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProfileNameService {

    private final ProfileRepository profileRepository;
    private final ImgRepository imgRepository;

    public void setProfileName(User user , ProfileNameDto profileNameDto, ProfileImg profileImg) {

        ProfileName profileName2 = ProfileName.builder()
                .user(user)
                .nickname(profileNameDto.getNickname())
                .img(profileImg.getImageUrl())
                .build();

        profileRepository.save(profileName2);

    }



    public UserProfileDto getProfile(User user) {
//        return profileRepository.findById(userId);
        UserProfileDto userProfileDto = new UserProfileDto(user);
        return userProfileDto;
    }

    public void updateProfile(User user, ProfileNameDto profileNameDto) {
//        profileRepository.delete(ProfileName.builder()
//                        .id(profileName.getId())
//                .build());
        Optional<ProfileName> optionalProfileName = profileRepository.findById(profileNameDto.getId());

        if(optionalProfileName.isPresent()) {
            ProfileName profileName = optionalProfileName.get();
            profileRepository.save(ProfileName.builder()
                    .id(profileName.getId())
                    .user(user)
                    .img(profileName.getImg())
                    .nickname(profileNameDto.getNickname())
                    .build());
        }

    }

    public void deleteProfile(ProfileName profileName) {
        profileRepository.delete(ProfileName.builder()
                .id(profileName.getId())
                .build());
    }


    public List<ProfileImg> getImgAll() {
        return imgRepository.findAll();
    }

    public ProfileImg findById(String imgId) {
        return imgRepository.findById(Long.valueOf(imgId)).orElseThrow();

    }

    public void setProfileImg(User user, ProfileImg profileImg, ProfileNameDto profileNameDto) {

        Optional<ProfileName> profileNameId = profileRepository.findById(profileNameDto.getId());
        if (profileNameId.isPresent()) {
            ProfileName getprofileName = profileNameId.get();

            ProfileName profileName = ProfileName.builder()
                    .id(getprofileName.getId())
                    .user(user)
                    .nickname(getprofileName.getNickname())
                    .img(profileImg.getImageUrl())
                    .build();

            profileRepository.save(profileName);
        }
    }

    public void setNickName(User user,String useremail, ProfileImg profileImg) {


        ProfileName profileName = new ProfileName(ProfileName.builder()
                .user(user)
                .nickname(useremail)
                .img(profileImg.getImageUrl())
                .build());

        profileRepository.save(profileName);
    }

//    public void findByImg(Long id) {
//        ProfileImg profileImg = imgRepository.findById(id).orElseThrow();
//        ProfileImg profileImg1 = new ProfileImg(profileImg);
//        System.out.println("profileImg : "+ profileImg1.getImageUrl());
//    }
}
