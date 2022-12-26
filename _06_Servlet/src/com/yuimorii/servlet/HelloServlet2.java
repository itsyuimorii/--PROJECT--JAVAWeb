package com.yuimorii.servlet;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class HelloServlet2 extends HttpServlet {
    @Override
    public void init(ServletConfig config) throws ServletException {
        //The init method must be called
        super.init(config);
        System.out.println("Rewrote the init initialization method");


    }

    /**
     * doGet（）=> Called on a get request
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("HelloServlet2 doGet method");
        ServletConfig servletConfig = getServletConfig();
        System.out.println(servletConfig);

        //2. Get the initialization parameter init-param
        System.out.println("The value of the initialization parameter username is;" + servletConfig.getInitParameter("username"));
        System.out.println("The value of the initialization parameter url is;" + servletConfig.getInitParameter("url"));
    }
    /**
     * doPost（）=> Called on a get request
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("HelloServlet2 doPost method");
    }
}