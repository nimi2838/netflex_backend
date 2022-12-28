package com.mysite.nexfilx.category.repository;

import com.mysite.nexfilx.Contents.domain.NetflixContents;
import com.mysite.nexfilx.category.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Long>, CategoryRepositoryCustom {

    List<Category> findByNetflixContentsId(Long netflixContentsId);

    List<Category> findAllByNetflixContentsId(Long netflixContentsId);
}
