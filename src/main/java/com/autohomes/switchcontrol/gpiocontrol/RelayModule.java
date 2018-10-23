package com.autohomes.switchcontrol.gpiocontrol;

import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.RaspiPin;
import java.util.HashMap;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RelayModule implements BoardControl {
  private static final Logger LOGGER = LoggerFactory.getLogger(RelayModule.class);

  private Map<Integer, GpioPinDigitalOutput> gpioPins;

  public RelayModule() {
    gpioPins = new HashMap<>();
    try {
      GpioPinDigitalOutput pin29 =
          GpioFactory.getInstance().provisionDigitalOutputPin(RaspiPin.GPIO_29, PinState.HIGH);
      pin29.setShutdownOptions(true, PinState.LOW);
      gpioPins.put(29, pin29);
    } catch (UnsatisfiedLinkError e) {
      LOGGER.info("Failed to initialized board pins.", e);
    }
  }

  @Override
  public void controlSwitch(BoardPin boardPin, int switchState) {
    GpioPinDigitalOutput gpioPinDigitalOutput = gpioPins.get(boardPin.getPinNumber());
    if (switchState == 1) {
      gpioPinDigitalOutput.high();
    } else if (switchState == 0) {
      gpioPinDigitalOutput.low();
    }
  }
}
