import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.io.*;
import java.util.Random;
import java.util.Scanner;
public class Calculate
{
    public static void main(String []args)
    {
        ScriptEngine javascript = new ScriptEngineManager().getEngineByName("JavaScript");
        String exp="";//����ʽ
        double result = 0; //���
        //�ļ�����
        PrintStream ps = null;
        try 
        {
            File file =new File("./result.txt");
            ps = new PrintStream(new FileOutputStream(file));
        } 
        catch (FileNotFoundException e) 
        {
            e.printStackTrace();
        }
        ps.println("201571030101");
        
        
        //����
        Scanner sc=new Scanner(System.in);
        System.out.println("��������Ҫ������������ʽ��");
        int number=sc.nextInt();
        for(int j=0;j<number;j++)
        {
            //�������������
            int[] array = new int[5];
            Random r = new Random();
            for (int i = 0, n = array.length; i < n; i++)
            {
                array[i] = r.nextInt(100);
            }
            char[] c={'+','-','*','/'};
            Random ran = new Random();
            int index=ran.nextInt(c.length);
            int index1=ran.nextInt(c.length);
            int index2=ran.nextInt(c.length);
            int index3=ran.nextInt(c.length);
            int s = r.nextInt(4);
            switch (s)
            {
            
            	case 0:
            	{
            		exp = String.valueOf(""+array[0]+c[index]+array[1]);
            		break;
            	}
            	case 1:
            	{
            		exp = String.valueOf(""+array[0]+c[index]+array[1]+c[index1]+array[2]);
            		break;
            	}
            	case 2:
            	{
            		exp = String.valueOf(""+array[0]+c[index]+array[1]+c[index1]+array[2]+c[index2]+array[3]);
            		break;
            	}
            	case 3:
            	{
            		exp = String.valueOf(""+array[0]+c[index]+array[1]+c[index1]+array[2]+c[index2]+array[3]+c[index3]+array[4]);
            		break;
            	}
            }
            
            try 
            {
                result = Double.parseDouble(String.valueOf(javascript.eval(exp)));
            } 
            catch (ScriptException e) 
            {
                e.printStackTrace();
            }
            if(Math.floor(result)==result&&result>0&&result<99999) 
            {
                System.out.println(exp + "=" + (int) result);
                ps.println(exp + "= " + (int) result);
            }
            else j--;

        }

    }

}