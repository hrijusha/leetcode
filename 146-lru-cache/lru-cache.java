class LRUCache {
    //Define the Doubly Linked List Node
    class Node {
        int key;
        int value;
        Node prev;
        Node next;

        Node(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }

    private int capacity;
    private Map<Integer, Node> cache;
    private Node head;
    private Node tail;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.cache = new HashMap<>();

        this.head = new Node(-1, -1);
        this.tail = new Node(-1, -1);
        head.next = tail;
        tail.prev = head;
    }

    //remove an existing node from the linked list
    private void remove(Node node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    //insert a node right after the dummy head (marks it as Most Recently Used)
    private void insert(Node node) {
        node.next = head.next;
        node.next.prev = node;
        head.next = node;
        node.prev = head;
    }

    public int get(int key) {
        //check the key in hashmap
        if (cache.containsKey(key)) {
            Node node = cache.get(key);
            remove(node);
            insert(node);
            return node.value;
        }
        return -1;
    }

    public void put(int key, int value) {
        if (cache.containsKey(key)) {
            //update existing node and move to front
            Node node = cache.get(key);
            node.value = value;
            remove(node);
            insert(node);
        } else {
            //create new node
            Node newNode = new Node(key, value);
            cache.put(key, newNode);
            insert(newNode);

            //check capacity
            if (cache.size() > capacity) {
                //The LRU node is the one right before the dummy tail
                Node lru = tail.prev;
                remove(lru);
                cache.remove(lru.key); // Also remove from HashMap
            }
        }

    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */