
public class LinkedListDeque<T> implements Deque<T> {

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

    @Override
    public void addFirst(T item) {
        //在双端队列的前面添加一个类型的项目
        //操作不得涉及任何循环或递归
        StuffNode p = new StuffNode(sentinel, item, sentinel.next);
        sentinel.next.prev = p;
        sentinel.next = p;
        size++;
    }

    @Override
    public void addLast(T item) {
        //T在双端队列的后面添加一个类型的项目
        StuffNode p = new StuffNode(sentinel.prev, item, sentinel);
        sentinel.prev.next = p;
        sentinel.prev = p;
        size++;
    }

    @Override
    public boolean isEmpty() {
        //如果 deque 为空，则返回 true，否则返回 false
        return size == 0;
    }

    @Override
    public int size() {
        //返回双端队列中的项目数
        //size 必须花费恒定的时间。
        return this.size;
    }

    @Override
    public void printDeque() {
        StuffNode L = sentinel.next;
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


    @Override
    public T removeFirst() {
        if (isEmpty()) {
            return null;
        }
        T res = sentinel.next.item;
        sentinel.next = sentinel.next.next;
        sentinel.next.prev = sentinel;
        size--;
        return res;
        
        //删除并返回双端队列前面的项目。如果不存在这样的项目，则返回 null。
    }

    @Override
    public T removeLast() {
        if (isEmpty()) {
            return null;
        }
        T res = sentinel.prev.item;
        sentinel.prev = sentinel.prev.prev;
        sentinel.prev.next = sentinel;
        size--;
        return res;
    }

    @Override
    public T get(int index) {
        StuffNode L = this.sentinel;
        if (index > this.size || index < 0) {
            return null;
        } else {
            for (int i = 0; i <= index; i++) {
                L = L.next;
            }
            return L.item;
        }

        /*获取给定索引处的项目,其中 0 是前面，1 是下一个项目，依此类推。
        如果不存在这样的项目，则返回 null。不得更改双端队列！
        get 必须使用迭代，而不是递归。 */
    }

    public LinkedListDeque() {
        StuffNode p = new StuffNode(null, (T) new Object(), null);
        this.sentinel = p;
        p.prev = p;
        p.next = p;
        this.size = 0;
        //创建一个空的链表双端队列。

    }


    public T getRecursive(int index) {
        return findIndex(index, sentinel.next);

    }

    private T findIndex(int index, StuffNode node) {
        if (index > this.size || index < 0) {
            return null;
        }
        if (index == 0) {
            return node.item;
        } else {
            T t = findIndex(index - 1, node.next);
            return t;
        }

    }

//    public static void main(String[] args) {
//        LinkedListDeque<Integer> p = new LinkedListDeque<>();
//        p.addFirst(1);
//        p.addFirst(2);
//        p.addFirst(3);
//        p.addLast(4);
//        p.addLast(5);
//        p.addLast(6);
//        p.printDeque();
//        System.out.println("when index = 5, the item is " + p.get(5));
//        System.out.println("when index = 5, the item is " + p.getRecursive(5));
//        p.removeLast();
//
//    }

}
