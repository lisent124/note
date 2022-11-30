package chain_of_responsibility;


/*
某软件公司为某企业开发一套在线办公自动化系统，以提升企业管理服务的质量和效率。
公司决定采用面向对象方法开发该系统，张工负责对员工请假审批业务进行了分析，具体描述如下：
1.企业员工通过请假申请页面提出请假申请后，首先由部门经理进行审核，若请假理由不合理，
部门经理拒绝请假申请，将申请退回给员工，员工对请假申请表进行修改再次提交，或放弃请假申请。
2.若请假理由合理，且请假天数小于等于3天，则部门经理直接批准请假申请，生成请假批准单，申请结束。
3.若请假天数大于3天，则在部门经理批准请假申请后，需要提交给副总经理审核批准。
副总经理若认为该申请合理，则审核通过，生成请假批准单，流程结束；
若副总经理认为申请不合理，则拒绝请假申请，将请假申请退回给员工。

 */


abstract class Manager {
    protected Manager superior;

    public abstract boolean handle(int reasons,int days);

    public  void setSuccessor(Manager superior){
        this.superior=superior;
    }

}

// reasons越多越合理
class DepartmentManager extends Manager {

    @Override
    public boolean handle(int reasons,int days) {
        if (reasons < 400){
            System.out.println("DepartmentManager refuse");
            return false;
            
        }else if (days <= 3){
            System.out.println("DepartmentManager pass");
            return true;
        }
        return this.superior.handle(reasons,days);
    }


}

class ViceManager extends Manager {

    @Override
    public boolean handle(int reasons,int days) {
        if (reasons < 800){
            System.out.println("viceManager refuse");
            return false;
        }
        System.out.println("viceManager pass");
        return true;
    }
}

class Worker{
    private String name;

    Worker(String nane){
        this.name =nane;
    }
    public boolean askForLeave(Manager manager,int reasons,int days){
        System.out.println(name+" ask for leave.");
        return manager.handle(reasons, days);
    }
}
public class Main {
    public static void main(String[] args){
        // reasons越多越合理
        Manager departmentManager = new DepartmentManager();
        Manager viceManager = new ViceManager();
        departmentManager.setSuccessor(viceManager);

        Worker john = new Worker("john");
        Worker breen = new Worker("breen");
        Worker lisent = new Worker("lisent");

        john.askForLeave(departmentManager, 900, 5);
        breen.askForLeave(departmentManager, 400, 2);
        lisent.askForLeave(departmentManager, 600, 5);
        
    }
}


