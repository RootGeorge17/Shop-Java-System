import java.util.Scanner;

public class Workwear extends Accessory {
    protected String manufacturing;
    protected String colour;
    protected String size;

    @Override
    public void printDetails() {
        super.printDetails();
        System.out.println("Manufacturing: " + manufacturing);
        System.out.println("Colour: " + colour);
        System.out.println("Size: " + size);
    }

    @Override
    public void readData(Scanner scanner) {
        super.readData(scanner);
        this.manufacturing = scanner.next();
        this.colour = scanner.next();
        this.size = scanner.next();
    }

}