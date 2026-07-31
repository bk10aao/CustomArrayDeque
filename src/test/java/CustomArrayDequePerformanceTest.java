import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CustomArrayDequePerformanceTest {

    private static final int ITERATIONS = 100;
    private static final int STRUCTURAL_ITERATIONS = 10;
    private static final int WARMUP_RUNS = 20000;

    private static long longBlackhole = 0;
    private static boolean boolBlackhole = false;
    private static int intBlackhole = 0;
    private static Object objBlackhole = null;

    public static void main(String[] args) {
        int[] sizes = { 10000, 20000, 30000, 40000, 50000, 60000, 70000, 80000, 90000, 100000 };

        long[][] results = new long[sizes.length][];
        Random random = new Random();

        System.out.println("Warming up JIT Compiler...");
        runGlobalWarmup(random);
        System.out.println("Warm-up complete. Starting full execution.");

        for (int i = 0; i < sizes.length; i++) {
            int size = sizes[i];
            System.out.println("Benchmarking size: " + size);

            long addFirstTime = benchmarkAddFirst(size, random);
            long addLastTime = benchmarkAddLast(size, random);
            long getFirstTime = benchmarkGetFirst(size);
            long getLastTime = benchmarkGetLast(size);
            long removeFirstTime = benchmarkRemoveFirst(size, random);
            long removeLastTime = benchmarkRemoveLast(size, random);
            long containsTime = benchmarkContains(size, random);
            long peekFirstTime = benchmarkPeekFirst(size);
            long peekLastTime = benchmarkPeekLast(size);
            long sizeTime = benchmarkSize(size);
            long isEmptyTime = benchmarkIsEmpty(size);
            long clearTime = benchmarkClear(size);
            long toArrayTime = benchmarkToArray(size);
            long equalsTime = benchmarkEquals(size);
            long toStringTime = benchmarkToString(size);
            long iteratorTime = benchmarkIterator(size);
            long descendingIteratorTime = benchmarkDescendingIterator(size);
            long addAllTime = benchmarkAddAll(size, random);
            long removeAllTime = benchmarkRemoveAll(size, random);
            long retainAllTime = benchmarkRetainAll(size, random);

            results[i] = new long[]{
                    size, addFirstTime, addLastTime, getFirstTime, getLastTime, removeFirstTime,
                    removeLastTime, containsTime, peekFirstTime, peekLastTime, sizeTime,
                    isEmptyTime, clearTime, toArrayTime, equalsTime, toStringTime,
                    iteratorTime, descendingIteratorTime, addAllTime, removeAllTime, retainAllTime
            };
        }

        writeResultsToCSV(sizes, results);

        if (boolBlackhole && longBlackhole == 9999) {
            System.out.println("Sink data checksum: " + intBlackhole);
        }
    }

    private static void runGlobalWarmup(Random random) {
        CustomArrayDeque<Integer> warmDeque = new CustomArrayDeque<>();
        for (int i = 0; i < WARMUP_RUNS; i++) {
            warmDeque.addLast(i);
            warmDeque.addFirst(i);
            boolBlackhole ^= warmDeque.contains(i);
            objBlackhole = warmDeque.peekFirst();
            objBlackhole = warmDeque.peekLast();
        }
        for (int i = 0; i < WARMUP_RUNS / 2; i++) {
            warmDeque.removeFirst();
            warmDeque.removeLast();
        }
        System.gc();
    }

    private static long benchmarkAddFirst(int size, Random random) {
        long totalElapsedTime = 0;
        for (int iter = 0; iter < STRUCTURAL_ITERATIONS; iter++) {
            CustomArrayDeque<Integer> deque = new CustomArrayDeque<>();
            populateDeque(deque, size);
            long start = System.nanoTime();
            deque.addFirst(random.nextInt());
            totalElapsedTime += (System.nanoTime() - start);
        }
        return totalElapsedTime / STRUCTURAL_ITERATIONS;
    }

    private static long benchmarkAddLast(int size, Random random) {
        long totalElapsedTime = 0;
        for (int iter = 0; iter < STRUCTURAL_ITERATIONS; iter++) {
            CustomArrayDeque<Integer> deque = new CustomArrayDeque<>();
            populateDeque(deque, size);
            long start = System.nanoTime();
            deque.addLast(random.nextInt());
            totalElapsedTime += (System.nanoTime() - start);
        }
        return totalElapsedTime / STRUCTURAL_ITERATIONS;
    }

    private static long benchmarkGetFirst(int size) {
        long totalElapsedTime = 0;
        CustomArrayDeque<Integer> deque = new CustomArrayDeque<>();
        populateDeque(deque, size);
        for (int iter = 0; iter < ITERATIONS; iter++) {
            long start = System.nanoTime();
            Integer val = deque.getFirst();
            totalElapsedTime += (System.nanoTime() - start);
            if (val != null) intBlackhole += val;
        }
        return totalElapsedTime / ITERATIONS;
    }

    private static long benchmarkGetLast(int size) {
        long totalElapsedTime = 0;
        CustomArrayDeque<Integer> deque = new CustomArrayDeque<>();
        populateDeque(deque, size);
        for (int iter = 0; iter < ITERATIONS; iter++) {
            long start = System.nanoTime();
            Integer val = deque.getLast();
            totalElapsedTime += (System.nanoTime() - start);
            if (val != null) intBlackhole += val;
        }
        return totalElapsedTime / ITERATIONS;
    }

    private static long benchmarkRemoveFirst(int size, Random random) {
        long totalElapsedTime = 0;
        for (int iter = 0; iter < STRUCTURAL_ITERATIONS; iter++) {
            CustomArrayDeque<Integer> deque = new CustomArrayDeque<>();
            populateDeque(deque, size);
            long start = System.nanoTime();
            deque.removeFirst();
            totalElapsedTime += (System.nanoTime() - start);
        }
        return totalElapsedTime / STRUCTURAL_ITERATIONS;
    }

    private static long benchmarkRemoveLast(int size, Random random) {
        long totalElapsedTime = 0;
        for (int iter = 0; iter < STRUCTURAL_ITERATIONS; iter++) {
            CustomArrayDeque<Integer> deque = new CustomArrayDeque<>();
            populateDeque(deque, size);
            long start = System.nanoTime();
            deque.removeLast();
            totalElapsedTime += (System.nanoTime() - start);
        }
        return totalElapsedTime / STRUCTURAL_ITERATIONS;
    }

    private static long benchmarkContains(int size, Random random) {
        long totalElapsedTime = 0;
        CustomArrayDeque<Integer> deque = new CustomArrayDeque<>();
        populateDeque(deque, size);
        for (int iter = 0; iter < ITERATIONS; iter++) {
            int target = random.nextInt(size * 2);
            long start = System.nanoTime();
            boolean checked = deque.contains(target);
            totalElapsedTime += (System.nanoTime() - start);
            boolBlackhole ^= checked;
        }
        return totalElapsedTime / ITERATIONS;
    }

    private static long benchmarkPeekFirst(int size) {
        long totalElapsedTime = 0;
        CustomArrayDeque<Integer> deque = new CustomArrayDeque<>();
        populateDeque(deque, size);
        for (int iter = 0; iter < ITERATIONS; iter++) {
            long start = System.nanoTime();
            Integer val = deque.peekFirst();
            totalElapsedTime += (System.nanoTime() - start);
            if (val != null) intBlackhole += val;
        }
        return totalElapsedTime / ITERATIONS;
    }

    private static long benchmarkPeekLast(int size) {
        long totalElapsedTime = 0;
        CustomArrayDeque<Integer> deque = new CustomArrayDeque<>();
        populateDeque(deque, size);
        for (int iter = 0; iter < ITERATIONS; iter++) {
            long start = System.nanoTime();
            Integer val = deque.peekLast();
            totalElapsedTime += (System.nanoTime() - start);
            if (val != null) intBlackhole += val;
        }
        return totalElapsedTime / ITERATIONS;
    }

    private static long benchmarkSize(int size) {
        long totalElapsedTime = 0;
        CustomArrayDeque<Integer> deque = new CustomArrayDeque<>();
        populateDeque(deque, size);
        for (int iter = 0; iter < ITERATIONS; iter++) {
            long start = System.nanoTime();
            int s = deque.size();
            totalElapsedTime += (System.nanoTime() - start);
            intBlackhole += s;
        }
        return totalElapsedTime / ITERATIONS;
    }

    private static long benchmarkIsEmpty(int size) {
        long totalElapsedTime = 0;
        CustomArrayDeque<Integer> deque = new CustomArrayDeque<>();
        populateDeque(deque, size);
        for (int iter = 0; iter < ITERATIONS; iter++) {
            long start = System.nanoTime();
            boolean empty = deque.isEmpty();
            totalElapsedTime += (System.nanoTime() - start);
            boolBlackhole ^= empty;
        }
        return totalElapsedTime / ITERATIONS;
    }

    private static long benchmarkClear(int size) {
        long totalElapsedTime = 0;
        for (int iter = 0; iter < STRUCTURAL_ITERATIONS; iter++) {
            CustomArrayDeque<Integer> deque = new CustomArrayDeque<>();
            populateDeque(deque, size);
            long start = System.nanoTime();
            deque.clear();
            totalElapsedTime += (System.nanoTime() - start);
        }
        return totalElapsedTime / STRUCTURAL_ITERATIONS;
    }

    private static long benchmarkToArray(int size) {
        long totalElapsedTime = 0;
        CustomArrayDeque<Integer> deque = new CustomArrayDeque<>();
        populateDeque(deque, size);
        for (int iter = 0; iter < ITERATIONS; iter++) {
            long start = System.nanoTime();
            Object[] arr = deque.toArray();
            totalElapsedTime += (System.nanoTime() - start);
            intBlackhole += arr.length;
        }
        return totalElapsedTime / ITERATIONS;
    }

    private static long benchmarkEquals(int size) {
        long totalElapsedTime = 0;
        CustomArrayDeque<Integer> deque1 = new CustomArrayDeque<>();
        CustomArrayDeque<Integer> deque2 = new CustomArrayDeque<>();
        populateDeque(deque1, size);
        populateDeque(deque2, size);
        for (int iter = 0; iter < ITERATIONS; iter++) {
            long start = System.nanoTime();
            boolean equals = deque1.equals(deque2);
            totalElapsedTime += (System.nanoTime() - start);
            boolBlackhole ^= equals;
        }
        return totalElapsedTime / ITERATIONS;
    }

    private static long benchmarkToString(int size) {
        long totalElapsedTime = 0;
        CustomArrayDeque<Integer> deque = new CustomArrayDeque<>();
        populateDeque(deque, size);
        for (int iter = 0; iter < STRUCTURAL_ITERATIONS; iter++) {
            long start = System.nanoTime();
            String s = deque.toString();
            totalElapsedTime += (System.nanoTime() - start);
            intBlackhole += s.length();
        }
        return totalElapsedTime / STRUCTURAL_ITERATIONS;
    }

    private static long benchmarkIterator(int size) {
        long totalElapsedTime = 0;
        CustomArrayDeque<Integer> deque = new CustomArrayDeque<>();
        populateDeque(deque, size);
        for (int iter = 0; iter < STRUCTURAL_ITERATIONS; iter++) {
            long start = System.nanoTime();
            var iterator = deque.iterator();
            while (iterator.hasNext()) {
                intBlackhole += iterator.next();
            }
            totalElapsedTime += (System.nanoTime() - start);
        }
        return totalElapsedTime / STRUCTURAL_ITERATIONS;
    }

    private static long benchmarkDescendingIterator(int size) {
        long totalElapsedTime = 0;
        CustomArrayDeque<Integer> deque = new CustomArrayDeque<>();
        populateDeque(deque, size);
        for (int iter = 0; iter < STRUCTURAL_ITERATIONS; iter++) {
            long start = System.nanoTime();
            var iterator = deque.descendingIterator();
            while (iterator.hasNext()) {
                intBlackhole += iterator.next();
            }
            totalElapsedTime += (System.nanoTime() - start);
        }
        return totalElapsedTime / STRUCTURAL_ITERATIONS;
    }

    private static long benchmarkAddAll(int size, Random random) {
        long totalElapsedTime = 0;
        for (int iter = 0; iter < STRUCTURAL_ITERATIONS; iter++) {
            CustomArrayDeque<Integer> deque = new CustomArrayDeque<>();
            List<Integer> source = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                source.add(random.nextInt());
            }
            long start = System.nanoTime();
            deque.addAll(source);
            totalElapsedTime += (System.nanoTime() - start);
        }
        return totalElapsedTime / STRUCTURAL_ITERATIONS;
    }

    private static long benchmarkRemoveAll(int size, Random random) {
        long totalElapsedTime = 0;
        for (int iter = 0; iter < STRUCTURAL_ITERATIONS; iter++) {
            CustomArrayDeque<Integer> deque = new CustomArrayDeque<>();
            List<Integer> targetList = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                int val = i;
                deque.addLast(val);
                if (i % 2 == 0) {
                    targetList.add(val);
                }
            }
            long start = System.nanoTime();
            deque.removeAll(targetList);
            totalElapsedTime += (System.nanoTime() - start);
        }
        return totalElapsedTime / STRUCTURAL_ITERATIONS;
    }

    private static long benchmarkRetainAll(int size, Random random) {
        long totalElapsedTime = 0;
        for (int iter = 0; iter < STRUCTURAL_ITERATIONS; iter++) {
            CustomArrayDeque<Integer> deque = new CustomArrayDeque<>();
            List<Integer> targetList = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                int val = i;
                deque.addLast(val);
                if (i % 2 == 0) {
                    targetList.add(val);
                }
            }
            long start = System.nanoTime();
            deque.retainAll(targetList);
            totalElapsedTime += (System.nanoTime() - start);
        }
        return totalElapsedTime / STRUCTURAL_ITERATIONS;
    }

    private static void populateDeque(CustomArrayDeque<Integer> deque, int size) {
        for (int i = 0; i < size; i++) {
            deque.addLast(i);
        }
    }

    private static void writeResultsToCSV(int[] sizes, long[][] results) {
        String csvFile = "CustomArrayDeque_performance.csv";
        try (FileWriter writer = new FileWriter(csvFile)) {
            writer.append("Size;addFirst(E);addLast(E);getFirst();getLast();removeFirst();removeLast();"
                    + "contains(Object);peekFirst();peekLast();size();isEmpty();clear();toArray();"
                    + "equals(Object);toString();iterator();descendingIterator();addAll(Collection);"
                    + "removeAll(Collection);retainAll(Collection)\n");

            for (long[] row : results) {
                StringBuilder sb = new StringBuilder();
                sb.append(row[0]);
                for (int j = 1; j < row.length; j++) {
                    sb.append(";").append(row[j]);
                }
                sb.append("\n");
                writer.append(sb.toString());
            }
            System.out.println("Results successfully documented in " + csvFile);
        } catch (IOException e) {
            System.err.println("Failed to write performance records to CSV file: " + e.getMessage());
        }
    }
}