public class ArrayDeque<T> {
    private int size;
    private T[] items;
    private int nextFirst;
    private int nextLast;
    private int capacity = 4;

    public ArrayDeque() {
        items = (T[]) new Object[capacity];
        size = 0;
        nextFirst = 0;
        nextLast = 0;
        //创建一个空数组双端队列
    }

    public void addFirst(T item) {
        if (item == null) {
            throw new NullPointerException();
        }
        nextFirst = (nextFirst - 1) & (items.length - 1);
        items[nextFirst] = item;
        size++;
        if (nextFirst == nextLast) {
            reSize(size * 2);
        }
    }
    public void addLast(T item) {
        if (item == null) {
            throw new NullPointerException();
        }
        items[nextLast] = item;
        nextLast = (nextLast + 1) & (items.length - 1);
        size++;
        if (nextLast == nextFirst) {
            reSize(size * 2);
        }

        //在双端队列的后面添加一个类型的项目
    }


    private void reSize(int x) {
        int p = nextFirst;
        int n = size;
        int r = n - p;
        T[] a = (T[]) new Object[x];
        System.arraycopy(items, p, a, 0, r);
        System.arraycopy(items, 0, a, r, p);
        items = a;
        nextFirst = 0;
        nextLast = n;

    }

    public boolean isEmpty() {
        return size == 0;
        

        //如果 deque 为空，则返回 true，否则返回 false
    }

    public int size() {
        return this.size;
        //返回双端队列中的项目数
    }

    public void printDeque() {
        for (int i = 0; i < size; i++) {
            System.out.print(items[i] + " ");
        }

        //从头到尾打印双端队列中的项目，用空格分隔。
    }

    public T removeFirst() {
        T t = findFirst();
        if (t == null) {
            return null;
        }
        return t;

        //删除并返回双端队列前面的项目。如果不存在这样的项目，则返回 null。

    }

    private T findFirst() {
        int p = nextFirst;
        T t = items[p];
        if (t == null) {
            return null;
        } else {
            items[p] = null;
            nextFirst = (nextFirst + 1) & (items.length - 1);
            return t;
        }

    }

    public T removeLast() {
        T t = findLast();
        if (t == null) {
            return null;
        } else {
            return t;
        }

        //删除并返回双端队列后面的项目。如果不存在这样的项目，则返回 null。
    }

    private  T findLast() {
        int p = (nextLast - 1) & (items.length - 1);
        T t = items[p];
        if (t == null) {
            return null;
        } else {
            items[p] = null;
            nextLast = p;
            return t;
        }
    }

    public T get(int index) {
        if (index < 0 || index >= size() || isEmpty()) {
            return null;
        }
        if (nextFirst < nextLast) {
            return items[index + nextFirst];
        }
        else if (nextFirst > nextLast) {
            if (nextFirst + index < items.length) {
                return items[nextFirst + index];
            } else {
                return items[(nextFirst + index) % items.length];
            }
        }
        return null;
        //获取给定索引处的项目
        // 其中 0 是前面，1 是下一个项目，依此类推。
        // 如果不存在这样的项目，则返回 null。不能改变双端队列！
    }

    public static void main(String[] args){
        ArrayDeque<Integer> p = new ArrayDeque<>();
        p.addLast(5);
        p.addLast(4);
        p.addLast(3);
        p.addLast(2);
        p.addFirst(1);
        p.addFirst(0);
        System.out.println(p.get(1));
        System.out.println(p.get(2));
        System.out.println(p.get(3));
        System.out.println(p.get(4));
        System.out.println(p.get(5));

    }

}
