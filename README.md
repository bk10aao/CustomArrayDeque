# Custom Array Deque

An implementation of a java ArrayDeque.

All methods implemented are identical to those found in the [Java Deque interface](https://docs.oracle.com/en/java/javase/21/docs/api/java.base/java/util/Deque.html).

# Build and Test

To build and test the project run command `./gradlew clean build`

To test the project run command `./gradlew test`

# Time Complexity
| Method                            |   Custom   |    JDK     | Winner |
|-----------------------------------|:----------:|:----------:|:------:|
| **add(E)**                        |   $O(1)$   |   $O(1)$   |  Tie   |
| **addAll(Collection)**            | $O(n + m)$ | $O(n + m)$ |  Tie   |
| **addFirst(E)**                   |   $O(1)$   |   $O(1)$   |  Tie   |
| **addLast(E)**                    |   $O(1)$   |   $O(1)$   |  Tie   |
| **clear()**                       |   $O(1)$   |   $O(n)$   | Custom |
| **contains(Object)**              |   $O(n)$   |   $O(n)$   |  Tie   |
| **containsAll(Collection)**       | $O(n + m)$ | $O(n + m)$ |  Tie   |
| **descendingIterator()**          |   $O(n)$   |   $O(1)$   |  JDK   |
| **element()**                     |   $O(1)$   |   $O(1)$   |  Tie   |
| **equals(Object)**                |   $O(n)$   |   $O(n)$   |  Tie   |
| **getFirst()**                    |   $O(1)$   |   $O(1)$   |  Tie   |
| **getLast()**                     |   $O(1)$   |   $O(1)$   |  Tie   |
| **isEmpty()**                     |   $O(1)$   |   $O(1)$   |  Tie   |
| **iterator()**                    |   $O(n)$   |   $O(1)$   |  JDK   |
| **offer(E)**                      |   $O(1)$   |   $O(1)$   |  Tie   |
| **offerFirst(E)**                 |   $O(1)$   |   $O(1)$   |  Tie   |
| **offerLast(E)**                  |   $O(1)$   |   $O(1)$   |  Tie   |
| **peek()**                        |   $O(1)$   |   $O(1)$   |  Tie   |
| **peekFirst()**                   |   $O(1)$   |   $O(1)$   |  Tie   |
| **peekLast()**                    |   $O(1)$   |   $O(1)$   |  Tie   |
| **poll()**                        |   $O(1)$   |   $O(1)$   |  Tie   |
| **pollFirst()**                   |   $O(1)$   |   $O(1)$   |  Tie   |
| **pollLast()**                    |   $O(1)$   |   $O(1)$   |  Tie   |
| **pop()**                         |   $O(1)$   |   $O(1)$   |  Tie   |
| **push(E)**                       |   $O(1)$   |   $O(1)$   |  Tie   |
| **remove()**                      |   $O(1)$   |   $O(1)$   |  Tie   |
| **remove(Object)**                |   $O(n)$   |   $O(n)$   |  Tie   |
| **removeAll(Collection)**         |   $O(n)$   |   $O(n)$   |  Tie   |
| **removeFirst()**                 |   $O(1)$   |   $O(1)$   |  Tie   |
| **removeFirstOccurrence(Object)** |   $O(n)$   |   $O(n)$   |  Tie   |
| **removeLast()**                  |   $O(1)$   |   $O(1)$   |  Tie   |
| **removeLastOccurrence(Object)**  |   $O(n)$   |   $O(n)$   |  Tie   |
| **retainAll(Collection)**         |   $O(n)$   |   $O(n)$   |  Tie   |
| **size()**                        |   $O(1)$   |   $O(1)$   |  Tie   |
| **toArray()**                     |   $O(n)$   |   $O(n)$   |  Tie   |
| **toArray(T[])**                  |   $O(n)$   |   $O(n)$   |  Tie   |
| **toString()**                    |   $O(n)$   |   $O(n)$   |  Tie   |

# Space Complexity

| Method                            | Custom |  JDK   | Winner |
|-----------------------------------|:------:|:------:|:------:|
| **add(E)**                        | $O(1)$ | $O(1)$ |  Tie   |
| **addAll(Collection)**            | $O(m)$ | $O(m)$ |  Tie   |
| **addFirst(E)**                   | $O(1)$ | $O(1)$ |  Tie   |
| **addLast(E)**                    | $O(1)$ | $O(1)$ |  Tie   |
| **clear()**                       | $O(1)$ | $O(1)$ |  Tie   |
| **contains(Object)**              | $O(1)$ | $O(1)$ |  Tie   |
| **containsAll(Collection)**       | $O(m)$ | $O(m)$ |  Tie   |
| **descendingIterator()**          | $O(n)$ | $O(1)$ |  JDK   |
| **element()**                     | $O(1)$ | $O(1)$ |  Tie   |
| **equals(Object)**                | $O(1)$ | $O(1)$ |  Tie   |
| **getFirst()**                    | $O(1)$ | $O(1)$ |  Tie   |
| **getLast()**                     | $O(1)$ | $O(1)$ |  Tie   |
| **isEmpty()**                     | $O(1)$ | $O(1)$ |  Tie   |
| **iterator()**                    | $O(n)$ | $O(1)$ |  JDK   |
| **offer(E)**                      | $O(1)$ | $O(1)$ |  Tie   |
| **offerFirst(E)**                 | $O(1)$ | $O(1)$ |  Tie   |
| **offerLast(E)**                  | $O(1)$ | $O(1)$ |  Tie   |
| **peek()**                        | $O(1)$ | $O(1)$ |  Tie   |
| **peekFirst()**                   | $O(1)$ | $O(1)$ |  Tie   |
| **peekLast()**                    | $O(1)$ | $O(1)$ |  Tie   |
| **poll()**                        | $O(1)$ | $O(1)$ |  Tie   |
| **pollFirst()**                   | $O(1)$ | $O(1)$ |  Tie   |
| **pollLast()**                    | $O(1)$ | $O(1)$ |  Tie   |
| **pop()**                         | $O(1)$ | $O(1)$ |  Tie   |
| **push(E)**                       | $O(1)$ | $O(1)$ |  Tie   |
| **remove()**                      | $O(1)$ | $O(1)$ |  Tie   |
| **remove(Object)**                | $O(1)$ | $O(1)$ |  Tie   |
| **removeAll(Collection)**         | $O(m)$ | $O(m)$ |  Tie   |
| **removeFirst()**                 | $O(1)$ | $O(1)$ |  Tie   |
| **removeFirstOccurrence(Object)** | $O(1)$ | $O(1)$ |  Tie   |
| **removeLast()**                  | $O(1)$ | $O(1)$ |  Tie   |
| **removeLastOccurrence(Object)**  | $O(1)$ | $O(1)$ |  Tie   |
| **retainAll(Collection)**         | $O(m)$ | $O(m)$ |  Tie   |
| **size()**                        | $O(1)$ | $O(1)$ |  Tie   |
| **toArray()**                     | $O(n)$ | $O(n)$ |  Tie   |
| **toArray(T[])**                  | $O(n)$ | $O(n)$ |  Tie   |
| **toString()**                    | $O(n)$ | $O(n)$ |  Tie   |

Notes: 
- n: Current number of elements in the deque. 
- m: Number of elements in the input collection.

# Performance

 Comparison table uses the **average** JMH score (ns/op) across sizes 10k–100k

| Method                          | Custom (ns) | JDK (ns)  |            Winner            | Margin |
|:--------------------------------|:------------|:----------|:----------------------------:|:------:|
| `add(E)`                        | 224,146     | 362,922   |          **Custom**          | 1.62x  |
| `addAll(Collection)`            | 94,839      | 167,741   |          **Custom**          | 1.77x  |
| `addFirst(E)`                   | 151,967     | 254,255   |          **Custom**          | 1.67x  |
| `addLast(E)`                    | 223,640     | 363,644   |          **Custom**          | 1.63x  |
| `clear()`                       | 32          | 56        |          **Custom**          | 1.78x  |
| `contains(Object)`              | 14,592      | 21,105    |          **Custom**          | 1.45x  |
| `containsAll(Collection)`       | 702,213     | 991,846   |          **Custom**          | 1.41x  |
| `descendingIterator()`          | 33,691      | 49,063    |          **Custom**          | 1.46x  |
| `element()`                     | 32          | 65        |          **Custom**          | 2.02x  |
| `equals(Object)`                | 262,560     | 359,352   |          **Custom**          | 1.37x  |
| `getFirst()`                    | 32          | 65        |          **Custom**          | 2.00x  |
| `getLast()`                     | 32          | 66        |          **Custom**          | 2.05x  |
| `isEmpty()`                     | 36          | 70        |          **Custom**          | 1.97x  |
| `iterator()`                    | 33,103      | 42,217    |          **Custom**          | 1.28x  |
| `offer(E)`                      | 221,613     | 271,155   |          **Custom**          | 1.22x  |
| `offerFirst(E)`                 | 140,523     | 166,344   |          **Custom**          | 1.18x  |
| `offerLast(E)`                  | 148,519     | 174,098   |          **Custom**          | 1.17x  |
| `peek()`                        | 40          | 59        |          **Custom**          | 1.46x  |
| `peekFirst()`                   | 41          | 55        |          **Custom**          | 1.34x  |
| `peekLast()`                    | 39          | 58        |          **Custom**          | 1.48x  |
| `poll()`                        | 109,990     | 128,832   |          **Custom**          | 1.17x  |
| `pollFirst()`                   | 110,071     | 128,485   |          **Custom**          | 1.17x  |
| `pollLast()`                    | 110,568     | 132,518   |          **Custom**          | 1.20x  |
| `pop()`                         | 109,347     | 132,193   |          **Custom**          | 1.21x  |
| `push(E)`                       | 150,868     | 179,258   |          **Custom**          | 1.19x  |
| `remove()`                      | 109,896     | 132,917   |          **Custom**          | 1.21x  |
| `remove(Object)`                | 129,862     | 151,925   |          **Custom**          | 1.17x  |
| `removeAll(Collection)`         | 763,193     | 869,923   | **Statistically Equivalent** | 1.14x  |
| `removeFirst()`                 | 109,818     | 126,329   | **Statistically Equivalent** | 1.15x  |
| `removeFirstOccurrence(Object)` | 127,908     | 167,785   |          **Custom**          | 1.31x  |
| `removeLast()`                  | 111,249     | 131,815   |          **Custom**          | 1.18x  |
| `removeLastOccurrence(Object)`  | 126,932     | 150,749   |          **Custom**          | 1.19x  |
| `retainAll(Collection)`         | 709,455     | 829,622   |          **Custom**          | 1.17x  |
| `size()`                        | 40          | 71        |          **Custom**          | 1.79x  |
| `toArray()`                     | 6,653       | 7,619     | **Statistically Equivalent** | 1.15x  |
| `toArray(T[])`                  | 32,613      | 41,216    |          **Custom**          | 1.26x  |
| `toString()`                    | 880,184     | 1,113,066 |          **Custom**          | 1.26x  |

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