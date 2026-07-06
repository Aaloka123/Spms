package com.spms.constants;

// Stores all API endpoint paths.
public final class ApiPath {

    // Prevent object creation.
    private ApiPath() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated.");
    }

    // Base API path for Role module.
    public static final String ROLES = "/api/roles";

    // Base API path for User module.
    public static final String USERS = "/api/users";

    // Base API path for Product module.
    public static final String PRODUCTS = "/api/products";

}