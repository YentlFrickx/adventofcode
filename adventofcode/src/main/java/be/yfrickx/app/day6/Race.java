package be.yfrickx.app.day6;

public class Race {
    long time;
    long distanceToBeat;

    public Race(long time, long distanceToBeat) {
        this.time = time;
        this.distanceToBeat = distanceToBeat;
    }

    public long getNumWaysToBeat() {
        long result = 0;

        for (long i = 1; i < this.time; i++) {
            if (beatsRecord(i)) {
                result += 1;
            }
        }

        return result;
    }

    private boolean beatsRecord(long holdSecs) {
        long remainingTime = this.time - holdSecs;
        long distance = remainingTime * holdSecs;
        return distance > distanceToBeat;
    }
}
