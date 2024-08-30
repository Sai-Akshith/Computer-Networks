import java.net.*;
import java.io.*;
import java.util.Scanner;

public class client1 {
    public static void main(String[] args) throws IOException {
        Socket s = new Socket("localhost", 4999);

        PrintWriter pr = new PrintWriter(s.getOutputStream(), true);
        InputStreamReader in = new InputStreamReader(s.getInputStream());
        BufferedReader bf = new BufferedReader(in);
        Scanner scanner = new Scanner(System.in);

        String userMessage;
        String serverMessage;

        while (true) {
            // Take input from the user
            System.out.print("Enter message to send to server: ");
            userMessage = scanner.nextLine();
            pr.println(userMessage);

            if (userMessage.equalsIgnoreCase("end")) {
                break;
            }

            // Read response from server
            serverMessage = bf.readLine();
            System.out.println("Server: " + serverMessage);
        }

        // Close resources
        scanner.close();
        bf.close();
        in.close();
        pr.close();
        s.close();
    }
}



/*PrintWriter pr = new PrintWriter(s.getOutputStream(), true);  ->   this line of code creates a PrintWriter object that allows the client to send text messages to the server through the socket's output stream, with automatic flushing enabled to ensure timely delivery of messages.*/

/*InputStreamReader in = new InputStreamReader(s.getInputStream());  ->  this line of code creates an InputStreamReader object that allows the client to read text messages sent from the server through the socket's input stream. This enables the client to receive and process responses from the server.*/

/*BufferedReader bf = new BufferedReader(in);  ->  this line of code creates a BufferedReader object that wraps the InputStreamReader. This allows the client to read text messages sent from the server more efficiently, enabling operations like reading entire lines of text using methods such as readLine(). The buffering mechanism improves performance by minimizing the number of read operations performed on the underlying input stream*/

/*Scanner scanner = new Scanner(System.in);  ->   this line of code creates a Scanner object that allows the client to read user input from the keyboard. The Scanner can be used to capture messages that the user types, which can then be sent to the server. It provides a convenient way to handle input in a console application.*/

/*String userMessage:  -> It will be used to capture what the user wants to send to the server.*/

/*String serverMessage: -> It will be used to capture the server's reply after the client sends a message.*/

/*nextLine(): -> This is a method of the Scanner class that reads an entire line of text input from the user.*/

/*equalsIgnoreCase("end"): -> This is a method of the String class in Java. It compares the userMessage string with the string "end" while ignoring case differences. */

