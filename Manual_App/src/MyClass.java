import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class MyClass extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("Request Received!");

        /** This time , We were ran the project without using Tomcat server through IntelliJ IDEA.

         After configured the  module,

            1. Build >> Build module 'ManualApp'
            2. Build >> Build Artifacts >> 'manualApp' >> Build
            3. Then "out" folder will be created.
            4. Then goto that folder artifacts >> manualApp >>manualApp.war copy it.
            5. Then paste it to webApp folder of Tomcat Installation locations.
            6. Then Start Tomcat server manually and open the web browser and type "localhost:8080/manualApp.war" hit enter.

            Finally, Check the webApp folder. There will be a folder Named -"manualApp".
                In that folder,

                1.index.html - this is copy of the index file that we created here.

                2.META-INF - this contains a "web-tracker" file.

                3.WEB-INF - This contains two files.

                        I. web.xml file

                        II. classes folder - This contains "MyClass.class" class file that we created here.(copy)

         *
         * */
    }
}
