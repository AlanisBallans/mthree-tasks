import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.time.temporal.ChronoUnit;
import java.util.Scanner;
import java.time.LocalDate;

public class BirthDayCalculator {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("What's your birthday? (dd/MM/yyyy)");
        String stringBirthday = scanner.nextLine();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate birthday = LocalDate.parse(stringBirthday, formatter);
        System.out.println("That means you were born on a " + birthday.getDayOfWeek() + "!");

        LocalDate today = LocalDate.now();

        LocalDate birthdayThisYear = LocalDate.of(today.getYear(), birthday.getMonth(), birthday.getDayOfMonth());
        System.out.println("This year it falls on a " + birthdayThisYear.getDayOfWeek() + "...");

        LocalDate nextBirthday = birthdayThisYear;
        if (nextBirthday.isBefore(today)) {
            nextBirthday = birthdayThisYear.plusYears(1);
        }
        System.out.println("And since today is " + today.format(formatter) + ", there's only " + ChronoUnit.DAYS.between(today, nextBirthday) + " more days until the next one!");
        System.out.println("Bet yer excited to be turning " + birthday.until(nextBirthday).getYears() + "!");


    }
}
