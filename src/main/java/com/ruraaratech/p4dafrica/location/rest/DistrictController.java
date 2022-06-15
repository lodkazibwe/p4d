package com.ruraaratech.p4dafrica.location.rest;

import com.ruraaratech.p4dafrica.location.dto.DistrictRequest;
import com.ruraaratech.p4dafrica.location.dto.DistrictResponse;
import com.ruraaratech.p4dafrica.location.model.District;
import com.ruraaratech.p4dafrica.location.service.DistrictService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
@CrossOrigin
@RestController
@RequestMapping("/district")
public class DistrictController {
    @Autowired DistrictService districtService;

    @ApiOperation(value = "adds new districts to a country.")
    @PostMapping("/add/{countryId}")
    public ResponseEntity<List<District>> addDistrict(@Valid @RequestBody List<DistrictRequest> requests, @PathVariable long countryId){
        return ResponseEntity.status(HttpStatus.OK).body(districtService.add(requests, countryId));
    }

    @ApiOperation(value = "returns a district with all its sectors.")
    @GetMapping("/get/{districtId}")
    public ResponseEntity<DistrictResponse> getDistrict(@PathVariable long districtId){
        return ResponseEntity.status(HttpStatus.OK).body(districtService.get(districtId));
    }
    @ApiOperation(value = "returns list of all districts in a country.")
    @GetMapping("/get/all/{countryId}")
    public ResponseEntity<List<DistrictResponse>> getAll(@PathVariable long countryId){
        return ResponseEntity.status(HttpStatus.OK).body(districtService.getAll(countryId));
    }

}
