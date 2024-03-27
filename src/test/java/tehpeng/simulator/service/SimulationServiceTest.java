package tehpeng.simulator.service;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import tehpeng.simulator.model.Car;

public class SimulationServiceTest {

  private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
  private final PrintStream standardOut = System.out;

  @BeforeEach
  public void setUp() {
    System.setOut(new PrintStream(outputStreamCaptor));
  }

  @AfterEach
  public void tearDown() {
    System.setOut(standardOut);
  }

  @Test
  void runStartScreen_PrintsWelcomeMessage() {
    // Arrange
    SimulationService simulationService = new SimulationService();
    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    System.setOut(new PrintStream(outputStream));

    // Act
    simulationService.runStartScreen();

    // Assert
    assertEquals("Welcome to Auto Driving Car Simulation!\n", outputStream.toString());
  }

  @Test
  void runInputBoundary_ReturnsInputBoundaryArray() {
    // Arrange
    SimulationService simulationService = new SimulationService();
    String input = "5 7"; // Example input
    InputStream inputStream = new ByteArrayInputStream(input.getBytes());
    Scanner scanner = new Scanner(inputStream);

    // Mock System.out for testing error messages
    System.setOut(new java.io.PrintStream(new java.io.ByteArrayOutputStream()));

    // Act
    String[] result = simulationService.runInputBoundary(scanner);

    // Assert
    assertArrayEquals(new String[] { "5", "7" }, result);
  }

  @Test
  void runInputBoundary_PrintsErrorMessageForInvalidInput() {
    // Arrange
    SimulationService simulationService = new SimulationService();
    String input = "abc def\n5 7"; // Invalid input followed by valid input
    InputStream inputStream = new ByteArrayInputStream(input.getBytes());
    Scanner scanner = new Scanner(inputStream);

    // Act
    simulationService.runInputBoundary(scanner);

    // Assert
    String expectedErrorMessage = "Please enter the width and height of the simulation field in x y format\n" +
        "=ERROR================================================\n" +
        "Please enter a correct dimension of x and y. (integer)\n" +
        "======================================================\n\n" +
        "Please enter the width and height of the simulation field in x y format\n";
    assertEquals(expectedErrorMessage, outputStreamCaptor.toString());
  }

  @Test
  void runInputOption_WhenValidInputProvided_ReturnsInputOption() {
    // Arrange
    SimulationService simulationService = new SimulationService();
    String input = "1"; // Simulate user input "1"
    InputStream inputStream = new ByteArrayInputStream(input.getBytes());
    Scanner scanner = new Scanner(inputStream);

    // Act
    String result = simulationService.runInputOption(scanner, new HashMap<>());

    // Assert
    assertEquals("1", result);
  }

  @Test
  void runInputOption_WhenInvalidInputProvidedThenValidInput_ReturnsValidInput() {
    // Arrange
    SimulationService simulationService = new SimulationService();
    String input = "a\n2"; // Simulate invalid input "a" followed by valid input "2"
    InputStream inputStream = new ByteArrayInputStream(input.getBytes());
    Scanner scanner = new Scanner(inputStream);

    // Act
    String result = simulationService.runInputOption(scanner, new HashMap<>());

    // Assert
    assertEquals("2", result);
  }

  @Test
  void runInputOption_WithValidInput_ReturnsInputOption() {
    // Arrange
    String input = "2\n"; // Simulate user input "2" (valid option)
    InputStream inputStream = new ByteArrayInputStream(input.getBytes());
    System.setIn(inputStream); // Redirect System.in to provide input

    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    PrintStream originalOut = System.out;
    System.setOut(new PrintStream(outputStream)); // Redirect System.out to capture output

    Scanner scanner = new Scanner(System.in);
    HashMap<String, Car> carMap = new HashMap<>();

    // Act
    SimulationService simulationService = new SimulationService();
    String result = simulationService.runInputOption(scanner, carMap);

    // Restore original System.in and System.out
    System.setIn(System.in);
    System.setOut(originalOut);

    // Assert
    assertEquals("2", result.trim()); // Expecting "2" as the user input
    // Since no cars are present, we shouldn't see the car details
    assertEquals("\n\n\n" +
        "Please choose from the following options:\n" +
        "[1] Add a car to the field\n" +
        "[2] Run simulation\n", outputStream.toString());
  }

  @Test
  void runInputOption_WithValidInputAndCarExist_ReturnsInputOption() {
    // Arrange
    String input = "2\n"; // Simulate user input "2" (valid option)
    InputStream inputStream = new ByteArrayInputStream(input.getBytes());
    System.setIn(inputStream); // Redirect System.in to provide input

    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    PrintStream originalOut = System.out;
    System.setOut(new PrintStream(outputStream)); // Redirect System.out to capture output

    Scanner scanner = new Scanner(System.in);
    HashMap<String, Car> carMap = new HashMap<>();
    Car car = new Car("TestCar", 1, 2, 5, 5, 0, "FRL");
    carMap.put(car.getName(), car);

    // Act
    SimulationService simulationService = new SimulationService();
    String result = simulationService.runInputOption(scanner, carMap);

    // Restore original System.in and System.out
    System.setIn(System.in);
    System.setOut(originalOut);

    // Assert
    assertEquals("2", result.trim()); // Expecting "2" as the user input
    // Since no cars are present, we shouldn't see the car details
    assertEquals("\n\n\n" +
        "Your current list of cars are:\n" +
        "- TestCar, (1,2) N, [F, R, L]\n" +
        "Please choose from the following options:\n" +
        "[1] Add a car to the field\n" +
        "[2] Run simulation\n", outputStream.toString());
  }

  @Test
  void runInputOption_WithInvalidInputThenValidInput_ReturnsValidInput() {
    // Arrange
    String input = "3\n2\n"; // Simulate user input "3" (invalid) then "2" (valid option)
    InputStream inputStream = new ByteArrayInputStream(input.getBytes());
    System.setIn(inputStream); // Redirect System.in to provide input

    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    PrintStream originalOut = System.out;
    System.setOut(new PrintStream(outputStream)); // Redirect System.out to capture output

    Scanner scanner = new Scanner(System.in);
    HashMap<String, Car> carMap = new HashMap<>();

    // Act
    SimulationService simulationService = new SimulationService();
    String result = simulationService.runInputOption(scanner, carMap);

    // Restore original System.in and System.out
    System.setIn(System.in);
    System.setOut(originalOut);

    // Assert
    assertEquals("2", result.trim()); // Expecting "2" as the user input
    // Expecting the error message for the invalid input followed by the valid
    // options
    assertEquals("\n\n\n" +
        "Please choose from the following options:\n" +
        "[1] Add a car to the field\n" +
        "[2] Run simulation\n" +
        "=ERROR===============================================\n" +
        "You have entered a wrong option. Selected option: 3\n" +
        "Please choose a correct option of 1 or 2\n" +
        "=====================================================\n\n" +
        "Please choose from the following options:\n" +
        "[1] Add a car to the field\n" +
        "[2] Run simulation\n", outputStream.toString());
  }

  @Test
  void runInputCarName_ValidInput_ReturnsTrimmedInput() {
    // Arrange
    String input = "Car1\n"; // Simulate user input "Car1" (valid name)
    InputStream inputStream = new ByteArrayInputStream(input.getBytes());
    System.setIn(inputStream); // Redirect System.in to provide input

    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    PrintStream originalOut = System.out;
    System.setOut(new PrintStream(outputStream)); // Redirect System.out to capture output

    Scanner scanner = new Scanner(System.in);

    // Act
    SimulationService simulationService = new SimulationService();
    String result = simulationService.runInputCarName(scanner);

    // Restore original System.in and System.out
    System.setIn(System.in);
    System.setOut(originalOut);

    // Assert
    assertEquals("Car1", result); // Expecting "Car1" as the user input
    // Since input is valid, we shouldn't see any error message

    assertEquals("\n\n\n" +
        "Please enter the name of the car:\n", outputStream.toString());
  }

  @Test
  void runInputCarName_InvalidInputThenValidInput_ReturnsTrimmedInput() {
    // Arrange
    String input = " \ntest"; // Simulate user input "invalid" (invalid name) then "Car2" (valid name)
    InputStream inputStream = new ByteArrayInputStream(input.getBytes());
    System.setIn(inputStream); // Redirect System.in to provide input

    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    PrintStream originalOut = System.out;
    System.setOut(new PrintStream(outputStream)); // Redirect System.out to capture output

    Scanner scanner = new Scanner(System.in);

    // Act
    SimulationService simulationService = new SimulationService();
    String result = simulationService.runInputCarName(scanner);

    // Restore original System.in and System.out
    System.setIn(System.in);
    System.setOut(originalOut);

    // Assert
    assertEquals("test", result); // Expecting "Car2" as the user input
    // Since input is invalid first, followed by valid input, we shouldn't see the
    // error message after the first attempt
    assertEquals("\n\n\n" +
        "Please enter the name of the car:\n" +
        "=ERROR======================\n" +
        "Please give your car a name.\n" +
        "============================\n" +
        "\n" +
        "Please enter the name of the car:\n", outputStream.toString());
  }

  @Test
  void runInputCommand_ValidCommandEntered_ReturnsInputCommand() {
    // Arrange
    ByteArrayInputStream inputStream = new ByteArrayInputStream("FRL".getBytes());
    System.setIn(inputStream);
    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    System.setOut(new PrintStream(outputStream));
    Scanner scanner = new Scanner(System.in);
    SimulationService simulationService = new SimulationService();

    // Act
    String inputCarName = "Car1";
    String inputCommand = simulationService.runInputCommand(scanner, inputCarName);

    // Assert
    assertEquals("FRL", inputCommand);
  }

  @Test
  void runInputCommand_InvalidCommandEntered_PrintsErrorMessageAndRetries() {
    // Arrange
    String invalidCommandInput = "invalid\nFF\n";
    ByteArrayInputStream inputStream = new ByteArrayInputStream(invalidCommandInput.getBytes());
    System.setIn(inputStream);
    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    System.setOut(new PrintStream(outputStream));

    Scanner scanner = new Scanner(System.in);
    SimulationService simulationService = new SimulationService();

    // Act
    String inputCarName = "Car1";
    simulationService.runInputCommand(scanner, inputCarName);

    // Assert
    String expectedErrorMessage = "\n\n\n" +
        "Please enter the commands for car Car1:\n" +
        "=ERROR==================\n" +
        "Please enter the command\n" +
        "========================\n\n" +
        "Please enter the commands for car Car1:\n";
    assertEquals(expectedErrorMessage, outputStream.toString());
  }

  @Test
  void runInputCarPosition_ValidInput_ReturnsSplitInput() {
    // Arrange
    String input = "1 2 N\n"; // Simulate user input "1 2 N" (valid position)
    InputStream inputStream = new ByteArrayInputStream(input.getBytes());
    System.setIn(inputStream); // Redirect System.in to provide input

    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    PrintStream originalOut = System.out;
    System.setOut(new PrintStream(outputStream)); // Redirect System.out to capture output

    Scanner scanner = new Scanner(System.in);

    // Act
    SimulationService simulationService = new SimulationService();
    String[] result = simulationService.runInputCarPosition(scanner, 5, 5, "Car1");

    // Restore original System.in and System.out
    System.setIn(System.in);
    System.setOut(originalOut);

    // Assert
    assertArrayEquals(new String[] { "1", "2", "N" }, result); // Expecting {"1", "2", "N"} as the split input
    // Since input is valid, we shouldn't see any error message
    assertEquals("\n\n\n" +
        "Please enter initial Position of car Car1 in x y Direction format:\n",
        outputStream.toString());
  }

  @Test
  void runInputCarPosition_InvalidInputThenValidInput_ReturnsSplitInput() {
    // Arrange
    String input = "invalid\n2 3 E\n"; // Simulate user input "invalid" (invalid position) then "2 3 E" (valid
                                       // position)
    InputStream inputStream = new ByteArrayInputStream(input.getBytes());
    System.setIn(inputStream); // Redirect System.in to provide input

    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    PrintStream originalOut = System.out;
    System.setOut(new PrintStream(outputStream)); // Redirect System.out to capture output

    Scanner scanner = new Scanner(System.in);

    // Act
    SimulationService simulationService = new SimulationService();
    String[] result = simulationService.runInputCarPosition(scanner, 5, 5, "Car2");

    // Restore original System.in and System.out
    System.setIn(System.in);
    System.setOut(originalOut);

    // Assert
    assertArrayEquals(new String[] { "2", "3", "E" }, result); // Expecting {"2", "3", "E"} as the split input
    // Since input is invalid first, followed by valid input, we shouldn't see the
    // error message after the first attempt
    assertEquals("\n\n\n" + "Please enter initial Position of car Car2 in x y Direction format:\n" +
        "=ERROR================================================================\n" +
        "Please fill correct format. (x y n). ; y=y-axis; n=direction\n" +
        "x = x-axis    options: (integer)\n" +
        "y = y-axis    options: (integer)\n" +
        "n=direction   options: (N / S / E / W)\n" +
        "======================================================================\n" +
        "\n" +
        "Please enter initial Position of car Car2 in x y Direction format:\n", outputStream.toString());
  }

  @Test
  void runSimulation_NoCars_ReturnsFalse() {
    // Arrange
    HashMap<String, Car> lsCarMap = new HashMap<>();
    SimulationService simulationService = new SimulationService();

    // Act
    boolean result = simulationService.runSimulation(lsCarMap, 5, 5);

    // Assert
    assertFalse(result); // Expecting false since there are no cars
  }

  @Test
  void runSimulation_WithCars_ReturnsTrue() {
    // Arrange
    HashMap<String, Car> lsCarMap = new HashMap<>();
    Car car = new Car("TestCar", 1, 2, 5, 5, 0, "FRL");
    lsCarMap.put(car.getName(), car);
    SimulationService simulationService = new SimulationService();

    // Act
    boolean result = simulationService.runSimulation(lsCarMap, 5, 5);

    // Assert
    assertTrue(result); // Expecting true since there are cars
  }

  @Test
  public void testRunInputBoundary() {
    // Simulate user input
    String userInput = "10 10";
    Scanner mockScanner = new Scanner(userInput);

    SimulationService simulationService = new SimulationService();
    String[] boundaries = simulationService.runInputBoundary(mockScanner);

    // Assert boundary values
    assertEquals(10, Integer.parseInt(boundaries[0]));
    assertEquals(10, Integer.parseInt(boundaries[1]));

    // Close the scanner (important for resource management)
    mockScanner.close();
  }

  @Test
  void runDisplayCarDetailsScreenAfterSimulation_ValidInput_PrintsCorrectOutput() {
    // Arrange
    HashMap<String, Car> lsCarMap = new HashMap<>();
    Car car1 = new Car("TestCar", 1, 2, 5, 5, 0, "FRL");
    car1.setCurrCoordinate(new Integer[] { 3, 4 });
    car1.setCurrDirection(0);
    Car car2 = new Car("car2", 1, 2, 5, 5, 0, "FRL");
    car2.setCurrCoordinate(new Integer[] { 5, 6 });
    car2.setCurrDirection(2);
    car2.setCollideWith(List.of("Car3"));
    lsCarMap.put("Car1", car1);
    lsCarMap.put("Car2", car2);
    ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    System.setOut(new PrintStream(outContent));
    SimulationService simulationService = new SimulationService();

    // Act
    simulationService.runSimulationResult(lsCarMap);

    // Assert
    assertTrue(outContent.toString().contains("Your current list of cars are:"));
    assertTrue(outContent.toString().contains("After simulation, the result is:"));
  }

  @Test
  void runSimulationResult_CallsDisplayMethods() {
    // Arrange
    HashMap<String, Car> lsCarMap = new HashMap<>();
    lsCarMap.put("Car1", new Car("Car1", 0, 0, 5, 5, 0, "FF"));
    SimulationService simulationService = new SimulationService();
    ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    System.setOut(new PrintStream(outContent));

    // Act
    simulationService.runSimulationResult(lsCarMap);

    // Assert
    assertTrue(outContent.toString().contains("Your current list of cars are:"));
    assertTrue(outContent.toString().contains("After simulation, the result is:"));
  }

  @Test
  void runInputEndingOption_ValidOption_ReturnsOption() {
    // Arrange
    String simulatedUserInput = "1\n";
    InputStream inputStream = new ByteArrayInputStream(simulatedUserInput.getBytes());
    System.setIn(inputStream);
    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    System.setOut(new PrintStream(outputStream));
    Scanner scanner = new Scanner(System.in);
    SimulationService simulationService = new SimulationService();

    // Act
    String inputEndingOption = simulationService.runInputEndingOption(scanner);

    // Assert
    assertEquals("1", inputEndingOption);
  }

  @Test
  void runInputEndingOption_InvalidOption_ReturnsOption() {
    // Arrange
    String simulatedUserInput = "3\n2\n";
    InputStream inputStream = new ByteArrayInputStream(simulatedUserInput.getBytes());
    System.setIn(inputStream);
    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    System.setOut(new PrintStream(outputStream));
    Scanner scanner = new Scanner(System.in);
    SimulationService simulationService = new SimulationService();

    // Act
    String inputEndingOption = simulationService.runInputEndingOption(scanner);

    // Assert
    assertEquals("2", inputEndingOption);
    assertTrue(outputStream.toString().contains("=ERROR================================================="));
    assertTrue(outputStream.toString().contains("You have entered a wrong option."));
    assertTrue(outputStream.toString().contains("Please choose a correct option of 1 or 2"));
  }

  @Test
  void runExitScreen_PrintsExitMessage() {
    // Arrange
    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    System.setOut(new PrintStream(outputStream));
    SimulationService simulationService = new SimulationService();

    // Act
    simulationService.runExitScreen();

    // Assert
    assertEquals("\n\n\nThank you for running the simulation. Goodbye!\n", outputStream.toString());
  }
}
