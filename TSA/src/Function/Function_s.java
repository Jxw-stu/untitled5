package Function;

import Feedback.Parameters;

public class Function_s {

    public double GetObjectiveValue(double x[]) {

        if (Parameters.modelName == "fBR") {
            return GetObjectiveValueBR(x);
        } else if (Parameters.modelName == "DevilliersGlasser02") {
            return GetObjectiveValueDevilliersGlasser02(x);
        } else if (Parameters.modelName == "CEC2017-f10-d10") {
            return GetObjectiveValueCEC2017(x,10,10);
        } else if (Parameters.modelName == "CEC2017-f10-d30") {
            return GetObjectiveValueCEC2017(x,10,30);
        } else if (Parameters.modelName == "CEC2017-f10-d50") {
            return GetObjectiveValueCEC2017(x,10,50);
        } else if (Parameters.modelName == "CEC2017-f10-d100") {
            return GetObjectiveValueCEC2017(x,10,100);
        }
        if (Parameters.modelName == "fCA") {
            return GetObjectiveValueCA(x);
        }
        if (Parameters.modelName == "fGP") {
            return GetObjectiveValueGP(x);
        }
        if (Parameters.modelName == "fRA") {
            return GetObjectiveValueRA(x);
        }
        if (Parameters.modelName == "fSH") {
            return GetObjectiveValueSH(x);
        }
        if (Parameters.modelName == "fHartman") {
            return GetObjectiveValueH(x);
        }
        if (Parameters.modelName == "f3") {
            return GetObjectiveValue3(x);
        }
        if (Parameters.modelName == "f20") {
            return GetObjectiveValue20(x);
        }
        return 1000000;
    }

    //平移函数
    public double[] ping_yi(double pra1[], double num) {
        int len = pra1.length;
        double[] item1 = new double[len];
        for (int i = 0; i < len; i++) {
            item1[i] = pra1[i] - num;
        }
        return item1;
    }
    
    public double GetObjectiveValueCEC2017(double x[],int funNum,int dimension) {
    	double Value = 0;
        double a = 0;
        double b = 0;
        int N = x.length;
        int func_num = funNum,n=dimension,m=2;//10，10
        double f[] = new double[m];
        //CEC2017 myCEC = new CEC2017();
        CEC17 myCEC17 = new CEC17();
        if(Parameters.algoName == "ENBALANCE"){
        	x = ping_yi(x, 100);
        } else if(Parameters.algoName == "TSA"){
        }
        myCEC17.cec17_test_func(x, f, n,m,func_num);
        return f[0]+f[1];
        //return myCEC.GetValue(x, x.length, 10);
        
        //System.out.println("CEC2017-f10:"+myCEC.GetValue(x, x.length, 10));

        //double x[];// = new double[pra.length];
//        int VarsSum = x.length;
//        if(Parameters.algoName == "ENBALANCE"){//TSA
//        	for (int i = 1; i < 6; i++) {
//                a += i * Math.cos((i + 1) * (x[0] - 5.12) + i);
//            }
//            for (int i = 1; i < 6; i++) {
//                b += i * Math.cos((i + 1) * (x[1] - 5.12) + i);
//            }
//            Value += a * b;
//        } else if(Parameters.algoName == "TSA"){//TSA
//        	for (int i = 1; i < 6; i++) {
//                a += i * Math.cos((i + 1) * (x[0]) + i);
//            }
//            for (int i = 1; i < 6; i++) {
//                b += i * Math.cos((i + 1) * (x[1]) + i);
//            }
//            Value += a * b;
//        }
//        if(Parameters.optimalModel.equals("MAX") ) return -Value;
//        return Value;
    }
    public double GetObjectiveValueDevilliersGlasser02(double x[]) {
        double Value = 0;
        double t[] = new double[25];
        double y[] = new double[25];
        for(int i = 1; i < 25; i++){
        	t[i] = 0.1 * (i - 1);
        }
        for(int i = 1; i < 25; i++){
        	y[i] = 53.81 * Math.pow(1.27, t[i]) * Math.tanh(3.012*t[i]+Math.sin(2.13*t[i])) * Math.cos(Math.exp(0.507)*t[i]);
        }
        
//        x[0] = 53.81;x[1] = 1.27;x[2] = 3.012;x[3] = 2.13;x[4] = 0.507;

        for (int i = 1; i < 24; i++) {
        	Value += Math.pow((x[0]*Math.pow(x[1], t[i])*Math.tanh(x[2]*t[i]+Math.sin(x[3]*t[i])))*Math.cos(t[i]*Math.exp(x[4]))-y[i],2);//i * Math.cos((i + 1) * (x[0]) + i);
        }
        if(Parameters.optimalModel.equals("MAX") ) return -Value;
        return Value;
    }
    

    /*fSH*/
    public double GetObjectiveValueSH(double x[]) {
        double Value = 0;
        double a = 0;
        double b = 0;
        //double x[];// = new double[pra.length];
        int VarsSum = x.length;
        if(Parameters.algoName == "ENBALANCE"){//TSA
        	for (int i = 1; i < 6; i++) {
                a += i * Math.cos((i + 1) * (x[0] - 5.12) + i);
            }
            for (int i = 1; i < 6; i++) {
                b += i * Math.cos((i + 1) * (x[1] - 5.12) + i);
            }
            Value += a * b;
        } else if(Parameters.algoName == "TSA"){//TSA
        	for (int i = 1; i < 6; i++) {
                a += i * Math.cos((i + 1) * (x[0]) + i);
            }
            for (int i = 1; i < 6; i++) {
                b += i * Math.cos((i + 1) * (x[1]) + i);
            }
            Value += a * b;
        }
        if(Parameters.optimalModel.equals("MAX") ) return -Value;
        return Value;
    }

    /*fHartman*/
    public double GetObjectiveValueH(double x[]) {
        double Value = 0;
        int VarsSum = x.length;

        double a[] = {1.0, 1.2, 3.0, 3.2};
        double A[][] = {{3.0, 10, 30}, {0.1, 10, 35}, {3.0, 10, 30}, {0.1, 10, 35}};
        double p[][] = {{3689, 1170, 2673}, {4699, 4387, 7470}, {1091, 8732, 5547}, {381, 5743, 8828}};
        double P[][] = new double[4][3];
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 3; j++) {
                P[i][j] = p[i][j] * 0.0001;
            }
        }
        double area1[] = new double[4];
        double area2 = 0;
        for (int i = 0; i < 4; i++) {
        	area1[i] = 0;
            for (int j = 0; j < 3; j++) {
                area1[i] += A[i][j] * (x[j] - P[i][j]) * (x[j] - P[i][j]);
            }
            area1[i] = Math.exp(-area1[i]);
            area2 += a[i] * area1[i];
        }
        Value = -area2;
        if(Parameters.optimalModel.equals("MAX") ) return -Value;
        return Value;
    }

    /*fBR*/
    public double GetObjectiveValueBR(double x[]) {
        double Value = 0,a=0,b=0;
        int VarsSum = x.length;
        if(Parameters.algoName == "ENBALANCE"){
        	a = x[1] - 5.1 / (4 * Math.PI * Math.PI) * (x[0] - 5) * (x[0] - 5) + 5 / Math.PI * (x[0] - 5) - 6;
            Value += Math.pow(a, 2);
            b = (10 - 10 / (8 * Math.PI)) * Math.cos((x[0] - 5)) + 10;
            Value += b;
        } else if(Parameters.algoName == "TSA"){//TSA
        	a = x[1]-5.1/(4*Math.PI*Math.PI) * x[0]*x[0]+ 5/Math.PI*x[0]-6;
            Value += Math.pow(a,2);
            b = (10-10/(8*Math.PI))*Math.cos(x[0])+10;
            Value +=b;
        }

        if(Parameters.optimalModel.equals("MAX") ) return -Value;
        return Value;
    }

    /*fCA*/
    public double GetObjectiveValueCA(double x[]) {
        double Value = 0, a = 0, b = 0, c = 0;
        if(Parameters.algoName == "ENBALANCE"){
        	a = 4 * (x[0] - 3) * (x[0] - 3) - 2.1 * Math.pow((x[0] - 3), 4) + Math.pow((x[0] - 3), 6) / 3;
            Value += a;
            b = (x[0] - 3) * (x[1] - 2);
            Value += b;
            c = 4 * Math.pow((x[1] - 2), 4) - 4 * Math.pow((x[1] - 2), 2);
            Value += c;
            //return Value;
        } else if(Parameters.algoName == "TSA"){//TSA
        	a = 4 * (x[0]) * (x[0]) - 2.1 * Math.pow((x[0]), 4) + Math.pow((x[0]), 6) / 3;
            Value += a;
            b = (x[0]) * (x[1]);
            Value += b;
            c = 4 * Math.pow((x[1]), 4) - 4 * Math.pow((x[1]), 2);
            Value += c;
            //return Value;
        }
        if(Parameters.optimalModel.equals("MAX") ) return -Value;
        return Value;
    }

    /*fGP*/
    public double GetObjectiveValueGP(double x[]) {
        double Value = 0, a = 0, b = 0;
//        double a = 1+(x[0]+x[1]+1)*(x[0]+x[1]+1)*(19-14*x[0]+3*x[0]*x[0]-14*x[1]+6*x[0]*x[1]+3*x[1]*x[1]);
//        double b =30+(2*x[0]-3*x[1])*(2*x[0]-3*x[1])*(18-32*x[0]+12*x[0]*x[0]+48*x[1]-36*x[0]*x[1]+27*x[1]*x[1]);
//        Value+=a*b;
        if(Parameters.algoName == "ENBALANCE"){
        	a = 1 + ((x[0] - 2) + (x[1] - 2) + 1) * ((x[0] - 2) + (x[1] - 2) + 1) * (19 - 14 * (x[0] - 2) + 3 * (x[0] - 2) * (x[0] - 2) - 14 * (x[1] - 2) + 6 * (x[0] - 2) * (x[1] - 2) + 3 * (x[1] - 2) * (x[1] - 2));
            b = 30 + (2 * (x[0] - 2) - 3 * (x[1] - 2)) * (2 * (x[0] - 2) - 3 * (x[1] - 2)) * (18 - 32 * (x[0] - 2) + 12 * (x[0] - 2) * (x[0] - 2) + 48 * (x[1] - 2) - 36 * (x[0] - 2) * (x[1] - 2) + 27 * (x[1] - 2) * (x[1] - 2));
            Value += a * b;
            return Value;
        } else if(Parameters.algoName == "TSA"){
        	a = 1 + ((x[0]) + (x[1]) + 1) * ((x[0]) + (x[1]) + 1) * (19 - 14 * (x[0]) + 3 * (x[0]) * (x[0]) - 14 * (x[1]) + 6 * (x[0]) * (x[1]) + 3 * (x[1]) * (x[1]));
            b = 30 + (2 * (x[0]) - 3 * (x[1])) * (2 * (x[0]) - 3 * (x[1])) * (18 - 32 * (x[0]) + 12 * (x[0]) * (x[0]) + 48 * (x[1]) - 36 * (x[0]) * (x[1]) + 27 * (x[1]) * (x[1]));
            Value += a * b;
            return Value;
        }
        if(Parameters.optimalModel.equals("MAX") ) return -Value;
        return Value;
    }

    /*fRA*/
    public double GetObjectiveValueRA(double X[]) {
    	double Value = 0, a = 0, b = 0;
        int VarsSum = X.length;
        //double[] x = new double[VarsSum];
        a = 10 * VarsSum;
        for (int i = 0; i < VarsSum; i++) {
        	if(Parameters.algoName == "ENBALANCE"){
        		Value += Math.pow(X[i]-5.12, 2) - 10 * Math.cos(2 * Math.PI * (X[i]-5.12));
            } else if(Parameters.algoName == "TSA"){
            	Value += Math.pow(X[i], 2) - 10 * Math.cos(2 * Math.PI * (X[i]));
            }
        }
        Value = Value + a;
        if(Parameters.optimalModel.equals("MAX") ) return -Value;
        return Value;
//        return Value;
    }

    //f3
    public double GetObjectiveValue3(double x[]) {
        int N = x.length;
        //double[] x = new double[N];
//        x = ping_yi(pra, 10);

        double res = 0;
        double area1 = 0;
        double area2 = 1;
        if(Parameters.algoName == "ENBALANCE"){
        	for (int i = 0; i < N; i++) {
                area1 += Math.abs(x[i] - 10);
            }
            for (int i = 0; i < N; i++) {
                area2 *= Math.abs(x[i] - 10);
            }
        } else if(Parameters.algoName == "TSA"){
        	for (int i = 0; i < N; i++) {
                area1 += Math.abs(x[i]);
            }
            for (int i = 0; i < N; i++) {
                area2 *= Math.abs(x[i]);
            }
        }
        res = area1 + area2;
        if(Parameters.optimalModel.equals("MAX") ) return -res;
        return res;
//        return res;
    }


    //f20
    public double GetObjectiveValue20(double x[]) {
    	int N = x.length;
        //double[] x = new double[N];
    	if(Parameters.algoName == "ENBALANCE"){
    		x = ping_yi(x, 10);
    	} else if(Parameters.algoName == "TSA"){
    		
    	}
    	

        double area1 = 0;
        double area2 = 0;
        for (int i = 0; i < N; i++) {
            area1 += Math.abs(x[i] * Math.sin(x[i]) + 0.1 * x[i]);
        }
        double res = area1;
        return res;
//        int N = x.length;
//        //double[] x = new double[N];
////        if(Parameters.moveModel == "MOVE"){
////        	x = ping_yi(pra, 10);
////        }
//
//        double area1 = 0;
//        if(Parameters.algoName == "ENBALANCE"){
//        	for (int i = 0; i < N; i++) {
//                area1 += Math.abs((x[i] - 10) * Math.sin((x[i] - 10)) + 0.1 * (x[i] - 10));
//            }
//        } else if(Parameters.algoName == "TSA"){
//        	for (int i = 0; i < N; i++) {
//                area1 += Math.abs(x[i] * Math.sin(x[i]) + 0.1 * x[i]);
//            }
//        }
//        
//        double res = area1;
//        if(Parameters.optimalModel.equals("MAX") ) return -res;
//        return res;
//        return res;
    }

}
