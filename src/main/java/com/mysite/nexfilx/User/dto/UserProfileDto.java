package com.mysite.nexfilx.User.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mysite.nexfilx.User.domain.ProfileName;
import com.mysite.nexfilx.User.domain.User;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.OneToMany;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
public class UserProfileDto {

    private Long id;

    private String useremail;

    private String password;


    private Date lastPaymentDate;

    private List<ProfileNameDto> profileNameList;

    public UserProfileDto(User user) {
        this.id = user.getId();
        this.useremail = user.getUseremail();
        this.password = user.getPassword();
        this.lastPaymentDate = user.getLastPaymentDate();
        profileNameList = user.getProfileNameList()
                .stream()
                .map(profileName -> {
                    ProfileNameDto profileNameDto = new ProfileNameDto(profileName);
                    return profileNameDto;
                })
                .collect(Collectors.toList());

    }
}
