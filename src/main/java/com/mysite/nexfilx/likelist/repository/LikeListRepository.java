package com.mysite.nexfilx.likelist.repository;


import com.mysite.nexfilx.User.domain.User;
import com.mysite.nexfilx.likelist.domain.LikeList;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface LikeListRepository extends JpaRepository<LikeList,Long>, LikeListRepositoryCustom {
    Optional<LikeList> findByUserIdAndNetflixContentsId(Long userid, Long netflixcontentsid);
    List<LikeList> findByUserId(Long Id);
}
