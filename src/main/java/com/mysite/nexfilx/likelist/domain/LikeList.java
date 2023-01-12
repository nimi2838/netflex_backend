package com.mysite.nexfilx.likelist.domain;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.mysite.nexfilx.Contents.domain.NetflixContents;
import com.mysite.nexfilx.User.domain.ProfileName;
import com.mysite.nexfilx.User.domain.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity(name = "LikeList")
@Table(name="LikeList")
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
@AllArgsConstructor
@NoArgsConstructor
public class LikeList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    private NetflixContents netflixContents;


    public LikeList(User user, NetflixContents netflixContents){
        this.user = user;
        this.netflixContents = netflixContents;
    }
}
