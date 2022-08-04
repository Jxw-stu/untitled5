package HouseDemo.HouseService;

import HouseDemo.HouseDomain.House;
//干活的，业务层
public class HouseService {
    private House[] houses;//定义一个对象数组当数据库用
    private int housenum=1;//房子的个数
    private int iditem=1;
    //构造器初始化
    public HouseService(int size){
        houses = new House[size];//允许由多少个对象
        //为单个对象开辟空间new
        houses[0] = new House(1,"jxw","15641231","湖州师范学院",200,"未");
    }

    public House[] list(){
        return houses;
    }
    public boolean add(House house){

        if (housenum>houses.length){
            System.out.println("超标了");
            return false;
        }


        houses[housenum] = house;
        housenum+=1;

        iditem++;
        house.setId(iditem);
        return true;
    }
    public boolean delete(int id){
        int index = -1;//记录要删的数组索引
        for (int i=0;i<housenum;i++){
            if (id==houses[i].getId()){//id不过是自增长的编号
                index = i;
            }
        }
        if (index==-1)return false;
        for (int i = index; i < housenum-1; i++) {
            houses[i] = houses[i+1];
        }
        houses[housenum-1]=null;
        housenum--;
        return true;
    }
    public House findById(int findeid){
        for (int i = 0; i < housenum; i++) {
            if (findeid==houses[i].getId()){
                return houses[i];
            }
        }
        return null;
    }
}
