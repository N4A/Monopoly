package monopoly.card;

import monopoly.map.Map;
import monopoly.player.Player;
import monopoly.ui.controller.UIController;

/**
 * @author duocai
 * @date 2016年4月18日 下午9:37:03
 */
public abstract class Card {
	
	protected String name;
	protected String description;
	
	public Card(String name, String description) { 
		this.name = name;
		this.description = description;
	}
	
	/**
	 * parameter player use this card and it may influence the map or creature on the map
	 * @param c
	 * @param map
	 * 2016年4月21日 下午7:30:388
	 */
	public abstract void execute(Player player, Map map, UIController uiController);
	
	public String getName() {
		return name;
	}
	
	public String getDescription() {
		return description;
	}
}
