package tehpeng.simulator;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.Test;

public class SimulatorApplicationTest {

  @Test
  void givenScenario1_whenRunSimulation_thenReturnCarAResult() {
    // Given
    String input = "10 10\n1\nA\n1 2 N\nFFRFFFFRRL\n2\n2\n";
    InputStream inputStream = new ByteArrayInputStream(input.getBytes());
    System.setIn(inputStream);

    // Redirect output streams
    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    System.setOut(new PrintStream(outputStream));
    ByteArrayOutputStream errorStream = new ByteArrayOutputStream();
    System.setErr(new PrintStream(errorStream));

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
    ByteArrayOutputStream errorStream = new ByteArrayOutputStream();
    System.setErr(new PrintStream(errorStream));

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

  @Test
  void given_whenRunSimulation_thenRerunInput() {
    // Given
    String input = "10 10\n1\nA\n1 2 N\nFFRFFFFRRL\n2\n1\n1\nA\n1 2 N\nFFRFFFFRRL\n2\n2\n";
    InputStream inputStream = new ByteArrayInputStream(input.getBytes());
    System.setIn(inputStream);

    // Redirect output streams
    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    System.setOut(new PrintStream(outputStream));
    ByteArrayOutputStream errorStream = new ByteArrayOutputStream();
    System.setErr(new PrintStream(errorStream));

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
}
