import java.io.*;
import java.net.*;

public class Client {
    private Socket socket = null;
    private BufferedReader input = null;
    private DataOutputStream out = null;
    private DataInputStream BACK_in = null;

    public Client(String address, int port) {
        try {
            socket = new Socket(address, port);
            System.out.println("Connected");

            input = new BufferedReader(new InputStreamReader(System.in));
            out = new DataOutputStream(socket.getOutputStream());
            BACK_in = new DataInputStream(new BufferedInputStream(socket.getInputStream()));

        } catch (UnknownHostException u) {
            System.out.println("Unknown Host: " + u.getMessage());
            return;
        } catch (IOException i) {
            System.out.println("IO Error: " + i.getMessage());
            return;
        }

        String line = "";
        String BACK_line = "";

        while (!line.equals("over") || !BACK_line.equals("over")) {
            try {
                System.out.print("Client: ");
                line = input.readLine();
                
                out.writeUTF(line);
                if (line.equals("over"))
                    break;

                BACK_line = BACK_in.readUTF();
                if (BACK_line.equals("over")) {
                    System.out.println("Closing Connection");
                    break;                
                }
                System.out.print("Server: ");
                System.out.println(BACK_line);
            } catch (IOException i) {
                System.out.println("IO Error: " + i.getMessage());
            }
        }

        try {
            input.close();
            out.close();
            socket.close();
        } catch (IOException i) {
            System.out.println("IO Error: " + i.getMessage());
        }
    }

    public static void main(String args[]) {
        Client client = new Client("127.0.0.1", 5000);
    }
}
