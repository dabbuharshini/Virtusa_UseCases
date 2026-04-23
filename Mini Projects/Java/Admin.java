import java.io.*;
import java.util.*;
class Admin
{
    static final String ad_userName="admin";
    static final String ad_passWord="1234";
    public boolean checkIdentity()
    {
        Scanner sc=new Scanner(System.in);
        System.out.print("Enter username:");
        String username=sc.nextLine();
        System.out.print("Enter password:");
        String password=sc.nextLine();
        if(username.equals(ad_userName)&&password.equals(ad_passWord))
        {
            System.out.println("Login successful");
            return true;
        }
        else
        {
            System.out.println("Invalid user");
            return false;
        }
    }
    public void addQuestion()
    {
        if(!checkIdentity())
        {
            System.out.println("Access Denied");
            return;
        }
        Scanner sc=new Scanner(System.in);
        try
        {
            FileWriter fw=new FileWriter("questions.txt",true);
            System.out.print("Enter Question:");
            String question=sc.nextLine();
            String options[]=new String[4];
            for(int i=0;i<4;i++)
            {
                System.out.print("Option "+(i+1)+":");
                options[i]=sc.nextLine();
            }
            int ans;
            while(true)
            {
                System.out.print("Enter Correct Option:");
                ans=sc.nextInt();
                if(ans>=1&&ans<=4)
                {
                    break;
                }
                else
                {
                    System.out.println("Invalid selection of answer");
                }
            }
            String line=question+"|"+String.join(",",options)+"|"+ans+"\n";
            fw.write(line);
            fw.close();
            System.out.println("Successfully..!!! Question added to file..");
        }
        catch(Exception e)
        {
            System.out.println("Error:"+e.getMessage());
        }
    }
}