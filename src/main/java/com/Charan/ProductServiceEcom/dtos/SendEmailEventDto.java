package com.Charan.ProductServiceEcom.dtos;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SendEmailEventDto {

    private String to;
    private String subject;
    private String body;
    private String from;
}
