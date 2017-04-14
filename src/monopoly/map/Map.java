package monopoly.map;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import monopoly.building.Bank;
import monopoly.building.Creature;
import monopoly.building.Land;
import monopoly.building.Roadblock;
import monopoly.map.Cell;
import monopoly.player.Player;
import monopoly.ui.controller.UIController;

/**
 * @author duocai
 * @date 2016年4月18日 下午9:20:40
 */
public class Map {
	private int width;
	private int height;
	private Collection<Cell> cells = new CopyOnWriteArrayList<Cell>();
	private List<Cell> eventCells = new CopyOnWriteArrayList<Cell>();
	private List<Player> players = new CopyOnWriteArrayList<>();
	private UIController uiController;

	public Map(int width, int height) {
		this.width = width;
		this.height = height;
	}

	public void addEventCell(Cell cell) {
		eventCells.add(cell);
	}

	public Cell getEventCell(int index) {
		return eventCells.get(index);
	}

	public Cell getCellAt(int x, int y) {
		return cells.stream().filter(item -> (item.getX() == x && item.getY() == y)).findFirst()
				.orElse(createCell(x, y));
	}

	/**
	 * create a new cell at position (x,y)
	 * 
	 * @param x
	 * @param y
	 * @return 2016年4月21日 上午8:30:16
	 */
	private Cell createCell(int x, int y) {
		Cell c = new Cell(this, x, y);
		cells.add(c);
		return c;
	}

	/**
	 * get cell which is n steps after the parameter cell.
	 * 
	 * @param cell
	 * @param n
	 * @return 2016年4月21日 上午8:29:23
	 */
	public Cell getCellAt(Cell cell, int n) {
		int max = eventCells.size();
		return eventCells.get((eventCells.indexOf(cell) + max + n) % max);
	}

	public UIController getUiController() {
		return uiController;
	}

	public void setUiController(UIController uiController) {
		this.uiController = uiController;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public String eventCellToTexture() {
		StringBuffer sb = new StringBuffer();
		int size = eventCells.size();
		for (int i = 0; i < size; i++) {
			sb.append(eventCells.get(i).toTexture());
		}
		return sb.toString();
	}

	public String toTexture() {
		StringBuffer sb = new StringBuffer();
		for (int x = 0; x < height; x++) {
			for (int y = 0; y < width; y++) {
				sb.append(getCellAt(x, y).toTexture());
			}
			sb.append("\n");
		}
		return sb.toString();
	}

	/**
	 * check bank or road block from parameter cell's position to n-1 steps
	 * later if there is no bank or road block, return return null; if there are
	 * banks and road block, return the object
	 * 
	 * @param cell
	 * @param n
	 * @return 2016年4月21日 上午9:49:56
	 */
	public Creature checkBankAndRoadblock(Cell cell, int n) {
		int max = eventCells.size();
		int pos = eventCells.indexOf(cell);
		int distance = Math.abs(n);
		int step = n / distance;

		for (int i = 1; i < distance; i++) {
			pos += step;
			List<Creature> creatures = eventCells.get((pos + max) % max).getCreatures();

			for (Creature creature : creatures) {
				if (creature instanceof Bank || creature instanceof Roadblock) {
					return creature;// meet bank or road block
				}
			}
		}

		return null;
	}

	/**
	 * get landlord who has most lands
	 * 
	 * @return 2016年4月22日 下午9:44:33
	 */
	public List<Player> getLandlord() {
		List<Player> list = new ArrayList<Player>();
		list.add(players.get(0));
		int max = players.get(0).getLandsNumber();
		for (int i = 1; i < players.size(); i++) {
			int cur = players.get(i).getLandsNumber();
			if (cur > max) {
				list = new ArrayList<Player>();
				list.add(players.get(i));
				max = cur;
			} else if (cur == max) {
				list.add(players.get(i));
			}
			// else do nothing
		}

		return list;
	}

	/**
	 * return the list of players that have least lands
	 * 
	 * @return 2016年4月23日 上午9:58:09
	 */
	public List<Player> getLeastLandOwner() {
		List<Player> list = new ArrayList<Player>();
		list.add(players.get(0));
		int min = players.get(0).getLandsNumber();
		for (int i = 1; i < players.size(); i++) {
			int cur = players.get(i).getLandsNumber();
			if (cur < min) {
				list = new ArrayList<Player>();
				list.add(players.get(i));
				min = cur;
			} else if (cur == min) {
				list.add(players.get(i));
			}
			// else do nothing
		}

		return list;
	}

	public List<Player> getPlayers() {
		return players;
	}

	/**
	 * get the distance between player and player2
	 * 
	 * @param player
	 * @param player2
	 * @return 2016年4月24日 下午3:40:57
	 */
	public int getDistance(Player player, Player player2) {
		return getDistance(player.getCell(), player2.getCell());
	}

	/**
	 * get distance between cell and cell2
	 * 
	 * @param cell
	 * @param cell2
	 * @return 2016年4月24日 下午5:10:21
	 */
	public int getDistance(Cell cell, Cell cell2) {
		int pos1 = eventCells.indexOf(cell);
		int pos2 = eventCells.indexOf(cell2);

		int distance = Math.abs(pos1 - pos2);

		if (distance > eventCells.size() / 2) {
			distance = eventCells.size() - distance;
		}
		return distance;
	}

	/**
	 * remove the parameter player
	 * 
	 * @param player
	 *            2016年4月25日 下午5:40:08
	 */
	public void rmPlayer(Player player) {
		players.remove(player);
	}

	/**
	 * calculate the cost of the whole street
	 * @param land
	 * @return
	 * 2016年4月26日 下午8:43:17
	 */
	public int getStreetPay(Land land) {
		Cell curCell = land.getCell();
		int cost;
		cost = land.getCost();
		
		int i = 0;
		while (true) {//back search
			i--;
			Cell cell = getCellAt(curCell, i);
			Creature creature = cell.getCreature(0);
			if (!(creature instanceof Land)) {
				break;
			}
			Land land2 = (Land)creature;
			if (land.getOwner() == land2.getOwner()) {
				cost += land2.getCost();
			}
			
		}
		
		i=0;
		while (true) {//forward search
			i++;
			Cell cell = getCellAt(curCell, i);
			Creature creature = cell.getCreature(0);
			if (!(creature instanceof Land)) {
				break;
			}
			Land land2 = (Land)creature;
			if (land.getOwner() == land2.getOwner()) {
				cost += land2.getCost();
			}
			
		}
		
		return (int)(cost*0.3);
	}
	
	/**
	 * get players whose distance between the parameter player is less than or equal to n
	 * @param player
	 * @param n
	 * @return
	 * 2016年4月28日 下午8:04:07
	 */
	public List<Player> getPlayersWithinDistance(Player player, int n) {
		List<Player> targets = new ArrayList<>();
		
		for (Player player2 : players) {
			int distance = getDistance(player, player2);
			if (distance <= n) {
				targets.add(player2);
			}
		}
		
		return targets;
	}
	
}
