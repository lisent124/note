package strategy;

import java.util.*;

/*
SalaryManager类管理计时工资，workHourList是历次工作的时长，hourlyRate是小时费率，计算计时工资有两种策略：
SaryComputerStrategyOne策略，累计工作时长*小时费率，若累计工作时长大于160，再乘以1.2
SaryComputerStrategyTwo策略，若单项工作时长大于40，则乘以1.2 ,累计后*小时费率
请完成SaryComputerStrategyOne SaryComputerStrategyTwo 两个策略并提交
*/
class SalaryManager{
    private List<Double> workHourList=new ArrayList<Double>();
    private SaryComputerStrategy strage;
    private double hourlyRate;
    public void setHourlyRate(double hourlyRate) {
        this.hourlyRate = hourlyRate;
    }
    public void setStrage(SaryComputerStrategy strage) {
        this.strage = strage;
    }
    public void addWorkHour(double h){
        workHourList.add(h);
    }
    public double computerSalary(){
        return  strage.computerSalary(workHourList,hourlyRate);
    }
}
interface SaryComputerStrategy{
    double computerSalary(List<Double> list,double rate);
}
//完成SaryComputerStrategyOne 和 SaryComputerStrategyTwo 类并提交
class SaryComputerStrategyOne implements SaryComputerStrategy{

    @Override
    public double computerSalary(List<Double> list, double rate) {
        double sum = 0;
        for (Double worktime : list) {
            sum += worktime;
        }
        double res = sum*rate;
        if (sum > 120) res *= 1.2;
        return res;
    }
}
class SaryComputerStrategyTwo implements SaryComputerStrategy{
    @Override
    public double computerSalary(List<Double> list, double rate) {
        double sum = 0;
        for (Double worktime : list) {
            if (worktime > 40)  worktime *= 1.2; 
            sum += worktime;
        }
        double res = sum*rate;
        return res;
    }
}
public class Strategy {
    public static void main(String[] args){
        SalaryManager salaryManager=new SalaryManager();
        salaryManager.setHourlyRate(50);
        salaryManager.addWorkHour(52);
        salaryManager.addWorkHour(40.5);
        salaryManager.addWorkHour(38.5);
        salaryManager.addWorkHour(60);
        salaryManager.setStrage(new SaryComputerStrategyOne());
        System.out.println(salaryManager.computerSalary());
        salaryManager.setStrage(new SaryComputerStrategyTwo());
        System.out.println(salaryManager.computerSalary());
    }
}
