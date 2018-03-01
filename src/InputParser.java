import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class InputParser {

    public static Ride[] getRidesFor(String fileName) {
        Scanner scanner = null;
        try {
            scanner = new Scanner(new File(fileName));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        String firstLine = scanner.nextLine();
        String[] firstValues = firstLine.split(" ");
        Ride[] rides = new Ride[Integer.parseInt(firstValues[3])];
        int i = 0;
        while (scanner.hasNext()) {
            String rideRaw = scanner.nextLine();
            String[] rideRawArray = rideRaw.split(" ");
            Ride.Builder builder = Ride.newRide();
            builder.id(i);
            builder.startPoint(new Point(Integer.parseInt(rideRawArray[0]), Integer.parseInt(rideRawArray[1])));
            builder.endPoint(new Point(Integer.parseInt(rideRawArray[2]), Integer.parseInt(rideRawArray[3])));
            builder.earliestTime(Long.parseLong(rideRawArray[4]));
            builder.lastTimeToFinish(Long.parseLong(rideRawArray[5]));
            rides[i] = builder.build();
            i++;
        }
        return rides;
    }
}
