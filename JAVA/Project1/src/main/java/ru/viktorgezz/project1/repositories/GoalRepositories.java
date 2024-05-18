package ru.viktorgezz.project1.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.viktorgezz.project1.model.Goal;

@Repository
public interface GoalRepositories extends JpaRepository<Goal, Integer> {
}
