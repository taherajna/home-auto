package com.autohomes.switchcontrol.controller;

import com.autohomes.switchcontrol.gpiocontrol.BoardControl;
import com.autohomes.switchcontrol.gpiocontrol.ESPModule;
import com.autohomes.switchcontrol.gpiocontrol.RelayModule;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {

    @Value("${node1.host}")
    private String node1HostAddress;

    @Value("${node1.port}")
    private int node1Port;

    @Bean
    public BoardControl relayModule() {
        return new RelayModule();
    }

    @Bean
    BoardControl espModule() {
        return new ESPModule(node1HostAddress, node1Port);
    }

    @Bean
    public Map<Integer, BoardControl> nodes() {
        HashMap<Integer, BoardControl> nodes = new HashMap<>();
        nodes.put(1, relayModule());
        nodes.put(2, espModule());
        return nodes;
    }
}
