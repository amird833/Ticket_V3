import java.io.IOException;
import java.io.RandomAccessFile;

public class TicketFile {
    RandomAccessFile randomAccessFile;
    public void bookTicket(String username, String ticketId, String flightId) throws IOException {
        randomAccessFile=new RandomAccessFile("Tickets.dat","rw");
        if (findNullLine())
        {
            randomAccessFile.seek(findNullLineIndex());
            randomAccessFile.writeChars(fixSize(username));
            randomAccessFile.writeChars(fixSize(ticketId));
            randomAccessFile.writeChars(fixSize(flightId));
        }
        else
        {
            randomAccessFile.seek(randomAccessFile.length());
            randomAccessFile.writeChars(fixSize(username));
            randomAccessFile.writeChars(fixSize(ticketId));
            randomAccessFile.writeChars(fixSize(flightId));
        }
        randomAccessFile.close();
    }
    private boolean findNullLine() throws IOException {
        long line = randomAccessFile.length()/Ticket.RECORD;
        for (long i = 0; i < line; i++) {
            if ("\\null".equals(readString(i*Ticket.RECORD+Ticket.SIZE*2)))
            {
                return true;
            }
        }
        return false;
    }
    private long findNullLineIndex() throws IOException {
        long line = randomAccessFile.length()/Ticket.RECORD;
        for (long i = 0; i < line; i++) {
            if ("\\null".equals(readString(i*Ticket.RECORD+Ticket.SIZE*2)))
            {
                return i*Ticket.RECORD;
            }
        }
        return -3;
    }
    private String fixSize(String string) {
        while (string.length()<Ticket.SIZE)
            string+=" ";
        return string;
    }
    public boolean findExistTicketId(String ticketId) throws IOException {
        randomAccessFile = new RandomAccessFile("Tickets.dat","rw");
        long line = randomAccessFile.length()/Ticket.RECORD;
        for (long i = 0; i < line; i++) {
            if (ticketId.equals(readString(i*Ticket.RECORD+Ticket.SIZE*2)))
            {
                randomAccessFile.close();
                return true;
            }
        }
        randomAccessFile.close();
        return false;
    }
    private String readString(long position) throws IOException {
        randomAccessFile.seek(position);
        String temp="";
        for (int i = 0; i < Ticket.SIZE; i++) {
            temp+=randomAccessFile.readChar();
        }
        return temp.trim();
    }

    public String findFlightId(String ticketId) throws IOException {
        randomAccessFile = new RandomAccessFile("Tickets.dat","rw");
        String flightId;
        long line = randomAccessFile.length()/Ticket.RECORD;
        for (long i = 0; i < line; i++) {
            if (ticketId.equals(readString(i*Ticket.RECORD+Ticket.SIZE*2)))
            {
                flightId=readString(i*Ticket.RECORD+Ticket.SIZE*4);
                randomAccessFile.close();
                return flightId;
            }
        }
        randomAccessFile.close();
        return null;
    }

    public void removeTicket(String ticketId) throws IOException {
        randomAccessFile = new RandomAccessFile("Tickets.dat","rw");
        long line = randomAccessFile.length()/Ticket.RECORD;
        for (long i = 0; i < line; i++) {
            if (ticketId.equals(readString(i*Ticket.RECORD+Ticket.SIZE*2)))
            {
                randomAccessFile.seek(i*Ticket.RECORD+Ticket.SIZE*2);
                randomAccessFile.writeChars(fixSize("\\null"));
                randomAccessFile.close();
            }
        }
        randomAccessFile.close();
    }

    public void print(String username) throws IOException {
        randomAccessFile = new RandomAccessFile("Tickets.dat","rw");
        long line = randomAccessFile.length()/Ticket.RECORD;
        for (long i = 0; i < line; i++) {
            if (username.equals(readString(i*Ticket.RECORD)) && !"\\null".equals(readString(i*Ticket.RECORD+Ticket.SIZE*2)))
            {
                System.out.printf("%-20s %-20s %-20s\n",readString(i*Ticket.RECORD),readString(i*Ticket.RECORD+Ticket.SIZE*2),readString(i*Ticket.RECORD+Ticket.SIZE*4));
            }
        }
        randomAccessFile.close();

    }
}
