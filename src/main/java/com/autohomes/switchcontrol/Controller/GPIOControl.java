package com.autohomes.switchcontrol.Controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@Controller
public class GPIOControl {

  @ResponseBody
  @RequestMapping("/")
  public String welcome(Map<String, Object> model) {
    model.put("message","message");
    return "welcome";
  }

  @ResponseBody
  @RequestMapping(value = "/PrintHelloWorld", method = RequestMethod.POST)
  public void printHelloWorld(HttpServletResponse httpServletResponse) {
    System.out.println("HelloWorld!");
  }
}

