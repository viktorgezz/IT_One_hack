package ru.viktorgezz.project1.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.viktorgezz.project1.model.Account;
import ru.viktorgezz.project1.model.Category;
import ru.viktorgezz.project1.model.Expense;
import ru.viktorgezz.project1.repositories.CategoryRepositories;
import ru.viktorgezz.project1.util.CashFlowOperations;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
public class CategoryService {

    private final CategoryRepositories categoryRepositories;
    private final CashFlowOperations cashFlowOperations;

    @Autowired
    public CategoryService(CategoryRepositories categoryRepositories, CashFlowOperations cashFlowOperations) {
        this.categoryRepositories = categoryRepositories;
        this.cashFlowOperations = cashFlowOperations;
    }

    @Transactional
    public void save(Category category) {
        categoryRepositories.save(category);
    }

    @Transactional
    public void delete(int id) {
        categoryRepositories.deleteById(id);
    }

    public List<String> getListTitleCategory(int idAccount) {
        Account foundAccount = cashFlowOperations.findOneAccount(idAccount);
        List<Category> categories = foundAccount.getCategories();
        return categories.stream()
                .map(this::convertToString)
                .collect(Collectors.toList());
    }

    public HashMap<String, Double> getSumCategories(int idAccount) {
        Account foundAccount = cashFlowOperations.findOneAccount(idAccount);
        List<Category> categories = foundAccount.getCategories();
        HashMap<String, Double> sumByCategory = new HashMap<>();
        for (Category category : categories) {
            sumByCategory.put(category.getTitle(), category.getExpenses()
                    .stream().mapToDouble(Expense::getAmount)
                    .sum());
        }

        return sumByCategory;
    }

    public Category findCategory(int id) {
        Optional<Category> foundCategory = categoryRepositories.findById(id);
        return foundCategory.orElse(null);

    }

    private String convertToString(Category category) {
        return category.getTitle();
    }


}
