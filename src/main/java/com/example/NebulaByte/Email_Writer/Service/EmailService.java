package com.example.NebulaByte.Email_Writer.Service;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import com.example.NebulaByte.Email_Writer.Dto.EmailRequest;
import com.example.NebulaByte.Email_Writer.Prompts.EmailPromptTemplate;

@Service
public class EmailService {

    @Value("classpath:System-prompt.st")
    private Resource systemPromptResource;

    private final ChatClient chatClient;

    public EmailService(ChatClient chatClient) {
        this.chatClient = chatClient;
    }

    public String generateEmail(EmailRequest emailRequest) {

        String prompt = EmailPromptTemplate.buildPrompt(emailRequest);
        return chatClient
                .prompt()
                .system(system -> system.text(this.systemPromptResource))
                .user(prompt)
                .call()
                .content();
    }
}
