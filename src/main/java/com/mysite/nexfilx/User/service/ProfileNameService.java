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

    //   프로필 초기 등록
    public void setProfileName(User user , ProfileNameDto profileNameDto, ProfileImg profileImg) {

        ProfileName profileName2 = ProfileName.builder()
                .user(user)
                .nickname(profileNameDto.getNickname())
                .img(profileImg.getImageUrl())
                .build();

        profileRepository.save(profileName2);

    }




    //    유저 별 프로필 전체 조회

    public UserProfileDto getProfile(User user) {
//        return profileRepository.findById(userId);
        UserProfileDto userProfileDto = new UserProfileDto(user);
        return userProfileDto;
    }


    //    프로필 수정
    public void updateProfile(User user, ProfileNameDto profileNameDto, ProfileImg profileImg ) {

        Optional<ProfileName> optionalProfileName = profileRepository.findById(profileNameDto.getId());

        if(optionalProfileName.isPresent()) {
            ProfileName profileName = optionalProfileName.get();
            profileRepository.save(ProfileName.builder()
                    .id(profileName.getId())
                    .user(user)
                    .img(profileImg.getImageUrl())
                    .nickname(profileNameDto.getNickname())
                    .build());
        }

    }


    //    프로필 삭제

    public void deleteProfile(ProfileName profileName) {
        profileRepository.delete(ProfileName.builder()
                .id(profileName.getId())
                .build());
    }



    //   프로필 이미지 전체 조회

    public List<ProfileImg> getImgAll() {
        return imgRepository.findAll();
    }

    public ProfileImg findById(String imgId) {
        System.out.println("imgId : "+ imgId);
        return imgRepository.findById(Long.valueOf(imgId)).orElseThrow();

    }


//    public void setProfileImg(User user, ProfileImg profileImg, ProfileNameDto profileNameDto) {

//
//        Optional<ProfileName> profileNameId = profileRepository.findById(profileNameDto.getId());
//        if (profileNameId.isPresent()) {
//            ProfileName getprofileName = profileNameId.get();
//
//            ProfileName profileName = ProfileName.builder()
//                    .id(getprofileName.getId())

//                    .user(user)

//                    .nickname(getprofileName.getNickname())
//                    .img(profileImg.getImageUrl())
//                    .build();
//
//            profileRepository.save(profileName);
//        }
//    }


    //    회원가입 시 첫 프로필 이름 아이디로 입력되게 함

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
