package testRunners;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
		//features = {"./features"}
		features = {"./features/login.feature"},
		//features = {"./features/login.feature","./features/register.feature" }
		//features = {"./target/rerun.txt"}
				plugin = {
				        "pretty",
				        "html:reports/cucumber-reports/cucumber.html",
				        "json:reports/cucumber-reports/cucumber.json",
				        "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"
				    },
		glue="stepDefinitions",
		publish=true,
		monochrome = true
)


public class StepRunner {

}
