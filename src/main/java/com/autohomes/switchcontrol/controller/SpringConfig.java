package com.autohomes.switchcontrol.controller;

import com.autohomes.switchcontrol.gpiocontrol.RelayModule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {

  @Bean
  public RelayModule relayModule() {
    return new RelayModule();
  }
}
