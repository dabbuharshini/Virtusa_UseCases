import java.util.*;
import java.io.*;
class QuizService
{
    List<Question> questions=new ArrayList<>();
    int score=0;
    int correct=0;
    int wrong=0;
    String studentName;
    String rollNo;
    List<Integer> userAns=new ArrayList<>();
    public void loadQues(String filename)
    {
        questions.clear();
        try
        {
            BufferedReader br=new BufferedReader(new FileReader(filename));
            String line;
            while((line=br.readLine())!=null)
            {
                String parts[]=line.split("\\|");
                String question=parts[0];
                String options[]=parts[1].split(",");
                int answer=Integer.parseInt(parts[2]);
                questions.add(new Question(question,options,answer));
            }
            br.close();
        }
        catch(Exception e)
        {
            System.out.println("Erorr occurred while loading questions");
        }
    }
    public void startQuiz()
    {
        score=0;
        correct=0;
        wrong=0;
        Scanner sc=new Scanner(System.in);
        userAns.clear();
        System.out.print("Enter your name:");
        studentName=sc.nextLine();
        System.out.print("Enter roll number:");
        rollNo=sc.nextLine();
        if(questions.size()==0)
        {
            System.out.println("No Questions Available at this time....");
            return;
        }
        Collections.shuffle(questions);
        long startTime=System.currentTimeMillis();
        long timelimit=300000; //5 minutes

        for(int i=0;i<questions.size();i++)
        {
            Question q=questions.get(i);
            long currentTime=System.currentTimeMillis();
            if(currentTime-startTime>timelimit)
            {
                System.out.println("\n Time up..!!");
                break;
            }
            System.out.println("\nQuestion "+(i+1)+": "+q.question);
            for(int j=0;j<q.options.length;j++)
            {
                System.out.println((j+1)+". "+q.options[j]);
            }
            System.out.print("Your answer:");
            int ans;
            try
            {
                ans=sc.nextInt();
            }
            catch(Exception e)
            {
                System.out.println("Invalid input ! skipping..");
                sc.nextLine();
                i--;
                continue;
            }
            userAns.add(ans);
            if(ans==q.validanswer)
            {
                score++;
                correct++;
            }
            else
            {
                wrong++;
            }

        }
        showResult();
    }
    public void showResult()
    {
        int total=questions.size();
        int total_answered=correct+wrong;
        double percentage=(score*100.0)/total;
        System.out.println("\n-----Results and Performance-----");
        System.out.println("Name:"+studentName);
        System.out.println("Roll No:"+rollNo);
        System.out.println("Total Questions:"+total);
        System.out.println("Total Questions Answered:"+total_answered);
        System.out.println("Correctly answered:"+correct);
        System.out.println("Wrong answered:"+wrong);
        System.out.println("Score:"+score);
        System.out.println("Percentage:"+percentage+"%");

        if(percentage>=75)
        {
            System.out.println("Performance: Excellent");
        }
        else if(percentage>=50)
        {
            System.out.println("Performance: Good");
        }
        else
        {
            System.out.println("Performance: Needs Improvement..");
        }
        System.out.println("----Answers Review----");
        boolean wrong=false;
        for(int i=0;i<userAns.size();i++)
        {
            Question q=questions.get(i);
            int useransw=userAns.get(i);
            if(useransw!=q.validanswer)
            {
                wrong=true;
                System.out.println("Q"+(i+1)+": "+q.question);
                for(int j=0;j<q.options.length;j++)
                {
                    String option=(j+1)+". "+q.options[j];
                    if((j+1)==useransw)
                    {
                        option+="<--Your`s answer";
                    }
                    if((j+1)==q.validanswer)
                    {
                        option+="<--Correct answer";
                    }
                    System.out.println(option);
                }
                System.out.println("Result: Wrong");
            }
        }
        if(!wrong)
        {
            System.out.println("All answers are correct!!");
        }
    }
}