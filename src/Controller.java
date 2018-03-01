import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.*;
import java.util.concurrent.ConcurrentLinkedQueue;

public class Controller implements Car.RideDoneListener {
    static long steps = 150000;
    static int amountCars = 350;
    static Queue<Ride> ridesQueue;
    static List<Car> cars = new LinkedList<>();

    public void start() {
        String fileName = "e_high_bonus.in";
        Ride[] rides = InputParser.getRidesFor(fileName);
        Arrays.sort(rides, Comparator.comparingLong(Ride::getEarliestTime));
        List<Ride> temp = Arrays.asList(rides);
        ridesQueue = new ConcurrentLinkedQueue<>(temp);
        for (int i = 0; i < amountCars; i++) {
            Car toAdd = new Car(ridesQueue.remove());
            toAdd.setRideDoneListener(this);
            cars.add(toAdd);
        }
        for (long i = 0; i < steps; i++) {
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

    @Override
    public Ride getNewRide() {
        if (ridesQueue.size() > 0) {
            return ridesQueue.remove();
        } else {
            return null;
        }
    }
}
