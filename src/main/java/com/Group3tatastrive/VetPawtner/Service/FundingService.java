package com.Group3tatastrive.VetPawtner.Service;

import com.Group3tatastrive.VetPawtner.Entity.Funding;
import com.Group3tatastrive.VetPawtner.Repository.FundingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
public class FundingService {
    @Autowired
    private FundingRepository fundingRepository;
    public List<Funding> GetAllFundingDetails()
    {
        return fundingRepository.findAll();
    }

    public Funding insertFunds(@RequestBody Funding funding)
    {
        return fundingRepository.save(funding);
    }

    public Funding modifyFunds(@PathVariable int id, @RequestBody Funding funding)
    {
        Funding funding1 = fundingRepository.findById(id).orElse(null);
        funding1.setFunding_type(funding.getFunding_type());
        funding1.setPurpose(funding.getPurpose());
        return fundingRepository.save(funding1);
    }

    public Funding deleteFunding(int id)
    {
        Funding funding1 = fundingRepository.findById(id).orElse(null);
        if(funding1 != null)
        {
            fundingRepository.delete(funding1);
        }
        return funding1;
    }
}
