import java.util.Arrays;
import java.util.Collection;
import java.util.Deque;
import java.util.HashSet;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Set;

import static java.util.Objects.requireNonNull;

/**
 * Resizable-array implementation of the {@link Deque} interface.
 * <p>
 * This class manages an internal array backed by explicit left and right pointers
 * to support double-ended queue operations without capacity restrictions other than
 * memory limitations. It does not permit {@code null} elements.
 * <p>
 * Unlike standard implementations, this class manages dynamic resizing and array shifting
 * internally. It supports efficient insertion, extraction, and traversal at both ends
 * of the deque, alongside standard collection and bulk querying views.
 * <p>
 * <b>Note:</b> This implementation is not synchronized.
 *
 * @param <E> the type of elements held in this deque
 * @author Benjamin Kane
 * @see <a href="https://www.linkedin.com/in/benjamin-kane-81149482/">LinkedIn</a>
 * @see <a href="https://github.com/bk10aao">GitHub account bk10aao</a>
 * @see <a href="https://github.com/bk10aao/CustomArrayDeque">Repository</a>
 */
public class CustomArrayDeque<E> implements Deque<E> {

    private E[] deque;
    private int size;
    private int leftPointer;
    private int rightPointer;

    /**
     * Constructs an empty deque with an initial capacity of 16 elements.
     */
    public CustomArrayDeque() {
        deque = (E[]) new Object[16];
        size = 0;
        leftPointer = rightPointer = 8;
    }

    /**
     * Constructs an empty deque with an initial capacity sufficient to hold
     * the specified number of elements without immediate resizing.
     *
     * @param numElements the initial capacity hint
     * @throws IllegalArgumentException if {@code numElements < 0}
     */
    public CustomArrayDeque(final int numElements) {
        if (numElements < 0)
            throw new IllegalArgumentException();
        int capacity = getNewSize(numElements);
        deque = (E[]) new Object[capacity];
        leftPointer = rightPointer = capacity / 2;
    }

    /**
     * Constructs a deque containing the elements of the specified collection,
     * in the order they are returned by the collection's iterator.
     *
     * @param c the collection whose elements are to be placed into this deque
     * @throws NullPointerException if the specified collection or any of its elements is null
     */
    public CustomArrayDeque(final Collection<? extends E> c) {
        if(c == null)
            throw new NullPointerException();
        int capacity = getNewSize(c.size());
        deque = (E[]) new Object[capacity];
        leftPointer = rightPointer = capacity / 2;
        addAll(c);
    }

    /**
     * Inserts the specified element at the tail of this deque.
     *
     * @param item the element to add
     * @return {@code true} (as specified by {@link Collection#add})
     * @throws NullPointerException if the specified element is null
     */
    public boolean add(final E item) {
        if (item == null)
            throw new NullPointerException();
        if (size >= deque.length) {
            expand(size + 1);
        } else if (rightPointer >= deque.length)
            recenter();
        deque[rightPointer++] = item;
        size++;
        return true;
    }

    /**
     * Adds all of the elements in the specified collection to the tail of this deque,
     * in the order that they are returned by the specified collection's iterator.
     *
     * @param c collection containing elements to be added to this deque
     * @return {@code true} if this deque changed as a result of the call
     * @throws NullPointerException if the specified collection or any of its elements is null
     */
    public boolean addAll(final Collection<? extends E> c) {
        if (c == null)
            throw new NullPointerException();
        if (c.isEmpty())
            return false;
        if (size + c.size() > deque.length || rightPointer + c.size() > deque.length)
            expand(size + c.size());
        for (E e : c) {
            if (e == null)
                throw new NullPointerException();
            deque[rightPointer++] = e;
            size++;
        }
        return true;
    }

    /**
     * Inserts the specified element at the front of this deque.
     *
     * @param item the element to add
     * @throws NullPointerException if the specified element is null
     */
    public void addFirst(final E item) {
        if (item == null)
            throw new NullPointerException();
        if (size >= deque.length || leftPointer <= 0)
            expand(size + 1);
        deque[--leftPointer] = item;
        size++;
    }

    /**
     * Inserts the specified element at the tail of this deque.
     *
     * @param item the element to add
     * @throws NullPointerException if the specified element is null
     */
    public void addLast(final E item) {
        add(item);
    }

    /**
     * Removes all of the elements from this deque. The deque will be empty after this call returns.
     */
    public void clear() {
        reset();
    }

    /**
     * Returns {@code true} if this deque contains the specified element.
     *
     * @param o element whose presence in this deque is to be tested
     * @return {@code true} if this deque contains the specified element
     */
    public boolean contains(final Object o) {
        if(size == 0)
            return false;
        for(int i = leftPointer; i < rightPointer; i++)
            if (deque[i] != null && deque[i].equals(o)) return true;
        return false;
    }

    /**
     * Returns {@code true} if this deque contains all of the elements in the specified collection.
     *
     * @param c collection to be checked for containment in this deque
     * @return {@code true} if this deque contains all of the elements in the specified collection
     * @throws NullPointerException if the specified collection is null
     */
    public boolean containsAll(final Collection<?> c) {
        requireNonNull(c);
        Set<?> set = (c instanceof Set<?>) ? (Set<?>) c : new HashSet<>(c);
        for(int i = leftPointer; i < rightPointer; i++)
            if (deque[i] != null && set.remove(deque[i]))
                if (set.isEmpty())
                    return true;
        return set.isEmpty();
    }

    /**
     * Retrieves, but does not remove, the first element of this deque.
     *
     * @return the head of this deque
     * @throws NoSuchElementException if this deque is empty
     */
    public E element() {
        return getFirst();
    }

    /**
     * Compares the specified object with this deque for equality.
     * Returns {@code true} if the specified object is also a collection, the two collections
     * have the same size, and all corresponding pairs of elements are equal.
     *
     * @param o object to be compared for equality with this deque
     * @return {@code true} if the specified object is equal to this deque
     */
    @Override
    public boolean equals(final Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Collection<?> c))
            return false;
        if (c.size() != size)
            return false;
        if (o instanceof CustomArrayDeque<?> other)
            return Arrays.equals(deque, leftPointer, rightPointer,
                other.deque, other.leftPointer, other.rightPointer
        );
        Iterator<?> it = c.iterator();
        for (int i = leftPointer; i < rightPointer; i++)
            if (!Objects.equals(deque[i], it.next()))
                return false;
        return true;
    }

    /**
     * Retrieves, but does not remove, the first element of this deque.
     *
     * @return the head of this deque
     * @throws NoSuchElementException if this deque is empty
     */
    public E getFirst() {
        if(size == 0)
            throw new NoSuchElementException();
        return deque[leftPointer];
    }

    /**
     * Retrieves, but does not remove, the last element of this deque.
     *
     * @return the tail of this deque
     * @throws NoSuchElementException if this deque is empty
     */
    public E getLast() {
        if(size == 0)
            throw new NoSuchElementException();
        return deque[rightPointer - 1];
    }

    /**
     * Returns {@code true} if this deque contains no elements.
     *
     * @return {@code true} if this deque contains no elements
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Inserts the specified element at the tail of this deque.
     *
     * @param item the element to add
     * @return {@code true} if the element was added to this deque
     * @throws NullPointerException if the specified element is null
     */
    public boolean offer(final E item) {
        return add(item);
    }

    /**
     * Inserts the specified element at the front of this deque.
     *
     * @param item the element to add
     * @return {@code true} if the element was added to this deque
     * @throws NullPointerException if the specified element is null
     */
    public boolean offerFirst(final E item) {
        if(item == null)
            throw new NullPointerException();
        if (leftPointer <= 0)
            expand(size + 1);
        deque[--leftPointer] = item;
        size++;
        return true;
    }

    /**
     * Inserts the specified element at the tail of this deque.
     *
     * @param item the element to add
     * @return {@code true} if the element was added to this deque
     * @throws NullPointerException if the specified element is null
     */
    public boolean offerLast(final E item) {
        if(item == null)
            throw new NullPointerException();
        if(rightPointer >= deque.length)
            expand(size + 1);
        deque[rightPointer++] = item;
        size++;
        return true;
    }

    /**
     * Retrieves, but does not remove, the first element of this deque,
     * or returns {@code null} if this deque is empty.
     *
     * @return the head of this deque, or {@code null} if this deque is empty
     */
    public E peek() {
        if(size == 0)
            return null;
        return getFirst();
    }

    /**
     * Retrieves, but does not remove, the first element of this deque,
     * or returns {@code null} if this deque is empty.
     *
     * @return the head of this deque, or {@code null} if this deque is empty
     */
    public E peekFirst() {
        if(size == 0)
            return null;
        return getFirst();
    }

    /**
     * Retrieves, but does not remove, the last element of this deque,
     * or returns {@code null} if this deque is empty.
     *
     * @return the tail of this deque, or {@code null} if this deque is empty
     */
    public E peekLast() {
        if(size == 0)
            return null;
        return getLast();
    }

    /**
     * Retrieves and removes the first element of this deque,
     * or returns {@code null} if this deque is empty.
     *
     * @return the head of this deque, or {@code null} if this deque is empty
     */
    public E poll() {
        if(size == 0)
            return null;
        return remove();
    }

    /**
     * Retrieves and removes the first element of this deque,
     * or returns {@code null} if this deque is empty.
     *
     * @return the head of this deque, or {@code null} if this deque is empty
     */
    public E pollFirst() {
        if(size == 0)
            return null;
        return removeFirst();
    }

    /**
     * Retrieves and removes the last element of this deque,
     * or returns {@code null} if this deque is empty.
     *
     * @return the tail of this deque, or {@code null} if this deque is empty
     */
    public E pollLast() {
        if(size == 0)
            return null;
        return removeLast();
    }

    /**
     * Pop an element from the stack represented by this deque.
     * Equivalent to {@link #removeFirst()}.
     *
     * @return the element at the front of this deque
     * @throws NoSuchElementException if this deque is empty
     */
    public E pop() {
        return removeFirst();
    }

    /**
     * Pushes an element onto the stack represented by this deque.
     * Equivalent to {@link #addFirst(Object)}.
     *
     * @param item the element to push
     * @throws NullPointerException if the specified element is null
     */
    public void push(E item) {
        addFirst(item);
    }

    /**
     * Retrieves and removes the first element of this deque.
     *
     * @return the head of this deque
     * @throws NoSuchElementException if this deque is empty
     */
    public E remove() {
        return removeFirst();
    }

    /**
     * Removes the first occurrence of the specified element from this deque.
     *
     * @param o element to be removed from this deque, if present
     * @return {@code true} if the element was removed
     */
    public boolean remove(Object o) {
        return removeFirstOccurrence(o);
    }

    /**
     * Retrieves and removes the first element of this deque.
     *
     * @return the head of this deque
     * @throws NoSuchElementException if this deque is empty
     */
    public E removeFirst() {
        if(size == 0)
            throw new NoSuchElementException();
        E item = deque[leftPointer];
        deque[leftPointer++] = null;
        if(--size == 0)
            reset();
        return item;
    }

    /**
     * Retrieves and removes the last element of this deque.
     *
     * @return the tail of this deque
     * @throws NoSuchElementException if this deque is empty
     */
    public E removeLast() {
        if(size == 0)
            throw new NoSuchElementException();
        E item = deque[--rightPointer];
        deque[rightPointer] = null;
        if(--size == 0)
            reset();
        return item;
    }

    /**
     * Removes the first occurrence of the specified element from this deque.
     *
     * @param o element to be removed from this deque, if present
     * @return {@code true} if the element was found and removed
     */
    public boolean removeFirstOccurrence(final Object o) {
        if (size == 0)
            return false;
        for (int i = leftPointer; i < rightPointer; i++)
            if (deque[i] != null && deque[i].equals(o))
                return removeInnerElement(i);
        return false;
    }

    /**
     * Removes the last occurrence of the specified element from this deque.
     *
     * @param o element to be removed from this deque, if present
     * @return {@code true} if the element was found and removed
     */
    public boolean removeLastOccurrence(final Object o) {
        if (size == 0)
            return false;
        for (int i = rightPointer - 1; i >= leftPointer; i--)
            if (deque[i] != null && deque[i].equals(o))
                return removeInnerElement(i);
        return false;
    }

    /**
     * Removes all of this deque's elements that are also contained in the specified collection.
     *
     * @param c collection containing elements to be removed from this deque
     * @return {@code true} if this deque changed as a result of the call
     * @throws NullPointerException if the specified collection is null
     */
    public boolean removeAll(final Collection<?> c) {
        requireNonNull(c);
        Set<?> set = (c instanceof Set<?>) ? (Set<?>) c : new HashSet<>(c);
        boolean modified = false;
        for(int i = leftPointer; i < rightPointer; i++)
            if (deque[i] != null && set.contains(deque[i])) {
                deque[i] = null;
                size--;
                modified = true;
            }
        if(modified)
            shiftElements();
        return modified;
    }

    /**
     * Retains only the elements in this deque that are contained in the specified collection.
     *
     * @param c collection containing elements to be retained in this deque
     * @return {@code true} if this deque changed as a result of the call
     * @throws NullPointerException if the specified collection is null
     */
    public boolean retainAll(final Collection<?> c) {
        requireNonNull(c);
        Set<?> set = (c instanceof Set<?>) ? (Set<?>) c : new HashSet<>(c);
        boolean modified = false;
        for(int i = leftPointer; i < rightPointer; i++)
            if (deque[i] != null && !set.contains(deque[i])) {
                deque[i] = null;
                size--;
                modified = true;
            }
        if(modified)
            shiftElements();
        return modified;
    }

    /**
     * Returns the number of elements in this deque.
     *
     * @return the number of elements in this deque
     */
    public int size() {
        return size;
    }

    /**
     * Returns an array containing all of the elements in this deque in proper sequence.
     *
     * @return an array containing all of the elements in this deque
     */
    public Object[] toArray() {
        return Arrays.copyOfRange(deque, leftPointer, rightPointer);
    }

    /**
     * Returns an array containing all of the elements in this deque in proper sequence;
     * the runtime type of the returned array is that of the specified array.
     *
     * @param a the array into which the elements of the deque are to be stored, if it is big enough;
     *          otherwise, a new array of the same runtime type is allocated for this purpose
     * @param <T> the runtime type of the array to contain the collection
     * @return an array containing the elements of the deque
     * @throws NullPointerException if the specified array is null
     */
    public <T> T[] toArray(final T[] a) {
        Objects.requireNonNull(a);
        if(a.length < size)
            return (T[]) Arrays.copyOfRange(deque, leftPointer, rightPointer, a.getClass());
        System.arraycopy(deque, leftPointer, a, 0, size);
        if(a.length > size)
            a[size] = null;
        return a;
    }

    /**
     * Returns a string representation of this deque.
     *
     * @return a string representation of this deque
     */
    @Override
    public String toString() {
        if(size == 0)
            return "[]";
        StringBuilder stringBuilder = new StringBuilder("[");
        for(int i = leftPointer; i < rightPointer; i++) {
            stringBuilder.append(deque[i]);
            if(i < rightPointer - 1)
                stringBuilder.append(", ");
        }
        return stringBuilder.append("]").toString();
    }

    /**
     * Returns an iterator over the elements in this deque in proper sequence.
     *
     * @return an iterator over the elements in this deque
     */
    @Override
    public Iterator<E> iterator() {
        return new Iterator<>() {
            private final Object[] snapshot = toArray();
            private int cursor = 0;
            private boolean canRemove = false;

            @Override
            public boolean hasNext() {
                return cursor < snapshot.length;
            }

            @Override
            public E next() {
                if (!hasNext())
                    throw new NoSuchElementException();
                canRemove = true;
                return (E) snapshot[cursor++];
            }

            @Override
            public void remove() {
                if (!canRemove)
                    throw new IllegalStateException();
                CustomArrayDeque.this.remove(snapshot[cursor - 1]);
                canRemove = false;
            }
        };
    }

    /**
     * Returns an iterator over the elements in this deque in reverse sequential order.
     *
     * @return an iterator over the elements in this deque in reverse sequence
     */
    @Override
    public Iterator<E> descendingIterator() {
        return new Iterator<>() {
            private final Object[] snapshot = toArray();
            private int cursor = snapshot.length - 1;
            private boolean canRemove = false;

            @Override
            public boolean hasNext() {
                return cursor >= 0;
            }

            @Override
            public E next() {
                if (!hasNext())
                    throw new NoSuchElementException();
                canRemove = true;
                return (E) snapshot[cursor--];
            }

            @Override
            public void remove() {
                if (!canRemove)
                    throw new IllegalStateException();
                CustomArrayDeque.this.remove(snapshot[cursor + 1]);
                canRemove = false;
            }
        };
    }

    private void expand(final int minimumCapacity) {
        int newSize = Math.max(deque.length * 2, minimumCapacity);
        while (newSize < minimumCapacity)
            newSize <<= 1;
        E[] newArray = (E[]) new Object[newSize];
        int currentSize = this.size;
        int newLeft = (newSize - minimumCapacity) / 2;
        System.arraycopy(deque, this.leftPointer, newArray, newLeft, currentSize);
        deque = newArray;
        this.leftPointer = newLeft;
        this.rightPointer = newLeft + currentSize;
    }

    private static int getNewSize(final int minimumCapacity) {
        int newSize = 16;
        while(newSize < minimumCapacity)
            if ((newSize <<= 1) <= 0)
                throw new IllegalStateException();
        return newSize;
    }

    private void reset() {
        deque = (E[])new Object[16];
        leftPointer = rightPointer = 8;
        size = 0;
    }

    private void shiftElements() {
        int writeIndex = leftPointer;
        for(int readIndex = leftPointer; readIndex < rightPointer; readIndex++)
            if (deque[readIndex] != null) {
                if (readIndex != writeIndex) {
                    deque[writeIndex] = deque[readIndex];
                    deque[readIndex] = null;
                }
                writeIndex++;
            }
        rightPointer = writeIndex;
    }

    private void recenter() {
        int newLeft = (deque.length - size) / 2;
        System.arraycopy(deque, leftPointer, deque, newLeft, size);
        if (newLeft > leftPointer)
            Arrays.fill(deque, leftPointer, newLeft, null);
        else if (newLeft < leftPointer)
            Arrays.fill(deque, newLeft + size, rightPointer, null);
        leftPointer = newLeft;
        rightPointer = leftPointer + size;
    }

    private boolean removeInnerElement(int i) {
        deque[i] = null;
        int leftDistance = i - leftPointer;
        int rightDistance = (rightPointer - 1) - i;
        if (leftDistance < rightDistance) {
            System.arraycopy(deque, leftPointer, deque, leftPointer + 1, leftDistance);
            deque[leftPointer] = null;
            leftPointer++;
        } else {
            System.arraycopy(deque, i + 1, deque, i, rightDistance);
            deque[rightPointer - 1] = null;
            rightPointer--;
        }
        size--;
        return true;
    }
}
