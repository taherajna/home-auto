package com.autohomes.switchcontrol.gpiocontrol;

import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.RaspiPin;

public class RelayModule {

  private GpioPinDigitalOutput pin29;

  public RelayModule() {
    pin29 = GpioFactory.getInstance().provisionDigitalOutputPin(RaspiPin.GPIO_29, PinState.HIGH);
    pin29.setShutdownOptions(true, PinState.LOW);
  }

  public void turnOn21() {
    pin29.low();
  }

  public void turnOff21() {
    pin29.high();
  }
}
