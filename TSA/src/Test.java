public class Test {


    public int[] find(boolean[] nums){
        int k = 0;
        for(int i = 0;i < nums.length;i++){
            if(nums[i] == true) k++;
        }
        int res[] = new int[k];
        for(int i = 0;i < k;i++){
            if(nums[i] == false){
                res[i] = i + 1;
            }
        }
        return res;
    }

    public double norm(double[] nums){
        double res = 0;
        for(int i = 0;i < nums.length;i++){
            res = res + nums[i] * nums[i];
        }
        return Math.sqrt(res);
    }

    public int randi(int imax){
        return (int) (Math.random() * imax + 1);
    }

    public double sign(double num){
        if(num < 0){
            return  -1;
        }else if(num > 0){
            return 1;
        }else return 0;
    }

    public boolean isequal(double[] s1,double[] s2){
        if(s1.length != s2.length) return false;
        for(int i = 0;i < s1.length;i++){
            double diff = s1[i] - s2[i];
            if(diff != 0)return false;
        }
        return true;
    }

    public static void main(String[] args) {
        boolean []s = {true,false,true,false};
        int[] res = new Test().find(s);
        for(int i = 0;i < res.length;i++){
            System.out.println(res[i]);
        }
        int a = 2;
        int b = new Test().randi(a);
        System.out.println(b);
        for(int i = 0;i < 5;i++) {
            System.out.println(Math.random());
        }
        double []X = new double[]{1,2,4,8,16};
        double []Y = new double[]{1,2,3,7,16};
        double C= new Test().norm(X);
        System.out.println(C);
        double[] D=new double[X.length];
        for(int i = 0;i < X.length;i++) {
            D[i] = new Test().sign(X[i] - 2);
            System.out.println(D[i]);
        }
        boolean qq = new Test().isequal(X,Y);
        System.out.println(qq);
        if (0.2<(double)1/2){
            System.out.println("11");
        }
    }
}
