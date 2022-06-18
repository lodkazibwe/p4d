package com.ruraaratech.p4dafrica.location.rest;

import com.ruraaratech.p4dafrica.location.dto.CountryDto;
import com.ruraaratech.p4dafrica.location.dto.CountryRequest;
import com.ruraaratech.p4dafrica.location.dto.CountryResponse;
import com.ruraaratech.p4dafrica.location.model.Country;
import com.ruraaratech.p4dafrica.location.service.CountryService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/country")
public class CountryController {
    @Autowired CountryService countryService;

    @ApiOperation(value = "adds a new country.")
    @PostMapping("/add")
    public ResponseEntity<List<Country>>  addCountry(@Valid @RequestBody List<CountryRequest> requests){
        return ResponseEntity.status(HttpStatus.OK).body(countryService.addCountry(requests));
    }

    @ApiOperation(value = "returns country with all its districts and sectors.")
    @GetMapping("/get/{CountryId}")
    public ResponseEntity<CountryResponse> getCountry(@PathVariable long CountryId){
        return ResponseEntity.status(HttpStatus.OK).body(countryService.getCountry(CountryId));
    }

    @ApiOperation(value = "returns list of all countries.")
    @GetMapping("/get")
    public ResponseEntity<List<CountryDto>> getCountry(){
        return ResponseEntity.status(HttpStatus.OK).body(countryService.getAll());
    }
}
