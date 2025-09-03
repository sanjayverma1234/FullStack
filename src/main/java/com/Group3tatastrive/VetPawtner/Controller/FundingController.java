package com.Group3tatastrive.VetPawtner.Controller;

import com.Group3tatastrive.VetPawtner.Entity.Funding;
import com.Group3tatastrive.VetPawtner.Service.FundingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class FundingController {
    @Autowired
    private FundingService fundingService;
    @GetMapping("/fetch")
    public ResponseEntity<List<Funding>> GetAllFunds()
    {
        return ResponseEntity.ok(fundingService.GetAllFundingDetails());
    }
    @PostMapping("/insert")
    public ResponseEntity<Funding> insertFunds(@RequestBody Funding funding)
    {
        return ResponseEntity.ok(fundingService.insertFunds(funding));
    }
    @PutMapping("{id}")
    public ResponseEntity<Funding> modifyFunds(@PathVariable int id, @RequestBody Funding funding)
    {
        return ResponseEntity.ok(fundingService.modifyFunds(id,funding));
    }
    @DeleteMapping("funding/{id}")
    public ResponseEntity<Funding> deleteFunds(@PathVariable int id)
    {
        return ResponseEntity.ok(fundingService.deleteFunding(id));
    }
}
