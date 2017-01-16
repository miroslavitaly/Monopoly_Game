import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class monopolyGame {

	public static void main(String[] args) {
		Scanner sc1 = new Scanner(System.in);
		System.out.println("How many players will play from 1 to 4 ?");
		int howManyPlayers = sc1.nextInt(5);

		String[] playerName = new String[howManyPlayers];
		for (int i = 0; i <= playerName.length - 1; i++) {
			Scanner sc2 = new Scanner(System.in);
			System.out.println("Enter the name of the player " + (i + 1) + " :");
			String name = sc2.next();
			playerName[i] = name;
		}
		int[] player = new int[howManyPlayers];
		for (int i = 0; i < player.length; i++) {
			player[i] = i;
		}
		int[] sum = new int[howManyPlayers];
			for (int i = 0; i < sum.length; i++) {
				sum[i] = 1000;
			}
		int[] positionPlayer = new int[howManyPlayers];
		int[] property = new int[40];
			for (int i = 0; i < property.length; i++) {
				property[i] = getValue(i);
			}
		String[] positionName = new String[40];
		for (int i = 0; i < positionName.length; i++) {
			positionName[i] = getName(i);
		}
		int[] positionPrise = { 0, 0, 50, 60, 200, 150, 0, 100, 100, 120, 0, 150, 150, 180, 150, 150, 0, 200, 200, 220,
				               0, 240, 250, 250, 0, 150, 150, 260, 260, 280, 0, 300, 300, 320, 0, 150, 0, 360, 400, 300 };
		boolean runGame = true;
		int playersInGame = howManyPlayers;
		int winner = 0;
		
		while (runGame) {
			for (int j = 0; j <= player.length - 1; j++) {
				if (sum[j] <= 0) {
					continue;
				}
				System.out.println(playerName[j] + " , its your turn! Press enter!");
				Scanner sc3 = new Scanner(System.in);
				char run = sc3.next(".").charAt(0);

				Random rand = new Random();
				int dice = rand.nextInt(12)+1;
				System.out.println("The sum of the dice: " + dice);

				System.out.println("Previous   position:" + positionName[positionPlayer[j]]);
				sum[j] += sumPassStart(positionPlayer[j], dice);
				positionPlayer[j] = passStartDice(positionPlayer[j], dice);
				System.out.println("Current    position:" + positionName[positionPlayer[j]]);
				if (property[positionPlayer[j]] == 6) {
					System.out.println(
							"Position is free to buy! Prise :  " + positionPrise[positionPlayer[j]] + " dollars!");
					System.out.println("On your account has: " + sum[j] + " dollars!");
					if ((property[positionPlayer[j]] == 6) && (sum[j] >= positionPrise[positionPlayer[j]])) {
						System.out.println(playerName[j] + " Do you want to buy? " + " y - for Yes ,or  n - for No");
						Scanner sc4 = new Scanner(System.in);
						char bayOrNo = sc4.next(".").charAt(0);
						switch (bayOrNo) {
						case 'y':
							property[positionPlayer[j]] = buy(property[positionPlayer[j]], player[j]);
							System.out.println("You bought: " + positionName[positionPlayer[j]]);
							sum[j] -= positionPrise[positionPlayer[j]];
							break;
						case 'n':
							continue;
						default:
							System.out.println("Wrong index!");
							continue;
						}
					} else if ((property[positionPlayer[j]] == 6) && (sum[j] < positionPrise[positionPlayer[j]])) {
						System.out.println("You do not have enough money to buy!");
						continue;
					}
				}else if(property[positionPlayer[j]] == 7){
					System.out.println(
							"Position is free to use!");
				}else if(property[positionPlayer[j]] == 8){
					System.out.println("GOLD! You get bonus 200$ ");
					sum[j] += 200;
				}else if(property[positionPlayer[j]] == 9){
					System.out.println("You must pay tax 200$ ");
					sum[j] -= 200;
				}else if(property[positionPlayer[j]] == 10){
					System.out.println("You are on spetial position! ");
					Random rand1 = new Random();
					int chance = rand1.nextInt(10)+1;
					if(chance <= 5){
						positionPlayer[j] += specialCard1(positionPlayer[j], chance);
					}else{
						sum[j] += specialCard2(sum[j], chance);
					}
					continue;
				}else if(property[positionPlayer[j]] == 11){
					System.out.println("You are in JAIL! ");
					continue;
				}
				 
				System.out.println("In your account has: " + sum[j] + " dollars!");
				if((sum[player[j]] > 0)&&(property[positionPlayer[j]] <= 4)&&(property[positionPlayer[j]] != player[j])){
					System.out.println("Position is property to: " + playerName[property[positionPlayer[j]]]);
					sum[j] = payRent(property[positionPlayer[j]], player[j], sum[j], positionPrise[positionPlayer[j]],
							playerName[property[positionPlayer[j]]]);
					sum[property[positionPlayer[j]]] = getMoney(property[positionPlayer[j]], player[j],
							sum[property[positionPlayer[j]]], positionPrise[positionPlayer[j]]);
				}
								
				winner = isWinner(sum[j], player[j]);
				System.out.println("Accounts of players " + Arrays.toString(sum));
				System.out.println("------------------------------------------------");
				for (int i = 0; i < sum.length; i++) {
					if (sum[i] <= 0) {
						System.out.println("In your account has: " + sum[i] + " dollars. You have to sell something!");
						for (int k = 0; k < property.length; k++) {
							if (property[k] == player[i]) {
								System.out.println("You have : " + positionName[k]);
							}
						}
						for (int k1 = 0; k1 < property.length; k1++) {

							if (property[k1] == player[i]) {
								System.out.println(playerName[i] + " ,do you want to sell yuor property? "
										+ " y - for YES ;  n - for NO");
								Scanner sc5 = new Scanner(System.in);
								char sellOrNo = sc5.next(".").charAt(0);
								switch (sellOrNo) {
								case 'y':

									Scanner sc6 = new Scanner(System.in);
									System.out.println("Select position number to sell:");
									int whatToSell = sc6.nextInt();
									if (property[whatToSell] == player[i]) {
										property[whatToSell] = 6;
										sum[i] += (positionPrise[whatToSell] * 0.5);
										System.out.println("Now in your account has: " + sum[i] + " dollars.");
									} else {
										System.out.println("Wrong index!");
										continue;
									}
									break;
								case 'n':
									continue;
								default:
									System.out.println("Wrong index!");
									continue;
								}

							}
						}

					}

				}

				System.out.println("Position property " + Arrays.toString(property));
				System.out.println("Accounts of players " + Arrays.toString(sum));
				if (sum[j] <= 0) {
					System.out.println(playerName[player[j]] + "! Game over for you!");
					playersInGame--;
				}

				System.out.println("------------------------------------------------");
			}
			if (playersInGame <= 1) {
				runGame = false;
				System.out.println("Game over! " + playerName[player[winner]] + ", you win!");
			}
		}
	}
	public static int sumPassStart(int positionPlayer, int dice ) {
		int sumJ = 0;
		positionPlayer += dice;
		if (positionPlayer > 39) {
			System.out.println("You pass START! Got 200$ ");
			sumJ = 200;
		}
		return sumJ;
	}
	public static int passStartDice(int positionPlayer, int dice) {
		positionPlayer = positionPlayer + dice;
		if (positionPlayer > 39) {
			positionPlayer -= 40;
		} else if (positionPlayer == 40) {
			positionPlayer = 0;
		}
		return positionPlayer;
	}

	public static int getValue(int i) {
		int property = 0;
		if ((i == 0) || (i == 10)||(i==20)) {
			property = 7;// free position
		} else if ((i == 1) || (i == 16) || (i == 34)) {
			property = 8;// gold position
		} else if (i == 4) {
			property = 9;// pay tax
		} else if ((i == 6) || (i == 24) || (i == 36)) {
			property = 10;// special cart
		} else if (i == 30) {
			property = 11;// in jail
		} else {
			property = 6;
		}
		return property;
	}

	public static String getName(int i) {
		String name = null;
		switch (i) {
		case 0:
			name = " /0/ START ";
			break;
		case 1:
			name = " /1/ Gold ";
			break;
		case 2:
			name = " /2/ Mediterranean 50$ ";
			break;
		case 3:
			name = " /3/ Baltic 60$";
			break;
		case 4:
			name = " /4/ Pay tax 200$ ";
			break;
		case 5:
			name = " /5/ Reading 150$ ";
			break;
		case 6:
			name = " /6/ Special card ";
			break;
		case 7:
			name = " /7/ Oriental 100$ ";
			break;
		case 8:
			name = " /8/ Vermont 100$ ";
			break;
		case 9:
			name = " /9/ Connecticut 120$ ";
			break;
		case 10:
			name = " /10/ Free place ";
			break;
		case 11:
			name = " /11/ St. charles 150$ ";
			break;
		case 12:
			name = " /12/ States 150$ ";
			break;
		case 13:
			name = " /13/ Virginia 180$ ";
			break;
		case 14:
			name = " /14/ Electric 150$ ";
			break;
		case 15:
			name = " /15/ Reading 150$ ";
			break;
		case 16:
			name = " /16/ Gold ";
			break;
		case 17:
			name = " /17/ St. James 200$";
			break;
		case 18:
			name = " /18/ Tennessee 200$";
			break;
		case 19:
			name = " /19/ New York 220$";
			break;
		case 20:
			name = " /20/ Free parking ";
			break;
		case 21:
			name = " /21/ Kentucky 240$";
			break;
		case 22:
			name = " /22/ Indiana 250$";
			break;
		case 23:
			name = " /23/ Illinois 250$";
			break;
		case 24:
			name = " /24/ Special cart ";
			break;
		case 25:
			name = " /25/ Reading 150$ ";
			break;
		case 26:
			name = " /26/ Water works 150$";
			break;
		case 27:
			name = " /27/ Atlantic 260$ ";
			break;
		case 28:
			name = " /28/ Ventnor 260$ ";
			break;
		case 29:
			name = " /29/ Marvin Gardens 280$ ";
			break;
		case 30:
			name = " /30/ In JAIL ";
			break;
		case 31:
			name = " /31/ Pacific 300$ ";
			break;
		case 32:
			name = " /32/ North Carolina 300$ ";
			break;
		case 33:
			name = " /33/ Pennsylvania 320$ ";
			break;
		case 34:
			name = " /34/ Gold ";
			break;
		case 35:
			name = " /35/ Reading 150$ ";
			break;
		case 36:
			name = " /36/ Special cart ";
			break;
		case 37:
			name = " /37/ Park Place 360$ ";
			break;
		case 38:
			name = " /38/ Bcardwalk 400$ ";
			break;
		case 39:
			name = " /39/ Luxury tax 300$";
			break;
		default:
			break;
		}
		return name;

	}

	public static int buy(int propertyPositionPlayerJ, int playerJ) {
		propertyPositionPlayerJ = playerJ;
		return propertyPositionPlayerJ;
	}

	public static int payRent(int propertyPositionPlayerJ, int playerJ, int sumJ, int positionPrisePpositionPlayerJ,
			String playerNamePropertyPositionPlayerJ) {
		if (propertyPositionPlayerJ != playerJ) {
			sumJ -= (positionPrisePpositionPlayerJ * 0.5);
			System.out.println("You pay " + (positionPrisePpositionPlayerJ * 0.5) + " dollars rent to "
					+ playerNamePropertyPositionPlayerJ);
		}
		return sumJ;
	}

	public static int getMoney(int propertyPositionPlayerJ, int playerJ, int sumPropertyPositionPlayerJ,
			int positionPrisePpositionPlayerJ) {
		if (propertyPositionPlayerJ != playerJ) {
			sumPropertyPositionPlayerJ += (positionPrisePpositionPlayerJ * 0.5);
		}
		return sumPropertyPositionPlayerJ;
	}
	public static int specialCard1(int positionPlayerJ,int chance){
		switch(chance){
			case 1:System.out.println(" Go to /23/ Illinois 250$! ");
			positionPlayerJ = 23;
				break;
			case 2:System.out.println(" Go to /33/ Pennsylvania 320$! ");
			positionPlayerJ = 33;
				break;
			case 3:System.out.println(" Go to START! ");
			positionPlayerJ = 0;
				break;
			case 4:System.out.println(" Go forward 5 position ! ");
			positionPlayerJ += 5;
				break;
			case 5:System.out.println(" Go forward 10 position ! ");
			positionPlayerJ += 10;;
				break;
				default: break;
		}
		return positionPlayerJ;
	}
	public static int specialCard2(int sumJ,int chance){
		switch(chance){
				case 6:System.out.println(" You have to pay 200$ for dentist! ");
				sumJ = -200;
					break;
				case 7:System.out.println(" You have to pay 300$ for your car!");
				sumJ = -300;
					break;
				case 8:System.out.println(" You go to JAIL! Have to pay 400$");
				sumJ = -400;
					break;
				case 9:System.out.println(" You have birthday! Get 200$ ");
				sumJ = 200;
					break;
				case 10:System.out.println(" You win 400$ from this cart ");
				sumJ = 400;
					break;
				default: break;
			}
		return sumJ;
	}
	public static int isWinner(int sumJ, int playerJ) {
		int previousMaxAmount = 0;
		int playerMaxSum = 0;
		if (sumJ > previousMaxAmount) {
			previousMaxAmount = sumJ;
			playerMaxSum = playerJ;
		}
		return playerMaxSum;
	}
}
