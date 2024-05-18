package ru.viktorgezz.project1.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.viktorgezz.project1.model.Expense;

@Repository
public interface ExpenseRepositories extends JpaRepository<Expense, Integer> {

}
