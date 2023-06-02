import java.io.IOException;
import java.io.RandomAccessFile;

public class UserFile {
    RandomAccessFile randomAccessFile;
    public UserFile() throws IOException {
        this.randomAccessFile = new RandomAccessFile("Users.dat","rw");
        randomAccessFile.close();
    }

    public boolean checkExistUsername(String username) throws IOException {
        this.randomAccessFile = new RandomAccessFile("Users.dat","rw");
        long line = randomAccessFile.length()/(User.USER_SIZE);
        for (long i = 0; i < line; i++) {
            if (readString(i*User.USER_SIZE).equals(username))
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
        for (int i = 0; i < User.USER_STRING_SIZE; i++) {
            temp+=randomAccessFile.readChar();
        }
        return temp.trim();
    }
    public void writeUser(String username, String password, int money) throws IOException {
        this.randomAccessFile = new RandomAccessFile("Users.dat","rw");
        randomAccessFile.seek(randomAccessFile.length());
        randomAccessFile.writeChars(fixSize(username));
        randomAccessFile.writeChars(fixSize(password));
        randomAccessFile.writeInt(money);
        randomAccessFile.close();
    }
    private String fixSize(String string) {
        while (string.length()<User.USER_STRING_SIZE)
            string+=" ";
        return string;
    }
    public boolean checkPassword(String password) throws IOException {
        randomAccessFile = new RandomAccessFile("Users.dat","rw");
        long line = randomAccessFile.length()/(User.USER_SIZE);
        for (long i = 0; i < line; i++) {
            if (password.equals(readString((i*User.USER_SIZE)+(User.USER_STRING_SIZE*2))))
            {
                randomAccessFile.close();
                return true;
            }
        }
        randomAccessFile.close();
        return false;
    }

    public void changepassword(String name, String password) throws IOException {
        randomAccessFile = new RandomAccessFile("Users.dat","rw");
        long line = randomAccessFile.length()/(User.USER_SIZE);
        for (long i = 0; i < line; i++) {
            if (name.equals(readString(i*User.USER_SIZE)))
            {
                randomAccessFile.seek((i*User.USER_SIZE)+(User.USER_STRING_SIZE*2));
                randomAccessFile.writeChars(fixSize(password));
            }
        }
        randomAccessFile.close();
    }

    public int readmony(String username) throws IOException {
        randomAccessFile = new RandomAccessFile("Users.dat","rw");
        int mony;
        long line = randomAccessFile.length()/(User.USER_SIZE);
        for (long i = 0; i < line; i++) {
            if (username.equals(readString(i*User.USER_SIZE)))
            {
                randomAccessFile.seek((i*User.USER_SIZE)+(User.USER_STRING_SIZE*4));
                mony=randomAccessFile.readInt();
                randomAccessFile.close();
                return mony;
            }
        }
        randomAccessFile.close();
        return -1;
    }

    public void changeMoney(String username, int money) throws IOException {
        randomAccessFile = new RandomAccessFile("Users.dat","rw");

        long line = randomAccessFile.length()/(User.USER_SIZE);
        for (long i = 0; i < line; i++) {
            if (username.equals(readString(i*User.USER_SIZE)))
            {
                randomAccessFile.seek((i*User.USER_SIZE)+(User.USER_STRING_SIZE*4));
                randomAccessFile.writeInt(money);
                randomAccessFile.close();
                break;
            }
        }
        randomAccessFile.close();
    }
}
