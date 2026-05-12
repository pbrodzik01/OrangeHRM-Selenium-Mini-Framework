package models;

public class Employee {

    private String firstName;
    private String middleName;
    private String lastName;
    private String updatedLastName;
    private String employeeId;

    public Employee(String firstName, String middleName, String lastName, String updatedLastName, String employeeId) {
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.updatedLastName = updatedLastName;
        this.employeeId = employeeId;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getUpdatedLastName() {
        return updatedLastName;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}