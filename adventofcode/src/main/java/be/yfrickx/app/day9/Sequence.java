package be.yfrickx.app.day9;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Sequence {

    private final LinkedList<Integer> initialSequence;

    public Sequence(String sequenceString) {
        initialSequence = Arrays.stream(sequenceString.split(" ")).map(Integer::parseInt).collect(Collectors.toCollection(LinkedList::new));
    }

    public int getNextValue() {
        LinkedList<LinkedList<Integer>> listOfLists = new LinkedList<>();
        listOfLists.add(initialSequence);

        while (!listOfLists.getLast().stream().allMatch(Predicate.isEqual(0))) {
            listOfLists.add(generateNewIntermediateSequence(listOfLists.getLast()));
        }

        for (int i = listOfLists.size()-2; i >= 0; i--) {
            int nextValue = calculateNextValue(listOfLists.get(i+1), listOfLists.get(i));
            listOfLists.get(i).add(nextValue);
        }

        return initialSequence.getLast();
    }

    public int getPreviousValue() {
        LinkedList<LinkedList<Integer>> listOfLists = new LinkedList<>();
        listOfLists.add(initialSequence);

        while (!listOfLists.getLast().stream().allMatch(Predicate.isEqual(0))) {
            listOfLists.add(generateNewIntermediateSequence(listOfLists.getLast()));
        }

        for (int i = listOfLists.size()-2; i >= 0; i--) {
            int previousValue = calculatePreviousValue(listOfLists.get(i+1), listOfLists.get(i));
            listOfLists.get(i).add(0, previousValue);
        }

        return initialSequence.getFirst();
    }

    private int calculateNextValue(LinkedList<Integer> previousList, LinkedList<Integer> nextList) {
        return nextList.getLast() + previousList.getLast();
    }

    private int calculatePreviousValue(LinkedList<Integer> previousList, LinkedList<Integer> nextList) {
        return nextList.getFirst() - previousList.getFirst();
    }

    private LinkedList<Integer> generateNewIntermediateSequence(LinkedList<Integer> sequence) {
        LinkedList<Integer> newSequence = new LinkedList<>();
        for (int i = 0; i < sequence.size()-1; i++) {
            newSequence.add(sequence.get(i+1) - sequence.get(i));
        }
        return newSequence;
    }
}
