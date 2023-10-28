import java.io.PrintWriter;
import java.util.Date;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;

public class ShopItemReservation {
    private String reservationNo;
    private String itemID;
    private String customerID;
    private Date startDate;
    private int noOfDays;

    public ShopItemReservation() {
        
    }
    
    public ShopItemReservation(String reservationNo, String itemID, String customerID, String startDate, int noOfDays) {
        this.reservationNo = reservationNo;
        this.itemID = itemID;
        this.customerID = customerID;
        this.startDate = DateUtil.convertStringToDate(startDate);
        this.noOfDays = noOfDays;
    }

    public String getReservationNo() {
        return reservationNo;
    }

    public String getItemID() {
        return itemID;
    }

    public String getCustomerID() {
        return customerID;
    }

    public Date getStartDate() {
        return startDate;
    }

    public int getNoOfDays() {
        return noOfDays;
    }

    public void setReservationNo(String reservationNo) {
        this.reservationNo = reservationNo;
    }

    public void setItemID(String itemID) {
        this.itemID = itemID;
    }

    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public void setNoOfDays(int noOfDays) {
        this.noOfDays = noOfDays;
    }

    public void readData(Scanner scanner) {
        scanner.useDelimiter("\\s*,\\s*|\\s+(?=[,])");

        this.reservationNo = scanner.next();
        this.itemID = scanner.next();
        this.customerID = scanner.next();
        this.startDate = DateUtil.convertStringToDate(scanner.next());
        this.noOfDays = Integer.parseInt(scanner.next());
    }

    public void writeData() {
        try {
            FileWriter myWriter = new FileWriter("data/customer_data.txt");
            myWriter.write("Reservation No: " + this.reservationNo);
            myWriter.write("Item ID: " + this.itemID);
            myWriter.write("Customer ID: " + this.customerID);
            myWriter.write("Start Date: " + this.startDate);
            myWriter.write("No of Days: " + this.noOfDays);
            myWriter.write("\n");
            myWriter.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred writing the reservation number from the file.");
            e.printStackTrace();
        }
        
    }

    public void printDetails() {
        System.out.println("Reservation No: " + reservationNo);
        System.out.println("Item ID: " + itemID);
        System.out.println("Customer ID: " + customerID);
        System.out.println("Start Date: " + startDate);
        System.out.println("No of Days: " + noOfDays);
    }
}