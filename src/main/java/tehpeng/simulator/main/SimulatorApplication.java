package tehpeng.simulator.main;

import tehpeng.simulator.util.CommonUtil;

import java.util.*;

public class SimulatorApplication {
  public static void main(String[] args) {
    // Init
    Scanner scanner = new Scanner(System.in);
    System.out.println("Welcome to Auto Driving Car Simulation!");
    System.out.println("Please enter the width and height of the simulation field in x y format");

    String inputOne = scanner.nextLine();
    String[] inputOneSplit = inputOne.split(" ");

    // TODO: check if there is size of 2
    int x = Integer.parseInt(inputOneSplit[0]);
    int y = Integer.parseInt(inputOneSplit[1]);

    String inputTwo = "";
    while (true) {
      System.out.println("You have created a field of " + x + " x " + y);
      System.out.println("Please choose from the following options: ");
      System.out.println("[1] Add a car to the field");
      System.out.println("[2] Run simulation");
      inputTwo = scanner.nextLine();

      if (inputTwo.trim().equals("1")) {
        // adding a new car
        break;
      } else if (inputTwo.trim().equals("2")) {
        // run simulation
        break;
      } else {
        System.out.println("=ERROR==================================");
        System.out.println("You have entered a wrong option. Selected option: " + inputTwo);
        System.out.println("Please choose a correct option of 1 or 2");
        System.out.println("========================================\n");
      }
    }

    // keep details of the car
    HashMap<String, String[]> carsPosition = new HashMap<>();
    HashMap<String, List<String>> carsCommand = new HashMap<>();

    if (inputTwo.trim().equals("1")) {
      // add car
      System.out.println("Please enter the name of the car:");
      String inputThree = scanner.nextLine();

      String inputFour = "";
      String[] inputFourSplit = null;
      while (true) {
        System.out.println("Please enter initial Position of car A in x y Direction format:");
        inputFour = scanner.nextLine();
        inputFourSplit = inputFour.split(" ");

        if (inputFourSplit.length == 3) {
          // check value options
          String inputFourSplit1 = inputFourSplit[0];
          String inputFourSplit2 = inputFourSplit[1];
          String inputFourSplit3 = inputFourSplit[2];

          boolean inputFourSplit1StatusInt = CommonUtil.checkInteger(inputFourSplit1);
          boolean inputFourSplit2StatusInt = CommonUtil.checkInteger(inputFourSplit2);
          boolean inputFourSplit3StatusDir = CommonUtil.checkDirection(inputFourSplit3);

          if (inputFourSplit1StatusInt && inputFourSplit2StatusInt && inputFourSplit3StatusDir) {
            // checking for boundary here to make sure can cast to integer
            boolean inputFourSplit1StatusBoundary = CommonUtil.checkBoundary(Integer.parseInt(inputFourSplit1), x);
            boolean inputFourSplit2StatusBoundary = CommonUtil.checkBoundary(Integer.parseInt(inputFourSplit2), x);

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

      carsPosition.put(inputThree, inputFourSplit);
    } else if (inputTwo.trim().equals("2")) {
      // start simulator
      // Simulator simulator = new Simulator();
      // simulator.StartSimulator();
    }

    // clean up
    scanner.close();
  }
}