package com.pfsystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.pfsystem.dto.NewSimOrderStatusDto;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
public class NotificationService {

    @Autowired
    private JavaMailSender sender;

    final String ACCOUNT_SID = "AC0360ce612c223bb2c6bbac67fabe37b2";
    final String AUTH_TOKEN = "7a39ab7bf0b5145cf3f3d8186d5cabec";

    public void sendEmail(String toEmail, String subject, String content) {
        MimeMessage message = sender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        try {
            helper.setTo(toEmail);
            helper.setSubject(subject);
            helper.setText(content, true);

            sender.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    public void sendNewSimEmail(NewSimOrderStatusDto newSimOrderStatusDto, String orderID) {
        String htmlContent = "<html><body>" +
                "<h1>Greetings from ProCharge!!!!</h1>" +
                "<h2>Your new sim is on the way.</h2>" +
                "<table border='1'>" +
                "<tr><td>order ID</td><td>" + orderID + "</td></tr>" +
                "<tr><td>Status</td><td>" + newSimOrderStatusDto.getStatus() + "</td></tr>" +
                "<tr><td>Name</td><td>" + newSimOrderStatusDto.getName() + "</td></tr>" +
                "<tr><td>Email</td><td>" + newSimOrderStatusDto.getEmail() + "</td></tr>" +
                "<tr><td>Address</td><td>" + newSimOrderStatusDto.getAddress() + "</td></tr>" +
                "<tr><td>Existing Number</td><td>" + newSimOrderStatusDto.getExistingNumber() + "</td></tr>" +
                "<tr><td>New SIM Number</td><td>" + newSimOrderStatusDto.getNewSimNumber() + "</td></tr>" +
                "<tr><td>Price</td><td>" + newSimOrderStatusDto.getPrice() + "</td></tr>" +
                "<tr><td>Ordered at </td><td>" + newSimOrderStatusDto.getOrderTime().toString() + "</td></tr>" +
                "<tr><td>Payment Method: </td><td>" + newSimOrderStatusDto.getPaidVia() + "</td></tr>" +
                "</table>" +
                "<p>Note: Your sim card will be delivered in 3 working days.</p>" +
                "<p>Thank you for choosing us, we are happy to serve you.</p>" +
                "<p>For any assistance, please contact us at <a href='mailto:procharge.foryou@gmail.com'>procharge.foryou@gmail.com</a>.</p>"
                +
                "</body></html>";
        sendEmail(newSimOrderStatusDto.getEmail(), "New SIM Order Status:" + newSimOrderStatusDto.getStatus(),
                htmlContent);
    }

    public void sendOTP() {

        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
        Message message = Message.creator(
                new com.twilio.type.PhoneNumber("+916281705722"),
                new com.twilio.type.PhoneNumber("+13346038286"),
                "Hi there")
                .create();

        System.out.println(message.getSid());
    }

    public void sendSMSNotification(String mobileNumber, String messageInfo) {
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
        Message message = Message.creator(
                new com.twilio.type.PhoneNumber("+91" + mobileNumber),
                new com.twilio.type.PhoneNumber("+13346038286"),
                messageInfo)
                .create();

        System.out.println(message.getSid());
    }

}