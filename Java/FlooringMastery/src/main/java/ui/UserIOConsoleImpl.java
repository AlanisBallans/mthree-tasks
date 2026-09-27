package ui;

import java.math.BigDecimal;
import java.util.Scanner;

public class UserIOConsoleImpl implements UserIO {
    private final Scanner scanner = new Scanner(System.in);

    @Override
    public void print(String message) {
        System.out.println(message);
    }

    @Override
    public String readString(String prompt) {
        System.out.println(prompt);
        return scanner.nextLine();
    }

    @Override
    public int readInt(String prompt) {
        String stringInput = readString(prompt);
        int input = -1;
        try {
            input = Integer.parseInt(stringInput);
        } catch (NumberFormatException e) {
            System.err.println("Invalid integer entered");
        }
        return input;
    }

    @Override
    public int readInt(String prompt, int min, int max) {
        int input;

        do {
            input = readInt(prompt);
        } while (input < min || input > max);

        return input;
    }

    @Override
    public double readDouble(String prompt) {
        String stringInput = readString(prompt);
        double input = -1;
        try {
            input = Double.parseDouble(stringInput);
        } catch (NumberFormatException e) {
            System.err.println("Invalid double entered");
        }
        return input;
    }

    @Override
    public double readDouble(String prompt, double min, double max) {
        double input;

        do {
            input = readDouble(prompt);
        } while (input < min || input > max);

        return input;
    }

    @Override
    public float readFloat(String prompt) {
        String stringInput = readString(prompt);
        float input = -1;
        try {
            input = Float.parseFloat(stringInput);
        } catch (NumberFormatException e) {
            System.err.println("Invalid float entered");
        }
        return input;
    }

    @Override
    public float readFloat(String prompt, float min, float max) {
        float input;

        do {
            input = readFloat(prompt);
        } while (input < min || input > max);

        return input;
    }

    @Override
    public long readLong(String prompt) {
        String stringInput = readString(prompt);
        long input = -1;
        try {
            input = Long.parseLong(stringInput);
        } catch (NumberFormatException e) {
            System.err.println("Invalid long entered");
        }
        return input;
    }

    @Override
    public long readLong(String prompt, long min, long max) {
        long input;

        do {
            input = readLong(prompt);
        } while (input < min || input > max);

        return input;
    }

    public BigDecimal readBigDecimal(String prompt) {
        String stringInput = readString(prompt);
        BigDecimal input = null;
        try {
            input = new BigDecimal(stringInput);
        } catch (NumberFormatException e) {
            System.err.println("Invalid decimal entered");
        }
        return input;
    }

    public BigDecimal readBigDecimal(String prompt, BigDecimal min, BigDecimal max) {
        BigDecimal input;

        do {
            input = readBigDecimal(prompt);
        } while (input.compareTo(min) < 0 || input.compareTo(max) > 0);

        return input;
    }
}
