package org.example.fitnessstudiomanagement.Model;

import org.example.fitnessstudiomanagement.Data.Database;
import org.example.fitnessstudiomanagement.Enums.MembershipType;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.Objects;

public class Account {
    private final String name;
    private final String address;
    private final String telNumber;
    private final LocalDate birtDate;
    private final String pass;

    private Membership membership;
    private int checkinNr = 0;

    public Account(String name, String address, String telNumber, LocalDate birtDate, String pass, MembershipType type, String expirationData) {
        this.name = name;
        this.address = address;
        this.telNumber = telNumber;
        this.birtDate = birtDate;
        this.pass = pass;

        if(expirationData == null) {
            switch(type){
                case MONTHLY -> this.membership = new Membership(ZonedDateTime.now().plusMonths(1).toLocalDate(), type);
                case QUARTERLY -> this.membership = new Membership(ZonedDateTime.now().plusMonths(3).toLocalDate(), type);
                case YEARLY -> this.membership = new Membership(ZonedDateTime.now().plusYears(1).toLocalDate(), type);
                default -> throw new IllegalStateException("Unexpected value: " + type);
            }
        }else{
            this.membership = new Membership(LocalDate.parse(expirationData), type);
        }
    }


    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getTelNumber() {
        return telNumber;
    }

    public LocalDate getBirtDate() {
        return birtDate;
    }

    public String getPass() {
        return pass;
    }

    public Membership getMembership() {
        return membership;
    }

    public int getCheckinNr() {
        return checkinNr;
    }

    public void setCheckinNr(int checkinNr) {
        this.checkinNr = checkinNr;
    }

    public void changeMembership(MembershipType membershipType) {
        switch (membershipType) {
            case MONTHLY -> {
                this.membership = new Membership(ZonedDateTime.now().plusMonths(1).toLocalDate(), MembershipType.MONTHLY);
                try {
                    Database.getDatabase().updateMembership(this.getName(), MembershipType.MONTHLY);
                } catch (
                        SQLException e) {
                    throw new RuntimeException(e);
                }
            }
            case YEARLY -> {
                this.membership = new Membership(ZonedDateTime.now().plusYears(1).toLocalDate(), MembershipType.YEARLY);
                try {
                    Database.getDatabase().updateMembership(this.getName(), MembershipType.YEARLY);
                } catch (
                        SQLException e) {
                    throw new RuntimeException(e);
                }
            }
            case QUARTERLY -> {
                this.membership = new Membership(ZonedDateTime.now().plusMonths(3).toLocalDate(), MembershipType.QUARTERLY);
                try {
                    Database.getDatabase().updateMembership(this.getName(), MembershipType.QUARTERLY);
                } catch (
                        SQLException e) {
                    throw new RuntimeException(e);
                }
            }
            default -> throw new IllegalStateException("Unexpected value: " + membershipType);
        }
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return Objects.equals(name, account.name) && Objects.equals(address, account.address) && Objects.equals(telNumber, account.telNumber) && Objects.equals(birtDate, account.birtDate) && Objects.equals(pass, account.pass);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, address, telNumber, birtDate, pass);
    }

    @Override
    public String toString() {
        return "Account{" +
                name + '\'' +
                pass + '\'' +
                checkinNr +
                '}';
    }
}
