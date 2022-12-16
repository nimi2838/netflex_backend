package com.mysite.nexfilx.Contents.dto;

import com.mysite.nexfilx.Contents.domain.NetflixContents;
import com.mysite.nexfilx.likelist.dto.LikeListDto;
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

public class NetflixDto {


    private Long id;


    private String contentNum;

    private List<NetflixDetailDto> detailDtos;

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
        detailDtos = new ArrayList<>();
        netflixContents.getDetails().stream()
                .forEach(detail -> {
                    NetflixDetailDto netflixDetailDto = new NetflixDetailDto(detail);
                    detailDtos.add(netflixDetailDto);
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



}