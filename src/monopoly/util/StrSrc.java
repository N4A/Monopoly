package monopoly.util;

/**
 * @author duocai
 * @date 2016年4月20日 下午9:17:34
 * string source
 */
public enum StrSrc {
	//game ui
	title("大富翁——群山环绕的湖"),startGame("点我开始游戏"),
	//game ui menu
	file("文件"),load("加载进度"),save("存储进度"),
	tool("操作"),card("卡片"),
	feature("其它功能"),stock("股票市场"),
	//player name
	p1("千与千寻"),p2("崛越二郎"),p3("娜乌西卡"),
	//player
	player1("□"),player2("○"),player3("△"),player4("◇"),
	//land
	landP1("■"),landP2("●"),landP3("▲"),landP4("◆"),landOnsell("◎"),landNone("空"),
	//building show on the map
	bank("银"),toolStore("道"),lotteryStore("彩"),blank("　"),cardStation("卡"),couponStaion("券"),news("新"),
	roadblock("※"),hospital("医"),
	//building name
	bankName("银行"),toolStoreName("道具店"),lotteryStoreName("彩票店"),cardStationName("赠卡处"),couponStaionName("赠点券处"),
	newsName("新闻点"),landNoneName("空地"),landName("土地"),playerName("玩家"),roadBlockName("路障"),
	hospitalName("医院"),
	//card name
	averageCashCard("均富卡"),billikenCard("福神卡"),buyLandCard("购地卡"),changeDirectionCard("转向卡"),
	controlDiceCard("遥控骰子卡"),inspectTaxCard("查税卡"),landCard("土地卡"),lotteryCard("彩票卡"),monsterCard("怪兽卡"),
	plunderCard("掠夺卡"),removeCard("拆迁卡"),roadblockCard("路障卡"),stayCard("滞留卡"),tortoiseCard("乌龟卡"),wealthCard("财富卡"),
	
	//card description
	averageCashCardDes("将所有人的现金平均分配"),
	buyLandCardDes("强行用现价购买自己当前所在位置的土地，当然发动者不能购买自己的房屋"),
	changeDirectionCardDes("对自己或距离自己五步以内的对手使用，使目标掉转方向"),
	controlDiceCardDes("对自己使用，使用时可以任意控制骰子的结果，结果只能是1-6"),
	plunderCardDes("对距离自己五步以内的对手使用，随机将对手的一张卡牌据为己有"),
	roadBlockCardDes("可以在前后 8 步之内安放一个路障，任意玩家经过路障时会停在路障所在位置不能前行"),
	stayCardDes("对自己使用，该回合停留在原地，并再次触发原地事件"),
	tortoiseCardDes("对自己或距离自己五步以内的对手使用，使目标每次只能走一步，持续三回合"),
	
	//cell name
	cell1("北大"),cell2("清华"),cell3("复旦"),cell4("上交"),cell5("北大医学部"),
	cell6("人大"),cell7("中科大"),cell8("浙大"),cell9("南大"),cell10("北航"),
	cell11("北师"),cell12("对外经贸"),cell13("南开"),cell14("哈工大"),cell15("央财"),
	cell16("上交医学部"),cell17("北外"),cell18("天大"),cell19("同济"),cell20("北理"),
	cell21("北邮"),cell22("厦大"),cell23("上财"),cell24("武大"),cell25("西交"),
	cell26("北交"),cell27("中国政法"),cell28("中国传媒"),cell29("东南"),cell30("北科"),
	cell31("华师"),cell32("山东大学"),cell33("宁波诺丁汉"),cell34("华科"),cell35("川大"),
	cell36("上外"),cell37("农大"),cell38("北语"),cell39("西南财经"),cell40("北京中医药"),
	cell41("大连理工"),cell42("西北工业"),cell43("首都医科"),cell44("北林"),cell45("中南"),
	cell46("北化"),cell47("电子科技大"),cell48("哈佛"),cell49("666"),cell50("233"),
	cell51("Orz"),cell52("en"),cell53("\\(^o^)/"),cell54("Y(^o^)Y"),cell55("~~~^_^~~~"),
	cell56("O(∩_∩)O~~"),cell57("<(￣︶￣)>"),cell58("(～￣▽￣)～"),cell59("o(*≧▽≦)ツ"),cell60("o(*≧▽≦)ツ┏━┓"),
	
	//player properties
	cash("现金"),deposit("存款"),coupond("点券"),you("你"),playerInfoNotice("玩家资产信息如下："),playerInfoTitle("玩家名\t点券\t现金\t存款\t房产\t资产总额"),
	housePropery("房产"),totalMoney("总产"),throwDice("点我掷骰子前进"),
	//land properties
	landLevel("级"),type("类型"),name("名字"),owner("拥有者"),level("等级"),initPrice("初始价格:"),onsellState("待售状态"),
	
	
	//direction
	clockwise("顺时针方向"),anticlockwise("逆时针方向"),
	
	//stock system
	stock1("壳牌"),stock2("诺贝尔"),stock3("宝丽华"),stock4("通用"),stock5("亨氏"),
	stock6("哈雷"),stock7("IBM"),stock8("瞻博"),stock9("大通"),stock10("美赞成"),
	id("序号"),stockName("股票名"),price("价格"),status("涨跌幅"),ownNumber("持有数"),stockLeft("剩余量"),
	stockNotice("本日股票信息如下："),notEnoughDepositAndCash("您的存款和现金不足"),buyStockSuccess("购买股票成功"),noSock("没有该股票"),
	stockNotEnough("你持有的股票量不足"),sellStockSuccess("成功售出股票"),stockLeftNotEnough("本股余量不足"),
	stockoperate("输入 b x n 表示买入序号为 x 的股票 n 股， s x n 表示卖出序号为 x 的股票 n股(x 退出):"),
	stockWeekendNotice("股票周末休市，不能交易"),date("日期"),choose("请选择你要进行的交易："),buy("购买"),
	sell("出售"),cancel("取消"),inputNum("请输入数量:"),
	//week
	sunday("星期天"),monday("星期一"),tuesday("星期二"),wensday("星期三"),thursday("星期四"),friday("星期五"),saturday("星期六"),
	
	//handle process string
	congratulateYou("恭喜你"),congatulatePlayer("恭喜玩家"),get("获得了"),youReach("你到达"),back("返回"),invalidInput("非法输入"),
	intNeed("你应当输入一个整数"),youForward("你前进了"),
	noCash("你的现金不足"),whetherSpend("是否花费"),payToOwner("你支付了玩家"),youBuy("你成功购入"),welcomeNext("欢迎下次光临"),
	exit("输入任意字符退出"),giveUp("放弃"),toady("今天是"),now("现在是"),oprateTime("的操作时间."),forwardDirection("你的前进方向是"),
	startLine("==============================  游  戏  开  始  ==============================="),
	choosePNum("选择玩家数目(2-4)："),
	input("输入"),
	nameLen("的姓名(长度2-7)："),
	operationsNotice("您现在可进行如下操作："),
	operation1("0 - 查看地图"),operation2("1 - 查看原始地图"),operation3("2 - 使用道具"),operation4("3 - 前方10步内示警"),
	operation5("4 - 查看前后指定步数的具体信息"),operation6("5 - 查看玩家的资产信息"),operation7("6 - 想看的都看了，心满意足地掷色子"),
	operation8("7 - 认输"),operation9("8 - 查看股票"),operation10("9 - 查看我的彩票"),
	pleaseChoose("请选择："),
	winGame("取得了最终的胜利"),
	diceNumber("骰子点数"),reach("到达"),
	
	//player to player
	meet("遇见了"),fight("两人之间发生了激烈的拳脚斗殴"),
	
	//use card
	useCardNotice("输入对应序号选择相应卡片（输入0退出）"),choosePlayerNotice("输入对应序号选择玩家（输入0退出）"),
	useCardsuccess("成功使用卡片:"),cantUseCard("不能使用该卡片"),cancelUseCard("您取消使用该卡片"),
	plunderSuccess("您成功抢了一张"),inputDice("请输入你想要的点数（1-6）："),roadblockInput("请输入要在几格（至多8格）远设置路障，后方用负数"),
	roadblockNotice("你遇到了路障,被拦在此地"),buyLandCardWarning("您真的要用购地卡买自己的土地吗!!!"),
	noBlockHere("不可以在脚下设置路障"),
	
	
	//bank process
	bankYouOwn("你当前拥有:\n"),bankNotice("w取款，s存款，x不操作。"),bankSave("请输入存款金额"),bankWithDraw("请输入取款金额"),bankSucced("操作成功，欢迎下次光临`.`"),
	bankGiveInterest("每月开始，银行发利息啦（计入存款）"),bankGetInterest("获得利息"),
	
	//buy lottery process
	chooseBuyLottery("是否购买彩票输入 y or n"),lotterySuccess("恭喜您成功购买一张彩票,回去刮开看看吧"),lotteryDraw("新月伊始，彩票开奖了。"),lotteryWin("本期中奖号码为"),
	lotteryNotice("您的彩票如下："),noLotteryLeft("对不起，彩票已售完，请等下月"),
	noLottery("您还没有彩票"),leftLottery("彩票还剩下"),
	
	//land procell
	landOnsellName("待售土地"),landOfSelf("自己的土地"),landSellSuccess("购买土地成功,当前土地为"),
	buyland("购买该土地"),upgradeLand("升级该土地"),landInputNotice("输入 y or n"),landUpgradeSeccess("升级土地成功,当前土地为"),
	landFee("过路费"),noCash00("你的现金已不足，现系统同自动将银行存款转为现金。"),
	noDeposit("你的银行存款也不足，现系统自动变卖你的田产。"),sellLand("的土地被卖出"),lose("您的土地也不购卖，你破产了，失败离场"),
	topLevel("土地已满级"),
	
	//news process
	praiseLandlordNews("公开表扬第一地主"),helpLeastLandOwnerNews("公开补助土地最少者"),bankNews("银行加发储金红利每个人得到存款 10%"),
	taxNews("所有人缴纳财产税 10%"),cardNews("每人得到一张卡片"),disaster("发生天灾："),hurt("不幸受伤。"),
	noHurt("庆幸没有伤害。"),inHospital("在医院疗伤中"),
	
	//tool store process
	noCoupond("你的点券不足。"),chooseBuyCard("请输入对应序号选择你要购买的卡(输入0退出)："),
	
	//give card station process
	cardName("卡片"),oneCard("一张"),
	
	//input
	intRange("输入整数应属于"),strLenRange("字符串长度应属于"),
	
	//show info
	ndistanceNotice("请输入指定步数，向前为正，向后为负，最多查看12格。(x 退出)"),warning("前方十步内警告信息如下："),
	step("步"),foward("前方"),hasBank("有银行"),hasRoadblock("有路障");
	
	private String value;
	
	private StrSrc(String value) {
		this.value = value;
	}
	
	public String getValue() {
		return this.value;
	}
}
