import java.io.IOException;
import java.util.Scanner;

public class Admin {
    private int adminMenu(Scanner scanner)
    {
        int chose;
        System.out.println("1- Add");
        System.out.println("2- Update");
        System.out.println("3- Remove");
        System.out.println("4 - Flight schedules");
        System.out.println("0 - Sign out");
        System.out.print(">>");
        chose = scanner.nextInt();
        while (chose > 4 || chose<0)
        {
            System.out.println("Try again!");
            System.out.print(">>");
            chose = scanner.nextInt();
        }
        return chose;
    }
    public void adminAction(Scanner scanner , Flight flight , FlightFile flightFile) throws IOException {
        int exitFlag = 0;
        while (exitFlag == 0)
        {
            switch (adminMenu(scanner)) {
                case 1 -> flight.flightAdd(scanner,flightFile);
                case 2 -> flight.updateFlight(scanner , flightFile);
                case 3 -> flight.removeFlight(scanner, flightFile);
                case 4 -> flight.printFlight(flightFile);
                case 0 -> exitFlag = 1;
            }
        }

    }
}
