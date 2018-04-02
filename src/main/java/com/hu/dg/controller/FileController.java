package com.hu.dg.controller;

import com.hu.dg.domain.ImageInfo;
import com.hu.dg.exception.ServiceException;
import com.hu.dg.service.ImageInfoService;
import com.hu.dg.util.*;
import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageDecoder;
import com.sun.image.codec.jpeg.JPEGImageEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;

/**
 * 类的功能，目的，描述等写在此处
 *
 * @author 胡玉波
 * @version 1.0
 * @(#) FileController.java 2016/06/10 16:10
 */
@Controller
public class FileController {

    @Autowired
    private ImageInfoService imageInfoService;

    @RequestMapping(value = "/uploadImage",method = RequestMethod.POST)
    @ResponseBody
    public Tip uploadImage(@RequestParam(value = "file") MultipartFile file,
                           HttpServletRequest request,
                           boolean useWaterLogo) {
        try {
            String filePath = saveUploadImage(request.getSession().getServletContext(), file,useWaterLogo);
            String contextPath = request.getContextPath();
            String serverUrl = request.getScheme()+"://"+request.getServerName()+contextPath;
            return new Tip(true,100,"保存成功",serverUrl+filePath);
        } catch (Exception e) {
            return ExceptionTipHandler.handler(e);
        }
    }

    /**
     * 保存上传的图片并返回服务器存储的图片名称
     *
     * @param servletContext
     * @param file
     * @return
     * @throws Exception
     */
    protected String saveUploadImage(ServletContext servletContext,
                                     MultipartFile file,
                                     boolean useWaterLogo) throws Exception {
        if (file == null) {
            throw new ServiceException(102,"上传图片为空");//上传图片为空
        }
        if (!FileUtil.isImage(file)) {
            throw new ServiceException(103,"不是正确的图片文件");
        }

        String path = "/upload/image/" + DateUtil.getDateStringByFormat(System.currentTimeMillis(), "yyyy/MM/dd");
        return saveBackPath(servletContext, file, path,useWaterLogo);
    }

    protected String saveBackPath(ServletContext servletContext,
                                  MultipartFile file,
                                  String path,
                                  boolean useWaterLogo) throws IOException {

        //根据md5查询文件是否已存在 start
        String md5Code = Md5Factory.encoding(file);
        ImageInfo byCode = imageInfoService.findByCode(md5Code);
        if(byCode!=null){
            return byCode.getUrl();
        }
        //根据md5查询文件是否已存在 start

        String descPathString = servletContext.getRealPath(path);
        File descPath = new File(descPathString);
        if (!descPath.exists()) {
            descPath.mkdirs();
        }
        String fileExtendName = FileUtil.getFileExtendName(file.getOriginalFilename());
        String fileName = DateUtil.getDateStringByFormat(System.currentTimeMillis(), "yyyyMMddHHmmssSSS") + "." + fileExtendName;
        String descFilePath = descPathString + File.separator + fileName;
        if(useWaterLogo){//添加水印并保存
            addWaterLogo(file,fileExtendName,descFilePath,servletContext);
        }else{//直接保存
            File imageFile = new File(descFilePath);
            file.transferTo(imageFile);
        }

        String fullPath = path + File.separator + fileName;
        fullPath = fullPath.replaceAll("\\\\", "/");

        ImageInfo imageInfo = new ImageInfo();
        imageInfo.setUrl(fullPath);
        imageInfo.setMd5Code(md5Code);
        imageInfoService.create(imageInfo);
        return fullPath;
    }

    /**
     * 添加水印
     */
    private void addWaterLogo(MultipartFile file,String formatName,String filePath,ServletContext servletContext){
        try {
            //1.jpg是你的 主图片的路径
            InputStream is = file.getInputStream();

            BufferedImage buffImg = ImageIO.read(is);
            //得到画笔对象
            Graphics g = buffImg.getGraphics();

            //创建你要附加的图象。
            //2.jpg是你的小图片的路径
            String path = File.separator+"res"+File.separator+"img"+File.separator+"water_logo.png";
            String realPath = servletContext.getRealPath(path);
            ImageIcon imgIcon = new ImageIcon(realPath);
            //得到Image对象。
            Image img = imgIcon.getImage();

            //将小图片绘到大图片上。
            int width = buffImg.getWidth();
            int height = buffImg.getHeight();
            int width1 = img.getWidth(null);
            int height1 = img.getHeight(null);
            g.drawImage(img,width-width1,height-height1,null);

            OutputStream os = new FileOutputStream(filePath);
            //创键编码器，用于编码内存中的图象数据。
            ImageIO.write(buffImg,formatName,os);

            is.close();
            os.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}