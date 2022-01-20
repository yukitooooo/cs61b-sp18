
public class LinkedListDeque<T> {

    private class StuffNode {
        private T item;
        private StuffNode prev;
        private StuffNode next;

        private StuffNode(StuffNode prev, T item, StuffNode next) {
            this.item = item;
            this.prev = prev;
            this.next = next;
        }

    }

    private StuffNode sentinel;
    private int size;


    public void addFirst(T item) {
        //在双端队列的前面添加一个类型的项目
        //操作不得涉及任何循环或递归
        StuffNode p = new StuffNode(null, item, null);
        if (!isEmpty()) {

            p.prev = sentinel.prev;
            sentinel.prev.next = p;
            p.next = sentinel;
            sentinel.prev = p;
            sentinel = p;
            size++;
        } else {
            sentinel = p;
            p.prev = p;
            p.next = p;
            size++;

        }
    }

    public void addLast(T item) {
        //T在双端队列的后面添加一个类型的项目
        StuffNode p = new StuffNode(null, item, null);
        if (!isEmpty()) {
            sentinel.prev.next = p;
            p.prev = sentinel.prev;
            p.next = sentinel;
            sentinel.prev = p;
            size++;
        } else {
            sentinel = p;
            p.prev = p;
            p.next = p;
            size++;
        }

    }

    public boolean isEmpty() {
        //如果 deque 为空，则返回 true，否则返回 false
        if (!size()) {
            return true;
        } 
        return false;
    }

    public int size() {
        //返回双端队列中的项目数
        //size 必须花费恒定的时间。
        return this.size;
    }

    public void printDeque() {
        StuffNode L = sentinel;
        int theSize = size();
        if (theSize == 0) {
            System.out.print("This is an empty LinkList!");
        } else {
            for (int i = 0; i < theSize; i++) {
                System.out.print(L.item + " ");
                L = L.next;
            }
            System.out.print("\n");
        }

        //从头到尾打印双端队列中的项目，以空格分隔。
    }


    public T removeFirst() {
        if (this.size == 0 ||  this.size == 1) {
            sentinel = null;
            this.size = 0;
            return null;
        } else {
            sentinel.next.prev = sentinel.prev;
            sentinel.prev.next = sentinel.next;
            sentinel = sentinel.next;
            this.size--;
            return sentinel.item;

        }
        //删除并返回双端队列前面的项目。如果不存在这样的项目，则返回 null。
    }

    public T removeLast() {
        if (this.size == 0 ||  this.size == 1) {
            this.sentinel = null;
            this.size = 0;
            return null;
        } else if (size == 2) {
            this.size--;
            sentinel.prev = null;
            sentinel.next = null;
            return sentinel.item;
        } else {
            sentinel.prev = sentinel.prev.prev;
            sentinel.prev.next = sentinel;
            this.size--;
            return sentinel.prev.item;
        }
        //删除并返回双端队列后面的项目。如果不存在这样的项目，则返回 null

    }

    public T get(int index) {
        StuffNode L = this.sentinel;
        if (index > this.size || index < 0) {
            return null;
        } else {
            for (int i = 0; i <= this.size; i++) {
                L = L.next;
            }
            return L.item;
        }

        /*获取给定索引处的项目,其中 0 是前面，1 是下一个项目，依此类推。
        如果不存在这样的项目，则返回 null。不得更改双端队列！
        get 必须使用迭代，而不是递归。 */
    }

    public LinkedListDeque() {
        StuffNode p = new StuffNode(null, null, null);
        this.sentinel = p;
        p.prev = p;
        p.next = p;
        this.size = 1;
        //创建一个空的链表双端队列。

    }

/*    public LinkedListDeque(T item) {

        StuffNode p = new StuffNode(null, item, null);
        this.sentinel = p;
        p.prev = p;
        p.next = p;
        this.size = 1;

    }

    */

    public T getRecursive(int index) {
        return findIndex(index, this.sentinel);

    }

    private T findIndex(int index, StuffNode node) {
        if (index > this.size || index < 0) {
            return null;
        }
        if (index == 0) {
            return node.item;
        } else {
            T t = findIndex(index - 1,node.next);
            return t;
        }

    }

}
