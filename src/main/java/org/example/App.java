package org.example;

import java.util.List;

public class App 
{
    public static void main( String[] args )
    {
        String filePath = "https://informer.com.ua/dut/java/pr2.csv";

        DataReader reader = new CSVDataReader();
        List<String> lines = reader.readData(filePath);

        TransactionProcessor processor = new TransactionProcessor();
        List<Transaction> transactions = processor.process(lines);

        for (Transaction transaction : transactions) {
            System.out.println(transaction);
        }

        double totalBalance = TransactionAnalyzer.calculateTotalBalance(transactions);
        TransactionReportGenerator.printBalanceReport(totalBalance);

        String monthYear = "01-2024";
        int transactionsCount = TransactionAnalyzer.countTransactionsByMonth(transactions, monthYear);
        TransactionReportGenerator.printTransactionsCountByMonth(monthYear, transactionsCount);

        List<Transaction> topExpenses = TransactionAnalyzer.findTopExpenses(transactions);
        TransactionReportGenerator.printTopExpensesReport(topExpenses);

        List<Transaction> result = TransactionAnalyzer.smallestAndBiggestExpensesByPeriod(
                transactions,
                "01-01-2024",
                "31-01-2024"
        );

        for (Transaction t : result) {
            System.out.println(t);
        }

        List<Transaction> minAndMaxExpenses = TransactionAnalyzer.smallestAndBiggestExpensesByPeriod(
                transactions,
                "01-01-2024",
                "31-01-2024"
        );

        for (Transaction transaction : minAndMaxExpenses) {
            System.out.println(transaction);
        }

        double unitAmount = 1000.0;
        String report = TransactionAnalyzer.generateExpensesReport(transactions, unitAmount);
        System.out.println(report);
    }
}
