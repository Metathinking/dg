import java.io.*;
import com.sun.image.codec.jpeg.*;
import sun.awt.image.PNGImageDecoder;

import java.awt.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import javax.swing.*;

/**
 * 类的功能，目的，描述等写在此处
 *
 * @author 胡玉波
 * @version 1.0
 * @(#) ImageTest.java 2016/08/30 15:03
 */
public class ImageTest {

    public static void main(String[]args) {

        try {
            //1.jpg是你的 主图片的路径
            InputStream is = new FileInputStream("i:"+File.separator+"22.png");

            //通过JPEG图象流创建JPEG数据流解码器
//            JPEGImageDecoder jpegDecoder = JPEGCodec.createJPEGDecoder(is);
            //解码当前JPEG数据流，返回BufferedImage对象
//            BufferedImage buffImg = jpegDecoder.decodeAsBufferedImage();
            BufferedImage buffImg = ImageIO.read(is);
            //得到画笔对象
            Graphics g = buffImg.getGraphics();

            //创建你要附加的图象。
            //2.jpg是你的小图片的路径
            ImageIcon imgIcon = new ImageIcon("i:"+File.separator+"water_logo.png");

            //得到Image对象。
            Image img = imgIcon.getImage();

            //将小图片绘到大图片上。
            int width = buffImg.getWidth();
            int height = buffImg.getHeight();
            int width1 = img.getWidth(null);
            int height1 = img.getHeight(null);
            g.drawImage(img,width-width1,height-height1,null);

//            //设置颜色。
//            g.setColor(Color.BLACK);
//            //最后一个参数用来设置字体的大小
//            Font f = new Font("宋体",Font.BOLD,30);
//            g.setFont(f);
//            //10,20 表示这段文字在图片上的位置(x,y) .第一个是你设置的内容。
//            g.drawString("默哀555555。。。。。。。",10,30);
//            g.dispose();

            OutputStream os = new FileOutputStream("i:"+File.separator+"union.png");
            //创键编码器，用于编码内存中的图象数据。

//            JPEGImageEncoder en = JPEGCodec.createJPEGEncoder(os);
//            en.encode(buffImg);
//            File imageFile = new File("i:"+File.separator+"union.jpg");
            ImageIO.write(buffImg,"png",os);

            is.close();
            os.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println ("合成结束。。。。。。。。");


    }
}