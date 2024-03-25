package tehpeng.simulator.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Scanner;

import org.junit.jupiter.api.Test;

public class SimulationServiceTest {
  @Test
  public void testRunInputBoundaryWithValidInput() {
    // Prepare input for the scanner
    String input = "5 5\n"; // Example: "5 5" represents width and height

    // Set the input stream to the prepared input
    InputStream inputStream = new ByteArrayInputStream(input.getBytes());
    System.setIn(inputStream);

    // Create a new scanner with the prepared input stream
    Scanner scanner = new Scanner(System.in);

    // Call the method to test
    SimulationService simulationService = new SimulationService();
    String[] result = simulationService.runInputBoundary(scanner);

    // Verify the result
    assertNotNull(result);
    assertEquals(2, result.length);
    assertEquals("5", result[0]);
    assertEquals("5", result[1]);
  }

}
