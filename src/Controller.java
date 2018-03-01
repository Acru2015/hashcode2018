import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.*;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.ConcurrentLinkedQueue;

public class Controller implements Car.RideDoneListener {
    static long steps = 150000;
    static int amountCars = 350;
    static Deque<Ride> ridesQueue;
    static List<Car> cars = new LinkedList<>();
    long currentTime = 0;

    public void start() {
        String fileName = "e_high_bonus.in";
        Ride[] rides = InputParser.getRidesFor(fileName);
        Arrays.sort(rides, Comparator.comparingLong(Ride::getEarliestTime));
        List<Ride> temp = Arrays.asList(rides);
        ridesQueue = new ConcurrentLinkedDeque<>(temp);
        for (int i = 0; i < amountCars; i++) {
            Car toAdd = new Car(ridesQueue.remove());
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

    @Override
    public Ride getNewRide(Point currentPosition) {
        if (ridesQueue.size() > 0) {
            Queue<Ride> tempQueue = getFirst10();
            Ride nextRide = tempQueue.remove();
            int minDistance = Util.getDistance(currentPosition, nextRide.getStartPoint());
            while (!tempQueue.isEmpty()) {
                Ride tempRide = tempQueue.remove();
                if (Util.getDistance(currentPosition, tempRide.getStartPoint()) < minDistance) {
                    ridesQueue.addFirst(nextRide);
                    nextRide = tempRide;
                    minDistance = Util.getDistance(currentPosition, nextRide.getStartPoint());
                } else {
                    ridesQueue.addFirst(tempRide);
                }
            }
            return nextRide;
        } else {
            return null;
        }
    }

    private Queue<Ride> getFirst10() {
        Queue<Ride> temp = new ConcurrentLinkedQueue<>();
        int j = 0;
        while (!ridesQueue.isEmpty()) {
            temp.add(ridesQueue.remove());
            j++;
            if (j == 10) {
                return temp;
            }
        }
        return temp;
    }
}
