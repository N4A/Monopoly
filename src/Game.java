//此类new出各个静态对象，方便数据调用
public class Game {
	static frontPanel eventFrontPanel = new frontPanel();
	static FrontPanelOfUseCard useCardFrontPanel = new FrontPanelOfUseCard();
	static PlayerPanel playerPanel = new PlayerPanel();
	static FrontPanelOfPropStore propStoreFrontPanel = new FrontPanelOfPropStore();
	static FrontPanelOfBank bankFrontPanel = new FrontPanelOfBank();
	static MessagePanel messagePanel = new  MessagePanel();
	static Card card = new Card();
	static Land land = new Land();
	static Ai ai = new Ai();
	static boolean isControl = false;
	static int j = 0;//匿名内部类循环数
}
