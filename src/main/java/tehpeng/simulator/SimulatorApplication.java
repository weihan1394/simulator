package tehpeng.simulator;

import tehpeng.simulator.model.Car;
import tehpeng.simulator.util.CommonUtil;

import java.util.*;

public class SimulatorApplication {
  public static void main(String[] args) {
    // Init
    Scanner scanner = new Scanner(System.in);
    System.out.println("Welcome to Auto Driving Car Simulation!");

    String inputBoundary = "";
    int x = 0, y = 0;
    while (true) {
      System.out.println("Please enter the width and height of the simulation field in x y format");
      inputBoundary = scanner.nextLine();
      String[] inputBoundarySplit = inputBoundary.split(" ");

      if (CommonUtil.isValidBoundarySize(inputBoundarySplit)) {
        x = Integer.parseInt(inputBoundarySplit[0]);
        y = Integer.parseInt(inputBoundarySplit[1]);

        break;
      }
      System.out.println("You have created a field of " + x + " x " + y);

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

      if (inputOption.trim().equals("1")) {
        // adding a new car
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

        String inputCarPosition = "";
        String[] inputCarPositionSplit = null;
        while (true) {
          System.out.println("Please enter initial Position of car A in x y Direction format:");
          inputCarPosition = scanner.nextLine();
          inputCarPositionSplit = inputCarPosition.split(" ");

          if (inputCarPositionSplit.length == 3) {
            // check value options
            String inputFourSplit1 = inputCarPositionSplit[0];
            String inputFourSplit2 = inputCarPositionSplit[1];
            String inputFourSplit3 = inputCarPositionSplit[2];

            boolean inputFourSplit1StatusInt = CommonUtil.isValidInteger(inputFourSplit1);
            boolean inputFourSplit2StatusInt = CommonUtil.isValidInteger(inputFourSplit2);
            boolean inputFourSplit3StatusDir = CommonUtil.isValidDirection(inputFourSplit3);

            if (inputFourSplit1StatusInt && inputFourSplit2StatusInt && inputFourSplit3StatusDir) {
              // checking for boundary here to make sure can cast to integer
              boolean inputFourSplit1StatusBoundary = CommonUtil.isValidBoundary(Integer.parseInt(inputFourSplit1), x);
              boolean inputFourSplit2StatusBoundary = CommonUtil.isValidBoundary(Integer.parseInt(inputFourSplit2), x);

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

        Car car = new Car(x, y, inputCarName, inputCommand, inputCarPositionSplit);
        lsCar.add(car);
      } else if (inputOption.trim().equals("2")) {
        // run simulation
        // TODO: check if at least 1 car exist
        for (Car car : lsCar) {
          System.out.println(car.toString());
        }
        break;
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