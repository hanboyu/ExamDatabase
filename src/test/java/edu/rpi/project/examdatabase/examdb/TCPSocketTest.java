package edu.rpi.project.examdatabase.examdb;

import edu.rpi.project.examdatabase.examdb.HelperFunctions.StringHelperFunctions;
import edu.rpi.project.examdatabase.examdb.Objects.TCPSocket;
import org.junit.jupiter.api.Test;

import java.io.IOException;

public class TCPSocketTest {

    @Test
    void CreatSocketTest() {
        TCPSocket test_socket = new TCPSocket("localhost", 6969);
        test_socket.close();
    }

    @Test
    void SendOnyTest() {
        TCPSocket test_socket = new TCPSocket("localhost", 6969);
        try {
            test_socket.sendOnly("Hi, this is the java server.");
        } catch (IOException e) {
            System.err.println(e);
        }

        test_socket.close();
    }

    @Test
    void SendTest() {
        TCPSocket test_socket = new TCPSocket("localhost", 6969);
        String res;
        try {
            res = test_socket.send("Hi, are you listening?");
            System.out.println("'" + res + "'");
            byte[] p = res.getBytes();
            byte[] z = "ACK\n".getBytes();
            assert (res.equals("ACK\n"));
        } catch (IOException e) {
            System.err.println(e);
        }

        test_socket.close();
    }
}
