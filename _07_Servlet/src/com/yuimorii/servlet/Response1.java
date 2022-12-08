package com.yuimorii.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Response1 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("former Response1");
        req.setAttribute("key1", "value1");
        // 设置响应状态码302 ，表示重定向，（已搬迁）
//        resp.setStatus(302);
//        // 设置响应头，说明新的地址在哪里
//        resp.setHeader("Location", "http://localhost:8080/07_Servlet/response2");
//        //resp.setHeader("Location", "http://localhost:8080");

        //第二種方法sendRedirect
        resp.sendRedirect("http://localhost:8080/07_Servlet/response2");

    }
}
