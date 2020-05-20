package Team_Six;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Scanner;

public class StoregeTeam6 {

	private static StoregeTeam6 instance;

	private StoregeTeam6() {
	}

	public static StoregeTeam6 getInstance() {
		if (instance == null) {
			instance = new StoregeTeam6();
		}
		return instance;
	}

	int scanInt() {// ������ ��ĵ�Է� �Լ� ���� - ������, ���ؿ�
		int number = 0;
		boolean t = true;
		while (t) {
			try {
				Scanner scan = new Scanner(System.in);
				number = scan.nextInt();
				t = false;
			} catch (InputMismatchException e) {
				// TODO: handle exception
				System.out.println("�� �� �Է��ϼ̽��ϴ� ���ڸ� �Է����ּ���.");
			}
		}
		return number;
	}

	String scanStr() { // ���ڿ� ��ĵ�Է� �Լ� ���� - ������. ���ؿ�
		String str = "";
		boolean t = true;
		while (t) {
			try {
				Scanner scan = new Scanner(System.in);
				str = scan.next();
				t = false;
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println("�� �� �Է��ϼ̽��ϴ�.");
			}
		}
		return str;
	}

	int managerLogin() { // �����ڷα��� �Լ� ���� - ���ϸ���
//		Scanner pass = new Scanner(System.in);
		String mPass = "cap0159";
		// ��� ����
		System.out.println("������ ��й�ȣ 6�ڸ��� �Է��ϼ���!");
		String ManagerPass = scanStr();

		if (ManagerPass.equals(mPass)) {
			System.out.println("������ �α��� �Ǿ����ϴ�.");
			return 1;

		} else {
			System.out.println("�� �� �Է��ϼ̽��ϴ�.");
//			managerLogin();
			return -1;
		}
	}

	void franchiseLogin(String franchiseName) { // ����α��� �Լ� ���� - ���ϸ���

		String[] fPass = { "����", "����", "����" };

		for (int i = 0; i < fPass.length; i++) {
			if (franchiseName.equals(fPass[i])) {
				System.out.println(fPass[i] + "���� ���� ���� ȯ���մϴ�");
			}
		}
	}

	void wrongSearch(String franchiseName) { // ã�� ������ ������� �������� ��� �Լ� - �������

		Store call = new Store();

		int state = 0;

		for (int i = 0; i < call.sname.length; i++) {
			if (franchiseName.equals(call.sname[i]))
				break;

			state++;
		}

		if (state == call.sname.length)
			System.out.println("ã���ô� ������ �����ϴ�.");
	}

	void productManage() throws IOException {

		Date today = new Date();
		StoregeTeam6 management = new StoregeTeam6();
		System.out.println("1.��ǰ��� \t 2.��ǰ���� \t 3.��ǰ���� \t 4.�ڷΰ���");

		int intputNum3 = scanInt();
		if (intputNum3 == 1)
			productInput();
		if (intputNum3 == 2)
			productupdate();
		if (intputNum3 == 3)
			productdelete();
	}

	void productInput() throws IOException {// ��ǰ ��� �Լ� ���� - �����Ϸ�

		Date today = new Date();
		StoregeTeam6 management = new StoregeTeam6();
		int i = 001;

		ProductInfo m = new ProductInfo();
		ArrayList<String[]> mylist = new ArrayList<String[]>();
//		Scanner scan = new Scanner(System.in);

		String s[] = new String[6];
		mylist.add(s);

		while (true) {
			int state = 0;
			System.out.println("������ �Է��ϼ���. 0.����ȭ������");
			m.type = scanStr();
			if (m.type.equals("0"))
				break;
			else {
				state++;
			}
			if (state == 1) {
				System.out.println("�������� �Է��ϼ��� 0.�ٽ��Է�");
				m.design = scanStr();
				if (m.design.equals("0"))
					continue;
				else
					state++;

				if (state == 2) {
					System.out.println("������ �Է��ϼ��� 0.ó������");
					m.color = scanStr();
					if (m.color.equals("0"))
						continue;
					else {
						state++;
					}

					if (state == 3) {
						System.out.println("����� �Է��ϼ��� 0.ó������");
						m.size = scanStr();
						if (m.size.equals("0"))
							continue;
						else
							state++;

						if (state == 4) {
							System.out.println("������ �Է��ϼ���. 0.ó������");
							m.quantity = scanStr();
							if (m.quantity.equals("0"))
								continue;
							else
								state++;
						}
					}
				}
			}

			m.code = m.codefunc();
			s[0] = m.quantity;
			s[1] = m.type;
			s[2] = m.design;
			s[3] = m.color;
			s[4] = m.size;
			s[5] = m.code;
			try {
				OutputStream output = new FileOutputStream("C:/ProductInfo/" + s[5] + ".txt");
				String str = s[0] + "/" + s[1] + "/" + s[2] + "/" + s[3] + "/" + s[4] + "/" + s[5];
				byte[] by = str.getBytes();
				output.write(by);
				output.close();
			} catch (Exception e) {
				e.getStackTrace();
			}
			System.out.println("��ǰ�ڵ� : " + s[5]);
			System.out.println("���� : " + s[0]);
			management.logprint("��ǰ���:" + s[5] + "  " + s[0] + "  " + "������ " + today);

			break;
		}
	}

	void productSearch() {// ��ǰ �˻� �Լ� ���� - �����Ϸ�
		Date today = new Date();
		StoregeTeam6 management = new StoregeTeam6();
		while (true) {
			String path = "C:/ProductInfo/";
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
						File mFile = new File("C:/ProductInfo/" + file.getName());
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

			String search = scanStr();

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

	void productupdate() {// ��� ���� �Լ� ���� - �����Ϸ�
		Date today = new Date();
		StoregeTeam6 management = new StoregeTeam6();
		String path = "C:/ProductInfo/";
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
		System.out.println("���� �� ��ǰ�� �ڵ带 ���ּ���.");
//			Scanner scan = new Scanner(System.in);
		String search = scanStr();

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
				File mFile = new File("C:/ProductInfo/" + search);
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
				int ww = scanInt();
				id[0] = String.valueOf(ww);

				try {
					OutputStream output = new FileOutputStream("C:/ProductInfo/" + id[5] + ".txt");
					String str = id[0] + "/" + id[1] + "/" + id[2] + "/" + id[3] + "/" + id[4] + "/" + id[5];
					byte[] by = str.getBytes();
					output.write(by);
					System.out.println("������ �Ϸ�Ǿ����ϴ�.");
					management.logprint("��ǰ����: " + id[5] + "  " + "������ " + today);
				} catch (Exception e) {
					e.getStackTrace();
				}

				file_r.close();
			} catch (FileNotFoundException e) {
			} catch (Exception e) {
			}
		}
	}

	void productdelete() throws IOException {// ��ǰ ���� �Լ� ���� - �����Ϸ�
		Date today = new Date();
		StoregeTeam6 management = new StoregeTeam6();
		while (true) {
			String path = "C:/ProductInfo/";
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
			String code = scanStr();
			if (code.equals("0")) {
				break;
			}
			String search = code + ".txt";
			int status = 0;
			for (int y = 0; y < fileName.length; y++) {
				if (search.equals(fileName[y])) {
					break;
				} else if (y == fileName.length - 1) {
					System.out.println("��ǰ�� �����ϴ�.");
					status = -1;
					break;
				}
				status++;
			}
			if (status != -1) {
				File mFile = new File("C:/ProductInfo/" + search);
				boolean t = false;
				t = mFile.delete();
				System.out.println("������ �Ϸ� �Ǿ����ϴ�.");
				management.logprint("��ǰ����: " + code + "  " + "������ " + today);
			}
		}
	}

	void productRecall(String store, String code, int quantity) { // ���� ��ǰ ���� ���� �Լ� ���� - ����ٴ�
		// ----------------------------------- ���� ����
		// �޴´�.---------------------------------
		Date today = new Date();
		StoregeTeam6 management = new StoregeTeam6();
		Store sto = new Store();

//		Scanner scan = new Scanner(System.in);

		String fileCodeName = "";

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

		fileCodeName = code + ".txt";
		int status = 0;

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
System.out.println(main_code);
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
			System.out.println(store + "����â��� " + code + " " + quantity + "�� ��ǰ ó���� �Ϸ�Ǿ����ϴ�.");
			management.logprint(store + "���� ��ǰ��ǰ: " + code + "  ��ǰ :" + quantity + "  " + "��������� " + today);
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
				System.out.println(store + "�������� " + code + "  ��� :" + quantity + "�� ��ǰ ó���� �Ϸ�Ǿ����ϴ�.");

			} catch (Exception ee) {
				ee.getStackTrace();
			}
		}
	}

	void productRelease() {// ���� â�� ��ǰ ��� �Լ� - ����ٴ�
		Date today = new Date();
		StoregeTeam6 management = new StoregeTeam6();
		// ----------------------------------- ���� ����
		// �޴´�.---------------------------------
		Store sto = new Store();
		String store = "";
		int status = 0;
		Scanner scan = new Scanner(System.in);
		boolean aadd = true;
		while (aadd) {
		System.out.println("��������Դϴ�. ���� �̸��� �Է����ּ���.");
		store = scan.next();
		Store sto333 = new Store();
		for(int i =0 ; i <sto333.sname.length; i++) {
			if(store.equals(sto333.sname[i])) { 
				aadd = false ; 
			break;
		}else if(i==sto333.sname.length-1) {
				System.out.println("�������� �߸� �Է��ϼ̽��ϴ�.");
				status =-1;
			}		
		}

		if(status == -1) continue;
		break;
		} 
		
		
		String code = "";
		String fileCodeName = "";
		int quantity = 0;
		while (true) {
			for (int zz = 0; zz < sto.sname.length; zz++) {
				if (store.equals(sto.sname[zz])) {
					System.out.println(sto.sname[zz] + "������ �����ϼ̽��ϴ�.");
				}
			}

			// ==========================�ڵ尡 ����â�� �ִ� �ڵ����� ��===========================
			// ----------------------------------- ����â��� ���� ��
			// ����---------------------------------
			String path = "C:/Productinfo/";
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
			status = 0;

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
			quantity = scan.nextInt();
			int temp = 0;
			String[] stredit = new String[100000];

			try {
				File file = new File("C:/Productinfo/" + fileCodeName);
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

					OutputStream output = new FileOutputStream("C:/Productinfo/" + stredit[5] + ".txt");
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
				String path2 = "C:/"+store+"/";

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
				File file2 = new File("C:/"+store+"/" + fileCodeName);
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

				@SuppressWarnings("resource")
				OutputStream output = new FileOutputStream("C:/"+store+"/"+stredit2[5] + ".txt");
				String str = stredit2[0] + "/" + stredit2[1] + "/" + stredit2[2] + "/" + stredit2[3] + "/" + stredit2[4]
						+ "/" + stredit2[5];
				byte[] by = str.getBytes();
				output.write(by);
				System.out.println(store + "�������� " + code + " " + quantity + "�� ��� �Ϸ�Ǿ����ϴ�.");
				management.logprint("��ǰ���: " + store + "  " + code + " " + quantity + "  " + "������ " + today);
				file_r2.close();

			} catch (Exception e) {
				System.out.println("���忡 �ش� ��ǰ�� �����ϴ�. ������͸� ���� �����ϰڽ��ϴ�.");
				try {
					int storeQuan = 0;
					storeQuan = quantity;
					stredit2[0] = String.valueOf(storeQuan);

					@SuppressWarnings("resource")
					OutputStream output = new FileOutputStream("C:/"+store+"/" + stredit[5] + ".txt");
					String str = stredit2[0] + "/" + stredit[1] + "/" + stredit[2] + "/" + stredit[3] + "/" + stredit[4]
							+ "/" + stredit[5];
					byte[] by = str.getBytes();
					output.write(by);
					System.out.println(store + "�������� " + code + " " + quantity + "�� ��� �Ϸ�Ǿ����ϴ�.");
					management.logprint("��ǰ���: " + store + "  " + code + " " + quantity + "  " + "������ " + today);

				} catch (Exception ee) {
					ee.getStackTrace();
				}
			}
			break;
			
		}
		
	}

	void logprint(String txt) throws IOException {// �αױ��(�α�2��,3����� ���� : ���� + �����ϱ�) �Լ� ���� - ����ٴ�
		String text = txt;
		String abb = "\r\n" + text;
		String acc = StoregeTeam6.getInstance().logprint2() + abb;
		StoregeTeam6.getInstance().logprint3(acc);

	}

	String logprint2() throws IOException {// �αױ��(�ҷ�����) �Լ� ���� - ����ٴ�
		File mFile = new File("C:/Log/log.txt");
		FileReader file_r = new FileReader(mFile);
		int count = 0;
		char arr[] = new char[10000];
		int index = 0;

		while ((count = file_r.read()) != -1) {
			arr[index] = (char) count;
			index++;
		}
		char abc[] = new char[index];
		for (int i = 0; i < index; i++) {
			abc[i] = arr[i];
		}
		file_r.close();
		String txt = new String(abc);
		return txt;
	}

	void logprint3(String quan) throws IOException {// �αױ��(�����ϱ�) �Լ� ���� - ����ٴ�
		OutputStream log = new FileOutputStream("C:/Log/log.txt");
		try {
			byte[] text = quan.getBytes();
			log.write(text);
			log.close();
		} catch (Exception e) {
		}
	}

}
