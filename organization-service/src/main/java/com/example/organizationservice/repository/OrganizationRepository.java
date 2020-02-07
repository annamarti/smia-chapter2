package com.example.organizationservice.repository;

import com.example.organizationservice.model.Organization;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrganizationRepository extends CrudRepository<Organization, Integer> {

    Organization findById(String id);

    void update(Organization org);
}
