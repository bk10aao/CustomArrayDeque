# Custom Array Deque

An implementation of a java ArrayDeque.

All methods implemented are identical to those found in the Java Deque interface.

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

