package monopoly.building;

import java.util.List;

import monopoly.card.Card;
import monopoly.map.Cell;
import monopoly.map.Map;
import monopoly.player.Player;
import monopoly.ui.controller.UIController;
import monopoly.util.StrSrc;

/**
 * @author duocai
 * @date 2016年4月18日 下午9:30:39
 */
public class News extends Creature {

	/**
	 * @param cell
	 */
	public News(Cell cell) {
		super(cell, CreatureType.news);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see monopoly.map.Creature#toTexture()
	 */
	@Override
	public String toTexture() {
		// TODO Auto-generated method stub
		return StrSrc.news.getValue();
	}

	@Override
	public void execute(Player p, UIController uiController) {
		uiController.showMassage(StrSrc.youReach.getValue() + StrSrc.newsName.getValue());
		int news = (int) (Math.random() * 5);
		Map map = p.getMap();

		switch (news) {
		case 0: {// praise the landlord
			List<Player> players = map.getLandlord();
			for (Player player : players) {
				int landNumber = player.getLandsNumber();
				int prize = (int) (Math.random() * 20 * landNumber) * 5 + 500;
				player.setCash(player.getCash() + prize);
				uiController.showMassage(StrSrc.praiseLandlordNews.getValue() + player.getName() + " " + prize);
			}
			break;
		}
		case 1: {// help the player who own least lands
			List<Player> players = map.getLeastLandOwner();
			for (Player player : players) {
				int landNumber = player.getLandsNumber();
				int prize = (int) (Math.random() * 20 * (7 - landNumber)) * 5 + 500;
				player.setCash(player.getCash() + prize);
				uiController.showMassage(StrSrc.praiseLandlordNews.getValue() + player.getName() + " " + prize);
			}
			break;
		}
		case 2: {// give interest
			uiController.showMassage(StrSrc.bankNews.getValue());
			List<Player> players = map.getPlayers();
			for (Player player : players) {
				int deposit = player.getDeposit();
				player.setDeposit(deposit += deposit / 10);
			}
			break;
		}
		case 3: {// submit tax
			uiController.showMassage(StrSrc.taxNews.getValue());
			List<Player> players = map.getPlayers();
			for (Player player : players) {
				int deposit = player.getDeposit();
				player.setDeposit(deposit -= deposit / 10);
			}
			break;
		}
		case 4: {// get cards
			uiController.showMassage(StrSrc.cardNews.getValue());
			List<Player> players = map.getPlayers();
			for (Player player : players) {
				Card card = ToolStore.getRandomCard();
				player.addCard(card);
				uiController.showMassage(StrSrc.congatulatePlayer.getValue() + player.getName() + StrSrc.get.getValue()
						+ StrSrc.oneCard.getValue() + card.getName());
			}
		}

		default:
			break;
		}
	}

	@Override
	public String getInfo() {
		return StrSrc.type.getValue() + ":" + type.getValue();
	}

}
