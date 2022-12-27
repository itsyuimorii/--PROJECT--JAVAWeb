package com.yuimorii.servlet;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * @program: Javaweb
 * @description:
 * @author: yuimorii
 * @create: 2022-12-27 10:49
 **/
public class ServletDownloadDemo extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1. 创建字节输入流，关联读取的文件
        String realPath = getServletContext().getRealPath("/img/ServletLive.png");
        BufferedInputStream bis = new BufferedInputStream(new FileInputStream(realPath));
        //2. 设置响应消息头支持的类型
        //Content-Type是消息头名称，表示支持的类型
        //application/octet-stream是消息头参数，表示字节流
        resp.setHeader("Content-Type", "application/octet-stream");
        //3. 设置响应消息头以下载方式打开资源
        //Content-Disposition是消息头名称，表示处理形式
        //attachment;filename=ServletLive.png是消息头参数，表示附件形式处理，
        //filename表示文件名称
        resp.setHeader("Content-Disposition"," attachment; filename = ServletLive.png ");
        //4. 通过响应对象获得字节输出流对象
        ServletOutputStream outputStream = resp.getOutputStream();
        //5. 循环读写
        byte[] bytes = new byte[1024];
        int len;
        while ((len = bis.read(bytes)) != -1) {
            outputStream.write(bytes, 0, len);
        }
        //6. 释放资源
        bis.close();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse
            resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
