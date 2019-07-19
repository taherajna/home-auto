package com.autohomes.switchcontrol;

import java.io.PrintWriter;
import java.net.Socket;
import org.junit.Ignore;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SocketTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(SocketTest.class);

    @Test
    @Ignore
    public static void main(String args[]) throws Exception {
        Socket socket = new Socket("192.168.1.100", 4000);
        PrintWriter printWriter = new PrintWriter(socket.getOutputStream());
        for (int i = 0; i < 1000; i++) {
            printWriter.write("Message: " + i + "\r\n");
            printWriter.flush();
            Thread.sleep(2000);
        }

        socket.close();
    }
}
