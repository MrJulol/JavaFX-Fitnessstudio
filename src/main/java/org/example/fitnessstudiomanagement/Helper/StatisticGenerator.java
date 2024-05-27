package org.example.fitnessstudiomanagement.Helper;

import org.example.fitnessstudiomanagement.Data.Database;
import org.example.fitnessstudiomanagement.Enums.MembershipType;
import org.example.fitnessstudiomanagement.Model.Account;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StatisticGenerator {
    public static List<Account> getExpiredMemberships(){
        return Database.getDatabase().getAccountsAsList().stream()
                .filter(account -> account.getMembership()!=null
                    &&account.getMembership().getExpirationDate().isBefore(LocalDate.now()))
                .collect(Collectors.toList());
    }
    public static Map<YearMonth, Long> getCustomerCountPerMonth() {
        return Database.getDatabase().getAccountsAsList().stream()
                .collect(Collectors.groupingBy(account -> YearMonth.from(account.getBirtDate()), Collectors.counting()));
    }
    public static Map<YearMonth, Long> getCheckinCountPerMonth() {
        return Database.getDatabase().getAccountsAsList().stream()
                .flatMap(account -> Stream.generate(() -> YearMonth.from(LocalDate.now())).limit(account.getCheckinNr()))
                .collect(Collectors.groupingBy(ym -> ym, Collectors.counting()));
    }

    public static Map<MembershipType, Long> getMembershipsByType() {
        return Database.getDatabase().getAccountsAsList().stream()
                .filter(account -> account.getMembership() != null)
                .collect(Collectors.groupingBy(account -> account.getMembership().getType(), Collectors.counting()));
    }
}
