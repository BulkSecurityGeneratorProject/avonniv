package com.avonniv.service.dto;

import com.avonniv.domain.Publisher;

import java.time.Instant;

public class PublisherDTO extends AbstractEntityDTO {
    private String name;

    private String descriptionEN;

    private String descriptionSWE;

    private String address;

    private String email;

    private String phone;

    private String url;

    private boolean crawled;

    public PublisherDTO() {
    }

    public PublisherDTO(Publisher publisher) {
        this(publisher.getId(), publisher.getCreatedDate(), publisher.getLastModifiedDate(), publisher.getStatus(),
            publisher.getName(), publisher.getDescriptionEN(), publisher.getDescriptionSWE(), publisher.getAddress(), publisher.getEmail(), publisher.getPhone(), publisher.getUrl(),
            publisher.isCrawled());
    }

    public PublisherDTO(Long id, Instant createdDate, Instant lastModifiedDate, int status,
                        String name, String descriptionEN, String descriptionSWE, String address, String email, String phone, String url) {
        this.id = id;
        this.createdDate = createdDate;
        this.lastModifiedDate = lastModifiedDate;
        this.status = status;
        this.name = name;
        this.descriptionEN = descriptionEN;
        this.descriptionSWE = descriptionSWE;
        this.address = address;
        this.email = email;
        this.phone = phone;
        this.url = url;
        this.crawled = false;
    }

    public PublisherDTO(Long id, Instant createdDate, Instant lastModifiedDate, int status,
                        String name, String descriptionEN, String descriptionSWE, String address, String email, String phone, String url,
                        boolean crawled) {
        this.id = id;
        this.createdDate = createdDate;
        this.lastModifiedDate = lastModifiedDate;
        this.status = status;
        this.name = name;
        this.descriptionEN = descriptionEN;
        this.descriptionSWE = descriptionSWE;
        this.address = address;
        this.email = email;
        this.phone = phone;
        this.url = url;
        this.crawled = crawled;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescriptionEN() {
        return descriptionEN;
    }

    public void setDescriptionEN(String descriptionEN) {
        this.descriptionEN = descriptionEN;
    }

    public String getDescriptionSWE() {
        return descriptionSWE;
    }

    public void setDescriptionSWE(String descriptionSWE) {
        this.descriptionSWE = descriptionSWE;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public boolean isCrawled() {
        return crawled;
    }

    public void setCrawled(boolean crawled) {
        this.crawled = crawled;
    }
}
