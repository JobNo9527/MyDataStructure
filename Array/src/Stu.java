import java.util.ArrayList;

/**
 * @author 将晖
 */


public class Stu implements Comparable<Stu> {

    private String name;
    private int score;


    public Stu(String name, int score) {
        this.name = name;
        this.score = score;
    }

    @Override
    public boolean equals(Object stu) {

        //当前这个类的对象的地址是否相等于这个stu类的地址，实际上我们在比较同一个对象
        if (this == stu)
            return true;

        //判断传来的stu对象是否是空对象
        if (stu == null)
            return false;

        //判断强转是否成立：当前这个类是否等于传来stu的所对应的类，如果不是，说明不能进行强转
        if (this.getClass() != stu.getClass())
            return false;

        Stu another = (Stu) stu;
        //忽略大小写
        return this.name.toLowerCase().equals(another.name.toLowerCase());
    }

    @Override
    public int compareTo(Stu another) {

        ////传进来的打过本来的
        //if (this.score < another.score) {
        //    return -1;
        //}
        ////传进来的跟本来的实力相等
        //else if (this.score == another.score) {
        //    return 1;
        //}
        ////传过来的打不过本来的
        //return 1;

        //从小到大
        //return this.score - another.score;

        //从大到小
        return another.score-this.score;

    }

    @Override
    public String toString() {
        return "Stu{" +
                "name='" + name + '\'' +
                ", score=" + score +
                '}';
    }

    public static void main(String[] args) {

        Array<Stu> arr = new Array<>(); //无参构造时，默认是capacity = 10
        arr.addLast(new Stu("Alice",100));
        arr.addLast(new Stu("Bob",66));
        arr.addLast(new Stu("Charlie",88));
        System.out.println(arr);
        
    }
}
