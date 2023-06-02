import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

public class Ticket {
    public static final int SIZE=20;
    public static final int RECORD=SIZE*3*2;
    public void bookingTicket(String username, TicketFile ticketFile, Flight flight , Scanner scanner,FlightFile flightFile,UserFile userFile) throws IOException {
        String flightId;
        System.out.print("FlightId : ");
        flightId = flight.inputCorrectStringSize(scanner);
        while (!flightFile.findExistFlightId(flightId))
        {
            System.out.println("This flightId is INCORRECT");
            System.out.println("Try again");
            System.out.print(">>");
            flightId = flight.inputCorrectStringSize(scanner);
        }
        if (flightFile.readSeats(flightId)==0)
        {
            System.out.println("This flight dont have any seats");
        }
        else if (userFile.readmony(username) < flightFile.readPrice(flightId))
        {
            System.out.println("You dont have enugh many :(");
        }
        else
        {
            String ticketId;
            int money , seat;
            ticketId = randTicketId(ticketFile,new Random());
            money = userFile.readmony(username);
            seat=flightFile.readSeats(flightId);
            seat--;
            money-=flightFile.readPrice(flightId);
            userFile.changeMoney(username,money);
            flightFile.changeSeats(flightId,seat);
            ticketFile.bookTicket(username,ticketId,flightId);
            System.out.println("Booking is Successfully");
        }

    }
    private String randTicketId(TicketFile ticketFile , Random random) throws IOException {
        String randNum;
        randNum = Integer.toString(random.nextInt(100_000,1_000_000));
        while (ticketFile.findExistTicketId(randNum))
        {
            randNum = Integer.toString(random.nextInt(100_000,1_000_000));
        }
        return randNum;

    }

    public void cancellationTicket(String username, TicketFile ticketFile, Scanner scanner, FlightFile flightFile, UserFile userFile) throws IOException {
        System.out.print("TicketId : ");
        int seat;
        int money;
        String flightId;
        String ticketId;
        ticketId=scanner.next();
        while (!ticketFile.findExistTicketId(ticketId))
        {
            System.out.println("This ticketId is INCORRECT!");
            System.out.println("Try again ");
            System.out.print("Enter TicketId : ");
            ticketId = scanner.next();
        }
        flightId=ticketFile.findFlightId(ticketId);
        money=userFile.readmony(username);
        money+=flightFile.readPrice(flightId);
        seat=flightFile.readSeats(flightId);
        seat++;
        flightFile.changeSeats(flightId,seat);
        userFile.changeMoney(username,money);
        ticketFile.removeTicket(ticketId);
        System.out.println("Remove is successfully :)");
    }

    public void printTicket(String username, TicketFile ticketFile) throws IOException {
        ticketFile.print(username);
    }
}
