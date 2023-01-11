package com.mysite.nexfilx.Contents.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NetflixContentDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true)
    private Long id;

    @ManyToOne
    @JsonIgnore
    private NetflixContents netflixContents;

    private int detailEpisode;

    @Column
    private int playtime;

    @Lob
    private String subStory;

    @Lob
    private String subImg;



}





