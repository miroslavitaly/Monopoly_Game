import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class monopolyGame {

	public static void main(String[] args) {
		// ����� ������ �� ������
		Scanner sc1 = new Scanner(System.in);
		System.out.println("����� ������ �� ������? ");
		int howManyPlayers = sc1.nextInt(4);

		// ��������� �� ������� �� ��������
		String[] playerName = new String[howManyPlayers];
		for (int i = 0; i <= playerName.length - 1; i++) {
			Scanner sc2 = new Scanner(System.in);
			System.out.println("������ ����� �� ����� " + i + " :");
			String name = sc2.next();
			playerName[i] = name;
		}
		int[] player = new int[howManyPlayers];
		for (int i = 0; i < player.length; i++) {
			player[i] = i;
		}
		// ������� �������� �� ������
		int[] sum = new int[howManyPlayers];
		for (int i = 0; i < sum.length; i++) {
			sum[i] = 1000;
		}
		// ������� ��������� �� ������
		int[] positionPlayer = new int[howManyPlayers];
		// ������� ������������� �� ������
		int[] property = new int[5];
		for (int i = 0; i < property.length; i++) {
			property[i] = 9;
		}
		// ������� �� ���������
		String[] positionName = { " �������� ���� ", " ��������� ", " ������������ ����: 50$ ",
				" ����������� ����: 60$ ", " ����� �������: 200$ " };
		int [] positionPrise = {10, 10, 50, 60, 200};

		boolean runGame = true;

		// ����� �� �����
		while (runGame) {
			for (int j = 0; j <= player.length - 1; j++) {

				System.out.println(playerName[j] + " , ���� ��� �! ������� /Enter/! ");
				Scanner sc3 = new Scanner(System.in);
				char run = sc3.next(".").charAt(0);

				System.out.println("�� ������ ������ ���: " + sum[j] + " ������!");

				Random rand = new Random();
				int dice = rand.nextInt(4) + 1;
				System.out.println("���� �� ��������: " + dice);

				System.out.println("��������� ������� " + positionPlayer[j] + positionName[positionPlayer[j]]);
				positionPlayer[j] = passStart(positionPlayer[j], dice);
				System.out.println("��������  ������� " + positionPlayer[j] + positionName[positionPlayer[j]]);
				System.out.println("�� �� ����� " + positionName[positionPlayer[j]] + "����������� �� "
						+ Arrays.toString(property));

				// " �������� ���� "
				if (positionPlayer[j] == 0) {

					System.out.println("�� �� ����� /������/");

				}
				// " ��������� "
				if (positionPlayer[j] == 1) {
					System.out.println(playerName[j] + " ,�� �� ����� �� ����� �����! ������� ��? "
							+ " y - �� �� ;  n - �� ��");
					Scanner sc4 = new Scanner(System.in);
						char bayOrNo = sc4.next(".").charAt(0);
						switch (bayOrNo) {
						case 'y':property[positionPlayer[j]] = bay(property[positionPlayer[j]], player[j]);
								System.out.println("�� ����: " + positionName[positionPlayer[j]]);
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
				// " ������������ ����: 50$ "
				if (positionPlayer[j] == 2) {
					System.out.println(playerName[j] + " ,�� �� ����� �� ����� �����! ������� ��? "
							+ " y - �� �� ;  n - �� ��");
					Scanner sc4 = new Scanner(System.in);
						char bayOrNo = sc4.next(".").charAt(0);
						switch (bayOrNo) {
						case 'y':property[positionPlayer[j]] = bay(property[positionPlayer[j]], player[j]);
								System.out.println("�� ����: " + positionName[positionPlayer[j]]);
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
				// " ����������� ����: 60$ "
				if (positionPlayer[j] == 3) {
					System.out.println(playerName[j] + " ,�� �� ����� �� ����� �����! ������� ��? "
							+ " y - �� �� ;  n - �� ��");
					Scanner sc4 = new Scanner(System.in);
						char bayOrNo = sc4.next(".").charAt(0);
						switch (bayOrNo) {
						case 'y':property[positionPlayer[j]] = bay(property[positionPlayer[j]], player[j]);
						System.out.println("�� ����: " + positionName[positionPlayer[j]]);
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
				// " ����� �������: 200$ "
				if (positionPlayer[j] == 4) {
					
					//�� ��������, ������ ��������� ��������� ��������
					System.out.println(playerName[j] + " ,�� �� ����� �� ����� �����! ������� ��? "
							+ " y - �� �� ;  n - �� ��");
					Scanner sc4 = new Scanner(System.in);
						char bayOrNo = sc4.next(".").charAt(0);
						switch (bayOrNo) {
						case 'y':property[positionPlayer[j]] = bay(property[positionPlayer[j]], player[j]);
						System.out.println("�� ����: " + positionName[positionPlayer[j]]);
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
				System.out.println("�� ������ ������ �������: " + sum[j] + " ������!");
				System.out.println("������ �� �������� " + Arrays.toString(sum));
				//if (sum[j] == 0) {
				//	System.out.println(player[j] + "�� ������� �� ������");
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
	


