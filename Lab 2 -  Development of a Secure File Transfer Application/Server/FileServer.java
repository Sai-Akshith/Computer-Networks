import java.io.*;
import java.net.*;

public class FileServer {
    public static void main(String[] args) {
        try {
            // Start the server to listen on port 5000
            ServerSocket serverSocket = new ServerSocket(5000);
            System.out.println("Server is listening on port 5000");

            // Accept a connection from the client
            Socket socket = serverSocket.accept();
            System.out.println("Client connected");

            // Create input stream to receive the file
            InputStream inputStream = socket.getInputStream();
            FileOutputStream fileOutputStream = new FileOutputStream("received_file.txt");

            // Buffer for file transfer
            byte[] buffer = new byte[1024];
            int bytesRead;

            // Read and decrypt the file data
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                for (int i = 0; i < bytesRead; i++) {
                    byte cipherByte = buffer[i];
                    byte plainByte = (byte) ((cipherByte - 3 + 256) % 256); // Decrypt with a simple shift cipher
                    System.out.println("Cipher Text: " + cipherByte + " (" + (char) cipherByte + ") -> Plain Text: " + plainByte + " (" + (char) plainByte + ")");
                    buffer[i] = cipherByte;
                }
                fileOutputStream.write(buffer, 0, bytesRead);
            }

            // Close all connections and streams
            fileOutputStream.close();
            inputStream.close();
            socket.close();
            serverSocket.close();

            System.out.println("File received and decrypted successfully");

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}