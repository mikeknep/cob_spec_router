package com.mikeknep.basic_router.utils;

/**
 * Created by mrk on 6/2/14.
 */
public class Parser {
    String rootDirectory;
    String method;
    String requestedResource;
    String body;

    public Parser(String[] args) {
        this.rootDirectory = args[0];
        this.method = args[1];
        this.requestedResource = args[2];
        this.body = args[3];
    }

    public String getRootDirectory() {
        return this.rootDirectory;
    }

    public String getMethod() {
        return this.method;
    }

    public String getRequestedResource() {
        return this.requestedResource;
    }

    public String getBody() {
        return this.body;
    }
}
