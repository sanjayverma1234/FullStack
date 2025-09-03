package com.Group3tatastrive.VetPawtner.Controller;

import com.Group3tatastrive.VetPawtner.Entity.PetHealth;
import com.Group3tatastrive.VetPawtner.Repository.PetHealthRepository;
import com.Group3tatastrive.VetPawtner.Repository.PetRepository;
import com.Group3tatastrive.VetPawtner.Service.PetHealthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/demo/pethealth")
public class PetHealthController {

    @Autowired
    PetRepository petRepository;
    @Autowired
    PetHealthRepository petHealthRepository;


    @Autowired
    PetHealthService petHealthService;

    @GetMapping
    public ResponseEntity<List<PetHealth>> GetAllData() {
        return ResponseEntity.ok(petHealthService.GetAllData());
    }

    @PostMapping("/{petId}")
    public ResponseEntity<PetHealth> insertdata(@PathVariable Integer petId, @RequestBody PetHealth petHealth) {
        return ResponseEntity.ok(petHealthService.insertdata(petId, petHealth));
    }

    @PutMapping("/{petId}/{health_id}")
    public ResponseEntity<PetHealth> updatedata(@PathVariable Integer petId, @PathVariable long health_id, @RequestBody PetHealth petHealth) {
        return ResponseEntity.ok(petHealthService.updatedata(petId, health_id, petHealth));
    }

    @DeleteMapping("{health_id}")
    public ResponseEntity<PetHealth> deletedata(@PathVariable long health_id) {
        return ResponseEntity.ok(petHealthService.deletedata(health_id));
    }
}
