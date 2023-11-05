package com.azarenka.domain;

/**
 * Represents of .. .
 * <p>
 * Copyright (C) 2023 antazarenko@gmail.com
 * <p>
 * Date: 01/09/2023
 *
 * @author Anton Azarenka
 */
public class ResourcePassword extends AbstractEntity {

    private String resource;
    private String password;

    /**
     * Default constructor.
     */
    public ResourcePassword() {
    }

    /**
     * Constructor for copy object.
     *
     * @param resourcePassword
     */
    public ResourcePassword(ResourcePassword resourcePassword) {
        this.resource = resourcePassword.getResource();
        this.password = resourcePassword.getPassword();
    }

    public String getResource() {
        return resource;
    }

    public void setResource(String resource) {
        this.resource = resource;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
