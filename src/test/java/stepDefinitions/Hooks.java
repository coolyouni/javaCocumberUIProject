package stepDefinitions;

import commonUtils.DriverUtil;
import listeners.TestListener;
import io.cucumber.java.After;
import io.cucumber.java.AfterAll;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.WebDriver;

public class Hooks {
	private TestListener testListener;
	private WebDriver driver;

	@Before
	public void setUp(Scenario scenario) {
		// Initialize the WebDriver before each scenario
		driver = DriverUtil.getDefaultDriver();

		// Initialize the listener and set the driver
		testListener = new TestListener();
		testListener.setDriver(driver);

		// Log the starting scenario
		logScenarioStart(scenario);
	}

	private void logScenarioStart(Scenario scenario) {
		System.out.println("Starting scenario: " + scenario.getName());
	}

	@After
	public void tearDown(Scenario scenario) {
		// Log if the scenario failed
		if (scenario.isFailed()) {
			testListener.onTestFailure(scenario); // Capture screenshot for failed scenarios
			System.out.println("Scenario failed: " + scenario.getName());
		} else {
			System.out.println("Scenario passed: " + scenario.getName());
		}

	}

	@AfterAll
	public static void afterAll() {
		// Close the WebDriver session after all scenarios finish
          DriverUtil.closeDriver();
	}
}
