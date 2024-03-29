package tehpeng.simulator.service;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

import org.junit.jupiter.api.Test;

import tehpeng.simulator.model.Car;

public class SimulationServiceTest {

  @Test
  void givenVoid_whenrunStartScreen_PrintWelcomeMessage() {
    // Give
    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    System.setOut(new PrintStream(outputStream));

    // When
    SimulationService simulationService = new SimulationService();
    simulationService.runStartScreen();

    // Assert
    String expectedMessage = "Welcome to Auto Driving Car Simulation!\n";
    assertEquals(expectedMessage, outputStream.toString());
  }

  @Test
  void givenValidBoundary_whenrunInputBoundary_ReturnInputBoundaryArray() {
    // Given
    String input = "5 7"; // Example input
    InputStream inputStream = new ByteArrayInputStream(input.getBytes());
    Scanner scanner = new Scanner(inputStream);

    // When
    SimulationService simulationService = new SimulationService();
    String[] result = simulationService.runInputBoundary(scanner);

    // Assert
    assertArrayEquals(new String[] { "5", "7" }, result);
  }

  @Test
  void givenInvalidBoundary_whenrunInputBoundary_PrintErrorMessageAndRetry() {
    // Given
    String input = "abc def\n5 7"; // Invalid input followed by valid input
    InputStream inputStream = new ByteArrayInputStream(input.getBytes());
    Scanner scanner = new Scanner(inputStream);

    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    System.setOut(new PrintStream(outputStream)); // Redirect System.out to capture output

    // When
    SimulationService simulationService = new SimulationService();
    simulationService.runInputBoundary(scanner);

    // Assert
    String expectedErrorMessage = "Please enter the width and height of the simulation field in x y format\n" +
        "=ERROR================================================\n" +
        "Please enter a correct dimension of x and y. (integer)\n" +
        "======================================================\n\n" +
        "Please enter the width and height of the simulation field in x y format\n";
    assertEquals(expectedErrorMessage, outputStream.toString());
  }

  @Test
  void givenValidOption_whenrunInputOption_ReturnInputOption() {
    // Given
    String input = "1"; // Simulate user input "1"
    InputStream inputStream = new ByteArrayInputStream(input.getBytes());
    Scanner scanner = new Scanner(inputStream);

    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    System.setOut(new PrintStream(outputStream)); // Redirect System.out to capture output

    // When
    SimulationService simulationService = new SimulationService();
    String result = simulationService.runInputOption(scanner, new HashMap<>());

    // Assert
    assertEquals("1", result);
    assertEquals("\n\n\n" +
        "Please choose from the following options:\n" +
        "[1] Add a car to the field\n" +
        "[2] Run simulation\n", outputStream.toString());
  }

  @Test
  void givenValidOption_whenrunInputOption_PrintErrorMessageAndRetry() {
    // Given
    SimulationService simulationService = new SimulationService();
    String input = "a\n2"; // Simulate invalid input "a" followed by valid input "2"
    InputStream inputStream = new ByteArrayInputStream(input.getBytes());
    Scanner scanner = new Scanner(inputStream);

    // When
    String result = simulationService.runInputOption(scanner, new HashMap<>());

    // Assert
    assertEquals("2", result);
  }

  @Test
  void givenAddAnotherCarAfterValidOption_whenrunInputOption_ReturnCarAAndPromptOption() {
    // Given
    String input = "2"; // Simulate user input "2" (valid option)
    InputStream inputStream = new ByteArrayInputStream(input.getBytes());
    Scanner scanner = new Scanner(inputStream);

    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    System.setOut(new PrintStream(outputStream)); // Redirect System.out to capture output

    HashMap<String, Car> carMap = new HashMap<>();
    Car car = new Car("A", 1, 2, 0, "FFRFFFFRRL");
    carMap.put(car.getName(), car);

    // When
    SimulationService simulationService = new SimulationService();
    String result = simulationService.runInputOption(scanner, carMap);

    // Assert
    assertEquals("2", result.trim()); // Expecting "2" as the user input
    // Since no cars are present, we shouldn't see the car details
    assertEquals("\n\n\n" +
        "Your current list of cars are:\n" +
        "- A, (1,2) N, [F, F, R, F, F, F, F, R, R, L]\n" +
        "Please choose from the following options:\n" +
        "[1] Add a car to the field\n" +
        "[2] Run simulation\n", outputStream.toString());
  }

  @Test
  void givenValidCarName_whenrunInputCarName_ReturnNameTrimmed() {
    // Given
    String input = "A  \n"; // Simulate user input "Car1" (valid name)
    InputStream inputStream = new ByteArrayInputStream(input.getBytes());
    System.setIn(inputStream); // Redirect System.in to provide input

    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    PrintStream originalOut = System.out;
    System.setOut(new PrintStream(outputStream)); // Redirect System.out to capture output

    Scanner scanner = new Scanner(System.in);

    // When
    SimulationService simulationService = new SimulationService();
    String result = simulationService.runInputCarName(scanner);

    // Assert
    assertEquals("A", result); // Expecting "A" as the user input

    assertEquals("\n\n\n" +
        "Please enter the name of the car:\n", outputStream.toString());
  }

  @Test
  void giveValidCarNameAfterInvalidCarNAme_whenrunInputCarName_PrintErrorMessageAndRetry() {
    // Given
    String input = " \nA"; // Simulate user input empty car name
    InputStream inputStream = new ByteArrayInputStream(input.getBytes());
    System.setIn(inputStream); // Redirect System.in to provide input

    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    System.setOut(new PrintStream(outputStream)); // Redirect System.out to capture output

    Scanner scanner = new Scanner(System.in);

    // When
    SimulationService simulationService = new SimulationService();
    String result = simulationService.runInputCarName(scanner);

    // Assert
    assertEquals("A", result); // Expecting "A" as the user input
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
  void givenValidCommand_whenrunInputCommand_ReturnsInputCommand() {
    // Given
    ByteArrayInputStream inputStream = new ByteArrayInputStream("FRL".getBytes());
    System.setIn(inputStream);
    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    System.setOut(new PrintStream(outputStream));
    Scanner scanner = new Scanner(System.in);
    SimulationService simulationService = new SimulationService();

    // When
    String inputCarName = "A";
    String inputCommand = simulationService.runInputCommand(scanner, inputCarName);

    // Assert
    assertEquals("FRL", inputCommand);
  }

  @Test
  void givenValidCommandAfterInvalidCommand_runInputCommand_PrintErrorMessageAndRetry() {
    // Given
    String invalidCommandInput = "invalid\nFF\n";
    ByteArrayInputStream inputStream = new ByteArrayInputStream(invalidCommandInput.getBytes());
    System.setIn(inputStream);
    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    System.setOut(new PrintStream(outputStream));

    Scanner scanner = new Scanner(System.in);
    SimulationService simulationService = new SimulationService();

    // When
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
  void givenValidCarPosition_whenrunInputCarPosition_ReturnsSplitInput() {
    // Given
    String input = "1 2 N\n"; // Simulate user input "1 2 N" (valid position)
    InputStream inputStream = new ByteArrayInputStream(input.getBytes());
    System.setIn(inputStream); // Redirect System.in to provide input

    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    PrintStream originalOut = System.out;
    System.setOut(new PrintStream(outputStream)); // Redirect System.out to capture output

    Scanner scanner = new Scanner(System.in);

    // When
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
  void givenValidInputAfterInvalidInput_whenrunInputCarPosition_ReturnsSplitInput() {
    // Given
    String input = "invalid\n2 3 E\n"; // Simulate user input "invalid" (invalid position) then "2 3 E" (valid
                                       // position)
    InputStream inputStream = new ByteArrayInputStream(input.getBytes());
    System.setIn(inputStream); // Redirect System.in to provide input

    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    System.setOut(new PrintStream(outputStream)); // Redirect System.out to capture output

    Scanner scanner = new Scanner(System.in);

    // When
    SimulationService simulationService = new SimulationService();
    String[] result = simulationService.runInputCarPosition(scanner, 5, 5, "Car2");

    // Assert
    assertArrayEquals(new String[] { "2", "3", "E" }, result); // Expecting {"2", "3", "E"} as the split input
    // Since input is invalid first, followed by valid input, we shouldn't see the
    // error message after the first attempt
    assertEquals("\n\n\n" + "Please enter initial Position of car Car2 in x y Direction format:\n" +
        "=ERROR================================================================\n" +
        "Please fill correct format. (x y n)\n" +
        "x = x-axis    options: (integer)\n" +
        "y = y-axis    options: (integer)\n" +
        "n=direction   options: (N / S / E / W)\n" +
        "======================================================================\n" +
        "\n" +
        "Please enter initial Position of car Car2 in x y Direction format:\n", outputStream.toString());
  }

  @Test
  void givenNoCar_whenrunSimulation_ReturnsFalse() {
    // Given
    HashMap<String, Car> lsCarMap = new HashMap<>();
    SimulationService simulationService = new SimulationService();

    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    System.setOut(new PrintStream(outputStream)); // Redirect System.out to capture output

    // When
    boolean result = simulationService.runSimulation(lsCarMap, 5, 5);

    // Assert
    assertFalse(result); // Expecting false since there are no cars
    assertEquals("\n\n\n" +
        "=ERROR=====================================================================\n" +
        "There is no car to run for simulation. Please choose option 1 to add a car.\n" +
        "===========================================================================\n\n", outputStream.toString());
  }

  @Test
  void givenCarA_whenrunSimulation_ReturnsTrue() {
    // Given
    HashMap<String, Car> lsCarMap = new HashMap<>();
    Car car = new Car("TestCar", 1, 2, 0, "FRL");
    lsCarMap.put(car.getName(), car);
    SimulationService simulationService = new SimulationService();

    // When
    boolean result = simulationService.runSimulation(lsCarMap, 5, 5);

    // Assert
    assertTrue(result); // Expecting true since there are cars
  }

  @Test
  void givenValidBoundary_whenrunInputBoundary_ReturnValidArray() {
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
  void givenTwoCarsCollide_runDisplayCarDetailsScreenAfterSimulation_PrintCorrectOutput() {
    // Given
    HashMap<String, Car> lsCarMap = new HashMap<>();
    Car car1 = new Car("A", 1, 2, 0, "FRL");
    car1.setCurrCoordinate(new Integer[] { 3, 4 });
    car1.setCurrDirection(0);
    car1.setCollideWith(List.of("B"));
    Car car2 = new Car("B", 1, 2, 0, "FRL");
    car2.setCurrCoordinate(new Integer[] { 5, 6 });
    car2.setCurrDirection(2);
    car2.setCollideWith(List.of("A"));
    lsCarMap.put("Car1", car1);
    lsCarMap.put("Car2", car2);
    ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    System.setOut(new PrintStream(outContent));
    SimulationService simulationService = new SimulationService();

    // When
    simulationService.runSimulationResult(lsCarMap);

    // Assert
    assertEquals("Your current list of cars are:\n" +
        "- B, (1,2) N, [F, R, L]\n" +
        "- A, (1,2) N, [F, R, L]\n" +
        "After simulation, the result is:\n" +
        "- B, collides with A at (6,5) at step 1\n" +
        "- A, collides with B at (4,3) at step 1\n", outContent.toString());
  }

  @Test
  void givenOneCar_runDisplayCarDetailsScreenAfterSimulation_PrintCorrectOutput() {
    // Given
    HashMap<String, Car> lsCarMap = new HashMap<>();
    Car car = new Car("A", 1, 2, 0, "FFRFFFFRRL");
    car.setCurrCommand(10);
    car.setCurrCoordinate(new Integer[] { 5, 4 });
    car.setCurrDirection(2);
    lsCarMap.put(car.getName(), car);
    SimulationService simulationService = new SimulationService();
    ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    System.setOut(new PrintStream(outContent));

    // When
    simulationService.runSimulationResult(lsCarMap);

    // Assert
    assertEquals("Your current list of cars are:\n" +
        "- A, (1,2) N, [F, F, R, F, F, F, F, R, R, L]\n" +
        "After simulation, the result is:\n" +
        "- A, (4,5) S\n", outContent.toString());
  }

  @Test
  void givenValidOption_whenrunInputEndingOption_ReturnOption() {
    // Given
    String simulatedUserInput = "1\n";
    InputStream inputStream = new ByteArrayInputStream(simulatedUserInput.getBytes());
    System.setIn(inputStream);
    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    System.setOut(new PrintStream(outputStream));
    Scanner scanner = new Scanner(System.in);
    SimulationService simulationService = new SimulationService();

    // When
    String inputEndingOption = simulationService.runInputEndingOption(scanner);

    // Assert
    assertEquals("1", inputEndingOption);
  }

  @Test
  void givenInvalidOption_whenrunInputEndingOption_PrintErrorMessageAndRetry() {
    // Given
    String simulatedUserInput = "3\n2\n";
    InputStream inputStream = new ByteArrayInputStream(simulatedUserInput.getBytes());
    System.setIn(inputStream);
    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    System.setOut(new PrintStream(outputStream));
    Scanner scanner = new Scanner(System.in);
    SimulationService simulationService = new SimulationService();

    // When
    String inputEndingOption = simulationService.runInputEndingOption(scanner);

    // Assert
    assertEquals("2", inputEndingOption);
    assertEquals("\n\n\n" +
        "Please choose from the following options:\n" +
        "[1] Start Over\n" +
        "[2] Exit\n" +
        "=ERROR=================================================\n" +
        "You have entered a wrong option. Selected option: 3\n" +
        "Please choose a correct option of 1 or 2\n" +
        "=======================================================\n\n" +
        "Please choose from the following options:\n" +
        "[1] Start Over\n" +
        "[2] Exit\n", outputStream.toString());
  }

  @Test
  void givenVoid_whenrunExitScreen_PrintExitMessage() {
    // Given
    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    System.setOut(new PrintStream(outputStream));
    SimulationService simulationService = new SimulationService();

    // When
    simulationService.runExitScreen();

    // Assert
    assertEquals("\n\n\n" +
        "Thank you for running the simulation. Goodbye!\n", outputStream.toString());
  }
}
