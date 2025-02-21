
package SauceLabs.Slk;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.awt.desktop.SystemEventListener;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class AppTest {

    public static void main(String[] args) {
        // Setup Chrome options with desired capabilities
        ChromeOptions browserOptions = new ChromeOptions();
        browserOptions.setPlatformName("Windows 11"); // Set platform
        browserOptions.setBrowserVersion("latest");   // Set the browser version

        // Define Sauce Labs options (credentials and job details)
        Map<String, Object> sauceOptions = new HashMap<>();
        sauceOptions.put("username", "oauth-lokteja143-76606");  // Replace with your Sauce Labs username
        sauceOptions.put("accessKey", "a81cd1dc-2f5a-4fde-b749-c7012b3ad5d0");  // Replace with your Sauce Labs access key
        
       // sauceOptions.put("build", "your-build-id");  // Optionally specify a build ID
        //sauceOptions.put("name", "Your Test Name");  // Specify the name of the test
        browserOptions.setCapability("sauce:options", sauceOptions);  // Attach Sauce Labs options

        try {
            // Set up Remote WebDriver URL for Sauce Labs
           
			URL url = new URL("https://ondemand.eu-central-1.saucelabs.com:443/wd/hub");  // Sauce Labs URL

            // Create a Remote WebDriver session
            WebDriver driver = new RemoteWebDriver(url, browserOptions);

            // Run your test (Example: Open a website and validate the title)
            driver.get("https://saucedemo.com");
            String title = driver.getTitle();
            boolean titleIsCorrect = title.contains("Swag Labs");

            // Set job status based on test result
            String jobStatus = titleIsCorrect ? "passed" : "failed";

            // Send the test result to Sauce Labs (mark job as passed/failed)
          //  driver.executeScript("sauce:job-result=" + jobStatus);

            System.out.println(jobStatus);
            // Close the browser session
            driver.quit();

        } catch (Exception e) {
            // Handle any exceptions that occur during test execution
            e.printStackTrace();
        }
    }
}
