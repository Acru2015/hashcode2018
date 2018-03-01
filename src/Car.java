import java.util.LinkedList;
import java.util.List;

public class Car {
    private Ride currentRide;
    private int distanceLeftRide;
    private List<Ride> ridesDone = new LinkedList<>();
    private int distanceLeftToPickup;
    private RideDoneListener rideDoneListener;

    public Car(Ride currentRide) {
        this.currentRide = currentRide;
        distanceLeftToPickup = Util.getDistance(new Point(0, 0), currentRide.getStartPoint());
        distanceLeftRide = currentRide.getDistance();
    }

    public void oneRound() {
        if (distanceLeftToPickup == 0) {
            if (distanceLeftRide == 0) {
                startNewRide();
            } else {
                distanceLeftRide--;
            }
        } else {
            distanceLeftToPickup--;
        }

    }

    private void startNewRide() {
        Ride newRide = rideDoneListener.getNewRide();
        if (currentRide != null) {
            ridesDone.add(currentRide);
        }
        if (newRide != null) {
            distanceLeftToPickup = Util.getDistance(currentRide.getEndPoint(), newRide.getStartPoint());
            distanceLeftRide = newRide.getDistance();
            currentRide = newRide;
        } else {
            currentRide = null;
            return;
        }
    }

    public void setRideDoneListener(RideDoneListener rideDoneListener) {
        this.rideDoneListener = rideDoneListener;
    }

    public void printRidesDone() {
        System.out.print("" + ridesDone.size() + " ");
        for (Ride ride : ridesDone) {
            System.out.print(ride.getId() + " ");
        }
//        if (currentRide != null) {
//            System.out.print(currentRide.getId() + " not finished " + distanceLeftRide);
//        }
        System.out.println();
    }

    interface RideDoneListener {
        Ride getNewRide();
    }
}
