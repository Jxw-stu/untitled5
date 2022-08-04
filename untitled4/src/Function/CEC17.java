package Function;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class CEC17{
	public double OShift[],M[],y[],z[],x_bound[];
	public int ini_flag,n_flag,func_flag,SS[];
	public double Os[]=new double[100];
	public double Mr[]=new double[100];
	public int S[]=new int[100];
	
	public void cec17_test_func(double x[], double f[], int nx, int mx,int func_num)
	{
		int cf_num=10,j;
		x_bound = new double[nx];
		y = new double[nx];
		z = new double[nx];
		if (ini_flag==1)
		{
			if ((n_flag!=nx)||(func_flag!=func_num))
			{
				ini_flag=0;
			}
		}

		if (ini_flag==0)
		{
//			FILE *fpt;
//			char FileName[256];
			String FileName;
//			free(M);
//			free(OShift);
//			free(y);
//			free(z);
//			free(x_bound);
//			y=(double *)malloc(sizeof(double)  *  nx);
//			z=(double *)malloc(sizeof(double)  *  nx);
//			x_bound=(double *)malloc(sizeof(double)  *  nx);
			
			for (int i=0; i<nx; i++)
				x_bound[i]=100.0;

			if (!(nx==2||nx==10||nx==20||nx==30||nx==50||nx==100))
			{
				System.out.print("\nError: Test functions are only defined for D=2,10,20,30,50,100.\n");
			}
			if (nx==2&&((func_num>=17&&func_num<=22)||(func_num>=29&&func_num<=30)))
			{
				System.out.print("\nError: hf01,hf02,hf03,hf04,hf05,hf06,cf07&cf08 are NOT defined for D=2.\n");
			}
			/* Load Matrix M*/
			//Double M[];// = new Double[nx*nx];
	        BufferedReader bf = null;
	        FileName = "input_data/M_"+func_num+"_D"+nx+".txt";
	        if (func_num<20)
			{
	        	M = new double[nx*nx];
	        	try {
	        		bf = new BufferedReader(new FileReader(FileName));
		            String textLine[];
		            String str = "";
		            int jj = 0;
		            while ((str = bf.readLine()) != null) {
		            	String a[] = str.trim().split("  ");
	                    for (int i = 0; i < a.length; i++) {
	                        M[jj] = Double.parseDouble(a[i].trim());
	                        jj++;
	                    }
	                    if(jj >= nx*nx) break;
		            }
		            bf.close();
	        	}catch (IOException e) {
	                e.printStackTrace();
	                return ;
	            }
			} else {
				M = new double[cf_num*nx*nx];
				int jj = 0;
				try {
					bf = new BufferedReader(new FileReader(FileName));
		            String textLine[];
		            String str = "";
		            while ((str = bf.readLine()) != null) {
		            	String a[] = str.trim().split("  ");
	                    for (int i = 0; i < a.length; i++) {
	                        M[jj] = Double.parseDouble(a[i].trim());
	                        jj++;
	                    }
	                    if(jj >= cf_num*nx*nx) break;
		            }
		            bf.close();
				}catch (IOException e) {
	                e.printStackTrace();
	                return ;
	            }
			}
	        
			
			/* Load shift_data */
	        //Double OShift[];// = new Double[nx*nx];
	        bf = null;
	        FileName = "input_data/shift_data_"+func_num+".txt";
	        
			if (func_num<20){
				OShift = new double[nx*cf_num];
				int jj = 0;
				try {
					bf = new BufferedReader(new FileReader(FileName));
		            String textLine[];
		            String str = "";
		            while ((str = bf.readLine()) != null) {
		            	String a[] = str.trim().split("  ");
	                    for (int i = 0; i < a.length; i++) {
	                    	OShift[jj] = Double.parseDouble(a[i].trim());
	                    	jj++;
	                    }
	                    if(jj >= nx) break;
		            }
		            bf.close();
		            
				}catch (IOException e) {
	                e.printStackTrace();
	                return ;
	            }
			} else {
				OShift = new double[cf_num*nx];
				int jj = 0;
				try {
					bf = new BufferedReader(new FileReader(FileName));
		            String textLine[];
		            String str = "";
		            while ((str = bf.readLine()) != null) {
		            	String a[] = str.trim().split("  ");
	                    for (int i = 0; i < a.length; i++) {
	                    	OShift[jj] = Double.parseDouble(a[i].trim());
	                    	jj++;
	                    }
	                    if(jj >= (cf_num)*nx) break;
		            }
		            bf.close();
				}catch (IOException e) {
	                e.printStackTrace();
	                return ;
	            }
			}


			/* Load Shuffle_data */
			//Double SS[];// = new Double[nx*nx];
			bf = null;
	        
	        if (func_num>=11&&func_num<=20){
	        	FileName = "input_data/shuffle_data_"+func_num+"_D"+nx+".txt";
	        	SS = new int[nx];
	        	int jj = 0;
	        	try {
	        		bf = new BufferedReader(new FileReader(FileName));
		            String textLine[];
		            String str = "";
		            while ((str = bf.readLine()) != null) {
		            	String a[] = str.trim().split("  ");
		                for (int i = 0; i < a.length; i++) {
		                	SS[jj] = Integer.parseInt(a[i].trim());
		                	jj++;
		                }
		                if(jj >= nx) break;
		            }
		            bf.close();
				}catch (IOException e) {
	                e.printStackTrace();
	                return ;
	            }
			} else if (func_num==29||func_num==30){
				FileName = "input_data/shuffle_data_"+func_num+"_D"+nx+".txt";
				SS = new int[nx*cf_num];
				int jj = 0;
				try {
					bf = new BufferedReader(new FileReader(FileName));
		            String textLine[];
		            String str = "";
		            while ((str = bf.readLine()) != null) {
		            	String a[] = str.trim().split("  ");
		                for (int i = 0; i < a.length; i++) {
		                	SS[jj] = Integer.parseInt(a[i].trim());
		                	jj++;
		                }
		                if(jj >= nx*cf_num) break;
		            }
		            bf.close();
				}catch (IOException e) {
	                e.printStackTrace();
	                return ;
	            }
			}
			
			n_flag=nx;
			func_flag=func_num;
			ini_flag=1;
			//printf("Function has been initialized!\n");
		}


		for (int i = 0; i < mx; i++)
		{
			switch(func_num)
			{
			case 1:	
				//bent_cigar_func(&x[i*nx],&f[i],nx,OShift,M,1,1);
				f[i]+=100.0;
				break;
			case 2:	
				//sum_diff_pow_func(&x[i*nx],&f[i],nx,OShift,M,1,1);
				f[i]+=200.0;
				//printf("\nError: This function (F2) has been deleted\n");
				break;
			case 3:	
				//zakharov_func(&x[i*nx],&f[i],nx,OShift,M,1,1);
				f[i]+=300.0;
				break;
			case 4:	
				//rosenbrock_func(&x[i*nx],&f[i],nx,OShift,M,1,1);
				f[i]+=400.0;
				break;
			case 5:
				//rastrigin_func(&x[i*nx],&f[i],nx,OShift,M,1,1);
				f[i]+=500.0;
				break;
			case 6:
				//schaffer_F7_func(&x[i*nx],&f[i],nx,OShift,M,1,1);
				f[i]+=600.0;
				break;
			case 7:	
				//bi_rastrigin_func(&x[i*nx],&f[i],nx,OShift,M,1,1);
				f[i]+=700.0;
				break;
			case 8:	
				//step_rastrigin_func(&x[i*nx],&f[i],nx,OShift,M,1,1);
				f[i]+=800.0;
				break;
			case 9:	
				//levy_func(&x[i*nx],&f[i],nx,OShift,M,1,1);
				f[i]+=900.0;
				break;
			case 10:	
				//schwefel_func(&x[i*nx],&f[i],nx,OShift,M,1,1);
				schwefel_func(x,f,nx,OShift,M,1,1);
				f[i]+=1000.0;
				break;
			case 11:	
				//hf01(&x[i*nx],&f[i],nx,OShift,M,SS,1,1);
				f[i]+=1100.0;
				break;
			case 12:	
				//hf02(&x[i*nx],&f[i],nx,OShift,M,SS,1,1);
				f[i]+=1200.0;
				break;
			case 13:	
				//hf03(&x[i*nx],&f[i],nx,OShift,M,SS,1,1);
				f[i]+=1300.0;
				break;
			case 14:	
				//hf04(&x[i*nx],&f[i],nx,OShift,M,SS,1,1);
				f[i]+=1400.0;
				break;
			case 15:	
				//hf05(&x[i*nx],&f[i],nx,OShift,M,SS,1,1);
				f[i]+=1500.0;
				break;
			case 16:	
				//hf06(&x[i*nx],&f[i],nx,OShift,M,SS,1,1);
				f[i]+=1600.0;
				break;
			case 17:	
				//hf07(&x[i*nx],&f[i],nx,OShift,M,SS,1,1);
				f[i]+=1700.0;
				break;
			case 18:	
				//hf08(&x[i*nx],&f[i],nx,OShift,M,SS,1,1);
				f[i]+=1800.0;
				break;
			case 19:	
				//hf09(&x[i*nx],&f[i],nx,OShift,M,SS,1,1);
				f[i]+=1900.0;
				break;
			case 20:	
				//hf10(&x[i*nx],&f[i],nx,OShift,M,SS,1,1);
				f[i]+=2000.0;
				break;
			case 21:	
				//cf01(&x[i*nx],&f[i],nx,OShift,M,1);
				f[i]+=2100.0;
				break;
			case 22:	
				//cf02(&x[i*nx],&f[i],nx,OShift,M,1);
				f[i]+=2200.0;
				break;
			case 23:	
				//cf03(&x[i*nx],&f[i],nx,OShift,M,1);
				f[i]+=2300.0;
				break;
			case 24:	
				//cf04(&x[i*nx],&f[i],nx,OShift,M,1);
				f[i]+=2400.0;
				break;
			case 25:	
				//cf05(&x[i*nx],&f[i],nx,OShift,M,1);
				f[i]+=2500.0;
				break;
			case 26:
				//cf06(&x[i*nx],&f[i],nx,OShift,M,1);
				f[i]+=2600.0;
				break;
			case 27:
				//cf07(&x[i*nx],&f[i],nx,OShift,M,1);
				f[i]+=2700.0;
				break;
			case 28:
				//cf08(&x[i*nx],&f[i],nx,OShift,M,1);
				f[i]+=2800.0;
				break;
			case 29:
				//cf09(&x[i*nx],&f[i],nx,OShift,M,SS,1);
				f[i]+=2900.0;
				break;
			case 30:
				//cf10(&x[i*nx],&f[i],nx,OShift,M,SS,1);
				f[i]+=3000.0;
				break;
			default:
				System.out.print("\nError: There are only 30 test functions in this test suite!\n");
				f[i] = 0.0;
				break;
			}
			
		}
	}
	
	/* Schwefel's  f10 */
	//void schwefel_func (double *x, double *f, int nx, double *Os,double *Mr,int s_flag, int r_flag) /* Schwefel's  */
    public void schwefel_func(double x[], double f[], int nx, double Os[],double Mr[], int s_flag, int r_flag)
    {
        int i;
        double tmp;
        f[0]=0.0;
        double z[]=new double[nx];
        sr_func(x, z, nx, Os, Mr, 1000.0/100.0, s_flag, r_flag); /* shift and rotate */

        for (i=0; i<nx; i++)
        {
            z[i] += 4.209687462275036e+002;
            if (z[i]>500)
            {
                //f[0]-=(500.0-fmod(z[i],500))*sin(pow(500.0-fmod(z[i],500),0.5));
                f[0]-=(500.0-(z[i]%500))*Math.sin(Math.pow(500.0-(z[i]%500),0.5));
                tmp=(z[i]-500.0)/100;
                f[0]+= tmp*tmp/nx;
            }
            else if (z[i]<-500)
            {
                //f[0]-=(-500.0+fmod(Math.abs(z[i]),500))*sin(pow(500.0-fmod(Math.abs(z[i]),500),0.5));
                f[0]-=(-500.0+(Math.abs(z[i])%500))*Math.sin(Math.pow(500.0-(Math.abs(z[i])%500),0.5));
                tmp=(z[i]+500.0)/100;
                f[0]+= tmp*tmp/nx;
            }
            else
                f[0]-=z[i]*Math.sin(Math.pow(Math.abs(z[i]),0.5));
        }
        f[0] +=4.189828872724338e+002*nx;
    }
    
    /* shift and rotate */
    void sr_func (double x[], double sr_x[], int nx, double Os[],double Mr[], double sh_rate, int s_flag,int r_flag) 
    {
    	int i;
    	if (s_flag==1)
    	{
    		if (r_flag==1)
    		{	
    			shiftfunc(x, y, nx, Os);
//    			for(int j=0;j<10;j++)
//                {
//                    printf("x: %f " ,x[j]);
//                    printf("y: %f " ,y[j]);
//                    printf("Mr: %f " ,Mr[j]);
//                    printf("Os: %f \n" ,Os[j]);
//                }
    			for (i=0; i<nx; i++)//shrink to the original search range
    			{
    				y[i]=y[i]*sh_rate;
    			}
    			rotatefunc(y, sr_x, nx, Mr);
    			for(int j=0;j<10;j++)
                {
                    //printf("sr_x: %f " ,sr_x[j]);
                }
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
    
    void shiftfunc (double x[], double xshift[], int nx,double Os[])
    {
    	int i;
        for (i=0; i<nx; i++)
        {
            xshift[i]=x[i]-Os[i];
        }
    }

    void rotatefunc (double x[], double xrot[], int nx,double Mr[])
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
