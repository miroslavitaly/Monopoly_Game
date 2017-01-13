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
		// запазва сметката на играча
		int[] sum = new int[howManyPlayers];
		for (int i = 0; i < sum.length; i++) {
			sum[i] = 1000;
		}
		// запазва позицията на играча
		int[] positionPlayer = new int[howManyPlayers];
		// запазва собствеността на играча
		int[] property = new int[5];
		for (int i = 0; i < property.length; i++) {
			property[i] = 6;
		}
		// имената на позициите
		String[] positionName = { " START ", " 1- Special place: GOLD", " 2- ", " 3- ", " 4- PAY TAX 200 $", " 5- ",
				" 6- Special place: CARD", " 7- ", " 8- ", " 9- ", " 10- ", " 11- ", " 12- ", " 13- ", " 14- ", " 15- ",
				" 16- ", " 17- ", " 18- ", " 19- ", " 20- ", " 21- ", " 22- ", " 23- ", " 24- ", " 25- ", " 26- ",
				" 27- ", " 28- ", " 29- ", " 30- ", " 31- ", " 32- ", " 33- ", " 34- ", " 35- ", " 36- ", " 37- ",
				" 38- ", " 39- ", };
		int[] positionPrise = { 100, 200, 300, 400, 600 };

		boolean runGame = true;
		int playersInGame = howManyPlayers;
		int winner = 0;
		// Старт на ирата
		while (runGame) {
			for (int j = 0; j <= player.length - 1; j++) {
				if (sum[j] <= 0) {
					continue;
				}
				System.out.println(playerName[j] + " , its your turn! Press enter!");
				Scanner sc3 = new Scanner(System.in);
				char run = sc3.next(".").charAt(0);

				Random rand = new Random();
				int dice = rand.nextInt(4) + 1;
				System.out.println("The sum of the dice: " + dice);

				System.out.println("Previous   position:" + positionName[positionPlayer[j]]);
				positionPlayer[j] = passStart(positionPlayer[j], dice);
				System.out.println("Current    position:" + positionName[positionPlayer[j]]);
				if (property[positionPlayer[j]] == 6) {
					System.out.println("Position is free to buy! Prise :  " + positionPrise[positionPlayer[j]] + " dollars!");
				} else {
					System.out.println("Position is property to: "
							+ playerName[property[positionPlayer[j]]]);
				}
				System.out.println("On your account has: " + sum[j] + " dollars!");

				//if (positionPlayer[j] == positionPlayer[j]) {
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
					if (sum[property[positionPlayer[j]]] > 0) {
						sum[j] = payRent(property[positionPlayer[j]], player[j], sum[j],
								positionPrise[positionPlayer[j]], playerName[property[positionPlayer[j]]]);
						sum[property[positionPlayer[j]]] = getMoney(property[positionPlayer[j]], player[j],
								sum[property[positionPlayer[j]]], positionPrise[positionPlayer[j]]);
					}
					winner = isWinner(sum[j], player[j]);
					System.out.println("Accounts of players " + Arrays.toString(sum));
					System.out.println("------------------------------------------------");
				//}
				for (int i = 0; i < sum.length; i++) {
					if (sum[i] <= 0) {
						for (int k = 0; k < property.length; k++) {
							for (int k2 = 0; k2 < property.length; k2++) {
								if (property[k2] == player[i]){
									System.out.println("You have : " + Arrays.toString(positionName));
								}
							}
							if (property[k] == player[i]) {
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
	public static int passStart(int positionPlayer, int dice) {
		positionPlayer = positionPlayer + dice;
		if (positionPlayer > 4) {
			positionPlayer -= 5;
		} else if (positionPlayer == 5) {
			positionPlayer = 0;
		}
		return positionPlayer;
	}
	public static int buy(int propertyPositionPlayerJ, int playerJ) {
		propertyPositionPlayerJ = playerJ;
		return propertyPositionPlayerJ;
	}
	public static int payRent(int propertyPositionPlayerJ, int playerJ, int sumJ, int positionPrisePpositionPlayerJ,
			String playerNamePropertyPositionPlayerJ) {
		// Pay rent
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
