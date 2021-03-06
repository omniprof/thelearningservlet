package com.omniprof.learningservlet;

/**
 * TheLearningServlet Web Application
 *
 * Prepared by Ken Fogel
 *
 * Twitter: @omniprof
 *
 * One bit of homework for you, look up what idempotent means. Then use it in a
 * sentence
 */
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Servlet implementation class TheLearningServlet
 *
 * The @WebServlet annotation defines this class as a servlet. The description
 * attribute is informative and this will only appear in the app server's
 * console. The default name of the servlet is the name of the class but with
 * the first letter made lowercase. In this class it would be
 * 'theLearningServlet'. You can change this, as I have, in the attribute
 * urlPatterns. You can have more than one name for a servlet. This is used when
 * the name used to access the servlet can act as a selector to determine what
 * is expected of the servlet as it can discover what name was used to call it.
 *
 */
@WebServlet(description = "Basic Servlet for learning", urlPatterns = {"/learning"})
public class TheLearningServlet extends HttpServlet {

    // There is basic logging in servlets but I prefer the more robust log4j 
    // logging framework. log4j is servlet friendly. The basic servlet
    // logging only works after the servlet is fully constructed so you could
    // not log in the servlet's constructor as I have below.
    private static final Logger LOG = LogManager.getLogger(TheLearningServlet.class);

    /**
     * Constructor
     *
     * Can only be used to initialize class variables or perform tasks before
     * the servlet comes into existence. Class variables are not thread safe so
     * should rarely be used and then only accessed in a synchronized block.
     * Cannot communicate with the servlet container therefore init is preferred
     * for initialization tasks. Generally not implemented.
     */
    public TheLearningServlet() {
        LOG.info(">>> Constructor <<<");
    }

    /**
     * The servlet initializer
     *
     * Called once the servlet is constructed. Can interact with the servlet
     * container by calling getServletContext(). Preferred method to initialize
     * anything that will be available to every thread.
     *
     * @throws javax.servlet.ServletException
     */
    @Override
    public void init() throws ServletException {
        LOG.info(">>> init <<<");
    }

    /**
     * Destructor
     *
     * Java does not have the automatic destructor of C++ only the less than
     * useful finalize(). This method is called by the server just before the
     * servlet is removed from memory. Here you can release any resources that
     * were available to all threads of the servlet that you likely created in
     * init().
     */
    @Override
    public void destroy() {
        LOG.info(">>> destroy <<<");
    }

    /**
     * getter for a String of information
     *
     * Returns information about the servlet, such as author, version, and
     * copyright that you can add. Usually called by the app server similar to
     * how the attribute 'description' is used in @WebServlet.
     *
     * @return
     */
    @Override
    public String getServletInfo() {
        LOG.info(">>> getServletInfo <<<");
        return "BasicServlet01 Version 2.0";
    }

    /**
     * Master method for all requests to this servlet
     *
     * Called by the container/app server to allow the servlet to respond to a
     * request. All it does in the super class is figure out which 'do' method
     * is required and proceeds to call it. It allows you to inspect the request
     * and response objects before calling the appropriate do method should you
     * need to. Though rarely overriden this method should end with a call to
     * the super service method unless you plan to do it all yourself.
     *
     * @param request
     * @param response
     * @throws javax.servlet.ServletException
     * @throws java.io.IOException
     */
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        LOG.info(">>> service <<<");
        super.service(request, response);
    }

    /**
     * Called by the server (via the service method) to allow a servlet to
     * handle a GET request. In a restful web service it is CRUD -> R
     *
     * Retrieves a resource from the server. Must be idempotent and safe. For
     * example, most form queries have no side effects. If a client request is
     * intended to change stored data, the request should use another HTTP
     * method. The contents of the query string is part of the address field so
     * bookmarks include the query string Maximum length between 2K and 8K
     * depending on server and browser though 4K is the average.
     *
     * @param request
     * @param response
     * @throws javax.servlet.ServletException
     * @throws java.io.IOException
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /**
         * Output from a Servlet sent to a browser is similar to writing to the
         * console. The HttpServletResponse object knows the path to the user's
         * browser. You begin by declaring the data type. In this example its
         * text/html. In a try-with-resources structure we create a PrintWriter
         * object. With this you can now write to the user's browser. When the
         * PrintWriter closes the text is sent to the browser.
         *
         * Note to pros: You do not have to explicitly flush the PrintWriter as
         * some StackOverflow answers suggest. It flushes when the underlying
         * Writer object has its close method called.
         */
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter writer = response.getWriter()) {
            writer.print(createHTMLString("GET"));
        }
        LOG.trace(">>> doGet <<<");
    }

    /**
     * Called by the server (via the service method) to allow a servlet to
     * handle a POST request. In a restful web service it is CRUD -> C
     *
     * Adds a resource to a server. Is not safe or idempotent. Operations
     * requested through POST can have side effects. The query string containing
     * data is in a different part of the http request so bookmarks do not
     * include the query string. No limit on the length.
     *
     * @param request
     * @param response
     * @throws javax.servlet.ServletException
     * @throws java.io.IOException
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // See doGet for understanding these lines
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter writer = response.getWriter()) {
            writer.print(createHTMLString("POST"));
        }
        LOG.info(">>> doPost <<<");
    }

    /**
     * Called by the server (via the service method) to allow a servlet to
     * handle a PUT request. In a restful web service it is CRUD -> U
     *
     * Updates an existing resource on the server. It is idempotent but not
     * safe. Used almost exclusively in restful web services.
     *
     * Cannot be called from an HTML form. Use curl to access it:
     *
     * curl -X PUT http://localhost:8080/TheLearningServlet/Learning
     *
     * @param request
     * @param response
     * @throws javax.servlet.ServletException
     * @throws java.io.IOException
     */
    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // See doGet for understanding these lines
        // One difference is that since this output can only be seen if you use 
        // curl to call this method the content type is plain text
        response.setContentType("text/plain;charset=UTF-8");
        try ( PrintWriter writer = response.getWriter()) {
            writer.print("You have called doPut");
        }
        LOG.info(">>> doPut <<<");
    }

    /**
     * Called by the server (via the service method) to allow a servlet to
     * handle a DELETE request. In a restful web service CRUD -> D
     *
     * Deletes a resource on the server. It is idempotent but not safe. Used
     * almost exclusively in restful web services.
     *
     * Cannot be called from an HTML form. Use curl to access it:
     *
     * curl -X DELETE http://localhost:8080/TheLearningServlet/Learning
     *
     * @param request
     * @param response
     * @throws javax.servlet.ServletException
     * @throws java.io.IOException
     */
    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // See doGet for understanding these lines
        // One difference is that since this output can only be seen if you use 
        // curl to call this method the content type is plain text
        response.setContentType("text/plain;charset=UTF-8");
        try ( PrintWriter writer = response.getWriter()) {
            writer.print("You have called doDelete");
        }
        LOG.info(">>> doDelete <<<");
    }

    /**
     * This is a utility method to display output. Initially I had this in doGet
     * and doPut but whenever I see code that is fundamentally identical in two
     * places I make it a method.
     *
     * @param methodName
     * @return
     */
    private String createHTMLString(String methodName) {
        String htmlStr = "<html><head><link rel='stylesheet' "
                + "href='styles/main.css' "
                + "type='text/css'/><title>The Learning Servlet</title></head>"
                + "<body><h1>" + methodName + "method</h1>"
                + "<br/><br/>"
                + "<form action = 'index.html'><label>Return to Home page</label>"
                + "<br/><button class = 'button'>Return</button></form>"
                + "</body></html>";
        return htmlStr;
    }
}
