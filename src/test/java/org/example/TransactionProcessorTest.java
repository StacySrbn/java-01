package org.example;

import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class TransactionProcessorTest {

    @Test
    void testProcessValidData() {
        TransactionProcessor processor = new TransactionProcessor();

        List<String> lines = List.of(
                "01-01-2024,1000.50,Salary",
                "05-01-2024,-200.00,Groceries"
        );

        List<Transaction> transactions = processor.process(lines);

        assertEquals(2, transactions.size());

        assertEquals("01-01-2024", transactions.get(0).getDate());
        assertEquals(1000.50, transactions.get(0).getAmount());
        assertEquals("Salary", transactions.get(0).getDescription());

        assertEquals("05-01-2024", transactions.get(1).getDate());
        assertEquals(-200.00, transactions.get(1).getAmount());
        assertEquals("Groceries", transactions.get(1).getDescription());
    }

    @Test
    void testProcessInvalidData() {
        TransactionProcessor processor = new TransactionProcessor();

        List<String> lines = List.of(
                "01-01-2024,1000.50",
                "bad-data"
        );

        List<Transaction> transactions = processor.process(lines);

        assertEquals(0, transactions.size());
    }
}
