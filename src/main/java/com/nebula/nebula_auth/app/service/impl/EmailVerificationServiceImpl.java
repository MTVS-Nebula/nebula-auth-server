package com.nebula.nebula_auth.app.service.impl;

import com.nebula.nebula_auth.app.dao.entity.EmailVerificationCode;
import com.nebula.nebula_auth.app.dao.repository.EmailVerificationCodeRepository;
import com.nebula.nebula_auth.app.service.EmailVerificationService;
import com.nebula.nebula_auth.helper.mail.service.SendEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class EmailVerificationServiceImpl implements EmailVerificationService {
    private final SendEmailService sendEmailService;
    private final EmailVerificationCodeRepository emailVerificationCodeRepository;

    @Autowired
    public EmailVerificationServiceImpl(SendEmailService sendEmailService,
                                        EmailVerificationCodeRepository emailVerificationCodeRepository) {
        this.sendEmailService = sendEmailService;
        this.emailVerificationCodeRepository = emailVerificationCodeRepository;
    }

    @Override
    @Transactional
    public void sendVerificationEmail(String email) {
        Random random = new Random();
        int verificationCode = random.nextInt(888888) + 111111;
        emailVerificationCodeRepository.deleteByEmail(email);
        EmailVerificationCode emailVerificationCode = new EmailVerificationCode(email, verificationCode);
        emailVerificationCodeRepository.save(emailVerificationCode);
        String content = "<!DOCTYPE html>\n" +
                "<html lang=\"en\" xmlns=\"http://www.w3.org/1999/xhtml\" xmlns:o=\"urn:schemas-microsoft-com:office:office\">\n" +
                "<head>\n" +
                "  <meta charset=\"utf-8\">\n" +
                "  <meta name=\"viewport\" content=\"width=device-width,initial-scale=1\">\n" +
                "  <meta name=\"x-apple-disable-message-reformatting\">\n" +
                "  <title></title>\n" +
                "\n" +
                "\n" +
                "</head>\n" +
                "<body style=\"margin:0;padding:0;word-spacing:normal;background-color:#939297;\">\n" +
                "  <div role=\"article\" aria-roledescription=\"email\" lang=\"en\" style=\"text-size-adjust:100%;-webkit-text-size-adjust:100%;-ms-text-size-adjust:100%;background-color:#939297;\">\n" +
                "    <table role=\"presentation\" style=\"width:100%;border:none;border-spacing:0;\">\n" +
                "      <tr>\n" +
                "        <td align=\"center\" style=\"padding:0;\">\n" +
                "\n" +
                "          <table role=\"presentation\" style=\"width:94%;max-width:600px;border:none;border-spacing:0;text-align:left;font-family:Arial,sans-serif;font-size:16px;line-height:22px;color:#363636;\">\n" +
                "            <tr>\n" +
                "              <td style=\"padding:40px 30px 30px 30px;text-align:center;font-size:24px;font-weight:bold;\">\n" +
                "                <a href=\"https://ibb.co/YQfBKss\"><img src=\"\" alt=\"\" border=\"0\"></a>\n" +
                "            </td>\n" +
                "            </tr>\n" +
                "            <tr>\n" +
                "              <td style=\"padding:30px;background-color:#ffffff;\">\n" +
                "                <!--title-->\n" +
                "                <h1 align=\"center\" style=\"margin-top:0;margin-bottom:16px;font-size:26px;line-height:32px;font-weight:bold;letter-spacing:-0.02em;\">52Hertz</h1>\n" +
                "                <div align=\"center\"><img style=\"height:250px;\" src=\"https://cdn.discordapp.com/attachments/1020207271692738623/1046313069409751110/KakaoTalk_Photo_2022-11-25-15-56-13.png\"></div>\n" +
                "                <!--first paragraph-->\n" +
                "                <p style=\"margin:0;\"></p>\n" +
                "              </td>\n" +
                "            </tr>\n" +
                "\n" +
                "            <tr>\n" +
                "              <td align=\"center\" style=\"padding:35px 30px 11px 30px;font-size:0;background-color:#ffffff;border-bottom:1px solid #f0f0f5;border-color:rgba(201,201,207,.35);\">\n" +
                "\n" +
                "                <!-- <div class=\"col-sml\" style=\"display:inline-block;width:100%;max-width:145px;vertical-align:top;text-align:left;font-family:Arial,sans-serif;font-size:14px;color:#363636;\">\n" +
                "                  <img src=\"\" width=\"115\" alt=\"\" style=\"width:115px;max-width:80%;margin-bottom:20px;\">\n" +
                "                </div> -->\n" +
                "\n" +
                "                <div  class=\"col-lge\" style=\"display:inline-block;width:100%;max-width:395px;vertical-align:top;padding-bottom:20px;font-family:Arial,sans-serif;font-size:16px;line-height:22px;color:#363636;\">\n" +
                "                  <!--second paragraph-->             \n" +
                "                    <p style=\"color:#707070; font-size: 12px; margin-top:0;margin-bottom:12px;\">\n" +
                "                      이메일 인증번호를 입력하시면 인증절차가<br>\n" +
                "                      완료되어 가입을 진행하실 수 있습니다.\n<br>\n" +
                "                      인증시간이 만료되었을 경우,\n\n<br>\n" +
                "                      인증번호 재발송을 진행해 주시기 바랍니다.\n<br>\n" +
                "                    </p>\n" +
                "                 <!--third paragraph-->\n" +
                "                    <p style=\"margin-top:0;margin-bottom:18px;\"></p>\n" +
                "                  <p style=\"margin:0;\"><div href=\"https://52hertz.mtvs-nebula.com/\" style=\"background: #00AEEF; text-decoration: none; padding: 10px 25px; color: #ffffff; border-radius: 4px; display:inline-block; mso-padding-alt:0;text-underline-color:#ff3884\"><!--[if mso]><i style=\"letter-spacing: 25px;mso-font-width:-100%;mso-text-raise:20pt\">&nbsp;</i><![endif]--><span style=\"mso-text-raise:10pt;font-weight:bold;\">"+verificationCode+"</span><!--[if mso]><i style=\"letter-spacing: 25px;mso-font-width:-100%\">&nbsp;</i><![endif]--></div></p>\n" +
                "                </div>\n" +
                "              </td>\n" +
                "            </tr>\n" +
                "\n" +
                "\n" +
                "            <tr>\n" +
                "              <td style=\"padding:30px;text-align:center;font-size:12px;background-color:#404040;color:#cccccc;\">\n" +
                "                <p style=\"margin:0 0 8px 0;\"><a href=\"\" style=\"text-decoration:none;\"><img src=\"https://assets.codepen.io/210284/facebook_1.png\" width=\"40\" height=\"40\" alt=\"f\" style=\"display:inline-block;color:#cccccc;\"></a> <a href=\"\" style=\"text-decoration:none;\"><img src=\"https://assets.codepen.io/210284/twitter_1.png\" width=\"40\" height=\"40\" alt=\"t\" style=\"display:inline-block;color:#cccccc;\"></a></p>\n" +
                "                <p style=\"margin:0;font-size:14px;line-height:20px;\">Copyright 2022. 52hertz all rights reserved.<br></p>\n" +
                "              </td>\n" +
                "            </tr>\n" +
                "          </table>\n" +
                "\n" +
                "        </td>\n" +
                "      </tr>\n" +
                "    </table>\n" +
                "  </div>\n" +
                "</body>\n" +
                "</html>";
        List<String> receivers = List.of(email);
        sendEmailService.send("[52Hertz] 인증코드",content,receivers);
    }
}
