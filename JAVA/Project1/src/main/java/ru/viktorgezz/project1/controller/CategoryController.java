package ru.viktorgezz.project1.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.viktorgezz.project1.dto.CategoryDTO;
import ru.viktorgezz.project1.model.Category;
import ru.viktorgezz.project1.services.CategoryService;
import ru.viktorgezz.project1.util.ValidCashFlow;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {

    private final ValidCashFlow validCashFlow;
    private final CategoryService categoryService;

    @Autowired
    public CategoryController(ValidCashFlow validCashFlow, CategoryService categoryService) {
        this.validCashFlow = validCashFlow;
        this.categoryService = categoryService;
    }

    @PostMapping("/create")
    public ResponseEntity<HttpStatus> create(@RequestBody @Valid CategoryDTO categoryDTO,
                                             BindingResult bindingResult) {
        validCashFlow.checkError(bindingResult);

//        categoryService.save();
        return ResponseEntity.ok(HttpStatus.OK);
    }

//    private Category convertToCategory(CategoryDTO categoryDTO) {
//        return
//    }

    @GetMapping("/get_all_category_owner/{idAccount}")
    public List<String> getAllCategoryOwner(@PathVariable int idAccount) {
        return categoryService.getListTitleCategory(idAccount);
    }


}
