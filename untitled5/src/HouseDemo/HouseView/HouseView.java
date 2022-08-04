package HouseDemo.HouseView;

import HouseDemo.HouseDomain.House;
import HouseDemo.HouseService.HouseService;
import HouseDemo.HouseUtility.Utility;

public class HouseView {
    private boolean loop = true;
    private char key = ' ';
    HouseService houseService = new HouseService(10);
    public void mainMenu(){
        do {
            System.out.println("+++++++菜单++++++");
            System.out.println("\t\t\t1 create房源");
            System.out.println("\t\t\t2 find房子");
            System.out.println("\t\t\t3 删除房子");
            System.out.println("\t\t\t4 修改房屋信息");
            System.out.println("\t\t\t5 房屋列表");
            System.out.println("6 退出");
            System.out.println("输入1-6");
            key = Utility.readChar();
            switch (key){
                case '1':
                    System.out.println("create房源");
                    addhouse();
                    break;
                case '2':
                    System.out.println("find房子");
                    findhouse();
                    break;
                case '3':
                    System.out.println("删除房子");
                    deletehouse();
                    break;
                case '4':
                    System.out.println("修改房屋信息");
                    updatehouse();
                    break;
                case '5':
                    System.out.println("房屋列表");
                    listhouse();
                    break;
                case '6':
                    System.out.println("退出嘛");
                   char c =  Utility.readConfirmSelection();
                   if (c=='Y'){
                       loop= false;
                   }

                    break;
            }
        }while(loop);
    }
    public void listhouse(){
        System.out.println("===房屋列表===");
        System.out.println("id\t\tname\t\tphone\t\taddress\t\trent\t\tstate");
        House[] houses = houseService.list();//查read
        for (int i = 0; i < houses.length; i++) {
            if (houses[i]==null)break;
            System.out.println(houses[i]);
        }
    }
    public void addhouse(){
        System.out.println("增加");
        System.out.println("姓名");
        String name = Utility.readString(8);
        System.out.println("电话号码");
        String num = Utility.readString(12);
        System.out.println("地址");
        String address = Utility.readString(16);
        System.out.println("月租");
        int rent = Utility.readInt(5);
        System.out.println("状态");
        String state = Utility.readString(4);
        House house = new House(0, name, num, address, rent, state);
        if (houseService.add(house)){//create
            System.out.println("成果加入");
        }
        else System.out.println("失败");
    }
    public void deletehouse(){
        System.out.println("======删除======");
        System.out.println("删除编号（-1退出）");
        int delid = Utility.readInt();
        if (delid==-1){
            System.out.println("放弃删除");
            return;
        }
        System.out.println("确认呢删除");
        char choice = Utility.readConfirmSelection();
        if (choice=='Y'){
            if (houseService.delete(delid)){
                System.out.println("删掉了呢");
            }
        }
        else{
            System.out.println("结束");
        }

    }
    public void findhouse(){
        System.out.println("想要看的编号");
        int id = Utility.readInt();
        House house = houseService.findById(id);
       if (house!=null)System.out.println(house);
       else
           System.out.println("mei you");
    }
    public void updatehouse(){
        System.out.println("想要修改哪个");
        int id  = Utility.readInt();
        if (id==-1){
            System.out.println("睡觉吧");return;
        }
        else{
            House house = houseService.findById(id);//这边已经拿到这个对象了
            if (house==null){
                System.out.println("没找到");
            }
            else{
                System.out.println("姓名"+house.getName()+" 是否要修改？");
                String name = Utility.readString(8,"");
                if (!"".equals(name)){
                    house.setName(name);
                }
                System.out.println("电话"+house.getPhone()+" 是否要修改？");
                String phone = Utility.readString(8,"");
                if (!"".equals(phone)){
                    house.setPhone(phone);
                }
                System.out.println("address"+house.getAddress()+" 是否要修改？");
                String address = Utility.readString(8,"");
                if (!"".equals(address)){
                    house.setAddress(address);
                }
                System.out.println("rent"+house.getRent()+" 是否要修改？");
                int rent = Utility.readInt(-1);
                if (-1!=(rent)){
                    house.setRent(rent);
                }
                System.out.println("state"+house.getState()+" 是否要修改？");
                String zuj = Utility.readString(8,"");
                if (!"".equals(zuj)){
                    house.setState(zuj);
                }

            }
        }
    }
}
