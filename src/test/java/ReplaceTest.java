/**
 * 类的功能，目的，描述等写在此处
 *
 * @author 胡玉波
 * @version 1.0
 * @(#) ReplaceTest.java 2016/06/16 13:06
 */
public class ReplaceTest {

    public static void main(String[] args) {
//        String template="<a href='{info}'>连接</a>,复制:{info}";
//        String link = "http://www.baidu.com";
//        String back = template.replaceAll("\\{info\\}", link);
//        System.out.println(back);
//        String temp = "http://s.click.taobao.com/t?e=m%3D2%26s%3D%2FWhTn6hCAf8cQipKwQzePOeEDrYVVa64LKpWJ%2Bin0XJRAdhuF14FMb4ZXMNz7SBfxq3IhSJN6GQKAwUFYzoCaCGYsD4P%2B2IuHjank4%2BpnlFm8hUoJUZLssw9kXVqtRjV4Qt0D4uqJ%2FaJFpzlXgBIcKJn5AyUbPoV&pvid=50_119.186.47.249_2188_1466241351160";
//        String temp="https://detail.tmall.com/item.htm?id=526220527625&ali_trackid=2:mm_59033140_12440238_47166240:1467201166_2k1_936506683&spm=a21bo.7925826.192013.4.sSZejj&scm=1007.12846.33968.999999999999999&pvid=1d38a49e-43da-46a7-b5ef-7d2963cd7bf1&sku_properties=5919063:6536025";
//        System.out.println(temp.length());
        String str = "weicc-20100107-00001";
        str = str.substring(str.length()-5,str.length());
        System.out.println(str);//输出
    }
}