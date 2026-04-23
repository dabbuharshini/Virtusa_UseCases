import java.util.*;
interface Billable{
    double calculateTotal(int units);
}
class Bill implements Billable{
    double tax;
    @Override
    public double calculateTotal(int units) {
        double amount=0;
        if(units<=100)
        {
            amount=units*1.0;
        }
        else if(units<=300)
        {
            amount=(100*1.0)+(units-100)*2.0;
        }
        else
        {
            amount=(100*1.0)+(200*2.0)+(units-300)*5.0;
        }
        tax=amount*0.1;
        return tax+amount;
    }
    public double getTax()
    {
        return tax;
    }
}
public class SmartPay
{
    public static void main(String args[])
    {
        Scanner sc=new Scanner(System.in);
        Bill obj=new Bill();
        Map<Integer,Integer> consumers=new HashMap<>();
        while(true)
        {
            System.out.print("Enter name of the Consumer(or 'Exit' to quit):");
            String name=sc.nextLine();
            if(name.equalsIgnoreCase("exit"))
            {
                break;
            }
            System.out.print("Enter consumer ID:");
            int id=sc.nextInt();
            int prevunits=consumers.getOrDefault(id,0);
            System.out.print("Enter currently consumed units:");
            int currunits=sc.nextInt();
            sc.nextLine();
            if(currunits<prevunits)
            {
                System.out.println("Currently consumed units should be greater than previously consumed units.");
                continue;
            }
            int units=currunits-prevunits;
            consumers.put(id,currunits);
            double total=obj.calculateTotal(units);
            System.out.println("--------SmartPay Receipt--------");
            System.out.println("Consumer Name: "+name);
            System.out.println("Consumer ID: "+id);
            System.out.println("Previous Units: "+prevunits);
            System.out.println("Units Consumed: "+units);
            System.out.println("Tax: "+obj.getTax());
            System.out.println("Total Amount: "+total);
        }
    }
}