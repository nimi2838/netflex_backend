package dao;

import com.mysite.nexfilx.category.domain.Category;
import com.mysite.nexfilx.category.repository.CategoryRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class CategoryRepositoryTests {

    @Autowired
    private CategoryRepository categoryRepository;

    @Test
    @DisplayName("시리즈 카테고리")
    void t1() {
        Category category1 = Category.builder()

                .build();
    }


}
