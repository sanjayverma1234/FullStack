package com.Group3tatastrive.VetPawtner.Service;

import com.Group3tatastrive.VetPawtner.Entity.Appointment;
import com.Group3tatastrive.VetPawtner.Repository.AppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppointmentService {

    @Autowired
    private AppointmentRepository appointmentRepository;

    public List<Appointment> getAllAppointments() {
        return appointmentRepository.findAll();
    }


    public Appointment createAppointment(Appointment appointment) {
        return appointmentRepository.save(appointment);
    }


    public Appointment updateAppointment(Long aid, Appointment appointmentDetails) {
        Appointment app = appointmentRepository.findById(aid).orElse(null);
        if (app!=null) {
            app.setAid(appointmentDetails.getAid());
            app.setUser(appointmentDetails.getUser());
            app.setPet(appointmentDetails.getPet());
            app.setPetClinic(appointmentDetails.getPetClinic());
            app.setDoctorName(appointmentDetails.getDoctorName());
            app.setAppointmentDate(appointmentDetails.getAppointmentDate());
            app.setAppointmentTime(appointmentDetails.getAppointmentTime());
            app.setPayment(appointmentDetails.getPayment());
            app.setStatus(appointmentDetails.getStatus());
        }
            return null;
        }

    public Appointment deleteAppointment(Long aid) {
        Appointment app = appointmentRepository.findById(aid).orElse(null);
        if (app != null) {
            appointmentRepository.delete(app);
        }
        return app;
    }
}

