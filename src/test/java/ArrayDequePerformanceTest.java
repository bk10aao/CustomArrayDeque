import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.Random;

public class ArrayDequePerformanceTest {

    private static final int ITERATIONS = 100;
    private static final int STRUCTURAL_ITERATIONS = 10;
    private static final int WARMUP_RUNS = 20_000;

    private static long longBlackhole = 0;
    private static boolean boolBlackhole = false;
    private static int intBlackhole = 0;
    private static Object objBlackhole = null;

    public static void main(String[] args) {
        int[] sizes = {10_000, 20_000, 30_000, 40_000, 50_000,
                60_000, 70_000, 80_000, 90_000, 100_000};

        long[][] results = new long[sizes.length][];
        Random random = new Random(42);

        runGlobalWarmup(random);
        System.out.println("Warm-up complete. Starting benchmarks.");

        for (int i = 0; i < sizes.length; i++) {
            int size = sizes[i];
            System.out.println("Benchmarking size: " + size);

            results[i] = new long[]{
                    size,
                    // ----- insertion -----
                    benchmarkAdd(size, random),
                    benchmarkAddFirst(size, random),
                    benchmarkAddLast(size, random),
                    benchmarkOffer(size, random),
                    benchmarkOfferFirst(size, random),
                    benchmarkOfferLast(size, random),
                    benchmarkPush(size, random),
                    benchmarkAddAll(size, random),
                    // ----- access / query -----
                    benchmarkElement(size),
                    benchmarkGetFirst(size),
                    benchmarkGetLast(size),
                    benchmarkPeek(size),
                    benchmarkPeekFirst(size),
                    benchmarkPeekLast(size),
                    benchmarkContains(size, random),
                    benchmarkContainsAll(size, random),
                    benchmarkSize(size),
                    benchmarkIsEmpty(size),
                    // ----- removal -----
                    benchmarkRemove(size),
                    benchmarkRemoveFirst(size),
                    benchmarkRemoveLast(size),
                    benchmarkPoll(size),
                    benchmarkPollFirst(size),
                    benchmarkPollLast(size),
                    benchmarkPop(size),
                    benchmarkRemoveObject(size, random),
                    benchmarkRemoveFirstOccurrence(size, random),
                    benchmarkRemoveLastOccurrence(size, random),
                    benchmarkRemoveAll(size),
                    benchmarkRetainAll(size),
                    benchmarkClear(size),
                    // ----- conversion / views -----
                    benchmarkToArray(size),
                    benchmarkToArrayTyped(size),
                    benchmarkToString(size),
                    benchmarkEquals(size),
                    benchmarkIterator(size),
                    benchmarkDescendingIterator(size)
            };
        }

        writeResultsToCSV(results);
    }

    // =========================================================================
    // Factory
    // =========================================================================
    private static Deque<Integer> newDeque() {
        return new ArrayDeque<>();
    }

    private static void populate(Deque<Integer> d, int size) {
        for (int i = 0; i < size; i++) d.addLast(i);
    }

    // =========================================================================
    // Warm-up
    // =========================================================================
    private static void runGlobalWarmup(Random random) {
        Deque<Integer> w = newDeque();
        for (int i = 0; i < WARMUP_RUNS; i++) {
            w.add(i);
            w.addFirst(i);
            w.offer(i);
            w.offerFirst(i);
            w.push(i);
            boolBlackhole ^= w.contains(i);
            objBlackhole = w.peek();
            objBlackhole = w.peekFirst();
            objBlackhole = w.peekLast();
            objBlackhole = w.element();
        }
        for (int i = 0; i < WARMUP_RUNS / 4; i++) {
            w.poll();
            w.pollFirst();
            w.pollLast();
            w.pop();
            w.remove();
            w.removeFirst();
            w.removeLast();
        }
        System.gc();
    }

    // =========================================================================
    // Insertion
    // =========================================================================
    private static long benchmarkAdd(int size, Random r) {
        long t = 0;
        for (int i = 0; i < STRUCTURAL_ITERATIONS; i++) {
            Deque<Integer> d = newDeque();
            populate(d, size);
            long s = System.nanoTime();
            d.add(r.nextInt());
            t += System.nanoTime() - s;
        }
        return t / STRUCTURAL_ITERATIONS;
    }

    private static long benchmarkAddFirst(int size, Random r) {
        long t = 0;
        for (int i = 0; i < STRUCTURAL_ITERATIONS; i++) {
            Deque<Integer> d = newDeque();
            populate(d, size);
            long s = System.nanoTime();
            d.addFirst(r.nextInt());
            t += System.nanoTime() - s;
        }
        return t / STRUCTURAL_ITERATIONS;
    }

    private static long benchmarkAddLast(int size, Random r) {
        long t = 0;
        for (int i = 0; i < STRUCTURAL_ITERATIONS; i++) {
            Deque<Integer> d = newDeque();
            populate(d, size);
            long s = System.nanoTime();
            d.addLast(r.nextInt());
            t += System.nanoTime() - s;
        }
        return t / STRUCTURAL_ITERATIONS;
    }

    private static long benchmarkOffer(int size, Random r) {
        long t = 0;
        for (int i = 0; i < STRUCTURAL_ITERATIONS; i++) {
            Deque<Integer> d = newDeque();
            populate(d, size);
            long s = System.nanoTime();
            d.offer(r.nextInt());
            t += System.nanoTime() - s;
        }
        return t / STRUCTURAL_ITERATIONS;
    }

    private static long benchmarkOfferFirst(int size, Random r) {
        long t = 0;
        for (int i = 0; i < STRUCTURAL_ITERATIONS; i++) {
            Deque<Integer> d = newDeque();
            populate(d, size);
            long s = System.nanoTime();
            d.offerFirst(r.nextInt());
            t += System.nanoTime() - s;
        }
        return t / STRUCTURAL_ITERATIONS;
    }

    private static long benchmarkOfferLast(int size, Random r) {
        long t = 0;
        for (int i = 0; i < STRUCTURAL_ITERATIONS; i++) {
            Deque<Integer> d = newDeque();
            populate(d, size);
            long s = System.nanoTime();
            d.offerLast(r.nextInt());
            t += System.nanoTime() - s;
        }
        return t / STRUCTURAL_ITERATIONS;
    }

    private static long benchmarkPush(int size, Random r) {
        long t = 0;
        for (int i = 0; i < STRUCTURAL_ITERATIONS; i++) {
            Deque<Integer> d = newDeque();
            populate(d, size);
            long s = System.nanoTime();
            d.push(r.nextInt());
            t += System.nanoTime() - s;
        }
        return t / STRUCTURAL_ITERATIONS;
    }

    private static long benchmarkAddAll(int size, Random r) {
        long t = 0;
        for (int i = 0; i < STRUCTURAL_ITERATIONS; i++) {
            Deque<Integer> d = newDeque();
            List<Integer> src = new ArrayList<>(size);
            for (int j = 0; j < size; j++) src.add(r.nextInt());
            long s = System.nanoTime();
            d.addAll(src);
            t += System.nanoTime() - s;
        }
        return t / STRUCTURAL_ITERATIONS;
    }

    // =========================================================================
    // Access / Query
    // =========================================================================
    private static long benchmarkElement(int size) {
        long t = 0;
        Deque<Integer> d = newDeque();
        populate(d, size);
        for (int i = 0; i < ITERATIONS; i++) {
            long s = System.nanoTime();
            Integer v = d.element();
            t += System.nanoTime() - s;
            if (v != null) intBlackhole += v;
        }
        return t / ITERATIONS;
    }

    private static long benchmarkGetFirst(int size) {
        long t = 0;
        Deque<Integer> d = newDeque();
        populate(d, size);
        for (int i = 0; i < ITERATIONS; i++) {
            long s = System.nanoTime();
            Integer v = d.getFirst();
            t += System.nanoTime() - s;
            if (v != null) intBlackhole += v;
        }
        return t / ITERATIONS;
    }

    private static long benchmarkGetLast(int size) {
        long t = 0;
        Deque<Integer> d = newDeque();
        populate(d, size);
        for (int i = 0; i < ITERATIONS; i++) {
            long s = System.nanoTime();
            Integer v = d.getLast();
            t += System.nanoTime() - s;
            if (v != null) intBlackhole += v;
        }
        return t / ITERATIONS;
    }

    private static long benchmarkPeek(int size) {
        long t = 0;
        Deque<Integer> d = newDeque();
        populate(d, size);
        for (int i = 0; i < ITERATIONS; i++) {
            long s = System.nanoTime();
            Integer v = d.peek();
            t += System.nanoTime() - s;
            if (v != null) intBlackhole += v;
        }
        return t / ITERATIONS;
    }

    private static long benchmarkPeekFirst(int size) {
        long t = 0;
        Deque<Integer> d = newDeque();
        populate(d, size);
        for (int i = 0; i < ITERATIONS; i++) {
            long s = System.nanoTime();
            Integer v = d.peekFirst();
            t += System.nanoTime() - s;
            if (v != null) intBlackhole += v;
        }
        return t / ITERATIONS;
    }

    private static long benchmarkPeekLast(int size) {
        long t = 0;
        Deque<Integer> d = newDeque();
        populate(d, size);
        for (int i = 0; i < ITERATIONS; i++) {
            long s = System.nanoTime();
            Integer v = d.peekLast();
            t += System.nanoTime() - s;
            if (v != null) intBlackhole += v;
        }
        return t / ITERATIONS;
    }

    private static long benchmarkContains(int size, Random r) {
        long t = 0;
        Deque<Integer> d = newDeque();
        populate(d, size);
        for (int i = 0; i < ITERATIONS; i++) {
            int target = r.nextInt(size * 2);
            long s = System.nanoTime();
            boolBlackhole ^= d.contains(target);
            t += System.nanoTime() - s;
        }
        return t / ITERATIONS;
    }

    private static long benchmarkContainsAll(int size, Random r) {
        long t = 0;
        Deque<Integer> d = newDeque();
        populate(d, size);
        List<Integer> probe = new ArrayList<>();
        for (int i = 0; i < Math.min(100, size); i++) probe.add(r.nextInt(size));
        for (int i = 0; i < ITERATIONS; i++) {
            long s = System.nanoTime();
            boolBlackhole ^= d.containsAll(probe);
            t += System.nanoTime() - s;
        }
        return t / ITERATIONS;
    }

    private static long benchmarkSize(int size) {
        long t = 0;
        Deque<Integer> d = newDeque();
        populate(d, size);
        for (int i = 0; i < ITERATIONS; i++) {
            long s = System.nanoTime();
            intBlackhole += d.size();
            t += System.nanoTime() - s;
        }
        return t / ITERATIONS;
    }

    private static long benchmarkIsEmpty(int size) {
        long t = 0;
        Deque<Integer> d = newDeque();
        populate(d, size);
        for (int i = 0; i < ITERATIONS; i++) {
            long s = System.nanoTime();
            boolBlackhole ^= d.isEmpty();
            t += System.nanoTime() - s;
        }
        return t / ITERATIONS;
    }

    // =========================================================================
    // Removal
    // =========================================================================
    private static long benchmarkRemove(int size) {
        long t = 0;
        for (int i = 0; i < STRUCTURAL_ITERATIONS; i++) {
            Deque<Integer> d = newDeque();
            populate(d, size);
            long s = System.nanoTime();
            d.remove();
            t += System.nanoTime() - s;
        }
        return t / STRUCTURAL_ITERATIONS;
    }

    private static long benchmarkRemoveFirst(int size) {
        long t = 0;
        for (int i = 0; i < STRUCTURAL_ITERATIONS; i++) {
            Deque<Integer> d = newDeque();
            populate(d, size);
            long s = System.nanoTime();
            d.removeFirst();
            t += System.nanoTime() - s;
        }
        return t / STRUCTURAL_ITERATIONS;
    }

    private static long benchmarkRemoveLast(int size) {
        long t = 0;
        for (int i = 0; i < STRUCTURAL_ITERATIONS; i++) {
            Deque<Integer> d = newDeque();
            populate(d, size);
            long s = System.nanoTime();
            d.removeLast();
            t += System.nanoTime() - s;
        }
        return t / STRUCTURAL_ITERATIONS;
    }

    private static long benchmarkPoll(int size) {
        long t = 0;
        for (int i = 0; i < STRUCTURAL_ITERATIONS; i++) {
            Deque<Integer> d = newDeque();
            populate(d, size);
            long s = System.nanoTime();
            objBlackhole = d.poll();
            t += System.nanoTime() - s;
        }
        return t / STRUCTURAL_ITERATIONS;
    }

    private static long benchmarkPollFirst(int size) {
        long t = 0;
        for (int i = 0; i < STRUCTURAL_ITERATIONS; i++) {
            Deque<Integer> d = newDeque();
            populate(d, size);
            long s = System.nanoTime();
            objBlackhole = d.pollFirst();
            t += System.nanoTime() - s;
        }
        return t / STRUCTURAL_ITERATIONS;
    }

    private static long benchmarkPollLast(int size) {
        long t = 0;
        for (int i = 0; i < STRUCTURAL_ITERATIONS; i++) {
            Deque<Integer> d = newDeque();
            populate(d, size);
            long s = System.nanoTime();
            objBlackhole = d.pollLast();
            t += System.nanoTime() - s;
        }
        return t / STRUCTURAL_ITERATIONS;
    }

    private static long benchmarkPop(int size) {
        long t = 0;
        for (int i = 0; i < STRUCTURAL_ITERATIONS; i++) {
            Deque<Integer> d = newDeque();
            populate(d, size);
            long s = System.nanoTime();
            d.pop();
            t += System.nanoTime() - s;
        }
        return t / STRUCTURAL_ITERATIONS;
    }

    private static long benchmarkRemoveObject(int size, Random r) {
        long t = 0;
        for (int i = 0; i < STRUCTURAL_ITERATIONS; i++) {
            Deque<Integer> d = newDeque();
            populate(d, size);
            int target = r.nextInt(size);
            long s = System.nanoTime();
            boolBlackhole ^= d.remove(target);
            t += System.nanoTime() - s;
        }
        return t / STRUCTURAL_ITERATIONS;
    }

    private static long benchmarkRemoveFirstOccurrence(int size, Random r) {
        long t = 0;
        for (int i = 0; i < STRUCTURAL_ITERATIONS; i++) {
            Deque<Integer> d = newDeque();
            populate(d, size);
            int target = r.nextInt(size);
            long s = System.nanoTime();
            boolBlackhole ^= d.removeFirstOccurrence(target);
            t += System.nanoTime() - s;
        }
        return t / STRUCTURAL_ITERATIONS;
    }

    private static long benchmarkRemoveLastOccurrence(int size, Random r) {
        long t = 0;
        for (int i = 0; i < STRUCTURAL_ITERATIONS; i++) {
            Deque<Integer> d = newDeque();
            populate(d, size);
            int target = r.nextInt(size);
            long s = System.nanoTime();
            boolBlackhole ^= d.removeLastOccurrence(target);
            t += System.nanoTime() - s;
        }
        return t / STRUCTURAL_ITERATIONS;
    }

    private static long benchmarkRemoveAll(int size) {
        long t = 0;
        for (int i = 0; i < STRUCTURAL_ITERATIONS; i++) {
            Deque<Integer> d = newDeque();
            List<Integer> half = new ArrayList<>();
            for (int j = 0; j < size; j++) {
                d.addLast(j);
                if (j % 2 == 0) half.add(j);
            }
            long s = System.nanoTime();
            d.removeAll(half);
            t += System.nanoTime() - s;
        }
        return t / STRUCTURAL_ITERATIONS;
    }

    private static long benchmarkRetainAll(int size) {
        long t = 0;
        for (int i = 0; i < STRUCTURAL_ITERATIONS; i++) {
            Deque<Integer> d = newDeque();
            List<Integer> half = new ArrayList<>();
            for (int j = 0; j < size; j++) {
                d.addLast(j);
                if (j % 2 == 0) half.add(j);
            }
            long s = System.nanoTime();
            d.retainAll(half);
            t += System.nanoTime() - s;
        }
        return t / STRUCTURAL_ITERATIONS;
    }

    private static long benchmarkClear(int size) {
        long t = 0;
        for (int i = 0; i < STRUCTURAL_ITERATIONS; i++) {
            Deque<Integer> d = newDeque();
            populate(d, size);
            long s = System.nanoTime();
            d.clear();
            t += System.nanoTime() - s;
        }
        return t / STRUCTURAL_ITERATIONS;
    }

    // =========================================================================
    // Conversion / Views
    // =========================================================================
    private static long benchmarkToArray(int size) {
        long t = 0;
        Deque<Integer> d = newDeque();
        populate(d, size);
        for (int i = 0; i < ITERATIONS; i++) {
            long s = System.nanoTime();
            Object[] a = d.toArray();
            t += System.nanoTime() - s;
            intBlackhole += a.length;
        }
        return t / ITERATIONS;
    }

    private static long benchmarkToArrayTyped(int size) {
        long t = 0;
        Deque<Integer> d = newDeque();
        populate(d, size);
        Integer[] dest = new Integer[0];
        for (int i = 0; i < ITERATIONS; i++) {
            long s = System.nanoTime();
            Integer[] a = d.toArray(dest);
            t += System.nanoTime() - s;
            intBlackhole += a.length;
        }
        return t / ITERATIONS;
    }

    private static long benchmarkToString(int size) {
        long t = 0;
        Deque<Integer> d = newDeque();
        populate(d, size);
        for (int i = 0; i < STRUCTURAL_ITERATIONS; i++) {
            long s = System.nanoTime();
            String str = d.toString();
            t += System.nanoTime() - s;
            intBlackhole += str.length();
        }
        return t / STRUCTURAL_ITERATIONS;
    }

    private static long benchmarkEquals(int size) {
        long t = 0;
        Deque<Integer> a = newDeque();
        Deque<Integer> b = newDeque();
        populate(a, size);
        populate(b, size);
        for (int i = 0; i < ITERATIONS; i++) {
            long s = System.nanoTime();
            boolBlackhole ^= a.equals(b);
            t += System.nanoTime() - s;
        }
        return t / ITERATIONS;
    }

    private static long benchmarkIterator(int size) {
        long t = 0;
        Deque<Integer> d = newDeque();
        populate(d, size);
        for (int i = 0; i < STRUCTURAL_ITERATIONS; i++) {
            long s = System.nanoTime();
            for (Integer v : d) intBlackhole += v;
            t += System.nanoTime() - s;
        }
        return t / STRUCTURAL_ITERATIONS;
    }

    private static long benchmarkDescendingIterator(int size) {
        long t = 0;
        Deque<Integer> d = newDeque();
        populate(d, size);
        for (int i = 0; i < STRUCTURAL_ITERATIONS; i++) {
            long s = System.nanoTime();
            var it = d.descendingIterator();
            while (it.hasNext()) intBlackhole += it.next();
            t += System.nanoTime() - s;
        }
        return t / STRUCTURAL_ITERATIONS;
    }

    // =========================================================================
    // CSV output
    // =========================================================================
    private static void writeResultsToCSV(long[][] results) {
        String file = "ArrayDeque_performance.csv";

        String header =
                "Size;" +
                        "add(E);addFirst(E);addLast(E);offer(E);offerFirst(E);offerLast(E);push(E);addAll(Collection);" +
                        "element();getFirst();getLast();peek();peekFirst();peekLast();contains(Object);containsAll(Collection);size();isEmpty();" +
                        "remove();removeFirst();removeLast();poll();pollFirst();pollLast();pop();remove(Object);" +
                        "removeFirstOccurrence(Object);removeLastOccurrence(Object);removeAll(Collection);retainAll(Collection);clear();" +
                        "toArray();toArray(T[]);toString();equals(Object);iterator();descendingIterator()";

        try (FileWriter w = new FileWriter(file)) {
            w.append(header).append('\n');
            for (long[] row : results) {
                StringBuilder sb = new StringBuilder();
                sb.append(row[0]);
                for (int j = 1; j < row.length; j++) sb.append(';').append(row[j]);
                w.append(sb).append('\n');
            }
            System.out.println("Wrote " + file);
        } catch (IOException e) {
            System.err.println("CSV write failed: " + e.getMessage());
        }
    }
}