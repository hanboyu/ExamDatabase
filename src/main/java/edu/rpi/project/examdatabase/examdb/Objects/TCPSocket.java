package edu.rpi.project.examdatabase.examdb.Objects;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Arrays;

public class TCPSocket {
    private final String recv_addr;
    private final int recv_port;
    private final Socket socket;
    private final OutputStream toServer;
    private final InputStream fromServer;

    /**
     * Socket constructor
     *
     * @param addr address of the receiver
     * @param port port number of the receiver
     * @throws IOException when error occurs during socket creation.
     */
    public TCPSocket(String addr, int port) throws IOException {
        recv_addr = addr;
        recv_port = port;
        socket = new Socket(recv_addr, recv_port);
        fromServer = socket.getInputStream(); // Create an input stream to receive data from the server
        toServer = socket.getOutputStream(); // Create an output stream to send data to the server
    }

    /**
     * Send a string message to the socket, and wait for a response.
     * The response message must be less than 1024 bytes.
     *
     * @param data string message to send
     * @return the response message from the socket
     * @throws IOException when error occurs during send or receive data.
     */
    public String send(String data) throws IOException {
        byte[] data_bytes = data.getBytes();
        byte[] recv_buf = new byte[1024];
        String response;
        toServer.write(data_bytes);
        toServer.flush();

        int data_size = fromServer.read(recv_buf);
        response = new String(Arrays.copyOf(recv_buf, data_size));
        return response;
    }

    /**
     * Send a bytes array to the socket, and wait for a response.
     * The response message must be less than 1024 bytes.
     *
     * @param data string message to send
     * @return the response message from the socket
     * @throws IOException when error occurs during send or receive data.
     */
    public String send(byte[] data) throws IOException {
        byte[] recv_buf = new byte[1024];
        String response;
        toServer.write(data);
        toServer.flush();

        int data_size = fromServer.read(recv_buf);
        response = new String(Arrays.copyOf(recv_buf, data_size));
        return response;
    }

    /**
     * Send a string message to the socket.
     *
     * @param data string message to send
     * @throws IOException when error occurs during send
     */
    public void sendOnly(String data) throws IOException {
        byte[] data_bytes = data.getBytes();
        toServer.write(data_bytes);
        toServer.flush();
    }

    /**
     * close the socket.
     */
    public void close() {
        try {
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Socket info getters
     */

    public String getRecv_addr() {
        return recv_addr;
    }

    public int getRecv_port() {
        return recv_port;
    }
}
