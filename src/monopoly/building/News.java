package monopoly.building;

import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;

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
		StringBuffer sb = new StringBuffer(StrSrc.youReach.getValue() + StrSrc.newsName.getValue()+"\n");
		int news = (int) (Math.random() * 6);
		Map map = p.getMap();

		switch (news) {
		case 0: {// praise the landlord
			List<Player> players = map.getLandlord();
			for (Player player : players) {
				int landNumber = player.getLandsNumber();
				int prize = (int) (Math.random() * 20 * landNumber) * 5 + 500;
				player.setCash(player.getCash() + prize);
				sb.append(StrSrc.praiseLandlordNews.getValue() + player.getName() + " " + prize+"\n");
			}
			break;
		}
		case 1: {// help the player who own least lands
			List<Player> players = map.getLeastLandOwner();
			for (Player player : players) {
				int landNumber = player.getLandsNumber();
				int prize = (int) (Math.random() * 20 * (7 - landNumber)) * 5 + 500;
				player.setCash(player.getCash() + prize);
				sb.append(StrSrc.helpLeastLandOwnerNews.getValue() + player.getName() + " " + prize+"\n");
			}
			break;
		}
		case 2: {// give interest
			sb.append(StrSrc.bankNews.getValue()+"\n");
			List<Player> players = map.getPlayers();
			for (Player player : players) {
				int deposit = player.getDeposit();
				player.setDeposit(deposit += deposit / 10);
			}
			break;
		}
		case 3: {// submit tax
			sb.append(StrSrc.taxNews.getValue()+"\n");
			List<Player> players = map.getPlayers();
			for (Player player : players) {
				int deposit = player.getDeposit();
				player.setDeposit(deposit -= deposit / 10);
			}
			break;
		}
		case 4: {// get cards
			sb.append(StrSrc.cardNews.getValue()+"\n");
			List<Player> players = map.getPlayers();
			for (Player player : players) {
				Card card = ToolStore.getRandomCard();
				player.addCard(card);
				sb.append(StrSrc.congatulatePlayer.getValue() + player.getName() + StrSrc.get.getValue()
						+ StrSrc.oneCard.getValue() + card.getName()+"\n");
			}
			break;
		}
		case 5: {
			sb.append(StrSrc.disaster.getValue() + "\n");
			int rm = (int) (Math.random() * 2);
			if (rm == 0) {
				sb.append(StrSrc.you.getValue()+StrSrc.hurt.getValue() + "\n");
				p.setHurt(2);
				map.moveToHospital(p);
			}
			else
				sb.append(StrSrc.noHurt.getValue() + "\n");			
			break;
		}
		default:
			break;
		}
		uiController.showMassage(sb.toString());
	}

	@Override
	public String getInfo() {
		return StrSrc.type.getValue() + ":" + type.getValue();
	}
	@Override
	public JButton toSwing() {
		JButton button = new JButton(new ImageIcon("icons/news.png"));
		button.addActionListener(infoListener);
		return button;
	}
}
