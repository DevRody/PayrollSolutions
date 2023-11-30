package com.project.payrollSolutions.email;

import com.project.payrollSolutions.exceptionhandler.SendEmailException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class SendEmailService {
    private final JavaMailSender javaMailSender;

    @Autowired
    public SendEmailService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    public void sendEmail(String email, String subject, String text) {
        var message = new SimpleMailMessage();

        try {
            message.setTo(email);
            message.setSubject(subject);
            message.setText(text);
            javaMailSender.send(message);

        } catch (SendEmailException exception) {
            throw new SendEmailException(exception.getMessage());
        }
    }
}
