import java.util.Scanner;

public class HandTool extends Tool {
    protected boolean sharpenable;

    @Override
    public void readData(Scanner scanner) {
        super.readData(scanner);
        this.sharpenable = Boolean.parseBoolean(scanner.next());
    }

    @Override
    public void printDetails() {
        super.printDetails();
        System.out.println("Sharpenable: " + this.sharpenable);
    }
}