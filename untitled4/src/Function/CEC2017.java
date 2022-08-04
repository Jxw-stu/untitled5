package Function;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import static java.lang.Math.*;


public class CEC2017 {
	//int nx;
    double y[]=new double[100];
    double y0[]=new double[100];
    double y1[]=new double[100];
    double y2[]=new double[100];
    double y3[]=new double[100];
    double y4[]=new double[100];
    double y5[]=new double[100];

    double Os[]=new double[100];
    double Mr[]=new double[100];
    int S[]=new int[100];
    //double x[]=new double[]{};
    int s_flag;
    int r_flag;

    double f[]=new double[1] ;

    /*public CEC2017_Function(double[] shift_data_1, double[] m_1_d10, int varsSum) {
        for (int i=0;i<varsSum;i++){
            Os[i] = shift_data_1[i];
        }
        for (int i=0;i<varsSum*varsSum;i++){
            Mr[i] = m_1_d10[i];
        }
    }*/

    /*读取CEC2017_Shift M文件*/
    public boolean ReadOMFile(String FileName,double Number[],int nx){

        //double[] Number =new double[nx];
        Double n[] = new Double[nx*nx];
        BufferedReader bf = null;
        try {
            bf = new BufferedReader(new FileReader(FileName));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return false;
        }
        String textLine[];
        String str = "";
        int k=0;
        try {
            while ((str = bf.readLine()) != null) {
                String a[] = str.trim().split("  ");
                String b[] = new String[a.length];
                for (int i = 0; i < a.length; i++) {
                    b[i] = a[i].trim();
                    //double[] number =new double[b.length];
                    n[i] = Double.parseDouble(b[i]);
                    //System.out.println(Number[i]);
                    //System.out.println(a[i]);
                }

                /*for(int i= 0;i<Number.length;i++){
                    Number[i] = n[i];
                }*/
            }
            for (int i = 0; i < nx*nx; i++) {
                Number[i] = n[i];
            }
            bf.close();
        }catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /*获取函数值
     * //x[]为初始值数组,nx为维数，index为模型类型
     */
    public double GetValue(double x[],int nx,int index){

        switch(index) {
            case 1:
                bent_cigar_func(x, f, nx, s_flag, r_flag);
                f[0] += 100.0;
                break;
            case 2:
                sum_diff_pow_func(x, f, nx, s_flag, r_flag);
                f[0] += 200.0;
                //printf("\nError: This function (F2) has been deleted\n");
                break;
            case 3:
                zakharov_func( x, f, nx, s_flag, r_flag);
                f[0] += 300.0;
                break;
            case 4:
                rosenbrock_func( x, f, nx, s_flag, r_flag);
                f[0] += 400.0;
                break;
            case 5:
                rastrigin_func(x, f, nx, s_flag, r_flag);
                f[0] += 500.0;
                break;
            case 6:
                schaffer_F7_func( x, f, nx, s_flag, r_flag);
                f[0] += 600.0;
                break;
            case 7:
                bi_rastrigin_func(x, f, nx, s_flag, r_flag);
                f[0] += 700.0;
                break;
            case 8:
                step_rastrigin_func( x, f, nx, s_flag, r_flag);
                f[0] += 800.0;
                break;
            case 9:
                levy_func( x, f, nx, s_flag, r_flag);
                f[0] += 900.0;
                break;
            case 10:
                schwefel_func( x, f, nx, s_flag, r_flag);
                f[0] += 1000.0;
                break;
            case 11:
                hf01( x , f , nx , S , s_flag , r_flag );
                f[0] += 1100.0;
                break;
            case 12:
                hf02( x , f , nx , S , s_flag , r_flag );
                f[0]+=1200.0;
                break;
            case 13:
                hf03( x , f , nx , S , s_flag , r_flag );
                f[0]+=1300.0;
                break;
            case 14:
                hf04( x , f , nx , S , s_flag , r_flag );
                f[0]+=1400.0;
                break;
            case 15:
                hf05( x , f , nx , S , s_flag , r_flag );
                f[0]+=1500.0;
                break;
            case 16:
                hf06( x , f , nx , S , s_flag , r_flag );
                f[0]+=1600.0;
                break;
            case 17:
                hf07( x , f , nx , S , s_flag , r_flag );
                f[0]+=1700.0;
                break;
            case 18:
                hf08( x , f , nx , S , s_flag , r_flag );
                f[0]+=1800.0;
                break;
            case 19:
                hf09( x , f , nx , S , s_flag , r_flag );
                f[0]+=1900.0;
                break;
            case 20:
                hf10( x , f , nx , S , s_flag , r_flag );
                f[0]+=2000.0;
                break;
            default:
                System.out.print("\nError: There are only 30 test functions in this test suite!\n");
                f[0] = 0.0;
                break;
        }
        return f[0];
    }


    /* 获取偏移Os值 */
    public void SetShift_Data(double shift[],int nx){
        for(int i=0 ; i<nx ; i++){
            Os[i] = shift[i];
        }

    }


    /* 获取旋转M值 */
    public void SetM_Data(double M[] ,int nx){

        for(int i=0 ; i<nx ; i++){
            Mr[i] = M[i];
        }
    }

    /* 获取旋转M值 */
    public void SetShuffle_data(int Shuffle[] ,int nx){

        for(int i=0 ; i<nx ; i++){
            S[i] = Shuffle[i];
        }
    }


    /* 获取值 s_flag  r_flag*/
    public void Set_flag(int s_flag , int r_flag){
        this.s_flag = s_flag;
        this.r_flag = r_flag;
    }


    /* Bent_Cigar f1 */
    public void bent_cigar_func (double x[], double f[], int nx, int s_flag, int r_flag)
    {
        int i;
        double z[]=new double[nx];
        sr_func (x, z, nx, Os, Mr,1.0, s_flag, r_flag); /* shift and rotate */

        f[0]= z[0]*z[0];
        for (i=1; i<nx; i++)
        {
            f[0] += pow(10.0,6.0)*z[i]*z[i];
        }

    }


    /* sum of different power f2 */
    public void sum_diff_pow_func (double x[], double f[], int nx, int s_flag, int r_flag)
    {
        int i;
        double z[]=new double[nx];
        sr_func (x, z, nx, Os, Mr,1.0, s_flag, r_flag); // shift and rotate
        f[0] = 0.0;
        double sum = 0.0;
        for (i=0; i<nx; i++)
        {
            double xi = z[i];
            double newv = pow((Math.abs(xi)),(i+1));
            sum = sum + newv;
        }

        f[0] = sum;
    }


    /* zakharov  f3 */
    public void zakharov_func (double x[], double f[], int nx, int s_flag, int r_flag)
    {
        int i;
        double z[]=new double[nx];
        sr_func (x, z, nx, Os, Mr,1.0, s_flag, r_flag); // shift and rotate
        f[0] = 0.0;
        double sum1 = 0.0;
        double sum2 = 0.0;
        for (i=0; i<nx; i++)
        {
            double xi = z[i];
            sum1 = sum1 + pow(xi,2);
            sum2 = sum2 + 0.5*(i+1)*xi;
        }

        f[0] = sum1 + pow(sum2,2) + pow(sum2,4);
    }


    /* Rosenbrock's  f4 */
    public void rosenbrock_func (double x[], double f[], int nx, int s_flag, int r_flag)
    {
        int i;
        double tmp1,tmp2;
        double z[]=new double[nx];
        f[0] = 0.0;
        sr_func (x, z, nx, Os, Mr, 2.048/100.0, s_flag, r_flag); /* shift and rotate */
        z[0] += 1.0;//shift to orgin
        for (i=0; i<nx-1; i++)
        {
            z[i+1] += 1.0;//shift to orgin
            tmp1=z[i]*z[i]-z[i+1];
            tmp2=z[i]-1.0;
            f[0] += 100.0*tmp1*tmp1 +tmp2*tmp2;
        }
    }

    /* Rastrigin's  f5 */
    public void rastrigin_func (double x[], double f[], int nx, int s_flag, int r_flag)
    {
        int i;
        f[0] = 0.0;
        double z[]=new double[nx];
        sr_func (x, z, nx, Os, Mr, 5.12/100.0, s_flag, r_flag); /* shift and rotate */

        for (i=0; i<nx; i++)
        {
            f[0] += (z[i]*z[i] - 10.0* cos(2.0* PI*z[i]) + 10.0);
        }
    }


    /* Schwefel's 1.2  f6 */
    public void schaffer_F7_func (double x[], double f[], int nx, int s_flag, int r_flag)
    {
        int i;
        double tmp;
        double z[]=new double[nx];
        f[0] = 0.0;
        sr_func (x, z, nx, Os, Mr, 1.0, s_flag, r_flag); /* shift and rotate */
        for (i=0; i<nx-1; i++)
        {
            z[i]= pow(y[i]*y[i]+y[i+1]*y[i+1],0.5);
            tmp=Math.sin(50.0* pow(z[i],0.2));
            f[0] += pow(z[i],0.5)+ pow(z[i],0.5)*tmp*tmp ;
        }
        f[0] = f[0]*f[0]/(nx-1)/(nx-1);
    }


    /* Lunacek Bi_rastrigin Function  f7*/
    public void bi_rastrigin_func (double x[], double f[], int nx, int s_flag, int r_flag)
    {
        int i;
        double mu0=2.5,d=1.0,s,mu1,tmp,tmp1,tmp2;
        double tmpx[] =new double[nx];
        double z[]=new double[nx];
        //tmpx=(double *)malloc(sizeof(double)  *  nx);
        s=1.0-1.0/(2.0*pow(nx+20.0,0.5)-8.2);
        mu1=-pow((mu0*mu0-d)/s,0.5);

        if (s_flag==1)
            shiftfunc(x, y, nx, Os);
        else
        {
            for (i=0; i<nx; i++)//shrink to the orginal search range
            {
                y[i] = x[i];
            }
        }
        for (i=0; i<nx; i++)//shrink to the orginal search range
        {
            y[i] *= 10.0/100.0;
        }

        for (i = 0; i < nx; i++)
        {
            tmpx[i]=2*y[i];
            if (Os[i] < 0.0)
                tmpx[i] *= -1.;
        }
        for (i=0; i<nx; i++)
        {
            z[i]=tmpx[i];
            tmpx[i] += mu0;
        }
        tmp1=0.0;tmp2=0.0;
        for (i=0; i<nx; i++)
        {
            tmp = tmpx[i]-mu0;
            tmp1 += tmp*tmp;
            tmp = tmpx[i]-mu1;
            tmp2 += tmp*tmp;
        }
        tmp2 *= s;
        tmp2 += d*nx;
        tmp=0.0;

        if (r_flag==1)
        {
            rotatefunc(z, y, nx, Mr);
            for (i=0; i<nx; i++)
            {
                tmp+=cos(2.0*PI*y[i]);
            }
            if(tmp1<tmp2)
                f[0] = tmp1;
            else
                f[0] = tmp2;
            f[0] += 10.0*(nx-tmp);
        }
        else
        {
            for (i=0; i<nx; i++)
            {
                tmp+=cos(2.0*PI*z[i]);
            }
            if(tmp1<tmp2)
                f[0] = tmp1;
            else
                f[0] = tmp2;
            f[0] += 10.0*(nx-tmp);
        }

        //free(tmpx);
    }

    /* Noncontinuous Rastrigin's  f8 */
    public void step_rastrigin_func (double x[], double f[], int nx, int s_flag, int r_flag)
    {
        int i;
        f[0]=0.0;
        double z[]=new double[nx];
        for (i=0; i<nx; i++)
        {
            if (Math.abs(y[i]-Os[i])>0.5)
                y[i]=Os[i]+floor(2*(y[i]-Os[i])+0.5)/2;
        }

        sr_func (x, z, nx, Os, Mr, 5.12/100.0, s_flag, r_flag); /* shift and rotate */

        for (i=0; i<nx; i++)
        {
            f[0] += (z[i]*z[i] - 10.0*cos(2.0*PI*z[i]) + 10.0);
        }
    }


    /* Levy function *//* Levy f9 */
    public void levy_func (double x[], double f[], int nx, int s_flag, int r_flag)
    {
        int i;
        f[0] = 0.0;
        double z[]=new double[nx];
        sr_func (x, z, nx, Os, Mr,1.0, s_flag, r_flag); /* shift and rotate */

        double w[]=new double[nx];
        //w=(double *)malloc(sizeof(double)  *  nx);

        double sum1= 0.0;
        for (i=0; i<nx; i++)
        {
            w[i] = 1.0 + (z[i] - 1.0)/4.0;
        }

        double term1 = pow((sin(PI*w[0])),2);
        double term3 = pow((w[nx-1]-1),2) * (1+pow((sin(2*PI*w[nx-1])),2));

        double sum = 0.0;

        for (i=0; i<nx-1; i++)
        {
            double wi = w[i];
            double newv = pow((wi-1),2) * (1+10*pow((sin(PI*wi+1)),2));
            sum = sum + newv;
        }

        f[0] = term1 + sum + term3;
        //free(w);   // ADD THIS LINE to free memory! Thanks for Dr. Janez
    }

    /* Schwefel's  f10 */
    public void schwefel_func (double x[], double f[], int nx, int s_flag, int r_flag)
    {
        int i;
        double tmp;
        f[0]=0.0;
        double z[]=new double[nx];
        sr_func (x, z, nx, Os, Mr, 1000.0/100.0, s_flag, r_flag); /* shift and rotate */

        for (i=0; i<nx; i++)
        {
            z[i] += 4.209687462275036e+002;
            if (z[i]>500)
            {
                //f[0]-=(500.0-fmod(z[i],500))*sin(pow(500.0-fmod(z[i],500),0.5));
                f[0]-=(500.0-(z[i]%500))*sin(pow(500.0-(z[i]%500),0.5));
                tmp=(z[i]-500.0)/100;
                f[0]+= tmp*tmp/nx;
            }
            else if (z[i]<-500)
            {
                //f[0]-=(-500.0+fmod(Math.abs(z[i]),500))*sin(pow(500.0-fmod(Math.abs(z[i]),500),0.5));
                f[0]-=(-500.0+(Math.abs(z[i])%500))*sin(pow(500.0-(Math.abs(z[i])%500),0.5));
                tmp=(z[i]+500.0)/100;
                f[0]+= tmp*tmp/nx;
            }
            else
                f[0]-=z[i]*sin(pow(Math.abs(z[i]),0.5));
        }
        f[0] +=4.189828872724338e+002*nx;

    }

    /* Ellipsoidal */
    void ellips_func (double x[], double f[], int nx, int s_flag, int r_flag)
    {
        int i;
        double z[]=new double[nx];
        f[0] = 0.0;
        sr_func (x, z, nx, Os, Mr,1.0, s_flag, r_flag); /* shift and rotate */
        for (i=0; i<nx; i++)
        {
            f[0] += pow(10.0,6.0*i/(nx-1))*z[i]*z[i];
        }
    }

    /* Hybrid Function 1 f11 */
    void hf01 (double x[], double f[], int nx,int S[],int s_flag,int r_flag)
    {
        int i,tmp,cf_num=3;
        double fit[] =new double[1];
        double fit1[] =new double[1];
        double fit2[] =new double[1];
        int G[] =new int[3];
        int G_nx[] =new int[3];
        double Gp[]=new double[]{0.2,0.4,0.4};

        double z[]=new double[nx];
        tmp=0;
        for (i=0; i<cf_num-1; i++)
        {
            G_nx[i] = (int) ceil(Gp[i]*nx);
            tmp += G_nx[i];
        }
        G_nx[cf_num-1]=nx-tmp;
        G[0]=0;
        for (i=1; i<cf_num; i++)
        {
            G[i] = G[i-1]+G_nx[i-1];
        }

        sr_func (x, z, nx, Os, Mr, 1.0, s_flag, r_flag); /* shift and rotate */

        for (i=0; i<nx; i++)
        {
            y[i]=z[S[i]-1];
        }
        i=0;
        //y(G[0] = 2)开始 fit(0)
        for(int j=0;j<10;j++){
            y0[j] = y[G[i]+j];
        }
        zakharov_func(y0,fit,G_nx[i],0,0);
        i=1;
        //y(G[1] = 4)开始 fit(1)
        for(int j=0;j<10;j++){
            y1[j] = y[G[i]+j];
        }
        rosenbrock_func(y1,fit1,G_nx[i],0,0);
        i=2;
        for(int j=0;j<10;j++){
            y2[j] = y[G[i]+j];
        }
        //y(G[2] = 6)开始 fit(2)
        rastrigin_func(y2,fit2,G_nx[i],0,0);
        f[0]=0.0;
        f[0] = fit[0] + fit1[0] +fit2[0];
        /*for(i=0;i<cf_num;i++)
        {
            f[0] += fit[i];
        }*/
    }

    /* Hybrid Function 2   f12 */
    public void hf02 (double x[], double f[], int nx,int S[],int s_flag,int r_flag)
    {
        int i,tmp,cf_num=3;
        double fit[] =new double[1];
        double fit1[] =new double[1];
        double fit2[] =new double[1];
        int G[] =new int[3];
        int G_nx[] =new int[3];
        double Gp[] = new double[]{0.3,0.3,0.4};

        double z[]=new double[nx];
        tmp=0;
        for (i=0; i<cf_num-1; i++)
        {
            G_nx[i] = (int) ceil(Gp[i]*nx);
            tmp += G_nx[i];
        }
        G_nx[cf_num-1]=nx-tmp;

        G[0]=0;
        for (i=1; i<cf_num; i++)
        {
            G[i] = G[i-1]+G_nx[i-1];
        }

        sr_func (x, z, nx, Os, Mr, 1.0, s_flag, r_flag); /* shift and rotate */

        for (i=0; i<nx; i++)
        {
            y[i]=z[S[i]-1];
        }
        i=0;
        for(int j=0;j<10;j++){
            y0[j] = y[G[i]+j];
        }
        ellips_func(y0,fit,G_nx[i],0,0);

        i=1;
        for(int j=0;j<10;j++){
            y1[j] = y[G[i]+j];
        }
        schwefel_func(y1,fit1,G_nx[i],0,0);

        i=2;
        for(int j=0;j<10;j++){
            y2[j] = y[G[i]+j];
        }
        bent_cigar_func(y2,fit2,G_nx[i],0,0);

        f[0]=0.0;
        f[0] = fit[0] + fit1[0] +fit2[0];
        /*for(i=0;i<cf_num;i++)
        {
            f[0] += fit[i];
        }*/
    }

    /* Hybrid Function 2  f13 */
    void hf03 (double x[], double f[], int nx,int S[],int s_flag,int r_flag)
    {
        int i,tmp,cf_num=3;
        double fit[] =new double[1];
        double fit1[] =new double[1];
        double fit2[] =new double[1];
        int G[] =new int[3];
        int G_nx[] =new int[3];
        double Gp[] = new double[]{0.3,0.3,0.4};

        double z[]=new double[nx];
        tmp=0;
        for (i=0; i<cf_num-1; i++)
        {
            G_nx[i] = (int) ceil(Gp[i]*nx);
            tmp += G_nx[i];
        }
        G_nx[cf_num-1]=nx-tmp;

        G[0]=0;
        for (i=1; i<cf_num; i++)
        {
            G[i] = G[i-1]+G_nx[i-1];
        }

        sr_func (x, z, nx, Os, Mr, 1.0, s_flag, r_flag); /* shift and rotate */

        for (i=0; i<nx; i++)
        {
            y[i]=z[S[i]-1];
        }

        i=0;
        for(int j=0;j<10;j++){
            y0[j] = y[G[i]+j];
        }
        bent_cigar_func(y0,fit,G_nx[i],0,0);

        i=1;
        for(int j=0;j<10;j++){
            y1[j] = y[G[i]+j];
        }
        rosenbrock_func(y1,fit1,G_nx[i],0,0);

        i=2;
        for(int j=0;j<10;j++){
            y2[j] = y[G[i]+j];
        }
        bi_rastrigin_func(y2,fit2,G_nx[i],0,0);

        f[0]=0.0;
        f[0] = fit[0] + fit1[0] +fit2[0];
        /*for(i=0;i<cf_num;i++)
        {
            f[0] += fit[i];
        }*/
    }

    /* Ackley's  */
    void ackley_func (double x[], double f[], int nx, int s_flag, int r_flag)
    {
        int i;
        double sum1, sum2;
        sum1 = 0.0;
        sum2 = 0.0;
        double z[] = new double[nx];
        sr_func (x, z, nx, Os, Mr, 1.0, s_flag, r_flag); /* shift and rotate */

        for (i=0; i<nx; i++)
        {
            sum1 += z[i]*z[i];
            sum2 += cos(2.0*PI*z[i]);
        }
        sum1 = -0.2*sqrt(sum1/nx);
        sum2 /= nx;
        f[0] =  E - 20.0*exp(sum1) - exp(sum2) +20.0;
    }


    /* Hybrid Function 3  f14 */
    void hf04 (double x[], double f[], int nx,int S[],int s_flag,int r_flag)
    {
        int i,tmp,cf_num=4;
        double fit[] =new double[1];
        double fit1[] =new double[1];
        double fit2[] =new double[1];
        double fit3[] =new double[1];
        int G[] =new int[4];
        int G_nx[] =new int[4];

        double z[]=new double[nx];
        double Gp[]= new double[]{0.2,0.2,0.2,0.4};

        tmp=0;
        for (i=0; i<cf_num-1; i++)
        {
            G_nx[i] = (int) ceil(Gp[i]*nx);
            tmp += G_nx[i];
        }
        G_nx[cf_num-1]=nx-tmp;

        G[0]=0;
        for (i=1; i<cf_num; i++)
        {
            G[i] = G[i-1]+G_nx[i-1];
        }

        sr_func (x, z, nx, Os, Mr, 1.0, s_flag, r_flag); /* shift and rotate */

        for (i=0; i<nx; i++)
        {
            y[i]=z[S[i]-1];
        }
        i=0;
        for (int j=0;j<10;j++)
        {
            y0[j]=y[G[i]+j];
        }
        ellips_func(y0,fit,G_nx[i],0,0);

        i=1;
        for(int j=0;j<10;j++){
            y1[j] = y[G[i]+j];
        }
        ackley_func(y1,fit1,G_nx[i],0,0);

        i=2;
        for(int j=0;j<10;j++){
            y2[j] = y[G[i]+j];
        }
        schaffer_F7_func(y2,fit2,G_nx[i],0,0);

        i=3;
        for(int j=0;j<10;j++){
            y3[j] = y[G[i]+j];
        }
        rastrigin_func(y3,fit3,G_nx[i],0,0);

        f[0]=0.0;
        f[0] = fit[0] + fit1[0] + fit2[0] + fit3[0];
        /*for(i=0;i<cf_num;i++)
        {
            f[0] += fit[i];
        }*/
    }

    /* HGBat, provdided by Hans-Georg Beyer (HGB)*/
    void hgbat_func (double x[], double f[], int nx, int s_flag, int r_flag)
    /* original global optimum: [-1,-1,...,-1] */
    {
        int i;
        double alpha,r2,sum_z;
        alpha=1.0/4.0;
        double z[] = new double[nx];
        sr_func (x, z, nx, Os, Mr, 5.0/100.0, s_flag, r_flag); /* shift and rotate */

        r2 = 0.0;
        sum_z=0.0;
        for (i=0; i<nx; i++)
        {
            z[i]=z[i]-1.0;//shift to orgin
            r2 += z[i]*z[i];
            sum_z += z[i];
        }
        f[0]=pow(Math.abs(pow(r2,2.0)-pow(sum_z,2.0)),2*alpha) + (0.5*r2 + sum_z)/nx + 0.5;
    }

    /* Hybrid Function 4  f15 */
    void hf05 (double x[], double f[], int nx,int S[],int s_flag,int r_flag)
    {
        int i,tmp,cf_num=4;
        double fit[] =new double[1];
        double fit1[] =new double[1];
        double fit2[] =new double[1];
        double fit3[] =new double[1];
        int G[] =new int[4];
        int G_nx[] =new int[4];

        double z[]=new double[nx];
        double Gp[] = new double[]{0.2,0.2,0.3,0.3};

        tmp=0;
        for (i=0; i<cf_num-1; i++)
        {
            G_nx[i] = (int) ceil(Gp[i]*nx);
            tmp += G_nx[i];
        }
        G_nx[cf_num-1]=nx-tmp;

        G[0]=0;
        for (i=1; i<cf_num; i++)
        {
            G[i] = G[i-1]+G_nx[i-1];
        }

        sr_func (x, z, nx, Os, Mr, 1.0, s_flag, r_flag); /* shift and rotate */

        for (i=0; i<nx; i++)
        {
            y[i]=z[S[i]-1];
        }
        i=0;
        for (int j=0;j<10;j++)
        {
            y0[j]=y[G[i]+j];
        }
        bent_cigar_func(y0,fit,G_nx[i],0,0);

        i=1;
        for(int j=0;j<10;j++){
            y1[j] = y[G[i]+j];
        }
        hgbat_func(y1,fit1,G_nx[i],0,0);

        i=2;
        for(int j=0;j<10;j++){
            y2[j] = y[G[i]+j];
        }
        rastrigin_func(y2,fit2,G_nx[i],0,0);

        i=3;
        for(int j=0;j<10;j++){
            y3[j] = y[G[i]+j];
        }
        rosenbrock_func(y3,fit3,G_nx[i],0,0);

        f[0]=0.0;
        f[0] = fit[0] + fit1[0] + fit2[0] + fit3[0];
        /*for(i=0;i<cf_num;i++)
        {
            f[0] += fit[i];
        }*/
    }


    /* Expanded Scaffer??s F6  */
    void escaffer6_func (double x[], double f[], int nx, int s_flag, int r_flag)
    {
        int i;
        double temp1, temp2;
        double z[] = new double[nx];
        sr_func (x, z, nx, Os, Mr, 1.0, s_flag, r_flag); /* shift and rotate */

        f[0] = 0.0;
        for (i=0; i<nx-1; i++)
        {
            temp1 = sin(sqrt(z[i]*z[i]+z[i+1]*z[i+1]));
            temp1 =temp1*temp1;
            temp2 = 1.0 + 0.001*(z[i]*z[i]+z[i+1]*z[i+1]);
            f[0] += 0.5 + (temp1-0.5)/(temp2*temp2);
        }
        temp1 = sin(sqrt(z[nx-1]*z[nx-1]+z[0]*z[0]));
        temp1 =temp1*temp1;
        temp2 = 1.0 + 0.001*(z[nx-1]*z[nx-1]+z[0]*z[0]);
        f[0] += 0.5 + (temp1-0.5)/(temp2*temp2);
    }

    /* Hybrid Function 5  f16 */
    void hf06 (double x[], double f[], int nx,int S[],int s_flag,int r_flag)
    {
        int i,tmp,cf_num=4;
        double fit[] =new double[1];
        double fit1[] =new double[1];
        double fit2[] =new double[1];
        double fit3[] =new double[1];
        int G[] =new int[4];
        int G_nx[] =new int[4];

        double z[]=new double[nx];
        double Gp[]= new double[]{0.2,0.2,0.3,0.3};

        tmp=0;
        for (i=0; i<cf_num-1; i++)
        {
            G_nx[i] = (int) ceil(Gp[i]*nx);
            tmp += G_nx[i];
        }
        G_nx[cf_num-1]=nx-tmp;

        G[0]=0;
        for (i=1; i<cf_num; i++)
        {
            G[i] = G[i-1]+G_nx[i-1];
        }

        sr_func (x, z, nx, Os, Mr, 1.0, s_flag, r_flag); /* shift and rotate */


        for (i=0; i<nx; i++)
        {
            y[i]=z[S[i]-1];
        }
        i=0;
        for (int j=0;j<10;j++)
        {
            y0[j]=y[G[i]+j];
        }
        escaffer6_func(y0,fit,G_nx[i],0,0);

        i=1;
        for(int j=0;j<10;j++){
            y1[j] = y[G[i]+j];
        }
        hgbat_func(y1,fit1,G_nx[i],0,0);

        i=2;
        for(int j=0;j<10;j++){
            y2[j] = y[G[i]+j];
        }
        rosenbrock_func(y2,fit2,G_nx[i],0,0);

        i=3;
        for(int j=0;j<10;j++){
            y3[j] = y[G[i]+j];
        }
        schwefel_func(y3,fit3,G_nx[i],0,0);

        f[0]=0.0;
        f[0] = fit[0] + fit1[0] + fit2[0] + fit3[0];
        /*for(i=0;i<cf_num;i++)
        {
            f[0] += fit[i];
        }*/
    }

    /* Katsuura  */
    void katsuura_func (double x[], double f[], int nx, int s_flag, int r_flag)
    {
        int i,j;
        double temp,tmp1,tmp2,tmp3;
        f[0]=1.0;
        tmp3=pow(1.0*nx,1.2);
        double z[] = new double[nx];
        sr_func (x, z, nx, Os, Mr, 5.0/100.0, s_flag, r_flag); /* shift and rotate */

        for (i=0; i<nx; i++)
        {
            temp=0.0;
            for (j=1; j<=32; j++)
            {
                tmp1=pow(2.0,j);
                tmp2=tmp1*z[i];
                temp += Math.abs(tmp2 - floor(tmp2 + 0.5))/tmp1;
            }
            f[0] *= pow(1.0+(i+1)*temp,10.0/tmp3);
        }
        tmp1=10.0/nx/nx;
        f[0]=f[0]*tmp1-tmp1;

    }

    /* Griewank-Rosenbrock  */
    void grie_rosen_func (double x[], double f[], int nx, int s_flag, int r_flag)
    {
        int i;
        double temp,tmp1,tmp2;
        double z[] = new double[nx];
        f[0]=0.0;

        sr_func (x, z, nx, Os, Mr, 5.0/100.0, s_flag, r_flag); /* shift and rotate */

        z[0] += 1.0;//shift to orgin
        for (i=0; i<nx-1; i++)
        {
            z[i+1] += 1.0;//shift to orgin
            tmp1 = z[i]*z[i]-z[i+1];
            tmp2 = z[i]-1.0;
            temp = 100.0*tmp1*tmp1 + tmp2*tmp2;
            f[0] += (temp*temp)/4000.0 - cos(temp) + 1.0;
        }
        tmp1 = z[nx-1]*z[nx-1]-z[0];
        tmp2 = z[nx-1]-1.0;
        temp = 100.0*tmp1*tmp1 + tmp2*tmp2;
        f[0] += (temp*temp)/4000.0 - cos(temp) + 1.0 ;
    }

    /* Hybrid Function 6  f17*/
    void hf07 (double x[], double f[], int nx,int S[],int s_flag,int r_flag)
    {
        int i,tmp,cf_num=5;
        double fit[] =new double[1];
        double fit1[] =new double[1];
        double fit2[] =new double[1];
        double fit3[] =new double[1];
        double fit4[] =new double[1];
        int G[] =new int[5];
        int G_nx[] =new int[5];

        double z[]=new double[nx];
        double Gp[]= new double[]{0.1,0.2,0.2,0.2,0.3};

        tmp=0;
        for (i=0; i<cf_num-1; i++)
        {
            G_nx[i] = (int) ceil(Gp[i]*nx);
            tmp += G_nx[i];
        }
        G_nx[cf_num-1]=nx-tmp;

        G[0]=0;
        for (i=1; i<cf_num; i++)
        {
            G[i] = G[i-1]+G_nx[i-1];
        }

        sr_func (x, z, nx, Os, Mr, 1.0, s_flag, r_flag); /* shift and rotate */

        for (i=0; i<nx; i++)
        {
            y[i]=z[S[i]-1];
        }

        i=0;
        for (int j=0;j<10;j++)
        {
            y0[j]=y[G[i]+j];
        }
        katsuura_func(y0,fit,G_nx[i],0,0);

        i=1;
        for (int j=0;j<10;j++)
        {
            y1[j]=y[G[i]+j];
        }
        ackley_func(y1,fit1,G_nx[i],0,0);

        i=2;
        for (int j=0;j<10;j++)
        {
            y2[j]=y[G[i]+j];
        }
        grie_rosen_func(y2,fit2,G_nx[i],0,0);

        i=3;
        for (int j=0;j<10;j++)
        {
            y3[j]=y[G[i]+j];
        }
        schwefel_func(y3,fit3,G_nx[i],0,0);

        i=4;
        for (int j=0;j<10;j++)
        {
            y4[j]=y[G[i]+j];
        }
        rastrigin_func(y4,fit4,G_nx[i],0,0);

        f[0]=0.0;
        f[0] = fit[0] + fit1[0] + fit2[0] + fit3[0] + fit4[0];
        /*for(i=0;i<cf_num;i++)
        {
            f[0] += fit[i];
        }*/
    }

    /* Discus */
    void discus_func (double x[], double f[], int nx, int s_flag, int r_flag)
    {
        int i;
        double z[] = new double[nx];
        sr_func (x, z, nx, Os, Mr,1.0, s_flag, r_flag); /* shift and rotate */
        f[0] = pow(10.0,6.0)*z[0]*z[0];
        for (i=1; i<nx; i++)
        {
            f[0] += z[i]*z[i];
        }
    }

    /* Hybrid Function 6  f18 */
    void hf08 (double x[], double f[], int nx,int S[],int s_flag,int r_flag)
    {
        int i,tmp,cf_num=5;
        double fit[] =new double[1];
        double fit1[] =new double[1];
        double fit2[] =new double[1];
        double fit3[] =new double[1];
        double fit4[] =new double[1];
        int G[] =new int[5];
        int G_nx[] =new int[5];

        double z[]=new double[nx];
        double Gp[]= new double[]{0.2,0.2,0.2,0.2,0.2};

        tmp=0;
        for (i=0; i<cf_num-1; i++)
        {
            G_nx[i] = (int) ceil(Gp[i]*nx);
            tmp += G_nx[i];
        }
        G_nx[cf_num-1]=nx-tmp;

        G[0]=0;
        for (i=1; i<cf_num; i++)
        {
            G[i] = G[i-1]+G_nx[i-1];
        }

        sr_func (x, z, nx, Os, Mr, 1.0, s_flag, r_flag); /* shift and rotate */

        for (i=0; i<nx; i++)
        {
            y[i]=z[S[i]-1];
        }

        i=0;
        for (int j=0;j<10;j++)
        {
            y0[j]=y[G[i]+j];
        }
        ellips_func(y0,fit,G_nx[i],0,0);

        i=1;
        for (int j=0;j<10;j++)
        {
            y1[j]=y[G[i]+j];
        }
        ackley_func(y1,fit1,G_nx[i],0,0);

        i=2;
        for (int j=0;j<10;j++)
        {
            y2[j]=y[G[i]+j];
        }
        rastrigin_func(y2,fit2,G_nx[i],0,0);

        i=3;
        for (int j=0;j<10;j++)
        {
            y3[j]=y[G[i]+j];
        }
        hgbat_func(y3,fit3,G_nx[i],0,0);

        i=4;
        for (int j=0;j<10;j++)
        {
            y4[j]=y[G[i]+j];
        }
        discus_func(y4,fit4,G_nx[i],0,0);

        f[0]=0.0;
        f[0] = fit[0] + fit1[0] + fit2[0] + fit3[0] + fit4[0];
        /*for(i=0;i<cf_num;i++)
        {
            f[0] += fit[i];
        }*/
    }

    /* Weierstrass's  */
    void weierstrass_func (double x[], double f[], int nx, int s_flag, int r_flag)
    {
        int i,j,k_max;
        double sum,sum2 = 0, a, b;
        double z[] =new double[nx];
        a = 0.5;
        b = 3.0;
        k_max = 20;
        f[0] = 0.0;

        sr_func (x, z, nx, Os, Mr, 0.5/100.0, s_flag, r_flag); /* shift and rotate */

        for (i=0; i<nx; i++)
        {
            sum = 0.0;
            sum2 = 0.0;
            for (j=0; j<=k_max; j++)
            {
                sum += pow(a,j)*cos(2.0*PI*pow(b,j)*(z[i]+0.5));
                sum2 += pow(a,j)*cos(2.0*PI*pow(b,j)*0.5);
            }
            f[0] += sum;
        }
        f[0] -= nx*sum2;
    }

    /* Hybrid Function 6  f19*/
    void hf09 (double x[], double f[], int nx,int S[],int s_flag,int r_flag)
    {
        int i,tmp,cf_num=5;
        double fit[] =new double[1];
        double fit1[] =new double[1];
        double fit2[] =new double[1];
        double fit3[] =new double[1];
        double fit4[] =new double[1];
        int G[] =new int[5];
        int G_nx[] =new int[5];

        double z[]=new double[nx];
        double Gp[]= new double[]{0.2,0.2,0.2,0.2,0.2};

        tmp=0;
        for (i=0; i<cf_num-1; i++)
        {
            G_nx[i] = (int) ceil(Gp[i]*nx);
            tmp += G_nx[i];
        }
        G_nx[cf_num-1]=nx-tmp;

        G[0]=0;
        for (i=1; i<cf_num; i++)
        {
            G[i] = G[i-1]+G_nx[i-1];
        }

        sr_func (x, z, nx, Os, Mr, 1.0, s_flag, r_flag); /* shift and rotate */

        for (i=0; i<nx; i++)
        {
            y[i]=z[S[i]-1];
        }

        i=0;
        for (int j=0;j<10;j++)
        {
            y0[j]=y[G[i]+j];
        }
        bent_cigar_func(y0,fit,G_nx[i],0,0);

        i=1;
        for (int j=0;j<10;j++)
        {
            y1[j]=y[G[i]+j];
        }
        rastrigin_func(y1,fit1,G_nx[i],0,0);

        i=2;
        for (int j=0;j<10;j++)
        {
            y2[j]=y[G[i]+j];
        }
        grie_rosen_func(y2,fit2,G_nx[i],0,0);

        i=3;
        for (int j=0;j<10;j++)
        {
            y3[j]=y[G[i]+j];
        }
        weierstrass_func(y3,fit3,G_nx[i],0,0);

        i=4;
        for (int j=0;j<10;j++)
        {
            y4[j]=y[G[i]+j];
        }
        escaffer6_func(y4,fit4,G_nx[i],0,0);

        f[0]=0.0;
        f[0] = fit[0] + fit1[0] + fit2[0] + fit3[0] + fit4[0];
        /*for(i=0;i<cf_num;i++)
        {
            f[0] += fit[i];
        }*/
    }

    /* Hybrid Function 6 f20*/
    void hf10 (double x[], double f[], int nx,int S[],int s_flag,int r_flag)
    {
        int i,tmp,cf_num=6;
        double fit[] =new double[1];
        double fit1[] =new double[1];
        double fit2[] =new double[1];
        double fit3[] =new double[1];
        double fit4[] =new double[1];
        double fit5[] =new double[1];
        int G[] =new int[6];
        int G_nx[] =new int[6];

        double z[]=new double[nx];
        double Gp[]= new double[]{0.1,0.1,0.2,0.2,0.2,0.2};

        tmp=0;
        for (i=0; i<cf_num-1; i++)
        {
            G_nx[i] = (int) ceil(Gp[i]*nx);
            tmp += G_nx[i];
        }
        G_nx[cf_num-1]=nx-tmp;

        G[0]=0;
        for (i=1; i<cf_num; i++)
        {
            G[i] = G[i-1]+G_nx[i-1];
        }

        sr_func (x, z, nx, Os, Mr, 1.0, s_flag, r_flag); /* shift and rotate */

        for (i=0; i<nx; i++)
        {
            y[i]=z[S[i]-1];
        }

        i=0;
        for (int j=0;j<10;j++)
        {
            y0[j]=y[G[i]+j];
        }
        hgbat_func(y0,fit,G_nx[i],0,0);

        i=1;
        for (int j=0;j<10;j++)
        {
            y1[j]=y[G[i]+j];
        }
        katsuura_func(y1,fit1,G_nx[i],0,0);

        i=2;
        for (int j=0;j<10;j++)
        {
            y2[j]=y[G[i]+j];
        }
        ackley_func(y2,fit2,G_nx[i],0,0);

        i=3;
        for (int j=0;j<10;j++)
        {
            y3[j]=y[G[i]+j];
        }
        rastrigin_func(y3,fit3,G_nx[i],0,0);

        i=4;
        for (int j=0;j<10;j++)
        {
            y4[j]=y[G[i]+j];
        }
        schwefel_func(y4,fit4,G_nx[i],0,0);

        i=5;
        for (int j=0;j<10;j++)
        {
            y5[j]=y[G[i]+j];
        }
        schaffer_F7_func(y5,fit5,G_nx[i],0,0);

        f[0]=0.0;
        f[0]=0.0;
        f[0] = fit[0] + fit1[0] + fit2[0] + fit3[0] + fit4[0] + fit5[0];
        /*for(i=0;i<cf_num;i++)
        {
            f[0] += fit[i];
        }*/
    }

    /* shift and rotate */
    public void sr_func (double x[], double sr_x[], int nx, double Os[],double Mr[], double sh_rate, int s_flag,int r_flag)
    {
        int i;
        if (s_flag==1)
        {
            if (r_flag==1)
            {
                shiftfunc(x, y, nx, Os);
                /*for(int j=0;j<10;j++)
                {
                    System.out.println("x: " + x[j]);
                    System.out.println("y: " + y[j]);
                    System.out.println("Os: " + Os[j]);
                }*/
                for (i=0; i<nx; i++)//shrink to the original search range
                {
                    y[i]=y[i]*sh_rate;
                }
                rotatefunc(y, sr_x, nx, Mr);
                /*for(int j=0;j<10;j++)
                {
                    System.out.println("sr_x: " + sr_x[j]);
                }*/
            }
            else
            {
                shiftfunc(x, sr_x, nx, Os);
                for (i=0; i<nx; i++)//shrink to the original search range
                {
                    sr_x[i]=sr_x[i]*sh_rate;
                }
            }
        }
        else
        {

            if (r_flag==1)
            {
                for (i=0; i<nx; i++)//shrink to the original search range
                {
                    y[i]=x[i]*sh_rate;
                }
                rotatefunc(y, sr_x, nx, Mr);
            }
            else
                for (i=0; i<nx; i++)//shrink to the original search range
                {
                    sr_x[i]=x[i]*sh_rate;
                }
        }
    }

    public void shiftfunc (double x[], double xshift[], int nx,double Os[])
    {
        int i;
        for (i=0; i<nx; i++)
        {
            xshift[i]=x[i]-Os[i];
        }
    }

    public void rotatefunc (double []x, double []xrot, int nx,double []Mr)
    {
        int i,j;
        for (i=0; i<nx; i++)
        {
            xrot[i]=0;
            for (j=0; j<nx; j++)
            {
                xrot[i]=xrot[i]+x[j]*Mr[i*nx+j];
            }
        }
    }
}
