# JavaWeb 2022

### 1.Servlet 

 a) What is Servlet?
- Servlet is one of the JavaEE specification. Specification is the interface 
- Servlet on JavaWeb one of the three major components. The three components are: Servlet - - program, Filter filter, Listener listener. 
- Servlet is a java applet running on the server, it can receive requests sent by the client, and respond to the data to the client. 

 b) Manual implementation of Servlet program 
 1. Write a class to implement the Servlet interface
 2. Implement service methods, process requests, and respond to data
 3. Go to web.xml to configure the servlet program's access address.


> HelloServlet.java
```java
public class HelloServlet implements Servlet {
/**
* The service method is dedicated to handling requests and responses
* @param servletRequest
* @param servletResponse
* @throws ServletException
* @throws IOException
*/
@Override
public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws
ServletException, IOException {
System.out.println("Hello Servlet 被访问了");
}
}
```
> web.xml 配置
```java
<?xml version="1.0" encoding="UTF-8"?>
<web-app xmlns="http://xmlns.jcp.org/xml/ns/javaee"
xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
xsi:schemaLocation="http://xmlns.jcp.org/xml/ns/javaee
http://xmlns.jcp.org/xml/ns/javaee/web-app_4_0.xsd"
version="4.0">
<!-- servlet 标签给Tomcat 配置Servlet 程序-->
<servlet>
<!--servlet-name 标签Servlet 程序起一个别名（一般是类名） -->
<servlet-name>HelloServlet</servlet-name>
<!--servlet-class 是Servlet 程序的全类名-->
<servlet-class>com.yuimorii.servlet.HelloServlet</servlet-class>
</servlet>
<!--servlet-mapping 标签给servlet 程序配置访问地址-->
<servlet-mapping>
<!--servlet-name 标签的作用是告诉服务器，我当前配置的地址给哪个Servlet 程序使用-->
<servlet-name>HelloServlet</servlet-name>
<!--url-pattern 标签配置访问地址<br/>
/ 斜杠在服务器解析的时候，表示地址为：http://ip:port/工程路径<br/>
/hello 表示地址为：http://ip:port/工程路径/hello <br/>
-->
<url-pattern>/hello</url-pattern>
</servlet-mapping>
</web-app>
```