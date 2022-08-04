import Feedback.NonLinearMVModel;
import Feedback.Parameters;
import Function.Function_s;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import Function.CEC2017;
import Function.CEC17;
import Tang.TSAModify;

public class TSA {

    double varsMinValue[];//变量最小值  Variable minimum
    double varsMaxValue[];//变量最大值  Variable maximum
    
    private int dim = 0;

    private double[] lb;

    private double[] ub;

    //the intensification/exploration probability
    double Pswitch = 0.3;

    //the escape procedure probability
    double Pesc = 0.8;
    //population size
    int nbr_Agent = 20;

    //number of function evaluations
//    int MAX_FES = 50000;
    int MAX_FES = 50000;//50000;

    //current iteration
    int FES = 0;


    public double getObjectiveValue(double x[]) {
        Function_s function = new Function_s();
        return function.GetObjectiveValue(x);
    }


    public double sign(double num) {
        if (num < 0) {
            return -1;
        } else if (num > 0) {
            return 1;
        } else return 0;
    }


    public double norm(double[] nums) {
        double res = 0;
        for (int i = 0; i < nums.length; i++) {
            res = res + nums[i] * nums[i];
        }
        return Math.sqrt(res);
    }


    public boolean isequal(double[] s1, double[] s2) {
        if (s1.length != s2.length) return false;
        for (int i = 0; i < s1.length; i++) {
            double diff = s1[i] - s2[i];
            if (diff != 0) return false;
        }
        return true;
    }


    public int[] find(boolean[] nums) {
        int k = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == true) k++;
        }
        int res[] = new int[k];
        for (int i = 0; i < k; i++) {
            if (nums[i] == false) {
                res[i] = i + 1;
            }
        }

        return res;
    }


    public int randi(int imax) {
        return (int) (Math.random() * imax + 1);
    }
    
    /*
     * name: GetEuDis
     * function: 
     * creator: Chen Xiaohua
     * date: 2022/06/02
     */
    public double GetEuDis(double X1[],double X2[])
    {
    	double a = 0;
    	for(int i = 0; i < X1.length; i++){
    		if(Math.abs(X1[i]-X2[i]) > Parameters.alfa) return 1;
    		a += (X1[i] - X2[i]) * (X1[i] - X2[i]);
    	}
    	return 0;//Math.sqrt(a);
    }
    
    /*判断约束  Judgmental constraints */
    public boolean CheckConstraints(double x[]) {
        if(Parameters.modelName=="PVD"){
            if (x[0]>=1*0.0625 && x[0]<=99*0.0625 && x[1]>=1*0.0625 && x[1]<=99*0.0625 && x[2]>=10 && x[2]<=200 && x[3]>= 10 && x[3]<= 200 && 0.0193*x[2]-x[0] <=0 && 0.00954*x[2]-x[1] <=0 && -Math.PI*x[2]*x[2]*x[3] - (4*Math.PI*Math.pow(x[2],3))/3 +1296000 <=0 && x[3]-240<=0) {
                return true;
            }else{
                return false;
            }
        } else if(Parameters.modelName=="DevilliersGlasser02"){
            for(int i = 0;i < 6;i++){
            	if(x[i] < 0 || x[i] > 60) return false;
            }
            return true;
        }
        if(Parameters.modelName=="CUT"){
            if (x[0]>=400 && x[0]<=500 && x[1]>=0.01 && x[1]<=0.1 && x[2]>=0.01 && x[2]<=0.2 && x[3]>= 0.1 && x[3]<= 0.5 ) {
                return true;
            }else{
                return false;
            }
        }
        if(Parameters.modelName=="EP3"){
            if (x[0]>=35 && x[0]<=210 && x[1]>=130 && x[1]<=325 && x[2]>=125 && x[2]<=315 && x[0]+x[1]+x[2] <= 500 && x[0]+x[1]+x[2] > 499.9) {// x[0]+x[1]+x[2] <= 600+0.001 && && x[0]+x[1]+x[2] <= 600.0001 && x[0]+x[1]+x[2] > 599.933&& x[0]+x[1]+x[2] == 400 && x[0]+x[1]+x[2] <= 400 && x[0]+x[1]+x[2] > 399.9
//    	        if (x[0]>=10 && x[0]<=125 && x[1]>=10 && x[1]<=150 && x[2]>=35 && x[2]<=225 && x[3]>=35 && x[3]<=210 && x[4]>=130 && x[4]<=325  && x[5]>=125 && x[5]<=315 && x[0]+x[1]+x[2]+x[3]+x[4]+x[5]<= 600 && x[0]+x[1]+x[2]+x[3]+x[4]+x[5]> 599.9){//&& x[0]+x[1]+x[2] == 400
//    	        if ( x[0]+x[1]+x[2]+x[3]+x[4]+x[5]<= 700 && x[0]+x[1]+x[2]+x[3]+x[4]+x[5]> 699.9){//&& x[0]+x[1]+x[2] == 400
                return true;
            }else{
                return false;
            }
        }
        if(Parameters.modelName=="EP6"){
            //if (x[0]>=35 && x[0]<=210 && x[1]>=130 && x[1]<=325 && x[2]>=125 && x[2]<=315 ) {//&& x[0]+x[1]+x[2] <= 600.0001 && x[0]+x[1]+x[2] > 599.933&& x[0]+x[1]+x[2] == 400 && x[0]+x[1]+x[2] <= 400 && x[0]+x[1]+x[2] > 399.9
            if (x[0]>=10 && x[0]<=125 && x[1]>=10 && x[1]<=150 && x[2]>=35 && x[2]<=225 && x[3]>=35 && x[3]<=210 && x[4]>=130 && x[4]<=325  && x[5]>=125 && x[5]<=315 && x[0]+x[1]+x[2]+x[3]+x[4]+x[5]<= 600 && x[0]+x[1]+x[2]+x[3]+x[4]+x[5]> 599.9){// && x[0]+x[1]+x[2]+x[3]+x[4]+x[5]<= 900.1 && x[0]+x[1]+x[2]+x[3]+x[4]+x[5]> 500.9){//&& x[0]+x[1]+x[2] == 400
//    	        if ( x[0]+x[1]+x[2]+x[3]+x[4]+x[5]<= 700 && x[0]+x[1]+x[2]+x[3]+x[4]+x[5]> 699.9){//&& x[0]+x[1]+x[2] == 400
                return true;
            }else{
                return false;
            }
        }
        if(Parameters.modelName=="fSH"){
        	for(int i=0;i<x.length;i++){
        		if(x[i]<varsMinValue[i] || x[i]>varsMaxValue[i])
        			return false;
        	}
        	return true;
//            if (x[0]>=0 && x[0]<=3000 && x[1]>=0 && x[1]<=3000 ){//&& ){//&& x[0]+x[1]+x[2] == 400 && x[0]+x[1]+x[2] <= 400 && x[0]+x[1]+x[2] > 399.9
////    	        if (x[0]>=10 && x[0]<=125 && x[1]>=10 && x[1]<=150 && x[2]>=35 && x[2]<=225 && x[3]>=35 && x[3]<=210 && x[4]>=130 && x[4]<=325  && x[5]>=125 && x[5]<=315 && x[0]+x[1]+x[2]+x[3]+x[4]+x[5]<= 600 && x[0]+x[1]+x[2]+x[3]+x[4]+x[5]> 599.9){//&& x[0]+x[1]+x[2] == 400
////    	        if ( x[0]+x[1]+x[2]+x[3]+x[4]+x[5]<= 700 && x[0]+x[1]+x[2]+x[3]+x[4]+x[5]> 699.9){//&& x[0]+x[1]+x[2] == 400
//                return true;
//            }else{
//                return false;
//            }
        }
        if(Parameters.modelName=="fRA"){
        	for(int i=0;i<x.length;i++){
        		if(x[i]<varsMinValue[i] || x[i]>varsMaxValue[i])
        			return false;
        	}
        	return true;
//            if (x[0]>=0 && x[0]<=10.24 && x[1]>=0 && x[1]<=10.24 ){//&& ){//&& x[0]+x[1]+x[2] == 400 && x[0]+x[1]+x[2] <= 400 && x[0]+x[1]+x[2] > 399.9
////    	        if (x[0]>=10 && x[0]<=125 && x[1]>=10 && x[1]<=150 && x[2]>=35 && x[2]<=225 && x[3]>=35 && x[3]<=210 && x[4]>=130 && x[4]<=325  && x[5]>=125 && x[5]<=315 && x[0]+x[1]+x[2]+x[3]+x[4]+x[5]<= 600 && x[0]+x[1]+x[2]+x[3]+x[4]+x[5]> 599.9){//&& x[0]+x[1]+x[2] == 400
////    	        if ( x[0]+x[1]+x[2]+x[3]+x[4]+x[5]<= 700 && x[0]+x[1]+x[2]+x[3]+x[4]+x[5]> 699.9){//&& x[0]+x[1]+x[2] == 400
//                return true;
//            }else{
//                return false;
//            }
        }
        if(Parameters.modelName=="fBR"){
            if (x[0]>=0 && x[0]<=2000 && x[1]>=0 && x[1]<=2000 ){//&& ){//&& x[0]+x[1]+x[2] == 400 && x[0]+x[1]+x[2] <= 400 && x[0]+x[1]+x[2] > 399.9
//    	        if (x[0]>=10 && x[0]<=125 && x[1]>=10 && x[1]<=150 && x[2]>=35 && x[2]<=225 && x[3]>=35 && x[3]<=210 && x[4]>=130 && x[4]<=325  && x[5]>=125 && x[5]<=315 && x[0]+x[1]+x[2]+x[3]+x[4]+x[5]<= 600 && x[0]+x[1]+x[2]+x[3]+x[4]+x[5]> 599.9){//&& x[0]+x[1]+x[2] == 400
//    	        if ( x[0]+x[1]+x[2]+x[3]+x[4]+x[5]<= 700 && x[0]+x[1]+x[2]+x[3]+x[4]+x[5]> 699.9){//&& x[0]+x[1]+x[2] == 400
                return true;
            }else{
                return false;
            }
        }
        if(Parameters.modelName=="fCA"){
            if (x[0]>=0 && x[0]<=6 && x[1]>=0 && x[1]<=6 ){//&& ){//&& x[0]+x[1]+x[2] == 400 && x[0]+x[1]+x[2] <= 400 && x[0]+x[1]+x[2] > 399.9
//    	        if (x[0]>=10 && x[0]<=125 && x[1]>=10 && x[1]<=150 && x[2]>=35 && x[2]<=225 && x[3]>=35 && x[3]<=210 && x[4]>=130 && x[4]<=325  && x[5]>=125 && x[5]<=315 && x[0]+x[1]+x[2]+x[3]+x[4]+x[5]<= 600 && x[0]+x[1]+x[2]+x[3]+x[4]+x[5]> 599.9){//&& x[0]+x[1]+x[2] == 400
//    	        if ( x[0]+x[1]+x[2]+x[3]+x[4]+x[5]<= 700 && x[0]+x[1]+x[2]+x[3]+x[4]+x[5]> 699.9){//&& x[0]+x[1]+x[2] == 400
                return true;
            }else{
                return false;
            }
        }
        if(Parameters.modelName=="fGP"){
            if (x[0]>=0 && x[0]<=3000 && x[1]>=0 && x[1]<=3000 ){//&& ){//&& x[0]+x[1]+x[2] == 400 && x[0]+x[1]+x[2] <= 400 && x[0]+x[1]+x[2] > 399.9
//    	        if (x[0]>=10 && x[0]<=125 && x[1]>=10 && x[1]<=150 && x[2]>=35 && x[2]<=225 && x[3]>=35 && x[3]<=210 && x[4]>=130 && x[4]<=325  && x[5]>=125 && x[5]<=315 && x[0]+x[1]+x[2]+x[3]+x[4]+x[5]<= 600 && x[0]+x[1]+x[2]+x[3]+x[4]+x[5]> 599.9){//&& x[0]+x[1]+x[2] == 400
//    	        if ( x[0]+x[1]+x[2]+x[3]+x[4]+x[5]<= 700 && x[0]+x[1]+x[2]+x[3]+x[4]+x[5]> 699.9){//&& x[0]+x[1]+x[2] == 400
                return true;
            }else{
                return false;
            }
        }
        if(Parameters.modelName=="fHartman"){
            if (x[0]>=0 && x[0]<=10.24 && x[1]>=0 && x[1]<=10.24 && x[2]>=0 && x[2]<=10.24){//&& ){//&& x[0]+x[1]+x[2] == 400 && x[0]+x[1]+x[2] <= 400 && x[0]+x[1]+x[2] > 399.9
//    	        if (x[0]>=10 && x[0]<=125 && x[1]>=10 && x[1]<=150 && x[2]>=35 && x[2]<=225 && x[3]>=35 && x[3]<=210 && x[4]>=130 && x[4]<=325  && x[5]>=125 && x[5]<=315 && x[0]+x[1]+x[2]+x[3]+x[4]+x[5]<= 600 && x[0]+x[1]+x[2]+x[3]+x[4]+x[5]> 599.9){//&& x[0]+x[1]+x[2] == 400
//    	        if ( x[0]+x[1]+x[2]+x[3]+x[4]+x[5]<= 700 && x[0]+x[1]+x[2]+x[3]+x[4]+x[5]> 699.9){//&& x[0]+x[1]+x[2] == 400
                return true;
            }else{
                return false;
            }
        }

        if(Parameters.modelName=="Zohra"){
            if (x[0]>=0 && x[0]<=20 && x[1]>=0 && x[1]<=20 ){//&& ){//&& x[0]+x[1]+x[2] == 400 && x[0]+x[1]+x[2] <= 400 && x[0]+x[1]+x[2] > 399.9
//    	        if (x[0]>=10 && x[0]<=125 && x[1]>=10 && x[1]<=150 && x[2]>=35 && x[2]<=225 && x[3]>=35 && x[3]<=210 && x[4]>=130 && x[4]<=325  && x[5]>=125 && x[5]<=315 && x[0]+x[1]+x[2]+x[3]+x[4]+x[5]<= 600 && x[0]+x[1]+x[2]+x[3]+x[4]+x[5]> 599.9){//&& x[0]+x[1]+x[2] == 400
//    	        if ( x[0]+x[1]+x[2]+x[3]+x[4]+x[5]<= 700 && x[0]+x[1]+x[2]+x[3]+x[4]+x[5]> 699.9){//&& x[0]+x[1]+x[2] == 400
                return true;
            }else{
                return false;
            }
        }
        return false;
    }

    public double[] TSA(double initX[],int dim, double max[], double min[]) {
    	this.dim = dim;
        lb = new double[dim];
        ub = new double[dim];
        this.ub = max;
        this.lb = min;
        double y = 0;
        
        double[] BLO =new double[dim];//cxh add
        for (int i = 0; i < dim; i++) {
        	varsMinValue[i] = min[i];
        	varsMaxValue[i] = max[i];
        }
        //double varsMinValue[];//变量最小值  Variable minimum
        //double varsMaxValue[];//变量最大值  Variable maximum

        Agent[] agents = new Agent[nbr_Agent];
        for (int i = 0; i < nbr_Agent; i++) {
            agents[i] = new Agent();
        }
        double[] X = new double[dim];
        Agent bestAgent = null;


//        %%%%%%initialization%%%%%%%%%%%%%
        for (int i = 0; i < nbr_Agent; i++) {
            double[] J = new double[dim];
            for (int j = 0; j < dim; j++) {
                J[j] = lb[j] + (ub[j] - lb[j]);
            }
            double fitness = getObjectiveValue(J);
            agents[i].position = J;
            agents[i].fitness = fitness;
//            if(getObjectiveValue(J) < getObjectiveValue(initX) && CheckConstraints(J))//cxh add
//            	return J;										//cxh add
            
        }
        for (int i = 0; i < nbr_Agent; i++) {
            for (int j = 0; j < dim; j++) {
                X[j] = lb[j] + (ub[j] - lb[j]) * Math.random();
            }
            double fitness = getObjectiveValue(X);
//            if(getObjectiveValue(X) < getObjectiveValue(initX) && CheckConstraints(X))//cxh add
//            	return X;
//            agents[i].position = X;
            for (int j = 0; j < dim; j++) {
                agents[i].position[j] = X[j];
                agents[i].fitness = fitness;
            }
            FES = FES + 1;

            if (i == 0) {
                bestAgent = agents[0];
            }

            if (agents[i].fitness < bestAgent.fitness) {
                bestAgent = agents[i];
//                bestAgent.fitness = agents[i].fitness;
            }
            //System.out.println("Valopt = "+bestAgent.fitness+ ",FES="+FES);
        }


        while (FES <= MAX_FES) {    //% iteration process
            if (FES > MAX_FES) break;

            double optx[] = new double[dim];
            for (int j = 0; j < nbr_Agent; j++) {
                for (int k = 0; k < dim; k++) {
                    optx[k] = bestAgent.position[k];
                    X[k] = agents[j].position[k];
                }
                if (Math.random() < Pswitch) {
                    double[] B = new double[dim];
                    for (int i = 0; i < dim; i++) {
                        B[i] = X[i];
                    }

                    double teta = Math.random() * Math.PI / 2.1;
                    double step = 1 * sign(Math.random() - 0.5) * norm(optx) * Math.log(1 + 10 * dim / FES);
                    if (isequal(optx, X)) {
                        for (int i = 0; i < dim; i++) {
                            X[i] = X[i] + step * Math.tan(teta) * (Math.random() * optx[i] - X[i]);
                        }
                    } else {
                        for (int i = 0; i < dim; i++) {
                            X[i] = X[i] + step * Math.tan(teta) * (optx[i] - X[i]);
                        }
                    }
//                    if(getObjectiveValue(X) < getObjectiveValue(initX) && CheckConstraints(X))//cxh add
//                    	return X;
                    //select the variable to copied from optx
                    boolean[] nums = new boolean[dim];
                    for (int i = 0; i < dim; i++) {
                        double random = Math.random();
                        if (random <= 0.2) {
                            nums[i] = true;
                        } else {
                            nums[i] = false;
                        }
                    }

                    int[] ind = find(nums);
                    if (ind.length > 0) {
                        for (int i = 0; i < ind.length; i++) {
                            X[ind[i]] = optx[ind[i]];
                        }
                    } else {
                        int newInd = randi(dim);
                        X[newInd - 1] = optx[newInd - 1];
                    }
//                    if(getObjectiveValue(X) < getObjectiveValue(initX) && CheckConstraints(X))//cxh add
//                    	return X;
                } else {
//                    System.out.println("Exploration search");
                    // Exploration search
                    double teta = Math.random() * Math.PI / 2.5;
                    for (int jk = 0; jk < dim; jk++) {
                        if (Math.random() <= (double) 1 / dim) {
                            if (isequal(optx, X)) {
                                double step = 0.1 * sign(Math.random() - 0.5) / Math.log(20 + FES);
                                X[jk] = X[jk] + step * (Math.tan(teta));
                            } else {
                                double[] temp = new double[dim];
                                for (int i = 0; i < dim; i++) {
                                    temp[i] = optx[i] - X[i];
                                }
                                double step = 10 * sign(Math.random() - 0.5) * norm(temp) / Math.log(20 + FES);
                                X[jk] = X[jk] + step * (Math.tan(teta));
                            }
//                            if(getObjectiveValue(X) < getObjectiveValue(initX)  && CheckConstraints(X))//cxh add
//                            	return X;
                        }
                    }
                }

                double[] Xnew = new double[dim];
                for (int i = 0; i < dim; i++) {
                    Xnew[i] = X[i];
                }

                //越界
                for (int i = 0; i < Xnew.length; i++) {
                    if (Xnew[i] > ub[i]) {
                        Xnew[i] = Math.random() * (ub[i] - lb[i]) + lb[i];
                    }
                    if (Xnew[i] < lb[i]) {
                        Xnew[i] = Math.random() * (ub[i] - lb[i]) + lb[i];
                    }
                }
//                if(getObjectiveValue(Xnew) < getObjectiveValue(initX)  && CheckConstraints(Xnew))//cxh add
//                	return Xnew;

                double fitness = getObjectiveValue(Xnew);

                if (fitness < agents[j].fitness) {
                    for (int i = 0; i < dim; i++) {
                        agents[j].position[i] = Xnew[i];
                        agents[j].fitness = fitness;
                    }
                }
                FES = FES + 1;
                if (fitness < bestAgent.fitness) {
                    for (int i = 0; i < dim; i++) {
                        bestAgent.position[i] = Xnew[i];
                        bestAgent.fitness = fitness;
                    }

                    //System.out.println("Valopt = "+bestAgent.fitness+" ,FES="+FES+" ,1 MAX_FES:"+MAX_FES);

                }
                if (FES > MAX_FES) {
                    break;
                }

            }

            if (Math.random() < Pesc) {
                int im = randi(nbr_Agent);
                for (int i = 0; i < dim; i++) {
                    X[i] = agents[im - 1].position[i];
                }
                if (Math.random() < 0.5) {
                    double f1 = sign(0.5 - Math.random());
                    double ro = 15 * f1 * 1 / Math.log(1 + FES);
                    double[] mu = new double[dim];
                    for (int i = 0; i < dim; i++) {
                        mu[i] = Math.random() * (optx[i] - X[i]);
                        X[i] = X[i] + ro * (optx[i] - mu[i]);
                    }
                } else {
                    double teta = Math.random() * Math.PI;
                    for (int i = 0; i < dim; i++) {
                        X[i] = X[i] + Math.tan(teta) * (ub[i] - lb[i]);
                    }
                }
//                if(getObjectiveValue(X) < getObjectiveValue(initX) && CheckConstraints(X))//cxh add
//                	return X;

                double[] Xnew = new double[dim];
                for (int i = 0; i < dim; i++) {
                    Xnew[i] = X[i];
                }

                //越界
                for (int i = 0; i < Xnew.length; i++) {
                    if (Xnew[i] > ub[i]) {
                        Xnew[i] = Math.random() * (ub[i] - lb[i]) + lb[i];
                    }
                    if (Xnew[i] < lb[i]) {
                        Xnew[i] = Math.random() * (ub[i] - lb[i]) + lb[i];
                    }
                }
                
//                if(getObjectiveValue(Xnew) < getObjectiveValue(initX) && CheckConstraints(Xnew)){//cxh add      	
//                	return Xnew;
//                }


                double fitness = getObjectiveValue(Xnew);

                if (fitness < agents[im - 1].fitness) {
                    for (int i = 0; i < dim; i++) {
                        agents[im - 1].position[i] = Xnew[i];
                    }
                    agents[im - 1].fitness = fitness;
                }
                FES = FES + 1;

                if (fitness < bestAgent.fitness) {
                    for (int i = 0; i < dim; i++) {
                        bestAgent.position[i] = Xnew[i];
                        bestAgent.fitness = fitness;
                    }
                    //System.out.println("Valopt = "+bestAgent.fitness+" ,FES="+FES+" ,2 MAX_FES:"+MAX_FES);
                }
            }
        }

        y = bestAgent.fitness;
        if(!CheckConstraints(X))
        	System.out.println("Error.*********************");
        return X;
    }

    public double TSA(int dim, double max[], double min[]) {
        this.dim = dim;
        lb = new double[dim];
        ub = new double[dim];
        this.ub = max;
        this.lb = min;
        double y = 0;
        
        double[] BLO =new double[dim];//cxh add

        Agent[] agents = new Agent[nbr_Agent];
        for (int i = 0; i < nbr_Agent; i++) {
            agents[i] = new Agent();
        }
        double[] X = new double[dim];
        Agent bestAgent = null;


//        %%%%%%initialization%%%%%%%%%%%%%
        for (int i = 0; i < nbr_Agent; i++) {
            double[] J = new double[dim];
            for (int j = 0; j < dim; j++) {
                J[j] = lb[j] + (ub[j] - lb[j]);
            }
            double fitness = getObjectiveValue(J);
            agents[i].position = J;
            agents[i].fitness = fitness;
        }
        for (int i = 0; i < nbr_Agent; i++) {
            for (int j = 0; j < dim; j++) {
                X[j] = lb[j] + (ub[j] - lb[j]) * Math.random();
            }
            double fitness = getObjectiveValue(X);
//            agents[i].position = X;
            for (int j = 0; j < dim; j++) {
                agents[i].position[j] = X[j];
                agents[i].fitness = fitness;
            }
            FES = FES + 1;

            if (i == 0) {
                bestAgent = agents[0];
            }

            if (agents[i].fitness < bestAgent.fitness) {
                bestAgent = agents[i];
//                bestAgent.fitness = agents[i].fitness;
            }
            //System.out.println("Valopt = "+bestAgent.fitness+ ",FES="+FES);
        }


        while (FES <= MAX_FES) {    //% iteration process
//            System.out.println(FES);
            if (FES > MAX_FES) break;

            double optx[] = new double[dim];
            for (int j = 0; j < nbr_Agent; j++) {
                for (int k = 0; k < dim; k++) {
                    optx[k] = bestAgent.position[k];
                    X[k] = agents[j].position[k];
                }
                if (Math.random() < Pswitch) {
                    double[] B = new double[dim];
                    for (int i = 0; i < dim; i++) {
                        B[i] = X[i];
                    }

                    double teta = Math.random() * Math.PI / 2.1;
                    double step = 1 * sign(Math.random() - 0.5) * norm(optx) * Math.log(1 + 10 * dim / FES);
                    if (isequal(optx, X)) {
                        for (int i = 0; i < dim; i++) {
                            X[i] = X[i] + step * Math.tan(teta) * (Math.random() * optx[i] - X[i]);
                        }
                    } else {
                        for (int i = 0; i < dim; i++) {
                            X[i] = X[i] + step * Math.tan(teta) * (optx[i] - X[i]);
                        }
                    }
                    //select the variable to copied from optx
                    boolean[] nums = new boolean[dim];
                    for (int i = 0; i < dim; i++) {
                        double random = Math.random();
                        if (random <= 0.2) {
                            nums[i] = true;
                        } else {
                            nums[i] = false;
                        }
                    }

                    int[] ind = find(nums);
                    if (ind.length > 0) {
                        for (int i = 0; i < ind.length; i++) {
                            X[ind[i]] = optx[ind[i]];
                        }
                    } else {
                        int newInd = randi(dim);
                        X[newInd - 1] = optx[newInd - 1];
//                        for(int i = 0 ;i < ind.length;i++){
//                            X[newInd[i] - 1] = optx[newInd[i] - 1];
//                        }
                    }
                } else {
//                    System.out.println("Exploration search");
                    // Exploration search
                    double teta = Math.random() * Math.PI / 2.5;
                    for (int jk = 0; jk < dim; jk++) {
                        if (Math.random() <= (double) 1 / dim) {
                            if (isequal(optx, X)) {
                                double step = 0.1 * sign(Math.random() - 0.5) / Math.log(20 + FES);
                                X[jk] = X[jk] + step * (Math.tan(teta));
                            } else {
                                double[] temp = new double[dim];
                                for (int i = 0; i < dim; i++) {
                                    temp[i] = optx[i] - X[i];
                                }
                                double step = 10 * sign(Math.random() - 0.5) * norm(temp) / Math.log(20 + FES);
                                X[jk] = X[jk] + step * (Math.tan(teta));
                            }
                        }
                    }
                }

                double[] Xnew = new double[dim];
                for (int i = 0; i < dim; i++) {
                    Xnew[i] = X[i];
                }

                //越界
                for (int i = 0; i < Xnew.length; i++) {
                    if (Xnew[i] > ub[i]) {
                        Xnew[i] = Math.random() * (ub[i] - lb[i]) + lb[i];
                    }
                    if (Xnew[i] < lb[i]) {
                        Xnew[i] = Math.random() * (ub[i] - lb[i]) + lb[i];
                    }
                }

                double fitness = getObjectiveValue(Xnew);

                if (fitness < agents[j].fitness) {
                    for (int i = 0; i < dim; i++) {
                        agents[j].position[i] = Xnew[i];
                        agents[j].fitness = fitness;
                    }
                }
                FES = FES + 1;
                if (fitness < bestAgent.fitness) {
                    for (int i = 0; i < dim; i++) {
                        bestAgent.position[i] = Xnew[i];
                        bestAgent.fitness = fitness;
                    }
//                    for(int i = 0;i < dim ;i++){
//                        System.out.print("x[" + i +"]="+ bestAgent.position[i]+" ,");
//                    }
                    //System.out.println("Valopt = "+bestAgent.fitness+" ,FES="+FES+" ,1 MAX_FES:"+MAX_FES);
//                    for (int i = 0; i < dim; i++) {
//                        System.out.println("x["+i+"]:"+bestAgent.position[i]);
//                    }
                }
                if (FES > MAX_FES) {
                    break;
                }
                
//                if (Math.random() < 0.01) {
//                	NonLinearMVModel NM =new NonLinearMVModel();
//                    NM.SetMaxMinValue(dim, lb, ub);
//                    Parameters.optimalModel = "MIN";
//                    System.out.println("Find BLO pre "+FES);
//                    BLO = NM.Balance(bestAgent.position,lb,ub);
//                    //System.out.println("Find BLO");
//                    double fitness1 = getObjectiveValue(BLO);
//                    if (fitness1 < bestAgent.fitness) {
//                    	for (int i = 0; i < dim; i++) {
//                    		bestAgent.position[i] = BLO[i];
//                    		bestAgent.fitness = fitness1;
//                    	}
//                    	X = bestAgent.position;
//                    }
//                }

            }

            if (Math.random() < Pesc) {
                int im = randi(nbr_Agent);
                for (int i = 0; i < dim; i++) {
                    X[i] = agents[im - 1].position[i];
                }
                if (Math.random() < 0.5) {
                    double f1 = sign(0.5 - Math.random());
                    double ro = 15 * f1 * 1 / Math.log(1 + FES);
                    double[] mu = new double[dim];
                    for (int i = 0; i < dim; i++) {
                        mu[i] = Math.random() * (optx[i] - X[i]);
                        X[i] = X[i] + ro * (optx[i] - mu[i]);
                    }
                } else {
                    double teta = Math.random() * Math.PI;
                    for (int i = 0; i < dim; i++) {
                        X[i] = X[i] + Math.tan(teta) * (ub[i] - lb[i]);
                    }
                }

                double[] Xnew = new double[dim];
                for (int i = 0; i < dim; i++) {
                    Xnew[i] = X[i];
                }

                //越界
                for (int i = 0; i < Xnew.length; i++) {
                    if (Xnew[i] > ub[i]) {
                        Xnew[i] = Math.random() * (ub[i] - lb[i]) + lb[i];
                    }
                    if (Xnew[i] < lb[i]) {
                        Xnew[i] = Math.random() * (ub[i] - lb[i]) + lb[i];
                    }
                }

                double fitness = getObjectiveValue(Xnew);

                if (fitness < agents[im - 1].fitness) {
                    for (int i = 0; i < dim; i++) {
                        agents[im - 1].position[i] = Xnew[i];
                    }
                    agents[im - 1].fitness = fitness;
                }
                FES = FES + 1;

                if (fitness < bestAgent.fitness) {
                    for (int i = 0; i < dim; i++) {
                        bestAgent.position[i] = Xnew[i];
                        bestAgent.fitness = fitness;
                    }
//                    System.out.println("1");
//                    for(int i = 0;i < dim ;i++){
//                        System.out.print("x[" + i +"]="+ bestAgent.position[i]+" ,");
//                    }
                    //System.out.println("Valopt = "+bestAgent.fitness+" ,FES="+FES+" ,2 MAX_FES:"+MAX_FES);
//                    for (int i = 0; i < dim; i++) {
//                        System.out.println("x["+i+"]:"+bestAgent.position[i]);
//                    }
                }
                
//                if (Math.random() < 0.01) {
//                	NonLinearMVModel NM =new NonLinearMVModel();
//                    NM.SetMaxMinValue(dim, lb, ub);
//                    Parameters.optimalModel = "MIN";
//                    System.out.println("Find BLO pre "+FES);
//                    BLO = NM.Balance(bestAgent.position,lb,ub);
//                    //System.out.println("Find BLO");
//                    double fitness1 = getObjectiveValue(BLO);
//                    if (fitness1 < bestAgent.fitness) {
//                    	for (int i = 0; i < dim; i++) {
//                    		bestAgent.position[i] = BLO[i];
//                    		bestAgent.fitness = fitness1;
//                    	}
//                    	X = bestAgent.position;
//                    }
//                }
            }
            
//            NonLinearMVModel NM =new NonLinearMVModel();
//            NM.SetMaxMinValue(dim, lb, ub);
//            Parameters.optimalModel = "MIN";
//            System.out.println("Find BLO pre "+FES);
//            BLO = NM.Balance(bestAgent.position,lb,ub);
//            System.out.println("Find BLO");
//            double fitness1 = getObjectiveValue(BLO);
//            if (fitness1 < bestAgent.fitness) {
//            	for (int i = 0; i < dim; i++) {
//            		bestAgent.position[i] = BLO[i];
//            		bestAgent.fitness = fitness1;
//            	}
//            	X = bestAgent.position;
//            }

        }
//        System.out.println("start NM.");
//        NonLinearMVModel NM =new NonLinearMVModel();
//        NM.SetMaxMinValue(dim, lb, ub);
//        Parameters.optimalModel = "MIN";
//        BLO = NM.Balance(bestAgent.position,lb,ub);
//        //BLO = NM.Balance(bestAgent.position,lb,ub);
//        double fitness1 = getObjectiveValue(BLO);
//        if (fitness1 < bestAgent.fitness) {
//            for (int i = 0; i < dim; i++) {
//                bestAgent.position[i] = BLO[i];
//                bestAgent.fitness = fitness1;
//            }
//            for (int i = 0; i < dim; i++) {
//                System.out.println("x["+i+"]:"+bestAgent.position[i]);
//            }
//        }
//
        y = bestAgent.fitness;
//        System.out.println("Valopt = "+bestAgent.fitness+" ,Balance min");
//
//        //double[] BLO =new double[dim];
//        Parameters.optimalModel = "MAX";
//        Agent bestAgentTMP = agents[0];//bestAgent = agents[0];
//        //BLO = NM.Balance(bestAgent.position,lb,ub);
//        BLO = NM.Balance(bestAgent.position,lb,ub);
//        for (int i = 0; i < dim; i++) {
//            bestAgentTMP.position[i] = BLO[i];
//            bestAgentTMP.fitness = fitness1;
//        }
//        Parameters.optimalModel = "MIN";
//        //BLO = NM.Balance(bestAgentTMP.position,lb,ub);
//        BLO = NM.Balance(bestAgentTMP.position,lb,ub);
//        fitness1 = getObjectiveValue(BLO);
//        if (fitness1 < bestAgent.fitness) {
//            for (int i = 0; i < dim; i++) {
//                bestAgent.position[i] = BLO[i];
//                bestAgent.fitness = fitness1;
//            }
//            for (int i = 0; i < dim; i++) {
//                System.out.println("x["+i+"]:"+bestAgent.position[i]);
//            }
//        }
//        System.out.println("Valopt = "+bestAgent.fitness+" ,Balance no 0");
//        
//        for (int i = 0; i < dim; i++) {
//        	bestAgentTMP.position[i] = 0;
//        }
//        Parameters.optimalModel = "MIN";
//        //BLO = NM.Balance(bestAgentTMP.position,lb,ub);
//        BLO = NM.Balance(bestAgentTMP.position,lb,ub);
//        fitness1 = getObjectiveValue(BLO);
//        if (fitness1 < bestAgent.fitness) {
//            for (int i = 0; i < dim; i++) {
//                bestAgent.position[i] = BLO[i];
//                bestAgent.fitness = fitness1;
//            }
//            for (int i = 0; i < dim; i++) {
//                System.out.println("x["+i+"]:"+bestAgent.position[i]);
//            }
//        }

        y = bestAgent.fitness;
//        System.out.println("Valopt = "+bestAgent.fitness+" ,Balance");
//        //y = bestAgent.fitness;
        for (int i = 0; i < dim; i++) {
            //System.out.println("x["+i+"]:"+bestAgent.position[i]);
        }
//        bestAgent.position[0] = 0;//60.137;
//        bestAgent.position[1] = 0;//1.371;
//        bestAgent.position[2] = 0;
//        bestAgent.position[3] = 0;
//        bestAgent.position[4] = 0;
//        System.out.println("Op:"+getObjectiveValue(bestAgent.position));
        //if(!CheckConstraints(bestAgent.position))
        //	System.out.println("Error.*********************");
        return y;
    }


    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();//获取开始时间  Get start time
        int dim = 2;
        int Runs = 1;
        int Funs = 16;
        double[] max;// = new double[dim];
        double[] min;// = new double[dim];
        //Parameters.moveModel = "NOMOVE";
        Parameters.algoName = "TSA";
        //Parameters.moveModel = "MOVE";
        Parameters.algoName = "ENBALANCE";//"BALANCE";
        
        
        String[] funName = new String[Funs];
        funName = new String[]{"fBR", "fCA", "fGP", "fRA", "fSH", "fHartman","f3","f20","DevilliersGlasser02","CEC2017-f10-d10","CEC2017-f10-d30","CEC2017-f10-d50","CEC2017-f10-d100"};
        for (int nfun = 10; nfun < 11; nfun++) {
        	if (funName[nfun].contains("CEC2017-f10-d")) {
        		if (funName[nfun].contains("CEC2017-f10-d100")) {
        			dim = 100;
        		} else if (funName[nfun].contains("CEC2017-f10-d30")) {
        			dim = 30;
        		} else if (funName[nfun].contains("CEC2017-f10-d50")) {
        			dim = 50;
        		} else if (funName[nfun].contains("CEC2017-f10-d10")) {
        			dim = 10;
        		}
                max = new double[dim];
                min = new double[dim];
                for (int i = 0; i < dim; i++) {
                	if(Parameters.algoName == "TSA"){
                		max[i] = 100;
                		min[i] = -100;
                	} else if(Parameters.algoName == "ENBALANCE"){
                		max[i] = 200;//5.12;//10;//
                		min[i] = 0;//-5.12;//-10;//
                	}
                }
            } else if (funName[nfun].contains("fHartman")) {
                dim = 3;
                max = new double[dim];
                min = new double[dim];
                for (int i = 0; i < dim; i++) {
                	if(Parameters.algoName == "TSA"){
                		max[i] = 1;
                		min[i] = 0;
                	} else if(Parameters.algoName == "ENBALANCE"){
                		max[i] = 1;//5.12;//10;//
                		min[i] = 0;//-5.12;//-10;//
                	}
                }
            } else if(funName[nfun].contains("f3")||funName[nfun].contains("f20")){
                dim = 30;
                max = new double[dim];
                min = new double[dim];
                for (int i = 0; i < dim; i++) {
                	if(Parameters.algoName == "TSA"){
                		max[i] = 10;
                		min[i] = -10;
                	} else if(Parameters.algoName == "ENBALANCE"){
                		max[i] = 20;//5.12;//10;//
                		min[i] = 0;//-5.12;//-10;//
                	}
                }
            } else if(funName[nfun].contains("DevilliersGlasser02")){
                dim = 5;
                max = new double[dim];
                min = new double[dim];
                for (int i = 0; i < dim; i++) {
                	if(Parameters.algoName == "TSA"){
                		max[i] = 60;
                		min[i] = 0;
                	} else if(Parameters.algoName == "ENBALANCE"){
                		max[i] = 60;//5.12;//10;//
                		min[i] = 0;//-5.12;//-10;//
                	}
                }
            }else{
                dim = 2;
                max = new double[dim];
                min = new double[dim];
                if(funName[nfun].contains("fBR")){
                	if(Parameters.algoName == "TSA"){
                		max[0] = 10;max[1] = 15;
                		min[0] = -5;min[1] = 0;
                	} else if(Parameters.algoName == "ENBALANCE"){
                		max[0] = 15;max[1] = 20;
                		min[0] = 0;min[1] = 5;
                	}
                } else if(funName[nfun].contains("fCA")){
                	if(Parameters.algoName == "TSA"){
                		max[0] = 3;max[1] = 2;
                		min[0] = -3;min[1] = -2;
                	} else if(Parameters.algoName == "ENBALANCE"){
                		max[0] = 6;max[1] = 4;
                		min[0] = 0;min[1] = 0;
                	}
                } else if(funName[nfun].contains("fGP")){
                	if(Parameters.algoName == "TSA"){
                		max[0] = 2;max[1] = 2;
                		min[0] = -2;min[1] = -2;
                	} else if(Parameters.algoName == "ENBALANCE"){
                		max[0] = 4;max[1] = 4;
                		min[0] = 0;min[1] = 0;
                	}
                } else if(funName[nfun].contains("fRA")){
                	if(Parameters.algoName == "TSA"){
                		max[0] = 5.12;max[1] = 5.12;
                		min[0] = -5.12;min[1] = -5.12;
                	} else if(Parameters.algoName == "ENBALANCE"){
                		max[0] = 10.24;max[1] = 10.24;
                		min[0] = 0;min[1] = 0;
                	}
                } else if(funName[nfun].contains("fSH")){
                	if(Parameters.algoName == "TSA"){
                		max[0] = 5.12;max[1] = 5.12;
                		min[0] = -5.12;min[1] = -5.12;
                	} else if(Parameters.algoName == "ENBALANCE"){
                		max[0] = 10.24;max[1] = 10.24;
                		min[0] = 0;min[1] = 0;
                	}
                }
            }
            
            System.out.print("\n-------------------------------------------------------\n");
            System.out.print("Function = " + funName[nfun] + " ,Dimension size = " + dim + "\n");
            Parameters.modelName = funName[nfun];
            if(Parameters.algoName == "TSA"){
            	for (int nbex = 0; nbex < Runs; nbex++) {
                    TSA tsa = new TSA();
                    //TSAModify tsa = new TSAModify();
                    //double res[] = tsa.TSA(dim, max, min);
                    double res = tsa.TSA(dim, max, min);
                    //System.out.println("best solution balue = " + tsa.getObjectiveValue(res));
                    System.out.println("best solution balue = " + res + " ,fun = " + Parameters.modelName + " ,nbex = " + nbex);
                }
            } else if(Parameters.algoName == "ENBALANCE"){
            	for (int nbex = 0; nbex < Runs; nbex++) {
            		double[] ub = new double[dim];
                    double[] lb = new double[dim];
                    lb = min;
                    ub = max;
                    System.out.println("start NM.");
                    NonLinearMVModel NM =new NonLinearMVModel();
                    NM.SetMaxMinValue(dim, lb, ub);
                    Parameters.optimalModel = "MIN";
                    double[] BLO = new double[dim];
                    for (int i = 0; i < dim; i++) {
                    	BLO[i] = lb[i] + Math.random() * (ub[i] - lb[i]);//i+9;//10
                    }
                    
                    //start
                    
//                    CEC17 myCEC17 = new CEC17();
//                    int func_num = 10,n=30,m=2;
//                    BufferedReader bf = null;
//                    double x[] = new double[n*m];
//                    double f[] = new double[m];
//                    String FileName = "input_data/shift_data_"+func_num+".txt";
//                    try {
//    	        		bf = new BufferedReader(new FileReader(FileName));
//    		            String textLine[];
//    		            String str = "";
//    		            while ((str = bf.readLine()) != null) {
//    		            	String a[] = str.trim().split("  ");
//    	                    for (int i = 0; i < n; i++) {
//    	                        x[i] = Double.parseDouble(a[i].trim());//+100;;//
//    	                    }
//    		            }
//    		            bf.close();
//    		            for (int j = 0; j < n; j++){
//    						x[1*n+j]=0.0;
//    					}
//    		            myCEC17.cec17_test_func(x, f, n,m,func_num);
//    					for (int j = 0; j < 2; j++)
//    					{
//    						System.out.println("f"+func_num+"(x["+(j+1)+"])="+f[j]);
//    						//printf(" f%d(x[%d]) = %Lf,",func_num,j+1,f[j]);
//    					}
//    	        	}catch (IOException e) {
//    	                e.printStackTrace();
//    	                return ;
//    	            }
                    //end
                    BLO = NM.BalanceX(BLO,lb,ub);
//                    //BLO = NM.Balance(bestAgent.position,lb,ub);
                    double fitness1 = NM.GetObjectiveValue(BLO);//getObjectiveValue(BLO);

            	}
            }
        }
        long endTime = System.currentTimeMillis(); //获取结束时间  Get end time
        System.out.println("Time: " + (endTime - startTime) + "ms");
    }
}
