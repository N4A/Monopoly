import javax.swing.JDialog;


public class Monopoly {
	static int playerAmount = 1,totalAmount = 1,aiAmount = 0;
	static Player[] players;
	static MonopolyFrame0 monopolyFrame0;
	static MonopolyFrame monopolyFrame;
	//dialog���ڸ��ֹ��ܵ���
	static JDialog eventDialog = new JDialog(Monopoly.monopolyFrame,"�����¼�",true);
	static JDialog useCardDialog = new JDialog(Monopoly.monopolyFrame,"ʹ�ÿ�Ƭ",true);
	static JDialog bankDialog = new JDialog(Monopoly.monopolyFrame,"����",true);
	static JDialog propStoreDialog = new JDialog(Monopoly.monopolyFrame,"���ߵ�",true);
	static JDialog diceDialog = new JDialog(Monopoly.monopolyFrame,"������",true);
	static JDialog messageDialog = new JDialog(Monopoly.monopolyFrame,"��Ϣ��",false);
	static Music music = new Music();
	static Land land = new Land();
	//main ����
	public static void main(String[] args) {
		music.backMusic.loop();
		eventDialog.setLocation(410,230);
		eventDialog.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
		eventDialog.setSize(350, 260);
		eventDialog.setResizable(false);
		useCardDialog.setLocation(410,230);
		useCardDialog.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
		useCardDialog.setSize(455, 325);
		useCardDialog.setResizable(false);
		bankDialog.setLocation(410,230);
		bankDialog.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
		bankDialog.setSize(395, 298);
		bankDialog.setResizable(false);
		bankDialog.setVisible(false);	
		propStoreDialog.setLocation(410,230);
		propStoreDialog.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
		propStoreDialog.setSize(455, 325);
		propStoreDialog.setResizable(false);
		propStoreDialog.setVisible(false);
		diceDialog.add(new FrontPanelOfDice());
		diceDialog.setLocation(450,250);
		diceDialog.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
		diceDialog.setSize(350, 120);
		diceDialog.setResizable(false);
		diceDialog.setVisible(false);	 
		messageDialog.setLocation(410,230);
		messageDialog.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
		messageDialog.setSize(300, 100);
		messageDialog.setResizable(false);
		messageDialog.setVisible(false);
		monopolyFrame0 = new MonopolyFrame0();
	}
}
