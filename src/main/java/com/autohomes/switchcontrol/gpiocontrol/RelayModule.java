package com.autohomes.switchcontrol.gpiocontrol;

import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.RaspiPin;

public class RelayModule {

  public void turnOn21() {
    GpioFactory.getInstance()
        .provisionDigitalOutputPin(RaspiPin.GPIO_21, PinState.LOW)
        .setShutdownOptions(true, PinState.LOW);
  }

  public void turnOff21() {
    GpioFactory.getInstance().shutdown();
  }
}
