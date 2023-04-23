package org.example;

import java.io.*;
import java.nio.charset.*;
import java.nio.file.*;
import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;
import javax.mail.internet.MimeMessage.RecipientType;


public class MailTest
{
   public static void main(String[] args) throws MessagingException, IOException
   {
      // read properties
      Properties props = new Properties();
      try (InputStream in = Files.newInputStream(Paths.get("/home/txy/saz/SUSTECH_CS209A/week8/src/main/resources/mail.properties")))
      {
         props.load(in);
      }

      // read message info
      List<String> lines = Files.readAllLines(Paths.get("/home/txy/saz/SUSTECH_CS209A/week8/src/main/resources/message.txt"), StandardCharsets.UTF_8);

      String from = lines.get(0);
      String to = lines.get(1);
      String subject = lines.get(2);

      StringBuilder builder = new StringBuilder();
      for (int i = 3; i < lines.size(); i++)
      {
         builder.append(lines.get(i));
         builder.append("\n");
      }


      // read password for your email account
//      System.out.println("Password: ");
//      Scanner scanner = new Scanner(System.in);
      String password = "lshhcxtklwtgdgji";


      Session mailSession = Session.getDefaultInstance(props,
                 new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                       return new PasswordAuthentication(to, password);
                    }
                 }
              );
      MimeMessage message = new MimeMessage(mailSession);
      // TODO 1: check the MimeMessage API to figure out how to set the sender, receiver, subject, and email body
// 2. From: 发件人
      message.setFrom(new InternetAddress(from, "我自己", "UTF-8"));
// 3. To: 收件人（可以增加多个收件人、抄送、密送）
      message.setRecipient(MimeMessage.RecipientType.TO, new InternetAddress(to));

// 4,抄送人
//      message.addRecipient(MimeMessage.RecipientType.CC, new InternetAddress("xxx@.com"));

// 5. Subject: 邮件主题
      message.setSubject(subject, "UTF-8");

//// 6，设置邮件内容，混合模式
//      MimeMultipart msgMultipart = new MimeMultipart("mixed");
//      message.setContent(msgMultipart);
//
//// 7，设置消息正文
//      MimeBodyPart content = new MimeBodyPart();
//      msgMultipart.addBodyPart(content);
//
//// 8，设置正文格式
//      MimeMultipart bodyMultipart = new MimeMultipart("related");
//      content.setContent(bodyMultipart);
//
//// 9，设置正文内容
//      MimeBodyPart htmlPart = new MimeBodyPart();
//      bodyMultipart.addBodyPart(htmlPart);
//      htmlPart.setContent(builder.toString(), "text/html;charset=UTF-8");

      message.setText(builder.toString());

// 11. 设置发件时间
      message.setSentDate(new Date());

// 12. 保存文件准备发送
      message.saveChanges();
      Transport.send(message);
      // TODO 2: check the Session API to figure out how to connect to the mail server and send the message

   }
}