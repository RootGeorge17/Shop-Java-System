import java.util.Scanner;

public class ShopItem {
    protected String itemName;
    protected String itemCode;
    protected int cost;

    public String getItemCode() {
        return itemCode;
    }
    
    public String getItemName() {
        return itemName;
    }

    public void printDetails() {
        System.out.println("Item Name: " + itemName);
        System.out.println("Item Code: " + itemCode);
        System.out.println("Cost: " + cost);
    }

    public void readData(Scanner scanner) {
        scanner.useDelimiter("\\s*,\\s*");

        this.itemName = scanner.next();
        this.itemCode = scanner.next();
        this.cost = Integer.parseInt(scanner.next());
    }
}