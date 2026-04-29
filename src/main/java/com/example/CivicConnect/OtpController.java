package com.example.CivicConnect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/otp")
@CrossOrigin("*")
public class OtpController {

    @Autowired
    private EmailService emailService;

    @Autowired
    private OTPGenerator otpGenerator;

    @PostMapping("/send")
    public String sendOTP(@RequestParam String email) {

        int otp = otpGenerator.generateOTP();

        emailService.sendOtp(email, String.valueOf(otp));

        return "OTP Sent Successfully ✅";
    }

    @GetMapping("/verify")
    public String verifyOTP(@RequestParam int otp) {

        if (otpGenerator.getGeneratedOTP() == otp) {
            return "OTP Verified Successfully ✅";
        } else {
            return "Invalid OTP ❌";
        }
    }
}
