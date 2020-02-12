package com.example.licenses.service;

import com.example.licenses.client.OrganizationDiscoveryClient;
import com.example.licenses.client.OrganizationFeignClient;
import com.example.licenses.client.OrganizationRestTemplateClient;
import com.example.licenses.config.ServiceConfig;
import com.example.licenses.model.License;
import com.example.licenses.model.Organization;
import com.example.licenses.repository.LicenseRepository;
import com.example.licenses.util.UserContextHolder;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class LicenseService {

    @Autowired
    private LicenseRepository licenseRepository;

    @Autowired
    private ServiceConfig serviceConfig;

    @Autowired
    private OrganizationDiscoveryClient organizationDiscoveryClient;

    @Autowired
    private OrganizationRestTemplateClient organizationRestTemplateClient;

    @Autowired
    private OrganizationFeignClient organizationFeignClient;

    public License getLicense(String licenseId) {
        return licenseRepository.findById(licenseId).get();
    }

    public License getLicense(String organizationId, String licenseId) {
        return getLicense(organizationId, licenseId, null);
    }

    public License getLicense(String organizationId, String licenseId, String clientType) {
        License license = licenseRepository.findByOrganizationIdAndLicenseId(organizationId, licenseId);
        Organization org = getOrganization(organizationId, clientType);
        license.setOrganizationName(org.getName());
        license.setContactName(org.getContactName());
        license.setContactEmail(org.getContactEmail());
        license.setContactPhone(org.getContactPhone());
        license.setComment(serviceConfig.getTracerProperty());
        return license;
    }

    @HystrixCommand
    private Organization getOrganization(String organizationId, String clientType) {
        Organization organization;
        switch (clientType.toUpperCase()) {
            case "DISCOVERY":
                organization = organizationDiscoveryClient.getOrganization(organizationId);
                break;
            case "FEIGN":
                organization = organizationFeignClient.getOrganization(organizationId);
                break;
            default:
                organization = organizationRestTemplateClient.getOrganization(organizationId);
                break;
        }
        return organization;
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

    @HystrixCommand(
            fallbackMethod = "buildFallbackMethodList",
            threadPoolKey = "licensesByOrgThreadPool", // The threadPoolKey attribute defines the unique name of thread pool.
            threadPoolProperties = {
                    @HystrixProperty(name = "coreSize", value = "30"),  // The coreSize attribute lets you define the maximum number of threads in the thread pool.
                    @HystrixProperty(name = "maxQueueSize", value = "10") // The maxQueueSize lets you define a queue that sits in front of your thread pool and that can queue incoming requests

            },
            commandProperties = {
                    @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "10"),
                    @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "75"),
                    @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "7000"),
                    @HystrixProperty(name = "metrics.rollingStats.timeInMilliseconds", value = "15000"),
                    @HystrixProperty(name = "metrics.rollingStats.numBuckets", value = "5")
            }
    )
    public List<License> findLicenseByOrganizationId(String organizationId) {
        UserContextHolder
                .getContext()
                .getCorrelationId();
        randomlyRunLong();
        return licenseRepository.findByOrganizationId(organizationId);
    }

    private void randomlyRunLong() {
        Random rand = new Random();
        int randomNum = rand.nextInt((3 - 1) + 1) + 1;
        if (randomNum == 3) {
            sleep();
        }
    }

    private void sleep() {
        try {
            Thread.sleep(11000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public List<License> getLicensesByOrg(String organizationId) {
        randomlyRunLong();
        return licenseRepository.findByOrganizationId(organizationId);
    }

    private List<License> buildFallbackMethodList(String organizationId) {
        List<License> fallBackList = new ArrayList<>();
        License license = new License();
        license.setLicenseId("0000-0000-0000");
        license.setOrganizationId(organizationId);
        license.setProductName("Sorry no license information currently availabe");
        fallBackList.add(license);
        return fallBackList;
    }
}