package com.tdd.grupo5.medallero.entities;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;
import lombok.Setter;

@Getter
public enum Role {
    Admin("admin", true),
    Athlete("athlete", false);

    @JsonValue
    private final String id;
    private final boolean isAdmin;

    Role(String id, boolean isAdmin) {
        this.id = id;
        this.isAdmin = isAdmin;
    }

    @JsonCreator
    public static Role createFromId(String id) {
        for (Role role : values()) {
            if (role.id.equals(id)) {
                return role;
            }
        }
        throw new IllegalArgumentException("No se pudo encontrar el rol con id " + id);
    }

}
