<!-- vscode-markdown-toc -->

    * 1. [1. Overview](#Overview)
    	* 1.1. [a) What is Servlet?](#aWhatisServlet)
    	* 1.2. [b) Manual implementation of Servlet program](#bManualimplementationofServletprogram)
    * 2. [2. Servlet interface](#Servletinterface)
    * 3. [3. The life cycle of Servlet](#ThelifecycleofServlet)
    * 4. [4. Servlet configure](#Servletconfigure)
    * 5. [5. Implementing Servlet programs by inheriting HttpServlet](#ImplementingServletprogramsbyinheritingHttpServlet)
    * 6. [6. Servlet creation timing](#Servletcreationtiming)
    * 7. [1. Overview](#Overview-1)
    * 8. [2. ServletConfig configuration](#ServletConfigconfiguration)
    * 9. [ServletConfig function](#ServletConfigfunction)
    * 10. [1. Overview](#Overview-1)
    	* 10.1. [a) What is ServletContext?](#aWhatisServletContext)
    	* 10.2. [b) ServletContext class of the four roles](#bServletContextclassofthefourroles)
    * 11. [2. ServletContext configuration](#ServletContextconfiguration)
    * 12. [3. ServletContext configuration](#ServletContextconfiguration-1)
    * 13. [1. What is HTTP?](#WhatisHTTP)
    * 14. [2. HTTP get/post request style](#HTTPgetpostrequeststyle)
    * 15. [3. HTTP protocol format of the response](#HTTPprotocolformatoftheresponse)
    * 16. [1. Overview](#Overview-1)
    * 17. [2. HTTP Response Status Code Definition](#HTTPResponseStatusCodeDefinition)
    * 18. [3. Methods for sending status codes](#Methodsforsendingstatuscodes)
    * 19. [4. methods for sending response message header](#methodsforsendingresponsemessageheader)
    * 20. [5. Methods for sending the response message body](#Methodsforsendingtheresponsemessagebody)
    * 21. [6. Request redirection](#Requestredirection)
    * 22. [7. Set the cache time](#Setthecachetime)
    * 23. [8. Set timer refresh](#Settimerrefresh)
    * 24. [9. File Download](#FileDownload)
    * 25. [10. How to send data back to the client](#Howtosenddatabacktotheclient)
    * 26. [1. Overview](#Overview-1)
    * 27. [2. Related methods](#Relatedmethods)
    	* 27.1. [1. Get request line information](#Getrequestlineinformation)
    	* 27.2. [2. Methods related to obtaining request header information](#Methodsrelatedtoobtainingrequestheaderinformation)
    	* 27.3. [3. Methods related to getting request parameters](#Methodsrelatedtogettingrequestparameters)
    * 28. [3.HttpServletRequest share data](#HttpServletRequestsharedata)
    * 29. [4.Request.getRequestDispatcher("/test.jsp").forword(request,response)](#Request.getRequestDispatchertest.jsp.forwordrequestresponse)
    	* 29.1. [[‼️RequestDispatcher.forward vs Redirection](https://segmentfault.com/a/1190000038375532)](#RequestDispatcher.forwardvsRedirectionhttps:segmentfault.coma1190000038375532)
    	* 29.2. [[‼️Servlet – forward() and sendRedirect() Method With Example](https://www.geeksforgeeks.org/servlet-forward-and-sendredirect-method-with-example/)](#ServletforwardandsendRedirectMethodWithExamplehttps:www.geeksforgeeks.orgservlet-forward-and-sendredirect-method-with-example)
    * 30. [5. Streaming objects to obtain data](#Streamingobjectstoobtaindata)

<!-- vscode-markdown-toc-config
	numbering=true
	autoSave=true
	/vscode-markdown-toc-config -->
<!-- /vscode-markdown-toc -->

# 🚀Section 1.

### 1. <a name='Overview'></a>1. Overview

#### 1.1. <a name='aWhatisServlet'></a>a) What is Servlet?

- Servlet is an application running on the web server side, written in Java language
- Servlet object mainly encapsulates the processing of HTTP requests, its operation requires the support of the Servlet container (such as Tomcat)
- Servlet is managed by the Servlet container, Servlet container will be dynamically loaded on the server, and HTTP protocol-related Servlet use HTTP requests and HTTP responses to interact with the client

> As shown below, Servlet requests will first be received by HTTP servers (such as Apache), HTTP servers are only responsible for static HTML interface parsing, while Servlet requests are forwarded to the Servlet container, which will call the corresponding Servlet according to the request path and the mapping relationship between Servlets, which will return the processing results to the Servlet container and transmit the response to the client through the HTTP server

#### 1.2. <a name='bManualimplementationofServletprogram'></a>b) Manual implementation of Servlet program

1.  Write a class to implement the Servlet interface
2.  Implement service methods, process requests, and respond to data
3.  Go to web.xml to configure the servlet program's access address.

### 2. <a name='Servletinterface'></a>2. Servlet interface

> Servlet is a class that implements the Servlet interface, which is created and called by the Web server to receive and respond to user requests, with five abstract methods defined in the Servlet interface

| Method                                           | description                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                            |
| ------------------------------------------------ | ------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------ |
| void init(ServletConfig config)                  | After the Servlet is instantiated, the Servlet container calls the method to complete the initialization of the Servlet                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                |
| ServletConfig getServletConfig()                 | Get the configuration information of the Servlet object                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                |
| String getServletInfo()                          | Returns a string containing Servlet information, such as author, copyright, etc.                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                       |
| void service(ServletRequest req,ServletResponse) | Responsible for responding to user requests, when the container receives a request from the client to access the Servlet object will call this method. The **container will construct a ServletRequest object that represents the client request information and a ServletResponse object that is used to respond to the client as parameters passed to the service() method**, in the service() method you can get the relevant information about the client and the request information through the ServletRequest object, after After the request is processed, the method of ServletResponse object is called to set the corresponding information |
| void destroy()                                   | Responsible for releasing the resources occupied by the Servlet object, when the server is closed or the Servlet object When the server is shut down or the Servlet object is removed, the Servlet object will be destroyed, and this method will be called.                                                                                                                                                                                                                                                                                                                                                                                           |

Servlet class must be written to implement the Servlet interface or inherit the Servlet interface implementation class, such as **GenericServlet** and **HttpServlet**, both of which are **abstract** **classes** and HttpServlet inherits **GenericServlet**, which GenericServlet has an abstract method service (), and HttpServlet no abstract method

```java
public class ServletDemo02 extends HttpServlet {
@Override
protected void doGet(HttpServletRequest req,
HttpServletResponse resp) throws ServletException, IOException {
System.out.println("method executed");
}
@Override
protected void doPost(HttpServletRequest req,
HttpServletResponse resp) throws ServletException, IOException {
doGet(req,resp);
}
}
```

> Servlet thread safety problem: In the implementation of the Servlet class if there is a custom member variable, and in the service () method to manipulate this variable may be a thread safety problem, the solution is to change the member variable to a local variable defined inside the method, or use sychronized lock

Summarize the three ways to write Servlet class

1. implement the **Servlet interface**, you need to override all the methods in the Servlet interface
2. inherit **GenericServlet abstract class**, you must override the service () method, other methods can also be overridden
3. Inherit **HttpServlet abstract class**, do not need to override service() method, because HttpServlet in service() method calls another overloaded service() method, this overloaded method will be selected according to the parameters of the corresponding method, such as doGet () method or doPost () method, so we We can override doGet() and doPost() methods to achieve the desired function.
4. usually use inheritance of HttpServlet abstract class way to write Servlet

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

```xml
<?xml version="1.0" encoding="UTF-8"?>
<web-app xmlns="http://xmlns.jcp.org/xml/ns/javaee"
xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
xsi:schemaLocation="http://xmlns.jcp.org/xml/ns/javaee
http://xmlns.jcp.org/xml/ns/javaee/web-app_4_0.xsd"
version="4.0">
<! -- servlet tag to configure a servlet program for Tomcat -->
<servlet>
<! --servlet-name tag Servlet program with an alias (usually a class name) -->
<servlet-name>HelloServlet</servlet-name>
<! --servlet-class is the full class name of the Servlet program -->

<servlet-class>com.yuimorii.servlet.HelloServlet</servlet-class>
</servlet>
<! The --servlet-mapping tag configures access addresses for servlet programs -->

<servlet-mapping>
<! The purpose of the --servlet-name tag is to tell the server which Servlet application I am currently configuring to use the address -->
<servlet-name>HelloServlet</servlet-name>

<url-pattern>/hello</url-pattern>
</servlet-mapping>
</web-app>
```

##### c) url address to the Servlet program access

![- image-20221225150214855](https://github.com/itsyuimorii/Javaweb/blob/main/img/-%20image-20221225150214855.png)

### 3. <a name='ThelifecycleofServlet'></a>3. The life cycle of Servlet

[the entire life cycle of a servlet]: https://www.geeksforgeeks.org/life-cycle-of-a-servlet/

**Stages of the Servlet Life Cycle**: The Servlet life cycle mainly goes through four stages,

- Loading a Servlet.

- Initializing the Servlet.

- Request handling.

- Destroying the Servlet.

- Initialization phase:

![Frame 6](https://github.com/itsyuimorii/Javaweb/blob/main/img/Frame%206.png)

- When the client sends an HTTP request to the Servlet container to access the Servlet, the Servlet container first parses the request and checks whether the Servlet object is already in memory, if so, the Servlet object is used directly; if not, the Servlet instance object is created, and then by calling the init() method to complete the Servlet initialization. Note that in the entire life cycle of the Servlet, **its init() method is called only once. **

- Run phase:
  - The Servlet container creates the ServletRequest object representing the HTTP request and the ServletResponse object representing the HTTP response for the client request, and then passes them as parameters to the service() method of the Servlet. service() method from the ServletRequest object to get the client request and processing the request, through the ServletResponse object to generate the response. In the whole life cycle of the Servlet, for each access request of the Servlet, the Servlet container will call the service() method of the Servlet once and create new ServletRequest and ServletResponse objects. **That is, the service () method will be called several times throughout the life of the Servlet **
- Destruction phase
  - when the server is closed or the Web application is removed from the container, the Servlet with the destruction of the Web application and destroyed. Before destroying the Servlet, the Servlet container will call the Servlet destroy() method in order to allow the Servlet object to release the resources it occupies. **The destroy() method is also called only once during the entire life cycle of the Servlet. **Note that once created, the Servlet object resides in memory waiting for access from the client until the server is shut down or the Web application is removed from the container when the Servlet object is destroyed

##### e) The service method is dedicated to handling requests and responses

```java
public class HelloServlet implements Servlet {
/**
* service method is dedicated to handling requests and responses
* @param servletRequest
* @param servletResponse
* @throws ServletException
* @throws IOException
*/
@Override
public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws
ServletException, IOException {
System.out.println("3 service === Hello Servlet was visited");
// Type conversion (because it has getMethod() method)
HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
// The way to get the request
String method = httpServletRequest.getMethod();
  if ("GET".equals(method)) {
    doGet();
  } else if ("POST".equals(method)) {
    doPost();
  }
}
/**
* get operation
*/
public void doGet(){
	System.out.println("get request");
}
/**
* post operation
*/
public void doPost(){
	System.out.println("post request");
	}
}
```

### 4. <a name='Servletconfigure'></a>4. Servlet configure

In the web.xml file, registration is done through the <servlet></servlet> tag, which contains several subelements with the following functions

| element                        | type   | description                                                                                |
| ------------------------------ | ------ | ------------------------------------------------------------------------------------------ |
| <servlet-name></servletname>   | String | Specify the Servlet name, generally the same name as the Servlet class, requiring a unique |
| <servlet-class></servletclass> | String | Specify the location of the Servlet, generally the full class name                         |
| <description></description>    | String | Specify the description information of the Servlet                                         |
| <display-name></displayname>   | String | Specify the display name of the Servlet                                                    |

> Mapping the Servlet to a URL address, using <servlet-mapping></servlet-mapping>, and using <servlet-name></servlet-name> under the <servlet-mapping> element to indicate the name of the Servlet, which needs to be the same as the one previously registered under the <servlet> tag; using <url-pattern></url-pattern> to map the URL address, which must be preceded by /

```java
<servlet>
<!--指出Servlet名称-->
<servlet-name>ServletDemo01</servlet-name>
<!--指出Servlet全类名-->
<servlet-class>com.demo.ServletDemo01</servlet-class>
</servlet>
<servlet-mapping>
<!--和servlet标签下的名称一致-->
<servlet-name>ServletDemo01</servlet-name>
<!--映射地址，必须加斜杠-->
<url-pattern>/ServletDemo01</url-pattern>
</servlet-mapping>
```

> Configuring Servlets with Annotations

1. @WebServlet annotation is used to replace <servlet>, <servlet-mapping> and other tags in the web.xml file, the annotation will be processed by the container when the project is deployed, the container will deploy the corresponding class as a Servlet according to the specific property configuration, @WebServlet annotation common properties are as follows
1. Common attributes

### 5. <a name='ImplementingServletprogramsbyinheritingHttpServlet'></a>5. Implementing Servlet programs by inheriting HttpServlet

Generally in the actual project development, using the inheritance of the HttpServlet class to implement the Servlet program.

1. write a class to inherit the HttpServlet class
2. according to business needs to rewrite the doGet or doPost method
3. to the web.xml in the configuration of the Servlet program access address

| description                                   | method                                                                |
| --------------------------------------------- | --------------------------------------------------------------------- |
| User processing of HTTP requests of type GETl | protected void doGet(HttpServletRequestreq,HttpServletResponse resp)  |
| For handling POST type HTTP requests          | protected void doPost(HttpServletRequestreq,HttpServletResponse resp) |
| For handling PUT type HTTP requests           | protected void doPut(HttpServletRequestreq,HttpServletResponse resp)  |

![Screen Shot 2022-12-25 at 9.00.55 PM](https://github.com/itsyuimorii/Javaweb/blob/main/img/Screen%20Shot%202022-12-25%20at%209.00.55%20PM.png)

```java
//HelloServlet2
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
```

```java
//form.html
<!DOCTYPE html>
<html lang="zh_CN">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>
<form action="http://localhost:8080/06_Servlet/hello2" method="post">
    <input type="hidden" name="action" value="login" />
    <input type="hidden" name="username" value="root" />
    <input type="submit">
</form>
</body>
</html>
```

```xml
//web.xml
<?xml version="1.0" encoding="UTF-8"?>
<web-app xmlns="http://xmlns.jcp.org/xml/ns/javaee"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://xmlns.jcp.org/xml/ns/javaee http://xmlns.jcp.org/xml/ns/javaee/web-app_4_0.xsd"
         version="4.0">
<servlet>
    <servlet-name>HelloServlet2</servlet-name>
    <servlet-class>com.yuimorii.servlet.HelloServlet2</servlet-class>
</servlet>

 <servlet-mapping>
      <servlet-name>HelloServlet2</servlet-name>
      <url-pattern>/hello2</url-pattern>
 </servlet-mapping>
</web-app>
```

##### g) Use IDEA to create a Servlet program

```java
package com.yuimorii.servlet;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;

public class HelloServlet3 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("HelloServlet3 doPost");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("HelloServlet3 doGet");
    }
}
```

##### h) Servlet class inheritance system![servlet idea ](/Users/yuimorii/Documents/GitHub/Javaweb/img/servlet idea .jpg)

### 6. <a name='Servletcreationtiming'></a>6. Servlet creation timing

##### Created on the first visit

> Advantage: reduce the waste of server memory
> Disadvantage: If there are some initialization operations that need to be done when the application is loaded, they cannot be completed

##### Created when the server is loaded

> Advantage: create the object in advance to improve the efficiency of the first execution, you can complete some of the initialization work to be done when the application is loaded
> Disadvantages: more memory consumption of the server, affecting the efficiency of the server startup

Modify the timing of Servlet creation: add <load-on-startup></load-on-startup> tag to the <servlet> tag in the web.xml file, the middle of the tag is filled with an integer, positive integer means created when the server is loaded, the smaller the value the higher the priority, negative integer or not filled means created on the first visit

6. Default Servlet

- The default servlet is a servlet provided by the server, which is configured in the web.xml file in the conf directory of Tomcat.
- Its mapping path is <url-pattern>/</url-pattern>, and when receiving a request, it will first be set in the project's

**[⬆ back to top](#table-of-contents)**

# 🚀Section 2.ServletConfig

### 7. <a name='Overview-1'></a>1. Overview

- ServletConfig is an **interface**

- When Tomcat initializes a Servlet, it encapsulates the Servlet's configuration information into a ServletConfig object, and passes the ServletConfig object to the Servlet by calling the init(ServletConfig config) method. life cycle is the same as Servlet

- The configuration information encapsulated in ServletConfig is in the form of key-value pairsa) ServletConfig class three major roles

### 8. <a name='ServletConfigconfiguration'></a>2. ServletConfig configuration

| Method                               | description                                                                |
| ------------------------------------ | -------------------------------------------------------------------------- |
| String getInitParameter(String name) | Returns the corresponding parameter value according to the parameter name  |
| Enumeration getInitParameterNames()  | Returns an Enumeration object with all parameter names                     |
| ServletContext getServletContext()   | Returns the ServletContext object representing the current web application |
| String getServletName()              | Return the name of the Servlet                                             |

- The configuration of ServletConfig is also done in the web.xml file under the <servlet></servlet> tag, which requires the <init-param></init-param> tag to wrap the parameter name and parameter value to represent a key-value pair, where the <paramname></para- name> for the parameter name, <param-value></param-value> for the parameter value

- <init-param></init-param> for only one key-value pair

```java
<servlet>
<servlet-name>ServletConfigDemo</servlet-name>
<servlet-class>com.liaoxiangqian.ServletConfigDemo</servlet-class>
<! -- Configure the encoding parameter, using an init-param tag->
<init-param>
<param-name>encoding</param-name>
<param-value>UTF-8</param-value>
</init-param>
<! -- Configure the desc parameter, using an init-param tag -->
<init-param>
<param-name>desc</param-name>
<param-value>this is ServletConfigDemo</param-value>
</init-param>
</servlet>
<servlet-mapping>
<servlet-name>ServletConfigDemo</servlet-name>
<url-pattern>/ServletConfigDemo</url-pattern>
</servlet-mapping>

```

### 9. <a name='ServletConfigfunction'></a>ServletConfig function

- Get the value of servlet-name
- Get the initialization parameter init-param
- Get the ServletContext object

```java
 public void init(ServletConfig config) throws ServletException {
   System.out.println("2 init initialization method");
        // 1. you can get the value of servlet-name alias of Servlet program
        System.out.println("The alias of HelloServlet program is:" + servletConfig.getServletName());
        // 2. get the initialization parameter init-param
        System.out.println("The value of initialization parameter The value of username is;" + servletConfig.getInitParameter("username"));
        System.out.println("The value of the initialization parameter url is;" + servletConfig.getInitParameter("url"));
        // 3, get the ServletContext object
        System.out.println( servletConfig.getServletContext());}

```

> Override the init method inside must call the parent class init (ServiceConfig) operation

```java
public class HelloServlet2 extends HttpServlet {
    @Override
    public void init(ServletConfig config) throws ServletException {
        //The init method must be called
        super.init(config);
        System.out.println("Rewrote the init initialization method");
    }
```

# 🚀Section 3.ServletContext

### 10. <a name='Overview-1'></a>1. Overview

#### 10.1. <a name='aWhatisServletContext'></a>a) What is ServletContext?

- ServletContext is an interface

- Servlet container will create a **unique ServletContext object** for each Web application to represent the current Web application, ServletContext object encapsulates all the information of the current Web application

- ServletContext can be configured and get the global initialization parameters of the application, and can achieve data sharing between multiple Servlets

- life cycle: the application is loaded to create, the application stops to destroy

- ServletContext object is a domain object.

  - What is a domain object?

    > A domain object is an object that can access data like a Map, called a domain object. Here the domain refers to the scope of operations to access data, the whole web project.

| store data     | retrieve data  | delete data       |
| -------------- | :------------- | ----------------- |
| Put()          | Get()          | Remove()          |
| setAttribute() | GetAttribute() | RemoveAttribute() |

#### 10.2. <a name='bServletContextclassofthefourroles'></a>b) ServletContext class of the four roles

- to get the **web.xml configuration** of the context parameters context-param
- to get the current project path, the format: **/ project path**
- to get the absolute path on the server hard disk after deployment of the project
- Access data like Map

```java
protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
// 1. Get the context parameter context-param configured in web.xml
        ServletContext context = getServletConfig().getServletContext();
        String username = context.getInitParameter("username");
        System.out.println("context-param username ：" + username)。
        System.out.println("context-param password :" + context.getInitParameter("password"));

// 2. Get the current project path, format: /project path
        System.out.println( "Current project path:" + context.getContextPath() );

// 3. Get the absolute path of the project on the server hard disk after deployment
        /**
         * / slash is resolved by the server at :http://ip:port/工程名/ Mapped to the web directory of the IDEA code<br/>
         */
        System.out.println("The path of the project deployment is:" + context.getRealPath("/"));
        System.out.println("The absolute path of the css directory under the project is:" + context.getRealPath("/css"));
        System.out.println("The absolute path to the imgs directory 1.jpg under the project is:" + context.getRealPath("/imgs/1.jpg"));
    }
}
```

### 11. <a name='ServletContextconfiguration'></a>2. ServletContext configuration

- The <web-app> tag in the web.xml file is configured by the <context-param> tag, which has two sub-tags
- Sub-tag <param-name> indicates the key of the global initialization parameter
- The sub-tag <param-value> represents the value of the global initialization parameter

```java
<context-param>
<param-name>globalEncoding</param-name>
<param-value>UTF-8</param-value>
</context-param>
<context-param>
<param-name>globaleDesc</param-name>
<param-value>This is SevletContext</param-value>
</context-param>
```

### 12. <a name='ServletContextconfiguration-1'></a>3. ServletContext configuration

Get the initialization parameters of the Web application

1. the Enumeration getInitParameterName() method is used to return the Enumeration object containing all the parameter names
2. String getInitParameter(String name) method is used to get the parameter value according to the parameter name to achieve data sharing between multiple Servlets

| method                                    | description                                                                                                                   |
| ----------------------------------------- | ----------------------------------------------------------------------------------------------------------------------------- |
| Enumeration getAttributeNames()           | Returns an Enumeration object containing the names of all domain attributes                                                   |
| Object getAttribute(String name)          | Returns the domain attribute value based on the domain attribute name                                                         |
| void removeAttribute(String name)         | Remove the corresponding domain attribute from the ServletContext according to the domain attribute name                      |
| void setAttribute(String name,Object obj) | Set the domain attribute of the ServletContext, where name is the domain attribute name and obj is the domain attribute value |

> Note: The domain attribute refers to the data that can be shared, that is, can be accessed by multiple Servlets

- Read the resource files under the Web application
  - Actual development sometimes need to read some resource files in the Web application, for this reason ServletContext interface defines a number of methods to read Web resources, these methods rely on the Servlet container to achieve, according to the path of the resource file relative to the Web application, return the IO stream associated with the resource file, the absolute path of the resource file, etc.

| method                                       | description                                                                                                                                                                                                                                                                                                         |
| -------------------------------------------- | ------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| Set getResourcePaths(String path)            | return a Set collection, the set contains the path specified by path subdirectory names and file names but does not contain subdirectory file names, path with a slash / to indicate the root directory of the Web application                                                                                      |
| String getRealPath(String path)              | Return the real path of the resource file on the server file system, the parameter path indicates the path of the resource file relative to the web application, starting with a slash / indicates the root directory of the web application, if the virtual path cannot be converted to real path then return null |
| URL getResource(String path                  | Returns a URL object mapped to a resource file, the parameter must start with a slash / to indicate the root directory of the web application                                                                                                                                                                       |
| InputStream getResourceAsStream(String path) | Returns the InputStream object mapped to a resource file, with the same rules for passing path as getResource()                                                                                                                                                                                                     |

> Summary: The path in these four methods is actually the path relative to the web application, starting with a slash

# 🚀Section 4: HTTP protocols

### 13. <a name='WhatisHTTP'></a>1. What is HTTP?

The [Hypertext Transfer Protocol](https://reqbin.com/Article/HttpProtocol) (HTTP) is the core protocol of the World Wide Web. It is designed to support communication between a browser or an application and servers. HTTP protocol is used to send information in a format that both the client and the server can understand. HTTP works as a stateless request-response protocol between the client and the web server.

### 14. <a name='HTTPgetpostrequeststyle'></a>2. HTTP get/post request style

- Request line:

  - method

  - URL

  - Resource Identified

- Request header

- Request body

- Description of common request headers

  - Accept: Indicates the type of data that the client can receive

  - Accpet-Languege: Indicates the type of language that the client can receive

  - User-Agent: Indicates the information of the client's browser

  - Host: Indicates the IP and port number of the server where the request was made.

> **Server Response to HTTP GET Request**

```
GET / HTTP/1.1
  Host: reqbin.com
  Connection: keep-alive
  User-Agent: Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/79.0.3945.88 Safari/537.36
  Upgrade-Insecure-Requests: 1
  Accept: text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9
  Accept-Language: en-US,en;q=0.9
  Accept-Encoding: gzip, deflate
```

> **Server Response to HTTP POST Request**

```
POST /echo/post/json HTTP/1.1
Authorization: Bearer mt0dgHmLJMVQhvjpNXDyA83vA_Pxh33Y
Accept: application/json
Content-Type: application/json
Content-Length: 85
Host: reqbin.com

{
   "Id": 12345,
   "Customer": "John Smith",
   "Quantity": 1,
   "Price": 10.00
}
```

**Accept**: indicates the type of data that can be received by the client
**Accept-Language**: indicates the language type that can be accepted by the client
**Referer**: The address in the browser's address bar at the time of request initiation.
**User-Agent**: indicates the information of the browser
**Content-Type**: Indicates the type of the sent train

- **application/x-www-form-urlencoded**: Indicates that the submitted data format is: name=val ue&name=value, and then the url encoding of the selected line
  - **url** encoding is to replace the content parameter as: %xx%xx
  - **multipart/form-data**: indicates that the data is submitted to the server in the form of multipart (submitted as a stream, with sub-upload)

### 15. <a name='HTTPprotocolformatoftheresponse'></a>3. HTTP protocol format of the response

# 🚀Section 5. HttpServletResponse

### 16. <a name='Overview-1'></a>1. Overview

- HttpServletResponse interface inherits from ServletResponse interface
- Used to encapsulate the HTTP response message
- HTTP response message is divided into three parts: response line, response header, response body, so HttpServletResponse defines the response status code, response message header, response message body and other methods to send to the client

##### Description of the two output streams.

> ByteStream
> getOutputStream(); commonly used for downloading (passing binary data)

> CharacterStream
> getWriter(); commonly used for passing back strings (commonly used)

### 17. <a name='HTTPResponseStatusCodeDefinition'></a>2. HTTP Response Status Code Definition

https://developer.mozilla.org/en-US/docs/Web/HTTP/Status/302

| status | description                                                                                                                                                                                                          |
| ------ | -------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| 200    | Success                                                                                                                                                                                                              |
| 302    | **the resource requested has been temporarily moved to the URL given by the Location header**                                                                                                                        |
| 304    | **Not Modified** – the web page you requested hasn't changed since the last time you accessed it                                                                                                                     |
| 400    | **the server cannot or will not process the request due to something that is perceived to be a client error** (for example, malformed request syntax, invalid request message framing, or deceptive request routing) |
| 404    | **indicates that the server cannot find the requested resource**.                                                                                                                                                    |
| 500    | generic error response, it means**the server encountered an unexpected condition that prevented it from fulfilling the request**                                                                                     |
| 405    | **the server knows the request method, but the target resource doesn't support this method**                                                                                                                         |
|        |                                                                                                                                                                                                                      |

### 18. <a name='Methodsforsendingstatuscodes'></a>3. Methods for sending status codes

- void setStatus(int status) , set the status code of the response message and generate the response status line

- void sendError(int sc) , send a status code indicating an error message

- void sendError(int sc,String message) , set the status code of the error and also send an error message to the client, which contains the content of message

### 19. <a name='methodsforsendingresponsemessageheader'></a>4. methods for sending response message header

- void addHeader(String name,String value) , set the value of the name field specified in the response header, it will overwrite the original value of the name field

- void setHeader(String name,String value) , same function as addHeader() method, but will not overwrite the original value but add a value.

- void setContentLength(int len) , set the size of the entity content of the response header, the unit is bytes

- void setContentType(String type) , set the type of the response content, that is, the value of Content-Type, and the response content for the text type can also specify the character set encoding, such as text/html;charset=UTF-8

### 20. <a name='Methodsforsendingtheresponsemessagebody'></a>5. Methods for sending the response message body

- ServletOutputStream getOutputStream(), used to get the byte output stream object

- PrintWriter getWriter(), used to obtain the output stream object

- Note: When using these output streams may appear garbled, so you can use resp.setContentType("text/html;UTF-8"); to set the character set encoding, used to solve the problem of garbled code

### 21. <a name='Requestredirection'></a>6. Request redirection

Request redirection means that after **the server receives a request from the client, it may not be able to access the resource specified in the request due to certain conditions.**Let the client to access another specified resource

> **HttpServletResponse** defines a **sendRedirect()** method that generates a **304 response status code and Location responseheader**, which notifies the client to revisit the URL specified in the Location response header
> Method: void sendRedirect(String location)

location can use relative paths to redirect to other Servlets on the same Web server, or **absolute paths to redirect to other web servers**

Note: The redirect will regenerate another **HttpServletRequest objec**t, so it can not achieve the Servlet's shared data, such as If you need to share data then you can use request forwarding

The first option for request redirection.

```java
// Set the response status code 302 , indicating the redirect, (relocated)
resp.setStatus(302);
// set the response header to indicate where the new address is
resp.setHeader("Location", "http://localhost:8080");
```

Second option for redirects (recommended):

```java
resp.sendRedirect("http://localhost:8080");
```

### 22. <a name='Setthecachetime'></a>7. Set the cache time

For data that does not change frequently, you can **set the cache time to reduce frequent visits to the server and improve efficiency**
Use **void setDateHeader(String name,long time)** method to set the response header
Example:

```
resp.setDateHeader("Expires",System.currentTimeMills+16060*1000), set the cache time to 1 hour
```

### 23. <a name='Settimerrefresh'></a>8. Set timer refresh

Timed refresh is to set a certain time to jump to a certain page automatically
**Use void setHeader(String name,String value) method**
Example:

```
resp.setHeader("Refresh", "3;URL=/virtual directory/demo.html");
```

### 24. <a name='FileDownload'></a>9. File Download

[Example-code](https://github.com/itsyuimorii/Javaweb/blob/main/_07_Servlet/src/com/yuimorii/servlet/ServletDownloadDemo.java)

Implement the function of downloading a file in the browser with the following steps.

- Create a byte input stream and associate it with the file to be read
- Set the types supported by the response message header
- Set the response message header to open the resource as a download
- Get the byte output stream object through the response object
- Read and write in a loop
- Release the resource

### 25. <a name='Howtosenddatabacktotheclient'></a>10. How to send data back to the client

```java
public class ResponseIOServlet extends HttpServlet {
  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException,
  IOException {
  PrintWriter writer = resp.getWriter();
  writer.write("response's content!!!");
  }
}
```

# 🚀Section 6. HttpServletRequest

### 26. <a name='Overview-1'></a>1. Overview

Whenever a request comes into the Tomcat server, the Tomcat server will **parse the HTTP protocol information** and **encapsulate it in a Request object.** Then it is passed to the **service methods (doGet and doPost)** for us to use. We can get all the requested information through the HttpServletRequest object.

- HttpServletRequest interface **inherits from ServletRequest interface**

- HttpServletRequest is specifically used to **encapsulate HTTP request messages**

- HttpServletRequest provides a number of methods for accessing the request message, such as **getting the** **request line, getting the request heade**r, etc.

### 27. <a name='Relatedmethods'></a>2. Related methods

#### 27.1. <a name='Getrequestlineinformation'></a>1. Get request line information

- **GetRequestURI()-**-Get the requested resource path

- **GetRequestURL()-**-Get the requested uniform resource locator (absolute path)

- **String getRemoteHost()** --- This method is used to get the full **hostname of the client that sent the request,** or return the IP address if it cannot be resolved.

- **GetParameter()**--Get the requested parameters

- **GetParameterValues()** --Get the requested parameters (used when there are multiple values)

- **GetRequestDispatcher()** --Get the request dispatcher object

- **SetAttribute(key, value);** Setting the domain data

- **String getMethod()** - this method is used to get the request method in the HTTP request message, such as GET or Post

- **String getRequestURI()** -- this method is used to get the resource name part of the request line, i.e. the part of the URL after the host name and port number and before the parameter part

```java
  public class RequestAPIServlet extends HttpServlet {
      @Override
      protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
          System.out.println("URI => " + req.getRequestURI());
          System.out.println("URL => " + req.getRequestURL());
          System.out.println("Client ip address => " + req.getRemoteHost());
          System.out.println("Request Header User-Agent ==>> " + req.getHeader("User-Agent"));
          System.out.println( "Method of request ==>> " + req.getMethod() );
      }
  }
```

- **String getQueryString()** -- this method is used to get the parameter part of the request line, i.e. the resource path? The entire content after the number

- **String getProtocol()** -- This method is used to get the protocol name and version in the request line, such as HTTP/1.0 or HTTP/1.1

- **String getContextPath()** -- This method is used to get the path to the web application in the URL, starting with / and ending with no slash

- **String getServletPath()** - this method is used to get the path of the Servlet mapping or the name of the Servlet

- **String getRemoteAddr()**--This method is used to get the IP address of the client that sent the request.

- **int getRemotePort()** -- This method is used to get the port number of the client that sent the request.

- String getLocalAddr() -- This method is used to get the IP address of the network receiving the current request on the web server

- **String getLocalName()** -- This method is used to get the host name corresponding to the IP address of the network receiving the current request on the web server

- **int getLocalPort()** -- This method is used to get the port number on the web server that receives the current network connection

- **String getServerName(),** the method is used to obtain the host name to which the current request is directed, that is, the host name corresponding to the Host field in the HTTP request message

- **String getServerPort()**, the method is used to get the server port number connected to the current request, that is, the HTTP request message in the Host field corresponding to the port number part

- **String getSchema()**, this method is used to get the protocol name of the request, such as HTTP or HTTPS

- **StringBuffer getRequestURL(),** this method is used to get the complete URL of the request sent by the client, including the protocol, server name, port number, resource path, etc., but does not include the query parameters part of the latter, StringBuffer type is convenient to modify

```java
@WebServlet("/HttpServletRequestDemo")
public class HttpServletRequestDemo extends HttpServlet {
  @Override
  	protected void doGet(HttpServletRequest req, HttpServletResponse
  resp) throws ServletException, IOException {
  		String method = req.getMethod();
      System.out.println(method);
      String requestURI = req.getRequestURI();
      System.out.println(requestURI);
      String queryString = req.getQueryString();
      System.out.println(queryString);
      String protocol = req.getProtocol();
      System.out.println(protocol);
      String contextPath = req.getContextPath();
      System.out.println(contextPath);
      String servletPath = req.getServletPath();
      System.out.println(servletPath);
      String remoteAddr = req.getRemoteAddr();
      System.out.println(remoteAddr);
      String remoteHost = req.getRemoteHost();
      System.out.println(remoteHost);
      int remotePort = req.getRemotePort();
      System.out.println(remotePort);
      String localAddr = req.getLocalAddr();
      System.out.println(localAddr);
      String localName = req.getLocalName();
      System.out.println(localName);
      int localPort = req.getLocalPort();
      System.out.println(localPort);
      String serverName = req.getServerName();
      System.out.println(serverName);
      int serverPort = req.getServerPort();
      System.out.println(serverPort);
      String scheme = req.getScheme();
      System.out.println(scheme);
      StringBuffer requestURL = req.getRequestURL();
      System.out.println(requestURL);
  }
	@Override
		protected void doPost(HttpServletRequest req, HttpServletResponse
resp) throws ServletException, IOException {
			doGet(req,resp);
	}
}
```

#### 27.2. <a name='Methodsrelatedtoobtainingrequestheaderinformation'></a>2. Methods related to obtaining request header information

- **String getHeader(String name)** , the role of the method is to get the value of the specified name field in the request header, if there is no such field, then return null, if there are multiple fields of the specified name, then return the value of the first field

- Enumeration getHeaders(String name), return an Enumeration object, the object contains all the request header field values of the specified name

- **Enumeration getHeaderName()** , returns an Enumeration object containing all the fields of the request header

- **int getIntHeader(String name)**, the method gets the value of a specified request header field and converts the value of the field to int type and then returns, if the field does not exist then returns -1, if the value of the field cannot be converted to int type then throws a NumberFormatException

- **long getDateHeaders(String name)**, this method gets the value of a specified request header field and converts it to a long integer representing the date/time, which is the millisecond value from January 1, 1970, 0:00 minutes and 0 seconds to the present 6. String getContentType(), get the value of the Content-Type field 7. int getContentLength(), get the value of the Content-Length field 8. String getCharacterEncoding(), get the character set of the entity part of the request message encoding, usually taken from the Content-Type field

#### 27.3. <a name='Methodsrelatedtogettingrequestparameters'></a>3. Methods related to getting request parameters

- **String getParameter(String name)** --the method to get the parameter value of the specified name parameter, if there is no such parameter then return null, if the parameter does not set the value then return the empty string, if the parameter has more than one value then return the first

- **String getParameterValues(String name)**-- this method is used to get all values of the same parameter name

- **Enumeration getParameterNames()**-- the method is used to return the Enumeration object containing all parameter names

- **Map getParameterMap() **-- the parameter name and parameter values into a Map object to return

### 28. <a name='HttpServletRequestsharedata'></a>3.HttpServletRequest share data

HttpServletRequest not only can get some column data, but also can share and pass some data through attributes, such as those used in request forwarding and request inclusion methods

1. **void setAttribute(String name, Object obj)--** used to define an attribute, where name is the name of the attribute, obj is the value of the attribute, if the original attribute with the same name already exists, the original attribute will be deleted and then add the attribute, if obj is null, the attribute with the specified name will be deleted

2. **object getAttribute(String name)**--get the attribute value according to the attribute name

3. **void removeAttribute(String name)** --remove the attribute with the specified name

4. **Enumeration getAttributeNames()**--return an Enumeration object containing all the attribute names need to pay attention to the same request in order to share and pass the data

5. #### **Multipurpose Internet Mail Extensions** (**MIME**)

   _Multipurpose Internet Mail Extensions_ (_MIME_) is an Internet standard that extends the format of email messages to support text in character sets other than ...

### 29. <a name='Request.getRequestDispatchertest.jsp.forwordrequestresponse'></a>4.Request.getRequestDispatcher("/test.jsp").forword(request,response)

Servlets can jump between each other, if the function of a Servlet can not handle the client's request then you can use the jump to **pass the request to another Servlet,** by another Servlet to complete and respond to the client

##### Example

> https://github.com/itsyuimorii/Javaweb/blob/main/_07_Servlet/src/com/yuimorii/servlet/Servlet2.java > https://github.com/itsyuimorii/Javaweb/blob/main/_07_Servlet/src/com/yuimorii/servlet/Servlet1.java

**Related methods**

1. **RequestDispatcher getRequestDispatcher(String path)**, this method is provided by HttpServletRequest, used to obtain a RequestDispatcher object that encapsulates the resources specified by a path, the path must begin with a slash /, that is, the path is the need to forward to a Servlet path

2. **void forward(ServletRequest req, ServletResponse resp)**, this method is provided by RequestDispatcher, used to forward the request to another Web resource, such as a Servlet request contains a Servlet can not fully handle a request when another Servlet "included" to achieve functional merging and then respond to the client, then the browser's address bar will not send a change, the response header of the contained Servlet will be lost, because it is the first Servlet to respond

**Related methods**

1. **RequestDispatcher getRequestDispatcher(String path)**, the method root request forwarding the same, the path is the path of which Servlet needs to be included

2. **void include(ServletRequest req, ServletResponse resp)**, this method is provided by RequestDispatcher, used to achieve the function of including another Servlet

#### 29.1. <a name='RequestDispatcher.forwardvsRedirectionhttps:segmentfault.coma1190000038375532'></a>[‼️RequestDispatcher.forward vs Redirection](https://segmentfault.com/a/1190000038375532)

#### 29.2. <a name='ServletforwardandsendRedirectMethodWithExamplehttps:www.geeksforgeeks.orgservlet-forward-and-sendredirect-method-with-example'></a>[‼️Servlet – forward() and sendRedirect() Method With Example](https://www.geeksforgeeks.org/servlet-forward-and-sendredirect-method-with-example/)

### 30. <a name='Streamingobjectstoobtaindata'></a>5. Streaming objects to obtain data

- **BufferedReader getReader(),** the method to obtain a character input buffer stream
- **ServletInputStream getInputStream(),** the method to get a byte input stream
- Note that the data read by these two methods comes from the request body, so the GET method cannot get the data, while the POST method can
