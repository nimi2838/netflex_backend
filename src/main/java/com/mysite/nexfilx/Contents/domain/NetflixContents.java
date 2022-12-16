package com.mysite.nexfilx.Contents.domain;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NetflixContents {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String contentNum;

    @Column(unique = true)
    @OneToMany(mappedBy = "netflixContents")
    private List<NetflixContentDetails> details;

    private String contentId;

    @Column
    private String contentName;
    private int episodes;
    private String date;
    private String actor;
    private int age;
    private String director;


    @Lob
    private String contentImg;
    @Lob
    private String rankingImg;
    @Lob
    private String contentVideo;
    @Lob
    private String detailImg;
    @Lob
    private String detailTextImg;
    @Lob
    private String videoLink;

    private String mainStory;


}
