package com.ship.controller;


import net.coobird.thumbnailator.Thumbnails;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import jakarta.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.UUID;

/**
 * 文件上传和下载
 */
@RestController
@RequestMapping("/common")
public class ImgController {
    private static final Logger log = LoggerFactory.getLogger(ImgController.class);
    private final String basePath, chatImg;

    public ImgController(@Value("${pet-ship.images-path}") String basePath, @Value("${pet-ship.chatImg-path}") String chatImg) {
        this.basePath = basePath;
        this.chatImg = chatImg;
    }

    /**
     * 文件上传，只限头像图片
     */
    @PostMapping("/upload")
    public String upload(MultipartFile myFile) {
        String fileName = getFileName(myFile);//使用UUID重新生成文件名
        String filePath = basePath + fileName;
        try {
            myFile.transferTo(new File(filePath));//转存临时文件
            Thumbnails.of(filePath).forceSize(128, 128).toFile(filePath);//修改图片尺寸
        } catch (IOException e) {
            log.warn("转存图片失败");
        }
        return fileName;
    }

    /**
     * 文件上传，聊天图片消息
     */
    @PostMapping("/upChat")
    public String upChat(MultipartFile myFile) {
        String fileName = getFileName(myFile);//使用UUID重新生成文件名
        String filePath = chatImg + fileName;
        try {
            myFile.transferTo(new File(filePath));//转存临时文件
        } catch (IOException e) {
            log.warn("转存图片失败");
        }
        return fileName;
    }

    /**
     * 文件下载，头像图片
     */
    @GetMapping("/download")
    public void download(String name, HttpServletResponse response) {
        sendImg(name, response, basePath);
    }

    /**
     * 文件下载，聊天图片
     */
    @GetMapping("/downChat")
    public void downChat(String name, HttpServletResponse response) {
        sendImg(name, response, chatImg);
    }


    /**
     * 文件上传：使用UUID重新生成文件名
     *
     * @param myFile 上传的图片
     * @return 新的文件名
     */
    private String getFileName(MultipartFile myFile) {
        if (myFile == null) return "文件上传为空";
        String originalFilename = myFile.getOriginalFilename();//abc.jpg
        assert originalFilename != null;
        String suffix = originalFilename.substring(originalFilename.lastIndexOf("."));
        return UUID.randomUUID() + suffix;
    }

    /**
     * 图片发送到前端
     *
     * @param name     图片名
     * @param response 响应流
     * @param imgPath  图片路径
     */
    private void sendImg(String name, HttpServletResponse response, String imgPath) {
        try (var fis = new FileInputStream(imgPath + name); var ops = response.getOutputStream()) {
            response.setContentType("image/jpeg");
            int len;
            byte[] bytes = new byte[1024];
            while ((len = fis.read(bytes)) != -1) {
                ops.write(bytes, 0, len);
                ops.flush();
            }
        } catch (Exception e) {
            log.warn("未找到相应图片" + name);
        }
    }
}
