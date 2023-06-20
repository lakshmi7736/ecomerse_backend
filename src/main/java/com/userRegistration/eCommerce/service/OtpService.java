package com.userRegistration.eCommerce.service;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.userRegistration.eCommerce.dao.UserDao;
import com.userRegistration.eCommerce.entity.User;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Random;

public class OtpService {
    @Autowired
    private UserDao userDao;

    public String generateOtp(String phoneNumber) {
        // Generate a random 4-digit number for OTP
        String otp = String.format("%04d", new Random().nextInt(10000));

        // Save the OTP to the User entity
        User user = userDao.findByPhoneNumber(phoneNumber);
        user.setOtp(otp);
        userDao.save(user);

        return otp;
    }
    public static final String ACCOUNT_SID = "AC1285e34e7466cf07959fde500aafe1e6";
    public static final String AUTH_TOKEN = "82dd2909be4a5f0114f1d555a20976fb";
    public void sendSms (String phoneNumber, String message){
        try {
            Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
            Message.creator(
                    new com.twilio.type.PhoneNumber(phoneNumber),
                    new com.twilio.type.PhoneNumber("+13156311494"),
                    message
            ).create();
        } catch (Exception e) {
            // Handle any exceptions related to sending the SMS
            e.printStackTrace();
        }
    }

}
