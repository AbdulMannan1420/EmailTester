package nl.hu.fronttester.testAPP.Service;


import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender emailSender;

    public void sendHtmlEmail(String to, String subject) {
        String htmlBody = """       
                              
                <!--
                ## Introduction
                                
                This sample demonstrates how to build an AMP-powered email that contains a simple interactive survey.
                -->
                                
                <!-- -->
                <!doctype html>
                <html ⚡4email lang="en" data-css-strict>
                <head>
                  <meta charset="utf-8">
                  <script async src="https://cdn.ampproject.org/v0.js"></script>
                  <script async custom-element="amp-form" src="https://cdn.ampproject.org/v0/amp-form-0.1.js"></script>
                  <style amp4email-boilerplate>body{visibility:hidden}</style>
                  <style amp-custom>
                    .container {
                      max-width: 500px;
                      margin: auto;
                      font-family: sans-serif;
                      padding: 1em;
                      text-align: center;
                    }
                                
                    .block {
                      display: block;
                      width: 100%;
                    }
                                
                    .m1 {
                      margin: 1em 0;
                    }
                    .button{
                      background-color: lightblue;
                      border: none;
                      border-radius: 10px;
                      padding: 10px;
                    }
                    .button:hover{
                      color: white;
                    }
                                
                    label {
                      margin-bottom: 0.5em;
                    }
                  </style>
                </head>
                <body>
                  <div class="container">
                    <!--
                      The main content of the email, an image and a short description of the event.
                    -->
                    <div>
                      <amp-img class="m1" width="600" height="314" layout="responsive" src="https://amp.dev/static/img/sharing/default-600x314.png"></amp-img>
                      <p>It’s been a busy few days at the latest AMP conference. We hope you had a good time!</p>
                    </div>
                                
                    <hr>
                                
                    <!--
                      To make the survey, we use an `amp-form` with radio button input fields.
                                
                      The second step of the form, free text input, is hidden initially and gets displayed after the user selects a rating, as this triggers a `change` event.
                                
                      When the form is submitted, we display a short confirmation message to the user by using `<div submit-success>`.
                    -->
<form action="http://localhost:8080/api/v1/receive" method="post">
    <label for="fname">First name:</label><br>
    <input type="text" id="fname" name="fname"><br>
    <label for="lname">Last name:</label><br>
    <input type="text" id="lname" name="lname"><br>
    <label for="options">Options:</label>
    <select id="options" name="options">
        <option value="1">1</option>
        <option value="2">2</option>
        <option value="3">3</option>
    </select>
    <input type="submit" value="Submit">
</form>
                  </div>
                </body>
                </html>
                                
                """;
        MimeMessage message = emailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(htmlBody, true); // true indicates html

            emailSender.send(message);
        } catch (MessagingException e) {
            // Handle exception appropriately
            e.printStackTrace();
        }
    }
}