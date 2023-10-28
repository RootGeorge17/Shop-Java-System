import java.util.Scanner;

public class ElectricTool extends Tool {
    protected boolean rechargeable;
    protected String power;

    @Override
    public void readData(Scanner scanner) {
        super.readData(scanner);
        this.rechargeable = scanner.nextBoolean();
        this.power = scanner.next();
    }

    @Override
    public void printDetails(){
        super.printDetails();
        System.out.println("Rechargeable: "+this.rechargeable);
        System.out.println("Power: "+this.power);
    }

}