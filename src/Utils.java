import java.io.IOException;
import java.util.Scanner;

public class Utils {

    User user = new User();
    Scanner scanner = new Scanner(System.in);
    UserFile userFile = new UserFile();
    Admin admin = new Admin();
    Flight flight = new Flight();
    FlightFile flightFile = new FlightFile();

    Ticket ticket = new Ticket();
    TicketFile ticketFile = new TicketFile();
    public Utils() throws IOException {
    }

    public void start() throws IOException {
        while (true)
        {
            switch (user.signMenu(scanner)) {
                case 1 -> user.signInUser(scanner,userFile,admin,flight,flightFile,ticket,ticketFile);
                case 2 -> user.signUpUser(scanner,userFile);
            }
        }
    }
}
