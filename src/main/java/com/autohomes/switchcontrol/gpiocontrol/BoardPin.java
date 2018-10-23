package com.autohomes.switchcontrol.gpiocontrol;

public class BoardPin {
  int pinNumber;

  public BoardPin(int pinNumber) {
    this.pinNumber = pinNumber;
  }

  public int getPinNumber() {
    return pinNumber;
  }

  public void setPinNumber(int pinNumber) {
    this.pinNumber = pinNumber;
  }
}
