package com.autohomes.switchcontrol.gpiocontrol;

import java.io.PrintWriter;
import java.net.Socket;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ESPModule implements BoardControl {
    private static final Logger LOGGER = LoggerFactory.getLogger(ESPModule.class);

    Socket socket;
    PrintWriter printWriter;

    public ESPModule(String hostAddress, int portNumber) {
        try {
            socket = new Socket(hostAddress, portNumber);
            printWriter = new PrintWriter(socket.getOutputStream());
        } catch (Exception e) {
            LOGGER.info("Failed to initialize socket connection.", e);
        }
    }

    @Override
    public void controlSwitch(BoardPin boardPin, int switchState) {
        printWriter.println("switch=" + boardPin.getPinNumber() + ",state=" + resolveSwitchState(switchState));
        printWriter.flush();
    }

    private String resolveSwitchState(int switchState) {
        return switchState == 1 ? "on" : "off";
    }
}
