package com.mysite.nexfilx.category.domain;

import com.mysite.nexfilx.Contents.domain.NetflixContents;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "category")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;


    @Column(name = "categoryType")
    private String categoryType;

    private Long netflixContentsId;


}
