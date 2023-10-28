import java.util.Scanner;

public class Accessory extends ShopItem {
    protected boolean isRecyclable;

    @Override
    public void printDetails() {
        System.out.println("Is Recyclable: " + isRecyclable);
        super.printDetails();
    }

    @Override
    public void readData(Scanner scanner) {
        this.isRecyclable = Boolean.parseBoolean(scanner.next());
        super.readData(scanner);
    }

}