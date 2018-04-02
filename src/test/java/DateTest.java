import com.hu.dg.util.DateUtil;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 类的功能，目的，描述等写在此处
 *
 * @author 胡玉波
 * @version 1.0
 * @(#) DateTest.java 2016/06/19 16:58
 */
public class DateTest {

    public static void main(String[] args) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String format = simpleDateFormat.format(new Date(1466318085277l));
        System.out.println(format);
    }
}