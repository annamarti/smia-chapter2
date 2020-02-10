package com.example.licenses.controller;

import com.example.licenses.model.License;
import com.example.licenses.service.LicenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/organizations/{organizationId}/licences")
public class LicenseServiceController {

    @Autowired
    private LicenseService licenseService;

    @GetMapping(value = "/{licenseId}/{clientType}")
    public License getLicenseWithClient(@PathVariable("organizationId") String organizationId,
                                        @PathVariable("licenseId") String licenseId,
                                        @PathVariable("clientType") String clientType) {
        return licenseService.getLicense(organizationId, licenseId, clientType);
    }

    @GetMapping
    public List<License> getLicensesByOrganizationId(@PathVariable("organizationId") String organizationId) {
        return licenseService.findLicenseByOrganizationId(organizationId);
    }

    @GetMapping("{licenseId}")
    public License getLicenses(@PathVariable("organizationId") String organizationId,
                               @PathVariable("licenseId") String licenseId) {
        return licenseService.getLicense(organizationId, licenseId);
    }

    @PostMapping
    public void addLicenses(@RequestBody License license) {
        licenseService.saveLicense(license);
    }

    @PutMapping
    public void updateLicenses(@RequestBody License license) {
        licenseService.updateLicense(license);
    }

    @DeleteMapping("{licenseId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteLicenses(@PathVariable("licenseId") String licenseId) {
        licenseService.deleteLicense(licenseService.getLicense(licenseId));
    }

}
