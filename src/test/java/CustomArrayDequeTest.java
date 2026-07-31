import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CustomArrayDequeTest {

    @Test
    public void givenDefaultConstructor_returnsInitialQueueSizeOf_16() {
        assertEquals(16, new CustomArrayDeque<>().getArraySize());
    }

    @Test
    public void givenConstructor_withSizeOf_negative_1_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> new CustomArrayDeque<>(-1));
    }

    @Test
    public void givenConstructor_withSizeOf_10_returnsInitialQueueSizeOf_10() {
        assertEquals(16, new CustomArrayDeque<>(10).getArraySize());
    }

    @Test
    public void givenConstructor_withNullCollections_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new CustomArrayDeque<>(null));
    }

    @Test
    public void givenConstructor_withCollectionOfNumbers_1_to_5_addsItemsCorrectly_returnsSizeOf_5() {
        CustomArrayDeque<Integer> customArrayDeque = new CustomArrayDeque<>(List.of(1, 2, 3, 4, 5));
        assertEquals(5, customArrayDeque.size());
        assertEquals(16, customArrayDeque.getArraySize());
    }

    @Test
    public void givenConstructor_withCollectionOf_0_to_16_addsItemsCorrectly_returnsSizeOf_17_andResizesDequeTo_32() {
        List<Integer> toAdd = IntStream.rangeClosed(0, 16).boxed().collect(Collectors.toList());
        CustomArrayDeque<Integer> customArrayDeque = new CustomArrayDeque<>(toAdd);
        assertEquals(17, customArrayDeque.size());
        assertEquals(32, customArrayDeque.getArraySize());
    }

    @Test
    public void givenConstructor_withCollectionOf_0_to_9_addsItemsCorrectly_returnsSizeOf_9_andDequeSizeOf_16() {
        List<Integer> toAdd = IntStream.rangeClosed(0, 9).boxed().collect(Collectors.toList());
        CustomArrayDeque<Integer> customArrayDeque = new CustomArrayDeque<>(toAdd);
        assertEquals(10, customArrayDeque.size());
        assertEquals(16, customArrayDeque.getArraySize());
    }

    @Test
    public void whenAddingNullItem_throwsNullPointerException() {
        CustomArrayDeque<Integer> customArrayDeque = new CustomArrayDeque<>();
        assertThrows(NullPointerException.class, () -> customArrayDeque.add(null));
    }

    @Test
    public void whenAddingValue_1_returnsTrue_andSizeOf_1() {
        CustomArrayDeque<Integer> customArrayDeque = new CustomArrayDeque<>();
        assertTrue(customArrayDeque.add(1));
        assertEquals(1, customArrayDeque.size());
    }

    @Test
    public void whenAddingValues_1_2_3_returnsTrue_andSizeOf_3() {
        CustomArrayDeque<Integer> customArrayDeque = new CustomArrayDeque<>();
        assertTrue(customArrayDeque.add(1));
        assertTrue(customArrayDeque.add(2));
        assertTrue(customArrayDeque.add(3));
        assertEquals(3, customArrayDeque.size());
    }

    @Test
    public void whenAddingValue_triggersResize_returnsTrue_andDequeSizeOf_32() {
        CustomArrayDeque<Integer> customArrayDeque = new CustomArrayDeque<>();
        List<Integer> toAdd = IntStream.rangeClosed(0, 11).boxed().toList();
        assertTrue(customArrayDeque.addAll(toAdd));
        assertEquals(16, customArrayDeque.getArraySize());
        assertTrue(customArrayDeque.add(9));
        assertEquals(32, customArrayDeque.getArraySize());
    }

    @Test
    public void whenAddingNullCollection_throws_NullPointerException_andSizeDoesNotChange() {
        CustomArrayDeque<Integer> customArrayDeque = new CustomArrayDeque<>();
        assertThrows(NullPointerException.class, () -> customArrayDeque.addAll(null));
        assertEquals(0, customArrayDeque.size());
        assertEquals(16, customArrayDeque.getArraySize());
    }

    @Test
    public void whenAddingCollectionOf_1_2_3_returnsTrue_andDoesNotResize() {
        CustomArrayDeque<Integer> customArrayDeque = new CustomArrayDeque<>();
        Collection<Integer> c = List.of(1, 2, 3);
        assertTrue(customArrayDeque.addAll(c));
        assertEquals(3, customArrayDeque.size());
        assertEquals(16, customArrayDeque.getArraySize());
    }

    @Test
    public void whenAddingEmptyCollection_returnsFalse_andDoesNotResize() {
        CustomArrayDeque<Integer> customArrayDeque = new CustomArrayDeque<>();
        Collection<Integer> c = List.of();
        assertFalse(customArrayDeque.addAll(c));
        assertEquals(0, customArrayDeque.size());
    }

    @Test
    public void whenAddingCollectionWithValues_0_to_17_returnsTrue_andResizesTo_32() {
        CustomArrayDeque<Integer> customArrayDeque = new CustomArrayDeque<>();
        List<Integer> toAdd = IntStream.rangeClosed(0, 17).boxed().toList();
        assertTrue(customArrayDeque.addAll(toAdd));
        assertEquals(18, customArrayDeque.size());
        assertEquals(32, customArrayDeque.getArraySize());
    }

    @Test
    public void whenAddingCollectionWithValues_0_to_32_returnsTrue_andResizesTo_64() {
        CustomArrayDeque<Integer> customArrayDeque = new CustomArrayDeque<>();
        List<Integer> toAdd = IntStream.rangeClosed(0, 32).boxed().toList();
        assertTrue(customArrayDeque.addAll(toAdd));
        assertEquals(33, customArrayDeque.size());
        assertEquals(64, customArrayDeque.getArraySize());
    }

    @Test
    public void whenAddingCollectionWithValues_0_to_64_returnsTrue_andResizesTo_128() {
        CustomArrayDeque<Integer> customArrayDeque = new CustomArrayDeque<>();
        List<Integer> toAdd = IntStream.rangeClosed(0, 64).boxed().toList();
        assertTrue(customArrayDeque.addAll(toAdd));
        assertEquals(65, customArrayDeque.size());
        assertEquals(128, customArrayDeque.getArraySize());
    }

    @Test
    public void whenAddingCollectionWithNullValue_throwsNullPointerException() {
        CustomArrayDeque<Integer> customArrayDeque = new CustomArrayDeque<>();
        List<Integer> arr = new ArrayList<>();
        arr.add(1);
        arr.add(null);
        assertThrows(NullPointerException.class, () -> customArrayDeque.addAll(arr));
        assertEquals(1, customArrayDeque.size());
        assertEquals(16, customArrayDeque.getArraySize());
    }

    @Test
    public void whenAddingFirst_null_throwsNullPointerException() {
        CustomArrayDeque<Integer> customArrayDeque = new CustomArrayDeque<>();
        assertThrows(NullPointerException.class, () -> customArrayDeque.addFirst(null));
    }

    @Test
    public void whenAddingFirst_toEmptyList_addsElementToStartOfQueue() {
        CustomArrayDeque<Integer> customArrayDeque = new CustomArrayDeque<>();
        customArrayDeque.addFirst(1);
        assertEquals(1, customArrayDeque.size());
        assertEquals(16, customArrayDeque.getArraySize());
    }

    @Test
    public void whenAddingFirst_1_to_6_addsElementsToStartOfQueue() {
        CustomArrayDeque<Integer> customArrayDeque = new CustomArrayDeque<>();
        customArrayDeque.addFirst(1);
        customArrayDeque.addFirst(2);
        customArrayDeque.addFirst(3);
        customArrayDeque.addFirst(4);
        customArrayDeque.addFirst(5);
        customArrayDeque.addFirst(6);
        assertEquals(6, customArrayDeque.size());
        assertEquals(32, customArrayDeque.getArraySize());
    }

    @Test
    public void whenAddingLast_NullItem_throwsNullPointerException() {
        CustomArrayDeque<Integer> customArrayDeque = new CustomArrayDeque<>();
        assertThrows(NullPointerException.class, () -> customArrayDeque.addLast(null));
    }

    @Test
    public void whenAddingLastValue_1_returnsTrue_andSizeOf_1() {
        CustomArrayDeque<Integer> customArrayDeque = new CustomArrayDeque<>();
        customArrayDeque.addLast(1);
        assertEquals(1, customArrayDeque.size());
    }

    @Test
    public void whenAddingLast_1_2_3_returnsTrue_andSizeOf_3() {
        CustomArrayDeque<Integer> customArrayDeque = new CustomArrayDeque<>();
        customArrayDeque.addLast(1);
        customArrayDeque.addLast(2);
        customArrayDeque.addLast(3);
        assertEquals(3, customArrayDeque.size());
    }

    @Test
    public void whenAddingLast_triggersResize_returnsTrue_andDequeSizeOf_32() {
        CustomArrayDeque<Integer> customArrayDeque = new CustomArrayDeque<>();
        List<Integer> toAdd = IntStream.rangeClosed(0, 11).boxed().toList();
        assertTrue(customArrayDeque.addAll(toAdd));
        assertEquals(16, customArrayDeque.getArraySize());
        customArrayDeque.addLast(9);
        assertEquals(32, customArrayDeque.getArraySize());
    }

    @Test
    public void whenOfferingFirstNullItem_throwsNullPointerException() {
        CustomArrayDeque<Integer> customArrayDeque = new CustomArrayDeque<>();
        assertThrows(NullPointerException.class, () -> customArrayDeque.offerFirst(null));
    }

    @Test
    public void whenOfferingFirstValue_1_returnsTrue_andSizeOf_1() {
        CustomArrayDeque<Integer> customArrayDeque = new CustomArrayDeque<>();
        assertTrue(customArrayDeque.offerFirst(1));
        assertEquals(1, customArrayDeque.size());
    }

    @Test
    public void whenOfferingFirstValues_1_2_3_returnsTrue_andSizeOf_3() {
        CustomArrayDeque<Integer> customArrayDeque = new CustomArrayDeque<>();
        assertTrue(customArrayDeque.offerFirst(1));
        assertTrue(customArrayDeque.offerFirst(2));
        assertTrue(customArrayDeque.offerFirst(3));
        assertEquals(3, customArrayDeque.size());
    }

    @Test
    public void whenOfferingFirstValue_triggersResize_returnsTrue_andDequeSizeOf_32() {
        CustomArrayDeque<Integer> customArrayDeque = new CustomArrayDeque<>();
        assertTrue(customArrayDeque.offerFirst(1));
        assertTrue(customArrayDeque.offerFirst(2));
        assertTrue(customArrayDeque.offerFirst(3));
        assertTrue(customArrayDeque.offerFirst(4));
        assertTrue(customArrayDeque.offerFirst(5));
        assertEquals(32, customArrayDeque.getArraySize());
    }

    @Test
    public void whenOfferingLast_NullItem_throwsNullPointerException() {
        CustomArrayDeque<Integer> customArrayDeque = new CustomArrayDeque<>();
        assertThrows(NullPointerException.class, () -> customArrayDeque.offerLast(null));
    }

    @Test
    public void whenOfferingLast_1_returnsTrue_andSizeOf_1() {
        CustomArrayDeque<Integer> customArrayDeque = new CustomArrayDeque<>();
        assertTrue(customArrayDeque.offerLast(1));
        assertEquals(1, customArrayDeque.size());
    }

    @Test
    public void whenOfferingLast_1_2_3_returnsTrue_andSizeOf_3() {
        CustomArrayDeque<Integer> customArrayDeque = new CustomArrayDeque<>();
        assertTrue(customArrayDeque.offerLast(1));
        assertTrue(customArrayDeque.offerLast(2));
        assertTrue(customArrayDeque.offerLast(3));
        assertEquals(3, customArrayDeque.size());
    }

    @Test
    public void whenOfferingLast_triggersResize_returnsTrue_andDequeSizeOf_32() {
        CustomArrayDeque<Integer> customArrayDeque = new CustomArrayDeque<>();
        List<Integer> toAdd = IntStream.rangeClosed(0, 11).boxed().toList();
        assertTrue(customArrayDeque.addAll(toAdd));
        assertEquals(16, customArrayDeque.getArraySize());
        customArrayDeque.offerLast(9);
        assertEquals(32, customArrayDeque.getArraySize());
    }

    @Test
    public void whenGettingFirst_onEmptyList_throwsNoSuchElementException() {
        CustomArrayDeque<Integer> customArrayDeque = new CustomArrayDeque<>();
        assertThrows(NoSuchElementException.class, customArrayDeque::getFirst);
    }

    @Test
    public void whenGettingFirstValue_returns_1() {
        CustomArrayDeque<Integer> customArrayDeque = new CustomArrayDeque<>();
        assertTrue(customArrayDeque.offerFirst(1));
        assertEquals(1, customArrayDeque.size());
        assertEquals(1, customArrayDeque.getFirst());
    }

    @Test
    public void whenGettingFirstValues_returns_3() {
        CustomArrayDeque<Integer> customArrayDeque = new CustomArrayDeque<>();
        assertTrue(customArrayDeque.offerFirst(1));
        assertTrue(customArrayDeque.offerFirst(2));
        assertTrue(customArrayDeque.offerFirst(3));
        assertEquals(3, customArrayDeque.size());
        assertEquals(3, customArrayDeque.getFirst());
    }

    @Test
    public void whenGettingFirstValue_returns_5() {
        CustomArrayDeque<Integer> customArrayDeque = new CustomArrayDeque<>();
        assertTrue(customArrayDeque.offerFirst(1));
        assertTrue(customArrayDeque.offerFirst(2));
        assertTrue(customArrayDeque.offerFirst(3));
        assertTrue(customArrayDeque.offerFirst(4));
        assertTrue(customArrayDeque.offerFirst(5));
        assertEquals(32, customArrayDeque.getArraySize());
        assertEquals(5, customArrayDeque.getFirst());
    }

    @Test
    public void whenGettingLast_onEmptyList_throwsNoSuchElementException() {
        CustomArrayDeque<Integer> customArrayDeque = new CustomArrayDeque<>();
        assertThrows(NoSuchElementException.class, customArrayDeque::getLast);
    }

    @Test
    public void whenGettingLast_1_returns_1() {
        CustomArrayDeque<Integer> customArrayDeque = new CustomArrayDeque<>();
        customArrayDeque.offerLast(1);
        assertEquals(1, customArrayDeque.size());
        assertEquals(1, customArrayDeque.getLast());
    }

    @Test
    public void whenGettingLast_1_2_3_returns_3() {
        CustomArrayDeque<Integer> customArrayDeque = new CustomArrayDeque<>();
        assertTrue(customArrayDeque.offerLast(1));
        assertTrue(customArrayDeque.offerLast(2));
        assertTrue(customArrayDeque.offerLast(3));
        assertEquals(3, customArrayDeque.size());
        assertEquals(3, customArrayDeque.getLast());
    }

    @Test
    public void whenGettingLast_triggersResize_returns_11() {
        CustomArrayDeque<Integer> customArrayDeque = new CustomArrayDeque<>();
        List<Integer> toAdd = IntStream.rangeClosed(0, 11).boxed().toList();
        assertTrue(customArrayDeque.addAll(toAdd));
        assertEquals(16, customArrayDeque.getArraySize());
        customArrayDeque.offerLast(9);
        assertEquals(32, customArrayDeque.getArraySize());
        assertEquals(9, customArrayDeque.getLast());
    }

    @Test
    public void whenRemovingFirst_onEmptyList_throwsNoSuchElementException() {
        CustomArrayDeque<Integer> customArrayDeque = new CustomArrayDeque<>();
        assertThrows(NoSuchElementException.class, customArrayDeque::removeFirst);
    }

    @Test
    public void whenRemovingFirst_onListOf_1_returns_1() {
        CustomArrayDeque<Integer> customArrayDeque = new CustomArrayDeque<>();
        customArrayDeque.add(1);
        assertEquals(1, customArrayDeque.size());
        assertEquals(1, customArrayDeque.removeFirst());
        assertEquals(0, customArrayDeque.size());
    }

    @Test
    public void whenRemovingFirst_onListOf_1_2_3_returns_1() {
        CustomArrayDeque<Integer> customArrayDeque = new CustomArrayDeque<>();
        assertTrue(customArrayDeque.add(1));
        assertTrue(customArrayDeque.add(2));
        assertTrue(customArrayDeque.add(3));
        assertEquals(3, customArrayDeque.size());
        assertEquals(1, customArrayDeque.removeFirst());
        assertEquals(2, customArrayDeque.size());
    }

    @Test
    public void whenRemovingFirstValue_onListOf_1_2_3_4_5_returns_1() {
        CustomArrayDeque<Integer> customArrayDeque = new CustomArrayDeque<>();
        assertTrue(customArrayDeque.add(1));
        assertTrue(customArrayDeque.add(2));
        assertTrue(customArrayDeque.add(3));
        assertTrue(customArrayDeque.add(4));
        assertTrue(customArrayDeque.add(5));
        assertEquals(5, customArrayDeque.size());
        assertEquals(1, customArrayDeque.removeFirst());
    }

    @Test
    public void whenRemovingFirst_afterAddingFirst_returnsCorrectElement() {
        CustomArrayDeque<Integer> customArrayDeque = new CustomArrayDeque<>();
        customArrayDeque.addFirst(10);
        customArrayDeque.addFirst(20);
        assertEquals(2, customArrayDeque.size());
        assertEquals(20, customArrayDeque.removeFirst());
        assertEquals(10, customArrayDeque.removeFirst());
        assertEquals(0, customArrayDeque.size());
    }

    @Test
    public void whenRemovingAllElements_resetsDequeToInitialState() {
        CustomArrayDeque<Integer> customArrayDeque = new CustomArrayDeque<>();
        customArrayDeque.add(1);
        customArrayDeque.add(2);
        assertEquals(1, customArrayDeque.removeFirst());
        assertEquals(1, customArrayDeque.size());
        assertEquals(2, customArrayDeque.removeFirst());
        assertEquals(0, customArrayDeque.size());
        assertEquals(16, customArrayDeque.getArraySize());
    }

    @Test
    public void whenRemovingLast_onEmptyList_throwsNoSuchElementException() {
        CustomArrayDeque<Integer> customArrayDeque = new CustomArrayDeque<>();
        assertThrows(NoSuchElementException.class, customArrayDeque::removeLast);
    }

    @Test
    public void whenRemovingLast_onListOf_1_returns_1() {
        CustomArrayDeque<Integer> customArrayDeque = new CustomArrayDeque<>();
        customArrayDeque.add(1);
        assertEquals(1, customArrayDeque.size());
        assertEquals(1, customArrayDeque.removeLast());
        assertEquals(0, customArrayDeque.size());
    }

    @Test
    public void whenRemovingLast_onListOf_1_2_3_returns_3() {
        CustomArrayDeque<Integer> customArrayDeque = new CustomArrayDeque<>();
        assertTrue(customArrayDeque.add(1));
        assertTrue(customArrayDeque.add(2));
        assertTrue(customArrayDeque.add(3));
        assertEquals(3, customArrayDeque.size());
        assertEquals(3, customArrayDeque.removeLast());
        assertEquals(2, customArrayDeque.size());
    }

    @Test
    public void whenRemovingLastValue_onListOf_1_2_3_4_5_returns_5() {
        CustomArrayDeque<Integer> customArrayDeque = new CustomArrayDeque<>();
        assertTrue(customArrayDeque.add(1));
        assertTrue(customArrayDeque.add(2));
        assertTrue(customArrayDeque.add(3));
        assertTrue(customArrayDeque.add(4));
        assertTrue(customArrayDeque.add(5));
        assertEquals(5, customArrayDeque.size());
        assertEquals(5, customArrayDeque.removeLast());
        assertEquals(4, customArrayDeque.size());
    }

    @Test
    public void whenRemovingLast_afterAddingLast_returnsCorrectElement() {
        CustomArrayDeque<Integer> customArrayDeque = new CustomArrayDeque<>();
        customArrayDeque.addLast(10);
        customArrayDeque.addLast(20);
        assertEquals(2, customArrayDeque.size());
        assertEquals(20, customArrayDeque.removeLast());
        assertEquals(10, customArrayDeque.removeLast());
        assertEquals(0, customArrayDeque.size());
    }

    @Test
    public void whenRemovingLast_untilEmpty_resetsDequeToInitialState() {
        CustomArrayDeque<Integer> customArrayDeque = new CustomArrayDeque<>();
        customArrayDeque.add(1);
        customArrayDeque.add(2);
        assertEquals(2, customArrayDeque.removeLast());
        assertEquals(1, customArrayDeque.size());
        assertEquals(1, customArrayDeque.removeLast());
        assertEquals(0, customArrayDeque.size());
        assertEquals(16, customArrayDeque.getArraySize());
    }

    @Test
    public void whenPollFirst_onEmptyList_returnsNull() {
        CustomArrayDeque<Integer> customArrayDeque = new CustomArrayDeque<>();
        assertNull(customArrayDeque.pollFirst());
    }

    @Test
    public void whenPollFirst_onListOf_1_returns_1() {
        CustomArrayDeque<Integer> customArrayDeque = new CustomArrayDeque<>();
        customArrayDeque.add(1);
        assertEquals(1, customArrayDeque.size());
        assertEquals(1, customArrayDeque.pollFirst());
        assertEquals(0, customArrayDeque.size());
    }

    @Test
    public void whenPollFirst_onListOf_1_2_3_returns_1() {
        CustomArrayDeque<Integer> customArrayDeque = new CustomArrayDeque<>();
        assertTrue(customArrayDeque.add(1));
        assertTrue(customArrayDeque.add(2));
        assertTrue(customArrayDeque.add(3));
        assertEquals(3, customArrayDeque.size());
        assertEquals(1, customArrayDeque.pollFirst());
        assertEquals(2, customArrayDeque.size());
    }

    @Test
    public void whenPollFirst_afterAddingFirst_returnsCorrectElement() {
        CustomArrayDeque<Integer> customArrayDeque = new CustomArrayDeque<>();
        customArrayDeque.addFirst(10);
        customArrayDeque.addFirst(20);
        assertEquals(2, customArrayDeque.size());
        assertEquals(20, customArrayDeque.pollFirst());
        assertEquals(10, customArrayDeque.pollFirst());
        assertEquals(0, customArrayDeque.size());
        assertEquals(16, customArrayDeque.getArraySize());
    }

    @Test
    public void whenPollLast_onEmptyList_returnsNull() {
        CustomArrayDeque<Integer> customArrayDeque = new CustomArrayDeque<>();
        assertNull(customArrayDeque.pollLast());
    }

    @Test
    public void whenPollLast_onListOf_1_returns_1() {
        CustomArrayDeque<Integer> customArrayDeque = new CustomArrayDeque<>();
        customArrayDeque.add(1);
        assertEquals(1, customArrayDeque.size());
        assertEquals(1, customArrayDeque.pollLast());
        assertEquals(0, customArrayDeque.size());
    }

    @Test
    public void whenPollLast_onListOf_1_2_3_returns_3() {
        CustomArrayDeque<Integer> customArrayDeque = new CustomArrayDeque<>();
        assertTrue(customArrayDeque.add(1));
        assertTrue(customArrayDeque.add(2));
        assertTrue(customArrayDeque.add(3));
        assertEquals(3, customArrayDeque.size());
        assertEquals(3, customArrayDeque.pollLast());
        assertEquals(2, customArrayDeque.size());
    }

    @Test
    public void whenPollLast_afterAddingLast_returnsCorrectElement() {
        CustomArrayDeque<Integer> customArrayDeque = new CustomArrayDeque<>();
        customArrayDeque.addLast(10);
        customArrayDeque.addLast(20);
        assertEquals(2, customArrayDeque.size());
        assertEquals(20, customArrayDeque.pollLast());
        assertEquals(10, customArrayDeque.pollLast());
        assertEquals(0, customArrayDeque.size());
        assertEquals(16, customArrayDeque.getArraySize());
    }

    @Test
    public void whenPeekFirst_onEmptyList_returnsNull() {
        CustomArrayDeque<Integer> customArrayDeque = new CustomArrayDeque<>();
        assertNull(customArrayDeque.peekFirst());
    }

    @Test
    public void whenPeekFirst_onListOf_1_returns_1_withoutRemoving() {
        CustomArrayDeque<Integer> customArrayDeque = new CustomArrayDeque<>();
        customArrayDeque.add(1);
        assertEquals(1, customArrayDeque.size());
        assertEquals(1, customArrayDeque.peekFirst());
        assertEquals(1, customArrayDeque.size());
    }

    @Test
    public void whenPeekFirst_onListOf_1_2_3_returns_1() {
        CustomArrayDeque<Integer> customArrayDeque = new CustomArrayDeque<>();
        assertTrue(customArrayDeque.add(1));
        assertTrue(customArrayDeque.add(2));
        assertTrue(customArrayDeque.add(3));
        assertEquals(3, customArrayDeque.size());
        assertEquals(1, customArrayDeque.peekFirst());
        assertEquals(3, customArrayDeque.size());
    }

    @Test
    public void whenPeekFirst_afterAddingFirst_returnsCorrectElement() {
        CustomArrayDeque<Integer> customArrayDeque = new CustomArrayDeque<>();
        customArrayDeque.addFirst(10);
        customArrayDeque.addFirst(20);
        assertEquals(2, customArrayDeque.size());
        assertEquals(20, customArrayDeque.peekFirst());
        assertEquals(2, customArrayDeque.size());
    }

    @Test
    public void whenPeekLast_onEmptyList_returnsNull() {
        CustomArrayDeque<Integer> customArrayDeque = new CustomArrayDeque<>();
        assertNull(customArrayDeque.peekLast());
    }

    @Test
    public void whenPeekLast_onListOf_1_returns_1_withoutRemoving() {
        CustomArrayDeque<Integer> customArrayDeque = new CustomArrayDeque<>();
        customArrayDeque.add(1);
        assertEquals(1, customArrayDeque.size());
        assertEquals(1, customArrayDeque.peekLast());
        assertEquals(1, customArrayDeque.size());
    }

    @Test
    public void whenPeekLast_onListOf_1_2_3_returns_3() {
        CustomArrayDeque<Integer> customArrayDeque = new CustomArrayDeque<>();
        assertTrue(customArrayDeque.add(1));
        assertTrue(customArrayDeque.add(2));
        assertTrue(customArrayDeque.add(3));
        assertEquals(3, customArrayDeque.size());
        assertEquals(3, customArrayDeque.peekLast());
        assertEquals(3, customArrayDeque.size());
    }

    @Test
    public void whenPeekLast_afterAddingLast_returnsCorrectElement() {
        CustomArrayDeque<Integer> customArrayDeque = new CustomArrayDeque<>();
        customArrayDeque.addLast(10);
        customArrayDeque.addLast(20);
        assertEquals(2, customArrayDeque.size());
        assertEquals(20, customArrayDeque.peekLast());
        assertEquals(2, customArrayDeque.size());
    }

    @Test
    public void whenRemoveFirstOccurrence_onEmptyList_returnsFalse() {
        CustomArrayDeque<Integer> customArrayDeque = new CustomArrayDeque<>();
        assertFalse(customArrayDeque.removeFirstOccurrence(1));
    }

    @Test
    public void whenRemoveFirstOccurrence_itemExistsOnce_removesItemAndReturnsTrue() {
        CustomArrayDeque<Integer> customArrayDeque = new CustomArrayDeque<>();
        customArrayDeque.add(1);
        customArrayDeque.add(2);
        customArrayDeque.add(3);
        assertTrue(customArrayDeque.removeFirstOccurrence(2));
        assertEquals(2, customArrayDeque.size());
        assertEquals(1, customArrayDeque.peekFirst());
        assertEquals(3, customArrayDeque.peekLast());
    }

    @Test
    public void whenRemoveFirstOccurrence_itemExistsMultipleTimes_removesOnlyFirstAndReturnsTrue() {
        CustomArrayDeque<Integer> customArrayDeque = new CustomArrayDeque<>();
        customArrayDeque.add(1);
        customArrayDeque.add(2);
        customArrayDeque.add(1);
        customArrayDeque.add(3);
        assertTrue(customArrayDeque.removeFirstOccurrence(1));
        assertEquals(3, customArrayDeque.size());
        assertEquals(2, customArrayDeque.peekFirst());
        assertEquals(3, customArrayDeque.peekLast());
    }

    @Test
    public void whenRemoveFirstOccurrence_itemDoesNotExist_returnsFalse() {
        CustomArrayDeque<Integer> customArrayDeque = new CustomArrayDeque<>();
        customArrayDeque.add(1);
        customArrayDeque.add(2);
        assertFalse(customArrayDeque.removeFirstOccurrence(99));
        assertEquals(2, customArrayDeque.size());
    }

    @Test
    public void whenRemoveFirstOccurrence_withNullArgument_returnsFalseOrThrowsException() {
        CustomArrayDeque<Integer> customArrayDeque = new CustomArrayDeque<>();
        customArrayDeque.add(1);
        assertFalse(customArrayDeque.removeFirstOccurrence(null));
    }

    @Test
    public void whenRemoveLastOccurrence_onEmptyList_returnsFalse() {
        CustomArrayDeque<Integer> customArrayDeque = new CustomArrayDeque<>();
        assertFalse(customArrayDeque.removeLastOccurrence(1));
    }

    @Test
    public void whenRemoveLastOccurrence_itemExistsOnce_removesItemAndReturnsTrue() {
        CustomArrayDeque<Integer> customArrayDeque = new CustomArrayDeque<>();
        customArrayDeque.add(1);
        customArrayDeque.add(2);
        customArrayDeque.add(3);
        assertTrue(customArrayDeque.removeLastOccurrence(2));
        assertEquals(2, customArrayDeque.size());
        assertEquals(1, customArrayDeque.peekFirst());
        assertEquals(3, customArrayDeque.peekLast());
    }

    @Test
    public void whenRemoveLastOccurrence_itemExistsMultipleTimes_removesOnlyLastAndReturnsTrue() {
        CustomArrayDeque<Integer> customArrayDeque = new CustomArrayDeque<>();
        customArrayDeque.add(1);
        customArrayDeque.add(2);
        customArrayDeque.add(1);
        customArrayDeque.add(3);
        assertTrue(customArrayDeque.removeLastOccurrence(1));
        assertEquals(3, customArrayDeque.size());
        assertEquals(1, customArrayDeque.peekFirst());
        assertEquals(3, customArrayDeque.peekLast());
    }

    @Test
    public void whenRemoveLastOccurrence_itemDoesNotExist_returnsFalse() {
        CustomArrayDeque<Integer> customArrayDeque = new CustomArrayDeque<>();
        customArrayDeque.add(1);
        customArrayDeque.add(2);
        assertFalse(customArrayDeque.removeLastOccurrence(99));
        assertEquals(2, customArrayDeque.size());
    }

    @Test
    public void whenRemoveLastOccurrence_withNullArgument_returnsFalse() {
        CustomArrayDeque<Integer> customArrayDeque = new CustomArrayDeque<>();
        customArrayDeque.add(1);
        assertFalse(customArrayDeque.removeLastOccurrence(null));
    }

    @Test
    public void whenOffer_addsElementToTail_returnsTrue() {
        CustomArrayDeque<Integer> customArrayDeque = new CustomArrayDeque<>();
        assertTrue(customArrayDeque.offer(10));
        assertEquals(1, customArrayDeque.size());
        assertEquals(10, customArrayDeque.peekLast());
    }

    @Test
    public void whenOffer_multipleElements_appendsCorrectly() {
        CustomArrayDeque<Integer> customArrayDeque = new CustomArrayDeque<>();
        assertTrue(customArrayDeque.offer(1));
        assertTrue(customArrayDeque.offer(2));
        assertTrue(customArrayDeque.offer(3));
        assertEquals(3, customArrayDeque.size());
        assertEquals(1, customArrayDeque.peekFirst());
        assertEquals(3, customArrayDeque.peekLast());
    }

    @Test
    public void whenOffer_nullElement_throwsNullPointerException() {
        CustomArrayDeque<Integer> customArrayDeque = new CustomArrayDeque<>();
        assertThrows(NullPointerException.class, () -> customArrayDeque.offer(null));
    }

    @Test
    public void whenRemove_onNonEmptyDeque_removesAndReturnsFirstElement() {
        CustomArrayDeque<Integer> customArrayDeque = new CustomArrayDeque<>();
        customArrayDeque.add(10);
        customArrayDeque.add(20);
        assertEquals(10, customArrayDeque.remove());
        assertEquals(1, customArrayDeque.size());
        assertEquals(20, customArrayDeque.peekFirst());
    }

    @Test
    public void whenRemove_onEmptyDeque_throwsNoSuchElementException() {
        CustomArrayDeque<Integer> customArrayDeque = new CustomArrayDeque<>();
        assertThrows(NoSuchElementException.class, customArrayDeque::remove);
    }

    @Test
    public void whenPoll_onNonEmptyDeque_removesAndReturnsFirstElement() {
        CustomArrayDeque<Integer> customArrayDeque = new CustomArrayDeque<>();
        customArrayDeque.add(10);
        customArrayDeque.add(20);
        assertEquals(10, customArrayDeque.poll());
        assertEquals(1, customArrayDeque.size());
        assertEquals(20, customArrayDeque.peekFirst());
    }

    @Test
    public void whenPoll_onEmptyDeque_returnsNull() {
        CustomArrayDeque<Integer> customArrayDeque = new CustomArrayDeque<>();
        assertNull(customArrayDeque.poll());
    }

    @Test
    public void whenElement_onNonEmptyDeque_returnsFirstElementWithoutRemoving() {
        CustomArrayDeque<Integer> customArrayDeque = new CustomArrayDeque<>();
        customArrayDeque.add(10);
        customArrayDeque.add(20);
        assertEquals(10, customArrayDeque.element());
        assertEquals(2, customArrayDeque.size());
    }

    @Test
    public void whenElement_onEmptyDeque_throwsNoSuchElementException() {
        CustomArrayDeque<Integer> customArrayDeque = new CustomArrayDeque<>();
        assertThrows(NoSuchElementException.class, customArrayDeque::element);
    }

    @Test
    public void whenPeek_onNonEmptyDeque_returnsFirstElementWithoutRemoving() {
        CustomArrayDeque<Integer> customArrayDeque = new CustomArrayDeque<>();
        customArrayDeque.add(10);
        customArrayDeque.add(20);
        assertEquals(10, customArrayDeque.peek());
        assertEquals(2, customArrayDeque.size());
    }

    @Test
    public void whenPeek_onEmptyDeque_returnsNull() {
        CustomArrayDeque<Integer> customArrayDeque = new CustomArrayDeque<>();
        assertNull(customArrayDeque.peek());
    }

    @Test
    public void whenPop_onNonEmptyDeque_removesAndReturnsFirstElement() {
        CustomArrayDeque<Integer> customArrayDeque = new CustomArrayDeque<>();
        customArrayDeque.add(10);
        customArrayDeque.add(20);
        assertEquals(10, customArrayDeque.pop());
        assertEquals(1, customArrayDeque.size());
        assertEquals(20, customArrayDeque.peekFirst());
    }

    @Test
    public void whenPop_onEmptyDeque_throwsNoSuchElementException() {
        CustomArrayDeque<Integer> customArrayDeque = new CustomArrayDeque<>();
        assertThrows(NoSuchElementException.class, customArrayDeque::pop);
    }

    @Test
    public void whenSize_onEmptyDeque_returnsZero() {
        CustomArrayDeque<Integer> customArrayDeque = new CustomArrayDeque<>();
        assertEquals(0, customArrayDeque.size());
    }

    @Test
    public void whenSize_afterAdditions_returnsCorrectCount() {
        CustomArrayDeque<Integer> customArrayDeque = new CustomArrayDeque<>();
        customArrayDeque.add(10);
        customArrayDeque.add(20);
        customArrayDeque.add(30);
        assertEquals(3, customArrayDeque.size());
    }

    @Test
    public void whenSize_afterAdditionsAndRemovals_returnsCorrectCount() {
        CustomArrayDeque<Integer> customArrayDeque = new CustomArrayDeque<>();
        customArrayDeque.add(10);
        customArrayDeque.add(20);
        customArrayDeque.add(30);
        customArrayDeque.removeFirst();
        assertEquals(2, customArrayDeque.size());
        customArrayDeque.removeLast();
        assertEquals(1, customArrayDeque.size());
    }

    @Test
    public void whenClear_onNonEmptyDeque_resetsSizeAndRemovesAllElements() {
        CustomArrayDeque<Integer> customArrayDeque = new CustomArrayDeque<>();
        customArrayDeque.add(10);
        customArrayDeque.add(20);
        customArrayDeque.add(30);
        customArrayDeque.clear();
        assertTrue(customArrayDeque.isEmpty());
        assertNull(customArrayDeque.peekFirst());
        assertNull(customArrayDeque.peekLast());
    }

    @Test
    public void whenClear_onEmptyDeque_doesNothing() {
        CustomArrayDeque<Integer> customArrayDeque = new CustomArrayDeque<>();
        customArrayDeque.clear();
        assertTrue(customArrayDeque.isEmpty());
    }

    @Test
    public void whenNotEmpty_onIsEmpty_returnsFalse() {
        CustomArrayDeque<Integer> customArrayDeque = new CustomArrayDeque<>(List.of(1));
        assertFalse(customArrayDeque.isEmpty());
    }

    @Test
    public void whenClear_andThenAddElements_worksNormally() {
        CustomArrayDeque<Integer> customArrayDeque = new CustomArrayDeque<>();
        customArrayDeque.add(10);
        customArrayDeque.clear();
        assertTrue(customArrayDeque.isEmpty());
        customArrayDeque.add(42);
        assertEquals(42, customArrayDeque.peekFirst());
        assertEquals(42, customArrayDeque.peekLast());
    }

    @Test
    public void whenPush_addsElementToFront_returnsCorrectSizeAndPeek() {
        CustomArrayDeque<Integer> customArrayDeque = new CustomArrayDeque<>();
        customArrayDeque.push(10);
        assertEquals(1, customArrayDeque.size());
        assertEquals(10, customArrayDeque.peekFirst());
    }

    @Test
    public void whenPush_multipleElements_placesThemAtFrontInLIFOOrder() {
        CustomArrayDeque<Integer> customArrayDeque = new CustomArrayDeque<>();
        customArrayDeque.push(1);
        customArrayDeque.push(2);
        customArrayDeque.push(3);
        assertEquals(3, customArrayDeque.size());
        assertEquals(3, customArrayDeque.peekFirst());
        assertEquals(1, customArrayDeque.peekLast());
    }

    @Test
    public void whenPush_nullElement_throwsNullPointerException() {
        CustomArrayDeque<Integer> customArrayDeque = new CustomArrayDeque<>();
        assertThrows(NullPointerException.class, () -> customArrayDeque.push(null));
    }

    @Test
    public void whenRemoveObject_existingElement_removesAndReturnsTrue() {
        CustomArrayDeque<Integer> customArrayDeque = new CustomArrayDeque<>();
        customArrayDeque.add(10);
        customArrayDeque.add(20);
        customArrayDeque.add(30);
        assertTrue(customArrayDeque.remove(20));
        assertEquals(2, customArrayDeque.size());
        assertFalse(customArrayDeque.contains(20));
    }

    @Test
    public void whenRemoveObject_nonExistentElement_returnsFalse() {
        CustomArrayDeque<Integer> customArrayDeque = new CustomArrayDeque<>();
        customArrayDeque.add(10);
        customArrayDeque.add(20);
        assertFalse(customArrayDeque.remove(99));
        assertEquals(2, customArrayDeque.size());
    }

    @Test
    public void whenRemoveObject_nullElement_returnsFalse() {
        CustomArrayDeque<Integer> customArrayDeque = new CustomArrayDeque<>();
        customArrayDeque.add(10);
        assertFalse(customArrayDeque.remove(null));
        assertEquals(1, customArrayDeque.size());
    }

    @Test
    public void whenRemoveObject_duplicateElements_removesFirstOccurrence() {
        CustomArrayDeque<Integer> customArrayDeque = new CustomArrayDeque<>();
        customArrayDeque.add(10);
        customArrayDeque.add(20);
        customArrayDeque.add(10);
        assertTrue(customArrayDeque.remove(10));
        assertEquals(2, customArrayDeque.size());
        assertEquals(20, customArrayDeque.peekFirst());
        assertEquals(10, customArrayDeque.peekLast());
    }

    @Test
    public void whenContains_existingElement_returnsTrue() {
        CustomArrayDeque<Integer> customArrayDeque = new CustomArrayDeque<>();
        customArrayDeque.add(10);
        customArrayDeque.add(20);
        customArrayDeque.add(30);
        assertTrue(customArrayDeque.contains(20));
    }

    @Test
    public void whenContains_nonExistentElement_returnsFalse() {
        CustomArrayDeque<Integer> customArrayDeque = new CustomArrayDeque<>();
        customArrayDeque.add(10);
        customArrayDeque.add(20);
        assertFalse(customArrayDeque.contains(99));
    }

    @Test
    public void whenContains_nullElement_returnsFalse() {
        CustomArrayDeque<Integer> customArrayDeque = new CustomArrayDeque<>();
        customArrayDeque.add(10);
        assertFalse(customArrayDeque.contains(null));
    }

    @Test
    public void whenContains_onEmptyDeque_returnsFalse() {
        CustomArrayDeque<Integer> customArrayDeque = new CustomArrayDeque<>();
        assertFalse(customArrayDeque.contains(10));
    }

    @Test
    public void whenRemoveAll_withMatchingElements_removesAllOccurrencesAndReturnsTrue() {
        CustomArrayDeque<Integer> customArrayDeque = new CustomArrayDeque<>();
        customArrayDeque.add(10);
        customArrayDeque.add(20);
        customArrayDeque.add(10);
        customArrayDeque.add(30);
        assertTrue(customArrayDeque.removeAll(List.of(10, 30)));
        assertEquals(1, customArrayDeque.size());
        assertEquals(20, customArrayDeque.peekFirst());
        assertFalse(customArrayDeque.contains(10));
        assertFalse(customArrayDeque.contains(30));
    }

    @Test
    public void whenRemoveAll_withNoMatchingElements_returnsFalse() {
        CustomArrayDeque<Integer> customArrayDeque = new CustomArrayDeque<>();
        customArrayDeque.add(10);
        customArrayDeque.add(20);
        assertFalse(customArrayDeque.removeAll(List.of(99, 100)));
        assertEquals(2, customArrayDeque.size());
    }

    @Test
    public void whenRemoveAll_withEmptyCollection_returnsFalse() {
        CustomArrayDeque<Integer> customArrayDeque = new CustomArrayDeque<>();
        customArrayDeque.add(10);
        customArrayDeque.add(20);
        assertFalse(customArrayDeque.removeAll(Collections.emptyList()));
        assertEquals(2, customArrayDeque.size());
    }

    @Test
    public void whenRemoveAll_withNullCollection_throwsNullPointerException() {
        CustomArrayDeque<Integer> customArrayDeque = new CustomArrayDeque<>();
        customArrayDeque.add(10);
        assertThrows(NullPointerException.class, () -> customArrayDeque.removeAll(null));
    }

    @Test
    public void whenRetainAll_withMatchingElements_keepsOnlySpecifiedAndReturnsTrue() {
        CustomArrayDeque<Integer> customArrayDeque = new CustomArrayDeque<>();
        customArrayDeque.add(10);
        customArrayDeque.add(20);
        customArrayDeque.add(30);
        assertTrue(customArrayDeque.retainAll(List.of(10, 30)));
        assertEquals(2, customArrayDeque.size());
        assertTrue(customArrayDeque.contains(10));
        assertTrue(customArrayDeque.contains(30));
        assertFalse(customArrayDeque.contains(20));
    }

    @Test
    public void whenRetainAll_withAllElementsPresent_returnsFalse() {
        CustomArrayDeque<Integer> customArrayDeque = new CustomArrayDeque<>();
        customArrayDeque.add(10);
        customArrayDeque.add(20);
        assertFalse(customArrayDeque.retainAll(List.of(10, 20, 30)));
        assertEquals(2, customArrayDeque.size());
    }

    @Test
    public void whenRetainAll_withEmptyCollection_clearsDequeAndReturnsTrue() {
        CustomArrayDeque<Integer> customArrayDeque = new CustomArrayDeque<>();
        customArrayDeque.add(10);
        customArrayDeque.add(20);
        assertTrue(customArrayDeque.retainAll(Collections.emptyList()));
        assertEquals(0, customArrayDeque.size());
        assertTrue(customArrayDeque.isEmpty());
    }

    @Test
    public void whenRetainAll_withNullCollection_throwsNullPointerException() {
        CustomArrayDeque<Integer> customArrayDeque = new CustomArrayDeque<>();
        customArrayDeque.add(10);
        assertThrows(NullPointerException.class, () -> customArrayDeque.retainAll(null));
    }

    @Test
    public void whenContainsAll_allElementsPresent_returnsTrue() {
        CustomArrayDeque<Integer> customArrayDeque = new CustomArrayDeque<>();
        customArrayDeque.add(10);
        customArrayDeque.add(20);
        customArrayDeque.add(30);
        assertTrue(customArrayDeque.containsAll(List.of(10, 30)));
    }

    @Test
    public void whenContainsAll_someElementsMissing_returnsFalse() {
        CustomArrayDeque<Integer> customArrayDeque = new CustomArrayDeque<>();
        customArrayDeque.add(10);
        customArrayDeque.add(20);
        assertFalse(customArrayDeque.containsAll(List.of(10, 99)));
    }

    @Test
    public void whenContainsAll_emptyCollection_returnsTrue() {
        CustomArrayDeque<Integer> customArrayDeque = new CustomArrayDeque<>();
        customArrayDeque.add(10);
        customArrayDeque.add(20);
        assertTrue(customArrayDeque.containsAll(Collections.emptyList()));
    }

    @Test
    public void whenContainsAll_nullCollection_throwsNullPointerException() {
        CustomArrayDeque<Integer> customArrayDeque = new CustomArrayDeque<>();
        customArrayDeque.add(10);
        assertThrows(NullPointerException.class, () -> customArrayDeque.containsAll(null));
    }

    @Test
    public void whenToArray_onNonEmptyDeque_returnsCorrectArrayAndOrder() {
        CustomArrayDeque<Integer> customArrayDeque = new CustomArrayDeque<>();
        customArrayDeque.add(10);
        customArrayDeque.add(20);
        customArrayDeque.add(30);
        Object[] result = customArrayDeque.toArray();
        assertNotNull(result);
        assertArrayEquals(new Object[]{10, 20, 30}, result);
    }

    @Test
    public void whenToArray_onEmptyDeque_returnsEmptyArray() {
        CustomArrayDeque<Integer> customArrayDeque = new CustomArrayDeque<>();
        Object[] result = customArrayDeque.toArray();
        assertNotNull(result);
        assertEquals(0, result.length);
    }

    @Test
    public void whenToArray_afterShiftingOrModifications_returnsActiveElementsInOrder() {
        CustomArrayDeque<Integer> customArrayDeque = new CustomArrayDeque<>();
        customArrayDeque.add(10);
        customArrayDeque.add(20);
        customArrayDeque.add(30);
        customArrayDeque.remove(10);
        Object[] result = customArrayDeque.toArray();
        assertArrayEquals(new Object[]{20, 30}, result);
    }

    @Test
    public void whenToArrayWithArray_sufficientCapacity_fillsAndReturnsSameArray() {
        CustomArrayDeque<Integer> customArrayDeque = new CustomArrayDeque<>();
        customArrayDeque.add(10);
        customArrayDeque.add(20);

        Integer[] target = new Integer[2];
        Integer[] result = customArrayDeque.toArray(target);

        assertArrayEquals(target, result);
        assertArrayEquals(new Integer[]{10, 20}, result);
    }

    @Test
    public void whenToArrayWithArray_largerCapacity_fillsWithNullTerminator() {
        CustomArrayDeque<Integer> customArrayDeque = new CustomArrayDeque<>();
        customArrayDeque.add(10);
        customArrayDeque.add(20);

        Integer[] target = new Integer[5];
        Integer[] result = customArrayDeque.toArray(target);

        assertArrayEquals(target, result);
        assertArrayEquals(new Integer[]{10, 20, null, null, null}, result);
    }

    @Test
    public void whenToArrayWithArray_insufficientCapacity_allocatesNewArrayOfSameRuntimeType() {
        CustomArrayDeque<Integer> customArrayDeque = new CustomArrayDeque<>();
        customArrayDeque.add(10);
        customArrayDeque.add(20);
        customArrayDeque.add(30);
        Integer[] target = new Integer[0];
        Integer[] result = customArrayDeque.toArray(target);
        assertNotSame(target, result);
        assertArrayEquals(new Integer[]{10, 20, 30}, result);
    }

    @Test
    public void whenIterator_onNonEmptyDeque_iteratesThroughAllElements() {
        CustomArrayDeque<Integer> customArrayDeque = new CustomArrayDeque<>();
        customArrayDeque.add(10);
        customArrayDeque.add(20);
        customArrayDeque.add(30);
        Iterator<Integer> iterator = customArrayDeque.iterator();
        assertTrue(iterator.hasNext());
        assertEquals(10, iterator.next());
        assertTrue(iterator.hasNext());
        assertEquals(20, iterator.next());
        assertTrue(iterator.hasNext());
        assertEquals(30, iterator.next());
        assertFalse(iterator.hasNext());
    }

    @Test
    public void whenIterator_onEmptyDeque_hasNoElements() {
        CustomArrayDeque<Integer> customArrayDeque = new CustomArrayDeque<>();
        Iterator<Integer> iterator = customArrayDeque.iterator();
        assertFalse(iterator.hasNext());
    }

    @Test
    public void whenIterator_nextWithoutHasNext_throwsNoSuchElementException() {
        CustomArrayDeque<Integer> customArrayDeque = new CustomArrayDeque<>();
        Iterator<Integer> iterator = customArrayDeque.iterator();
        assertThrows(NoSuchElementException.class, iterator::next);
    }

    @Test
    public void whenIterator_removeElement_removesCurrentElementSuccessfully() {
        CustomArrayDeque<Integer> customArrayDeque = new CustomArrayDeque<>();
        customArrayDeque.add(10);
        customArrayDeque.add(20);
        customArrayDeque.add(30);
        Iterator<Integer> iterator = customArrayDeque.iterator();
        assertEquals(10, iterator.next());
        iterator.remove();
        assertEquals(2, customArrayDeque.size());
        assertFalse(customArrayDeque.contains(10));
        assertEquals(20, customArrayDeque.peekFirst());
    }

    @Test
    public void whenIterator_removeWithoutNext_throwsIllegalStateException() {
        CustomArrayDeque<Integer> customArrayDeque = new CustomArrayDeque<>();
        customArrayDeque.add(10);
        Iterator<Integer> iterator = customArrayDeque.iterator();
        assertThrows(IllegalStateException.class, iterator::remove);
    }

    @Test
    public void whenIterator_multipleRemovals_removesAndMaintainsCorrectIterationState() {
        CustomArrayDeque<Integer> customArrayDeque = new CustomArrayDeque<>();
        customArrayDeque.add(10);
        customArrayDeque.add(20);
        customArrayDeque.add(30);
        customArrayDeque.add(40);
        Iterator<Integer> iterator = customArrayDeque.iterator();
        assertEquals(10, iterator.next());
        iterator.remove();
        assertEquals(20, iterator.next());
        assertEquals(30, iterator.next());
        iterator.remove();
        assertEquals(40, iterator.next());
        assertFalse(iterator.hasNext());
        assertEquals(2, customArrayDeque.size());
        assertFalse(customArrayDeque.contains(10));
        assertFalse(customArrayDeque.contains(30));
        assertTrue(customArrayDeque.contains(20));
        assertTrue(customArrayDeque.contains(40));
    }

    @Test
    public void whenIterator_callRemoveTwiceWithoutNext_throwsIllegalStateException() {
        CustomArrayDeque<Integer> customArrayDeque = new CustomArrayDeque<>();
        customArrayDeque.add(10);
        customArrayDeque.add(20);
        Iterator<Integer> iterator = customArrayDeque.iterator();
        assertEquals(10, iterator.next());
        iterator.remove();
        assertThrows(IllegalStateException.class, iterator::remove);
    }

    @Test
    public void whenDescendingIterator_onNonEmptyDeque_iteratesInReverseOrder() {
        CustomArrayDeque<Integer> customArrayDeque = new CustomArrayDeque<>();
        customArrayDeque.add(10);
        customArrayDeque.add(20);
        customArrayDeque.add(30);
        Iterator<Integer> iterator = customArrayDeque.descendingIterator();
        assertTrue(iterator.hasNext());
        assertEquals(30, iterator.next());
        assertTrue(iterator.hasNext());
        assertEquals(20, iterator.next());
        assertTrue(iterator.hasNext());
        assertEquals(10, iterator.next());
        assertFalse(iterator.hasNext());
    }

    @Test
    public void whenDescendingIterator_onEmptyDeque_hasNoElements() {
        CustomArrayDeque<Integer> customArrayDeque = new CustomArrayDeque<>();
        Iterator<Integer> iterator = customArrayDeque.descendingIterator();
        assertFalse(iterator.hasNext());
    }

    @Test
    public void whenDescendingIterator_nextWithoutHasNext_throwsNoSuchElementException() {
        CustomArrayDeque<Integer> customArrayDeque = new CustomArrayDeque<>();
        Iterator<Integer> iterator = customArrayDeque.descendingIterator();
        assertThrows(NoSuchElementException.class, iterator::next);
    }

    @Test
    public void whenDescendingIterator_removeElement_removesCurrentElementSuccessfully() {
        CustomArrayDeque<Integer> customArrayDeque = new CustomArrayDeque<>();
        customArrayDeque.add(10);
        customArrayDeque.add(20);
        customArrayDeque.add(30);
        Iterator<Integer> iterator = customArrayDeque.descendingIterator();
        assertEquals(30, iterator.next());
        iterator.remove();
        assertEquals(2, customArrayDeque.size());
        assertFalse(customArrayDeque.contains(30));
        assertEquals(20, customArrayDeque.peekLast());
    }

    @Test
    public void whenDescendingIterator_removeWithoutNext_throwsIllegalStateException() {
        CustomArrayDeque<Integer> customArrayDeque = new CustomArrayDeque<>();
        customArrayDeque.add(10);
        Iterator<Integer> iterator = customArrayDeque.descendingIterator();
        assertThrows(IllegalStateException.class, iterator::remove);
    }

    @Test
    public void whenToString_onEmptyDeque_returnsEmptyBrackets() {
        CustomArrayDeque<Integer> customArrayDeque = new CustomArrayDeque<>();
        assertEquals("[]", customArrayDeque.toString());
    }

    @Test
    public void whenToString_onSingleElementDeque_returnsSingleElementFormatted() {
        CustomArrayDeque<Integer> customArrayDeque = new CustomArrayDeque<>();
        customArrayDeque.add(10);
        assertEquals("[10]", customArrayDeque.toString());
    }

    @Test
    public void whenToString_onMultipleElementsDeque_returnsCommaSeparatedElementsInOrder() {
        CustomArrayDeque<Integer> customArrayDeque = new CustomArrayDeque<>();
        customArrayDeque.add(10);
        customArrayDeque.add(20);
        customArrayDeque.add(30);
        assertEquals("[10, 20, 30]", customArrayDeque.toString());
    }

    @Test
    public void whenToString_afterModificationsAndShifting_returnsActiveElementsInOrder() {
        CustomArrayDeque<Integer> customArrayDeque = new CustomArrayDeque<>();
        customArrayDeque.add(10);
        customArrayDeque.add(20);
        customArrayDeque.add(30);
        customArrayDeque.removeFirst();
        customArrayDeque.addLast(40);
        assertEquals("[20, 30, 40]", customArrayDeque.toString());
    }

    @Test
    public void whenEquals_withSameInstance_returnsTrue() {
        CustomArrayDeque<Integer> customArrayDeque = new CustomArrayDeque<>();
        customArrayDeque.add(10);
        customArrayDeque.add(20);
        assertTrue(customArrayDeque.equals(customArrayDeque));
    }

    @Test
    public void whenEquals_withEquivalentDeque_returnsTrue() {
        CustomArrayDeque<Integer> deque1 = new CustomArrayDeque<>();
        deque1.add(10);
        deque1.add(20);
        deque1.add(30);
        CustomArrayDeque<Integer> deque2 = new CustomArrayDeque<>();
        deque2.add(10);
        deque2.add(20);
        deque2.add(30);
        assertTrue(deque1.equals(deque2));
        assertTrue(deque2.equals(deque1));
    }

    @Test
    public void whenEquals_withStandardDequeImplementation_returnsTrue() {
        CustomArrayDeque<Integer> customDeque = new CustomArrayDeque<>();
        customDeque.add(10);
        customDeque.add(20);
        java.util.ArrayDeque<Integer> standardDeque = new java.util.ArrayDeque<>();
        standardDeque.add(10);
        standardDeque.add(20);
        assertTrue(customDeque.equals(standardDeque));
    }

    @Test
    public void whenEquals_withDifferentSizes_returnsFalse() {
        CustomArrayDeque<Integer> deque1 = new CustomArrayDeque<>();
        deque1.add(10);
        deque1.add(20);
        CustomArrayDeque<Integer> deque2 = new CustomArrayDeque<>();
        deque2.add(10);
        assertFalse(deque1.equals(deque2));
    }

    @Test
    public void whenEquals_withDifferentElementsOrOrder_returnsFalse() {
        CustomArrayDeque<Integer> deque1 = new CustomArrayDeque<>();
        deque1.add(10);
        deque1.add(20);
        CustomArrayDeque<Integer> deque2 = new CustomArrayDeque<>();
        deque2.add(20);
        deque2.add(10);
        assertFalse(deque1.equals(deque2));
    }

    @Test
    public void whenEquals_withNull_returnsFalse() {
        CustomArrayDeque<Integer> customArrayDeque = new CustomArrayDeque<>();
        customArrayDeque.add(10);
        assertFalse(customArrayDeque.equals(null));
    }

    @Test
    public void whenEquals_withDifferentCollectionType_returnsFalse() {
        CustomArrayDeque<Integer> customArrayDeque = new CustomArrayDeque<>();
        customArrayDeque.add(10);
        List<Integer> list = new ArrayList<>();
        list.add(10);
        assertFalse(customArrayDeque.equals(list));
    }
}