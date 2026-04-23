import java.util.*;
public class Main
{
    public static void main(String args[])
    {
        Scanner sc=new Scanner(System.in);
        QuizService quiz=new QuizService();
        Admin admin=new Admin();
        while(true)
        {
            System.out.println("\n----Quiz System----");
            System.out.println("1.Admin");
            System.out.println("2.User");
            System.out.println("3.Exit");
            System.out.print("Enter Choice:");
            int choice=sc.nextInt();
            sc.nextLine();
            switch(choice)
            {
                case 1:
                    admin.addQuestion();
                    break;
                case 2:
                    quiz.loadQues("questions.txt");
                    quiz.startQuiz();
                    break;
                case 3:
                    System.out.println("Thank You..!!");
                    return;
                default:
                    System.out.println("Invalid choice!!");
            }
        }
    }
}