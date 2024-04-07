package nl.hu.fronttester.testAPP.Application;// EmailController.java

import nl.hu.fronttester.testAPP.Service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("api/v1/")
public class EmailController {

    @Autowired
    private EmailService emailService;

    @GetMapping("/sendEmail")
    public String sendEmail(@RequestBody dtoMail dtoMail) throws IOException {
        System.out.println("Sending email to: " + dtoMail.to);
        System.out.println("Subject: " + dtoMail.subject);
        emailService.sendHtmlEmail(dtoMail.to, dtoMail.subject);
        return "Email sent successfully!";
    }

    @GetMapping("/test")
    public dtoMail test() {
        dtoMail dtoMail = new dtoMail();
        dtoMail.to = "test.test@testmail.com";
        dtoMail.subject = "Test subject";
        return dtoMail;
    }
    @PostMapping(value= "/receive", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String receive(@ModelAttribute FormBody body) {
        System.out.println(body.getFname());
        System.out.println(body.getLname());
        System.out.println(body.getOptions());
        return "Email received successfully!";
    }

}
