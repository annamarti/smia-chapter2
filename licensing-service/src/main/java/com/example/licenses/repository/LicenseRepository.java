package com.example.licenses.repository;

import com.example.licenses.model.License;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LicenseRepository extends CrudRepository<License, Integer> {

    List<License> findByOrganizationId(String organizationId);

    License findByOrganizationIdAndLicenseId(String organizationId, String licenseId);
}
