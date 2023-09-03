package com.jsrdev.model;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class CategoryId implements Serializable {
    private String name;
    private String password;

    public CategoryId() {}

    public CategoryId(String name, String password) {
        this.name = name;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CategoryId that)) return false;
        return Objects.equals(name, that.name) && Objects.equals(password, that.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, password);
    }
}
