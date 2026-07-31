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
        deque = (E[]) new Object[16];
        size = 0;
        leftPointer = rightPointer = 8;
    }

    public CustomArrayDeque(final int numElements) {
        if (numElements < 0)
            throw new IllegalArgumentException();
        int capacity = getNewSize(numElements);
        deque = (E[]) new Object[capacity];
        leftPointer = rightPointer = capacity / 2;
    }

    public CustomArrayDeque(final Collection<? extends E> c) {
        if(c == null)
            throw new NullPointerException();
        int capacity = getNewSize(c.size());
        deque = (E[]) new Object[capacity];
        leftPointer = rightPointer = capacity / 2;
        addAll(c);
    }

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

    public void addFirst(final E item) {
        if (item == null)
            throw new NullPointerException();
        if (size >= deque.length || leftPointer <= 0)
            expand(size + 1);
        deque[--leftPointer] = item;
        size++;
    }

    public void addLast(final E item) {
        add(item);
    }

    public void clear() {
        reset();
    }

    public boolean contains(final Object o) {
        if(size == 0)
            return false;
        for(int i = leftPointer; i < rightPointer; i++)
            if (deque[i] != null && deque[i].equals(o)) return true;
        return false;
    }

    public boolean containsAll(final Collection<?> c) {
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
    public boolean equals(final Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Collection<?> c))
            return false;
        if (c.size() != size)
            return false;

        if (o instanceof CustomArrayDeque<?> other) {
            return Arrays.equals(
                    deque, leftPointer, rightPointer,
                    other.deque, other.leftPointer, other.rightPointer
            );
        }
        Iterator<?> it = c.iterator();
        for (int i = leftPointer; i < rightPointer; i++) {
            if (!Objects.equals(deque[i], it.next()))
                return false;
        }
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

    public boolean offer(final E item) {
        return add(item);
    }

    public boolean offerFirst(final E item) {
        if(item == null)
            throw new NullPointerException();
        if (leftPointer <= 0)
            expand(size + 1);
        deque[--leftPointer] = item;
        size++;
        return true;
    }

    public boolean offerLast(final E item) {
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

    public boolean removeFirstOccurrence(final Object o) {
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

    public boolean removeLastOccurrence(final Object o) {
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

    public int size() {
        return size;
    }

    public Object[] toArray() {
        return Arrays.copyOfRange(deque, leftPointer, rightPointer);
    }

    @Override
    public <T> T[] toArray(final T[] a) {
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
        while(newSize < minimumCapacity) {
            newSize <<= 1;
            if (newSize <= 0)
                throw new IllegalStateException();
        }
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
}
