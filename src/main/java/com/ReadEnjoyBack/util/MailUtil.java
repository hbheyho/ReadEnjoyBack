package com.ReadEnjoyBack.util;

import org.apache.commons.mail.HtmlEmail;

import java.net.URL;
import java.util.Date;

/**
 * @Author:HB
 * @Description:
 * @Createdata:Created in  15:56  2019/1/16.
 */
public class MailUtil {
    public static boolean sendTextMail(String strMail,String Url) throws Exception{
        String strTitle = "来自ReadEnjoy的消息";
        String strText = "<div style=\"background: #F4F7FC;z-index:-1;min-height: 100px;font-size: 16px;text-align: left; position: absolute;top:0;left: 0;right: 0;bottom: 0;\">\n" +
                "\t<img src=\"images/LOGO3.png\" style=\"width: 200px;height: auto;margin: 20px 15% 0 15%;\">\n" +
                "\t<div style=\"margin: 20px 15%;margin-top: 0px;padding: 50px;background: #fff;border-radius: 6px;line-height: 40px\">\n" +
                "\t\t<p>您好！我们只需要您验证下这是您的电子邮件地址。这是您的吗？</p>\n" +
                "\t\t<a href=\""+ Url +"|"+strMail+"\" title=\"点此验证邮箱\" style=\"width: 100%;padding:10px 0;text-align:center;color: #fff;text-decoration: none;font-weight: 600;background: #47587C;display: block;\"  onMouseOver=\"this.style.color='#558E3E';this.style.background='#EBF3FF'\" onMouseOut=\"this.style.color='#fff';this.style.textDecoration='none';this.style.background='#47587C'\" >这就是我！</a>\n" +
                "\t\t<p>多谢！我们要保护互联网免遭僵尸的侵害嘛。</p>\n" +
                "\t</div>\n" +
                "</div>";
        boolean bret = false;
        HtmlEmail mail = new HtmlEmail();
        // 设置邮箱服务器信息
        mail.setSslSmtpPort("25");
        mail.setHostName(PropertiesUtil.getProperty("163.server"));
        // 设置密码验证器
        mail.setAuthentication(PropertiesUtil.getProperty("163.username"),
                PropertiesUtil.getProperty("163.password"));
        // 设置邮件发送者
        mail.setFrom(PropertiesUtil.getProperty("163.username"));
        // 设置邮件接收者
        mail.addTo(strMail);
        // 设置邮件编码
        mail.setCharset("UTF-8");
        // 设置邮件主题
        mail.setSubject(strTitle);
        mail.setHtmlMsg(strText);
        //设置替代消息
        mail.setTextMsg("您的电子邮件客户端不支持HTML消息");
        // 设置邮件发送时间
        mail.setSentDate(new Date());
        // 发送邮件
        mail.send();
        return bret;
    }
}
