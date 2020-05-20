package Team_Six;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class WarehousingRecall { // Ŭ���� ���� - �������

	StoregeTeam6 management = StoregeTeam6.getInstance();

	void storeWR(MyQueue queueMessage, String franchiseName) { // �԰� ��û ��ǰ ��û ���� �Լ� ���� - �������

		while (true) {

			System.out.println("1.�԰� ��û \t 2.��ǰ ���� \t 3.���");
//			Scanner scan = new Scanner(System.in);
			int num = management.scanInt();
			if (num == 1) {

//----------------------------------- ������ ���� �� ����---------------------------------
				String path = "C:/ProductInfo/";
				File f = new File(path);
				File[] files = f.listFiles();

				int count = 0;
				for (int i = 0; i < files.length; i++) {
					if (files[i].isFile())
						count++;
				}

//-------------------------- ��ǰ�ڵ� ��Ʈ���迭�� ���ϸ� �ϳ� �ϳ� �����ϱ�---------------------------------

				File dir = new File(path);
				File[] fileList = dir.listFiles();
				String[] fileName = new String[count];
				int i = 0;
				for (File file : fileList) {
					if (file.isFile()) {
						fileName[i] = file.getName();
						i++;
					}
				}

//-----------------------�ش� ��ǰ�ڵ��� ������ �ҷ��� �迭�� �����Ű�� �԰��û ������ �� ---------------------------------

				System.out.println("��ǰ�ڵ带 �Է����ּ���");
				String code = management.scanStr();

				String fileCodeName = code + ".txt";

				int status = 0;

				for (i = 0; i < fileName.length; i++) { // ��ǰ�ڵ� ���ϸ� �˻�
					if (fileCodeName.equals(fileName[i])) // ��ǰ�ڵ��� ���ϸ��� ������ �ݺ��� ����
						break;
					else if (i == fileName.length - 1) { // ��ǰ�ڵ��� ���ϸ��� ������ �������� �߰�
						System.out.println("�ش� ��ǰ�ڵ� ������ �����ϴ�.");
						status = -1; // ������ ������ ���� ���� -1�� �ʱ�ȭ
					}
				}
				if (status == -1)
					continue;

				System.out.println("������ �Է����ּ���.");
				int quantity = management.scanInt();
				int temp = 0;
				if (status != -1) { // ��ǰ�ڵ�� �´� ������ ������ �ش������� ���� �ҷ�����
					try {
						File file = new File("C:/ProductInfo/" + fileCodeName);
						FileReader file_r = new FileReader(file);
						int cur;
						i = 0;
						char[] insert = new char[1000000];
						while ((cur = file_r.read()) != -1) {
							insert[i] = (char) (cur);
							i++;
						}

						char[] chstr = new char[i];

						for (int j = 0; j < i; j++)
							chstr[j] = insert[j];

						String str = new String(chstr);
						String[] stredit = str.split("/");

						temp = Integer.parseInt(stredit[0]);

						if (quantity > temp) {
							System.out.println("����â���� ������ �����մϴ�");
							System.out.println("����â�� " + code + "��ǰ�� ���� " + temp + "��");
						}
						file_r.close();
					} catch (FileNotFoundException e) {
						e.getStackTrace();
					} catch (IOException e) {
						e.getStackTrace();
					}
				}
				if (quantity > temp)
					continue;

				queueMessage.snadMessageW(franchiseName, code, quantity);
				queueMessage.loginMessageSaveW(franchiseName, code, quantity);
				System.out.println("��ǰ�ڵ�:" + code + " ����:" + quantity + "�� �԰� ��û�� �Ϸ� �Ǿ����ϴ�.");
			}
//--------------------------------------��ǰ ��û---------------------------------------------

			if (num == 2) {
//----------------------------------- ������ ���� �� ����---------------------------------
				String path = "C:/����/";
				File f = new File(path);
				File[] files = f.listFiles();

				int count = 0;
				for (int i = 0; i < files.length; i++) {
					if (files[i].isFile())
						count++; // ���� �� ���� ��
				}

//-------------------------- ��ǰ�ڵ� ��Ʈ���迭�� ���ϸ� �ϳ� �ϳ� �����ϱ�---------------------------------

				File dir = new File(path);
				File[] fileList = dir.listFiles();
				String[] fileName = new String[count];
				int i = 0;
				for (File file : fileList) {
					if (file.isFile()) {
						fileName[i] = file.getName();
						i++;
					}
				}

//-----------------------�ش� ��ǰ�ڵ��� ������ �ҷ��� �迭�� �����Ű�� �԰��û ������ �� ---------------------------------

				System.out.println("��ǰ�ڵ带 �Է����ּ���");
				String code = management.scanStr();

				String fileCodeName = code + ".txt";

				int status = 0;

				for (i = 0; i < fileName.length; i++) { // ��ǰ�ڵ� ���ϸ� �˻�
					if (fileCodeName.equals(fileName[i])) // ��ǰ�ڵ��� ���ϸ��� ������ �ݺ��� ����
						break;
					else if (i == fileName.length - 1) { // ��ǰ�ڵ��� ���ϸ��� ������ �������� �߰�
						System.out.println("�ش� ��ǰ�ڵ� ������ �����ϴ�.");
						status = -1; // ������ ������ ���� ���� -1�� �ʱ�ȭ
						break;
					}

				}

				if (status == -1)
					continue;

				System.out.println("������ �Է����ּ���.");
				int quantity = management.scanInt();

				queueMessage.snadMessageR(franchiseName, code, quantity);
				queueMessage.loginMessageSaveR(franchiseName, code, quantity);

//				System.out.println("��ǰ�ڵ�:" + code + " ����:" + quantity + "�� ��ǰ ó���� �Ϸ� �Ǿ����ϴ�.");
				
				management.productRecall(franchiseName, code, quantity);
				
			}

			if (num == 3)
				break;
		}
		
		

	}

	void managementWR(MyQueue queueMessage) {// ť�ȿ� ����ִ� �޽����� ����ϴ� �Լ� ���� - �������

		System.out.println("1.��� ����\t2.���� �޽��� Ȯ��");
//		Scanner scan = new Scanner(System.in);
		int num = management.scanInt();
		
		if (num == 1)
			management.productRelease();
		if (num == 2) {
			queueMessage.printMessageW();
			queueMessage.printMessageR();
		}
	}

}
