import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        String stringHeight;
        String stringWidth;

        float height;
        float width;

        float area;
        float perimeter;

        float cost;

        Scanner scanner = new Scanner(System.in);

        System.out.println("Please enter the height of your window: ");
        stringHeight = scanner.nextLine();
        System.out.println("Please enter the width of your window: ");
        stringWidth = scanner.nextLine();

        height = Float.parseFloat(stringHeight);
        width = Float.parseFloat(stringWidth);

        area = height * width;
        perimeter = 2 * (height + width);
        cost = (float) ((3.5 * area) + (2.25 * perimeter));

        System.out.println("The total cost for this window is $" + cost);
    }
}