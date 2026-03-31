package com.example.NebulaByte.Email_Writer.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class EmailRequest {

    private String emailContent;
    private String tone;
    private String language;

}
