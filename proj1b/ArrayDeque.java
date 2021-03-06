public class ArrayDeque<T> implements Deque<T> {
    private int size;
    private T[] items;
    private int nextFirst;
    private int nextLast;
    private int capacity;

    public ArrayDeque() {
        capacity = 8;
        items = (T[]) new Object[capacity];
        size = 0;
        nextFirst = 0;
        nextLast = 0;
        //创建一个空数组双端队列
    }

    @Override
    public void addFirst(T item) {
        if (item == null) {
            throw new NullPointerException();
        }
        nextFirst = (nextFirst - 1) & (items.length - 1);
        items[nextFirst] = item;
        size++;
        if (nextFirst == nextLast) {
            int p = calculatesize(size);
            doubleSize(p);
        }
    }

    @Override
    public void addLast(T item) {
        if (item == null) {
            throw new NullPointerException();
        }
        items[nextLast] = item;
        nextLast = (nextLast + 1) & (items.length - 1);
        size++;
        if (nextLast == nextFirst) {
            int p = calculatesize(size);
            doubleSize(p);
        }

        //在双端队列的后面添加一个类型的项目
    }


    private void doubleSize(int x) {
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

    private void reduceSize(int x) {
        T[] a = (T[]) new Object[x];
        if (nextFirst < nextLast) {
            for (int i = nextFirst, j = 0; i < nextLast && j < size; i++, j++) {
                a[j] = items[i];
            }
        } else if (nextFirst > nextLast) {
            int j = 0;
            for (int i = nextLast; j < items.length - nextLast; i++, j++) {
                a[j] = items[i];
            }
            for (int i = 0; j < size; i++, j++) {
                a[j] = items[i];
            }
        }
        nextLast = size;
        nextFirst = 0;
        items = a;
    }


    private static int calculatesize(int num) {
        int initialCapacity = 8;
        if (num >= initialCapacity) {
            initialCapacity = num;
            initialCapacity |= (initialCapacity >>> 1);
            initialCapacity |= (initialCapacity >>> 2);
            initialCapacity |= (initialCapacity >>> 4);
            initialCapacity |= (initialCapacity >>> 8);
            initialCapacity |= (initialCapacity >>> 16);
            initialCapacity++;
            if (initialCapacity < 0) {
                initialCapacity >>>= 1;
            }
        }
        return initialCapacity;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
        //如果 deque 为空，则返回 true，否则返回 false
    }

    @Override
    public int size() {
        return this.size;
        //返回双端队列中的项目数
    }

    @Override
    public void printDeque() {
        if (nextFirst < nextLast) {
            for (int i = nextFirst; i < nextLast; i++) {
                System.out.print(items[i] + " ");
            }
        } else if (nextFirst > nextLast) {
            for (int i = nextFirst; i < items.length; i++) {
                System.out.print(items[i] + " ");
            }
            for (int j = 0; j <= nextLast - 1; j++) {
                System.out.print(items[j] + " ");
            }
        }
        //从头到尾打印双端队列中的项目，用空格分隔。
    }

    @Override
    public T removeFirst() {
        T t = findFirst();
        if (t == null) {
            return null;
        }
        size--;
        if (isLowCap()) {
            int p = calculatesize(size);
            reduceSize(p);
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
            nextFirst = (p + 1) & (items.length - 1);
            return t;
        }

    }

    public T removeLast() {
        T t = findLast();
        if (t == null) {
            return null;
        } else {
            size--;
            if (isLowCap()) {
                int p = calculatesize(size);
                reduceSize(p);
            }
            return t;
        }

        //删除并返回双端队列后面的项目。如果不存在这样的项目，则返回 null。
    }

    private T findLast() {
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

    @Override
    public T get(int index) {
        if (index < 0 || index >= size() || isEmpty()) {
            return null;
        }
        if (nextFirst < nextLast) {
            return items[index + nextFirst];
        } else if (nextFirst > nextLast) {
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

    private boolean isLowCap() {
        return size() > 8 && (size() / (double) items.length) < 0.25;

    }
}
