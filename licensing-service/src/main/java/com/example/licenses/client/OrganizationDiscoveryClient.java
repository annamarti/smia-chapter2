package com.example.licenses.client;

import com.example.licenses.model.Organization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Component
public class OrganizationDiscoveryClient {

    //    'DiscoveryClient' - the class that interacts with Ribbon.
    //  You aren’t taking advantage of Ribbon’s client side load-balancing—By calling the DiscoveryClient
    //  directly, you get back a list of services, but it becomes your responsibility
    //  to choose which service instances returned you’re going to invoke
    @Autowired
    private DiscoveryClient discoveryClient;

    public Organization getOrganization(String organizationId) {
        RestTemplate restTemplate = new RestTemplate();
        List<ServiceInstance> instances =
                discoveryClient.getInstances("organizationservice");
        if (instances.size() == 0) return null;
        String serviceUri = String.format("%s/v1/organizations/%s",
                instances.get(0).getUri().toString(),
                organizationId);
        ResponseEntity<Organization> restExchange =
                restTemplate.exchange(
                        serviceUri,
                        HttpMethod.GET,
                        null, Organization.class, organizationId);
        return restExchange.getBody();
    }
}