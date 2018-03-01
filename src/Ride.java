public class Ride {
    private int id;
    private Point startPoint;
    private Point endPoint;
    private long earliestTime;
    private long lastTimeToFinish;

    private Ride(Builder builder) {
        this.id = builder.id;
        this.startPoint = builder.startPoint;
        this.endPoint = builder.endPoint;
        this.earliestTime = builder.earliestTime;
        this.lastTimeToFinish = builder.lastTimeToFinish;
    }

    public static Builder newRide() {
        return new Builder();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Point getStartPoint() {
        return startPoint;
    }

    public void setStartPoint(Point startPoint) {
        this.startPoint = startPoint;
    }

    public Point getEndPoint() {
        return endPoint;
    }

    public void setEndPoint(Point endPoint) {
        this.endPoint = endPoint;
    }

    public long getEarliestTime() {
        return earliestTime;
    }

    public void setEarliestTime(long earliestTime) {
        this.earliestTime = earliestTime;
    }

    public long getLastTimeToFinish() {
        return lastTimeToFinish;
    }

    public void setLastTimeToFinish(long lastTimeToFinish) {
        this.lastTimeToFinish = lastTimeToFinish;
    }

    public int getDistance() {
        return Util.getDistance(startPoint, endPoint);
    }

    public static final class Builder {
        private int id;
        private Point startPoint;
        private Point endPoint;
        private long earliestTime;
        private long lastTimeToFinish;

        private Builder() {
        }

        public Ride build() {
            return new Ride(this);
        }

        public Builder id(int id) {
            this.id = id;
            return this;
        }

        public Builder startPoint(Point startPoint) {
            this.startPoint = startPoint;
            return this;
        }

        public Builder endPoint(Point endPoint) {
            this.endPoint = endPoint;
            return this;
        }

        public Builder earliestTime(long earliestTime) {
            this.earliestTime = earliestTime;
            return this;
        }

        public Builder lastTimeToFinish(long lastTimeToFinish) {
            this.lastTimeToFinish = lastTimeToFinish;
            return this;
        }
    }
}
