package tn.esprit.spring.sms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

@org.springframework.stereotype.Service
public class Service {

    private final SmsSender smsSender;

    @Autowired
    public Service(@Qualifier("twilio") TwilioSmsSender smsSender) {
        this.smsSender = smsSender;
    }

    public void sendSms(SmsRequest smsRequest) {
        smsSender.sendSms(smsRequest);
    }
    
    public void sendmsg(String smsRequest) {
        smsSender.sendsmms(smsRequest);
    }
    
    public void msg(String tel) {
    	smsSender.sendsmms(tel);
    }
}
