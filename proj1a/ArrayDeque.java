public class ArrayDeque<T> {
    private int size;
    private T[] items;
    private int nextFirst;
    private int nextLast;

    public ArrayDeque(){
        items =(T[]) new Object[8];
        size = 0;
        nextFirst = 0;
        nextLast = 0;
        //创建一个空数组双端队列
    }

    public void addFirst(T item){
        if (item == null) {
            throw new NullPointerException();
        }
        nextFirst = (nextFirst - 1) & (items.length -1);
        items[nextFirst] = item;
        size ++;
        if (nextFirst ==nextLast){
            reSize(size * 2);
        }
    }
    public void addLast(T item){
        if (item == null) {
            throw new NullPointerException();
        }
        items[nextLast] = item;
        nextLast = (nextLast + 1) & (items.length + 1);
        size ++;
        if(nextLast == nextFirst){
            reSize( size * 2);
        }

        //在双端队列的后面添加一个类型的项目
    }


    public void reSize(int x){
        int p = nextFirst;
        int n = size;
        int r = n - p;
        T[] a = (T[]) new Object[x];
        System.arraycopy(items,p,a,0,r);
        System.arraycopy(items,0,a,r,p);
        items = a;
        nextFirst = 0;
        nextLast = n;

    }

    public boolean isEmpty(){
        if (this.size == 0){
            return true;
        }
        else return false;

        //如果 deque 为空，则返回 true，否则返回 false
    }

    public int size(){
        return this.size;
        //返回双端队列中的项目数
    }

    public void printDeque(){
        for(int i = 0 ; i < size ; i++){
            System.out.print(items[i] + " ");
        }

        //从头到尾打印双端队列中的项目，用空格分隔。
    }

    public T removeFirst(){
        T t = findFirst();
        if (t == null){
            return null;
        }
        return t;

        //删除并返回双端队列前面的项目。如果不存在这样的项目，则返回 null。

    }

    public T findFirst(){
        int p = nextFirst;
        T t = items[p];
        if (t == null){
            return null;
        }
        else {
            items[p] = null;
            nextFirst = (nextFirst + 1) & (items.length - 1);
            return t;
        }

    }

    public T removeLast() {
        T t = findLast();
        if(t == null){
            return null;
        }
        else{
            return t;
        }

        //删除并返回双端队列后面的项目。如果不存在这样的项目，则返回 null。
    }

    public T findLast(){
        int p =(nextLast - 1) & (items.length - 1);
        T t = items[p];
        if (t == null){
            return null;
        }
        else{
            items[p] = null;
            nextLast = p;
            return t;
        }

    }

    public T get(int index){
        if(index < 0 || index >= items.length){
            return null;
        }
        else return items[index];

        //获取给定索引处的项目
        // 其中 0 是前面，1 是下一个项目，依此类推。
        // 如果不存在这样的项目，则返回 null。不能改变双端队列！

    }

    public static void main(String[] args){


    }

}