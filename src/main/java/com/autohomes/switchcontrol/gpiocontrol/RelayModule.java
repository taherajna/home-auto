package com.autohomes.switchcontrol.gpiocontrol;

import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.RaspiPin;

public class RelayModule {

  private GpioPinDigitalOutput pin29;

  public void turnOn21() {
    if (pin29 == null) {
      pin29 = GpioFactory.getInstance().provisionDigitalOutputPin(RaspiPin.GPIO_29, PinState.LOW);
      pin29.setShutdownOptions(true, PinState.LOW);
    }
  }

  public void turnOff21() {
    pin29 = null;
    GpioFactory.getInstance().shutdown();
  }
}
