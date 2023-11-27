package com.jyula.jyulaapi.application.adapters;

import com.jyula.jyulaapi.core.providers.MailSenderProvider;
import com.resend.Resend;
import com.resend.core.exception.ResendException;
import com.resend.services.emails.model.SendEmailRequest;
import com.resend.services.emails.model.SendEmailResponse;
import org.springframework.stereotype.Service;

@Service
public class ResendMailSenderAdapter implements MailSenderProvider {
    private final Resend resend;

    public ResendMailSenderAdapter() {
        this.resend = new Resend("");
    }

    @Override
    public SendMailResponse send(SendMailRequest request) throws SendMailException {
        SendEmailRequest resendRequest = SendEmailRequest.builder()
                .from(request.getFrom())
                .to(request.getTo())
                .subject(request.getSubject())
                .html(request.getContent())
                .build();

        try {
            SendEmailResponse response = resend.emails().send(resendRequest);
            return new SendMailResponse(response.getId());
        } catch (ResendException e) {
            throw new SendMailException(request, e);
        }
    }
}
