package com.example.licenses.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class License {
    @Id
    @Column
    private String licenseId;
    @Column
    private String organizationId;
    @Column
    private String productName;
    @Column
    private String licenseType;
    @Column
    private String comment;

    public String getLicenseId() {
        return licenseId;
    }

    public void setLicenseId(String id) {
        this.licenseId = id;
    }

    public String getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(String organizationId) {
        this.organizationId = organizationId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getLicenseType() {
        return licenseType;
    }

    public void setLicenseType(String licenseType) {
        this.licenseType = licenseType;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}