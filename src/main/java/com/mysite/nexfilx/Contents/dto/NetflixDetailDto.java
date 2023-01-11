package com.mysite.nexfilx.Contents.dto;

import com.mysite.nexfilx.Contents.domain.NetflixContentDetails;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NetflixDetailDto {
    private Long id;

    private int detailEpisode;

    private int playtime;

    private String subStory;

    private String subImg;

    public NetflixDetailDto(NetflixContentDetails netflixContentDetails) {
        id = netflixContentDetails.getId();
        detailEpisode = netflixContentDetails.getDetailEpisode();
        playtime = netflixContentDetails.getPlaytime();
        subStory = netflixContentDetails.getSubStory();
        subImg = netflixContentDetails.getSubImg();
    }

}
