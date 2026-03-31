package com.example.NebulaByte.Email_Writer.Service;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;

import com.example.NebulaByte.Email_Writer.Dto.EmailRequest;
import com.example.NebulaByte.Email_Writer.Prompts.EmailPromptTemplate;

@Service
public class EmailService {

    private final ChatClient chatClient;

    public EmailService(ChatClient chatClient) {
        this.chatClient = chatClient;
    }

    public String generateEmail(EmailRequest emailRequest) {

        String prompt = EmailPromptTemplate.buildPrompt(emailRequest);
        return chatClient
                .prompt()
                .user(prompt)
                .call()
                .content();
    }

}
