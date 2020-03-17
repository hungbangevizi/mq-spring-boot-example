package com.evizi.mqspringbootexample.controller;

import com.evizi.mqspringbootexample.dto.MessageBody;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.jms.JmsException;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/messages")
public class HelloController {

    private final Logger log = LoggerFactory.getLogger(HelloController.class);

    @Value("${queue.name}")
    private String queueName;

    private final ObjectMapper objectMapper;
    private final JmsTemplate jmsTemplate;

    public HelloController(ObjectMapper objectMapper, JmsTemplate jmsTemplate) {
        this.objectMapper = objectMapper;
        this.jmsTemplate = jmsTemplate;
    }

    /**
     * Request to send a message to the queue
     * @return
     */
    @PostMapping("/send")
    public ResponseEntity<Void> send(@RequestBody MessageBody messageBody){
        log.debug("POST request to send the message body to the queue {} ", messageBody);
        try{
            jmsTemplate.convertAndSend(queueName, messageBody);
        }catch (JmsException e){
            e.printStackTrace();
            log.error("FAIL");
        }

        return ResponseEntity.ok().build();
    }

    /**
     * Request to receive a message to the queue
     * @return
     */
    @GetMapping("/receive")
    public ResponseEntity<MessageBody> receive(){
        log.debug("GET request to receive the message body to the queue");
        MessageBody messageBody = null;
        try{
            Object body = jmsTemplate.receiveAndConvert(queueName);
            if(body instanceof MessageBody){
                messageBody = (MessageBody)body;
            }

        }catch (JmsException e){
            e.printStackTrace();
            log.error("FAIL");
        }

        return ResponseEntity.ok(messageBody);
    }
}
