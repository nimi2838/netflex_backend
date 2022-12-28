package com.mysite.nexfilx.category.repository;

import com.mysite.nexfilx.Contents.domain.NetflixContents;
import com.mysite.nexfilx.Contents.domain.QNetflixContents;
import com.mysite.nexfilx.category.domain.Category;
import com.mysite.nexfilx.category.domain.QCategory;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class CategoryRepositoryImpl implements CategoryRepositoryCustom{

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<NetflixContents> getQslCategory(String keyword) {
        List<NetflixContents> categoryList =
                jpaQueryFactory
                        .select(QNetflixContents.netflixContents)
                        .distinct()
                        .from(QNetflixContents.netflixContents)
                        .join(QCategory.category)
                        .on(QCategory.category.netflixContentsId.eq(QNetflixContents.netflixContents.id))
                        .where(QNetflixContents.netflixContents.contentId.contains("Movie").and(QNetflixContents.netflixContents.contentId.contains(keyword)))
//                        .leftJoin(QNetflixContents.netflixContents)
//                        .on(QNetflixContents.netflixContents.id.eq(QCategory.category.netflixContentsId))
//                        .where(QCategory.category.categoryType.contains("시리즈").or(QCategory.category.categoryType.contains(keyword)).and(QNetflixContents.netflixContents.contentId.contains("K")))
                        .fetch();

//SELECT * FROM category
// RIGHT OUTER JOIN netflix_contents
// ON category.netflix_contents_id = netflix_contents.id
// WHERE ((category.category_type LIKE '%시리즈%' OR category.category_type LIKE '%한국%') AND netflix_contents.content_id LIKE '%K%')

    return categoryList;
    }

}
