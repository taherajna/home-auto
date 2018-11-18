package com.autohomes.switchcontrol;

import java.io.PrintWriter;
import java.net.Socket;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SocketTest {

  private static final Logger LOGGER = LoggerFactory.getLogger(SocketTest.class);

  @Test
  public static void main(String args[]) throws Exception {
    Socket socket = new Socket("192.168.1.7", 4000);
    PrintWriter printWriter = new PrintWriter(socket.getOutputStream());
    printWriter.println("pin=0,state=off");
    printWriter.flush();
    Thread.sleep(5000);
    printWriter = new PrintWriter(socket.getOutputStream());
    printWriter.println("pin=0,state=on");
    printWriter.flush();

    socket.close();
  }
}
