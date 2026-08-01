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

| Method                          | CustomArrayDeque (ns) | ArrayDeque (JDK) (ns) |   Winner   |  Margin   |
|:--------------------------------|:----------------------|:----------------------|:----------:|:---------:|
| `add(E)`                        | 326                   | 158                   |  **JDK**   |   2.06x   |
| `addAll(Collection)`            | 143,766               | 234,370               | **Custom** |   1.63x   |
| `addFirst(E)`                   | 117                   | 54                    |  **JDK**   |   2.17x   |
| `addLast(E)`                    | 221                   | 62                    |  **JDK**   |   3.57x   |
| `clear()`                       | 386                   | 45,641                | **Custom** |  118.30x  |
| `contains(Object)`              | 22,047                | 32,142                | **Custom** |   1.46x   |
| `containsAll(Collection)`       | 115,516               | 2,180,316             | **Custom** |  18.87x   |
| `descendingIterator()`          | 99,398                | 78,908                |  **JDK**   |   1.26x   |
| `element()`                     | 34                    | 38                    | **Custom** |   1.11x   |
| `equals(Object)`                | 71,564                | 303                   |  **JDK**   |  236.19x  |
| `getFirst()`                    | 43                    | 32                    |  **JDK**   |   1.33x   |
| `getLast()`                     | 40                    | 31                    |  **JDK**   |   1.27x   |
| `isEmpty()`                     | 52                    | 47                    |  **JDK**   |   1.10x   |
| `iterator()`                    | 93,027                | 83,062                |  **JDK**   |   1.12x   |
| `offer(E)`                      | 125                   | 99                    |  **JDK**   |   1.26x   |
| `offerFirst(E)`                 | 113                   | 100                   |  **JDK**   |   1.13x   |
| `offerLast(E)`                  | 270                   | 112                   |  **JDK**   |   2.41x   |
| `peek()`                        | 32                    | 32                    |  **Tie**   |   1.00x   |
| `peekFirst()`                   | 32                    | 30                    |  **Tie**   |   1.06x   |
| `peekLast()`                    | 32                    | 35                    |  **Tie**   |   1.09x   |
| `poll()`                        | 166                   | 108                   |  **JDK**   |   1.54x   |
| `pollFirst()`                   | 90                    | 179                   | **Custom** |   2.00x   |
| `pollLast()`                    | 120                   | 45                    |  **JDK**   |   2.66x   |
| `pop()`                         | 77                    | 83                    |  **Tie**   |   1.08x   |
| `push(E)`                       | 122                   | 225                   | **Custom** |   1.85x   |
| `remove()`                      | 67                    | 70                    |  **Tie**   |   1.05x   |
| `remove(Object)`                | 35,387                | 27,754                |  **JDK**   |   1.28x   |
| `removeAll(Collection)`         | 415,387               | 2,182,815,554         | **Custom** | 5,254.90x |
| `removeFirst()`                 | 82                    | 75                    |  **JDK**   |   1.10x   |
| `removeFirstOccurrence(Object)` | 23,336                | 30,425                | **Custom** |   1.30x   |
| `removeLast()`                  | 57                    | 54                    |  **Tie**   |   1.05x   |
| `removeLastOccurrence(Object)`  | 37,793                | 22,096                |  **JDK**   |   1.71x   |
| `retainAll(Collection)`         | 382,795               | 2,222,012,637         | **Custom** | 5,804.71x |
| `size()`                        | 44                    | 60                    | **Custom** |   1.35x   |
| `toArray()`                     | 7,879                 | 12,447                | **Custom** |   1.58x   |
| `toArray(T[])`                  | 33,924                | 57,850                | **Custom** |   1.71x   |
| `toString()`                    | 957,173               | 1,891,445             | **Custom** |   1.98x   |

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