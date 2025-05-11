package testRunner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
       features = "src/test/resources/features",
        glue ={"steps","Hooks"},
//        tags = "@login",
        monochrome = true,
        dryRun = false,
        plugin = {"pretty","html:target/cucumber-reports/cucumber.html",
        "io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm",
        "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:target/extent-report/extentReport.html"}
)
public class TestNgRunner extends AbstractTestNGCucumberTests {
}
