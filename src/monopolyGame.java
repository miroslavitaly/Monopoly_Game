import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class monopolyGame {

	public static void main(String[] args) {
		// Колко играча ще играят
		Scanner sc1 = new Scanner(System.in);
		System.out.println("Колко играча ще играят? ");
		int howManyPlayers = sc1.nextInt(4);

		// Въвеждане на имената на играчите
		String[] playerName = new String[howManyPlayers];
		for (int i = 0; i <= playerName.length - 1; i++) {
			Scanner sc2 = new Scanner(System.in);
			System.out.println("Въведи името на играч " + i + " :");
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
			property[i] = 9;
		}
		// имената на позициите
		String[] positionName = { " Стартово поле ", " Съкровище ", " Ботевградско шосе: 50$ ",
				" Цариградско шосе: 60$ ", " Данък печалба: 200$ " };
		int [] positionPrise = {10, 10, 50, 60, 200};

		boolean runGame = true;

		// Старт на ирата
		while (runGame) {
			for (int j = 0; j <= player.length - 1; j++) {

				System.out.println(playerName[j] + " , твой ред е! Натисни /Enter/! ");
				Scanner sc3 = new Scanner(System.in);
				char run = sc3.next(".").charAt(0);

				System.out.println("По твоята сметка има: " + sum[j] + " долара!");

				Random rand = new Random();
				int dice = rand.nextInt(4) + 1;
				System.out.println("Сума от заровете: " + dice);

				System.out.println("Предходна позиция " + positionPlayer[j] + positionName[positionPlayer[j]]);
				positionPlayer[j] = passStart(positionPlayer[j], dice);
				System.out.println("Настояща  позиция " + positionPlayer[j] + positionName[positionPlayer[j]]);
				System.out.println("Ти си върху " + positionName[positionPlayer[j]] + "Собственост на "
						+ Arrays.toString(property));

				// " Стартово поле "
				if (positionPlayer[j] == 0) {

					System.out.println("Ти си върху /Начало/");

				}
				// " Съкровище "
				if (positionPlayer[j] == 1) {
					System.out.println(playerName[j] + " ,ти би могъл да купиш имота! Купуваш ли? "
							+ " y - за Да ;  n - за Не");
					Scanner sc4 = new Scanner(System.in);
						char bayOrNo = sc4.next(".").charAt(0);
						switch (bayOrNo) {
						case 'y':property[positionPlayer[j]] = bay(property[positionPlayer[j]], player[j]);
								System.out.println("Ти купи: " + positionName[positionPlayer[j]]);
								sum[j] -= positionPrise[positionPlayer[j]]; 
							break;
						case 'n':
							break;
						default:
							break;
						}
					sum[j] = payRent(property[positionPlayer[j]], player[j], sum[j]);
					sum[property[positionPlayer[j]]] = getMoney(property[positionPlayer[j]], player[j], 
							sum[property[positionPlayer[j]]]);

				}
				// " Ботевградско шосе: 50$ "
				if (positionPlayer[j] == 2) {
					System.out.println(playerName[j] + " ,ти би могъл да купиш имота! Купуваш ли? "
							+ " y - за Да ;  n - за Не");
					Scanner sc4 = new Scanner(System.in);
						char bayOrNo = sc4.next(".").charAt(0);
						switch (bayOrNo) {
						case 'y':property[positionPlayer[j]] = bay(property[positionPlayer[j]], player[j]);
								System.out.println("Ти купи: " + positionName[positionPlayer[j]]);
								sum[j] -= positionPrise[positionPlayer[j]];
							break;
						case 'n':
							break;
						default:
							break;
						}
					sum[j] = payRent(property[positionPlayer[j]], player[j], sum[j]);
					sum[property[positionPlayer[j]]] = getMoney(property[positionPlayer[j]], player[j], 
							sum[property[positionPlayer[j]]]);

				}
				// " Цариградско шосе: 60$ "
				if (positionPlayer[j] == 3) {
					System.out.println(playerName[j] + " ,ти би могъл да купиш имота! Купуваш ли? "
							+ " y - за Да ;  n - за Не");
					Scanner sc4 = new Scanner(System.in);
						char bayOrNo = sc4.next(".").charAt(0);
						switch (bayOrNo) {
						case 'y':property[positionPlayer[j]] = bay(property[positionPlayer[j]], player[j]);
						System.out.println("Ти купи: " + positionName[positionPlayer[j]]);
						sum[j] -= positionPrise[positionPlayer[j]];
							break;
						case 'n':
							break;
						default:
							break;
						}
					sum[j] = payRent(property[positionPlayer[j]], player[j], sum[j]);
					sum[property[positionPlayer[j]]] = getMoney(property[positionPlayer[j]], player[j], 
							sum[property[positionPlayer[j]]]);

				}
				// " Данък печалба: 200$ "
				if (positionPlayer[j] == 4) {
					
					//за оправяне, защото разрешава постоянно купуване
					System.out.println(playerName[j] + " ,ти би могъл да купиш имота! Купуваш ли? "
							+ " y - за Да ;  n - за Не");
					Scanner sc4 = new Scanner(System.in);
						char bayOrNo = sc4.next(".").charAt(0);
						switch (bayOrNo) {
						case 'y':property[positionPlayer[j]] = bay(property[positionPlayer[j]], player[j]);
						System.out.println("Ти купи: " + positionName[positionPlayer[j]]);
						sum[j] -= positionPrise[positionPlayer[j]];
							break;
						case 'n':
							break;
						default:
							break;
						}
					sum[j] = payRent(property[positionPlayer[j]], player[j], sum[j]);
					sum[property[positionPlayer[j]]] = getMoney(property[positionPlayer[j]], player[j], 
							sum[property[positionPlayer[j]]]);

				}
				System.out.println("По твоята сметка остават: " + sum[j] + " долара!");
				System.out.println("Сметка на играчите " + Arrays.toString(sum));
				//if (sum[j] == 0) {
				//	System.out.println(player[j] + "Ти излизаш от играта");
				//	runGame = false;
				//}

			}

		}

	}

	public static int passStart(int positionPlayer, int dice) {
		// positionPlayer[j] = positionPlayer[j] + dice;
		// if (positionPlayer[j] > 4) {
		// positionPlayer[j] -= 5;
		// } else if (positionPlayer[j] == 5) {
		// positionPlayer[j] = 0;
		// }
		positionPlayer = positionPlayer + dice;
		if (positionPlayer > 4) {
			positionPlayer -= 5;
		} else if (positionPlayer == 5) {
			positionPlayer = 0;
		}
		return positionPlayer;
	}
	public static int bay(int propertyPositionPlayerJ, int playerJ){
		// Bay
		if (propertyPositionPlayerJ == 9) {
			propertyPositionPlayerJ = playerJ;
		}
		return propertyPositionPlayerJ;
	}
	public static int payRent(int propertyPositionPlayerJ, int playerJ, int sumJ){
		// Pay rent
		if (propertyPositionPlayerJ != playerJ) {
					if(sumJ>0){
						sumJ -= 200;
					}
		}
		return sumJ;
	}
	public static int getMoney(int propertyPositionPlayerJ, int playerJ, int sumPropertyPositionPlayerJ){
		if (propertyPositionPlayerJ != playerJ) {
						sumPropertyPositionPlayerJ += 200;
		}
						return sumPropertyPositionPlayerJ;
	}
	public static int sell(int n){
		// Sell
		return n;
	}
}	
	


