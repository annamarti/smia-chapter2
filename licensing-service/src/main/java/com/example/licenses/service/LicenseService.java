package com.example.licenses.service;

import com.example.licenses.client.OrganizationRestTemplateClient;
import com.example.licenses.config.ServiceConfig;
import com.example.licenses.model.License;
import com.example.licenses.model.Organization;
import com.example.licenses.repository.LicenseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LicenseService {

    @Autowired
    private LicenseRepository licenseRepository;

    @Autowired
    private ServiceConfig serviceConfig;

    @Autowired
    private OrganizationRestTemplateClient orgRestClient;

    public License getLicense(String organizationId, String licenseId) {
        License license = licenseRepository.findByOrganizationIdAndLicenseId(organizationId, licenseId);
        Organization org = getOrganization(organizationId);
        license.setOrganizationName(org.getName());
        license.setContactName(org.getContactName());
        license.setContactEmail(org.getContactEmail());
        license.setContactPhone(org.getContactPhone());
        license.setComment(serviceConfig.getTracerProperty());
        return license;
    }

    private Organization getOrganization(String organizationId) {
        return orgRestClient.getOrganization(organizationId);
    }

    public void saveLicense(License license) {
        licenseRepository.save(license);
    }

    public void updateLicense(License license) {
        licenseRepository.save(license);
    }

    public void deleteLicense(License license) {
        licenseRepository.delete(license);
    }

    public List<License> findLicenseByOrganizationId(String organizationId) {
        return licenseRepository.findByOrganizationId(organizationId);
    }

}