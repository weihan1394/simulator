package tehpeng.simulator.service;

import java.util.*;

import tehpeng.simulator.model.Car;
import tehpeng.simulator.util.CommonUtil;
import tehpeng.simulator.util.MapUtil;

public class SimulationService {

  public void runStartScreen() {
    System.out.println("Welcome to Auto Driving Car Simulation!");
  }

  public String[] runInputBoundary(Scanner scanner) {
    String inputBoundary;
    String[] inputBoundarySplit = null;
    while (true) {
      System.out.println("Please enter the width and height of the simulation field in x y format");
      inputBoundary = scanner.nextLine();
      inputBoundarySplit = inputBoundary.split(" ");

      if (CommonUtil.isValidBoundarySize(inputBoundarySplit)) {
        break;
      }

      // show error before prompt again
      System.out.println("=ERROR================================================");
      System.out.println("Please enter a correct dimension of x and y. (integer)");
      System.out.println("======================================================\n");
    }

    return inputBoundarySplit;
  }

  public String runInputOption(Scanner scanner, List<Car> lsCar) {
    runNewScreen();
    String inputOption = "";
    if (lsCar.size() > 0) {
      runDisplayCarDetailsScreen(lsCar);
    }

    while (true) {
      System.out.println("Please choose from the following options: ");
      System.out.println("[1] Add a car to the field");
      System.out.println("[2] Run simulation");
      inputOption = scanner.nextLine();

      if ((CommonUtil.isValidInteger(inputOption))
          && ((inputOption.trim().equals("1")) || (inputOption.trim().equals("2")))) {
        break;
      }

      // show error before prompt again
      System.out.println("=ERROR===============================================");
      System.out.println("You have entered a wrong option. Selected option: " + inputOption);
      System.out.println("Please choose a correct option of 1 or 2");
      System.out.println("=====================================================\n");
    }
    return inputOption;
  }

  public String runInputCarName(Scanner scanner) {
    runNewScreen();
    String inputCarName = "";
    while (true) {
      // add car
      System.out.println("Please enter the name of the car:");
      inputCarName = scanner.nextLine();
      if (CommonUtil.isValidCarName(inputCarName)) {
        break;
      }

      System.out.println("=ERROR======================");
      System.out.println("Please give your car a name.");
      System.out.println("============================\n");
    }

    return inputCarName;
  }

  public String[] runInputCarPosition(Scanner scanner, int inputBoundaryX, int inputBoundaryY) {
    runNewScreen();
    String inputCarPosition = "";
    String[] inputCarPositionSplit = null;
    while (true) {
      System.out.println("Please enter initial Position of car A in x y Direction format:");
      inputCarPosition = scanner.nextLine();
      inputCarPositionSplit = inputCarPosition.split(" ");

      if (inputCarPositionSplit.length == 3) {
        // check value options
        boolean inputFourSplit1StatusInt = CommonUtil.isValidInteger(inputCarPositionSplit[0]);
        boolean inputFourSplit2StatusInt = CommonUtil.isValidInteger(inputCarPositionSplit[1]);
        boolean inputFourSplit3StatusDir = CommonUtil.isValidDirection(inputCarPositionSplit[2]);

        if (inputFourSplit1StatusInt && inputFourSplit2StatusInt && inputFourSplit3StatusDir) {
          // checking for boundary here to make sure can cast to integer
          boolean inputFourSplit1StatusBoundary = CommonUtil
              .isValidBoundary(Integer.parseInt(inputCarPositionSplit[0]), inputBoundaryX);
          boolean inputFourSplit2StatusBoundary = CommonUtil
              .isValidBoundary(Integer.parseInt(inputCarPositionSplit[1]), inputBoundaryX);

          if (inputFourSplit1StatusBoundary && inputFourSplit2StatusBoundary) {
            // valid input
            break;
          }
        }
      }

      // show error before prompt again
      System.out.println("=ERROR================================================================");
      System.out.println("Please fill correct format. (x y n). ; y=y-axis; n=direction");
      System.out.println("x = x-axis    options: (integer)");
      System.out.println("y = y-axis    options: (integer)");
      System.out.println("n=direction   options: (N / S / E / W)");
      System.out.println("======================================================================\n");
    }

    return inputCarPositionSplit;
  }

  public String runInputCommand(Scanner scanner, String inputCarName) {
    runNewScreen();
    String inputCommand = "";
    while (true) {
      System.out.println("Please enter the commands for car " + inputCarName + ":");
      inputCommand = scanner.nextLine();

      if (CommonUtil.isValidCommand(inputCommand)) {
        break;
      }

      // show error before prompt again
      System.out.println("=ERROR==================");
      System.out.println("Please enter the command");
      System.out.println("========================\n");
    }

    return inputCommand;
  }

  public boolean runSimulation(List<Car> lsCar, int inputBoundaryX, int inputBoundaryY) {
    runNewScreen();
    boolean result = false;

    runDisplayCarDetailsScreen(lsCar);

    if (lsCar.size() > 0) {
      CarSimulatorService carSimulator = new CarSimulatorService(lsCar, inputBoundaryX, inputBoundaryY);
      do {
        carSimulator.moveNext();
      } while (carSimulator.hasCarsOnMap());

      runDisplayCarDetailsScreenAfterSimulation(carSimulator.cars);

      result = true;
    } else {
      // show error before prompt again
      System.out.println("=ERROR=====================================================================");
      System.out.println("There is no car to run for simulation. Please choose option 1 to add a car.");
      System.out.println("===========================================================================\n");

      result = false;
    }

    return result;
  }

  public void runSimulationResult(List<Car> lsCar) {
    runDisplayCarDetailsScreen(lsCar);
    runDisplayCarDetailsScreenAfterSimulation(lsCar);
  }

  public String runInputEndingOption(Scanner scanner) {
    runNewScreen();
    String inputEndingOption = "";
    while (true) {
      System.out.println("Please choose from the following options:");
      System.out.println("[1] Start Over");
      System.out.println("[2] Exit");

      inputEndingOption = scanner.nextLine();

      if ((inputEndingOption.trim().equals("1")) || (inputEndingOption.trim().equals("2"))) {
        break;
      }

      System.out.println("=ERROR=================================================");
      System.out.println("You have entered a wrong option. Selected option: " + inputEndingOption);
      System.out.println("Please choose a correct option of 1 or 2");
      System.out.println("=======================================================\n");

    }

    return inputEndingOption;
  }

  public void runExitScreen() {
    runNewScreen();
    System.out.println("Thank you for running the simulation. Goodbye!");
  }

  private void runDisplayCarDetailsScreen(List<Car> lsCar) {
    System.out.println("Your current list of cars are: ");
    for (Car car : lsCar) {
      System.out.println("- " + car.getName() + ", (" + car.getCoordinate()[1] + "," + car.getCoordinate()[0] +
          ") " + MapUtil.convertIndexToDirection(car.getDirection()) + ", " + car.getCommands());
    }
  }

  private void runDisplayCarDetailsScreenAfterSimulation(List<Car> lsCar) {
    System.out.println("After simulation, the result is:");
    for (int index = 0; index < lsCar.size(); index++) {
      // if the curposition < command size = print the collide
      Car car = lsCar.get(index);

      if (car.collided) {
        System.out.println("- " + car.getName() + "collides with " + car.collided + " at step " + car.getCurrCommand());
      } else {

        System.out.println("- " + car.getName() + ", (" + car.getCurrCoordinate()[1] + "," +
            car.getCurrCoordinate()[0] + ") " + MapUtil.convertIndexToDirection(car.getCurrDirection()));

      }
    }
  }

  private void runNewScreen() {
    System.out.println("\n\n");
  }
}
