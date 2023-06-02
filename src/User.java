import java.io.IOException;
import java.util.Scanner;

public class User {
    private String username;
    private String password;
    private int money;
    public static final int USER_STRING_SIZE = 20;
    public static final int USER_SIZE=USER_STRING_SIZE*4+4;
    public int signMenu(Scanner scanner){
        int chose;
        System.out.println("< 1 > Sign in");
        System.out.println("< 2 > Sign up");
        System.out.print(">>");
        chose = scanner.nextInt();
        while (chose > 2 || chose <1)
        {
            System.out.println("Try again!");
            System.out.print(">>");
            chose = scanner.nextInt();
        }
        return chose;
    }
    public void signUpUser(Scanner scanner , UserFile userFile) throws IOException {
        System.out.print("Username : ");
        username = inputCorrectStringSize(scanner);
        while (userFile.checkExistUsername(username))
        {
            System.out.println("This username is already EXIST !");
            System.out.println("Try again");
            System.out.print(">>");
            username = inputCorrectStringSize(scanner);
        }
        System.out.print("Password : ");
        password = inputCorrectStringSize(scanner);
        money=0;
        userFile.writeUser(username,password,money);
        System.out.println("Sign up is Successfully :)");
    }
    private String inputCorrectStringSize(Scanner scanner)
    {
        String temp;
        temp= scanner.next();
        while (temp.length()>USER_STRING_SIZE)
        {
            System.out.println("This is so LONG !");
            System.out.println("Try again");
            System.out.printf(">>");
            temp= scanner.next();
        }
        return temp;

    }
    public void signInUser(Scanner scanner,UserFile userFile , Admin admin , Flight flight , FlightFile flightFile,Ticket ticket , TicketFile ticketFile) throws IOException {
        System.out.print("Username : ");
        username = inputCorrectStringSize(scanner);
        while (!userFile.checkExistUsername(username))
        {
            if (checkAdminUsername())
                break;
            System.out.println("Cant find this user!");
            System.out.println("Try again");
            System.out.print(">>");
            username = inputCorrectStringSize(scanner);
        }
        System.out.print("Password : ");
        password = scanner.next();
        if (checkAdminUsername())
        {

            while (!checkAdminPassword())
            {
                System.out.println("Wrong password!");
                System.out.println("Try again");
                System.out.print(">>");
                password = scanner.next();
            }
            System.out.println("Welcome admin :)");
            admin.adminAction(scanner,flight,flightFile);
        }
        else
        {
            while (!userFile.checkPassword(password))
            {
                System.out.println("Wrong password!");
                System.out.println("Try again");
                System.out.print(">>");
                password = scanner.next();
            }
            System.out.printf("Welcome %s\n",username);
            userAction(scanner , userFile,flight,flightFile,ticket,ticketFile);
        }
    }
    private boolean checkAdminUsername()
    {
        return username.equals("admin") || username.equals("Admin") || username.equals("ADMIN");
    }
    private boolean checkAdminPassword()
    {
        return password.equals("admin")||password.equals("Admin")||password.equals("ADMIN");
    }

    private void changeUserPassword(Scanner scanner , UserFile userFile) throws IOException {
        System.out.printf("Old password : %s\n", password);
        System.out.print("New password : ");
        password = scanner.next();
        userFile.changepassword(username,password);
        System.out.println("Change successfully");
    }
    private void userAction(Scanner scanner,UserFile userFile,Flight flight,FlightFile flightFile,Ticket ticket , TicketFile ticketFile) throws IOException {
        int exitFlag =0 ;
        while (exitFlag == 0)
        {
            switch (userMenu(scanner)) {
                case 1 -> changeUserPassword(scanner,userFile);
                case 2 -> flight.filterFlight(scanner,flightFile);
                case 3 -> ticket.bookingTicket(username , ticketFile,flight,scanner,flightFile,userFile);
                case 4 -> ticket.cancellationTicket(username,ticketFile,scanner,flightFile,userFile);
                case 5 -> ticket.printTicket(username,ticketFile);
                case 6 -> addCharge(scanner,userFile);
                case 0 -> exitFlag = 1;
            }
        }

    }

    private void addCharge(Scanner scanner,UserFile userFile) throws IOException {
        money=userFile.readmony(username);
        System.out.printf("You have %,d money\nEnter you want charge : ",money);
        money+=scanner.nextInt();
        userFile.changeMoney(username,money);
    }

    private int userMenu(Scanner input)
    {
        int chose;
        System.out.println("Select number");
        System.out.println("< 1 > Change password");
        System.out.println("< 2 > Search flight tickets");
        System.out.println("< 3 > Booking ticket");
        System.out.println("< 4 > Ticket cancellation");
        System.out.println("< 5 > Booked tickets");
        System.out.println("< 6 > Add charge");
        System.out.println("< 0 > Sign out");
        System.out.print(">>");
        chose = input.nextInt();
        while (chose >6 || chose < 0 )
        {
            System.out.println("Try again!" );
            System.out.print(">>");
            chose=input.nextInt();
        }
        return chose;
    }
}
