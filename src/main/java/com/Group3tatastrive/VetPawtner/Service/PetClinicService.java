package com.Group3tatastrive.VetPawtner.Service;

import com.Group3tatastrive.VetPawtner.Entity.PetClinic;
import com.Group3tatastrive.VetPawtner.Repository.PetClinicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PetClinicService {

    @Autowired
    private PetClinicRepository petClinicRepository;

    public List<PetClinic> fetchallclinic() {
        return petClinicRepository.findAll();
    }

    public PetClinic insertclinic(PetClinic pc) {
        return petClinicRepository.save(pc);
    }

    public PetClinic updateallclinic(long cid, PetClinic pc) {
        PetClinic pcl = petClinicRepository.findById(cid).orElse(null);
        if (pcl != null) {
            pcl.setCname(pc.getCname());
            pcl.setCaddress(pc.getCaddress());
            pcl.setCphonenum(pc.getCphonenum());
            pcl.setEmail(pc.getEmail());
            pcl.setServiceoffered(pc.getServiceoffered());
            pcl.setOperatinghours(pc.getOperatinghours());
            pcl.setPayemt(pc.getPayemt());
            return petClinicRepository.save(pcl);
        }
        return null;
    }

    public PetClinic deletepetclinic(long cid) {
        PetClinic pcl = petClinicRepository.findById(cid).orElse(null);
        if (pcl != null) {
            petClinicRepository.delete(pcl);
        }
        return pcl;
    }
}
