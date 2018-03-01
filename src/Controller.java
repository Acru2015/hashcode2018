import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

public class Controller implements Car.RideDoneListener {
    static long[] stepsArray = {10, 25000, 20000, 50000, 150000};
    static int[] amountCarsArray = {2, 100, 81, 400, 350};
    static List<Ride> rideList;
    static List<Car> cars;
    static String[] fileNames = {"a_example.in", "b_should_be_easy.in", "c_no_hurry.in", "d_metropolis.in", "e_high_bonus.in"};
    long currentTime = 0;

    public void start() {
        for (int round = 0; round < 4; round++) {
            cars = new LinkedList<>();
            int amountCars = amountCarsArray[round];
            long steps = stepsArray[round];
            String fileName = fileNames[round];
            Ride[] rides = InputParser.getRidesFor(fileName);
            Arrays.sort(rides, Comparator.comparingLong(Ride::getEarliestTime));
            List<Ride> temp = Arrays.asList(rides);
            rideList = new LinkedList<>(temp);
            for (int i = 0; i < amountCars; i++) {
                Car toAdd = new Car(getNewRide(new Point(0, 0)));
                toAdd.setRideDoneListener(this);
                cars.add(toAdd);
            }
            for (currentTime = 0; currentTime < steps; currentTime++) {
                for (Car car : cars) {
                    car.oneRound();
                }
            }
            try {
                System.setOut(new PrintStream(fileName + ".solucion"));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            for (Car car : cars) {
                car.printRidesDone();
            }
        }
    }

    @Override
    public Ride getNewRide(Point currentPosition) {
        if (rideList.size() > 0) {
            Ride toReturn = null;
            int distanceToRide = Integer.MAX_VALUE;
            rideList.sort(Comparator.comparingInt(ride -> Util.getDistance(currentPosition, ride.getStartPoint())));
            int i = 0;
            List<Ride> tempList = new LinkedList<>();
            while (!rideList.isEmpty()) {
                tempList.add(rideList.get(0));
                rideList.remove(0);
                i++;
                if (i == 10) {
                    break;
                }
            }
            tempList.sort(Comparator.comparingLong(Ride::getLastTimeToFinish));
            toReturn = tempList.get(0);
            tempList.remove(0);
            rideList.addAll(tempList);
            return toReturn;
        } else {
            return null;
        }
    }
}
