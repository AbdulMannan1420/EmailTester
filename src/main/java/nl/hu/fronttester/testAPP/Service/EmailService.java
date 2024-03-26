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
                              
                <!DOCTYPE html>
                <html lang="en">
                <head>
                    <meta charset="UTF-8">
                    <meta name="viewport" content="width=device-width, initial-scale=1.0">
                    <title>Button Link to YouTube Video</title>
                </head>
                <body>
                    <p>Click the button below to watch the video:</p>
                    <!-- Replace 'VIDEO_ID' with the ID of the YouTube video you want to link to -->
                    <a href="https://www.youtube.com/watch?v=zOjov-2OZ0E" target="_blank">
                        <button>Watch Video</button>
                    </a>
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