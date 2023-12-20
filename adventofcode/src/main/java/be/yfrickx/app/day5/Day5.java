package be.yfrickx.app.day5;

import be.yfrickx.app.day4.Day4;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Day5 {

    public static String solve() {
        ClassLoader classLoader = Day5.class.getClassLoader();
        File file = new File(classLoader.getResource("day5.txt").getFile());
        List<String> lineList;
        try {
            lineList = FileUtils.readLines(file, "UTF-8");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return parseLines(lineList);
    }

    private static String parseLines(List<String> lineList) {
        int seedSoilStart = lineList.indexOf("seed-to-soil map:")+1;
        int seedSoilEnd = lineList.indexOf("soil-to-fertilizer map:")-1;
        Mapper seedSoilMapper = new Mapper(lineList.subList(seedSoilStart, seedSoilEnd));


        int soilFertStart = lineList.indexOf("soil-to-fertilizer map:")+1;
        int soilFertEnd = lineList.indexOf("fertilizer-to-water map:")-1;
        Mapper soilFertmapper = new Mapper(lineList.subList(soilFertStart, soilFertEnd));


        int fertWaterStart = lineList.indexOf("fertilizer-to-water map:")+1;
        int fertWaterEnd = lineList.indexOf("water-to-light map:")-1;
        Mapper fertWaterMapper = new Mapper(lineList.subList(fertWaterStart, fertWaterEnd));


        int waterLightStart = lineList.indexOf("water-to-light map:")+1;
        int waterLightEnd = lineList.indexOf("light-to-temperature map:")-1;
        Mapper waterLightMapper = new Mapper(lineList.subList(waterLightStart, waterLightEnd));


        int lightTempStart = lineList.indexOf("light-to-temperature map:")+1;
        int lightTempEnd = lineList.indexOf("temperature-to-humidity map:")-1;
        Mapper lightTempMapper = new Mapper(lineList.subList(lightTempStart, lightTempEnd));


        int tempHumStart = lineList.indexOf("temperature-to-humidity map:")+1;
        int tempHumEnd = lineList.indexOf("humidity-to-location map:")-1;
        Mapper tempHumMapper = new Mapper(lineList.subList(tempHumStart, tempHumEnd));


        int humLocStart = lineList.indexOf("humidity-to-location map:")+1;
        int humLocEnd = lineList.size()-1;
        Mapper humLocMapper = new Mapper(lineList.subList(humLocStart, humLocEnd));

//        long lowest = Long.MAX_VALUE;
//        for (Long seed : seeds) {
//            long location = mapToLocation(seedSoilMapper, soilFertmapper, fertWaterMapper, waterLightMapper, lightTempMapper, tempHumMapper, humLocMapper, seed);
//            if (location < lowest) {
//                lowest = location;
//            }
//        }

        long lowest = Long.MAX_VALUE;
        String cleanedLine = lineList.get(0).replace("seeds: ", "");
        List<Long> split = Arrays.stream(cleanedLine.split(" ")).map(Long::parseLong).collect(Collectors.toList());

        //part A
//        for (long seed : split) {
//            long location = mapToLocation(seedSoilMapper, soilFertmapper, fertWaterMapper, waterLightMapper, lightTempMapper, tempHumMapper, humLocMapper, seed);
//            if (location < lowest) {
//                lowest = location;
//                System.out.println();
//                System.out.printf("Printing for seed: %s\n", seed);
//                mapToLocation(seedSoilMapper, soilFertmapper, fertWaterMapper, waterLightMapper, lightTempMapper, tempHumMapper, humLocMapper, seed, true);
//            }
//        }

        for (int i = 0; i < split.size(); i+=2) {
            for (long j = 0; j < split.get(i + 1); j++) {
                long seed = split.get(i) + j;
                long location = mapToLocation(seedSoilMapper, soilFertmapper, fertWaterMapper, waterLightMapper, lightTempMapper, tempHumMapper, humLocMapper, seed);
                if (location < lowest) {
                    lowest = location;
//                    System.out.println();
//                    System.out.printf("Printing for seed: %s\n", seed);
//                    System.out.printf("Original: %s, Offset: %s/%s, Adjusted: %s \n", split.get(i), j, split.get(i + 1), seed);
//                    mapToLocation(seedSoilMapper, soilFertmapper, fertWaterMapper, waterLightMapper, lightTempMapper, tempHumMapper, humLocMapper, seed, true);
                }
            }
        }

        return String.valueOf(lowest);
    }

    private static long mapToLocation(Mapper seedSoilMapper,
                                      Mapper soilFertmapper,
                                      Mapper fertWaterMapper,
                                      Mapper waterLightMapper,
                                      Mapper lightTempMapper,
                                      Mapper tempHumMapper,
                                      Mapper humLocMapper,
                                      long seed) {
        return mapToLocation(seedSoilMapper, soilFertmapper, fertWaterMapper, waterLightMapper, lightTempMapper, tempHumMapper, humLocMapper, seed, false);
    }

    private static long mapToLocation(Mapper seedSoilMapper,
                                      Mapper soilFertmapper,
                                      Mapper fertWaterMapper,
                                      Mapper waterLightMapper,
                                      Mapper lightTempMapper,
                                      Mapper tempHumMapper,
                                      Mapper humLocMapper,
                                      long seed,
                                      boolean log) {
        long soil = seedSoilMapper.map(seed, log);
        long fertilizer = soilFertmapper.map(soil, log);
        long water = fertWaterMapper.map(fertilizer, log);
        long light = waterLightMapper.map(water, log);
        long temperature = lightTempMapper.map(light, log);
        long humidity = tempHumMapper.map(temperature, log);
        return humLocMapper.map(humidity, log);
    }

}

