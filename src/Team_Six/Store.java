package Team_Six;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Scanner;

public class Store { // Ŭ���� ���� - ������
	StoregeTeam6 management = StoregeTeam6.getInstance();
	String[] sname = { "����", "����", "����" };
	
	void productManage(String store) { // ��ǰ ���� ���μ��� ���� - ���ؿ�, ������
		System.out.println("1.��ǰ��� \t 2.��ǰ���� \t 3.��ǰ���� \t 4.�ڷΰ���");
		
		int intputNum = management.scanInt();
		if(intputNum == 1)
			storeInput(store);
		if(intputNum == 2)
			storeupdate(store);
		if(intputNum == 3)
			storedelete(store);	
	}		

	void storeInput(String store) { // ��ǰ ��� �Լ� ���� - ���ؿ��

		int i = 001;
		while (true) {
//			System.out.println("1. ��ǰ ���");
			ProductInfo m = new ProductInfo();

			ArrayList<String[]> mylist = new ArrayList<String[]>();
			;

//			Scanner scan = new Scanner(System.in);
//
//			int button = scan.nextInt();
//			while (true) {
			String s[] = new String[6];

			mylist.add(s);

			System.out.println("������ �Է��ϼ���");
			m.type = management.scanStr();

			System.out.println("�������� �Է��ϼ���");
			m.design = management.scanStr();

			System.out.println("������ �Է��ϼ���");
			m.color = management.scanStr();

			System.out.println("����� �Է��ϼ���");
			m.size = management.scanStr();

			System.out.println("������ �Է��ϼ���.");
			m.quantity = management.scanStr();

			m.code = m.codefunc();

			s[0] = m.quantity;
			s[1] = m.type;
			s[2] = m.design;
			s[3] = m.color;
			s[4] = m.size;
			s[5] = m.code;

			// count ���� ������ �˻�
			// �´� ��ü ������ ����// ��ǰ��������
			//

			try {
				OutputStream output = new FileOutputStream("C:/" + store + "/" + s[5] + ".txt");
				String str = s[0] + "/" + s[1] + "/" + s[2] + "/" + s[3] + "/" + s[4] + "/" + s[5];
				byte[] by = str.getBytes();
				output.write(by);
				output.close();
			} catch (Exception e) {
				e.getStackTrace();

			}

			System.out.println("��ǰ�ڵ� : " + s[5]);
			System.out.println("���� : " + s[0]);
			break;
		}
	}

	void storeupdate(String store) { // ��ǰ ���� �Լ� ���� - ���ؿ��
//		System.out.println("��� ���� ���");

		String path = "C:/" + store + "/";
		File f = new File(path);
		File[] files = f.listFiles();
		int i = 0;
		int count = 0;
		for (i = 0; i < files.length; i++) {
			if (files[i].isFile()) {
				count++;
			}
		}
		File dir = new File(path);
		File[] fileList = dir.listFiles();
		String[] fileName = new String[count];
		i = 0;
		for (File file : fileList) {
			if (file.isFile()) {
				fileName[i] = file.getName();
				i++;
			}
		}
		System.out.println("�˻� �ڵ带 ���ּ���.");
//			Scanner scan = new Scanner(System.in);
		String search = management.scanStr();
	
		search = search + ".txt";
		int status = 0;
		for (int y = 0; y < fileName.length; y++) {
			if (search.equals(fileName[y])) {
				break;
			} else if (y == fileName.length - 1) {
				System.out.println("��ǰ�� �����ϴ�.");
				status = -1;
			}
			status++;
		}
		if (status != -1) {
			try {
				File mFile = new File("C:/" + store + "/" + search);
				FileReader file_r = new FileReader(mFile);
				int c = 0;
				char arr[] = new char[100];
				int index = 0;
				while ((c = file_r.read()) != -1) {
					arr[index] = (char) c;
					index++;
				}
				char abc[] = new char[index];
				for (int u = 0; u < index; u++) {
					abc[u] = arr[u];
				}
				String txt = new String(abc);
				String id[] = txt.split("/");
				int a = Integer.valueOf(id[0]);
				System.out.println("�ڵ�: " + id[5] + "\t" + "���� : " + a);
				System.out.println("");
				System.out.println("���ο� ������ �Է����ּ���.");
				int ww = management.scanInt();
				id[0] = String.valueOf(ww);

				try {
					OutputStream output = new FileOutputStream("C:/" + store + "/" + id[5] + ".txt");
					String str = id[0] + "/" + id[1] + "/" + id[2] + "/" + id[3] + "/" + id[4] + "/" + id[5];
					byte[] by = str.getBytes();
					output.write(by);
					System.out.println("������ �Ϸ�Ǿ����ϴ�.");
				} catch (Exception e) {
					e.getStackTrace();
				}

				file_r.close();
			} catch (FileNotFoundException e) {

			} catch (Exception e) {

			}
		}

	}

	void storeSearch(String store) { // ��ǰ �˻� �Լ� ���� - ���ؿ��
		while (true) {
			String path = "C:/" + store + "/";
			File f = new File(path);
			File[] files = f.listFiles();
			int i = 0;
			int count = 0;
			for (i = 0; i < files.length; i++) {
				if (files[i].isFile()) {
					count++;
				}
			}
			File dir = new File(path);
			File[] fileList = dir.listFiles();
			String[] fileName = new String[count];
			i = 0;
			ArrayList<String> code11 = new ArrayList<String>();
			for (File file : fileList) {
				if (file.isFile()) {
					fileName[i] = file.getName();
					try {
						File mFile = new File("C:/" + store + "/" + file.getName());
						FileReader file_r = new FileReader(mFile);
						int c = 0;
						char arr[] = new char[100];
						int index = 0;
						while ((c = file_r.read()) != -1) {
							arr[index] = (char) c;
							index++;
						}
						char abc[] = new char[index];
						for (int u = 0; u < index; u++) {
							abc[u] = arr[u];
						}
						String txt = new String(abc);
						code11.add(txt);
						i++;
					} catch (Exception e) {
						// TODO: handle exception
					}
				}
			}
			ArrayList<String[]> code22 = new ArrayList<String[]>();
			for (int t = 0; t < code11.size(); t++) {
				String id[] = code11.get(t).split("/");
				code22.add(id);
			}
			System.out.println("�˻� �ڵ带 �Է����ּ���.");
//			Scanner scan = new Scanner(System.in);
			String search = management.scanStr();

			for (int p = 0; p < code22.size(); p++) {
				if (search.equals(code22.get(p)[1])) {
					System.out.println(code22.get(p)[5]);
					System.out.println("���� : " + code22.get(p)[0]);
				}
				if (search.equals(code22.get(p)[1] + code22.get(p)[2])) {
					System.out.println(code22.get(p)[5]);
					System.out.println("���� : " + code22.get(p)[0]);
				}
				if (search.equals(code22.get(p)[1] + code22.get(p)[2] + code22.get(p)[3])) {
					System.out.println(code22.get(p)[5]);
					System.out.println("���� : " + code22.get(p)[0]);
				}
				if (search.equals(code22.get(p)[5])) {
					System.out.println(code22.get(p)[5]);
					System.out.println("���� : " + code22.get(p)[0]);
				}
			}
		}
	}

	void storedelete(String store) { // ��ǰ ���� �Լ� ���� - ���ؿ��

		while (true) {
			String path = "C:/" + store + "/";
			File f = new File(path);
			File[] files = f.listFiles();
			int i = 0;
			int count = 0;
			for (i = 0; i < files.length; i++) {
				if (files[i].isFile()) {
					count++;
				}
			}
			File dir = new File(path);
			File[] fileList = dir.listFiles();
			String[] fileName = new String[count];
			i = 0;
			for (File file : fileList) {
				if (file.isFile()) {
					fileName[i] = file.getName();
					i++;
				}
			}
			System.out.println("���� �� ��ǰ�� �ڵ带 ���ּ���.\t0.�ڷΰ���");
//			Scanner scan = new Scanner(System.in);
			String search = management.scanStr();
			if (search.equals("0")) {
				break;
			}
			search = search + ".txt";
			int status = 0;
			for (int y = 0; y < fileName.length; y++) {
				if (search.equals(fileName[y])) {
					break;
				} else if (y == fileName.length - 1) {
					System.out.println("��ǰ�� �����ϴ�.");
					status = -1;
				}
				status++;
			}
			if (status != -1) {
				File mFile = new File("C:/" + store + "/" + search);
				boolean t = false;
				t = mFile.delete();
				System.out.println("������ �Ϸ� �Ǿ����ϴ�.");
			}
		}
	}

	void productRecall() { // ���� ��ǰ ���� ���� �Լ� ���� - ����ٴ�
		// ----------------------------------- ���� ����
		// �޴´�.---------------------------------
		
		Store sto = new Store();
		
		System.out.println("�����ô� ���� �̸�(CODE)�� �Է����ּ���.");
		Scanner scan = new Scanner(System.in);
		String store = scan.next();
		String code = "";
		String fileCodeName = "";
		int quantity = 0;
		for (int zz = 0; zz < sto.sname.length; zz++) {
			if (store.equals(sto.sname[zz])) {
				System.out.println(sto.sname[zz] + "������ Ȯ�εǾ����ϴ�.");
			}
		}

		// ==========================�ڵ尡 ����â�� �ִ� �ڵ����� ��===========================
		// ----------------------------------- ����â��� ���� ��
		// ����---------------------------------
		String path = "C:/" + store + "/";
		File f = new File(path);
		File[] files = f.listFiles();
		int count = 0;
		for (int i = 0; i < files.length; i++) {
			if (files[i].isFile())
				count++;
		}

		// -------------------------- ��ǰ�ڵ� ��Ʈ���迭�� ���ϸ�
		// �����ϱ�---------------------------------
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
		// -----------------�ش� ��ǰ�ڵ��� ������ �ҷ��� �迭�� �����Ű�� �԰��û ������ ��---------------------

		System.out.println("��ǰ�ڵ带 �Է����ּ���.");
		code = scan.next();
		fileCodeName = code + ".txt";
		int status = 0;
		System.out.println("������ �Է����ּ���.");
		quantity = scan.nextInt();
		int temp = 0;
		String[] stredit = new String[100000];

		try {
			File file = new File("C:/" + store + "/" + fileCodeName);
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
			stredit = str.split("/");

			temp = Integer.parseInt(stredit[0]);

			if (quantity > temp) {
				System.out.println("����â���� ������ �����մϴ�");
				System.out.println("����â�� " + code + "��ǰ�� ���� " + temp + "��");
			}
			try {
				int mainQuan = 0;
				mainQuan = Integer.valueOf(stredit[0]) - quantity;
				stredit[0] = String.valueOf(mainQuan);

				OutputStream output = new FileOutputStream("C:/" + store + "/" + stredit[5] + ".txt");
				String main_code = stredit[0] + "/" + stredit[1] + "/" + stredit[2] + "/" + stredit[3] + "/"
						+ stredit[4] + "/" + stredit[5];
				byte[] by = main_code.getBytes();
				output.write(by);
				output.close();
			} catch (Exception e) {
				e.getStackTrace();
			}

			file_r.close();
		} catch (Exception e) {
			System.out.println(e);
		}

		// ==========================�ڵ尡 ����â�� �ִ� �ڵ����� ��===========================
		// ----------------------------------- ����â��� ���� ��
		// ����---------------------------------
		try {
			String path2 = "C:/Productinfo/";

			File f2 = new File(path2);
			File[] files2 = f2.listFiles();
			int count2 = 0;
			for (i = 0; i < files2.length; i++) {
				if (files2[i].isFile())
					count2++;
			}

			// -------------------------- ��ǰ�ڵ� ��Ʈ���迭�� ���ϸ�
			// �����ϱ�---------------------------------
			File dir2 = new File(path2);
			File[] fileList2 = dir2.listFiles();
			String[] fileName2 = new String[count2];
			int ii = 0;
			for (File file : fileList2) {
				if (file.isFile()) {
					fileName2[ii] = file.getName();
					ii++;
				}
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		// -----------------�ش� ��ǰ�ڵ��� ������ �ҷ��� �迭�� �����Ű�� �԰��û ������ ��---------------------

		int temp2 = 0;

		int cur2;
		int zz = 0;
		char[] insert2 = new char[1000000];
		String[] stredit2 = new String[10000000];

		try {
			File file2 = new File("C:/Productinfo/" + fileCodeName);
			FileReader file_r2 = new FileReader(file2);
			while ((cur2 = file_r2.read()) != -1) {
				insert2[zz] = (char) (cur2);
				zz++;
			}

			char[] chstr2 = new char[zz];

			for (int j = 0; j < zz; j++)
				chstr2[j] = insert2[j];

			String str2 = new String(chstr2);
			stredit2 = str2.split("/");

			temp2 = Integer.parseInt(stredit2[0]);

			int storeQuan = 0;
			storeQuan = Integer.valueOf(stredit2[0]) + quantity;
			stredit2[0] = String.valueOf(storeQuan);

			OutputStream output = new FileOutputStream("C:/Productinfo/" + stredit2[5] + ".txt");
			String str = stredit2[0] + "/" + stredit2[1] + "/" + stredit2[2] + "/" + stredit2[3] + "/" + stredit2[4]
					+ "/" + stredit2[5];
			byte[] by = str.getBytes();
			output.write(by);
			System.out.println(store + "����â��� " + code + " " + quantity + "�� ��� �Ϸ�Ǿ����ϴ�.");
			file_r2.close();

		} catch (Exception e) {
			try {
				int storeQuan = 0;
				storeQuan = quantity;
				stredit2[0] = String.valueOf(storeQuan);

				OutputStream output = new FileOutputStream("C:/Productinfo/" + stredit[5] + ".txt");
				String str = stredit2[0] + "/" + stredit[1] + "/" + stredit[2] + "/" + stredit[3] + "/" + stredit[4]
						+ "/" + stredit[5];
				byte[] by = str.getBytes();
				output.write(by);
				System.out.println(store + "�������� " + code + " " + quantity + "�� ��� �Ϸ�Ǿ����ϴ�.");

			} catch (Exception ee) {
				ee.getStackTrace();
			}
		}
	}

}