package HashCode;

import java.util.HashMap;
import java.util.HashSet;

public class Main {

    public static void main(String[] args) {

        int a = 42;
        System.out.println(((Integer) a).hashCode());

        int b = -42;
        System.out.println(((Integer) b).hashCode());

        double c = 3.1415926;
        System.out.println(((Double) c).hashCode());

        String d = "xxx";
        System.out.println(d.hashCode());

        Student s1 = new Student(1, 1, "xxx", "hhh");
        System.out.println(s1.hashCode());

        //HashSet<HashCode.Student> set = new HashSet<>();
        //set.add(s1);
        //System.out.println(set);
        //
        //HashMap<HashCode.Student, Integer> sID = new HashMap<>();
        //sID.put(s1, 100);
        //System.out.println(sID);

        Student s2 = new Student(1,1,"xxx","hhh");
        System.out.println(s2.hashCode());

        System.out.println(s1.equals(s2));
    }
}