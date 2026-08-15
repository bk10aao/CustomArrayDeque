package customdeque;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Level;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Param;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Warmup;
import org.openjdk.jmh.results.RunResult;
import org.openjdk.jmh.results.format.ResultFormatType;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.TimeUnit;

@State(Scope.Thread)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@Warmup(iterations = 3, time = 1)
@Measurement(iterations = 3, time = 1)
@Fork(1)
public class CustomArrayDequePerformanceTest {

    @Param({"10000", "20000", "30000", "40000", "50000", "60000", "70000", "80000", "90000", "100000"})
    int size;

    private CustomArrayDeque<Integer> deque;
    private List<Integer> collection;

    @Setup(Level.Invocation)
    public void setUp() {
        deque = new CustomArrayDeque<>();
        collection = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            collection.add(i);
            deque.add(i);
        }
    }

    @Benchmark
    public CustomArrayDeque<Integer> constructorDefault() {
        return new CustomArrayDeque<>();
    }

    @Benchmark
    public CustomArrayDeque<Integer> constructorCapacity() {
        return new CustomArrayDeque<>(size);
    }

    @Benchmark
    public CustomArrayDeque<Integer> constructorCollection() {
        return new CustomArrayDeque<>(collection);
    }

    @Benchmark
    public boolean add() {
        CustomArrayDeque<Integer> d = new CustomArrayDeque<>();
        for (int i = 0; i < size; i++) {
            d.add(i);
        }
        return d.isEmpty();
    }

    @Benchmark
    public void addFirst() {
        CustomArrayDeque<Integer> d = new CustomArrayDeque<>();
        for (int i = 0; i < size; i++) {
            d.addFirst(i);
        }
    }

    @Benchmark
    public void addLast() {
        CustomArrayDeque<Integer> d = new CustomArrayDeque<>();
        for (int i = 0; i < size; i++) {
            d.addLast(i);
        }
    }

    @Benchmark
    public boolean offer() {
        CustomArrayDeque<Integer> d = new CustomArrayDeque<>();
        for (int i = 0; i < size; i++) {
            d.offer(i);
        }
        return d.isEmpty();
    }

    @Benchmark
    public void offerFirst() {
        CustomArrayDeque<Integer> d = new CustomArrayDeque<>();
        for (int i = 0; i < size; i++) {
            d.offerFirst(i);
        }
    }

    @Benchmark
    public void offerLast() {
        CustomArrayDeque<Integer> d = new CustomArrayDeque<>();
        for (int i = 0; i < size; i++) {
            d.offerLast(i);
        }
    }

    @Benchmark
    public void push() {
        CustomArrayDeque<Integer> d = new CustomArrayDeque<>();
        for (int i = 0; i < size; i++) {
            d.push(i);
        }
    }

    @Benchmark
    public boolean addAll() {
        CustomArrayDeque<Integer> d = new CustomArrayDeque<>();
        return d.addAll(collection);
    }

    @Benchmark
    public Integer element() {
        return deque.element();
    }

    @Benchmark
    public Integer getFirst() {
        return deque.getFirst();
    }

    @Benchmark
    public Integer getLast() {
        return deque.getLast();
    }

    @Benchmark
    public Integer peek() {
        return deque.peek();
    }

    @Benchmark
    public Integer peekFirst() {
        return deque.peekFirst();
    }

    @Benchmark
    public Integer peekLast() {
        return deque.peekLast();
    }

    @Benchmark
    public boolean contains() {
        return deque.contains(size / 2);
    }

    @Benchmark
    public boolean containsAll() {
        return deque.containsAll(collection);
    }

    @Benchmark
    public int size() {
        return deque.size();
    }

    @Benchmark
    public boolean isEmpty() {
        return deque.isEmpty();
    }

    @Benchmark
    public Integer remove() {
        CustomArrayDeque<Integer> d = new CustomArrayDeque<>(collection);
        return d.remove();
    }

    @Benchmark
    public Integer removeFirst() {
        CustomArrayDeque<Integer> d = new CustomArrayDeque<>(collection);
        return d.removeFirst();
    }

    @Benchmark
    public Integer removeLast() {
        CustomArrayDeque<Integer> d = new CustomArrayDeque<>(collection);
        return d.removeLast();
    }

    @Benchmark
    public Integer poll() {
        CustomArrayDeque<Integer> d = new CustomArrayDeque<>(collection);
        return d.poll();
    }

    @Benchmark
    public Integer pollFirst() {
        CustomArrayDeque<Integer> d = new CustomArrayDeque<>(collection);
        return d.pollFirst();
    }

    @Benchmark
    public Integer pollLast() {
        CustomArrayDeque<Integer> d = new CustomArrayDeque<>(collection);
        return d.pollLast();
    }

    @Benchmark
    public Integer pop() {
        CustomArrayDeque<Integer> d = new CustomArrayDeque<>(collection);
        return d.pop();
    }

    @Benchmark
    public boolean removeObject() {
        CustomArrayDeque<Integer> d = new CustomArrayDeque<>(collection);
        return d.remove(size / 2);
    }

    @Benchmark
    public boolean removeFirstOccurrence() {
        CustomArrayDeque<Integer> d = new CustomArrayDeque<>(collection);
        return d.removeFirstOccurrence(size / 2);
    }

    @Benchmark
    public boolean removeLastOccurrence() {
        CustomArrayDeque<Integer> d = new CustomArrayDeque<>(collection);
        return d.removeLastOccurrence(size / 2);
    }

    @Benchmark
    public boolean removeAll() {
        CustomArrayDeque<Integer> d = new CustomArrayDeque<>(collection);
        return d.removeAll(collection);
    }

    @Benchmark
    public boolean retainAll() {
        CustomArrayDeque<Integer> d = new CustomArrayDeque<>(collection);
        return d.retainAll(collection);
    }

    @Benchmark
    public void clear() {
        deque.clear();
    }

    @Benchmark
    public Object[] toArray() {
        return deque.toArray();
    }

    @Benchmark
    public Integer[] toArrayTyped() {
        return deque.toArray(new Integer[0]);
    }

    @Benchmark
    public String toStringBenchmark() {
        return deque.toString();
    }

    @Benchmark
    public boolean equalsBenchmark() {
        CustomArrayDeque<Integer> d2 = new CustomArrayDeque<>(collection);
        return deque.equals(d2);
    }

    @Benchmark
    public int iteratorConsumption() {
        int count = 0;
        for (Integer val : deque) {
            count += val;
        }
        return count;
    }

    @Benchmark
    public int descendingIteratorConsumption() {
        int count = 0;
        var it = deque.descendingIterator();
        while (it.hasNext()) {
            count += it.next();
        }
        return count;
    }

    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(CustomArrayDequePerformanceTest.class.getSimpleName())
                .measurementIterations(3)
                .forks(1)
                .result("CustomArrayDeque_performance_results.csv")
                .resultFormat(ResultFormatType.CSV)
                .build();

        Collection<RunResult> results = new Runner(opt).run();
        writeCustomCsv(results);
    }

    private static void writeCustomCsv(Collection<RunResult> results) {
        try (FileWriter writer = new FileWriter("CustomArrayDeque_jmh_performance.csv")) {
            writer.write("Benchmark;Size;Score (ns/op)\n");
            for (RunResult result : results) {
                String benchmarkName = result.getParams().getBenchmark();
                String shortName = benchmarkName.substring(benchmarkName.lastIndexOf('.') + 1);

                double score = result.getPrimaryResult().getScore();
                String sizeVal = result.getParams().getParam("size");

                writer.write("\"" + shortName + "\";" + (sizeVal != null ? sizeVal : "N/A") + ";" + score + "\n");
            }
            System.out.println("JMH Performance report saved: CustomArrayDeque_jmh_performance.csv");
        } catch (IOException e) {
            System.err.println("Failed to write CSV: " + e.getMessage());
        }
    }
}