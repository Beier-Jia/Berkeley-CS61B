public class ArrayDeque<Item> {
    private Item[] items;
    private int size;
    private int nextFirst;
    private int nextLast;

    /** Creates an empty array deque. */
    public ArrayDeque() {
        items = (Item[]) new Object[8];
        size = 0;
        nextFirst = 0;
        nextLast = 1;
    }

    /** Returns true if deque is full, false otherwise. */
    private boolean isFull() {
        return size == items.length;
    }

    /** Returns true if deque is sparse, false otherwise. */
    private boolean isSparse() {
        return items.length >= 16 && size < (items.length / 4);
    }

    /** Computes the index immediately “after” a given index. */
    private int plusOne(int index) {
        return (index + 1) % items.length;
    }

    /** Computes the index immediately “before” a given index. */
    private int minusOne(int index) {
        return (index - 1 + items.length) % items.length;
    }

    /** Resizes the deque. */
    private void resize(int capacity) {
        Item[] newDeque = (Item[]) new Object[capacity];
        int oldIndex = plusOne(nextFirst);
        for (int newIndex = 0; newIndex < size; newIndex++) {
            newDeque[newIndex] = items[oldIndex];
            oldIndex = plusOne(oldIndex);
        }
        items = newDeque;
        nextFirst = capacity - 1;
        nextLast = size;
    }

    /** Adds x to the front of the deque. */
    public void addFirst(Item x) {
        if (isFull()) {
            resize(items.length * 2);
        }
            items[nextFirst] = x;
            nextFirst = minusOne(nextFirst);
            size += 1;
    }

    /**  Adds x to the back of the deque. */
    public void addLast(Item x) {
        if (isFull()) {
            resize(items.length * 2);
        }
            items[nextLast] = x;
            nextLast = plusOne(nextLast);
            size += 1;
    }

    /** Returns true if deque is empty, false otherwise. */
    public boolean isEmpty() {
        return size == 0;
    }

    /** Returns the number of items in the deque. */
    public int size() {
        return size;
    }

    /** Prints the items in the deque from first to last, separated by a space.
     * Once all the items have been printed, print out a new line. */
    public void printDeque() {
        int index = plusOne(nextFirst);
        for (int i = 0; i < size; i++) {
            System.out.print(items[index] + " ");
            index = plusOne(index);
        }
        System.out.println();
    }

    /** Removes and returns the item at the front of the deque.
     * If no such item exists, returns null. */
    public Item removeFirst() {
        if (size == 0) {
            return null;
        } else {
            if (isSparse()) {
                resize(items.length / 2);
            }
            nextFirst = plusOne(nextFirst);
            Item toRemove = items[nextFirst];
            items[nextFirst] = null;
            size -= 1;
            return toRemove;
        }
    }

    /** Removes and returns the item at the back of the deque.
     * If no such item exists, returns null. */
    public Item removeLast() {
        if (size == 0) {
            return null;
        } else {
            if (isSparse()) {
                resize(items.length / 2);
            }
            nextLast = minusOne(nextLast);
            Item toRemove = items[nextLast];
            items[nextLast] = null;
            size -= 1;
            return toRemove;
        }
    }

    /** Gets the item at the given index. */
    public Item get(int index) {
        if (index > size) {
            return null;
        }
        int start = plusOne(nextFirst);
        return items[(start + index) % items.length];
    }

    /** Creates a deep copy of other. */
    public ArrayDeque(ArrayDeque other) {
        Item[] copy = (Item[]) new Object[other.items.length];
        size = other.size;
        nextFirst = other.nextFirst;
        nextLast = other.nextLast;
        System.arraycopy(other.items, 0, copy, 0, other.items.length);
    }
}

