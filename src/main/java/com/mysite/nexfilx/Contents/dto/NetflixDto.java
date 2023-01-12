package com.mysite.nexfilx.Contents.dto;

import com.mysite.nexfilx.Contents.domain.NetflixContentDetails;
import com.mysite.nexfilx.Contents.domain.NetflixContents;
import com.mysite.nexfilx.Contents.domain.QNetflixContents;
import com.mysite.nexfilx.likelist.dto.LikeListDto;
import com.querydsl.core.annotations.QueryProjection;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NetflixDto  {


    private Long id;


    private String contentNum;

    private List<NetflixDetailDto> details;

    private String contentId;
    private String contentName;
    private int episodes;
    private String date;
    private String actor;
    private int age;
    private String director;


    private String contentImg;

    private String rankingImg;
    private String contentVideo;

    private String detailImg;

    private String detailTextImg;

    private String mainStory;

    private String videoLink;

    private boolean likeStatus;


    public NetflixDto(NetflixContents netflixContents) {
        id = netflixContents.getId();
        contentNum = netflixContents.getContentNum();
        contentName = netflixContents.getContentName();
        contentId = netflixContents.getContentId();
        details = new ArrayList<>();
        netflixContents.getDetails().stream()
                .forEach(detail -> {
                    NetflixDetailDto netflixDetailDto = new NetflixDetailDto(detail);
                    details.add(netflixDetailDto);
                });
        episodes = netflixContents.getEpisodes();
        date = netflixContents.getDate();
        actor = netflixContents.getActor();
        age = netflixContents.getAge();
        director = netflixContents.getDirector();
        contentImg = netflixContents.getContentImg();
        rankingImg = netflixContents.getRankingImg();
        contentVideo = netflixContents.getContentVideo();
        detailImg = netflixContents.getDetailImg();
        detailTextImg = netflixContents.getDetailTextImg();
        mainStory = netflixContents.getMainStory();
        videoLink = netflixContents.getVideoLink();

        likeStatus=this.likeStatus;
    }

    @QueryProjection
    public NetflixDto(Long id, String contentNum, List<NetflixContentDetails> details, String contentId, String contentName, int episodes, String date, String actor, int age, String director, String contentImg, String rankingImg, String contentVideo, String detailImg, String detailTextImg, String mainStory, String videoLink) {
        this.id = id;
        this.contentNum = contentNum;
        details.stream()
                .forEach(detail -> {
                    NetflixDetailDto netflixDetailDto = new NetflixDetailDto(detail);
                    this.details.add(netflixDetailDto);
                });
        this.contentId = contentId;
        this.contentName = contentName;
        this.episodes = episodes;
        this.date = date;
        this.actor = actor;
        this.age = age;
        this.director = director;
        this.contentImg = contentImg;
        this.rankingImg = rankingImg;
        this.contentVideo = contentVideo;
        this.detailImg = detailImg;
        this.detailTextImg = detailTextImg;
        this.mainStory = mainStory;
        this.videoLink = videoLink;
    }

//    @QueryProjection
//    NetflixDto(
//            Long id,
//            String contentNum,
//            List<NetflixDetailDto> details,
//            String contentId,
//            String contentName,
//            Integer episodes,
//            String date,
//            String actor,
//            Integer age,
//            String director,
//            String contentImg,
//            String rankingImg,
//            String contentVideo,
//            String detailImg,
//            String detailTextImg,
//            String videoLink,
//            String mainStory
//    ) {
//
//    }




}