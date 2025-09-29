package org.example;

import java.util.ArrayList;
import java.util.List;

public class TransactionProcessor {
    public List<Transaction> process(List<String> lines) {
        List<Transaction> transactions = new ArrayList<>();
        for (String line : lines) {
            String[] values = line.split(",");
            if (values.length >= 3) {
                transactions.add(new Transaction(values[0], Double.parseDouble(values[1]), values[2]));
            }
        }
        return transactions;
    }
}
