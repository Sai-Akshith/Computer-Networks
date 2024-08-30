import java.io.*;
import java.net.*;

public class FileClient {
    public static void main(String[] args) {
        FileClient client = new FileClient();
        File file = new File("C:/Users/91939/Dropbox/Sem 5/CN Lab/Lab 2/Client/data_from_client.txt"); // Specify the file path here
        client.sendFile(file);
    }

    public void sendFile(File file) {
        try {
            Socket socket = new Socket("localhost", 5000);
            System.out.println("Connected to server");

            FileInputStream fileInputStream = new FileInputStream(file);
            OutputStream outputStream = socket.getOutputStream();

            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = fileInputStream.read(buffer)) != -1) {
                for (int i = 0; i < bytesRead; i++) {
                    byte plainByte = buffer[i];
                    byte cipherByte = (byte) ((plainByte + 3) % 256); // Simple encryption
                    System.out.println("Plain Text: " + plainByte + " (" + (char) plainByte + ") -> Cipher Text: " + cipherByte + " (" + (char) cipherByte + ")");
                    buffer[i] = cipherByte;
                }
                outputStream.write(buffer, 0, bytesRead);
            }

            fileInputStream.close();
            outputStream.close();
            socket.close();
            System.out.println("File encrypted and sent successfully");

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}