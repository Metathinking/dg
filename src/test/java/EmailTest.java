import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;

/**
 * 类的功能，目的，描述等写在此处
 *
 * @author 胡玉波
 * @version 1.0
 * @(#) EmailTest.java 2016/06/13 16:56
 */
public class EmailTest {

    public static void main(String[] args) {
        HtmlEmail email = new HtmlEmail();
        email.setHostName("smtp.mxhichina.com");
        email.setSmtpPort(25);
        email.setAuthentication("wolais@wolais.com","renshuang1986.");
        email.setCharset("UTF-8");
        try {
            email.addTo("luyilaosan@163.com");
//            email.addTo("503523986@qq.com","renshuang","UTF-8");
//            email.setFrom("wolais@wolais.com","");
            email.setFrom("wolais@wolais.com","人生");
            email.setSubject("主题测试");
            email.setHtmlMsg("<a href='http://www.baidu.com'>www.baidu.com</a>");
            email.send();

        } catch (EmailException e) {
            e.printStackTrace();
        }
    }
}