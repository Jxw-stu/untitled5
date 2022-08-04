package Feedback;

import Function.Function_s;


public class NonLinearMVModel {
    double objectiveValue;//目标函数  Objective Function value
    double curObjective;//当前目标函数的值  The value of the current objective function
    double curValue[];//当前值  The current value
    int varsSum;//变量数量  Number of variables
    double initValue[]; //初始值  Initial value
    double varsMinValue[];//变量最小值  Variable minimum
    double varsMaxValue[];//变量最大值  Variable maximum
    int p;
    int q;
    int maxIter = 20;


    public double GetObjectiveValue() {
        return curObjective;
    }

    /*判断函数值是否降低  Determine whether the function value decreases */
    public boolean CheckCurObjective(double x[]) {
        if (GetObjectiveValue(x) <= curObjective) return true;
        return false;
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
            for(int i = 0;i < 5;i++){
            	if(x[i] < 0 || x[i] > 60) return false;
            }
            return true;
        } else if(Parameters.modelName.contains("CEC2017")){//Parameters.modelName=="CEC2017-f10-d10"){
        	if(Parameters.algoName == "TSA"){
        		for(int i = 0;i < x.length;i++){
                	if(x[i] < -100 || x[i] > 100) return false;
                }
        	} else if(Parameters.algoName == "ENBALANCE"){
        		for(int i = 0;i < x.length;i++){
                	if(x[i] < 0 || x[i] > 200) return false;
                }
        	}
            return true;
        } else if(Parameters.modelName=="f3"){
        	if(Parameters.algoName == "TSA"){
        		for(int i = 0;i < x.length;i++){
                	if(x[i] < -10 || x[i] > 10) return false;
                }
        	} else if(Parameters.algoName == "ENBALANCE"){
        		for(int i = 0;i < x.length;i++){
                	if(x[i] < 0 || x[i] > 20) return false;
                }
        	}
            return true;
        } else if(Parameters.modelName=="f20"){
        	if(Parameters.algoName == "TSA"){
        		for(int i = 0;i < x.length;i++){
                	if(x[i] < -10 || x[i] > 10) return false;
                }
        	} else if(Parameters.algoName == "ENBALANCE"){
        		for(int i = 0;i < x.length;i++){
                	if(x[i] < 0 || x[i] > 20) return false;
                }
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
        	if(Parameters.algoName == "TSA"){
        		for(int i=0;i<x.length;i++){
            		if(x[i]<-5.12 || x[i]>5.12)
            			return false;
            	}
        		return true;
        	} else if(Parameters.algoName == "ENBALANCE"){
        		for(int i=0;i<x.length;i++){
            		if(x[i]<0 || x[i]>10.24)
            			return false;
            	}
        		return true;
        	}
//        	for(int i=0;i<x.length;i++){
//        		if(x[i]<varsMinValue[i] || x[i]>varsMaxValue[i])
//        			return false;
//        	}
//        	return true;
//            if (x[0]>=0 && x[0]<=3000 && x[1]>=0 && x[1]<=3000 ){//&& ){//&& x[0]+x[1]+x[2] == 400 && x[0]+x[1]+x[2] <= 400 && x[0]+x[1]+x[2] > 399.9
////    	        if (x[0]>=10 && x[0]<=125 && x[1]>=10 && x[1]<=150 && x[2]>=35 && x[2]<=225 && x[3]>=35 && x[3]<=210 && x[4]>=130 && x[4]<=325  && x[5]>=125 && x[5]<=315 && x[0]+x[1]+x[2]+x[3]+x[4]+x[5]<= 600 && x[0]+x[1]+x[2]+x[3]+x[4]+x[5]> 599.9){//&& x[0]+x[1]+x[2] == 400
////    	        if ( x[0]+x[1]+x[2]+x[3]+x[4]+x[5]<= 700 && x[0]+x[1]+x[2]+x[3]+x[4]+x[5]> 699.9){//&& x[0]+x[1]+x[2] == 400
//                return true;
//            }else{
//                return false;
//            }
        }
        if(Parameters.modelName=="fRA"){
        	if(Parameters.algoName == "TSA"){
        		for(int i=0;i<x.length;i++){
            		if(x[i]<-5.12 || x[i]>5.12)
            			return false;
            	}
        	} else if(Parameters.algoName == "ENBALANCE"){
        		for(int i=0;i<x.length;i++){
            		if(x[i]<0 || x[i]>10.24)
            			return false;
            	}
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
        	if(Parameters.algoName == "TSA"){
        		if (x[0]<-5 || x[0]>10 || x[1]<0 || x[1]>15 ){//&& ){//&& x[0]+x[1]+x[2] == 400 && x[0]+x[1]+x[2] <= 400 && x[0]+x[1]+x[2] > 399.9
                    return false;
                }
        	} else if(Parameters.algoName == "ENBALANCE"){
        		if (x[0]<0 || x[0]>15 || x[1]<0 || x[1]>15 ){//&& ){//&& x[0]+x[1]+x[2] == 400 && x[0]+x[1]+x[2] <= 400 && x[0]+x[1]+x[2] > 399.9
                    return false;
                }
        	}
        	return true;
        }
        if(Parameters.modelName=="fCA"){
        	if(Parameters.algoName == "TSA"){
        		if (x[0]<-3 || x[0]>3 || x[1]<-2 || x[1]>2 ){
        			return false;
        		}
        	} else if(Parameters.algoName == "ENBALANCE"){
        		if (x[0]<0 || x[0]>6 || x[1]<0 || x[1]>4 ){
        			return false;
        		}
        	}
        	return true;
//            if (x[0]>=0 && x[0]<=6 && x[1]>=0 && x[1]<=6 ){//&& ){//&& x[0]+x[1]+x[2] == 400 && x[0]+x[1]+x[2] <= 400 && x[0]+x[1]+x[2] > 399.9
////    	        if (x[0]>=10 && x[0]<=125 && x[1]>=10 && x[1]<=150 && x[2]>=35 && x[2]<=225 && x[3]>=35 && x[3]<=210 && x[4]>=130 && x[4]<=325  && x[5]>=125 && x[5]<=315 && x[0]+x[1]+x[2]+x[3]+x[4]+x[5]<= 600 && x[0]+x[1]+x[2]+x[3]+x[4]+x[5]> 599.9){//&& x[0]+x[1]+x[2] == 400
////    	        if ( x[0]+x[1]+x[2]+x[3]+x[4]+x[5]<= 700 && x[0]+x[1]+x[2]+x[3]+x[4]+x[5]> 699.9){//&& x[0]+x[1]+x[2] == 400
//                return true;
//            }else{
//                return false;
//            }
        }
        if(Parameters.modelName=="fGP"){
        	if(Parameters.algoName == "TSA"){
        		if ((x[0]<-2 || x[0]>2) || (x[1]<-2 || x[1]>2)){
        			return false;
        		}
        	} else if(Parameters.algoName == "ENBALANCE"){
        		if (x[0]<0 || x[0]>4 || x[1]<0 || x[1]>4){
        			return false;
        		}
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
        if(Parameters.modelName=="fHartman"){
        	if(Parameters.algoName == "TSA" || Parameters.algoName == "ENBALANCE"){
        		if ((x[0]<0 || x[0]>1) || (x[1]<0 || x[1]>1) || (x[2]<0 || x[2]>1)){
        			return false;
        		}
        	}
        	return true;
//            if (x[0]>=0 && x[0]<=10.24 && x[1]>=0 && x[1]<=10.24 && x[2]>=0 && x[2]<=10.24){//&& ){//&& x[0]+x[1]+x[2] == 400 && x[0]+x[1]+x[2] <= 400 && x[0]+x[1]+x[2] > 399.9
////    	        if (x[0]>=10 && x[0]<=125 && x[1]>=10 && x[1]<=150 && x[2]>=35 && x[2]<=225 && x[3]>=35 && x[3]<=210 && x[4]>=130 && x[4]<=325  && x[5]>=125 && x[5]<=315 && x[0]+x[1]+x[2]+x[3]+x[4]+x[5]<= 600 && x[0]+x[1]+x[2]+x[3]+x[4]+x[5]> 599.9){//&& x[0]+x[1]+x[2] == 400
////    	        if ( x[0]+x[1]+x[2]+x[3]+x[4]+x[5]<= 700 && x[0]+x[1]+x[2]+x[3]+x[4]+x[5]> 699.9){//&& x[0]+x[1]+x[2] == 400
//                return true;
//            }else{
//                return false;
//            }
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
    	/*
        //if(-(x[0]-1)*(x[0]-1)+x[1]>=0 && (-2)*x[0]-3*(x[1])+6>=0 && x[0]>=0&&x[0]<=10 && x[1]>=0 &&x[1]<=10){
        //if(-1*(x[0]-5)*(x[0]-5)-(x[1]-5)*(x[1]-5)+100<=0 && (x[0]-6)*(x[0]-6)+(x[1]-5)*(x[1]-5)-82.81<=0 && x[0]>=13 && x[0]<=100 && x[1]>=0 && x[1] <=100 ){
        //if(x[1]-100-100*Math.sin(0.1*x[0]-0.5)<=0 && (50/(x[0]+0.1))-40-(x[1]-100)<=0 && x[0]>=0 && x[0]<=100 && x[1]>=0 && x[1]<=200){
        if (x[0]>=35 && x[0]<=210 && x[1]>=130 && x[1]<=325 && x[2]>=125 && x[2]<=315 ){//&& x[0]+x[1]+x[2] == 400 && x[0]+x[1]+x[2] <= 400 && x[0]+x[1]+x[2] > 399.9
//        if (x[0]>=10 && x[0]<=125 && x[1]>=10 && x[1]<=150 && x[2]>=35 && x[2]<=225 && x[3]>=35 && x[3]<=210 && x[4]>=130 && x[4]<=325  && x[5]>=125 && x[5]<=315 && x[0]+x[1]+x[2]+x[3]+x[4]+x[5]<= 600 && x[0]+x[1]+x[2]+x[3]+x[4]+x[5]> 599.9){//&& x[0]+x[1]+x[2] == 400
//        if ( x[0]+x[1]+x[2]+x[3]+x[4]+x[5]<= 700 && x[0]+x[1]+x[2]+x[3]+x[4]+x[5]> 699.9){//&& x[0]+x[1]+x[2] == 400
            return true;
        }else{
            return false;
        }
        */

//        for (int i = 0; i < varsSum; i++) {
//
//            /*多变量无约束测试*/
//            if (x[i] > 20 || x[i] < 0) {
//                return false;
//            }
//            /*单变量无约束测试 */
//            /*if(x[i]<0 && x[i]>10){
//                return false;
//            }*/
//        }
//        return true;
    }

    /*获取初始点   Get initial point */
    public void SetInitValue(int inVarsSum, double initValue[]) {
        varsSum = inVarsSum;
        this.initValue = new double[inVarsSum];
        for (int i = 0; i < inVarsSum; i++) {
            this.initValue[i] = initValue[i];
            this.initValue[i] = initValue[i];
        }
    }


    /*获取函数值   Get function value */
    public double GetObjectiveValue(double x[])
    {
        Function_s res = new Function_s();
        return res.GetObjectiveValue(x);

    }
    
    

    /*获取最大最小值域   Get the min and max fields */
    public void SetMaxMinValue(int inVarsSum, double varsMinValue[], double varsMaxValue[]) {
        varsSum = inVarsSum;
        this.varsMinValue = new double[inVarsSum];
        this.varsMaxValue = new double[inVarsSum];
        for (int i = 0; i < inVarsSum; i++) {
            this.varsMinValue[i] = varsMinValue[i];
            this.varsMaxValue[i] = varsMaxValue[i];
        }
    }

    /*判断是否为可行解   Determine whether it is a feasible solution */
    public boolean JudgeIfFeasibleSolution(double x[]) {
        if (CheckConstraints(x)) return true;
        return false;
    }


    /*判断满足块内最大最小值   Judging to meet the maximum and minimum values in the block */
    public boolean CheckBlockMinMax(double x[], int vars, double min[], double max[]) {
        for (int i = 0; i < vars; i++) {
            if (x[i] < min[i]) {
                return false;
            }
            if (x[i] > max[i]) {
                return false;
            }
        }
        return true;
    }


    /*寻找初始一个可行解  Find an initial feasible solution */
    public boolean FindInitSolution(double init[], int versum, int curver, double min[], double max[]) {

        if (CheckConstraints(init) && CheckBlockMinMax(init, versum, min, max)) {
            return true;
        }
        if (versum > curver) {
            double coreinit = init[curver];
            if (init[curver] < min[curver]) {
                while (init[curver] < max[curver]) {
                    if (init[curver] < min[curver]) init[curver] = min[curver] - 0.01;
                    init[curver] += 0.01;
                    if (FindInitSolution(init, versum, curver + 1, min, max)) {
                        return true;
                    }
                }
                init[curver] = coreinit;
            } else if (init[curver] > max[curver]) {
                while (init[curver] > min[curver]) {
                    if (init[curver] > max[curver]) init[curver] = max[curver] + 0.01;
                    init[curver] -= 0.01;
                    if (FindInitSolution(init, versum, curver + 1, min, max)) {
                        return true;
                    }
                }
                init[curver] = coreinit;
            } else {

                //向左找  Look Left
                while (init[curver] >= min[curver]) {
                    init[curver] -= 0.01;
                    if (init[curver] < min[curver]) {
                        init[curver] += 0.01;
                        break;
                    }
                    if (FindInitSolution(init, versum, curver + 1, min, max)) {
                        return true;
                    }
                }

                init[curver] = coreinit;
                //向右找  Look Right
                while (init[curver] <= max[curver]) {
                    init[curver] += 0.01;
                    if (init[curver] > max[curver]) {
                        init[curver] -= 0.01;
                        break;
                    }
                    if (FindInitSolution(init, versum, curver + 1, min, max)) {
                        return true;
                    }
                }
                init[curver] = coreinit;
            }
        }
        return false;
    }
    
    /*求决策变量x1x2可行解*/
    /*Find the feasible solution for the decision variable x1x2*/
    /*
     * name:GetFeasibleSolution
     * function:Find the feasible solution for the decision variable x1x2
     * creator:Ye yaning
     * date:2021/1/28
     */
    public boolean GetFeasibleSolution(int varsSum, double x[], int direction, int index, double ret[], double Min[], double Max[]) {
        double temp[] = new double[varsSum];
        boolean find = false;
        for (int i = 0; i < varsSum; i++) {
            temp[i] = x[i];
        }
        //初始值在最大边界和最下边界外求得可行解
        /* The initial value is outside the maximum and lower bounds to obtain feasible solutions */
        if (temp[index] > Max[index]) {
            ret[0] = Max[index];
            temp[index] = ret[0];
        }
        if (temp[index] < Min[index]) {
            ret[0] = Min[index];
            temp[index] = ret[0];
        }

        if (JudgeIfFeasibleSolution(temp)) {
            ret[0] = temp[index];
            return true;
        }

        //0从右向左<---,则initX尽量靠右
        /* '0' from right to left <---, then initX is as far to the right as possible */
        if (direction == 0) {
            find = false;
            while (temp[index] <= Max[index]) {
                // initX大于0，向右靠近  InitX is greater than 0, close to the right
                if (temp[index] > 0) temp[index] *= 2;
                    //initX等于0时，向右靠近 When initX is equal to 0, move to the right
                else if (temp[index] == 0) temp[index] = 0.01;
                    //initX小于0的情况，当接近0时，设置为0  When initX is less than 0, when it is close to 0, it is set to 0
                else if (temp[index] >= -0.000001 && temp[index] < 0) temp[index] = 0;
                    //initX小于0的情况  When initX is less than 0
                else if (temp[index] < -0.000001) temp[index] /= -2;

                if (JudgeIfFeasibleSolution(temp)) {
                    find = true;
                    break;
                }
            }
            if (find) {
                ret[0] = temp[index];
                return true;
            }
            temp[index] = Max[index];
            if (JudgeIfFeasibleSolution(temp)) {
                ret[0] = Max[index];
                return true;
            }
            //最大值都无法满足要求，则从右向左搜索
            /*The maximum value cannot meet the requirements, then search from right to left*/
            while (temp[index] >= Min[index] + 0.000000000001) {
                if (temp[index] > 0) temp[index] /= 2;//initX>0,向左靠近  Close to the left
                else if (temp[index] < 0.000001 && temp[index] > 0) temp[index] = 0;
                else if (temp[index] == 0) temp[index] = -0.01;//initX==0,向左靠近  Close to the left
                else if (temp[index] < 0) temp[index] *= 2;//initX<0,向左靠近  Close to the left
                if (JudgeIfFeasibleSolution(temp)) {
                    find = true;
                    break;
                }
            }
            if (find) {
                ret[0] = temp[index];
                return true;
            }
            temp[index] = Min[index];
            if (JudgeIfFeasibleSolution(temp)) {
                ret[0] = temp[index];
                return true;
            }
        }

        //1从左向右---->,initX尽量靠左
        /* '1' From left to right---->, initX try to be left as far as possible */
        if (direction == 1) {
            find = false;
            while (temp[index] >= Min[index] + 0.000000000001) {
                if (temp[index] > 0) temp[index] /= 2;//initX>0,向左靠近 Close to the left
                else if (temp[index] < 0.000001 && temp[index] > 0) temp[index] = 0;//initX>0,向左靠近  Close to the left
                else if (temp[index] == 0) temp[index] = -0.01;//initX==0,向左靠近  Close to the left
                else if (temp[index] < -0.000001) temp[index] *= 2;//initX<0,向左靠近  Close to the left
                //else if(initX >= -0.000001 && initX < 0) initX = 0;//initX in [-0.000001,0],向左靠近  Close to the left
                if (JudgeIfFeasibleSolution(temp)) {
                    find = true;
                    break;
                }
            }
            if (find) {
                ret[0] = temp[index];
                return true;
            }
            temp[index] = Min[index];
            if (JudgeIfFeasibleSolution(temp)) {
                ret[0] = Min[index];
                return true;
            }

            //最小值都无法满足要求，则从左向右搜索
            /* If the minimum value cannot meet the requirements, search from left to right */
            temp[index] = Min[index];
            while (temp[index] <= Max[index]) {
                if (temp[index] > 0) temp[index] *= 2;//initX>0，向右靠近 Close to the right
                else if (temp[index] > -0.000001 && temp[index] < 0) temp[index] = 0;//initX>0，向右靠近 Close to the right
                else if (temp[index] == 0) temp[index] = 0.01;//initX==0，向右靠近 Close to the right
                else if (temp[index] < 0) temp[index] /= 2;//initX<0，向右靠近 Close to the right
                if (JudgeIfFeasibleSolution(temp)) {
                    find = true;
                    break;
                }
            }
            if (find) {
                ret[0] = temp[index];
                return true;
            }
            temp[index] = Max[index];
            if (JudgeIfFeasibleSolution(temp)) {
                ret[0] = Max[index];
                return true;
            }
        }
        return false;
    }

    /*根据x1求解一个可行解*/
    /*Find the feasible solution from the initial variable x1*/
    /*
     * name:GetFeasibleSolutionX
     * function:Find the feasible solution for the decision variable x1x2
     * creator: Chen Xiaohua
     * date: 2022/6/2
     */
    public boolean GetFeasibleSolutionX(double X[], int direction, double retX[][], double Min[], double Max[]) {
    	int varsSum = X.length;
        double ret[] = new double[1];
        for(int i = 0; i < 2; i++){
        	for (int index = 0; index < varsSum; index++) {
            	retX[i][index] = X[index];
            }
        }
        
        for (int index = 0; index < varsSum; index++) {
        	if(GetFeasibleSolution(varsSum, X, direction, index, ret, Min, Max)){
        		retX[0][index] = ret[0];
        		return true;
        	}
        }

        return false;
    	
//    	int varsSum = X.length;
//    	double temp[] = new double[varsSum];
//        boolean find = false;
//        for (int i = 0; i < varsSum; i++) {
//            temp[i] = X[i];
//        }
//        
//        
//        //初始值在最大边界和最下边界外求得可行解
//        /* The initial value is outside the maximum and lower bounds to obtain feasible solutions */
//        for (int i = 0; i < varsSum; i++) {
//        	if (temp[i] > Max[i]) {
//                temp[i] = Max[i];
//            }
//            if (temp[i] < Min[i]) {
//                temp[i] = Min[i];
//            }
//            if (JudgeIfFeasibleSolution(temp)) {
//                ret[0] = temp;
//                return true;
//            }
//        }
//        
//
//        //0从右向左<---,则initX尽量靠右
//        /* '0' from right to left <---, then initX is as far to the right as possible */
//        if (direction == 0) {
//            find = false;
//            for(int index = 0; index < varsSum; index++){
//            	while (temp[index] <= Max[index]) {
//                    // initX大于0，向右靠近  InitX is greater than 0, close to the right
//                    if (temp[index] > 0) temp[index] *= 2;
//                        //initX等于0时，向右靠近 When initX is equal to 0, move to the right
//                    else if (temp[index] == 0) temp[index] = 0.01;
//                        //initX小于0的情况，当接近0时，设置为0  When initX is less than 0, when it is close to 0, it is set to 0
//                    else if (temp[index] >= -0.000001 && temp[index] < 0) temp[index] = 0;
//                        //initX小于0的情况  When initX is less than 0
//                    else if (temp[index] < -0.000001) temp[index] /= -2;
//
//                    if (JudgeIfFeasibleSolution(temp)) {
//                        find = true;
//                        break;
//                    }
//                }
//            	if (find) {
//                    ret[0] = temp;
//                    return true;
//                }
//            }
//            
//            for(int index = 0; index < varsSum; index++){
//            	temp[index] = Max[index];
//            }
//            
//            if (JudgeIfFeasibleSolution(temp)) {
//                ret[0] = Max;
//                return true;
//            }
//            //最大值都无法满足要求，则从右向左搜索
//            /*The maximum value cannot meet the requirements, then search from right to left*/
//            for(int index = 0; index < varsSum; index++){
//            	while (temp[index] >= Min[index] + 0.000000000001) {
//                    if (temp[index] > 0) temp[index] /= 2;//initX>0,向左靠近  Close to the left
//                    else if (temp[index] < 0.000001 && temp[index] > 0) temp[index] = 0;
//                    else if (temp[index] == 0) temp[index] = -0.01;//initX==0,向左靠近  Close to the left
//                    else if (temp[index] < 0) temp[index] *= 2;//initX<0,向左靠近  Close to the left
//                    if (JudgeIfFeasibleSolution(temp)) {
//                        find = true;
//                        break;
//                    }
//                }
//                if (find) {
//                    ret[0] = temp;
//                    return true;
//                }
//            }
//            for(int index = 0; index < varsSum; index++){
//            	temp[index] = Min[index];
//            }
//            
//            if (JudgeIfFeasibleSolution(temp)) {
//                ret[0] = temp;//[index];
//                return true;
//            }
//        }
//
//        //1从左向右---->,initX尽量靠左
//        /* '1' From left to right---->, initX try to be left as far as possible */
//        if (direction == 1) {
//            find = false;
//            for(int index = 0; index < varsSum; index++){
//            	while (temp[index] >= Min[index] + 0.000000000001) {
//                    if (temp[index] > 0) temp[index] /= 2;//initX>0,向左靠近 Close to the left
//                    else if (temp[index] < 0.000001 && temp[index] > 0) temp[index] = 0;//initX>0,向左靠近  Close to the left
//                    else if (temp[index] == 0) temp[index] = -0.01;//initX==0,向左靠近  Close to the left
//                    else if (temp[index] < -0.000001) temp[index] *= 2;//initX<0,向左靠近  Close to the left
//                    //else if(initX >= -0.000001 && initX < 0) initX = 0;//initX in [-0.000001,0],向左靠近  Close to the left
//                    if (JudgeIfFeasibleSolution(temp)) {
//                        find = true;
//                        break;
//                    }
//                }
//                if (find) {
//                    ret[0] = temp;//[index];
//                    return true;
//                }
//            }
//            for(int index = 0; index < varsSum; index++){
//            	temp[index] = Min[index];
//            }
//            
//            if (JudgeIfFeasibleSolution(temp)) {
//                ret[0] = Min;//[index];
//                return true;
//            }
//
//            //最小值都无法满足要求，则从左向右搜索
//            /* If the minimum value cannot meet the requirements, search from left to right */
//            for(int index = 0; index < varsSum; index++){
//            	temp[index] = Min[index];
//            }
//            for(int index = 0; index < varsSum; index++){
//            	while (temp[index] <= Max[index]) {
//                    if (temp[index] > 0) temp[index] *= 2;//initX>0，向右靠近 Close to the right
//                    else if (temp[index] > -0.000001 && temp[index] < 0) temp[index] = 0;//initX>0，向右靠近 Close to the right
//                    else if (temp[index] == 0) temp[index] = 0.01;//initX==0，向右靠近 Close to the right
//                    else if (temp[index] < 0) temp[index] /= 2;//initX<0，向右靠近 Close to the right
//                    if (JudgeIfFeasibleSolution(temp)) {
//                        find = true;
//                        break;
//                    }
//                }
//                if (find) {
//                    ret[0] = temp;//[index];
//                    return true;
//                }
//            }
//            for(int index = 0; index < varsSum; index++){
//            	temp[index] = Max[index];
//            }
//            
//            if (JudgeIfFeasibleSolution(temp)) {
//                ret[0] = Max;//[index];
//                return true;
//            }
//        }
//        return false;
    }

    /*求决策变量x1x2可行解*/
    /* Find a feasible solution for the decision variable x1x2 */
    /*
     * name:GetOtherFeasibleSolution
     * function:Find a feasible solution for the decision variable x1x2
     * creator:Ye yaning
     * date:2021/1/28
     */
    public boolean GetOtherFeasibleSolution(int varsSum, double x[], int direction, int index, double ret[], double Min[], double Max[]) {
        boolean find = false;
        double temp[] = new double[varsSum];
        for (int i = 0; i < varsSum; i++) {
            temp[i] = x[i];
        }

        double otherFX = temp[index];//另外一个可行解 Another feasible solution
        if (temp[index] == Max[index]) otherFX = temp[index] - Tools.AreaOtherSolution;
        else if (temp[index] == Min[index]) otherFX = temp[index] + Tools.AreaOtherSolution;

        //0从右向左<---  '0' right to left <---
        if (direction == 0) {
            find = false;
            otherFX = temp[index] - Tools.AreaOtherSolution;//cxh del
            //otherFX = temp[index] / 2;
            while (otherFX >= Min[index] + 0.000000000001) {
                temp[index] = otherFX;
                if (JudgeIfFeasibleSolution(temp)) {
                    if (GetObjectiveValue(x) > GetObjectiveValue(temp)) {
                        find = true;
                        break;
                    }
                }
                otherFX /= 2;
            }
            if (find) {
                ret[0] = otherFX;
                ret[1] = x[index];
                return true;
            }
            //在1-2之间的一个范围  A range between 1-2
            find = false;
            otherFX = temp[index] - Tools.AreaOtherSolution;
            while (otherFX >= Min[index] + 0.000000000001) {
                temp[index] = otherFX;
                if (JudgeIfFeasibleSolution(temp)) {
                    if (GetObjectiveValue(x) > GetObjectiveValue(temp)) {
                        find = true;
                        break;
                    }
                }
                otherFX /= 1.5;
            }
            if (find) {
                ret[0] = otherFX;
                ret[1] = x[index];
                return true;
            }

            //最小值都无法满足要求，则从左向右搜索
            /* If the minimum value cannot meet the requirements, search from left to right */
            otherFX = temp[index] + Tools.AreaOtherSolution;
            //otherFX = temp[index] * 2;
            while (otherFX <= Max[index]) {
                temp[index] = otherFX;
                if (JudgeIfFeasibleSolution(temp)) {
                    if (GetObjectiveValue(x) > GetObjectiveValue(temp)) {
                        find = true;
                        break;
                    }
                }
                otherFX *= 2;
            }
            if (find) {
                //方向变化了，从从左向右，但是direction没有变化，则修改了ret[0]和ret[1]
                /* The direction has changed, from left to right, but the direction has not changed, then ret[0] and ret[1] are modified */
                ret[0] = x[index];
                ret[1] = otherFX;
                return true;
            }
        }

        if (direction == 1) {//1从左向右---->  '1' from left to right---->
            find = false;
            otherFX = temp[index] + Tools.AreaOtherSolution;
            //otherFX = temp[index] * 2;
            while (otherFX <= Max[index]) {
                temp[index] = otherFX;
                if (JudgeIfFeasibleSolution(temp)) {
                    if (GetObjectiveValue(x) > GetObjectiveValue(temp)) {
                        find = true;
                        break;
                    }
                }
                otherFX *= 2;
            }
            if (find) {
                ret[0] = otherFX;
                ret[1] = x[index];
                return true;
            }
            //最小值都无法满足要求，则从右向左搜索
            /* If the minimum value cannot meet the requirements, search from right to left */
            otherFX = temp[index] - Tools.AreaOtherSolution;
            //otherFX = temp[index] / 1.5;
            while (otherFX >= Min[index] + 0.000000000001) {
                temp[index] = otherFX;
                if (JudgeIfFeasibleSolution(temp)) {
                    if (GetObjectiveValue(x) > GetObjectiveValue(temp)) {
                        find = true;
                        break;
                    }
                }
                otherFX /= 1.5;
            }
            if (find) {
                //方向变化了，从右向左，但是direction没有变化，则修改了ret[0]和ret[1]
                /*T he direction has changed, from right to left, but the direction has not changed, then ret[0] and ret[1] are modified */
                ret[0] = x[index];
                ret[1] = otherFX;
                return true;
            }
            //在1-2之间的一个范围  A range between 1-2
            find = false;
            otherFX = temp[index] - Tools.AreaOtherSolution;
            //otherFX = temp[index] / 1.5;
            while (otherFX >= Min[index] + 0.000000000001) {
                temp[index] = otherFX;
                if (JudgeIfFeasibleSolution(temp)) {
                    if (GetObjectiveValue(x) > GetObjectiveValue(temp)) {
                        find = true;
                        break;
                    }
                }
                otherFX /= 1.5;
            }
            if (find) {
                ret[0] = x[index];
                ret[1] = otherFX;
                return true;
            }
        }
//        if(direction == 0) {//0从右向左<---
//            ret[0] = x[index];
//            ret[1] = Max[index];
//        }
//        if(direction == 1) {//1从右向左--->
//            ret[0] = x[index];
//            ret[1] = Min[index];
//        }
        return false;
    }
    
    /*求决策变量x1x2可行解*/
    /* Find a feasible solution for the decision variable x1x2 */
    /*
     * name:GetOtherFeasibleSolution
     * function:Find a feasible solution for the decision variable x1x2
     * creator:Ye yaning
     * date:2021/1/28
     */
    public double[] GetOtherFeasibleSolutionEn(int varsSum, double x[], int direction, int index, double ret[], double Min[], double Max[]) {
        boolean find = false;
        double temp[] = new double[varsSum];
        double temp1[] = new double[varsSum];
        for (int i = 0; i < varsSum; i++) {
            temp[i] = x[i];
            temp1[i] = x[i];
        }

        double otherFX = temp[index];//另外一个可行解 Another feasible solution
        if (temp[index] == Max[index]) otherFX = temp[index] - Tools.AreaOtherSolution;
        else if (temp[index] == Min[index]) otherFX = temp[index] + Tools.AreaOtherSolution;

        //0从右向左<---  '0' right to left <---
        if (direction == 0) {
            find = false;
            otherFX = temp[index] - Tools.AreaOtherSolution;//cxh del
            while (otherFX >= Min[index] + 0.000000000001) {
                temp[index] = otherFX;
                if (JudgeIfFeasibleSolution(temp)) {
                    if (GetObjectiveValue(temp1) > GetObjectiveValue(temp)) {
                        temp1 = temp;
                    }
                }
                otherFX /= 2;
            }
            //在1-2之间的一个范围  A range between 1-2
            find = false;
            otherFX = temp[index] - Tools.AreaOtherSolution;
            while (otherFX >= Min[index] + 0.000000000001) {
                temp[index] = otherFX;
                if (JudgeIfFeasibleSolution(temp)) {
                    if (GetObjectiveValue(temp1) > GetObjectiveValue(temp)) {
                        temp1 = temp;
                    }
                }
                otherFX /= 1.5;
            }

            //最小值都无法满足要求，则从左向右搜索
            /* If the minimum value cannot meet the requirements, search from left to right */
            otherFX = temp[index] + Tools.AreaOtherSolution;
            while (otherFX <= Max[index]) {
                temp[index] = otherFX;
                if (JudgeIfFeasibleSolution(temp)) {
                    if (GetObjectiveValue(temp1) > GetObjectiveValue(temp)) {
                        temp1 = temp;
                    }
                }
                otherFX *= 2;
            }
        }

        if (direction == 1) {//1从左向右---->  '1' from left to right---->
            find = false;
            otherFX = temp[index] + Tools.AreaOtherSolution;
            while (otherFX <= Max[index]) {
                temp[index] = otherFX;
                if (JudgeIfFeasibleSolution(temp)) {
                    if (GetObjectiveValue(temp1) > GetObjectiveValue(temp)) {
                        temp1 = temp;
                    }
                }
                otherFX *= 2;
            }
            //最小值都无法满足要求，则从右向左搜索
            /* If the minimum value cannot meet the requirements, search from right to left */
            otherFX = temp[index] - Tools.AreaOtherSolution;
            //otherFX = temp[index] / 1.5;
            while (otherFX >= Min[index] + 0.000000000001) {
                temp[index] = otherFX;
                if (JudgeIfFeasibleSolution(temp)) {
                    if (GetObjectiveValue(temp1) > GetObjectiveValue(temp)) {
                        temp1 = temp;
                    }
                }
                otherFX /= 1.5;
            }
            //在1-2之间的一个范围  A range between 1-2
            find = false;
            otherFX = temp[index] - Tools.AreaOtherSolution;
            while (otherFX >= Min[index] + 0.000000000001) {
                temp[index] = otherFX;
                if (JudgeIfFeasibleSolution(temp)) {
                    if (GetObjectiveValue(temp1) > GetObjectiveValue(temp)) {
                        temp1 = temp;
                    }
                }
                otherFX /= 1.5;
            }
        }
        return temp1;
    }
    
    
    /*求决策变量x1x2可行解*/
    /* Find a feasible solution for the decision variable x1x2 */
    /*
     * name:GetOtherFeasibleSolution
     * function:Find a feasible solution for the decision variable x1x2
     * creator:
     * date:
     */
    public boolean GetOtherFeasibleSolutionX(double X[], int direction, double retX[][], double Min[], double Max[]) {
    	int varsSum = X.length;
        double ret[] = new double[2];
        for(int i = 0; i < 2; i++){
        	for (int index = 0; index < varsSum; index++) {
            	retX[i][index] = X[index];
            }
        }
        for (int index = 0; index < varsSum; index++) {
        	if(GetOtherFeasibleSolution(varsSum, X, direction, index, ret, Min, Max)){
        		retX[0][index] = ret[0];
        		retX[1][index] = ret[1];
        		return true;
        	}
        }

        return false;
    }
    
    /*求决策变量x1x2可行解*/
    /* Find a feasible solution for the decision variable x1x2 */
    /*
     * name:GetOtherFeasibleSolution
     * function:Find a feasible solution for the decision variable x1x2
     * creator:
     * date:
     */
    public double[] GetOtherFeasibleSolutionXEn(double X[], int direction, double retX[][], double Min[], double Max[]) {
    	int varsSum = X.length;
        double ret[] = new double[2];
        double objValue = GetObjectiveValue(X);
        //int retXIndex0 = -1,retIndex1 = -1;
        double tmpX[] = new double[X.length];
        double tmpX1[] = new double[X.length];
        for(int i = 0; i < 2; i++){
        	for (int index = 0; index < varsSum; index++) {
            	retX[i][index] = X[index];
            	tmpX[index] = X[index];
            	tmpX1[index] = X[index];
            }
        }
        boolean find = true;
        while(find){
        	find = false;
        	for (int index = 0; index < varsSum; index++) {
        		tmpX = GetOtherFeasibleSolutionEn(varsSum, tmpX, direction, index, ret, Min, Max);
        		if(GetObjectiveValue(tmpX) < objValue){
        			objValue = GetObjectiveValue(tmpX);
        			tmpX1 = tmpX;
        			find = true;
        		}
        		//System.out.println("in GetOtherFeasibleSolutionXEn:"+GetObjectiveValue(tmpX)+" objec:"+objValue);
        	}
        }
        return tmpX1;
    }
    /* 
     * name: SetX()
     * function: Find a feasible solution for the decision variable x1 by using TSA
     * creator: Chen Xiaohua
     * date: 2022/06/08
     */
    public void SetX(String algoName,String funName[],int  nfun,double X[]) 
    {
    	
    	if (funName[nfun].contains("fHartman") || funName[nfun].contains("DevilliersGlasser02")) {
    		if(algoName == "TSA"){
        	} else if(algoName == "ENBALANCE"){
        	}
    	} else if (funName[nfun].contains("CEC2017-f10")) {
			for (int i = 0; i < X.length; i++) {
				if(algoName == "TSA"){
	    			X[i] -= 100;
	        	} else if(algoName == "ENBALANCE"){
	        		X[i] += 100;
	        	}
			}
		} else if (funName[nfun].contains("f3") || funName[nfun].contains("f20")) {
			for (int i = 0; i < X.length; i++) {
				if(algoName == "TSA"){
	    			X[i] -= 10;
	        	} else if(algoName == "ENBALANCE"){
	        		X[i] += 10;
	        	}
			}
		} else if (funName[nfun].contains("fBR")) {
			//for (int i = 0; i < X.length; i++) {
				if(algoName == "TSA"){
					X[0] -= 5;
	        	} else if(algoName == "ENBALANCE"){
	        		X[0] += 5;
	        	}
			//}
		} else if (funName[nfun].contains("fCA")) {
			//for (int i = 0; i < X.length; i++) {
				if(algoName == "TSA"){
					X[0] -= 3;X[1] -= 2;
	        	} else if(algoName == "ENBALANCE"){
	        		X[0] += 3;X[1] += 2;
	        	}
			//}
		} else if (funName[nfun].contains("fGP")) {
			for (int i = 0; i < X.length; i++) {
				if(algoName == "TSA"){
					X[i] -= 2;
	        	} else if(algoName == "ENBALANCE"){
	        		X[i] += 2;
	        	}
			}
		} else if(funName[nfun].contains("fRA") || funName[nfun].contains("fSH")) {
			for (int i = 0; i < X.length; i++) {
				if(algoName == "TSA"){
					X[i] -= 5.12;
	        	} else if(algoName == "ENBALANCE"){
	        		X[i] += 5.12;
	        	}
			}
		}    
    }
    
    /* 
     * name: SetMaxMin()
     * function: Find a feasible solution for the decision variable x1 by using TSA
     * creator: Chen Xiaohua
     * date: 2022/06/08
     */
    public void SetMaxMin(String funName[],int  nfun,double max[],double min[]) 
    {
    	int dim = 0;
    	if (funName[nfun].contains("fHartman")) {
            dim = 3;
            for (int i = 0; i < dim; i++) {
            	if(Parameters.algoName == "TSA"){
            		max[i] = 1;
            		min[i] = 0;
            	} else if(Parameters.algoName == "ENBALANCE"){
            		max[i] = 1;//5.12;//10;//
            		min[i] = 0;//-5.12;//-10;//
            	}
            }
        } else if (funName[nfun].contains("CEC2017-f10")) {
            dim = max.length;
            for (int i = 0; i < dim; i++) {
            	if(Parameters.algoName == "TSA"){
            		max[i] = 100;
            		min[i] = -100;
            	} else if(Parameters.algoName == "ENBALANCE"){
            		max[i] = 200;//5.12;//10;//
            		min[i] = 0;//-5.12;//-10;//
            	}
            }
        } else if(funName[nfun].contains("f3")||funName[nfun].contains("f20")){
            dim = 30;
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
    }
    /* 
     * name: GetOtherFeasibleSolutionXTSA
     * function: Find a feasible solution for the decision variable x1 by using TSA
     * creator: Chen Xiaohua
     * date: 2022/06/05
     */
    public double [] GetOtherFeasibleSolutionXTSA(double X[]) {
    	int varsSum = X.length;
    	double max[] = new double[varsSum];
    	double min[] = new double[varsSum];
    	double X1[] = new double[varsSum];
    	double tmpX[] = new double[varsSum];
        //初始化Min和Max
    	for (int i = 0; i < varsSum; i++) {
    		//max[i] = 5.12;min[i] = -5.12;
    		tmpX[i] = X[i];
        }
        
    	int Funs = 16;
    	int dim = varsSum;
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
            } else if (funName[nfun].contains("fHartman")) {
                dim = 3;
            } else if(funName[nfun].contains("f3")||funName[nfun].contains("f20")){
                dim = 30;
            } else if(funName[nfun].contains("DevilliersGlasser02")){
                dim = 5;
            }else{
                dim = 2;
            }
            max = new double[dim];
            min = new double[dim];
            
            Parameters.algoName = "TSA";
            SetMaxMin(funName,nfun,max,min);
            SetX(Parameters.algoName,funName,nfun,tmpX);
            
            System.out.print("\n-------------------------------------------------------\n");
            System.out.print("Function = " + funName[nfun] + " ,Dimension size = " + dim + "\n");
            Parameters.modelName = funName[nfun];
            Tang.TSAModify tsa = new Tang.TSAModify();
            X1 = tsa.TSA(tmpX, dim, max, min);
            //X1 = tsa.TSA(dim, max, min);
            System.out.print("TSA tmpX:"+GetObjectiveValue(tmpX)+" X1:"+GetObjectiveValue(X1));
            
            Parameters.algoName = "ENBALANCE";
            SetMaxMin(funName,nfun,max,min);
            SetX(Parameters.algoName,funName,nfun,X1);
            SetX(Parameters.algoName,funName,nfun,tmpX);
            System.out.print("\nTSA trans tmpX:"+GetObjectiveValue(tmpX)+" X1:"+GetObjectiveValue(X1));
            
            
            if(CheckConstraints(X1)){
            	return X1;
            } else {
            	System.out.println("Error!*********************");
            	return X1;
            }
        }
        //System.out.print("\nTSA at last tmpX:"+GetObjectiveValue(tmpX)+" X1:"+GetObjectiveValue(X1));
        
        return X1;
    }
    


    /*与GetOptimalInArea配合使用的收敛条件*/
    /*Convergence criteria for use with GetOptimalInArea*/
    /*
     * name:CheckIfOptimal
     * function:Convergence criteria for use with GetOptimalInArea
     * creator:Ye yaning
     * date:2021/1/28
     */
    public boolean CheckIfOptimal(double curoptimalx[], double optimalx[]) {
        int i;
        for (i = 0; i < varsSum; i++) {
            //判断每组变量是否都达到平衡状态，是否为最优解
            //Determine whether each group of variables has reached equilibrium and is the optimal solution
            if (Math.abs(curoptimalx[i] - optimalx[i]) > Parameters.alfa) {
//            if (Math.abs(curoptimalx[i] - optimalx[i]) > 0.000001) {
//            if (Math.abs(curoptimalx[i] - optimalx[i]) > 0.00000000000000001) {
                return true;
            }
        }
        return false;
    }


    /*获取单变量最优解*/
    /*
     * name:GetBalanceOneVariable
     * function:获取单变量最优解
     * creator:ya ning
     * date:2021/1/28
     */
    public double[]  GetOptimalOneVariable(double X1[], int index,double min[],double max[]) {
        double b1 = X1[index], b2 = -1;
        //int iter = 0;
        while(Math.abs(b1-b2)>Parameters.alfa){
            b1 = GetBalanceOneVariable(X1,index,0, min, max);X1[index] = b1;
            b2 = GetBalanceOneVariable(X1,index,1, min, max);X1[index] = b2;
            //iter ++;
        }
//        b1 = -1;
//        while(Math.abs(b1-b2)>Parameters.alfa){
//            b1 = GetBalanceOneVariableTwoInitX(X1,index,varsMaxValue[index],X1[index],0, varsMinValue, varsMaxValue);X1[index] = b1;
//            b2 = GetBalanceOneVariableTwoInitX(X1,index,varsMinValue[index],X1[index],1, varsMinValue, varsMaxValue);X1[index] = b2;
//        }
        //找到另外一个可行解
        //GetOtherFeasibleSolution(InitX.length, X1, direction, index, ret,varsMinValue,varsMaxValue);

        return X1;
    }

    /*
     * name: GetOptimalX
     * function:
     * creator:
     * date:
     */
    public double[]  GetOptimalX(double X1[], int index,double min[],double max[]) {
        //double b1 = X1[index], b2 = -1;
    	double tempX[] = new double[X1.length];
    	double X2[] = new double[X1.length];
        while(GetEuDis(tempX,X1)>Parameters.alfa){// && GetObjectiveValue(tempX)<=GetObjectiveValue(X1)){//&& GetObjectiveValue(X1)<=GetObjectiveValue(tempX)
        	tempX = X1;
        	X2 = GetBalanceX(X1,0, min, max);//X1[index] = b1;
        	System.out.println("\nBlance 0 "+GetObjectiveValue(X2)+" "+GetObjectiveValue(X1));
//        	for (int i = 0; i < X1.length; i++) {
//            	System.out.print(" X["+i+"]:"+X2[i]+" "+X1[i]);
//            }
        	if(GetObjectiveValue(X2) > GetObjectiveValue(X1))
        		X2 = X1;
        	X1 = GetBalanceX(X2,1, min, max);//X1[index] = b2;
        	if(GetObjectiveValue(X2) < GetObjectiveValue(X1))
        		X1 = X2;
        	System.out.println("\nBlance 1 "+GetObjectiveValue(X1)+" "+GetObjectiveValue(X2));
//        	for (int i = 0; i < X1.length; i++) {
//            	System.out.print(" X["+i+"]:"+X1[i]+" "+X2[i]);
//            }
        }

        return X1;
    }
    
    /*
     * name: GetOptimalXTwoP
     * function: 通过两个点获取平衡点，内涵了方向
     * creator: Chen Xiaohua
     * date: 2022/06/05
     */
    public double[]  GetOptimalXTwoP(double X1[], int index,double min[],double max[]) {
        //double b1 = X1[index], b2 = -1;
    	double tempX[] = new double[X1.length];
        while(GetEuDis(tempX,X1)>Parameters.alfa){
        	tempX = X1;
        	X1 = GetBalanceXTwoPDire(X1,0, min, max);//X1[index] = b1;
        	System.out.println("\nBlance 0 "+GetObjectiveValue(X1));
        	for (int i = 0; i < X1.length; i++) {
            	//System.out.print("X["+i+"]:"+X1[i]+" ");
            }
        	X1 = GetBalanceXTwoPDire(X1,1, min, max);//X1[index] = b2;
        	System.out.println("\nBlance 1 "+GetObjectiveValue(X1));
        	for (int i = 0; i < X1.length; i++) {
            	//System.out.print("X["+i+"]:"+X1[i]+" ");
            }
        }

        return X1;
    }
    /*获取单变量最优解*/
    /*
     * name:GetBalanceOneVariable
     * function:获取单变量最优解
     * creator:ya ning
     * date:2021/1/28
     */
    public double GetBalanceOneVariable(double InitX[], int index, int direction,double min[],double max[]) {
        int step = 2;
        double X1[] = new double[varsSum];
        double X2[] = new double[varsSum];
        double X3[] = new double[varsSum];
        double X4[] = new double[varsSum];
        double c = 1;
        double ret[] = new double[2];
        double x1,x2,x3,x4=-1;
        
        Feedback.Tools myDowith = new Feedback.Tools();//保存测试数据
        String debugData = "";
        //step 1 start
        for(int i = 0; i < InitX.length; i++){
            X2[i] = X1[i] = InitX[i];
        }
        if(Parameters.debugModel == 1) myDowith.SaveFile("debug.txt", "InitX:"+InitX[0], true);
        c = 1;//step 1 end
//        ret[0] = X2[index];
//        System.out.println(X2[index]);
//        GetFeasibleSolution(varsSum,InitX,direction,index,ret,min,max);
        //int iter = 0;
        while(c>Parameters.alfa ){//step 2 end
            GetFeasibleSolution(X2.length,X2,direction,index,ret,min,max);
            x2 = ret[0];//x2和x1是与方向direction有关的值
            GetOtherFeasibleSolution(varsSum, X2, direction, index, ret,min, max);
            x2 = ret[0];//x2和x1是与方向direction有关的值
            x1 = ret[1];
            X2[index] = x2;
            for(int i = 0; i < InitX.length; i++){
                X4[i] = X3[i] = X2[i];
            }//step 3 end
            X2[index] = x2;x3 = X3[index]; //step 4 end
            if(direction == 0) {
                x4 = x3 / Math.pow(step, p) - (x1 - x2) / Math.pow(step, q);
                X4[index] = x4;
            } else if(direction == 1) {
                x4 = x3 * Math.pow(step, p) - (x1 - x2) / Math.pow(step, q);
                X4[index] = x4;
            }//step 6 end
//            System.out.println("f(x4):"+GetObjectiveValue(X4)+" f(x2):"+GetObjectiveValue(X2)+" direction:" + direction + " c:" + c + " curX:" + X2[index] + " x4:"+ X4[index]+ " x3:"+X3[index]+ " x2:" + x2 + " x1:" + x1 + " p:" + p + " q:" + q + " step:" + step);
            if(Parameters.debugModel == 1) myDowith.SaveFile("debug.txt", "f(x4):"+GetObjectiveValue(X4)+" f(x2):"+GetObjectiveValue(X2)+" direction:" + direction + " c:" + c + " curX:" + X2[index] + " x4:"+ X4[index]+ " x3:"+X3[index]+ " x2:" + x2 + " x1:" + x1 + " p:" + p + " q:" + q + " step:" + step+"\n", true);
            
            if( GetObjectiveValue(X4) <= GetObjectiveValue(X2) && CheckConstraints(X4)) { //&& CheckBlockMinMax(X4, InitX.length,min, max)
                p++;
                q = 0;
                c = Math.abs(x4 - x2);
                for (int i = 0; i < InitX.length; i++) {
                    X1[i] = X2[i];
                    X2[i] = X3[i] = X4[i];
                }

            }else{
                q++;
                p = 0;
            }//step 7-8-9 end
            //iter++;
            //System.out.println("OneVariable iter:"+iter);

        }
        return X4[index];//step 12 end
    }
    

    /*
     * name: GetBalanceX
     * function:在某个方向上获取平衡点
     * creator:
     * date:
     */
    public double[] GetBalanceX(double InitX[],int direction,double min[],double max[]) {
        int step = 2;
        double X1[] = new double[varsSum];
        double X1Tmp[] = new double[varsSum];
        double X2[] = new double[varsSum];
        double X3[] = new double[varsSum];
        double X4[] = new double[varsSum];
        double c = 1;
        double ret[][] = new double[2][varsSum];
        double x1,x2,x3,x4=-1;
        int index = 0;
        
        Feedback.Tools myDowith = new Feedback.Tools();//保存测试数据
        String debugData = "";
        
        //step 1 start
        for(int i = 0; i < InitX.length; i++){
            X2[i] = X1[i] = X1Tmp[i] = InitX[i];
        }
        c = 1;//step 1 end
        if(Parameters.debugModel == 1) myDowith.SaveFile("debug.txt", "InitX:"+InitX[0], true);
        if(!GetFeasibleSolutionX(X1,direction,ret,min,max))
        	//return X1;
        X1 = ret[0];//x2和x1是与方向direction有关的值

//        GetOtherFeasibleSolutionX(X1, direction, ret,min, max);
//        X2 = ret[0];X1 = ret[1];
        X2 = GetOtherFeasibleSolutionXEn(X1, direction, ret,min, max);
       
        //X2 = X1;

        //if(GetEuDis(X2,X1)<=Parameters.alfa){//说明没有找到合适的另外一个可行解
        	X2 = GetOtherFeasibleSolutionXTSA(X2);
        	System.out.println("\ncxh1 X2:"+GetObjectiveValue(X2)+" X1:"+GetObjectiveValue(X1));
        	Parameters.algoName = "ENBALANCE";
        	if(GetObjectiveValue(X2) > GetObjectiveValue(X1)){
        		System.out.println("\ncxh4 X1:"+GetObjectiveValue(X1)+" X2:"+GetObjectiveValue(X2));
        		return X1;
        	}
            	
        	if(GetEuDis(X2,X1)<=Parameters.alfa){
        		System.out.println("\ncxh3 X1:"+GetObjectiveValue(X1));
        		return X2;
        	}
        		
        	//return X1;
        //}
        
        while(c>Parameters.alfa){//step 2 end
            for(int i = 0; i < InitX.length; i++){
                X3[i] = X2[i];//X4[i] = 
            }//step 3 end
            
            for (int i = 0; i < X1.length; i++) {
//            r[i] = 1.5 * m[i] - 0.5*SortX[VarsSum][i];
            	if(direction == 1) X4[i] = Math.pow(2, p) * X3[i] +  Math.abs(GetDisFromZeor(X1) - GetDisFromZeor(X2))/Math.pow(2, q);
            	else if(direction == 0) X4[i] =  X3[i]/Math.pow(2, p) -  Math.abs(GetDisFromZeor(X1) - GetDisFromZeor(X2))/Math.pow(2, q);
            }
            
//            System.out.println("f(x4):"+GetObjectiveValue(X4)+" f(x2):"+GetObjectiveValue(X2)+" direction:" + direction + " c:" + c + " curX:" + X2[index] + " x4:"+ X4[index]+ " x3:"+X3[index]+ " x2:" + x2 + " x1:" + x1 + " p:" + p + " q:" + q + " step:" + step);
        	if(Parameters.debugModel == 1) myDowith.SaveFile("debug.txt", "f(x4):"+GetObjectiveValue(X4)+" f(x2):"+GetObjectiveValue(X2)+" direction:" + direction + " c:" + c + " curX:" + X2[index] + " x4:"+ X4[index]+ " x3:"+X3[index]+ " x2:" + X2[index] + " x1:" + X1[index] + " p:" + p + " q:" + q + " step:" + step+"\n", true);
            
        	if( GetObjectiveValue(X4) <= GetObjectiveValue(X2) && CheckConstraints(X4)) { //&& CheckBlockMinMax(X4, InitX.length,min, max)
                p++;
                q = 0;
                c = GetEuDis(X4,X2);//Math.abs(X4[0] - X2[0]);//
                for (int i = 0; i < InitX.length; i++) {
                    X1[i] = X2[i];
                    X2[i] = X3[i] = X4[i];
                    //System.out.println("X["+i+"]:"+X4[i]+" obj:"+GetObjectiveValue(X4)+" dire:"+direction);
                }
            }else{
                q++;
                p = 0;
            }//step 7-8-9 end
        }
        System.out.println("\ncxh2 X2:"+GetObjectiveValue(X2));
        return X2;//[index];//step 12 end
    }
    
    
    /*
     * name: GetBalanceXTwoPDire
     * function: 通过两个点在某个方向上获取平衡点
     * creator: Chen Xiaohua
     * date: 2022/06/05
     */
    public double[] GetBalanceXTwoPDire(double InitX[],int direction,double min[],double max[])
    {
        int step = 2;
        double X1[] = new double[varsSum];
        double X1Tmp[] = new double[varsSum];
        double X2[] = new double[varsSum];
        double X3[] = new double[varsSum];
        double X4[] = new double[varsSum];
        double c = 1;
        double ret[][] = new double[2][varsSum];
        GetFeasibleSolutionX(X1,direction,ret,min,max);
        X1 = ret[0];//x2和x1是与方向direction有关的值
        GetOtherFeasibleSolutionX(X1, direction, ret,min, max);
        X2 = ret[0];X1 = ret[1];
        if(GetEuDis(X2,X1)<=Parameters.alfa){//说明没有找到合适的另外一个可行解
        	//用TSA查找一个可行解
        	return X1;
//        	X2 = GetOtherFeasibleSolutionXTSA(X1);
//        	if(GetEuDis(X2,X1)<=Parameters.alfa)
//        		return X1;
        }
        //GetOtherFeasibleSolutionX
        while(c>Parameters.alfa){//step 2 end
            for(int i = 0; i < InitX.length; i++){
                X3[i] = X2[i];//X4[i] = 
            }//step 3 end
        	
            for (int i = 0; i < X1.length; i++) {
//            r[i] = 1.5 * m[i] - 0.5*SortX[VarsSum][i];
            	if(direction == 1) X4[i] = Math.pow(2, p) * X3[i] +  Math.abs(GetDisFromZeor(X1) - GetDisFromZeor(X2))/Math.pow(2, q);
            	else if(direction == 0) X4[i] =  X3[i]/Math.pow(2, p) -  Math.abs(GetDisFromZeor(X1) - GetDisFromZeor(X2))/Math.pow(2, q);
            	
            }
            
//            System.out.println("f(x4):"+GetObjectiveValue(X4)+" f(x2):"+GetObjectiveValue(X2)+" direction:" + direction + " c:" + c + " curX:" + X2[index] + " x4:"+ X4[index]+ " x3:"+X3[index]+ " x2:" + x2 + " x1:" + x1 + " p:" + p + " q:" + q + " step:" + step);
//        	if(Parameters.debugModel == 1) myDowith.SaveFile("debug.txt", "f(x4):"+GetObjectiveValue(X4)+" f(x2):"+GetObjectiveValue(X2)+" direction:" + direction + " c:" + c + " curX:" + X2[index] + " x4:"+ X4[index]+ " x3:"+X3[index]+ " x2:" + X2[index] + " x1:" + X1[index] + " p:" + p + " q:" + q + " step:" + step+"\n", true);
            
        	if( GetObjectiveValue(X4) <= GetObjectiveValue(X2) && CheckConstraints(X4)) { //&& CheckBlockMinMax(X4, InitX.length,min, max)
                p++;
                q = 0;
                c = Math.abs(X4[0] - X2[0]);//GetEuDis(X4,X2);//
                for (int i = 0; i < InitX.length; i++) {
                    X1[i] = X2[i];
                    X2[i] = X3[i] = X4[i];
                    //System.out.println("X["+i+"]:"+X4[i]+" obj:"+GetObjectiveValue(X4)+" dire:"+direction);
                }
            }else{
                q++;
                p = 0;
            }//step 7-8-9 end
        }
        return X4;//[index];//step 12 end
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
    		//a += (X1[i] - X2[i]) * (X1[i] - X2[i]);
    	}
    	return 0;//Math.sqrt(a);//
    }
    
    /*
     * name: GetDisFromZeor
     * function: 
     * creator: Chen Xiaohua
     * date: 2022/06/04
     */
    public double GetDisFromZeor(double X1[])
    {
    	double a = 0;
    	for(int i = 0; i < X1.length; i++){
    		a += (X1[i] - 0) * (X1[i] - 0);
    	}
    	return Math.sqrt(a);
    }
    
    /*
     * name: GetXValue
     * function: 
     * creator: Chen Xiaohua
     * date: 2022/06/02
     */
    public double[] GetXValue(double X[],double s, double p,int direction)
    {
    	double XTmp[] = new double[X.length];
    	for(int i = 0; i < X.length; i++){
    		if(direction == 0)
    			XTmp[i] = X[i] / Math.pow(s, p);
    		else if(direction == 1)
    			XTmp[i] = X[i] * Math.pow(s, p);
    	}
    	return XTmp;
    }
    
    /*
     * name: GetXValue
     * function: 计算欧式距离
     * creator: Chen Xiaohua
     * date: 2022/06/02
     */
    public double[] GetXValue(double X1[],double X2[],double s, double q)
    {
    	double XTmp[] = new double[X1.length];
    	for(int i = 0; i < X1.length; i++){
    		XTmp[i] = (X1[i] - X2[i]) / Math.pow(s, q);
    	}
    	return XTmp;
    }


    /*寻找块内最优解*/
    /*
     * name:GetOptimalInArea
     * function:寻找块内最优解
     * creator:ya ning
     * date:2021/1/28
     */
    public double[] Balance(double initX[],double min[],double max[]) {

        for(int i=0;i<varsSum;i++){

            this.varsMaxValue[i] = max[i];
            this.varsMinValue[i] = min[i];
        }
        double curoptimalx[] = new double[initX.length];
        double GlobalOptimal[] = new double[initX.length];

        FindInitSolution(initX, initX.length, 0, varsMinValue, varsMaxValue);
//        System.out.print("Feasible ");
        for (int i = 0; i < varsSum; i++) {//防止最优解大于0，而最优解不设定初始值而默认为0
//            System.out.print("x["+ i + "] = "+initX[i]+" ");
            curoptimalx[i] = initX[i];
            GlobalOptimal[i] = curoptimalx[i] +0.01;
        }
//        System.out.println();
        //int iter = 0;
        while (CheckIfOptimal(curoptimalx, GlobalOptimal)) {//寻找块内最优解
            for (int i = 0; i < varsSum; i++) {//当前可行解作为块内最优解
                GlobalOptimal[i] = curoptimalx[i];
            }
            for(int index = 0; index < varsSum; index++) {//块内每个变量固定进行固定后寻找平衡点
                curoptimalx = GetOptimalOneVariable(curoptimalx, index, varsMinValue, varsMaxValue);

//                System.out.print("x["+ index +"] current balance ");
                for (int i = 0; i < varsSum; i++) {//求得的数组curoptimalotherx为当前最优解，进行赋值并输出
//                    System.out.print(curoptimalx[i] +" ");
                }
//                System.out.println(GetObjectiveValue(curoptimalx));
                q = 0; p = 0; //iter++;
                //System.out.println("Balance iter:"+iter + " maxIter:"+maxIter);
                //if(iter > maxIter) break;
            }
        }
        return GlobalOptimal;
    }


    /*
     * name: BalanceX
     * function: 以X向量寻找平衡点，以及找全局最优解
     * creator:
     * date:2022/6/2
     */
    public double[] BalanceX(double initX[],double min[],double max[]) {

        for(int i=0;i<varsSum;i++){

            this.varsMaxValue[i] = max[i];
            this.varsMinValue[i] = min[i];
        }
        double curoptimalx[] = new double[initX.length];
        double GlobalOptimal[] = new double[initX.length];

        FindInitSolution(initX, initX.length, 0, varsMinValue, varsMaxValue);
        System.out.print("Feasible ");
        for (int i = 0; i < varsSum; i++) {//防止最优解大于0，而最优解不设定初始值而默认为0
//            System.out.print("x["+ i + "] = "+initX[i]+" ");
            curoptimalx[i] = initX[i];
            GlobalOptimal[i] = curoptimalx[i] +0.01;
        }
//        System.out.println();
        while (CheckIfOptimal(curoptimalx, GlobalOptimal)) {//寻找块内最优解
            for (int i = 0; i < varsSum; i++) {//当前可行解作为块内最优解
                GlobalOptimal[i] = curoptimalx[i];
            }
            System.out.println("Pre:"+GetObjectiveValue(curoptimalx));
            curoptimalx = GetOptimalX(curoptimalx, 0, varsMinValue, varsMaxValue);
            //curoptimalx = GetOptimalXTwoP(curoptimalx, 0, varsMinValue, varsMaxValue);
            
//            for(int direction = 0; direction < 2; direction++) {//以方向寻找平衡点
//                curoptimalx = GetOptimalX(curoptimalx, direction, varsMinValue, varsMaxValue);
//
////                for (int i = 0; i < varsSum; i++) {//求得的数组curoptimalotherx为当前最优解，进行赋值并输出
//////                    System.out.print(curoptimalx[i] +" ");
////                }
//                q = 0; p = 0;
//            }
            System.out.println(GetObjectiveValue(curoptimalx));
        }
        return GlobalOptimal;
    }


}
