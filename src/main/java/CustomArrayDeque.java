import java.util.Arrays;
import java.util.Collection;
import java.util.Deque;
import java.util.HashSet;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Set;

import static java.util.Objects.requireNonNull;

public class CustomArrayDeque<E> implements Deque<E> {

    private E[] deque;
    private int size;
    private int leftPointer;
    private int rightPointer;

    public CustomArrayDeque() {
        deque = (E[])new Object[16];
        size = 0;
        leftPointer = rightPointer = 4;
    }

    public CustomArrayDeque(int numElements) {
        if (numElements < 0)
            throw new IllegalArgumentException();
        int capacity = getNewSize(numElements, 16);
        deque = (E[]) new Object[capacity];
        leftPointer = rightPointer = capacity / 4;
    }

    public CustomArrayDeque(Collection<? extends E> c) {
        if(c == null)
            throw new NullPointerException();
        int capacity = getNewSize(c.size(), 16);
        deque = (E[]) new Object[capacity];
        leftPointer = rightPointer = capacity / 4;
        addAll(c);
    }

    public boolean add(E item) {
        if(item == null)
            throw new NullPointerException();
        if(rightPointer >= deque.length)
            expand(size + 1);
        deque[rightPointer++] = item;
        size++;
        return true;
    }

    public boolean addAll(Collection<? extends E> c) {
        if(c == null)
            throw new NullPointerException();
        if(c.isEmpty())
            return false;
        if(rightPointer + c.size() > deque.length)
            expand(size + c.size());
        for(E e : c) {
            if (e == null)
                throw new NullPointerException();
            deque[rightPointer++] = e;
            size++;
        }
        return true;
    }

    public void addFirst(E item) {
        if(item == null)
            throw new NullPointerException();
        if(leftPointer <= 0)
            expand(size + 1);
        deque[--leftPointer] = item;
        size++;
    }

    public void addLast(E item) {
        add(item);
    }

    public void clear() {
        reset();
    }

    public boolean contains(Object o) {
        if(size == 0)
            return false;
        for(int i = leftPointer; i < rightPointer; i++)
            if (deque[i] != null && deque[i].equals(o)) return true;
        return false;
    }

    public boolean containsAll(Collection<?> c) {
        requireNonNull(c);
        Set<?> set = (c instanceof Set<?>) ? (Set<?>) c : new HashSet<>(c);
        for(int i = leftPointer; i < rightPointer; i++)
            if (deque[i] != null && set.remove(deque[i]))
                if (set.isEmpty())
                    return true;
        return set.isEmpty();
    }

    public E element() {
        return getFirst();
    }

    @Override
    public boolean equals(Object o) {
        if(this == o)
            return true;
        if(!(o instanceof Deque<?> other))
            return false;
        if(size != other.size())
            return false;
        Iterator<?> current = iterator();
        Iterator<?> otherIterator = other.iterator();
        while (current.hasNext())
            if(!Objects.equals(current.next(), otherIterator.next()))
                return false;
        return true;
    }

    public E getFirst() {
        if(size == 0)
            throw new NoSuchElementException();
        return deque[leftPointer];
    }

    public E getLast() {
        if(size == 0)
            throw new NoSuchElementException();
        return deque[rightPointer - 1];
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean offer(E item) {
        return add(item);
    }

    public boolean offerFirst(E item) {
        if(item == null)
            throw new NullPointerException();
        if (leftPointer <= 0)
            expand(size + 1);
        deque[--leftPointer] = item;
        size++;
        return true;
    }

    public boolean offerLast(E item) {
        if(item == null)
            throw new NullPointerException();
        if(rightPointer >= deque.length)
            expand(size + 1);
        deque[rightPointer++] = item;
        size++;
        return true;
    }

    public E peek() {
        if(size == 0)
            return null;
        return getFirst();
    }

    public E peekFirst() {
        if(size == 0)
            return null;
        return getFirst();
    }

    public E peekLast() {
        if(size == 0)
            return null;
        return getLast();
    }

    public E poll() {
        if(size == 0)
            return null;
        return remove();
    }

    public E pollFirst() {
        if(size == 0)
            return null;
        return removeFirst();
    }

    public E pollLast() {
        if(size == 0)
            return null;
        return removeLast();
    }

    public E pop() {
        return removeFirst();
    }

    public void push(E item) {
        addFirst(item);
    }

    public E remove() {
        return removeFirst();
    }

    public boolean remove(Object o) {
        return removeFirstOccurrence(o);
    }

    public E removeFirst() {
        if(size == 0)
            throw new NoSuchElementException();
        E item = deque[leftPointer];
        deque[leftPointer++] = null;
        if(--size == 0)
            reset();
        return item;
    }

    public E removeLast() {
        if(size == 0)
            throw new NoSuchElementException();
        E item = deque[--rightPointer];
        deque[rightPointer] = null;
        if(--size == 0)
            reset();
        return item;
    }

    public boolean removeFirstOccurrence(Object o) {
        if(size == 0)
            return false;
        for(int i = leftPointer; i < rightPointer; i++)
            if (deque[i] != null && deque[i].equals(o)) {
                deque[i] = null;
                for (int x = i; x > leftPointer; x--)
                    deque[x] = deque[x - 1];
                deque[leftPointer] = null;
                leftPointer++;
                size--;
                return true;
            }
        return false;
    }

    public boolean removeLastOccurrence(Object o) {
        if(size == 0)
            return false;
        for(int i = rightPointer - 1; i >= leftPointer; i--)
            if (deque[i] != null && deque[i].equals(o)) {
                deque[i] = null;
                for (int x = i; x < rightPointer - 1; x++)
                    deque[x] = deque[x + 1];
                deque[rightPointer - 1] = null;
                rightPointer--;
                size--;
                return true;
            }
        return false;
    }

    public boolean removeAll(Collection<?> c) {
        requireNonNull(c);
        Set<?> set = (c instanceof Set<?>) ? (Set<?>) c : new HashSet<>(c);
        boolean modified = false;
        for(int i = leftPointer; i < rightPointer; i++) {
            if(deque[i] != null && set.contains(deque[i])) {
                deque[i] = null;
                size--;
                modified = true;
            }
        }
        if(modified)
            shiftElements();
        return modified;
    }

    public boolean retainAll(Collection<?> c) {
        requireNonNull(c);
        Set<?> set = (c instanceof Set<?>) ? (Set<?>) c : new HashSet<>(c);
        boolean modified = false;
        for(int i = leftPointer; i < rightPointer; i++) {
            if(deque[i] != null && !set.contains(deque[i])) {
                deque[i] = null;
                size--;
                modified = true;
            }
        }
        if(modified)
            shiftElements();
        return modified;
    }

    public int size() {
        return size;
    }

    public Object[] toArray() {
        return Arrays.copyOfRange(deque, leftPointer, rightPointer);
    }

    @Override
    public <T> T[] toArray(T[] a) {
        Objects.requireNonNull(a);
        if(a.length < size)
            return (T[]) Arrays.copyOfRange(deque, leftPointer, rightPointer, a.getClass());
        System.arraycopy(deque, leftPointer, a, 0, size);
        if(a.length > size)
            a[size] = null;
        return a;
    }

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

    public int getArraySize() {
        return deque.length;
    }

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

    private void expand(int minimumCapacity) {
        int oldCapacity = deque.length;
        int newSize = getNewSize(minimumCapacity, oldCapacity * 2);
        E[] newArray = (E[]) new Object[newSize];
        int currentSize = this.size;
        int newLeft = newSize / 4;
        for(int i = 0; i < currentSize; i++)
            newArray[newLeft + i] = deque[(this.leftPointer + i) % oldCapacity];
        deque = newArray;
        this.leftPointer = newLeft;
        this.rightPointer = newLeft + currentSize;
    }

    private static int getNewSize(int minimumCapacity, int newSize) {
        while(newSize < minimumCapacity) {
            newSize <<= 1;
            if (newSize <= 0)
                throw new IllegalStateException();
        }
        return newSize;
    }

    private void reset() {
        deque = (E[])new Object[16];
        leftPointer = rightPointer = 4;
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
}
