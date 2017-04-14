package monopoly.player;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import monopoly.building.Bank;
import monopoly.building.Creature;
import monopoly.building.CreatureType;
import monopoly.building.Land;
import monopoly.building.LandType;
import monopoly.building.ToolStore;
import monopoly.card.Card;
import monopoly.feature.LotterySystem.Lottery;
import monopoly.map.Cell;
import monopoly.map.Map;
import monopoly.ui.controller.UIController;
import monopoly.util.StrSrc;

/**
 * @author duocai
 * @date 2016年4月12日 上午11:37:59
 * 
 */
public abstract class Player extends Creature {
	protected String name;
	protected int cash;
	protected int coupond;
	protected int deposit;
	protected PlayerType type;
	private LandType landType;
	private Direction direction;
	protected UIController uiController;
	protected Map map;
	private List<List<Card>> cards;
	private List<Land> lands;
	private List<Lottery> lotteries;
	private int controlDice = 0;
	private int tortoise = 0;

	public Player(Map map, Cell cell, UIController uiController, PlayerType playerType, String name,
			LandType landType) {
		super(cell, CreatureType.Player);
		this.type = playerType;
		this.setLandType(landType);
		this.direction = Direction.forward;
		this.uiController = uiController;
		this.map = map;
		this.name = name;
		this.cash = 20000;
		this.coupond = 30;
		this.deposit = 0;
		this.cards = new CopyOnWriteArrayList<>();
		this.lands = new CopyOnWriteArrayList<>();
		this.lotteries = new CopyOnWriteArrayList<>();
		initCards();
	}

	/**
	 * input steps
	 * 
	 * @return 2016年4月23日 下午3:53:22
	 */
	public abstract String chooseDitance();

	/**
	 * let player input an integer between [start,end]
	 * 
	 * @param start
	 * @param end
	 * @return 2016年4月21日 上午8:03:37
	 */
	protected abstract int inputInt(int start, int end);

	/**
	 * let player input a string of which length between [minlen,maxlen]
	 * 
	 * @param minLen
	 * @param maxLen
	 * @return 2016年4月21日 上午8:07:33
	 */
	protected abstract String inputString(int minLen, int maxLen);

	/**
	 * let player choose what to do
	 * @param max
	 * @param min 
	 * 
	 * @return 2016年4月22日 下午3:13:36
	 */
	public abstract int operate(int min, int max);

	/**
	 * choose whether to buy a new land a real player and an AI player may have
	 * different strategy
	 * 
	 * @param land
	 * @param uiController
	 *            2016年4月22日 下午12:01:15
	 */
	public abstract String chooseBuyLand();

	/**
	 * choose whether to upgrade the parameter land
	 * 
	 * @param land
	 * @param uiController
	 *            2016年4月22日 下午12:02:12
	 */
	public abstract String chooseUpgradeLand();

	/**
	 * choose the card to use 2016年4月22日 下午3:27:40
	 */
	public abstract int chooseCard(int min, int max);

	/**
	 * choose how to operate bank
	 * 
	 * @return 2016年4月22日 下午3:31:58
	 */
	public abstract String selectBankOperation();

	/**
	 * choose how much money to withDraw
	 * 
	 * @param min
	 * @param max
	 * @return 2016年4月22日 下午3:33:23
	 */
	public abstract int withDrawMoney(int min, int max);

	/**
	 * choose how much money to save
	 * 
	 * @param min
	 * @param max
	 * @return 2016年4月22日 下午3:33:49
	 */
	public abstract int saveMoney(int min, int max);

	/**
	 * choose to buy which card
	 * 
	 * @param min
	 * @param max
	 * @return 2016年4月23日 上午11:38:32
	 */
	public abstract int chooseBuyCard(int min, int max);

	/**
	 * buy a lottery 2016年4月22日 下午2:13:51
	 */
	public abstract String chooseBuyLottery();
	
	/**
	 * choose the player to make him change direction
	 * @param min
	 * @param max
	 * @return
	 * 2016年4月24日 下午3:53:48
	 */
	public abstract int chooseCDPlayer(int min, int max);
	
	/**
	 * choose dice number
	 */
	public abstract int chooseDice(int min, int max);
	
	/**
	 * choose road block distance
	 * @param min
	 * @param max
	 * @return
	 * 2016年4月24日 下午4:45:53
	 */
	public abstract int chooseRoadblockDistance(int min, int max);
	
	/**
	 * handle stock 
	 * @param min
	 * @param max
	 * @return
	 * 2016年4月25日 下午11:04:50
	 */
	public abstract String handleStock(int min, int max);

	/*
	 * the follows are implemented methods
	 **/

	/**
	 * use the parameter card
	 * 
	 * @param c
	 *            2016年4月22日 下午3:26:49
	 */
	public void useCard(Card c, Map map, UIController uiController) {
		c.execute(this, map, uiController);
	}

	@Override
	public String toTexture() {
		return type.getValue();
	}

	/**
	 * get by other players' land and pay for it
	 * 
	 * @param land
	 * @param uiController
	 *            2016年4月22日 下午12:04:48
	 * @return
	 */
	public int payForLand(Land land) {
		int money = map.getStreetPay(land);
		
		money = demandCash(money);//check if the player has no cash

		Player owner = land.getOwner();
		owner.setCash(owner.getCash() + money);
		return money;
	}
	
	/**
	 * check and sub the player's cash by money
	 * 
	 * 2016年4月25日 下午6:08:52
	 * @param money 
	 */
	private int demandCash(int money) {
		cash -= money;//sub money first
		if (cash < 0) {//no cash
			uiController.showMassage(StrSrc.noCash00.getValue());
			cash += deposit;
			deposit = 0;
			
			if (cash < 0) {//sell land
				uiController.showMassage(StrSrc.noDeposit.getValue());
				for (Land land : lands) {
					cash += land.getCost();
					rmLand(land);
					land.setOwner(null);
					land.setType(LandType.onSell);
					uiController.showMassage(land.getCell().getName() + StrSrc.sellLand.getValue());
					if (cash >= 0) {
						break;
					}
				}
				
				if (cash < 0) {
					giveUp();
					uiController.showMassage(StrSrc.lose.getValue());
					return money + cash;
				}
			}
		}
		return money;
	}

	/**
	 * player trigger the event at parameter cell
	 * 
	 * @param cell
	 *            2016年4月21日 上午8:39:56
	 */
	public void triggerEvent(Cell cell) {
		cell.handleEvent(this);
	}

	/**
	 * let player move to the parameter cell
	 * 
	 * @param cell
	 *            2016年4月21日 上午8:32:54
	 */
	public void moveTo(Cell cell) {
		cell.addCreature(this);
		this.setCell(cell);
		this.triggerEvent(cell);
	}

	/**
	 * let player throw dice and get return point this need no strategy and
	 * choice
	 * 
	 * @return 2016年4月21日 上午8:15:06
	 */
	public int throwDice() {
		int dice;
		if (controlDice > 0) {//people use control dice
			dice = controlDice;
		}
		else {
			dice = (int) (Math.random() * 6) + 1;
		}
		uiController.showMassage(StrSrc.diceNumber.getValue() + ":" + dice);
		return dice*direction.getValue();
	}

	/**
	 * let player move n steps this need no strategy and choice
	 * 
	 * @param n
	 *            2016年4月21日 上午8:16:07
	 */
	public void move(int n) {
		if (tortoise > 0) {//be tortoise
			n = n/Math.abs(n);
			tortoise--;
		}
		
		Cell cell = this.getCell();
		Creature creature = map.checkBankAndRoadblock(cell, n);// bank event and road block first
		
		if (creature == null) {// no bank or road block
			uiController.movePlayer(this, n, map.getCellAt(cell, n));
			cell.rmCreature(this);//rm from this cell
			this.setCell(null);
			this.moveTo(map.getCellAt(cell, n));
			return ;
		}
		
		int distance = map.getDistance(cell, creature.getCell())*direction.getValue();
		uiController.movePlayer(this, distance, creature.getCell());
		cell.rmCreature(this);//rm from this cell
		this.setCell(null);
		this.moveTo(creature.getCell());// move to bank or road block first	
		
		if (creature instanceof Bank) {			
			this.move(n - distance);// bank event is over , continue to move to original destination
		}
	}

	/**
	 * maybe something interest will happen
	 */
	@Override
	public void execute(Player p, UIController uiController) {
		if (p == this) {//self
			return ;
		}
		uiController.showMassage(StrSrc.you.getValue() + StrSrc.meet.getValue() +
				StrSrc.playerName.getValue() + this.getName() + "\n" 
				+ StrSrc.fight.getValue());
	}

	// get and setter methods
	public PlayerType getType() {
		return type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getCash() {
		return cash;
	}

	public void setCash(int cash) {
		this.cash = cash;
	}

	public int getCoupond() {
		return coupond;
	}

	public void setCoupond(int coupond) {
		this.coupond = coupond;
	}

	public int getDeposit() {
		return deposit;
	}

	public void setDeposit(int deposit) {
		this.deposit = deposit;
	}

	public int getHouseProperty() {
		int money = 0;
		for (Land land : lands) {
			money += land.getLevel() * 1000;
		}
		return money;
	}

	public void addCard(Card card) {
		boolean has = false;
		for (List<Card> list : cards) {
			if (!list.isEmpty()) {
				if (card.getName().equals(list.get(0).getName())) {
					list.add(card);
					has = true;
					break;
				}
			}
		}

		// player has not this kind of card;
		if (!has) {
			List<Card> cardsList = new ArrayList<>();
			cardsList.add(card);
			this.cards.add(cardsList);
		}
	}

	public void rmCard(Card card) {
		for (List<Card> list : cards) {
			if (!list.isEmpty()) {
				if (card.getName().equals(list.get(0).getName())) {
					list.remove(card);
					if (list.isEmpty()) {
						cards.remove(list);
					}
				}
			}
		}
	}

	public void addLand(Land land) {
		this.lands.add(land);
	}

	public void rmLand(Land land) {
		this.lands.remove(land);
	}

	public int getLandsNumber() {
		return this.lands.size();
	}

	public List<Lottery> getLotteries() {
		return lotteries;
	}

	public void addLottery(Lottery lottery) {
		this.lotteries.add(lottery);
	}

	public void clearLottery() {
		this.lotteries = new ArrayList<Lottery>();
	}

	public Direction getDirection() {
		return direction;
	}

	public int getTotalMoney() {
		return cash + deposit + getHouseProperty();
	}

	public LandType getLandType() {
		return landType;
	}

	public void setLandType(LandType landType) {
		this.landType = landType;
	}

	public Map getMap() {
		return map;
	}

	public List<List<Card>> getCards() {
		return cards;
	}

	/**
	 * give up
	 */
	public void giveUp() {
		this.getCell().rmCreature(this);
		map.rmPlayer(this);

		for (Land land : this.lands) {
			land.setType(LandType.onSell);
		}
	}

	/**
	 * give current player 5 random cards 2016年4月23日 下午9:20:55
	 */
	private void initCards() {
		for (int i = 0; i < 5; i++) {
			addCard(ToolStore.getRandomCard());
		}
	}

	public void changeDirection() {
		switch (direction) {
		case forward:
			direction = Direction.back;
			break;
		case back:
			direction = Direction.forward;
			break;
		default:
			break;
		}
	}
	
	/**
	 * use tortoise card to this player
	 * 2016年4月28日 下午7:56:00
	 */
	public void beTortoise() {
		tortoise = 3;
	}

	public void setControlDice(int controlDice) {
		this.controlDice = controlDice;
	}
}
