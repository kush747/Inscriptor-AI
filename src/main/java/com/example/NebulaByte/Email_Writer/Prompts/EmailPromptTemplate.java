package com.example.NebulaByte.Email_Writer.Prompts;

import com.example.NebulaByte.Email_Writer.Dto.EmailRequest;

public class EmailPromptTemplate {

    public static String buildPrompt(EmailRequest request) {

        return """
                Write a professional email reply.

                Original Email:
                %s

                Tone: %s
                Language: %s

                Keep the response clear and concise.
                """.formatted(
                request.getEmailContent(),
                request.getTone(),
                request.getLanguage());

    }

}
