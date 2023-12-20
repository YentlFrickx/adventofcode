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

        @Override
        public String toString() {
            return String.format("Deststart: %s, sourceStart: %s, rangeLength: %s", this.destStart, this.sourceStart, this.rangeLength);
        }
    }

    private final List<Entry> entryList = new ArrayList<>();

    public Mapper(List<String> inputLines) {
        inputLines.forEach( line -> {
            String[] lineArr = line.split(" ");
            entryList.add(new Entry(Long.parseLong(lineArr[0]), Long.parseLong(lineArr[1]), Long.parseLong(lineArr[2])));
        });
    }

    public long map(long source, boolean log) {
        Optional<Entry> optEntry = entryList.stream().filter(entry -> entry.isApplicable(source)).findFirst();
        if (log) {
            if (optEntry.isPresent()) {
                System.out.printf("%s for %s result: %s\n",optEntry.get(), source, optEntry.get().map(source));
            } else {
                System.out.printf("No entry, returning source %s\n", source);
            }
        }
        return optEntry.map(entry -> entry.map(source)).orElse(source);
    }
}
