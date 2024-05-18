package ru.viktorgezz.project1.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.viktorgezz.project1.model.Category;

@Repository
public interface CategoryRepositories extends JpaRepository<Category, Integer> {

    Category findCategoriesByTitleEquals(String title);
}
