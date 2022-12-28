package com.mysite.nexfilx.category.repository;

import com.mysite.nexfilx.Contents.domain.NetflixContents;
import com.mysite.nexfilx.category.domain.Category;

import java.util.List;

public interface CategoryRepositoryCustom {

    List<NetflixContents> getQslCategory(String keyword);
}
