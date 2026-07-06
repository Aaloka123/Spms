package com.spms.exception;

//Exception thrown when a role cannot be deleted because users are assigned to it.
public class RoleInUseException extends RuntimeException {

    public RoleInUseException(Long roleId) {
        super("Role cannot be deleted because it is assigned to users. Role id: " + roleId);
    }

}
