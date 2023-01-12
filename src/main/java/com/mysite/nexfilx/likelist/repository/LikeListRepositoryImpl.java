package com.mysite.nexfilx.likelist.repository;

import com.mysite.nexfilx.Contents.domain.NetflixContents;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;

import static com.mysite.nexfilx.Contents.domain.QNetflixContents.*;

import static com.mysite.nexfilx.likelist.domain.QLikeList.likeList;

@RequiredArgsConstructor
public class LikeListRepositoryImpl implements LikeListRepositoryCustom{
    private final JPAQueryFactory jpaQueryFactory;



    @Override
    public List<NetflixContents> getQslUserLike(Long checkid) {


        List<NetflixContents> list =
                jpaQueryFactory
                        .selectFrom(netflixContents)
                        .innerJoin(likeList)
                        .on(likeList.netflixContents.id.eq(netflixContents.id))
                        .where(likeList.user.id.eq(checkid))
                        .fetch();
        return list;
    }


}
