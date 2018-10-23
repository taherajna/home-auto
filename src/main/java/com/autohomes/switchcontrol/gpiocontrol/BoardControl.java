package com.autohomes.switchcontrol.gpiocontrol;

public interface BoardControl {
  void controlSwitch(BoardPin boardPin, int switchState);
}
