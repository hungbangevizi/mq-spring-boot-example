package com.evizi.mqspringbootexample.dto;


import lombok.Data;
import java.io.Serializable;

@Data
public class MessageBody implements Serializable {

    private String message;

}
