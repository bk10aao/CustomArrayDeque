# Custom Array Deque

An implementation of a java ArrayDeque.

All methods implemented are identical to those found in the [Java Deque interface](https://docs.oracle.com/en/java/javase/21/docs/api/java.base/java/util/Deque.html).

# Build and Test

To build and test the project run command `./gradlew clean build`

To test the project run command `./gradlew test`

# Time Complexity
| Method                            | CustomArrayDeque | ArrayDeque (JDK) | Winner |
|-----------------------------------|:----------------:|:----------------:|:------:|
| **add(E)**                        |      $O(1)$      | Amortized $O(1)$ |  Tie   |
| **addAll(Collection)**            |    $O(n + m)$    |    $O(n + m)$    |  Tie   |
| **addFirst(E)**                   |      $O(1)$      | Amortized $O(1)$ |  Tie   |
| **addLast(E)**                    |      $O(1)$      | Amortized $O(1)$ |  Tie   |
| **clear()**                       |      $O(1)$      |      $O(n)$      | Custom |
| **contains(Object)**              |      $O(n)$      |      $O(n)$      |  Tie   |
| **containsAll(Collection)**       |    $O(n + m)$    |    $O(n + m)$    |  Tie   |
| **descendingIterator()**          |      $O(n)$      |      $O(1)$      |  JDK   |
| **element()**                     |      $O(1)$      |      $O(1)$      |  Tie   |
| **equals(Object)**                |      $O(n)$      |      $O(n)$      |  Tie   |
| **getFirst()**                    |      $O(1)$      |      $O(1)$      |  Tie   |
| **getLast()**                     |      $O(1)$      |      $O(1)$      |  Tie   |
| **isEmpty()**                     |      $O(1)$      |      $O(1)$      |  Tie   |
| **iterator()**                    |      $O(n)$      |      $O(1)$      |  JDK   |
| **offer(E)**                      |      $O(1)$      | Amortized $O(1)$ |  Tie   |
| **offerFirst(E)**                 |      $O(1)$      | Amortized $O(1)$ |  Tie   |
| **offerLast(E)**                  |      $O(1)$      | Amortized $O(1)$ |  Tie   |
| **peek()**                        |      $O(1)$      |      $O(1)$      |  Tie   |
| **peekFirst()**                   |      $O(1)$      |      $O(1)$      |  Tie   |
| **peekLast()**                    |      $O(1)$      |      $O(1)$      |  Tie   |
| **poll()**                        |      $O(1)$      |      $O(1)$      |  Tie   |
| **pollFirst()**                   |      $O(1)$      |      $O(1)$      |  Tie   |
| **pollLast()**                    |      $O(1)$      |      $O(1)$      |  Tie   |
| **pop()**                         |      $O(1)$      |      $O(1)$      |  Tie   |
| **push(E)**                       |      $O(1)$      |      $O(1)$      |  Tie   |
| **remove()**                      |      $O(1)$      |      $O(1)$      |  Tie   |
| **remove(Object)**                |      $O(n)$      |      $O(n)$      |  Tie   |
| **removeAll(Collection)**         |      $O(n)$      |      $O(n)$      |  Tie   |
| **removeFirst()**                 |      $O(1)$      |      $O(1)$      |  Tie   |
| **removeFirstOccurrence(Object)** |      $O(n)$      |      $O(n)$      |  Tie   |
| **removeLast()**                  |      $O(1)$      |      $O(1)$      |  Tie   |
| **removeLastOccurrence(Object)**  |      $O(n)$      |      $O(n)$      |  Tie   |
| **retainAll(Collection)**         |      $O(n)$      |      $O(n)$      |  Tie   |
| **size()**                        |      $O(1)$      |      $O(1)$      |  Tie   |
| **toArray()**                     |      $O(n)$      |      $O(n)$      |  Tie   |
| **toArray(T[])**                  |      $O(n)$      |      $O(n)$      |  Tie   |
| **toString()**                    |      $O(n)$      |      $O(n)$      |  Tie   |

# Space Complexity

| Method                            | CustomArrayDeque | ArrayDeque (JDK) | Winner |
|-----------------------------------|:----------------:|:----------------:|:------:|
| **add(E)**                        |      $O(1)$      |      $O(1)$      |  Tie   |
| **addAll(Collection)**            |      $O(m)$      |      $O(m)$      |  Tie   |
| **addFirst(E)**                   |      $O(1)$      |      $O(1)$      |  Tie   |
| **addLast(E)**                    |      $O(1)$      |      $O(1)$      |  Tie   |
| **clear()**                       |      $O(1)$      |      $O(1)$      |  Tie   |
| **contains(Object)**              |      $O(1)$      |      $O(1)$      |  Tie   |
| **containsAll(Collection)**       |      $O(m)$      |      $O(m)$      |  Tie   |
| **descendingIterator()**          |      $O(n)$      |      $O(1)$      |  JDK   |
| **element()**                     |      $O(1)$      |      $O(1)$      |  Tie   |
| **equals(Object)**                |      $O(1)$      |      $O(1)$      |  Tie   |
| **getFirst()**                    |      $O(1)$      |      $O(1)$      |  Tie   |
| **getLast()**                     |      $O(1)$      |      $O(1)$      |  Tie   |
| **isEmpty()**                     |      $O(1)$      |      $O(1)$      |  Tie   |
| **iterator()**                    |      $O(n)$      |      $O(1)$      |  JDK   |
| **offer(E)**                      |      $O(1)$      |      $O(1)$      |  Tie   |
| **offerFirst(E)**                 |      $O(1)$      |      $O(1)$      |  Tie   |
| **offerLast(E)**                  |      $O(1)$      |      $O(1)$      |  Tie   |
| **peek()**                        |      $O(1)$      |      $O(1)$      |  Tie   |
| **peekFirst()**                   |      $O(1)$      |      $O(1)$      |  Tie   |
| **peekLast()**                    |      $O(1)$      |      $O(1)$      |  Tie   |
| **poll()**                        |      $O(1)$      |      $O(1)$      |  Tie   |
| **pollFirst()**                   |      $O(1)$      |      $O(1)$      |  Tie   |
| **pollLast()**                    |      $O(1)$      |      $O(1)$      |  Tie   |
| **pop()**                         |      $O(1)$      |      $O(1)$      |  Tie   |
| **push(E)**                       |      $O(1)$      |      $O(1)$      |  Tie   |
| **remove()**                      |      $O(1)$      |      $O(1)$      |  Tie   |
| **remove(Object)**                |      $O(1)$      |      $O(1)$      |  Tie   |
| **removeAll(Collection)**         |      $O(m)$      |      $O(m)$      |  Tie   |
| **removeFirst()**                 |      $O(1)$      |      $O(1)$      |  Tie   |
| **removeFirstOccurrence(Object)** |      $O(1)$      |      $O(1)$      |  Tie   |
| **removeLast()**                  |      $O(1)$      |      $O(1)$      |  Tie   |
| **removeLastOccurrence(Object)**  |      $O(1)$      |      $O(1)$      |  Tie   |
| **retainAll(Collection)**         |      $O(m)$      |      $O(m)$      |  Tie   |
| **size()**                        |      $O(1)$      |      $O(1)$      |  Tie   |
| **toArray()**                     |      $O(n)$      |      $O(n)$      |  Tie   |
| **toArray(T[])**                  |      $O(n)$      |      $O(n)$      |  Tie   |
| **toString()**                    |      $O(n)$      |      $O(n)$      |  Tie   |

Notes: 
- n: Current number of elements in the deque. 
- m: Number of elements in the input collection.

# Performance

Below performance is a comparison made at 100,000 operations per method.

Note: all data is an average of 100 runs. Margins within $\pm 5\%$ ($\le 1.05x$) are considered statistical ties.

| Method                           | CustomArrayDeque (ns) | ArrayDeque (JDK) (ns) |   Winner   |  Margin  |
|:---------------------------------|:----------------------|:----------------------|:----------:|:--------:|
| `add(E)`                         | 75                    | 158                   | **Custom** |  2.11x   |
| `addAll(Collection)`             | 195,641               | 234,370               | **Custom** |  1.20x   |
| `addFirst(E)`                    | 49                    | 54                    | **Custom** |  1.10x   |
| `addLast(E)`                     | 45                    | 62                    | **Custom** |  1.38x   |
| `clear()`                        | 550                   | 45,641                | **Custom** |  82.98x  |
| `contains(Object)`               | 31,694                | 32,142                |  **Tie**   |  1.01x   |
| `containsAll(Collection)`        | 161,310               | 2,180,316             | **Custom** |  13.52x  |
| `descendingIterator()`           | 69,775                | 78,908                | **Custom** |  1.13x   |
| `element()`                      | 35                    | 38                    |  **Tie**   |  1.09x   |
| `equals(Object)`                 | 123,731               | 303                   |  **JDK**   | 408.35x  |
| `getFirst()`                     | 30                    | 32                    | **Custom** |  1.07x   |
| `getLast()`                      | 163                   | 31                    |  **JDK**   |  5.26x   |
| `isEmpty()`                      | 49                    | 47                    |  **Tie**   |  1.04x   |
| `iterator()`                     | 68,062                | 83,062                | **Custom** |  1.22x   |
| `offer(E)`                       | 129                   | 99                    |  **JDK**   |  1.30x   |
| `offerFirst(E)`                  | 83                    | 100                   | **Custom** |  1.20x   |
| `offerLast(E)`                   | 204                   | 112                   |  **JDK**   |  1.82x   |
| `peek()`                         | 34                    | 32                    |  **Tie**   |  1.06x   |
| `peekFirst()`                    | 31                    | 30                    |  **Tie**   |  1.03x   |
| `peekLast()`                     | 27                    | 35                    | **Custom** |  1.30x   |
| `poll()`                         | 108                   | 108                   |  **Tie**   |  1.00x   |
| `pollFirst()`                    | 99                    | 179                   | **Custom** |  1.81x   |
| `pollLast()`                     | 58                    | 45                    |  **JDK**   |  1.29x   |
| `pop()`                          | 70                    | 83                    | **Custom** |  1.19x   |
| `push(E)`                        | 204                   | 225                   | **Custom** |  1.10x   |
| `remove()`                       | 79                    | 70                    |  **JDK**   |  1.13x   |
| `remove(Object)`                 | 72,733                | 27,754                |  **JDK**   |  2.62x   |
| `removeAll(Collection)`          | 500,220               | 2,182,815,554         | **Custom** | 4363.71x |
| `removeFirst()`                  | 54                    | 75                    | **Custom** |    1.39x |
| `removeFirstOccurrence(Object)`  | 86,791                | 30,425                |  **JDK**   |    2.85x |
| `removeLast()`                   | 46                    | 54                    | **Custom** |    1.17x |
| `removeLastOccurrence(Object)`   | 68,779                | 22,096                |  **JDK**   |    3.11x |
| `retainAll(Collection)`          | 475,924               | 2,222,012,637         | **Custom** | 4668.84x |
| `size()`                         | 47                    | 60                    | **Custom** |    1.28x |
| `toArray()`                      | 13,015                | 12,447                |  **Tie**   |    1.05x |
| `toArray(T[])`                   | 73,259                | 57,850                |  **JDK**   |    1.27x |
| `toString()`                     | 1,600,616             | 1,891,445             | **Custom** |    1.18x |

# Performance Charts

#### Note: The following performance charts are designed to be viewed in dark mode.

![Heatmap](PerformanceCharts/heatmap.png)
![add](PerformanceCharts/plot_add_E_.png)
![addAll](PerformanceCharts/plot_addAll_Collection_.png)
![addFirst](PerformanceCharts/plot_addFirst_E_.png)
![addLast](PerformanceCharts/plot_addLast_E_.png)
![clear](PerformanceCharts/plot_clear__.png)
![contains](PerformanceCharts/plot_contains_Object_.png)
![containsAll](PerformanceCharts/plot_containsAll_Collection_.png)
![descendingOrderIterator](PerformanceCharts/plot_descendingIterator__.png)
![element](PerformanceCharts/plot_element__.png)
![equals](PerformanceCharts/plot_equals_Object_.png)
![getFirst](PerformanceCharts/plot_getFirst__.png)
![getLast](PerformanceCharts/plot_getLast__.png)
![isEmpty](PerformanceCharts/plot_isEmpty__.png)
![iterator](PerformanceCharts/plot_iterator__.png)
![offer](PerformanceCharts/plot_offer_E_.png)
![offerFirst](PerformanceCharts/plot_offerFirst_E_.png)
![offerLast](PerformanceCharts/plot_offerLast_E_.png)
![peek](PerformanceCharts/plot_peek__.png)
![peekFirst](PerformanceCharts/plot_peekFirst__.png)
![peekLast](PerformanceCharts/plot_peekLast__.png)
![poll](PerformanceCharts/plot_poll__.png)
![pollFirst](PerformanceCharts/plot_pollFirst__.png)
![pollLast](PerformanceCharts/plot_pollLast__.png)
![pop](PerformanceCharts/plot_pop__.png)
![push](PerformanceCharts/plot_push_E_.png)
![remove](PerformanceCharts/plot_remove__.png)
![removeObject](PerformanceCharts/plot_remove_Object_.png)
![removeAll](PerformanceCharts/plot_removeAll_Collection_.png)
![removeFirst](PerformanceCharts/plot_removeFirst__.png)
![removeFirstOccurrence](PerformanceCharts/plot_removeFirstOccurrence_Object_.png)
![removeLast](PerformanceCharts/plot_removeLast__.png)
![removeLastOccurrence](PerformanceCharts/plot_removeLastOccurrence_Object_.png)
![retainAll](PerformanceCharts/plot_retainAll_Collection_.png)
![size](PerformanceCharts/plot_size__.png)
![toArray](PerformanceCharts/plot_toArray__.png)
![toArray_T](PerformanceCharts/plot_toArray_T[]_.png)
![toString](PerformanceCharts/plot_toString__.png)