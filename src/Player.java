import java.awt.Image;
import java.io.*;
public class Player implements Serializable{
	String name;
	String image;
	String smallImage;
	int cash = 20000,point = 30,deposit = 20000,houseProperty = 0,totalMoney = cash + deposit;
	int x = 0, y = 0;//x,y�������λ������
	int positonOfPlayer = 0 ;
	int directionOfPlayer = 0;//����˳ʱ��
	int numberOfLand= 0;
	int[] numberOfcard = new int[]{10,10,10,10,10,10,10,10,10,10,10,10,10,10,10};
	String[] nameOfCard = new String[]{"������","ת��","·�Ͽ�","ң�����ӿ�","�ӶῨ","������","��Ǩ��","���ؿ�","�ڹ꿨","��Ʊ��","���޿�","��˰��","���ؿ�","����","����"};
	String[] house;
	Image[] imageOfWalk = new Image[12];	
	boolean isAI = false;
	boolean stop = false;//������
	boolean lottery = false;//��Ʊ��
	int turtle = 0;//�ڹ꿨
	int land = 0;
	int caishen = 0;//����
	int fushen = 0;//����
	public Player() {		
	}
}
