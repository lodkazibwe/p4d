package com.ruraaratech.p4dafrica.Document.rest;

import com.ruraaratech.p4dafrica.Document.dto.FileRequest;
import com.ruraaratech.p4dafrica.Document.model.Plan;
import com.ruraaratech.p4dafrica.Document.service.PlanService;
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
@RequestMapping("/plan")
public class PlanController {
    @Autowired
    PlanService budgetService;

    @ApiOperation(value = "adds plan to a sector.")
    @PostMapping("/add")
    public ResponseEntity<Plan> addBudget(@RequestParam("file") MultipartFile file,
                                            @Valid @NotBlank(message ="title cannot be blank") @RequestParam("title") String title,
                                            @Valid @NotBlank(message ="sector id cannot be blank") @RequestParam("sectorId") long sectorId,
                                            @Valid @NotBlank(message ="year cannot be blank") @RequestParam("year") int year) throws IOException {
        FileRequest request =new FileRequest();
        request.setSectorId(sectorId);
        request.setTitle(title);
        request.setYear(year);
        return ResponseEntity.status(HttpStatus.OK).body(budgetService.add(file, request));
    }

    @ApiOperation(value = "gets plan by ID.")
    @GetMapping("/get/{budgetId}")
    public ResponseEntity<Plan> getBudget(@PathVariable long budgetId){
        return ResponseEntity.status(HttpStatus.OK).body(budgetService.get(budgetId));
    }

    @ApiOperation(value = "gets all plans in a sector.")
    @GetMapping("/get/sector/{sectorId}")
    public ResponseEntity<List<Plan>> getBySector(@PathVariable long sectorId){
        return ResponseEntity.status(HttpStatus.OK).body(budgetService.getAll(sectorId));
    }
}
