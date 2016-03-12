package algorithms;

import java.util.Iterator;
import java.util.LinkedList;

public class NQueens {
    public long countValidCombinations(int numQueens, int boardSize) {
        if(numQueens < 0 || boardSize < 0) {
            throw new IllegalArgumentException(String.format("Invalid numQueens [%d], or boardSize [%d]", numQueens, boardSize));
        }
        if(numQueens != boardSize) {
            throw new IllegalArgumentException(String.format("Expected numQueens [%d] be equal to boardSize [%d]", numQueens, boardSize));
        }
        return solve(numQueens, boardSize, new LinkedList<Integer>());
    }

    private long solve(int numQueens, int boardSize, LinkedList<Integer> prevPositions) {
        if(numQueens == 0) {
            return 1;
        }
        long totalValidCombinations = 0;
        for(int position = 0; position < boardSize; position++) {
            if(isValid(position, prevPositions)) {
                prevPositions.add(position);
                totalValidCombinations += solve(numQueens-1, boardSize, prevPositions);
                prevPositions.removeLast();
            }
        }
        return totalValidCombinations;
    }

    private boolean isValid(int position, LinkedList<Integer> prevPositions) {
        final Iterator<Integer> reverseIterator = prevPositions.descendingIterator();
        int level = 0;
        while (reverseIterator.hasNext()) {
            ++level;
            int prevPosition = reverseIterator.next();
            if(prevPosition == position) return false;
            if(Math.abs(prevPosition - position) == level) return false;
        }
        return true;
    }
}
