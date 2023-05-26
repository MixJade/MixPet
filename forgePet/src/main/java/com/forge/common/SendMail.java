package com.forge.common;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailAuthenticationException;
import org.springframework.mail.MailSendException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import java.util.Random;

/**
 * 发送邮件验证码
 */
@Component
public class SendMail {
    private static final Logger log = LoggerFactory.getLogger(SendMail.class);
    private final String from;
    private final JavaMailSender mailSender;
    private static final char[] CHARS = {'1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C',
            'D', 'E', 'F', 'G', 'H', 'J', 'K', 'L', 'M', 'N', 'P', 'Q', 'R', 'S', 'T', 'U', 'V',
            'W', 'X', 'Y', 'Z'};

    public SendMail(@Value("${spring.mail.username}") String from, @Autowired JavaMailSender mailSender) {
        this.from = from;
        this.mailSender = mailSender;
    }

    /**
     * 发送简单文本邮件
     */
    public String sendQQEmail(String to) {
        MimeMessage message = mailSender.createMimeMessage();
        try {
            MimeMessageHelper messageHelper = new MimeMessageHelper(message, true);
            messageHelper.setFrom(from);
            messageHelper.setTo(to);
            messageHelper.setSubject("你好！世界");
            String code = bornVerifyCode(4);
            // true 为 HTML 邮件
            String content = String.format("""
                    <!DOCTYPE html>
                    <html lang="zh-CN">
                    <head>
                       <meta charset="UTF-8">
                       <title>验证码邮件</title>
                    </head>
                    <body>
                    <h2>尊敬的用户，你好：</h2>
                    <p>&nbsp;&nbsp;兔年到，祝福到；</p>
                    <p>短信问好，友人可安；祝愿朋友，财源滚滚；</p>
                    <p>吉祥高照，鸿运当头；幸福围绕，健康相伴；</p>
                    <p>一生平安，万事顺心；笑口常开，兔年快乐！</p>
                    <br>
                    <p>祝朋友2023兔年快乐!</p>
                    <p>验证码：%s</p>
                    </body>
                    </html>""", code);
            messageHelper.setText(content, true);
            mailSender.send(message);
            log.info("成功向" + to + "发送邮件");
            return code;
        } catch (MessagingException e) {
            log.warn("邮件发送失败");
        } catch (MailSendException e) {
            log.warn("发送邮件过于频繁");
        } catch (MailAuthenticationException e) {
            log.warn("发送者邮箱的授权服务已关闭");
            System.err.println("我于2023年5月25日上传了代码，上传之后我关闭了我邮箱的POP3/SMTP服务");
        }
        return null;
    }

    /**
     * 使用系统默认字符源生成验证码
     *
     * @param verifySize 验证码长度
     * @return 验证码字符
     */
    public static String bornVerifyCode(int verifySize) {
        Random rand = new Random();
        StringBuilder verifyCode = new StringBuilder(verifySize);
        for (int i = 0; i < verifySize; i++) {
            verifyCode.append(CHARS[rand.nextInt(CHARS.length)]);
        }
        return verifyCode.toString();
    }
}

