package org.example.fitnessstudiomanagement.Data;

import javafx.stage.Stage;
import org.example.fitnessstudiomanagement.Enums.LanguageKey;
import org.example.fitnessstudiomanagement.Helper.Encrypt;
import org.example.fitnessstudiomanagement.Model.Account;

public class Data {

    public static LanguageKey language = LanguageKey.ENGLISH;

    public static boolean isDarkMode = false;

    public static Account loggedOnAccount;

    public static Stage stage;

    public class Admin{
        private static String username = Encrypt.sha256("admin");
        private static String password = Encrypt.sha256("123");

        public static String getUsername() {
            return username;
        }

        public static String getPassword() {
            return password;
        }
    }
}
