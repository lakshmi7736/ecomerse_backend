package com.userRegistration.eCommerce.controller;

import com.userRegistration.eCommerce.dao.UserDao;
import com.userRegistration.eCommerce.entity.AuthRequest;
import com.userRegistration.eCommerce.entity.JwtResponse;
import com.userRegistration.eCommerce.entity.User;
import com.userRegistration.eCommerce.service.JwtService;
import com.userRegistration.eCommerce.service.OtpService;
import com.userRegistration.eCommerce.util.JwtRequest;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.HashMap;
import java.util.Map;

public class OtpController {
    @Autowired
    private OtpService otpService;
    @Autowired
    private UserDao userDao;

    @Autowired
    private JwtRequest jwtRequest;

    @Autowired
    private JwtService jwtService;

    @RequestMapping(value = "requestOtp/{phoneNumber}", method = RequestMethod.GET)
    public Map<String, Object> getOtp(@PathVariable String phoneNumber) {
        Map<String, Object> returnMap = new HashMap<>();
        try {
            // Check if the user exists
            User user = userDao.findByPhoneNumber(phoneNumber);
            if (user == null) {
                returnMap.put("status", "failed");
                returnMap.put("message", "User not found");
                return returnMap;
            }

            // Generate OTP
            String otp = otpService.generateOtp(phoneNumber);

            // Send the OTP via Twilio SMS
            String message = "Your OTP: " + otp;
            otpService.sendSms(phoneNumber, message);

            returnMap.put("otp", otp);
            returnMap.put("status", "success");
            returnMap.put("message", "Otp sent successfully");
        } catch (Exception e) {
            returnMap.put("status", "failed");
            returnMap.put("message", e.getMessage());
        }

        return returnMap;
    }


    @RequestMapping(value = "verifyOtp/", method = RequestMethod.POST)
    public Map<String, Object> verifyOtp(@RequestBody AuthRequest authenticationRequest) {
        Map<String, Object> returnMap = new HashMap<>();
        try {
            User user = userDao.findByPhoneNumber(authenticationRequest.getPhoneNo());

            // Verify OTP
            if (user != null && authenticationRequest.getOtp().equals(user.getOtp())) {
                JwtRequest jwtRequest = new JwtRequest();
                jwtRequest.setPhoneNumber(authenticationRequest.getPhoneNo());

                JwtResponse jwtResponse = jwtService.generateToken(jwtRequest);
                String jwtToken = jwtResponse.getJwtToken();

                returnMap.put("status", "success");
                returnMap.put("message", "OTP verified successfully");
                returnMap.put("jwt", jwtToken);

                user.setOtp(null); // Clear the OTP after successful verification
                userDao.save(user);
            } else {
                returnMap.put("status", "failed");
                returnMap.put("message", "OTP is either expired or incorrect");
            }
        } catch (Exception e) {
            returnMap.put("status", "failed");
            returnMap.put("message", e.getMessage());
        }

        return returnMap;
    }


}