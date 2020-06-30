package com.work.utlis;

import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;

import java.util.Random;

public class SendUtil {
    public int sendmassge(String usertel){
        DefaultProfile profile = DefaultProfile.getProfile("cn-hangzhou", "LTAI4GG1sRhvnqxTYA62xX44", "YZbKGOGflDaDdPbYbNIXGX1JpICyo0");
        IAcsClient client = new DefaultAcsClient(profile);
        //int code=(int)(Math.random()*9+1)*100000;
        Random random = new Random();
        int code = new Integer(String.valueOf(new Random().nextInt(899999) + 100000));
        CommonRequest request = new CommonRequest();
        request.setSysMethod(MethodType.POST);
        request.setSysDomain("dysmsapi.aliyuncs.com");
        request.setSysVersion("2017-05-25");
        request.setSysAction("SendSms");
        request.putQueryParameter("RegionId", "cn-hangzhou");
        request.putQueryParameter("PhoneNumbers", usertel);
        request.putQueryParameter("SignName", "哈皮招聘");
        request.putQueryParameter("TemplateCode", "SMS_189762358");
        request.putQueryParameter("TemplateParam", "{\"code\":"+code+"}");
        try {
            CommonResponse response = client.getCommonResponse(request);
            System.out.println(response.getData());
        } catch (ServerException e) {
            e.printStackTrace();
        } catch (ClientException e) {
            e.printStackTrace();
        }
        return code;
    }
}
