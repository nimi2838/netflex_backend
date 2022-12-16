package com.mysite.nexfilx.User.dao;

import com.mysite.nexfilx.User.domain.ProfileImg;
import com.mysite.nexfilx.User.domain.ProfileName;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImgRepository extends JpaRepository<ProfileImg, Long> {
}
