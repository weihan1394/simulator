package tehpeng.simulator;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import tehpeng.simulator.service.SimulationService;

public class SimulatorApplicationTest {

  @Test
  void givenScenario1Mock_whenRunSimulation_thenReturnCarAResult() {
    // Given
    String input = "10 10\n1\nA\n1 2 N\nFFRFFFFRRL\n2\n2\n";
    InputStream inputStream = new ByteArrayInputStream(input.getBytes());
    System.setIn(inputStream);

    // Mock
    SimulationService simulationService = Mockito.mock(SimulationService.class);
    when(simulationService.runInputBoundary(Mockito.any())).thenReturn(new String[] { "10", "10" });
    when(simulationService.runInputOption(Mockito.any(),
        Mockito.any())).thenReturn("1");
    when(simulationService.runInputCarName(Mockito.any())).thenReturn("A");
    when(simulationService.runInputCarPosition(Mockito.any(), Mockito.anyInt(),
        Mockito.anyInt(), Mockito.any()))
        .thenReturn(new String[] { "1", "2", "N" });
    when(simulationService.runInputCommand(Mockito.any(),
        Mockito.any())).thenReturn("F");
    when(simulationService.runSimulation(Mockito.any(), Mockito.anyInt(),
        Mockito.anyInt())).thenReturn(true);
    when(simulationService.runInputEndingOption(Mockito.any())).thenReturn("2");

    // Redirect output streams
    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    System.setOut(new PrintStream(outputStream));

    // When
    SimulatorApplication.main(new String[0]);

    // Assert
    String expectedOutput = "Please enter the width and height of the simulation field in x y format\n" +
        "\n\n\n" +
        "Please choose from the following options:\n" +
        "[1] Add a car to the field\n" +
        "[2] Run simulation\n" +
        "\n\n\n" +
        "Please enter the name of the car:\n" +
        "\n\n\n" +
        "Please enter initial Position of car A in x y Direction format:\n" +
        "\n\n\n" +
        "Please enter the commands for car A:\n" +
        "\n\n\n" +
        "Your current list of cars are:\n" +
        "- A, (1,2) N, [F, F, R, F, F, F, F, R, R, L]\n" +
        "Please choose from the following options:\n" +
        "[1] Add a car to the field\n" +
        "[2] Run simulation\n" +
        "\n\n\n" +
        "Your current list of cars are:\n" +
        "- A, (1,2) N, [F, F, R, F, F, F, F, R, R, L]\n" +
        "After simulation, the result is:\n" +
        "- A, (5,4) S\n" +
        "\n\n\n" +
        "Please choose from the following options:\n" +
        "[1] Start Over\n" +
        "[2] Exit\n" +
        "\n\n\n" +
        "Thank you for running the simulation. Goodbye!\n";
    assertEquals(expectedOutput, outputStream.toString());
  }

  @Test
  void givenScenario1_whenRunSimulation_thenReturnCarAResult() {
    // Given
    String input = "10 10\n1\nA\n1 2 N\nFFRFFFFRRL\n2\n2\n";
    InputStream inputStream = new ByteArrayInputStream(input.getBytes());
    System.setIn(inputStream);

    // Redirect output streams
    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    System.setOut(new PrintStream(outputStream));

    // When
    SimulatorApplication.main(new String[0]);

    // Assert
    String expectedOutput = "Please enter the width and height of the simulation field in x y format\n" +
        "\n\n\n" +
        "Please choose from the following options:\n" +
        "[1] Add a car to the field\n" +
        "[2] Run simulation\n" +
        "\n\n\n" +
        "Please enter the name of the car:\n" +
        "\n\n\n" +
        "Please enter initial Position of car A in x y Direction format:\n" +
        "\n\n\n" +
        "Please enter the commands for car A:\n" +
        "\n\n\n" +
        "Your current list of cars are:\n" +
        "- A, (1,2) N, [F, F, R, F, F, F, F, R, R, L]\n" +
        "Please choose from the following options:\n" +
        "[1] Add a car to the field\n" +
        "[2] Run simulation\n" +
        "\n\n\n" +
        "Your current list of cars are:\n" +
        "- A, (1,2) N, [F, F, R, F, F, F, F, R, R, L]\n" +
        "After simulation, the result is:\n" +
        "- A, (5,4) S\n" +
        "\n\n\n" +
        "Please choose from the following options:\n" +
        "[1] Start Over\n" +
        "[2] Exit\n" +
        "\n\n\n" +
        "Thank you for running the simulation. Goodbye!\n";
    assertEquals(expectedOutput, outputStream.toString());
  }

  @Test
  void givenScenario1_whenRunSimulation_thenRerunScenario1Input() {
    // Given
    String input = "10 10\n1\nA\n1 2 N\nFFRFFFFRRL\n2\n1\n1\nA\n1 2 N\nFFRFFFFRRL\n2\n2\n";
    InputStream inputStream = new ByteArrayInputStream(input.getBytes());
    System.setIn(inputStream);

    // Redirect output streams
    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    System.setOut(new PrintStream(outputStream));

    // When
    SimulatorApplication.main(new String[0]);

    // Then
    String expectedOutput = "Please enter the width and height of the simulation field in x y format\n" +
        "\n\n\n" +
        "Please choose from the following options:\n" +
        "[1] Add a car to the field\n" +
        "[2] Run simulation\n" +
        "\n\n\n" +
        "Please enter the name of the car:\n" +
        "\n\n\n" +
        "Please enter initial Position of car A in x y Direction format:\n" +
        "\n\n\n" +
        "Please enter the commands for car A:\n" +
        "\n\n\n" +
        "Your current list of cars are:\n" +
        "- A, (1,2) N, [F, F, R, F, F, F, F, R, R, L]\n" +
        "Please choose from the following options:\n" +
        "[1] Add a car to the field\n" +
        "[2] Run simulation\n" +
        "\n\n\n" +
        "Your current list of cars are:\n" +
        "- A, (1,2) N, [F, F, R, F, F, F, F, R, R, L]\n" +
        "After simulation, the result is:\n" +
        "- A, (5,4) S\n" +
        "\n\n\n" +
        "Please choose from the following options:\n" +
        "[1] Start Over\n" +
        "[2] Exit\n" +
        "\n\n\n" +
        "Please choose from the following options:\n" +
        "[1] Add a car to the field\n" +
        "[2] Run simulation\n" +
        "\n\n\n" +
        "Please enter the name of the car:\n" +
        "\n\n\n" +
        "Please enter initial Position of car A in x y Direction format:\n" +
        "\n\n\n" +
        "Please enter the commands for car A:\n" +
        "\n\n\n" +
        "Your current list of cars are:\n" +
        "- A, (1,2) N, [F, F, R, F, F, F, F, R, R, L]\n" +
        "Please choose from the following options:\n" +
        "[1] Add a car to the field\n" +
        "[2] Run simulation\n" +
        "\n\n\n" +
        "Your current list of cars are:\n" +
        "- A, (1,2) N, [F, F, R, F, F, F, F, R, R, L]\n" +
        "After simulation, the result is:\n" +
        "- A, (5,4) S\n" +
        "\n\n\n" +
        "Please choose from the following options:\n" +
        "[1] Start Over\n" +
        "[2] Exit\n" +
        "\n\n\n" +
        "Thank you for running the simulation. Goodbye!\n";
    assertEquals(expectedOutput, outputStream.toString());
  }

  @Test
  void givenScenario2_whenRunSimulation_thenReturnCarAandBCollide() {
    // Given
    String input = "10 10\n1\nA\n1 2 N\nFFRFFFFRRL\n1\nB\n7 8 W\nFFLFFFFFFF\n2\n2\n";
    InputStream inputStream = new ByteArrayInputStream(input.getBytes());
    System.setIn(inputStream);

    // Redirect output streams
    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    System.setOut(new PrintStream(outputStream));

    // When
    SimulatorApplication.main(new String[0]);

    // Assert
    String expectedOutput = "Please enter the width and height of the simulation field in x y format\n" +
        "\n\n\n" +
        "Please choose from the following options:\n" +
        "[1] Add a car to the field\n" +
        "[2] Run simulation\n" +
        "\n\n\n" +
        "Please enter the name of the car:\n" +
        "\n\n\n" +
        "Please enter initial Position of car A in x y Direction format:\n" +
        "\n\n\n" +
        "Please enter the commands for car A:\n" +
        "\n\n\n" +
        "Your current list of cars are:\n" +
        "- A, (1,2) N, [F, F, R, F, F, F, F, R, R, L]\n" +
        "Please choose from the following options:\n" +
        "[1] Add a car to the field\n" +
        "[2] Run simulation\n" +
        "\n\n\n" +
        "Please enter the name of the car:\n" +
        "\n\n\n" +
        "Please enter initial Position of car B in x y Direction format:\n" +
        "\n\n\n" +
        "Please enter the commands for car B:\n" +
        "\n\n\n" +
        "Your current list of cars are:\n" +
        "- A, (1,2) N, [F, F, R, F, F, F, F, R, R, L]\n" +
        "- B, (7,8) W, [F, F, L, F, F, F, F, F, F, F]\n" +
        "Please choose from the following options:\n" +
        "[1] Add a car to the field\n" +
        "[2] Run simulation\n" +
        "\n\n\n" +
        "Your current list of cars are:\n" +
        "- A, (1,2) N, [F, F, R, F, F, F, F, R, R, L]\n" +
        "- B, (7,8) W, [F, F, L, F, F, F, F, F, F, F]\n" +
        "After simulation, the result is:\n" +
        "- A, collides with B at (5,4) at step 7\n" +
        "- B, collides with A at (5,4) at step 7\n" +
        "\n\n\n" +
        "Please choose from the following options:\n" +
        "[1] Start Over\n" +
        "[2] Exit\n" +
        "\n\n\n" +
        "Thank you for running the simulation. Goodbye!\n";
    assertEquals(expectedOutput, outputStream.toString());
  }

  // TODO: add test case A B collide then C collide after that
  @Test
  void givenScenario3_whenRunSimulation_thenReturnCarAandBCollideAtStep7AndCarCCollideWithCarAANDBAtStep10() {
    // Given
    String input = "10 10\n1\nA\n1 2 N\nFFRFFFFRRL\n1\nB\n7 8 W\nFFLFFFFFFF\n1\nC\n0 0 N\nFFFFRFFFFFF\n2\n2\n";
    InputStream inputStream = new ByteArrayInputStream(input.getBytes());
    System.setIn(inputStream);

    // Redirect output streams
    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    System.setOut(new PrintStream(outputStream));

    // When
    SimulatorApplication.main(new String[0]);

    // Assert
    String expectedOutput = "Please enter the width and height of the simulation field in x y format\n" +
        "\n\n\n" +
        "Please choose from the following options:\n" +
        "[1] Add a car to the field\n" +
        "[2] Run simulation\n" +
        "\n\n\n" +
        "Please enter the name of the car:\n" +
        "\n\n\n" +
        "Please enter initial Position of car A in x y Direction format:\n" +
        "\n\n\n" +
        "Please enter the commands for car A:\n" +
        "\n\n\n" +
        "Your current list of cars are:\n" +
        "- A, (1,2) N, [F, F, R, F, F, F, F, R, R, L]\n" +
        "Please choose from the following options:\n" +
        "[1] Add a car to the field\n" +
        "[2] Run simulation\n" +
        "\n\n\n" +
        "Please enter the name of the car:\n" +
        "\n\n\n" +
        "Please enter initial Position of car B in x y Direction format:\n" +
        "\n\n\n" +
        "Please enter the commands for car B:\n" +
        "\n\n\n" +
        "Your current list of cars are:\n" +
        "- A, (1,2) N, [F, F, R, F, F, F, F, R, R, L]\n" +
        "- B, (7,8) W, [F, F, L, F, F, F, F, F, F, F]\n" +
        "Please choose from the following options:\n" +
        "[1] Add a car to the field\n" +
        "[2] Run simulation\n" +
        "\n\n\n" +
        "Please enter the name of the car:\n" +
        "\n\n\n" +
        "Please enter initial Position of car C in x y Direction format:\n" +
        "\n\n\n" +
        "Please enter the commands for car C:\n" +
        "\n\n\n" +
        "Your current list of cars are:\n" +
        "- A, (1,2) N, [F, F, R, F, F, F, F, R, R, L]\n" +
        "- B, (7,8) W, [F, F, L, F, F, F, F, F, F, F]\n" +
        "- C, (0,0) N, [F, F, F, F, R, F, F, F, F, F, F]\n" +
        "Please choose from the following options:\n" +
        "[1] Add a car to the field\n" +
        "[2] Run simulation\n" +
        "\n\n\n" +
        "Your current list of cars are:\n" +
        "- A, (1,2) N, [F, F, R, F, F, F, F, R, R, L]\n" +
        "- B, (7,8) W, [F, F, L, F, F, F, F, F, F, F]\n" +
        "- C, (0,0) N, [F, F, F, F, R, F, F, F, F, F, F]\n" +
        "After simulation, the result is:\n" +
        "- A, collides with B at (5,4) at step 7\n" +
        "- B, collides with A at (5,4) at step 7\n" +
        "- C, collides with A, B at (5,4) at step 10\n" +
        "\n\n\n" +
        "Please choose from the following options:\n" +
        "[1] Start Over\n" +
        "[2] Exit\n" +
        "\n\n\n" +
        "Thank you for running the simulation. Goodbye!\n";
    assertEquals(expectedOutput, outputStream.toString());
  }

  @Test
  void givenScenario3_whenRunSimulation_thenReturnCarAandBCollideAtStep7AndCarContinueAndComplete() {
    // Given
    String input = "10 10\n1\nA\n1 2 N\nFFRFFFFRRL\n1\nB\n7 8 W\nFFLFFFFFFF\n1\nC\n0 0 N\nFFFFFFFFFFF\n2\n2\n";
    InputStream inputStream = new ByteArrayInputStream(input.getBytes());
    System.setIn(inputStream);

    // Redirect output streams
    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    System.setOut(new PrintStream(outputStream));

    // When
    SimulatorApplication.main(new String[0]);

    // Assert
    String expectedOutput = "Please enter the width and height of the simulation field in x y format\n" +
        "\n\n\n" +
        "Please choose from the following options:\n" +
        "[1] Add a car to the field\n" +
        "[2] Run simulation\n" +
        "\n\n\n" +
        "Please enter the name of the car:\n" +
        "\n\n\n" +
        "Please enter initial Position of car A in x y Direction format:\n" +
        "\n\n\n" +
        "Please enter the commands for car A:\n" +
        "\n\n\n" +
        "Your current list of cars are:\n" +
        "- A, (1,2) N, [F, F, R, F, F, F, F, R, R, L]\n" +
        "Please choose from the following options:\n" +
        "[1] Add a car to the field\n" +
        "[2] Run simulation\n" +
        "\n\n\n" +
        "Please enter the name of the car:\n" +
        "\n\n\n" +
        "Please enter initial Position of car B in x y Direction format:\n" +
        "\n\n\n" +
        "Please enter the commands for car B:\n" +
        "\n\n\n" +
        "Your current list of cars are:\n" +
        "- A, (1,2) N, [F, F, R, F, F, F, F, R, R, L]\n" +
        "- B, (7,8) W, [F, F, L, F, F, F, F, F, F, F]\n" +
        "Please choose from the following options:\n" +
        "[1] Add a car to the field\n" +
        "[2] Run simulation\n" +
        "\n\n\n" +
        "Please enter the name of the car:\n" +
        "\n\n\n" +
        "Please enter initial Position of car C in x y Direction format:\n" +
        "\n\n\n" +
        "Please enter the commands for car C:\n" +
        "\n\n\n" +
        "Your current list of cars are:\n" +
        "- A, (1,2) N, [F, F, R, F, F, F, F, R, R, L]\n" +
        "- B, (7,8) W, [F, F, L, F, F, F, F, F, F, F]\n" +
        "- C, (0,0) N, [F, F, F, F, F, F, F, F, F, F, F]\n" +
        "Please choose from the following options:\n" +
        "[1] Add a car to the field\n" +
        "[2] Run simulation\n" +
        "\n\n\n" +
        "Your current list of cars are:\n" +
        "- A, (1,2) N, [F, F, R, F, F, F, F, R, R, L]\n" +
        "- B, (7,8) W, [F, F, L, F, F, F, F, F, F, F]\n" +
        "- C, (0,0) N, [F, F, F, F, F, F, F, F, F, F, F]\n" +
        "After simulation, the result is:\n" +
        "- A, collides with B at (5,4) at step 7\n" +
        "- B, collides with A at (5,4) at step 7\n" +
        "- C, (0,9) N\n" +
        "\n\n\n" +
        "Please choose from the following options:\n" +
        "[1] Start Over\n" +
        "[2] Exit\n" +
        "\n\n\n" +
        "Thank you for running the simulation. Goodbye!\n";
    assertEquals(expectedOutput, outputStream.toString());
  }

  // TODO: add test case A B collide then C continue to completion after that
}
