package proxy;
/*
现设计一个投票管理器，用来管理学生选举班长等活动，其接口 VoteManagerInterface 中 
  addStudent(Student s) 方法用来添加学生候选人 
  vote(Student voter,Student select) 用来提交学生voter投了学生select一票， 
  int getVoteNum(Student s) 方法用来获取某个学生的得票数， 
  VoteManager 类是一个实现， 
  要求实现VoteManager的代理类，
控制对VoteManager的访问（每个学生不能多次投其他某学生的票，且学生不能投自己）。
*/
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
class Student{
    private String sno;
    public Student(String sno, String sname) {
        this.sno = sno;
    }
    public String getSno() {
        return sno;
    }
    public void setSno(String sno) {
        this.sno = sno;
    }
}
interface VoteManagerInterface{
    void addStudent(Student s);
    void vote(Student voter,Student select);
    int getVoteNum(Student s);
}
class VoteManager implements  VoteManagerInterface{
    private HashMap<String,Integer> map=new HashMap<String,Integer>();
    @Override
    public void addStudent(Student s) {
        map.put(s.getSno(),0);
    }
    @Override
    public void vote(Student voter, Student select) {
        if(map.containsKey(select.getSno())){
            Integer v=map.get(select.getSno());
            map.put(select.getSno(),v+1);
        }else{
            map.put(select.getSno(),0);
        }
    }
    @Override
    public int getVoteNum(Student s) {
        if(map.containsKey(s.getSno())){
           return map.get(s.getSno());
        }
        return 0;
    }
}
//只需要提交VoteManagerProxy 类
class  VoteManagerProxy implements VoteManagerInterface{
    VoteManager voteManager;
    private HashMap<String,List<Student>> map = new HashMap<>();

    VoteManagerProxy(VoteManager voteManager){
        this.voteManager = voteManager;
    }

    @Override
    public void addStudent(Student s) {
        voteManager.addStudent(s);
    }

    @Override
    public void vote(Student voter, Student select) {
        if (voter == select) return;
        if (!map.containsKey(voter.getSno())) {
            List<Student> list =  new ArrayList<>();
            list.add(select);
            map.put(voter.getSno(),list);
            voteManager.vote(voter, select);
            return;
        }
        if (map.get(voter.getSno()).contains(select)) return;
        voteManager.vote(voter, select);
        
    }

    @Override
    public int getVoteNum(Student s) {
        return voteManager.getVoteNum(s);
    }

}



public class Main{
    public static void main(String[] args){
        Student s1=new Student("001","张三");
        Student s2=new Student("002","李四");
        Student s3=new Student("003","王五");
        VoteManagerInterface v=new VoteManagerProxy(new VoteManager());
        v.addStudent(s1);
        v.addStudent(s2);
        v.addStudent(s3);
        v.vote(s1,s1);
        v.vote(s1,s2);
        v.vote(s1,s2);
        v.vote(s1,s3);
        v.vote(s2,s1);
        v.vote(s3,s2);
        v.vote(s3,s1);
        v.vote(s3,s3);
        System.out.println(v.getVoteNum(s1));
        System.out.println(v.getVoteNum(s2));
        System.out.println(v.getVoteNum(s3));
    }
}
