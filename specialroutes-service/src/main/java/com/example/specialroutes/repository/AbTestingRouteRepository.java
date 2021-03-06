package com.example.specialroutes.repository;

import com.example.specialroutes.model.AbTestingRoute;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AbTestingRouteRepository extends CrudRepository<AbTestingRoute, String> {
    public AbTestingRoute findByServiceName(String serviceName);
}