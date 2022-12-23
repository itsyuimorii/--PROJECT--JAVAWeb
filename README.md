# Curriculum:

JavaSE (the standard version of the Java language, the most basic class library provided by Java)

- Java's development environment build
- The basic syntax of Java
- Object-oriented of Java
- Arrays
- Common classes
- Exceptions
- Collections
- Multi-threaded
- IO streams
- Reflection mechanism
- Annotation

WEB Backend

- Servlet (Server Applet: server-side Java applet)
- JSP
- AJAX
- jQuery
- MyBatis
- Spring
- SpringMVC
- SpringBoot
- SpringCloud

# Servlet

## System Architecture

1. ##### What forms of system architecture are included?
- C/S architecture
- B/S architecture

2. ##### C/S
- Client/Server
- Characteristics of C/S architecture: requires installation of specific client software.
- What are the advantages and disadvantages of a system with C/S architecture?

  > Advantages.
  - Fast (most of the data in the software is integrated into the client software, a very small amount of data is transferred from the server side, so the C/S architecture system is fast)
  - Good experience (fast and cool interface, of course the experience is good.)
  - Cool interface (special language to achieve the interface, more flexible.)
  - Low server pressure (because a lot of data is integrated in the client software, so the server only needs to transmit a small amount of data, so the server pressure is low.)
  - Security (because a lot of data is integrated in the client software, and there are many clients, although there is only one server, even if the server side of the earthquake, fire, the server is damaged, the problem is not big, because a lot of data on multiple clients have cache, there is storage, so from this aspect, the C / S structure of the system is safer.)
  - .....

  > Disadvantages

	- Upgrade maintenance is poor. (Upgrading maintenance is more troublesome. The cost is higher. Every client software needs to be upgraded. Some software is not so easy to install.)

3. ##### B/S
> Advantages.
- Easy to upgrade and maintain, relatively low cost. (Only the server side needs to be upgraded.)
- No specific client software needs to be installed, and it is extremely easy for users to operate. You only need to open a browser and enter the URL.

> Disadvantages.
- Slow speed (not because of the problem of low bandwidth, because all the data is on the server, each request sent by the user is required to respond to the server's full attention to the data, so the B / S structure of the system in the network to transmit a relatively large amount of data.)
- Poor experience (the interface is not so cool, because the browser only supports three languages HTML CSS JavaScript. in addition to the slow speed.)
- Insecure (All the data is on the server, as long as the server fire, earthquake and other force majeure, eventually all the data is lost.)

## WEB Server Software
- Tomcat (WEB server)
- jetty (WEB server)
- JBOSS (Application Server)
- WebLogic (Application Server)
- WebSphere (Application Server)

##### What is the relationship between application servers and web servers?
- The application server **implements all of the JavaEE specifications**. (JavaEE has 13 different specifications.)
- WEB server implements only the **two core specifications of Servlet + JSP in JavaEE.**
- This explanation shows: **the application server is included in the WEB server.**
- Students who have used JBOSS server should be very clear that JBOSS has a Tomcat server embedded in it.

#### Tomcat

For Tomcat server to run, you need to have jre first, so install JDK and configure java runtime environment first.
- For Tomcat server to run, you need to have jre first, so install JDK and configure java runtime environment first.
  - ```
    - JAVA_HOME=C:\Program Files\Java\jdk-17.0.1
    - PATH=%JAVA_HOME%\bin
    ```
  - Currently JAVA_HOME is not configured, think about a question, will this work? Currently it is fine to run only java programs. Is it really no problem?

##### Tomcat server directory

- bin : This directory is the directory where the command files of Tomcat server are stored, such as: start Tomcat, shut down Tomcat, etc.

- conf : This directory is the directory where the configuration files of Tomcat server are stored. (You can configure the port number in the server.xml file, the default Tomcat port is 8080)

- lib : This directory is the core program directory of Tomcat server, because Tomcat server is written in Java language, the jar packages here are all class files inside.

- logs: Tomcat server logs directory, Tomcat server startup and other information will be generated in this directory log files.

- temp: Temporary directory of Tomcat server. Store temporary files.

- webapps: this directory is used to store a large number of webapp (web application: web application)

- work: This directory is used to store the java files after translation of JSP files and class files after compilation.


##### What environment variables are needed to configure Tomcat server?

```
JAVA_HOME=root of JDK
CATALINA_HOME=root of Tomcat server
PATH=%JAVA_HOME%\bin;%CATALINA_HOME%\bin
```

##### Restart tomcat
```
	1 Type cd Library/Tomcat/bin to get into the bin directory of Tomcat
	2 Enter sudo chmod 755 *.sh to set permissions
	3 Type sudo sh startup.sh to start Tomcat, and type localhost:8080 in your browser to see the Tomcat home page.
	4 Type sudo sh shutdown.sh to shut down Tomcat.

```

## B/S 結構系統

What are the components in the whole BS structured system

- Browser software development team ( Google Chrome, Firefox, Internet Explorer ....)

- WEB Server development team (Tomcat, Jetty, WebLogic, JBOSS, WebSphere ....)

- DB Server development team (Oracle, MySQL .....)

- webapp development team (WEB applications are developed by us as JavaWEB programmers)

What specifications and protocols need to be followed between roles :

- There is a set of specifications between the webapp development team and the WEB Server development team: **the Servlet specification, one of the JavaEE specifications.**
  - What is the role of the Servlet specification?
    - The WEB Server and the webapp are decoupled.
- There is a set of transport protocol between Browser and WebServer: **HTTP protocol**. (Hypertext Transfer Protocol.)
- webapp development team and DB Server development team between a set of specifications: **JDBC specification.**

#### What is the Servlet specification?

- A webapp that follows the Servlet specification is a webapp that can be run on a different web server. (Because this webapp is following Servlet specification.)
- What does the Servlet specification include?
  - specification of which interfaces
  - specification of which classes
  - specification of a web application should have what configuration files
  - specification of the name of a web application configuration file
  - specification of a web application in the path of the configuration file
  - specify the content of the configuration file in a web application
  - specifies what the directory structure of a legal and valid web application should look like.
  - .....

## Develop a webapp with Servlet (Java applet)  

- What are the development steps?

  - Step 1: Create a new directory under the webapps directory named crm (this crm is the name of the webapp). Of course, it can also be other projects, such as banking projects, you can create a directory bank, office systems can create a oa.

    - Note: crm is the root of this webapp

  - Step 2: Create a new directory under the root of the webapp: WEB-INF

    - Note: the name of this directory is specified in the Servlet specification, must be all capitalized, must be exactly the same. A must must.

  - Step 3: In the WEB-INF directory under a new directory: classes

    - Note: The name of this directory must be all lowercase classes. this is also specified in the Servlet specification. In addition, this directory must be stored in the Java program compiled after the class file (here is stored in the byte code file).

  - Step 4: Create a new directory in the WEB-INF directory: lib

    - Note: This directory is not required. But if a webapp needs a third-party jar package, the jar package should be placed in this lib directory, the name of this directory can not be written arbitrarily, must be all lowercase lib. java language connection to the database, for example, the database driver jar package. Then this jar package must be placed in the lib directory. This Servlet specification.

  - Step 5: Create a new file in the WEB-INF directory: web.xml

    - Note: This file is required, the file name must be called web.xml. This file must be placed here. For a legitimate webapp, the web.xml file is required. The web.xml file is a configuration file in which the request path and the Servlet class are described against each other.

    - This file is best copied from other webapp, preferably not written by hand. It is not necessary. Copy and paste

    - ```xml
      <?xml version="1.0" encoding="UTF-8"? >
      
      <web-app xmlns="https://jakarta.ee/xml/ns/jakartaee"
        xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
        xsi:schemaLocation="https://jakarta.ee/xml/ns/jakartaee
                            https://jakarta.ee/xml/ns/jakartaee/web-app_5_0.xsd"
        version="5.0"
        metadata-complete="true">
      
      
      </web-app>
      
      ```

  - Step 6: Write a Java program, this small Java program can not be developed arbitrarily, this small java program must implement the Servlet interface.

    - This Servlet interface is not in the JDK. (Because Servlet is not JavaSE anymore. Servlet belongs to JavaEE, is another set of class libraries.)
    - Servlet interface (Servlet.class file) is provided by Oracle. (The most original is provided by sun.)
    - Servlet interface is a member of the JavaEE specification.
    - Tomcat server implements the Servlet specification, so Tomcat server also needs to use the Servlet interface. tomcat server should have this interface, tomcat server CATALINA_HOME\lib directory there is a servlet-api.jar, unzip this servlet-api .jar, you will see a Servlet.class file inside.
    - Key: Starting from JakartaEE9, the full name of the Servlet interface has changed: jakarta.servlet.Servlet
    - Note: When writing this Java applet, the java source code is wherever you want it to be, the location doesn't matter, you just need to put the class file in the classes directory after compiling the java source code.

  - Step 7: Compile the HelloServlet we wrote

    - Focus: How can you make your HelloServlet compile through it? Configure the environment variable CLASSPATH

      CLASSPATH=. ;C:\dev\apache-tomcat-10.0.12\lib\servlet-api.jar

    - Think about the question: Is there any relationship between the above configuration of CLASSPATH and the operation of Tomcat server?

      - There is no relationship, the above configuration of this environment variable is only to allow your HelloServlet to be compiled normally to generate class files.

  - Step 8: Copy the above compiled HelloServlet.class file to WEB-INF\classes directory.

  - Step 9: Write configuration information in the web.xml file, so that the "request path" and "Servlet class name" are associated together.

    - This step is described in technical terms: register the Servlet class in the web.xml file.

    - ```xml
      <?xml version="1.0" encoding="UTF-8"? >
      
      <web-app xmlns="https://jakarta.ee/xml/ns/jakartaee"
        xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
        xsi:schemaLocation="https://jakarta.ee/xml/ns/jakartaee
                            https://jakarta.ee/xml/ns/jakartaee/web-app_5_0.xsd"
        version="5.0"
        metadata-complete="true">
      
      	<! --servlet description information -->
      	<! --any-servlet-corresponds-to-a-servlet-mapping -->
      	<servlet>
      		<servlet-name>fdsafdsagfdsafdsa</servlet-name>
      		<! -- This location must be a fully qualified class name with a package name -->
      		<servlet-class>com.bjpowernode.servlet.HelloServlet</servlet-class
      	</servlet
      
      	<! --servlet mapping information -->
      	<servlet-mapping
      		<! -- This is also random, but write the same thing here as above. -->
      		<servlet-name>fdsafdsagfdsafdsa</servlet-name>
      		<! --> -- A path is needed here -->
      		<! -- The only requirement for this path is that it must start with / -->
      		<! --this current path can be written anywhere -->
      		<url-pattern>/fdsa/fd/saf/d/sa/fd/sa/fd</url-pattern>
      	</servlet-mapping>
      	
      </web-app>
      
      ```

      

  - Step 10: Start Tomcat server

  - Step 11: Open a browser and type a url into the browser address bar, this URL must be

    - http://127.0.0.1:8080/crm/fdsa/fd/saf/d/sa/fd/sa/fd   
    - One very important thing: the request path on the browser can not be written randomly, the request path must be consistent with the url-pattern in the web.xml file.
    - Note: The only difference between the request path on the browser and the url-pattern in the web.xml file is that the request path on the browser has the project name: /crm

  - If the path written on the browser is too complicated, you can use hyperlinks. (**Very important: html pages can only be placed outside the WEB-INF directory. **)

  - In the future, we do not need to write the main method. tomcat server is responsible for calling the main method, which is executed when the Tomcat server starts. We javaweb programmers just need to write the implementation class of the Servlet interface and then register it to the web.xml file.

  - To summarize: what should a legitimate webapp directory structure look like?

    ```
    webapproot
         |------WEB-INF
         		  |------classes(stores bytecode)
         		  ------lib(third-party jar packages)
         		  |------web.xml(Register Servlet)
         |------html
         |------css
         |------javascript
         |------image
         ....
    ````

  - What is the process of sending a request from the browser to the final server to call the method in the Servlet? (The following process is a very rough description. There are many steps that I have omitted.)

    - The user enters the URL, or directly clicks on the hyperlink: http://127.0.0.1:8080/crm/fdsa/fd/saf/d/sa/fd/sa/fd  
    - Then the Tomcat server receives the request and intercepts the path: /crm/fdsa/fd/saf/d/sa/fd/sa/fd  
    - Tomcat server finds the crm project
    - Tomcat server finds /fdsa/fd/saf/d/sa/fd/sa/fd in the web.xml file The corresponding Servlet is: com.bjpowernode.servlet.HelloServlet
    - Tomcat server creates the object com.bjpowernode.servlet.HelloServlet by reflection mechanism.
    - Tomcat server calls the service method of the com.bjpowernode.servlet.HelloServlet object.

##   
