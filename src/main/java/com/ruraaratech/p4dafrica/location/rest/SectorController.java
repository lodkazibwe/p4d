package com.ruraaratech.p4dafrica.location.rest;

import com.ruraaratech.p4dafrica.location.dto.SectorRequest;
import com.ruraaratech.p4dafrica.location.dto.SectorResponse;
import com.ruraaratech.p4dafrica.location.model.Sector;
import com.ruraaratech.p4dafrica.location.service.SectorService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
@CrossOrigin
@RestController
@RequestMapping("/sector")
public class SectorController {
    @Autowired SectorService sectorService;

    @ApiOperation(value = "adds new sectors to a district.")
    @PostMapping("/add/{districtId}")
    public ResponseEntity<List<Sector>> addSector(@Valid @RequestBody List<SectorRequest> requests, @PathVariable long districtId){
        return ResponseEntity.status(HttpStatus.OK).body(sectorService.add(requests, districtId));
    }

    @ApiOperation(value = "returns a sector by ID.")
    @GetMapping("/get/{sectorId}")
    public ResponseEntity<Sector> getSector(@PathVariable long sectorId){
        return ResponseEntity.status(HttpStatus.OK).body(sectorService.get(sectorId));
    }

    @ApiOperation(value = "returns list of all sectors in a district.")
    @GetMapping("/get/all/{districtId}")
    public ResponseEntity<List<SectorResponse>> getAll(@PathVariable long districtId){
        return ResponseEntity.status(HttpStatus.OK).body(sectorService.getAll(districtId));
    }
}
