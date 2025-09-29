package org.example;

import lombok.AllArgsConstructor;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.util.stream.Collectors;

@AllArgsConstructor
public abstract class TransactionAnalyzer {
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    public static double calculateTotalBalance(List<Transaction> transactions){
        return transactions.stream()
                .mapToDouble(Transaction::getAmount)
                .sum();
    }

    public static int countTransactionsByMonth(List<Transaction> transactions, String monthYear) {
        return (int) transactions.stream()
                .map(transaction -> LocalDate.parse(transaction.getDate(), DATE_FORMATTER))
                .filter(d -> d.format(DateTimeFormatter.ofPattern("MM-yyyy")).equals(monthYear))
                .count();
    }

    public static List<Transaction> findTopExpenses(List<Transaction> transactions) {
        return transactions.stream()
                .filter(transaction -> transaction.getAmount() < 0)
                .sorted(Comparator.comparing(Transaction::getAmount))
                .limit(10)
                .collect(Collectors.toList());
    }

    public static List<Transaction> smallestAndBiggestExpensesByPeriod(
            List<Transaction> transactions,
            String startDate,
            String endDate
    ) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate start = LocalDate.parse(startDate, formatter);
        LocalDate end = LocalDate.parse(endDate, formatter);

        List<Transaction> expenses = transactions.stream()
                .filter(t -> t.getAmount() < 0)
                .filter(t -> {
                    LocalDate date = LocalDate.parse(t.getDate(), formatter);
                    return (date.isEqual(start) || date.isAfter(start)) &&
                            (date.isEqual(end) || date.isBefore(end));
                })
                .toList();

        if (expenses.isEmpty()) {
            return List.of();
        }

        Transaction min = expenses.stream()
                .min(Comparator.comparingDouble(Transaction::getAmount))
                .get();

        Transaction max = expenses.stream()
                .max(Comparator.comparingDouble(Transaction::getAmount))
                .get();

        if (min.equals(max)) {
            return List.of(min);
        }
        return List.of(min, max);
    }


    public static String generateExpensesReport(List<Transaction> transactions, double unitAmount) {
        if (transactions == null || transactions.isEmpty()) {
            return "No transactions.";
        }

        Map<String, Double> byCategory = new HashMap<>();
        Map<String, Double> byMonth = new HashMap<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        for (Transaction t : transactions) {
            if (t.getAmount() < 0) {
                double amount = Math.abs(t.getAmount());

                String category = t.getDescription() == null ? "Uncategorized" : t.getDescription();
                byCategory.put(category, byCategory.getOrDefault(category, 0.0) + amount);

                LocalDate date = LocalDate.parse(t.getDate(), formatter);
                String month = date.format(DateTimeFormatter.ofPattern("MM-yyyy"));
                byMonth.put(month, byMonth.getOrDefault(month, 0.0) + amount);
            }
        }

        StringBuilder sb = new StringBuilder();
        sb.append("Expenses report\n");
        sb.append("1 * = ").append(unitAmount).append(" грн\n\n");

        sb.append("By Categories:\n");
        for (Map.Entry<String, Double> entry : byCategory.entrySet()) {
            sb.append(entry.getKey()).append(" : ")
                    .append(entry.getValue()).append(" | ")
                    .append(repeatStars(entry.getValue(), unitAmount))
                    .append("\n");
        }

        sb.append("\nBy Months:\n");
        for (Map.Entry<String, Double> entry : byMonth.entrySet()) {
            sb.append(entry.getKey()).append(" : ")
                    .append(entry.getValue()).append(" | ")
                    .append(repeatStars(entry.getValue(), unitAmount))
                    .append("\n");
        }

        return sb.toString();
    }

    private static String repeatStars(double sum, double unitAmount) {
        int count = (int) (sum / unitAmount);
        if (count == 0 && sum > 0) count = 1;
        return "*".repeat(count);
    }
}


