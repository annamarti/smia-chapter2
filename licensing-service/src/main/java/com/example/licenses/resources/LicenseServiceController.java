package com.example.licenses.resources;

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
    public void updateLicenses(@PathVariable("licenseId") String licenseId,
                               @RequestBody License license) {
        licenseService.updateLicense(license);
    }

    @DeleteMapping("{licenseId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public String deleteLicenses(@PathVariable("licenseId") String licenseId) {
        return String.format("This is the Delete");
    }

}
