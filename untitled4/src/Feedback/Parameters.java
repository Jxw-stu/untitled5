package Feedback;

public class Parameters {
    public static String  algoName = "";//TSA//BALANCE//ENBALANCE
    public static String  modelName = "";//函数模型名称
    public static int  debugModel = 0;//1为debug为真，0为debug为假
    public static double  alfa = 0.000001;//
    public static String  optimalModel = "MIN";//MAX:最大值；MIN最小值
    public static String  moveModel = "MOVE";//MOVE:平移；NOMOVE不平移
    
    public static double INF = 1.0e99;
    public static double  EPS = 1.0e-14;
    public static double  E = 2.7182818284590452353602874713526625;
    public static double  PI = 3.1415926535897932384626433832795029;
    //models:fBR/fCA/fGP/fRA/fSH/fHartman
    //applications:EP3/EP6/CUT
}
