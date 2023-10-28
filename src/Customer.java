import java.io.PrintWriter;
import java.util.Scanner;
import java.util.Random;
import java.util.List;
import java.util.ArrayList;
import java.io.FileWriter;
import java.io.IOException;

public class Customer {
    private String customerID;
    private String surname;
    private String firstName;
    private String otherInitials;
    private String title;
    
    private Random randomGenerator;

    public Customer() {
        
    }

    public Customer(String surname, String firstName, String otherInitials, String title) {
        this.customerID = "unknown";
        this.surname = surname;
        this.firstName = firstName;
        this.otherInitials = otherInitials;
        this.title = title;
        
    }


    public String getCustomerID() {
        return customerID;
    }

    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }

    public void printDetails() {
        System.out.println("First Name: " + firstName);
        System.out.println("Surname: " + surname);
        System.out.println("Other initials: " + otherInitials);
        System.out.println("Title: " + title);
        System.out.println("Customer ID: " + customerID);
    }


    public void readData(Scanner scanner) {
        this.customerID = scanner.next();
        this.surname = scanner.next();
        this.firstName = scanner.next();
        this.otherInitials = scanner.next();
        this.title = scanner.next();
    }

    public void writeData() {
        try {
            FileWriter myWriter = new FileWriter("new_customer_data.txt");
            myWriter.write("Customer ID: " + this.customerID + "\n");
            myWriter.write("Surname: " + this.surname + "\n");
            myWriter.write("First name: " + this.firstName + "\n");
            myWriter.write("OtherInitials: " + this.otherInitials + "\n");
            myWriter.write("Gender: " + this.title);
            myWriter.write("\n");
            myWriter.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred writing the reservation number from the file.");
            e.printStackTrace();
        }
    }
    
    public void generateUniqueCustomerID(String prefix, int numDigits, ArrayList<String> definedCustomerIds) {
        int min = (int) Math.pow(10, numDigits - 1);
        int max = (int) Math.pow(10, numDigits) - 1;

        this.randomGenerator = new Random();
        String newCustomerId;
        int randomNum = randomGenerator.nextInt(max - min + 1) + min;
        newCustomerId = prefix + randomNum;
        while (true) {
            if (!definedCustomerIds.isEmpty() && definedCustomerIds.contains(newCustomerId)) {
                randomNum = randomGenerator.nextInt(max - min + 1) + min;
                newCustomerId = prefix + randomNum;
            } else
                break;
        }
        this.customerID = newCustomerId;
    }
}