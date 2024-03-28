package tehpeng.simulator;

import tehpeng.simulator.model.Car;
import tehpeng.simulator.service.SimulationService;
import tehpeng.simulator.util.MapUtil;

import java.util.*;

public class SimulatorApplication {
  public static void main(String[] args) {
    // Init
    Scanner scanner = new Scanner(System.in);
    SimulationService simulationService = new SimulationService();

    // Step 1: Request for dimension
    String[] inputBoundarySplit = simulationService.runInputBoundary(scanner);
    int inputBoundaryX = Integer.parseInt(inputBoundarySplit[0]);
    int inputBoundaryY = Integer.parseInt(inputBoundarySplit[1]);

    // keep details of the car
    // List<Car> lsCar = new ArrayList<>();
    HashMap<String, Car> lsCarMap = new HashMap<>();
    while (true) {
      // Step 2: Request for option
      String inputOption = simulationService.runInputOption(scanner, lsCarMap);

      int inputCarPositionSplitX = 0;
      int inputCarPositionSplitY = 0;
      String inputCarPositionSplitDirection = "";

      if (inputOption.trim().equals("1")) {
        // Step 3: Request for car
        String inputCarName = simulationService.runInputCarName(scanner);

        // Step 4: Request for car current position
        String[] inputCarPositionSplit = simulationService.runInputCarPosition(scanner, inputBoundaryX, inputBoundaryY,
            inputCarName);
        inputCarPositionSplitX = Integer.parseInt(inputCarPositionSplit[0]);
        inputCarPositionSplitY = Integer.parseInt(inputCarPositionSplit[1]);
        inputCarPositionSplitDirection = inputCarPositionSplit[2];

        // Step 5: Request for car command
        String inputCommand = simulationService.runInputCommand(scanner, inputCarName);

        // Add car to the list
        Car car = new Car(inputCarName, inputCarPositionSplitX, inputCarPositionSplitY,
            MapUtil.convertDirectionToIndex(inputCarPositionSplitDirection), inputCommand);
        lsCarMap.put(car.getName(), car);
      } else if (inputOption.trim().equals("2")) {
        // Step 6: Run simulation
        boolean simulationResult = simulationService.runSimulation(lsCarMap, inputBoundaryX, inputBoundaryY);
        if (simulationResult) {
          simulationService.runSimulationResult(lsCarMap);

          String inputEndingOption = simulationService.runInputEndingOption(scanner);
          if (inputEndingOption.trim().equals("2")) {
            simulationService.runExitScreen();
            break;
          } else if (inputEndingOption.trim().equals("1")) {
            lsCarMap = new HashMap<>();
          }
        }
      }
    }

    // clean up
    scanner.close();
  }
}