import java.io.IOException;
import java.io.RandomAccessFile;

public class FlightFile {
    RandomAccessFile randomAccessFile;

    public FlightFile() throws IOException {
        this.randomAccessFile = new RandomAccessFile("Flight.dat","rw");
        randomAccessFile.close();
    }

    private String fixSize(String string) {
        while (string.length()<Flight.FLIGHT_SIZE)
            string+=" ";
        return string;
    }
    public boolean findExistFlightId(String flightId) throws IOException {
        randomAccessFile = new RandomAccessFile("Flight.dat","rw");
        long line = randomAccessFile.length()/Flight.RECORD_SIZE;
        for (long i = 0; i < line; i++) {
            if (flightId.equals(readString(i*Flight.RECORD_SIZE)))
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
        for (int i = 0; i < Flight.FLIGHT_SIZE; i++) {
            temp+=randomAccessFile.readChar();
        }
        return temp.trim();
    }

    public void addFlight(String flightId, String origin, String destination, String date, String time, int price, int seats) throws IOException {

        randomAccessFile = new RandomAccessFile("Flight.dat","rw");
        if (findNullLine())
        {
            randomAccessFile.seek(findNullLineIndex());
            randomAccessFile.writeChars(fixSize(flightId));
            randomAccessFile.writeChars(fixSize(origin));
            randomAccessFile.writeChars(fixSize(destination));
            randomAccessFile.writeChars(fixSize(date));
            randomAccessFile.writeChars(fixSize(time));
            randomAccessFile.writeInt(price);
            randomAccessFile.writeInt(seats);

        }
        else
        {
            randomAccessFile.seek(randomAccessFile.length());
            randomAccessFile.writeChars(fixSize(flightId));
            randomAccessFile.writeChars(fixSize(origin));
            randomAccessFile.writeChars(fixSize(destination));
            randomAccessFile.writeChars(fixSize(date));
            randomAccessFile.writeChars(fixSize(time));
            randomAccessFile.writeInt(price);
            randomAccessFile.writeInt(seats);
        }
        System.out.println("Add is Successfully :)");
        randomAccessFile.close();
    }
    private boolean findNullLine() throws IOException {
        long line = randomAccessFile.length()/Flight.RECORD_SIZE;
        for (long i = 0; i < line; i++) {
            if ("\\null".equals(readString(i*Flight.RECORD_SIZE)))
            {
                return true;
            }
        }
        return false;
    }
    private long findNullLineIndex() throws IOException {
        long line = randomAccessFile.length()/Flight.RECORD_SIZE;
        for (long i = 0; i < line; i++) {
            if ("\\null".equals(readString(i*Flight.RECORD_SIZE)))
            {
                return i*Flight.RECORD_SIZE;
            }
        }
        return -3;
    }
    public void removeFlight(String flightId) throws IOException {
        randomAccessFile = new RandomAccessFile("Flight.dat","rw");
        long line = randomAccessFile.length()/Flight.RECORD_SIZE;
        for (long i = 0; i < line; i++) {
            if (flightId.equals(readString(i*Flight.RECORD_SIZE)))
            {
                randomAccessFile.seek(i*Flight.RECORD_SIZE);
                randomAccessFile.writeChars(fixSize("\\null"));
            }
        }
        randomAccessFile.close();
    }
    public void printFlight() throws IOException {
        randomAccessFile = new RandomAccessFile("Flight.dat","rw");
        long line = randomAccessFile.length()/Flight.RECORD_SIZE;

        for (long i = 0; i < line; i++) {
            if (!readString(i*Flight.RECORD_SIZE).equals("\\null"))
            {
                System.out.printf("%-10s       | %-10s       | %-10s       | %-10s       | %-10s       | %-,10d       | %-3d\n", readString(i*Flight.RECORD_SIZE),  readString(i*Flight.RECORD_SIZE+(Flight.FLIGHT_SIZE*2)),  readString(i*Flight.RECORD_SIZE+(Flight.FLIGHT_SIZE*4)),  readString(i*Flight.RECORD_SIZE+(Flight.FLIGHT_SIZE*6)),  readString(i*Flight.RECORD_SIZE+(Flight.FLIGHT_SIZE*8)),randomAccessFile.readInt(), randomAccessFile.readInt());
            }
        }
        randomAccessFile.close();
    }
    public void updateOrigin(String flightId, String origin) throws IOException {
        randomAccessFile = new RandomAccessFile("Flight.dat","rw");
        long line = randomAccessFile.length()/Flight.RECORD_SIZE;
        for (long i = 0; i < line; i++) {
            if (flightId.equals(readString(i*Flight.RECORD_SIZE)))
            {
                randomAccessFile.seek(i*Flight.RECORD_SIZE+Flight.FLIGHT_SIZE*2);
                randomAccessFile.writeChars(fixSize(origin));
            }
        }
        randomAccessFile.close();
    }

    public void updateDestination(String flightId, String destination) throws IOException {
        randomAccessFile = new RandomAccessFile("Flight.dat","rw");
        long line = randomAccessFile.length()/Flight.RECORD_SIZE;
        for (long i = 0; i < line; i++) {
            if (flightId.equals(readString(i*Flight.RECORD_SIZE)))
            {
                randomAccessFile.seek(i*Flight.RECORD_SIZE+Flight.FLIGHT_SIZE*4);
                randomAccessFile.writeChars(fixSize(destination));
            }
        }
        randomAccessFile.close();
    }

    public void updateDate(String flightId, String date) throws IOException {
        randomAccessFile = new RandomAccessFile("Flight.dat","rw");
        long line = randomAccessFile.length()/Flight.RECORD_SIZE;
        for (long i = 0; i < line; i++) {
            if (flightId.equals(readString(i*Flight.RECORD_SIZE)))
            {
                randomAccessFile.seek(i*Flight.RECORD_SIZE+Flight.FLIGHT_SIZE*6);
                randomAccessFile.writeChars(fixSize(date));
            }
        }
        randomAccessFile.close();
    }

    public void updateTime(String flightId, String time) throws IOException {
        randomAccessFile = new RandomAccessFile("Flight.dat","rw");
        long line = randomAccessFile.length()/Flight.RECORD_SIZE;
        for (long i = 0; i < line; i++) {
            if (flightId.equals(readString(i*Flight.RECORD_SIZE)))
            {
                randomAccessFile.seek(i*Flight.RECORD_SIZE+Flight.FLIGHT_SIZE*8);
                randomAccessFile.writeChars(fixSize(time));
            }
        }
        randomAccessFile.close();
    }

    public void updatePrice(String flightId, int price) throws IOException {
        randomAccessFile = new RandomAccessFile("Flight.dat","rw");
        long line = randomAccessFile.length()/Flight.RECORD_SIZE;
        for (long i = 0; i < line; i++) {
            if (flightId.equals(readString(i*Flight.RECORD_SIZE)))
            {
                randomAccessFile.seek(i*Flight.RECORD_SIZE+Flight.FLIGHT_SIZE*10);
                randomAccessFile.writeInt(price);
            }
        }
        randomAccessFile.close();
    }

    public void updateSeats(String flightId, int seats) throws IOException {
        randomAccessFile = new RandomAccessFile("Flight.dat","rw");
        long line = randomAccessFile.length()/Flight.RECORD_SIZE;
        for (long i = 0; i < line; i++) {
            if (flightId.equals(readString(i*Flight.RECORD_SIZE)))
            {
                randomAccessFile.seek(i*Flight.RECORD_SIZE+Flight.FLIGHT_SIZE*10+4);
                randomAccessFile.writeInt(seats);
            }
        }
        randomAccessFile.close();
    }

    public void flightIdFilter(String flightId) throws IOException {
        randomAccessFile = new RandomAccessFile("Flight.dat","rw");
        long line = randomAccessFile.length()/Flight.RECORD_SIZE;

        for (long i = 0; i < line; i++) {
            if (readString(i*Flight.RECORD_SIZE).equals(flightId))
            {
                System.out.printf("%-10s       | %-10s       | %-10s       | %-10s       | %-10s       | %-,10d       | %-3d\n", readString(i*Flight.RECORD_SIZE),  readString(i*Flight.RECORD_SIZE+(Flight.FLIGHT_SIZE*2)),  readString(i*Flight.RECORD_SIZE+(Flight.FLIGHT_SIZE*4)),  readString(i*Flight.RECORD_SIZE+(Flight.FLIGHT_SIZE*6)),  readString(i*Flight.RECORD_SIZE+(Flight.FLIGHT_SIZE*8)),randomAccessFile.readInt(), randomAccessFile.readInt());
            }
        }
        randomAccessFile.close();
    }

    public void originFilter(String origin) throws IOException {
        randomAccessFile = new RandomAccessFile("Flight.dat","rw");
        long line = randomAccessFile.length()/Flight.RECORD_SIZE;

        for (long i = 0; i < line; i++) {
            if (readString(i*Flight.RECORD_SIZE+Flight.FLIGHT_SIZE*2).equals(origin))
            {
                System.out.printf("%-10s       | %-10s       | %-10s       | %-10s       | %-10s       | %-,10d       | %-3d\n", readString(i*Flight.RECORD_SIZE),  readString(i*Flight.RECORD_SIZE+(Flight.FLIGHT_SIZE*2)),  readString(i*Flight.RECORD_SIZE+(Flight.FLIGHT_SIZE*4)),  readString(i*Flight.RECORD_SIZE+(Flight.FLIGHT_SIZE*6)),  readString(i*Flight.RECORD_SIZE+(Flight.FLIGHT_SIZE*8)),randomAccessFile.readInt(), randomAccessFile.readInt());
            }
        }
        randomAccessFile.close();
    }

    public void destinationFilter(String destination) throws IOException {
        randomAccessFile = new RandomAccessFile("Flight.dat","rw");
        long line = randomAccessFile.length()/Flight.RECORD_SIZE;

        for (long i = 0; i < line; i++) {
            if (readString(i*Flight.RECORD_SIZE+Flight.FLIGHT_SIZE*4).equals(destination))
            {
                System.out.printf("%-10s       | %-10s       | %-10s       | %-10s       | %-10s       | %-,10d       | %-3d\n", readString(i*Flight.RECORD_SIZE),  readString(i*Flight.RECORD_SIZE+(Flight.FLIGHT_SIZE*2)),  readString(i*Flight.RECORD_SIZE+(Flight.FLIGHT_SIZE*4)),  readString(i*Flight.RECORD_SIZE+(Flight.FLIGHT_SIZE*6)),  readString(i*Flight.RECORD_SIZE+(Flight.FLIGHT_SIZE*8)),randomAccessFile.readInt(), randomAccessFile.readInt());
            }
        }
        randomAccessFile.close();
    }

    public void dateFilter(String date) throws IOException {
        randomAccessFile = new RandomAccessFile("Flight.dat","rw");
        long line = randomAccessFile.length()/Flight.RECORD_SIZE;

        for (long i = 0; i < line; i++) {
            if (readString(i*Flight.RECORD_SIZE+Flight.FLIGHT_SIZE*6).equals(date))
            {
                System.out.printf("%-10s       | %-10s       | %-10s       | %-10s       | %-10s       | %-,10d       | %-3d\n", readString(i*Flight.RECORD_SIZE),  readString(i*Flight.RECORD_SIZE+(Flight.FLIGHT_SIZE*2)),  readString(i*Flight.RECORD_SIZE+(Flight.FLIGHT_SIZE*4)),  readString(i*Flight.RECORD_SIZE+(Flight.FLIGHT_SIZE*6)),  readString(i*Flight.RECORD_SIZE+(Flight.FLIGHT_SIZE*8)),randomAccessFile.readInt(), randomAccessFile.readInt());
            }
        }
        randomAccessFile.close();
    }

    public void timeFilter(String time) throws IOException {
        randomAccessFile = new RandomAccessFile("Flight.dat","rw");
        long line = randomAccessFile.length()/Flight.RECORD_SIZE;

        for (long i = 0; i < line; i++) {
            if (readString(i*Flight.RECORD_SIZE+Flight.FLIGHT_SIZE*8).equals(time))
            {
                System.out.printf("%-10s       | %-10s       | %-10s       | %-10s       | %-10s       | %-,10d       | %-3d\n", readString(i*Flight.RECORD_SIZE),  readString(i*Flight.RECORD_SIZE+(Flight.FLIGHT_SIZE*2)),  readString(i*Flight.RECORD_SIZE+(Flight.FLIGHT_SIZE*4)),  readString(i*Flight.RECORD_SIZE+(Flight.FLIGHT_SIZE*6)),  readString(i*Flight.RECORD_SIZE+(Flight.FLIGHT_SIZE*8)),randomAccessFile.readInt(), randomAccessFile.readInt());
            }
        }
        randomAccessFile.close();
    }

    public void priceFilter(int min, int max) throws IOException {
        int price;
        randomAccessFile = new RandomAccessFile("Flight.dat","rw");
        long line = randomAccessFile.length()/Flight.RECORD_SIZE;

        for (long i = 0; i < line; i++) {
            randomAccessFile.seek(i*Flight.RECORD_SIZE+Flight.FLIGHT_SIZE*10);
            price=randomAccessFile.readInt();
            if (price<=max && price>=min)
            {
                System.out.printf("%-10s       | %-10s       | %-10s       | %-10s       | %-10s       | %-,10d       | %-3d\n", readString(i*Flight.RECORD_SIZE),  readString(i*Flight.RECORD_SIZE+(Flight.FLIGHT_SIZE*2)),  readString(i*Flight.RECORD_SIZE+(Flight.FLIGHT_SIZE*4)),  readString(i*Flight.RECORD_SIZE+(Flight.FLIGHT_SIZE*6)),  readString(i*Flight.RECORD_SIZE+(Flight.FLIGHT_SIZE*8)),randomAccessFile.readInt(), randomAccessFile.readInt());
            }
        }
        randomAccessFile.close();
    }

    public void seatsFilter(int min, int max) throws IOException {
        int seats;
        randomAccessFile = new RandomAccessFile("Flight.dat","rw");
        long line = randomAccessFile.length()/Flight.RECORD_SIZE;

        for (long i = 0; i < line; i++) {
            randomAccessFile.seek(i*Flight.RECORD_SIZE+Flight.FLIGHT_SIZE*10+4);
            seats=randomAccessFile.readInt();
            if (seats<=max && seats>=min)
            {
                System.out.printf("%-10s       | %-10s       | %-10s       | %-10s       | %-10s       | %-,10d       | %-3d\n", readString(i*Flight.RECORD_SIZE),  readString(i*Flight.RECORD_SIZE+(Flight.FLIGHT_SIZE*2)),  readString(i*Flight.RECORD_SIZE+(Flight.FLIGHT_SIZE*4)),  readString(i*Flight.RECORD_SIZE+(Flight.FLIGHT_SIZE*6)),  readString(i*Flight.RECORD_SIZE+(Flight.FLIGHT_SIZE*8)),randomAccessFile.readInt(), randomAccessFile.readInt());
            }
        }
        randomAccessFile.close();
    }

    public int readSeats(String flightId) throws IOException {
        int seat;
        randomAccessFile = new RandomAccessFile("Flight.dat","rw");
        long line = randomAccessFile.length()/Flight.RECORD_SIZE;

        for (long i = 0; i < line; i++) {
            if (readString(i*Flight.RECORD_SIZE).equals(flightId))
            {
                randomAccessFile.seek(i*Flight.RECORD_SIZE+Flight.FLIGHT_SIZE*10+4);
                seat = randomAccessFile.readInt();
                randomAccessFile.close();
                return seat;
            }
        }
        randomAccessFile.close();
        return -3;
    }

    public int readPrice(String flightId) throws IOException {
        int price;
        randomAccessFile = new RandomAccessFile("Flight.dat","rw");
        long line = randomAccessFile.length()/Flight.RECORD_SIZE;

        for (long i = 0; i < line; i++) {
            if (readString(i*Flight.RECORD_SIZE).equals(flightId))
            {
                randomAccessFile.seek(i*Flight.RECORD_SIZE+Flight.FLIGHT_SIZE*10);
                price = randomAccessFile.readInt();
                randomAccessFile.close();
                return price;
            }
        }
        randomAccessFile.close();
        return -3;
    }

    public void changeSeats(String flightId, int seat) throws IOException {
        randomAccessFile = new RandomAccessFile("Flight.dat","rw");
        long line = randomAccessFile.length()/Flight.RECORD_SIZE;

        for (long i = 0; i < line; i++) {
            if (readString(i*Flight.RECORD_SIZE).equals(flightId))
            {
                randomAccessFile.seek(i*Flight.RECORD_SIZE+Flight.FLIGHT_SIZE*10+4);
                randomAccessFile.writeInt(seat);
                randomAccessFile.close();
                break;
            }
        }
        randomAccessFile.close();
    }
}
