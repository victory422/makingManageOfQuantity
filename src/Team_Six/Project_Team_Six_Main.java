package Team_Six;

import java.io.IOException;
import java.util.Date;
import java.util.Scanner;

public class Project_Team_Six_Main {

	public static void main(String[] args) throws IOException { // ���� ���� - ��������
		Date today = new Date();
		StoregeTeam6 management = StoregeTeam6.getInstance();
		WarehousingRecall sWR = new WarehousingRecall();
		MyQueue queueMessage = new MyQueue();
		Store call = new Store();

		while (true) {

			System.out.println("1. ������ �α��� \t 2. ���� �α���");
//			Scanner scan = new Scanner(System.in);
			int inputNum1 = management.scanInt();
					
			if (inputNum1 == 1) {
				int login = management.managerLogin();
				management.logprint("������ �α��� " +today);
				
				if(login == -1)
					continue;
				
				else if(login != -1)
				queueMessage.frameMakerWR();

				while (true) {

					System.out.println("1.��ǰ���� \t 2.��ǰ�˻� \t 3.�������� \t 4.�α׾ƿ�");

					int inputNum2 = management.scanInt();
					if (inputNum2 == 1) {
						management.productManage();
						
					}
					
					if (inputNum2 == 2) {
						management.productSearch();
					}

					if (inputNum2 == 3) {
						
						WarehousingRecall wr = new WarehousingRecall();
						wr.managementWR(queueMessage);
					}
					if (inputNum2 == 4) {
						System.out.println("�α׾ƿ�");
						management.logprint("������ �α׾ƿ� " +today);
						break;
					}
				}
			}

			if (inputNum1 == 2) {// ���� ��� �÷���

				System.out.println("���� ������ �α����� ���� ������� �Է��ϼ���!");
				String franchiseName = management.scanStr();
				management.franchiseLogin(franchiseName);

				while (franchiseName.equals("����")) {
					System.out.println("���� ����\n");
					System.out.println("1.��ǰ���� \t 2.��ǰ�˻� \t 3.�԰� ��ǰ ó�� \t 4.�α׾ƿ�  ");

					int inputNum3 = management.scanInt();

					if (inputNum3 == 1) {
						call.productManage(franchiseName);
					}

					if (inputNum3 == 2) {
						System.out.println("��ǰ �˻�");
						call.storeSearch("����");
					}
					if (inputNum3 == 3) 
						sWR.storeWR(queueMessage, franchiseName);
					
					if (inputNum3 == 4) {
						System.out.println("�α׾ƿ�");
						break;
					}
				}

				while (franchiseName.equals("����")) {
					System.out.println("���� ����\n");
					System.out.println("1.��ǰ���� \t 2.��ǰ�˻� \t 3.�԰� ��ǰ ���� \t 4.�α׾ƿ�");

					int inputNum4 = management.scanInt();

					if (inputNum4 == 1) {
					
						call.productManage(franchiseName);
					}

					if (inputNum4 == 2) {
						System.out.println("��ǰ �˻�");
						call.storeSearch("����");
					}

					if (inputNum4 == 3) {
						sWR.storeWR(queueMessage, franchiseName);
					}

					if (inputNum4 == 4) {
						System.out.println("�α׾ƿ�");
						break;
					}
				}

				while (franchiseName.equals("����")) {
					System.out.println("���� ����\n");
					System.out.println("1.��ǰ���� \t 2.��ǰ�˻� \t 3.�԰� ��ǰ ���� \t 4.�α׾ƿ�");

					int inputNum6 = management.scanInt();

					if (inputNum6 == 1) {
						call.productManage(franchiseName);
					}

					if (inputNum6 == 2) {
						System.out.println("��ǰ �˻�");
						call.storeSearch("����");
					}
					if (inputNum6 == 3) 
						sWR.storeWR(queueMessage, franchiseName);

					if (inputNum6 == 4) {
						System.out.println("�α׾ƿ�");
						break;
					}

				}
				
				management.wrongSearch(franchiseName); // ã�� ���� ���� �������� ���
				
			}

		}

	}

}
