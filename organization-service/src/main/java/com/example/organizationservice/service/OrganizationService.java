package com.example.organizationservice.service;

import com.example.organizationservice.model.Organization;
import com.example.organizationservice.repository.OrganizationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class OrganizationService {
    @Autowired
    private OrganizationRepository orgRepository;

    public Organization getOrg(String organizationId) {
        return orgRepository.findById(organizationId);
    }

    public void save(Organization org){
        org.setId( UUID.randomUUID().toString());
        orgRepository.save(org);
    }

    public void update(Organization org){
        orgRepository.save(org);
    }

    public void delete(Organization org){
        orgRepository.delete(org);
    }
}