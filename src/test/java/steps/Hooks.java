package steps;

import io.cucumber.java.After;
import io.cucumber.java.Before;

public class Hooks {
    @Before
    public void setUp() {
        System.out.println("Setting up test environment...");
        // Initialize mock server or test data
    }

    @After
    public void tearDown() {
        System.out.println("Cleaning up test environment...");
        // Clean up test data or reset configurations
    }


}
