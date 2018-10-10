package com.autohomes.switchcontrol.controller;

import com.autohomes.switchcontrol.gpiocontrol.RelayModule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@Controller
public class RequestController {

  @Autowired private RelayModule relayModule;

  private static final Logger LOGGER = LoggerFactory.getLogger(RequestController.class);

  @ResponseBody
  @RequestMapping("/")
  public String welcome(Map<String, Object> model) {
    model.put("message", "message");
    return "welcome";
  }

  @ResponseBody
  @PostMapping(value = "/PrintHelloWorld")
  public void printHelloWorld(HttpServletResponse httpServletResponse) {
    LOGGER.info("HelloWorld!");
  }

  @ResponseBody
  @GetMapping(value = "/on21")
  public void turnOnRelay1(HttpServletResponse httpServletResponse) {
    relayModule.turnOn21();
  }

  @ResponseBody
  @GetMapping(value = "/off21")
  public void turnOffRelay1(HttpServletResponse httpServletResponse) {
    relayModule.turnOn21();
  }
}
