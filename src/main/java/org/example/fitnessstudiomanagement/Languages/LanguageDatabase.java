package org.example.fitnessstudiomanagement.Languages;

import org.example.fitnessstudiomanagement.Data.Data;
import org.example.fitnessstudiomanagement.Enums.LanguageKey;
import org.example.fitnessstudiomanagement.Enums.TranslationKey;

import java.util.HashMap;

public class LanguageDatabase {
    private HashMap<LanguageKey, HashMap<TranslationKey, String>> languages;
    private static LanguageDatabase instance;

    public static LanguageDatabase getInstance() {
        if (instance == null) {
            instance = new LanguageDatabase();
        }
        return instance;
    }

    private LanguageDatabase() {
        this.languages = new HashMap<>();
        german();
        italian();
        english();

    }

    private void italian() {
        HashMap<TranslationKey, String> italian = new HashMap<>();
        //LOGIN
        italian.put(TranslationKey.USERNAME, "Nome utente");
        italian.put(TranslationKey.PASSWORD, "Password");
        italian.put(TranslationKey.LOGIN, "Accedi");
        italian.put(TranslationKey.SETTINGS, "Impostazioni");

        //ADMIN
        italian.put(TranslationKey.REGISTER, "Registrazione");
        italian.put(TranslationKey.MEMBERSHIP, "Clienti");
        italian.put(TranslationKey.YOUR_CLIENTS, "I tuoi clienti");
        italian.put(TranslationKey.LOGOUT, "Disconnettersi");

        //CLIENT
        italian.put(TranslationKey.WELCOME, "Bentornato  ");
        italian.put(TranslationKey.LOGIN_NR, "Numero di accessi: ");
        italian.put(TranslationKey.EXP_DATE, "Data di scadenza: ");
        italian.put(TranslationKey.CHECKIN, "Entrare");
        //MEMBERSHIP
        italian.put(TranslationKey.DELETE, "Elimina");
        italian.put(TranslationKey.MONTHLY, "Mensile");
        italian.put(TranslationKey.YEARLY, "Annuale");
        italian.put(TranslationKey.QUARTERLY, "Trimestrale");
        italian.put(TranslationKey.BACK, "Indietro");

        //REGISTER
        italian.put(TranslationKey.REG_NAME, "Nome e Cognome");
        italian.put(TranslationKey.REG_PHONE, "Numero di telefono");
        italian.put(TranslationKey.REG_ADDRESS, "Indirizzo");
        italian.put(TranslationKey.REG_DATE, "Data di nascita");
        italian.put(TranslationKey.CANCEL, "Annulla");
        italian.put(TranslationKey.OK, "continua");

        this.languages.put(LanguageKey.ITALIAN, italian);
    }

    private void english() {
        HashMap<TranslationKey, String> english = new HashMap<>();
        //LOGIN
        english.put(TranslationKey.USERNAME, "Username");
        english.put(TranslationKey.PASSWORD, "Password");
        english.put(TranslationKey.LOGIN, "Login");
        english.put(TranslationKey.SETTINGS, "Settings");

        //ADMIN
        english.put(TranslationKey.REGISTER, "Register");
        english.put(TranslationKey.MEMBERSHIP, "Customers");
        english.put(TranslationKey.YOUR_CLIENTS, "Your Customers");
        english.put(TranslationKey.LOGOUT, "Logout");

        //CLIENT
        english.put(TranslationKey.WELCOME, "Welcome back  ");
        english.put(TranslationKey.LOGIN_NR, "Number of Check-ins: ");
        english.put(TranslationKey.EXP_DATE, "Expiration date: ");
        english.put(TranslationKey.CHECKIN, "Check-In");
        //MEMBERSHIP
        english.put(TranslationKey.DELETE, "Delete");
        english.put(TranslationKey.MONTHLY, "Monthly");
        english.put(TranslationKey.YEARLY, "Yearly");
        english.put(TranslationKey.QUARTERLY, "Quarterly");
        english.put(TranslationKey.BACK, "Back");

        //REGISTER
        english.put(TranslationKey.REG_NAME, "Full Name");
        english.put(TranslationKey.REG_PHONE, "Phone number");
        english.put(TranslationKey.REG_ADDRESS, "Address");
        english.put(TranslationKey.REG_DATE, "Date of Birth");
        english.put(TranslationKey.CANCEL, "Cancel");
        english.put(TranslationKey.OK, "Next");

        this.languages.put(LanguageKey.ENGLISH, english);
    }



    private void german() {
        HashMap<TranslationKey, String> german = new HashMap<>();
        //LOGIN
        german.put(TranslationKey.USERNAME, "Benutzername");
        german.put(TranslationKey.PASSWORD, "Passwort");
        german.put(TranslationKey.LOGIN, "Anmelden");
        german.put(TranslationKey.SETTINGS, "Einstellungen");

        //ADMIN
        german.put(TranslationKey.REGISTER, "Registrierung");
        german.put(TranslationKey.MEMBERSHIP, "Kunden");
        german.put(TranslationKey.YOUR_CLIENTS, "Ihre Kunden");
        german.put(TranslationKey.LOGOUT, "Abmelden");

        //CLIENT
        german.put(TranslationKey.WELCOME, "Willkommen zurück ");
        german.put(TranslationKey.LOGIN_NR, "Anzahl der Anmeldungen: ");
        german.put(TranslationKey.EXP_DATE, "Verfallsdatum: ");
        german.put(TranslationKey.CHECKIN, "Check-In");
        //MEMBERSHIP
        german.put(TranslationKey.DELETE, "Löschen");
        german.put(TranslationKey.MONTHLY, "Monatlich");
        german.put(TranslationKey.YEARLY, "Jährlich");
        german.put(TranslationKey.QUARTERLY, "3-Monate");
        german.put(TranslationKey.BACK, "Zurück");

        //REGISTER
        german.put(TranslationKey.REG_NAME, "Vor- und Nachname");
        german.put(TranslationKey.REG_PHONE, "Telefonnummer");
        german.put(TranslationKey.REG_ADDRESS, "Adresse");
        german.put(TranslationKey.REG_DATE, "Geburtsdatum");
        german.put(TranslationKey.CANCEL, "Abbrechen");
        german.put(TranslationKey.OK, "weiter");

        this.languages.put(LanguageKey.GERMAN, german);
    }

    public String get(TranslationKey key){
        return languages.get(Data.language).get(key);
    }

}
