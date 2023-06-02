import java.io.IOException;
import java.util.Scanner;

public class Flight {
    public static final int FLIGHT_SIZE=10;
    public static final int RECORD_SIZE=(FLIGHT_SIZE*2*5)+8;
    private String flightId;
    private String origin;
    private String destination;
    private String date;
    private String time;
    private int price;
    private int seats;

    public void flightAdd(Scanner scanner, FlightFile flightFile) throws IOException {
        System.out.print("FlightId : ");
        flightId = inputCorrectStringSize(scanner);
        while (flightFile.findExistFlightId(flightId))
        {
            System.out.println("This flightId already EXIST");
            System.out.println("Try again");
            System.out.print(">>");
            flightId = inputCorrectStringSize(scanner);
        }
        System.out.print("Origin : ");
        origin=inputCorrectStringSize(scanner);
        System.out.print("Destination : ");
        destination=inputCorrectStringSize(scanner);
        System.out.print("Date : ");
        date=inputCorrectStringSize(scanner);
        System.out.print("Time : ");
        time=inputCorrectStringSize(scanner);
        System.out.print("Price : ");
        price = scanner.nextInt();
        System.out.print("Seats : ");
        seats = scanner.nextInt();
        flightFile.addFlight(flightId,origin,destination,date,time,price,seats);
    }
    public String inputCorrectStringSize(Scanner scanner)
    {
        String temp;
        temp= scanner.next();
        while (temp.length()>FLIGHT_SIZE)
        {
            System.out.println("This is so LONG !");
            System.out.println("Try again");
            System.out.printf(">>");
            temp= scanner.next();
        }
        return temp;
    }
    public void removeFlight(Scanner scanner, FlightFile flightFile) throws IOException {
        System.out.print("FlightId : ");
        flightId = inputCorrectStringSize(scanner);
        while (!flightFile.findExistFlightId(flightId))
        {
            System.out.println("This flightId is INCORRECT!");
            System.out.println("Try again ");
            System.out.print("Enter flightId : ");
            flightId = inputCorrectStringSize(scanner);
        }
        flightFile.removeFlight(flightId);
        System.out.println("Remove is successfully :)");
    }
    public void printFlight(FlightFile flightFile) throws IOException {
        flightFile.printFlight();
    }
    public void updateFlight(Scanner scanner, FlightFile flightFile) throws IOException {
        System.out.print("FlightId : ");
        flightId = inputCorrectStringSize(scanner);
        while (!flightFile.findExistFlightId(flightId))
        {
            System.out.println("This flightId is INCORRECT!");
            System.out.println("Try again ");
            System.out.print("Enter flightId : ");
            flightId = inputCorrectStringSize(scanner);
        }
        switch (updateMenu(scanner)) {
            case 1 -> {
                System.out.print("New Origin : ");
                origin = inputCorrectStringSize(scanner);
                flightFile.updateOrigin( flightId, origin);
            }
            case 2 -> {
                System.out.print("New Destination : ");
                destination = inputCorrectStringSize(scanner);
                flightFile.updateDestination(flightId,destination);
            }
            case 3 -> {
                System.out.print("New Date : ");
                date = inputCorrectStringSize(scanner);
                flightFile.updateDate(flightId,date);
            }
            case 4 -> {
                System.out.print("New Time : ");
                time = inputCorrectStringSize(scanner);
                flightFile.updateTime(flightId,time);
            }
            case 5 -> {
                System.out.print("New Price : ");
                price = scanner.nextInt();
                flightFile.updatePrice(flightId,price);
            }
            case 6 -> {
                System.out.print("New Seats : ");
                seats = scanner.nextInt();
                flightFile.updateSeats(flightId,seats);
            }
        }
    }
    private int updateMenu(Scanner scanner)
    {
        int chose;
        System.out.println("Select number");
        System.out.println("< 1 > Origin");
        System.out.println("< 2 > Destination");
        System.out.println("< 3 > Date");
        System.out.println("< 4 > Time");
        System.out.println("< 5 > Price");
        System.out.println("< 6 > Seats");
        System.out.print(">>");
        chose = scanner.nextInt();
        while (chose > 6 || chose<1)
        {
            System.out.println("Try again!");
            System.out.print(">>");
            chose = scanner.nextInt();
        }
        return chose;
    }

    public void filterFlight(Scanner scanner,FlightFile flightFile) throws IOException {
        System.out.println("Filter by :");
        switch (searchFlight(scanner)) {
            case 1 -> {
                System.out.printf("Enter FlightId : ");
                flightId = inputCorrectStringSize(scanner);
                if (!flightFile.findExistFlightId(flightId))
                {
                    System.out.println("We dont have flight with this flightId :(");
                }
                else
                {
                    flightFile.flightIdFilter(flightId);

                }
            }
            case 2 -> {
                System.out.printf("Enter Origin : ");
                origin = inputCorrectStringSize(scanner);
                flightFile.originFilter(origin);
            }
            case 3 -> {
                System.out.printf("Enter Destination : ");
                destination = inputCorrectStringSize(scanner);
                flightFile.destinationFilter(destination);
            }
            case 4 -> {
                System.out.printf("Enter Date : ");
                date = inputCorrectStringSize(scanner);
                flightFile.dateFilter(date);
            }
            case 5 -> {
                System.out.printf("Enter Time : ");
                time = inputCorrectStringSize(scanner);
                flightFile.timeFilter(time);
            }
            case 6 -> {
                int min;
                int max;
                System.out.printf("Enter min Price : ");
                min = scanner.nextInt();
                System.out.printf("Enter max Price : ");
                max = scanner.nextInt();
                flightFile.priceFilter(min,max);
            }
            case 7 -> {
                int min;
                int max;
                System.out.printf("Enter min Seats : ");
                min = scanner.nextInt();
                System.out.printf("Enter max Seats : ");
                max = scanner.nextInt();
                flightFile.seatsFilter(min,max);
            }
        }
    }
    private int searchFlight(Scanner input)
    {
        int chose ;
        System.out.println("< 1 > FlightId");
        System.out.println("< 2 > Origin");
        System.out.println("< 3 > Destination");
        System.out.println("< 4 > Date");
        System.out.println("< 5 > Time");
        System.out.println("< 6 > Price");
        System.out.println("< 7 > Seats");
        System.out.print(">>");
        chose = input.nextInt();
        while (chose >7 || chose < 1 )
        {
            System.out.println("Try again!" );
            System.out.print(">>");
            chose=input.nextInt();
        }
        return chose;
    }
}
