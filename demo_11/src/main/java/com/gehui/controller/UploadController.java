package com.gehui.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;

/**
 * Created by Administrator on 2018/7/27 16:28.
 **/
@Controller
public class UploadController {
    private static final Logger logger = LoggerFactory.getLogger(UploadController.class);

    /**
     * 用来转发index.jsp页面，不能直接访问jsp页面
     *
     * @return
     */
    @RequestMapping(value = "index", method = RequestMethod.GET)
    public String index() {
        logger.info("请求首页成功");
        return "index";
    }

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public @ResponseBody
    String upload(HttpServletRequest request, MultipartFile file) {
        try {
            logger.info("开始上传文件");
            //获取项目路径组织上传地址
            String uploadDir = request.getSession().getServletContext().getRealPath("/") + "upload\\";
            logger.info("文件上传路径是:{}", uploadDir);
            //如果目录不存在则自动创建
            File dir = new File(uploadDir);
            if (!dir.exists()) {
                dir.mkdir();
            }
            //获取上传文件名
            String fileName = file.getOriginalFilename();
            logger.info("上传文件名是: " + fileName);
            //服务器保存的文件对象
            File serverFile = new File(uploadDir + fileName);
            //将上传的文件写入到服务器端文件内
            file.transferTo(serverFile);
        } catch (IOException e) {
            logger.error("文件上传失败: " + e);
            return "上传失败";
        }

        return "上传成功";
    }


}
