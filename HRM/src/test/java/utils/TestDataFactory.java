package utils;

import models.Employee;

import java.util.UUID;

public final class TestDataFactory {

    private TestDataFactory() {
    }

    public static Employee createRandomEmployee() {
        String letters = UUID.randomUUID()
                .toString()
                .replaceAll("[^A-Za-z]", "")
                .substring(0, 6);

        String digits = String.valueOf(System.currentTimeMillis()).substring(6);

        String firstName = "John" + letters.substring(0, 2);
        String middleName = "QA";
        String lastName = "Test" + letters.substring(2, 6);
        String updatedLastName = "Edit" + letters.substring(0, 4);
        String employeeId = digits;

        return new Employee(firstName, middleName, lastName, updatedLastName, employeeId);
    }
}