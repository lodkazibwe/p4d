package com.ruraaratech.p4dafrica.Document.rest;

import com.ruraaratech.p4dafrica.Document.dto.FileRequest;
import com.ruraaratech.p4dafrica.Document.model.Budget;
import com.ruraaratech.p4dafrica.Document.service.BudgetService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.io.IOException;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/budget")
public class BudgetController {
    @Autowired BudgetService budgetService;

    @ApiOperation(value = "adds budget to a sector.")
    @PostMapping("/add")
    public ResponseEntity<Budget>  addBudget(@RequestParam("file") MultipartFile file,
                                             @Valid @NotBlank(message ="title cannot be blank") @RequestParam("title") String title,
                                             @Valid @NotBlank(message ="sector id cannot be blank") @RequestParam("sectorId") long sectorId,
                                             @Valid @NotBlank(message ="year cannot be blank") @RequestParam("year") int year) throws IOException {
        FileRequest request =new FileRequest();
        request.setSectorId(sectorId);
        request.setTitle(title);
        request.setYear(year);
        return ResponseEntity.status(HttpStatus.OK).body(budgetService.add(file, request));
    }

    @ApiOperation(value = "gets budget by ID.")
    @GetMapping("/get/{budgetId}")
    public ResponseEntity<Budget> getBudget(@PathVariable long budgetId){
        return ResponseEntity.status(HttpStatus.OK).body(budgetService.get(budgetId));
    }

    @ApiOperation(value = "gets all budgets in a sector.")
    @GetMapping("/get/sector/{sectorId}")
    public ResponseEntity<List<Budget>> getBySector(@PathVariable long sectorId){
        return ResponseEntity.status(HttpStatus.OK).body(budgetService.getAll(sectorId));
    }

    @ApiOperation(value = "returns all budgets.")
    @GetMapping("/get/all")
    public ResponseEntity<List<Budget>> getAll(){
        return ResponseEntity.status(HttpStatus.OK).body(budgetService.getAll());
    }

}
