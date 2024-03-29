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

  public String runInputOption(Scanner scanner, HashMap<String, Car> lsCarMap) {
    runNewScreen();
    String inputOption = "";
    if (lsCarMap.size() > 0) {
      runDisplayCarDetailsScreen(lsCarMap);
    }

    while (true) {
      System.out.println("Please choose from the following options:");
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

    return inputCarName.trim();
  }

  public String[] runInputCarPosition(Scanner scanner, int inputBoundaryX, int inputBoundaryY, String carName) {
    runNewScreen();
    String inputCarPosition = "";
    String[] inputCarPositionSplit = null;
    while (true) {
      System.out.println("Please enter initial Position of car " + carName + " in x y Direction format:");
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
              .isValidBoundary(Integer.parseInt(inputCarPositionSplit[1]), inputBoundaryY);

          if (inputFourSplit1StatusBoundary && inputFourSplit2StatusBoundary) {
            // valid input
            break;
          }
        }
      }

      // show error before prompt again
      System.out.println("=ERROR================================================================");
      System.out.println("Please fill correct format. (x y n)");
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

  public boolean runSimulation(HashMap<String, Car> lsCarMap, int inputBoundaryX, int inputBoundaryY) {
    runNewScreen();

    if (lsCarMap.size() > 0) {
      CarService carSimulation = new CarService(lsCarMap, inputBoundaryX, inputBoundaryY);
      // start simulation
      while (carSimulation.hasNextStep()) {
        // move car
        carSimulation.nextMove();
      }
    } else {
      // show error before prompt again
      System.out.println("=ERROR=====================================================================");
      System.out.println("There is no car to run for simulation. Please choose option 1 to add a car.");
      System.out.println("===========================================================================\n");

      return false;
    }

    return true;
  }

  public void runSimulationResult(HashMap<String, Car> lsCarMap) {
    runDisplayCarDetailsScreen(lsCarMap);
    runDisplayCarDetailsScreenAfterSimulation(lsCarMap);
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

  private void runDisplayCarDetailsScreen(HashMap<String, Car> lsCarMap) {
    System.out.println("Your current list of cars are:");

    for (String key : lsCarMap.keySet()) {
      Car car = lsCarMap.get(key);
      System.out.println("- " + car.getName() + ", (" + car.getCoordinate()[1] + "," + car.getCoordinate()[0] +
          ") " + MapUtil.convertIndexToDirection(car.getDirection()) + ", " + car.getCommands());
    }
  }

  private void runDisplayCarDetailsScreenAfterSimulation(HashMap<String, Car> lsCarMap) {
    System.out.println("After simulation, the result is:");

    for (String key : lsCarMap.keySet()) {
      Car car = lsCarMap.get(key);

      if (car.getCollideWith().size() == 0) {
        System.out.println("- " + car.getName() + ", (" + car.getCurrCoordinate()[1] + "," +
            car.getCurrCoordinate()[0] + ") " + MapUtil.convertIndexToDirection(car.getCurrDirection()));
      } else {
        System.out.println("- " + car.getName() + ", collides with " + String.join(", ", car.getCollideWith())
            + " at (" + car.getCurrCoordinate()[1] + "," + car.getCurrCoordinate()[0] + ") at step "
            + (car.getCurrCommand() + 1));
      }
    }
  }

  private void runNewScreen() {
    System.out.println("\n\n");
  }
}
