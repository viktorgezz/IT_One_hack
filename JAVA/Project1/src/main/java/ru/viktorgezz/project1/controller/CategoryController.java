package ru.viktorgezz.project1.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.viktorgezz.project1.dto.CategoryDTO;
import ru.viktorgezz.project1.model.Account;
import ru.viktorgezz.project1.model.Category;
import ru.viktorgezz.project1.services.CategoryService;
import ru.viktorgezz.project1.util.CashFlowOperations;
import ru.viktorgezz.project1.util.ValidCashFlow;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {

    private final ValidCashFlow validCashFlow;
    private final CategoryService categoryService;
    private final CashFlowOperations cashFlowOperations;

    @Autowired
    public CategoryController(ValidCashFlow validCashFlow, CategoryService categoryService, CashFlowOperations cashFlowOperations) {
        this.validCashFlow = validCashFlow;
        this.categoryService = categoryService;
        this.cashFlowOperations = cashFlowOperations;
    }

    @PostMapping("/create")
    public ResponseEntity<HttpStatus> create(@RequestBody @Valid CategoryDTO categoryDTO,
                                             BindingResult bindingResult) {
        validCashFlow.checkError(bindingResult);

        categoryService.save(convertToCategory(categoryDTO));
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @GetMapping("/get_sum_categories/{idAccount}")
    public HashMap<String, Double> getSumCategories(@PathVariable int idAccount) {
        return categoryService.getSumCategories(idAccount);
    }

    @GetMapping("/get_all_category_owner/{idAccount}")
    public List<String> getAllCategoryOwner(@PathVariable int idAccount) {
        return categoryService.getListTitleCategory(idAccount);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable int id) {
        categoryService.delete(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    private Category convertToCategory(CategoryDTO categoryDTO) {
        Account account = cashFlowOperations.findOneAccount(categoryDTO.getAccountId());
        return new Category(
                categoryDTO.getTitleCategory(),
                account,
                account.getExpenses()
        );
    }



}
