package ru.testfield.ansible.inventory;

import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class InitializerListener {

    @EventListener({ContextRefreshedEvent.class})
    public void contextRefreshedEvent() {

    }

}