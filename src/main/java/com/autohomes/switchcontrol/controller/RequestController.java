package com.autohomes.switchcontrol.controller;

import com.autohomes.switchcontrol.gpiocontrol.BoardControl;
import com.autohomes.switchcontrol.gpiocontrol.BoardPin;
import com.autohomes.switchcontrol.gpiocontrol.RelayModule;
import java.util.Map;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RequestController {

  @Autowired private RelayModule relayModule;

  @Autowired private Map<Integer, BoardControl> nodes;

  @ResponseBody
  @RequestMapping("/")
  public String welcome() {
    return "Welcome!";
  }

  @ResponseBody
  @GetMapping(value = "/PrintHelloWorld")
  public String printHelloWorld(HttpServletResponse httpServletResponse) {
    return "Hello World!";
  }

  @ResponseBody
  @GetMapping(value = "/control")
  public String controlSwitch(
      @RequestParam(value = "node") int nodeId,
      @RequestParam(value = "pin") int pinNumber,
      @RequestParam(value = "state") int state) {
    String response;
    BoardControl boardControl = nodes.get(nodeId);
    if (boardControl != null) {
      boardControl.controlSwitch(new BoardPin(pinNumber), state);
      response = "Command executed!";
    } else {
      response = "Invalid node!";
    }
    return response;
  }
}
