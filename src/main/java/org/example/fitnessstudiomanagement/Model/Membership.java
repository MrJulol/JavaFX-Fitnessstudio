package org.example.fitnessstudiomanagement.Model;

import org.example.fitnessstudiomanagement.Enums.MembershipType;

import java.time.LocalDate;

public class Membership {
    private LocalDate expirationDate;
    private MembershipType type;

    public Membership(LocalDate expirationDate, MembershipType type) {
        this.expirationDate = expirationDate;
        this.type = type;
    }

    public LocalDate getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(LocalDate expirationDate) {
        this.expirationDate = expirationDate;
    }
    public String name() {
        return type.name();
    }
}
