package com.qkhl.common;

import java.io.IOException;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import android.os.Message;

import com.qkhl.activity.ForgetPassword;

public class ForgetSendSMS {
	private static String Url = "http://106.ihuyi.cn/webservice/sms.php?method=Submit";
	private String phone_number;
	private String message;

	private static int mobile_code;

	public ForgetSendSMS(String phone_number) {
		this.phone_number = phone_number;
	}
 
	//返回验证码
	public static int get_mobile_code(){
		return mobile_code;
	}
	//返回message
	public String get_message(){
		return message;
	}
	//发送验证码
	public void send() {
		new Thread(new Runnable() {

			@Override
			public void run() {
				HttpClient client = new HttpClient();
				PostMethod method = new PostMethod(Url);

				// client.getParams().setContentCharset("GBK");
				client.getParams().setContentCharset("UTF-8");
				method.setRequestHeader("ContentType","application/x-www-form-urlencoded;charset=UTF-8");

				mobile_code = (int) ((Math.random() * 9 + 1) * 100000);

				// System.out.println(mobile);

				String content = new String("您的校验码是：" + mobile_code+ "。请不要把校验码泄露给其他人。如非本人操作，可不用理会！");

				NameValuePair[] data = {// 提交短信
						new NameValuePair("account", "cf_guoqingyu"),
						new NameValuePair("password", "luping521"), // 密码可以使用明文密码或使用32位MD5加密
						// new NameValuePair("password",
						// util.StringUtil.MD5Encode("密码")),
						new NameValuePair("mobile", phone_number),
						new NameValuePair("content", content), };

				method.setRequestBody(data);

				try {
					client.executeMethod(method);

					String SubmitResult = method.getResponseBodyAsString();

					// System.out.println(SubmitResult);

					Document doc = DocumentHelper.parseText(SubmitResult);
					Element root = doc.getRootElement();

					String code = root.elementText("code");
					message = root.elementText("msg");
					String smsid = root.elementText("smsid");

					System.out.println(code+":1");
					System.out.println(message+":2");
					System.out.println(smsid+":3");
					Message msg = new Message();
						msg.obj = message;
					if ("2".equals(code)) {
						msg.obj = "短信发送成功";
					}
					if ("406".equals(code)) {
						msg.obj = "手机格式不正确";
					}
					if ("403".equals(code)) {
						msg.obj = "手机号不能为空";
					}
					
					ForgetPassword.forgetphonenumerrhandle.sendMessage(msg);

				} catch (HttpException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				} catch (DocumentException e) {
					e.printStackTrace();
				}
			}
		}).start();

	}

}
