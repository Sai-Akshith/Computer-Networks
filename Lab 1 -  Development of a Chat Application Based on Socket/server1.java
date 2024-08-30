import java.net.*;
import java.io.*;
import java.util.Scanner;

public class server1 {
    public static void main(String[] args) throws IOException {
        ServerSocket ss = new ServerSocket(4999);
        Socket s = ss.accept();

        System.out.println("Client connected");

        InputStreamReader in = new InputStreamReader(s.getInputStream());
        BufferedReader bf = new BufferedReader(in);
        PrintWriter pr = new PrintWriter(s.getOutputStream(), true);

        Scanner scanner = new Scanner(System.in);
        String clientMessage;
        String serverResponse;

        while (true) {
            // Read message from client
            clientMessage = bf.readLine();
            if (clientMessage == null || clientMessage.equalsIgnoreCase("end")) {
                break;
            }
            System.out.println("Client: " + clientMessage);

            // Take input from the user and send response
            System.out.print("Enter response to client: ");
            serverResponse = scanner.nextLine();
            pr.println(serverResponse);

            if (serverResponse.equalsIgnoreCase("end")) {
                break;
            }
        }

        // Close resources
        scanner.close();
        bf.close();
        in.close();
        pr.close();
        s.close();
        ss.close();
    }
}
