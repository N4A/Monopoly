import java.awt.Image;
import java.io.*;
public class Player implements Serializable{
	String name;
	String image;
	String smallImage;
	int cash = 20000,point = 30,deposit = 20000,houseProperty = 0,totalMoney = cash + deposit;
	int x = 0, y = 0;//x,y代表玩家位置坐标
	int positonOfPlayer = 0 ;
	int directionOfPlayer = 0;//代表顺时针
	int numberOfLand= 0;
	int[] numberOfcard = new int[]{10,10,10,10,10,10,10,10,10,10,10,10,10,10,10};
	String[] nameOfCard = new String[]{"均富卡","转向卡","路障卡","遥控骰子卡","掠夺卡","滞留卡","拆迁卡","购地卡","乌龟卡","彩票卡","怪兽卡","查税卡","土地卡","福神卡","财神卡"};
	String[] house;
	Image[] imageOfWalk = new Image[12];	
	boolean isAI = false;
	boolean stop = false;//滞留卡
	boolean lottery = false;//彩票卡
	int turtle = 0;//乌龟卡
	int land = 0;
	int caishen = 0;//财神卡
	int fushen = 0;//福神卡
	public Player() {		
	}
}
