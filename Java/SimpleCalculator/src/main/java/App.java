import java.util.Scanner;

public class App {

    private final Scanner scanner = new Scanner(System.in);
    private final SimpleCalculator simpleCalculator = new SimpleCalculator();

    public static void main(String[] args) {
        App app = new App();

        boolean cont = true;

        while (cont) {
            cont = app.ui();
        }
    }

    private boolean ui() {
        System.out.println("Choose an operation: add (1), subtract (2), multiply (3), divide (4), or exit the program (5)");
        Integer operation = null;

        while (operation == null) {
            operation = validateInteger(1,5);
        }
        if (operation == 5) {
            System.out.println("Thank you!");
            return false;
        }

        // Choose operand 1
        Integer operand1 = null;
        System.out.println("What is your first operand?");
        while (operand1 == null) {
            operand1 = validateInteger(Integer.MIN_VALUE,Integer.MAX_VALUE);
        }

        // Choose operand 2
        Integer operand2 = null;
        System.out.println("What is your second operand?");
        while (operand2 == null) {
            operand2 = validateInteger(Integer.MIN_VALUE,Integer.MAX_VALUE);
        }

        calculate(operation, operand1, operand2);
        return true;
    }

    private Integer validateInteger(int min, int max) {
        Integer value = null;
        try {
            value = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.err.println("Invalid input. Please type a number from " + min + " to " + max + ".");
            return null;
        }

        if (value < min || value > max) {
            System.err.println("Invalid input. Please type a number from " + min + " to " + max + ".");
        }
        return value;
    }

    private void calculate(int operation, int operand1, int operand2) {
        System.out.print("The result is: ");
        switch (operation) {
            case 1:
                System.out.println(simpleCalculator.add(operand1, operand2));
                break;
            case 2:
                System.out.println(simpleCalculator.subtract(operand1, operand2));
                break;
            case 3:
                System.out.println(simpleCalculator.multiply(operand1, operand2));
                break;
            case 4:
                System.out.println(simpleCalculator.divide(operand1, operand2));
                break;
        }
    }


}
