package tehpeng.simulator;

import tehpeng.simulator.model.Car;
import tehpeng.simulator.util.CommonUtil;
import tehpeng.simulator.util.MapUtil;

import java.util.*;

public class SimulatorApplication {
  public static void main(String[] args) {
    // Init
    Scanner scanner = new Scanner(System.in);
    System.out.println("Welcome to Auto Driving Car Simulation!");

    String inputBoundary = "";
    int inputBoundaryX = 0, inputBoundaryY = 0;
    while (true) {
      System.out.println("Please enter the width and height of the simulation field in x y format");
      inputBoundary = scanner.nextLine();
      String[] inputBoundarySplit = inputBoundary.split(" ");

      if (CommonUtil.isValidBoundarySize(inputBoundarySplit)) {
        inputBoundaryX = Integer.parseInt(inputBoundarySplit[0]);
        inputBoundaryY = Integer.parseInt(inputBoundarySplit[1]);
        System.out.println("You have created a field of " + inputBoundaryX + " x " + inputBoundaryY);

        break;
      }

      // show error before prompt again
      System.out.println("=ERROR================================================");
      System.out.println("Please enter a correct dimension of x and y. (integer)");
      System.out.println("======================================================\n");
    }

    String inputOption = "";
    // keep details of the car
    List<Car> lsCar = new ArrayList<>();
    while (true) {
      System.out.println("Please choose from the following options: ");
      System.out.println("[1] Add a car to the field");
      System.out.println("[2] Run simulation");
      inputOption = scanner.nextLine();

      int inputCarPositionSplitX = 0;
      int inputCarPositionSplitY = 0;
      String inputCarPositionSplitDirection = "";

      if (inputOption.trim().equals("1")) {
        // ask for name
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

        // ask for car position
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
                inputCarPositionSplitX = Integer.parseInt(inputCarPositionSplit[0]);
                inputCarPositionSplitY = Integer.parseInt(inputCarPositionSplit[1]);
                inputCarPositionSplitDirection = inputCarPositionSplit[2];

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

        // add command
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

        Car car = new Car(inputCarName, inputCarPositionSplitX, inputCarPositionSplitY, inputBoundaryX, inputBoundaryY,
            MapUtil.convertDirectionToIndex(inputCarPositionSplitDirection), inputCommand);
        lsCar.add(car);
      } else if (inputOption.trim().equals("2")) {
        // run simulation
        // TODO: check if at least 1 car exist
        if (lsCar.size() > 0) {
          for (Car car : lsCar) {
            List<Character> lsCommand = car.getCommands();

            for (int index = 0; index < lsCommand.size(); index++) {
              char currCommand = lsCommand.get(index);
              if (currCommand == 'F') {
                // move forward
                int currDirection = car.getCurrDirection();
                int currDirectionIndex = currDirection % 2;
                if (currDirection > 1) {
                  // moving south or west (-1)
                  if (currDirectionIndex == 0) {
                    car.minusCurrCoordinateY();
                  } else {
                    car.minusCurrCoordinateX();
                  }
                } else {
                  // moving north or east (+1)
                  if (currDirectionIndex == 0) {
                    car.plusCurrCoordinateY(inputBoundaryY);
                  } else {
                    car.plusCurrCoordinateX(inputBoundaryX);
                  }
                }
              } else if ((currCommand == 'R') || (currCommand == 'L')) {
                // move direction
                int newDirection = 9;
                if (currCommand == 'R') {
                  newDirection = (car.getCurrDirection() + 1) % 4;
                } else if (currCommand == 'L') {
                  newDirection = (car.getCurrDirection() - 1) % 4;
                }

                car.setCurrDirection(newDirection);
              }
            }
          }

          System.out.println("\nYour current list of cars are:");
          for (int index = 0; index < lsCar.size(); index++) {
            Car car = lsCar.get(index);
            System.out.println("- " + car.getName() + ", (" + car.getCoordinate()[1] + "," +
                car.getCoordinate()[0] + "), " + MapUtil.convertIndexToDirection(car.getDirection()) +
                ", " + car.getCommands());
          }

          System.out.println("\nAfter simulation, the result is:");
          for (int index = 0; index < lsCar.size(); index++) {
            Car car = lsCar.get(index);
            System.out.println("- " + car.getName() + ", (" + car.getCurrCoordinate()[1] + "," +
                car.getCurrCoordinate()[0] + "), " + MapUtil.convertIndexToDirection(car.getCurrDirection()));
          }

          String inputEndingOption = "";
          while (true) {
            System.out.println("Please choose from the following options:");
            System.out.println("[1] Start Over");
            System.out.println("[2] Exit");

            inputEndingOption = scanner.nextLine();

            if ((inputEndingOption.trim().equals("1")) || (inputEndingOption.trim().equals("2"))) {
              break;
            } else {
              System.out.println("=ERROR=================================================");
              System.out.println("You have entered a wrong option. Selected option: " + inputOption);
              System.out.println("Please choose a correct option of 1 or 2");
              System.out.println("=======================================================\n");
            }
          }

          if (inputEndingOption.trim().equals("2")) {
            // exit
            System.out.println("Thank you for running the simulation. Goodbye!");
            break;
          }
        }

      } else {
        System.out.println("=ERROR==================================");
        System.out.println("You have entered a wrong option. Selected option: " + inputOption);
        System.out.println("Please choose a correct option of 1 or 2");
        System.out.println("========================================\n");
      }
    }

    // clean up
    scanner.close();
  }
}