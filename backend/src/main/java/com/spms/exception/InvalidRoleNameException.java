package com.spms.exception;

//Exception thrown when role name is not one of the allowed system roles.
public class InvalidRoleNameException extends RuntimeException {

    public InvalidRoleNameException(String roleName) {
        super("Invalid role name: " + roleName + ". Allowed roles are ADMIN, PHARMACIST, and USER.");
    }

}
