//package com.js.freeproject.domain.category;
//
//import com.js.freeproject.domain.category.application.CategoryService;
//import com.js.freeproject.domain.category.domain.Category;
//import com.js.freeproject.domain.category.domain.CategoryRepository;
//import com.js.freeproject.domain.user.domain.User;
//import com.js.freeproject.domain.user.domain.UserRepository;
//import org.assertj.core.api.Assertions;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.annotation.Rollback;
//import org.springframework.transaction.annotation.Transactional;
//
//import javax.persistence.EntityManager;
//import javax.swing.text.html.parser.Entity;
//
//import java.util.List;
//
//import static org.assertj.core.api.Assertions.*;
//import static org.junit.jupiter.api.Assertions.*;
//
//@SpringBootTest
//@Transactional
//class CategoryServiceTest {
//    @Autowired
//    EntityManager em;
//
//    @Autowired
//    CategoryService categoryService;
//
//    @Autowired
//    CategoryRepository categoryRepository;
//
//    @Autowired
//    UserRepository userRepository;
//
//    @Test
//    @Rollback(false)
//    public void saveCategoryTest(){
//        User user = User.builder()
//                .email("sgs1159@naver.com")
//                .nickName("조순몬")
//                .name("이조순")
//                .pass("abcd1234")
//                .image("abd")
//                .build();
//        userRepository.save(user);
//        em.persist(user);
//        em.flush();
//        em.clear();
//        Category category = new Category("운영체제","컴퓨터구조를 배워봐요");
//        em.persist(category);
//
//        Category category2 = new Category("네트워크","이게 네트워크지");
//        em.persist(category2);
//
//        em.flush();
//        em.clear();
//
//        List<Category> categories = categoryService.findCategorys();
//
//        assertThat(categories.size()).isEqualTo(2);
//        for (Category category1: categories) {
//            System.out.println(category1.getName());
//        }
//    }
//}