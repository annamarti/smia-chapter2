package com.example.organizationservice.repository;

import com.example.organizationservice.model.Organization;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrganizationRepository extends CrudRepository<Organization, Integer> {

    List<Organization> get(String id);
}
