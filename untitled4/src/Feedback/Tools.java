package Feedback;
import java.io.*;
import java.util.*;

public class Tools {
    public static double  MinValue = 0.00000000001;
    public static double  ChangeInFeaSolution = 0.01;//在step中增加或者减少的值
    public static double AreaOtherSolution = 0.001;
    public void Sort(int desOrInc,int[] arr)
    {
        boolean sort = false;
        int t = -1;
        for(int i=0;i<arr.length-1;i++){
            sort = false;
            for(int j=0;j<arr.length-1-i;j++){
                if(desOrInc == 1){//
                    if(arr[i] < arr[i+1]) {
                        t = arr[i];
                        arr[i] = arr[i+1];
                        arr[i+1] = t;
                        sort = true;
                    }
                } else if(desOrInc == 2){//????
                    if(arr[i] > arr[i+1]) {
                        t = arr[i];
                        arr[i] = arr[i+1];
                        arr[i+1] = t;
                        sort = true;
                    }
                }
            }
            if(!sort) break;
        }
    }

    public void Sort(int desOrInc,int[] arr,int[] arrIndex)
    {
        boolean sort = false;
        int t = -1;
        for(int i=0;i<arr.length;i++){
            arrIndex[i] = i;
        }
        for(int i=0;i<arr.length-1;i++){
            sort = false;
            for(int j=0;j<arr.length-1-i;j++){
                if(desOrInc == 1){//????
                    if(arr[i] < arr[i+1]) {
                        t = arr[i];
                        arr[i] = arr[i+1];
                        arr[i+1] = t;

                        t = arrIndex[i];
                        arrIndex[i] = arrIndex[i+1];
                        arrIndex[i+1] = t;

                        sort = true;
                    }
                } else if(desOrInc == 2){//
                    if(arr[i] > arr[i+1]) {
                        t = arr[i];
                        arr[i] = arr[i+1];
                        arr[i+1] = t;

                        t = arrIndex[i];
                        arrIndex[i] = arrIndex[i+1];
                        arrIndex[i+1] = t;

                        sort = true;
                    }
                }
            }
            if(!sort) break;
        }
    }

    public void CreateDiffRandom(int start,int end,ArrayList<Object> list){
        int n = end-start;
        Random rand = new Random();
        boolean[] bool = new boolean[n];
        int num =0;

        for (int i = 0; i<n; i++){
            do{
                num = rand.nextInt(n);
            }while(bool[num]);
            bool[num] =true;
            list.add(num);
        }
    }

    public static int GetRandom(int min, int max)
    {
        Random random = new Random();
        int s = random.nextInt(max) % (max - min + 1) + min;
        return s;
        //return String.valueOf(s);
    }
    public void CreateDiffRandomRetArr(int n,int min,int max,int[] randomArr)
    {
        //ArrayList Value = new ArrayList();
        //int n = end-start;
        //Random rand = new Random();
        //boolean[] bool = new boolean[n];
        if(n > (max-min)) {
            System.out.println("CreateDiffRandomRetArr is error.*******************");
            return ;
        }
        int num =0;
        boolean find = false;
        int j = 0;

        for (int i = 0; i < n; ){
            num = GetRandom(min,max);
            find = false;
            for(j = 0; j < i; j++){
                if(num == randomArr[j]) {
                    find = true;
                    break;
                }
            }
            if(!find || j==i){
                randomArr[i] = num;
                System.out.print(num+" ");
                i++;
            }
        }
        System.out.println("");
    }

    public void SaveFile(String fileName,String data,boolean append)
    {
        try{
            File file =new File(fileName);

            if(!file.exists()){
                file.createNewFile();
            }

            FileWriter fileWritter = new FileWriter(file.getName(),append);
            BufferedWriter bufferWritter = new BufferedWriter(fileWritter);
            bufferWritter.write(data);
            bufferWritter.flush();
            bufferWritter.close();
            //fileWritter.flush();
            fileWritter.close();
            //System.out.println("Done");
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public void readFileByLines(String fileName,String data,int lineNum) {
        BufferedReader reader = null;
        try {
            System.out.println("???????λ?????????????ζ?????У?");
            reader = new BufferedReader(new FileReader(fileName));
            String tempString = null;
            int line = 1;

            while ((tempString = reader.readLine()) != null) {
                System.out.println("line " + line + ": " + tempString);
                if(line == lineNum) break;
                line++;
            }
            reader.close();
            data = tempString;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {

                }
            }
        }

    }

    public int Getlinenum(String fileName) {
        BufferedReader reader = null;
        try {
            //System.out.println("???????λ?????????????ζ?????У?");
            reader = new BufferedReader(new FileReader(fileName));
            String tempString = null;
            String s = reader.readLine();
            int lines = 0;
            while (s != null) {
                lines++;
                s = reader.readLine();
            }
            reader.close();
            return lines;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {

                }
            }
        }
        return 0;
    }

    public String ReadAppointedLine(String fileName , int num){
        BufferedReader reader = null;
        try {
            //System.out.println("查找指定行");
            reader = new BufferedReader(new FileReader(fileName));
            String s = reader.readLine();
            int lines = 0;
            while (s != null ) {
                if (lines == num)
                {
                    return s;
                }
                s = reader.readLine();
                lines++;
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {

                }
            }
        }
        return " ";
    }

    /*读取CEC2017_Shift M文件*/
    public double[] ReadOMFile(String FileName,int nx){

        double[] Number =new double[nx];
        BufferedReader bf = null;
        try {
            bf = new BufferedReader(new FileReader(FileName));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        String textLine[];
        String str = "";
        try {
            while ((str = bf.readLine()) != null) {
                String a[] = str.trim().split("  ");
                String b[] = new String[a.length];
                for (int i = 0; i < nx; i++) {
                    b[i] = a[i].trim();
                    //double[] number =new double[b.length];
                    Number[i] = Double.parseDouble(b[i]);
                    //u[i] = Number[i];
                    //System.out.println(Number[i]);
                    //System.out.println(b[i]);
                }
            }
            bf.close();
        }catch (IOException e) {
            e.printStackTrace();
        }

        return Number;
    }

    /*判断素数*/
    public static boolean IsPrime(int n){
        if(n == 1 || n % 2 ==0 && n !=2 )
        {
            return false;
        }
        else{
            for (int i=3;i<Math.sqrt(n);i+=2){
                if(n%i == 0){
                    return false;
                }
            }
        }
        return true;
    }
}
