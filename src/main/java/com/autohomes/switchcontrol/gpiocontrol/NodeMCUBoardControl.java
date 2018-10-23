package com.autohomes.switchcontrol.gpiocontrol;

import java.io.PrintWriter;
import java.net.Socket;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NodeMCUBoardControl implements BoardControl {
  private static final Logger LOGGER = LoggerFactory.getLogger(NodeMCUBoardControl.class);

  Socket socket;
  PrintWriter printWriter;

  public NodeMCUBoardControl(String hostAddress, int portNumber) {
    try {
      socket = new Socket(hostAddress, portNumber);
      printWriter = new PrintWriter(socket.getOutputStream());
    } catch (Exception e) {
      LOGGER.info("Failed to initialize socket connection.", e);
    }
  }

  @Override
  public void controlSwitch(BoardPin boardPin, int switchState) {
    printWriter.println("pin=" + boardPin.getPinNumber() + ",state=" + switchState);
    printWriter.flush();
  }
}
