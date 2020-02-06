package com.example.licenses.resources;

import com.example.licenses.model.License;
import com.example.licenses.service.LicenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/organizations/{organizationId}/licences")
public class LicenseServiceController {
    @Autowired
    private LicenseService licenseService;

    @RequestMapping(value="/{licenseId}",method = RequestMethod.GET)
    public License getLicenses(@PathVariable("organizationId") String organizationId,
                               @PathVariable("licenseId") String licenseId) {

        //return licenseService.getLicense(licenseId);
        return licenseService.getLicense(organizationId, licenseId);
    }

    @RequestMapping(value="{licenseId}",method = RequestMethod.PUT)
    public String updateLicenses( @PathVariable("licenseId") String licenseId) {
        return String.format("This is the put");
    }

    @RequestMapping(value="{licenseId}",method = RequestMethod.POST)
    public String saveLicenses( @PathVariable("licenseId") String licenseId) {
        return String.format("This is the post");
    }

    @RequestMapping(value="{licenseId}",method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public String deleteLicenses( @PathVariable("licenseId") String licenseId) {
        return String.format("This is the Delete");
    }
}
