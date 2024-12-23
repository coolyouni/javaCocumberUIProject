package runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        // Path to your feature files
        dryRun = false, features = {
       "src/test/resources/features/01_Registration.feature",
       "src/test/resources/features/02_Subscription.feature",
        "src/test/resources/features/03_LogIn.feature",
        "src/test/resources/features/04_CreateSubsite.feature",
        "src/test/resources/features/05_CreateUser.feature",
        "src/test/resources/features/06_Userrole.feature"
        "src/test/resources/features/07_CreateProfile.feature"
        
},
        glue = {"stepDefinitions"}, // Package where your step definitions are located
        plugin = {"pretty", "html:target/cucumber-html-reports/cucumber.html", // Adjusted to match desired output
                // folder
                // "json:target/cucumber-report.json" // Simplified file name
                "json:target/cucumber-report.json"},
        tags = "@endtoend or @sanity",
        monochrome = false // Makes the console output more readable
)

public class TestRunner extends AbstractTestNGCucumberTests {
    // You can override any method from AbstractTestNGCucumberTests if needed
}