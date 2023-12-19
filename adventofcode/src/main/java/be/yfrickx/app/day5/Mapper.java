package be.yfrickx.app.day5;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

public class Mapper {

    private class Entry {
        long destStart;
        long sourceStart;
        long rangeLength;

        public Entry(long destStart, long sourceStart, long rangeLength) {
            this.destStart = destStart;
            this.sourceStart = sourceStart;
            this.rangeLength = rangeLength;
        }

        public boolean isApplicable(long source) {
            return source >= sourceStart && source <= sourceStart + rangeLength;
        }

        public long map(long source) {
            return (source - sourceStart) + destStart;
        }
    }

    private List<Entry> entryList = new ArrayList<>();

    public Mapper(List<String> inputLines) {
        inputLines.forEach( line -> {
            String[] lineArr = line.split(" ");
            entryList.add(new Entry(Long.parseLong(lineArr[0]), Long.parseLong(lineArr[1]), Long.parseLong(lineArr[2])));
        });
    }

    public long map(long source) {
        Optional<Entry> optEntry = entryList.stream().filter(entry -> entry.isApplicable(source)).findFirst();
        return optEntry.map(entry -> entry.map(source)).orElse(source);
    }
}
