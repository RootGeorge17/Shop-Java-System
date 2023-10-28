import java.awt.*;
import java.util.HashMap;
import java.util.HashSet;
import java.io.File;
import java.util.Random;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.text.DateFormat;
import java.util.ArrayList;
import java.io.FileWriter;
import java.io.IOException;
import java.util.TreeMap;
import java.util.LinkedHashMap;
import java.util.Date;
import java.util.Calendar;


public class Shop {
    /*
    private ArrayList<ShopItem> shopItemList;
    private ArrayList<Customer> customerList;
    
    private List<ShopItem> itemsList;
    private List<Customer> customerList;
    */
    
    private LinkedHashMap<String, Customer> customerMap;
    private LinkedHashMap<String, ShopItem> itemsMap;
    private LinkedHashMap<String, ShopItemReservation> itemReservationsMap;
    
    private ArrayList<String> definedCustomerIds;
    private int lastReservationNo = 0;
    
    private Diary reservationDiary;

    public Shop() {
        /*
        itemsList = new ArrayList<ShopItem>();
        customerList = new ArrayList<Customer>();
        */
    
        this.itemsMap = new LinkedHashMap<String, ShopItem>();
        this.customerMap = new LinkedHashMap<String, Customer>();
        this.itemReservationsMap = new LinkedHashMap<String, ShopItemReservation>();

        this.definedCustomerIds = new ArrayList<String>();
        
        this.reservationDiary = new Diary();
    }
    
    // Store Shop Items to HashMap
    public void storeItem(ShopItem item) 
    {
        //itemsList.add(item);
        
        itemsMap.put(item.getItemCode(), item);
    }
    
    // Retrieve Shop Item by itemCode
    public ShopItem getItem(String itemCode) 
    {
        return itemsMap.get(itemCode);
    }

    // Retrieve Customer by customerId
    public Customer getCustomer(String customerId) 
    {
        return customerMap.get(customerId);
    }

    // Store Customer Data
    public void storeCustomer(Customer customer) {
        // Check if customerID is defined, generate one otherwise
        if (customer.getCustomerID().equals("unknown")) {
            customer.generateUniqueCustomerID("AB-", 6, definedCustomerIds);
            definedCustomerIds.add(customer.getCustomerID());
        }
        //this.customerList.add(customer);
        this.customerMap.put(customer.getCustomerID(), customer);
    }

    // Retrieve Item Reservation by Unique Reservation Number
    public ShopItemReservation getItemReservation(String reservationNo) 
    {
        return itemReservationsMap.get(reservationNo);
    }
    
    // Store Item Reservation Data
    public void storeItemReservation(ShopItemReservation itemReservation) {
        reservationDiary.addReservation(itemReservation);
        itemReservationsMap.put(itemReservation.getReservationNo(), itemReservation);
    }

    // Print Shop Items
    public void printAllTools() {
        System.out.println("===== Shop Items (Tools) =====");
        
        for (HashMap.Entry<String, ShopItem> item : this.itemsMap.entrySet()) {
            item.getValue().printDetails();
            System.out.println("==============================");
        }

    }

    // Print Shop Customers
    public void printAllCustomers() {
        System.out.println("======= Shop Customers =======");

        for (HashMap.Entry<String, Customer> customer : this.customerMap.entrySet()) {
            customer.getValue().printDetails();
            System.out.println("==============================");
        }
    }

    // Print Item Reservations
    public void printItemReservations() {
        System.out.println("======= Shop Item Reservations =======");
        
        for (HashMap.Entry<String, ShopItemReservation> reservationEntry : this.itemReservationsMap.entrySet()) {
            reservationEntry.getValue().printDetails();
            System.out.println("======================================");
        }
    }
    
    public void printDiaryEntries(Date startDate, Date endDate)
    {
        reservationDiary.printEntries(startDate, endDate);
    }
    
    // Parse Map and write Customer Data to the file
    public void writeCustomerData() {
        for (HashMap.Entry<String, Customer> customer : this.customerMap.entrySet()) {
            customer.getValue().writeData();
        }
    }

    // Parse Map and write Item Reservation to the file
    public void writeItemReservationData() {
        for (HashMap.Entry<String, ShopItemReservation> itemReservation : this.itemReservationsMap.entrySet()) {
            itemReservation.getValue().writeData();
        }
    }

    private String generateReservationNo()
    {
        int reservationNo = 0;
        try {
            File myObj = new File("data/item_reservation_no.txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                reservationNo = Integer.parseInt(myReader.nextLine());
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred reading the reservation number from the file.");
            e.printStackTrace();
        }
        
        reservationNo++;
        
        try {
            FileWriter myWriter = new FileWriter("data/item_reservation_no.txt");
            myWriter.write(reservationNo);
            myWriter.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred writing the reservation number from the file.");
            e.printStackTrace();
        }
        return String.format("%06d", reservationNo);
    }

   public void readToolData() {
        // create a FileDialog to select the input file
        FileDialog dialog = new FileDialog((Frame) null, "Select Items Data File to Open");
        dialog.setMode(FileDialog.LOAD);
        dialog.setVisible(true);

        String fileName = dialog.getFile();

            if (fileName != null) {

            File file = new File(dialog.getDirectory(), fileName);
            String typeOfData = "";

            try {
                Scanner fileScanner = new Scanner(file);

                // read each line of the file and print it to the console
                while (fileScanner.hasNextLine()) {

                    String lineOfText = (fileScanner.nextLine()).trim();

                    if (lineOfText.startsWith("//") || lineOfText.isEmpty() || lineOfText.equals("")) {
                        continue; // ignore comments and empty lines
                    }

                    if (lineOfText.startsWith("[")) {
                        typeOfData = lineOfText; // set type of data based on flag
                    } else {
                        if (typeOfData.equalsIgnoreCase("[ElectricTool data]")) {
                            Scanner lineScanner = new Scanner(lineOfText);
                            ElectricalTool electricTool = new ElectricalTool();
                            electricTool.readData(lineScanner);
                            storeItem(electricTool);
                            lineScanner.close(); // close scanner for this line
                        } else if (typeOfData.equals("[HandTool data]")) {
                            Scanner lineScanner = new Scanner(lineOfText);
                            HandTool handtool = new HandTool();
                            handtool.readData(lineScanner);
                            storeItem(handtool);
                            lineScanner.close(); // close scanner for this line
                        } else if (typeOfData.equals("[Perishable data]")) {
                            Scanner lineScanner = new Scanner(lineOfText);
                            Perishable perishable = new Perishable();
                            perishable.readData(lineScanner);
                            storeItem(perishable);
                            lineScanner.close(); // close scanner for this line
                        } else if (typeOfData.equals("[Workwear data]")) {
                            Scanner lineScanner = new Scanner(lineOfText);
                            Workwear workwear = new Workwear();
                            workwear.readData(lineScanner);
                            storeItem(workwear);
                            lineScanner.close(); // close scanner for this line
                        }
                    }
                }

                fileScanner.close(); // close scanner for file
            } catch (FileNotFoundException e) {
                System.out.println("File not found: " + e.getMessage());
            }
        } 

        printAllTools(); // print all tools for debugging purposes
    }
    
    // Read Shop Customers from File and loads them
    public void readCustomerData() {
        Frame myFrame = null;
        FileDialog fileBox = new FileDialog(myFrame, "Open", FileDialog.LOAD);
        fileBox.setVisible(true);
        File dataFile = new File(fileBox.getFile());
        System.out.println("You have selected the file: " + fileBox.getFile());
        
        try {
            Scanner toolScanner = new Scanner(dataFile);
            
            while (toolScanner.hasNext()) {
                String lineOfText = toolScanner.nextLine().trim();
                Scanner secondToolScanner = new Scanner(lineOfText).useDelimiter("\\s*,\\s*");
                if (lineOfText.length() == 0 || lineOfText.startsWith("//")) {
                    continue;
                }  
                else {
                    Customer customer = new Customer();
                    customer.readData(secondToolScanner);
                    storeCustomer(customer);
                }
                secondToolScanner.close();
            }
        toolScanner.close();
        System.out.println("File was successfully read!");
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + e.getMessage());
        }
    }

    // Read Shop Item Reservation from File and loads them
    public void readItemReservationData() {
        Frame myFrame = null;
        FileDialog fileBox = new FileDialog(myFrame, "Open", FileDialog.LOAD);
        fileBox.setVisible(true);
        File dataFile = new File(fileBox.getFile());
        System.out.println("You have selected the file: " + fileBox.getFile());
        try {
            Scanner toolScanner = new Scanner(dataFile);
            String typeOfData = "";
            while (toolScanner.hasNext()) {
                String lineOfText = toolScanner.nextLine().trim();
                Scanner secondToolScanner = new Scanner(lineOfText).useDelimiter("\\s*,\\s*");
                if (lineOfText.length() == 0 || lineOfText.startsWith("//")) {
                    continue;
                } else if (lineOfText.startsWith("[")) // this is a flag
                {
                    typeOfData = lineOfText;
                } else {
                    ShopItemReservation itemReservation = new ShopItemReservation();
                    itemReservation.readData(secondToolScanner);
                    storeItemReservation(itemReservation);
                }
                secondToolScanner.close();
            }
        toolScanner.close();
        System.out.println("File was successfully read!");
        } catch (FileNotFoundException e) 
        {
            System.out.println("File not found: " + e.getMessage());
        }
    }

    public boolean makeItemReservation(String customerID, String itemID, String startDate, int noOfDays) throws ParseException 
    {
        if (customerID == null) 
        {
            System.out.println("The Customer ID is invalid.");
            return false;
        }
        if (!itemsMap.containsKey(itemID) || itemID == null) {
            System.out.println("The Item ID is invalid or there is no Shop Items with that itemID.");
            return false;
        }
        if (!DateUtil.isValidDateString(startDate)) 
        {
            System.out.println("The Date inserted is invalid.");
            return false;
        }
        if (noOfDays <= 0)
        {
            System.out.println("Reservation duration (in days) must be greater than zero.");
            return false;
        }
        
        
        
        ShopItemReservation[] reservations = new ShopItemReservation[noOfDays];
        ArrayList<Date> reserved = new ArrayList<Date>();
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        Date dateAfter = new Date();
        for (int i = 1; i <= noOfDays; i++)
        {
            Calendar cal = Calendar.getInstance();
            cal.add(Calendar.DAY_OF_MONTH, i);
            dateAfter = new SimpleDateFormat("dd-MM-yyyy").parse(sdf.format(cal.getTime()));
            if (reservationDiary.getReservations(dateAfter) != null)
            {
                reservations = reservationDiary.getReservations(dateAfter);
                for(ShopItemReservation res : reservations)
                {
                    ShopItem item = itemsMap.get(res.getItemID());
                    if (item.getItemCode() == itemID) {
                        System.out.println("Shop Item " + item.getItemName() + " is already reserved for " + dateAfter);
                        return false;
                    }
                }
            }       
        }
        
        
        String reservationNo = generateReservationNo();
        ShopItemReservation reservation = new ShopItemReservation(reservationNo, customerID, itemID, startDate, noOfDays);
        storeItemReservation(reservation);
        reservationDiary.addReservation(reservation);
        return true;
    }
    
    public void deleteReservation(String reservationNo) {
        ShopItemReservation reservation = itemReservationsMap.get(reservationNo);
        itemReservationsMap.remove(reservationNo);
        reservationDiary.deleteReservation(reservation);
        System.out.println("Reservation No. " + reservation.getReservationNo() + " has been deleted");
    }

}