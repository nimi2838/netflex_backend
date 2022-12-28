package com.mysite.nexfilx.category.service;

import com.mysite.nexfilx.Contents.dao.ContentRepository;
import com.mysite.nexfilx.Contents.domain.NetflixContents;
import com.mysite.nexfilx.category.domain.Category;
import com.mysite.nexfilx.category.repository.CategoryRepository;
import com.mysite.nexfilx.category.repository.CategoryRepositoryCustom;
import com.mysite.nexfilx.category.repository.CategoryRepositoryImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryService {

    @Autowired
    private final CategoryRepository categoryRepository;



    @Autowired
    private final ContentRepository contentRepository;

    public List<Category> getCategoryType(Long id) {
        List<Category> categoryList = categoryRepository.findByNetflixContentsId(id);
        Optional<NetflixContents> netflixContents = contentRepository.findById(id);

        return categoryList;

    }

    public List<NetflixContents> findSeries(String keyword) {


            List<NetflixContents> findseries = categoryRepository.getQslCategory(keyword);


        return findseries;
    }
}
