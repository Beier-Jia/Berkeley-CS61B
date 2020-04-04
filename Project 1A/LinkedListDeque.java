public class LinkedListDeque<Offer> {
    private class StuffNode {
        public StuffNode prev;
        public Offer item;
        public StuffNode next;

        public StuffNode(StuffNode p, Offer i, StuffNode n) {
            prev = p;
            item = i;
            next = n;
        }
    }

    private StuffNode sentinel;
    private int size;

    /** Creates an empty linked list deque. */
    public LinkedListDeque() {
        sentinel = new StuffNode(null, null, null);
        sentinel.prev = sentinel;
        sentinel.next = sentinel;
        size = 0;
    }

    /** Adds x to the front of the deque. */
    public void addFirst(Offer x) {
        sentinel.next = new StuffNode(sentinel, x, sentinel.next);
        sentinel.next.next.prev = sentinel.next;
        size += 1;
    }

    /**  Adds x to the back of the deque. */
    public void addLast(Offer x) {
        sentinel.prev = new StuffNode(sentinel.prev, x, sentinel);
        sentinel.prev.prev.next = sentinel.prev;
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
        for (int i = 0; i < size; i++) {
            System.out.print(sentinel.next.item + " ");
            sentinel = sentinel.next;
        }
        System.out.println();
    }

    /** Removes and returns the item at the front of the deque.
     * If no such item exists, returns null. */
    public Offer removeFirst() {
        if (size == 0) {
            return null;
        } else {
            Offer x = sentinel.next.item;
            sentinel.next.next.prev = sentinel;
            sentinel.next = sentinel.next.next;
            size -= 1;
            return x;
        }
    }

    /** Removes and returns the item at the back of the deque.
     * If no such item exists, returns null. */
    public Offer removeLast() {
        if (size == 0) {
            return null;
        } else {
            Offer x = sentinel.prev.item;
            sentinel.prev.prev.next = sentinel;
            sentinel.prev = sentinel.prev.prev;
            size -= 1;
            return x;
        }
    }

    /** Gets the item at the given index. */
    public Offer get(int index) {
        StuffNode p = sentinel.next;
        if ((index == 0) || (index >= size)) {
            return null;
        } else {
            for (int i = 0; i < index; i++) {
                p = p.next;
            }
            return p.item;
        }
    }

    /** Creates a deep copy of other. */
    public LinkedListDeque(LinkedListDeque other) {
        sentinel = new StuffNode(null, null, null);
        sentinel.prev = sentinel;
        sentinel.next = sentinel;
        size = 0;

        for (int i = 0; i < other.size(); i++) {
            addLast((Offer) other.get(i));
        }
    }

    /** Gets the item at the given index using recursion. */
    private Offer getRecursive(int index, StuffNode p) {
        if (index == 0) {
            return p.item;
        }
        return getRecursive(index - 1, p.next);
    }

    public Offer getRecursive(int index) {
        if (index > size) {
            return null;
        }
        return getRecursive(index, sentinel.next);
    }
}

