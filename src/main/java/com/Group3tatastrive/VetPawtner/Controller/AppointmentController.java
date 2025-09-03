package com.Group3tatastrive.VetPawtner.Controller;

import com.Group3tatastrive.VetPawtner.Entity.Appointment;
import com.Group3tatastrive.VetPawtner.Service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("Appointment")
public class AppointmentController {

    @Autowired
    private AppointmentService appointmentService;


    // fetch all appointments
    @GetMapping("/all")
    public ResponseEntity<List<Appointment>> getAllAppointments() {
        return ResponseEntity.ok(appointmentService.getAllAppointments());
    }

    // Insert a new appointment
    @PostMapping("/create")
    public ResponseEntity<Appointment> createAppointment(@RequestBody Appointment appointment) {
        return ResponseEntity.ok(appointmentService.createAppointment(appointment));
    }


    // Update an existing appointment
    @PutMapping("/{aid}")
    public ResponseEntity<Appointment> updateAppointment(@PathVariable Long aid, @RequestBody Appointment appointmentDetails) {
        return ResponseEntity.ok(appointmentService.updateAppointment(aid, appointmentDetails));
    }

    // Delete an appointment
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAppointment(@PathVariable Long aid) {
        appointmentService.deleteAppointment(aid);
        return ResponseEntity.ok("Appointment with ID " + aid + " deleted successfully.");
    }
}
