package template_mode;

import java.util.*;

/*
使用模板方法实现对不同类对象的排序。 抽象类BubbleSort 中模板方法 sort()实现了冒泡排序框架。
现有从BubbleSort 继承下来子类StudentBubbleSort和IntBubbleSort，
StudentBubbleSort类完成对学生列表的排序，而IntBubbleSort类完成对整数序列的排序，
请补充实现这两个类的类定义体代码部分：
现需要对Studnet类的列表按身高height从矮到高排序并输出学生信息，学生类定义如下：
*/
abstract class BubbleSort {
    public final void sort() {
        for (int i = 0; i < getLength() - 1; i++) {
            for (int j = 0; j < getLength() - i - 1; j++) {
                if (compare(j + 1, j)) {
                    swap(j + 1, j);
                }
            }
        }
    }
    public abstract int getLength();
    public abstract boolean compare(int i, int j);
    public abstract void swap(int i, int j);
}
class Student {
    private String name;
    private int height;
    private int age;
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getHeight() {
        return height;
    }
    public void setHeight(int height) {
        this.height = height;
    }
    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }
    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", height=" + height +
                ", age=" + age +
                '}';
    }
    public Student(String name, int height, int age) {
        this.name = name;
        this.height = height;
        this.age = age;
    }
}

class StudentBubbleSort extends BubbleSort{
    private List<Student> list;

    StudentBubbleSort(List<Student> list){
        this.list = list;
    }
    @Override
    public int getLength() {
        return list.size();
    }

    @Override
    public boolean compare(int i, int j) {
        if (list.get(i).getHeight()<list.get(j).getHeight()) return true;
        return false;
    }

    @Override
    public void swap(int i, int j) {
        Student student = list.get(i);
        list.remove(i);
        list.add(i, list.get(j));
        list.remove(j);
        list.add(j,student);
    }   

}
class IntBubbleSort extends BubbleSort{
    private int[] arr;
    IntBubbleSort(int[] arr){
        this.arr = arr;
    }
    @Override
    public int getLength() {
        return arr.length;
    }

    @Override
    public boolean compare(int i, int j) {
        if (arr[i]<arr[j]) return true;
        return false;
    }

    @Override
    public void swap(int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

}

public class Main {
    public static void main(String[] args) {
        List<Student> studentList = new ArrayList<Student>();
        studentList.add(new Student("ZhangShan", 170, 20));
        studentList.add(new Student("LiSi", 165, 20));
        studentList.add(new Student("WangWu", 168, 20));
        StudentBubbleSort bs = new StudentBubbleSort(studentList);
        bs.sort();
        for (Student s : studentList) {
            System.out.println(s.toString());
        }
        int[] arr = new int[]{3, 6, 234, 2, 3, 86, 9};
        IntBubbleSort intSorter = new IntBubbleSort(arr);
        intSorter.sort();
        for (int i : arr) {
            System.out.println(i);
        }
    }
}
/*
Student{name='LiSi', height=165, age=20}
Student{name='WangWu', height=168, age=20}
Student{name='ZhangShan', height=170, age=20}
2
3
3
6
9
86
234 */