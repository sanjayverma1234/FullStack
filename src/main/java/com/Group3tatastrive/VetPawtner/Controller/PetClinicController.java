package com.Group3tatastrive.VetPawtner.Controller;

import com.Group3tatastrive.VetPawtner.Entity.PetClinic;
import com.Group3tatastrive.VetPawtner.Service.PetClinicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/PetClinic")
public class PetClinicController {

    @Autowired
    private PetClinicService petClinicService;

    // fetch
    @GetMapping("/pcfetch")
    public ResponseEntity<List<PetClinic>> fetchallclinic() {
        return ResponseEntity.ok(petClinicService.fetchallclinic());
    }

    // insert
    @PostMapping("pcinsert")
    public ResponseEntity<PetClinic> insertclinic(@RequestBody PetClinic pc) {
        return ResponseEntity.ok(petClinicService.insertclinic(pc));
    }

    // update
    @PutMapping("{cid}")
    public ResponseEntity<PetClinic> updateallclinic(@PathVariable int cid, @RequestBody PetClinic pc) {
        return ResponseEntity.ok(petClinicService.updateallclinic(cid, pc));
    }

    // delete
    @DeleteMapping("{cid}")
    public ResponseEntity<PetClinic> deletepetclinic(@PathVariable int cid) {
        return ResponseEntity.ok(petClinicService.deletepetclinic(cid));
    }
}
