package com.spms.exception;

//Exception thrown when a role with the same name already exists.
public class RoleAlreadyExistsException extends RuntimeException {

    public RoleAlreadyExistsException(String roleName) {
        super("Role already exists with name: " + roleName);
    }

}
