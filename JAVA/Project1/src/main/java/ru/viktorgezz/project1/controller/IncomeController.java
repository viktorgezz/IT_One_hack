package ru.viktorgezz.project1.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.viktorgezz.project1.dto.IncomeDTO;
import ru.viktorgezz.project1.model.Income;
import ru.viktorgezz.project1.services.IncomeService;
import ru.viktorgezz.project1.util.ValidCashFlow;

@RestController
@RequestMapping("/income")
public class IncomeController {

    private final IncomeService incomeService;
    private final ValidCashFlow validCashFlow;

    @Autowired
    public IncomeController(IncomeService incomeService, ValidCashFlow validCashFlow) {
        this.incomeService = incomeService;
        this.validCashFlow = validCashFlow;
    }

    @GetMapping("/get_total_profit/{idAccount}")
    public Double getTotalProfit(@PathVariable int idAccount) {
        return incomeService.getSumIncome(idAccount);
    }

    @PostMapping("/create")
    public ResponseEntity<HttpStatus> create(@RequestBody @Valid IncomeDTO incomeDTO,
                                             BindingResult bindingResult) {
        validCashFlow.checkError(bindingResult);

        incomeService.save(convertToIncome(incomeDTO));
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable int id) {
        incomeService.delete(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    private Income convertToIncome(IncomeDTO incomeDTO) {
        return new Income(
                incomeDTO.getProfit(),
                incomeDTO.getTitle(),
                incomeService.findOneAccount(incomeDTO.getAccountId())
        );
    }

}
