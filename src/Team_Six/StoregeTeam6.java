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

	int scanInt() {// 정수형 스캔입력 함수 설계 - 선영우, 허준욱
		int number = 0;
		boolean t = true;
		while (t) {
			try {
				Scanner scan = new Scanner(System.in);
				number = scan.nextInt();
				t = false;
			} catch (InputMismatchException e) {
				// TODO: handle exception
				System.out.println("잘 못 입력하셨습니다 숫자를 입력해주세요.");
			}
		}
		return number;
	}

	String scanStr() { // 문자열 스캔입력 함수 설계 - 선영우. 허준욱
		String str = "";
		boolean t = true;
		while (t) {
			try {
				Scanner scan = new Scanner(System.in);
				str = scan.next();
				t = false;
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println("잘 못 입력하셨습니다.");
			}
		}
		return str;
	}

	int managerLogin() { // 관리자로그인 함수 설계 - 정완만님
//		Scanner pass = new Scanner(System.in);
		String mPass = "cap0159";
		// 기능 구현
		System.out.println("관리자 비밀번호 6자리를 입력하세요!");
		String ManagerPass = scanStr();

		if (ManagerPass.equals(mPass)) {
			System.out.println("관리자 로그인 되었습니다.");
			return 1;

		} else {
			System.out.println("잘 못 입력하셨습니다.");
//			managerLogin();
			return -1;
		}
	}

	void franchiseLogin(String franchiseName) { // 매장로그인 함수 설계 - 정완만님

		String[] fPass = { "강남", "서초", "목포" };

		for (int i = 0; i < fPass.length; i++) {
			if (franchiseName.equals(fPass[i])) {
				System.out.println(fPass[i] + "점에 오신 것을 환영합니다");
			}
		}
	}

	void wrongSearch(String franchiseName) { // 찾는 매장이 없을경우 오류구문 출력 함수 - 선영우님

		Store call = new Store();

		int state = 0;

		for (int i = 0; i < call.sname.length; i++) {
			if (franchiseName.equals(call.sname[i]))
				break;

			state++;
		}

		if (state == call.sname.length)
			System.out.println("찾으시는 매장이 없습니다.");
	}

	void productManage() throws IOException {

		Date today = new Date();
		StoregeTeam6 management = new StoregeTeam6();
		System.out.println("1.제품등록 \t 2.제품갱신 \t 3.제품삭제 \t 4.뒤로가기");

		int intputNum3 = scanInt();
		if (intputNum3 == 1)
			productInput();
		if (intputNum3 == 2)
			productupdate();
		if (intputNum3 == 3)
			productdelete();
	}

	void productInput() throws IOException {// 제품 등록 함수 설계 - 최종완료

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
			System.out.println("종류를 입력하세요. 0.메인화면으로");
			m.type = scanStr();
			if (m.type.equals("0"))
				break;
			else {
				state++;
			}
			if (state == 1) {
				System.out.println("디자인을 입력하세요 0.다시입력");
				m.design = scanStr();
				if (m.design.equals("0"))
					continue;
				else
					state++;

				if (state == 2) {
					System.out.println("색상을 입력하세요 0.처음으로");
					m.color = scanStr();
					if (m.color.equals("0"))
						continue;
					else {
						state++;
					}

					if (state == 3) {
						System.out.println("사이즈를 입력하세요 0.처음으로");
						m.size = scanStr();
						if (m.size.equals("0"))
							continue;
						else
							state++;

						if (state == 4) {
							System.out.println("수량을 입력하세요. 0.처음으로");
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
			System.out.println("제품코드 : " + s[5]);
			System.out.println("수량 : " + s[0]);
			management.logprint("제품등록:" + s[5] + "  " + s[0] + "  " + "관리자 " + today);

			break;
		}
	}

	void productSearch() {// 제품 검색 함수 설계 - 최종완료
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
			System.out.println("검색 코드를 입력해주세요.");

			String search = scanStr();

			for (int p = 0; p < code22.size(); p++) {
				if (search.equals(code22.get(p)[1])) {
					System.out.println(code22.get(p)[5]);
					System.out.println("수량 : " + code22.get(p)[0]);
				}
				if (search.equals(code22.get(p)[1] + code22.get(p)[2])) {
					System.out.println(code22.get(p)[5]);
					System.out.println("수량 : " + code22.get(p)[0]);
				}
				if (search.equals(code22.get(p)[1] + code22.get(p)[2] + code22.get(p)[3])) {
					System.out.println(code22.get(p)[5]);
					System.out.println("수량 : " + code22.get(p)[0]);
				}
				if (search.equals(code22.get(p)[5])) {
					System.out.println(code22.get(p)[5]);
					System.out.println("수량 : " + code22.get(p)[0]);
				}
			}
		}
	}

	void productupdate() {// 재고 갱신 함수 설계 - 최종완료
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
		System.out.println("갱신 할 제품의 코드를 쳐주세요.");
//			Scanner scan = new Scanner(System.in);
		String search = scanStr();

		search = search + ".txt";
		int status = 0;
		for (int y = 0; y < fileName.length; y++) {
			if (search.equals(fileName[y])) {
				break;
			} else if (y == fileName.length - 1) {
				System.out.println("제품이 없습니다.");
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
				System.out.println("코드: " + id[5] + "\t" + "수량 : " + a);
				System.out.println("");
				System.out.println("새로운 수량을 입력해주세요.");
				int ww = scanInt();
				id[0] = String.valueOf(ww);

				try {
					OutputStream output = new FileOutputStream("C:/ProductInfo/" + id[5] + ".txt");
					String str = id[0] + "/" + id[1] + "/" + id[2] + "/" + id[3] + "/" + id[4] + "/" + id[5];
					byte[] by = str.getBytes();
					output.write(by);
					System.out.println("변경이 완료되었습니다.");
					management.logprint("제품갱신: " + id[5] + "  " + "관리자 " + today);
				} catch (Exception e) {
					e.getStackTrace();
				}

				file_r.close();
			} catch (FileNotFoundException e) {
			} catch (Exception e) {
			}
		}
	}

	void productdelete() throws IOException {// 제품 삭제 함수 설계 - 최종완료
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
			System.out.println("삭제 할 제품의 코드를 쳐주세요.\t0.뒤로가기");
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
					System.out.println("제품이 없습니다.");
					status = -1;
					break;
				}
				status++;
			}
			if (status != -1) {
				File mFile = new File("C:/ProductInfo/" + search);
				boolean t = false;
				t = mFile.delete();
				System.out.println("삭제가 완료 되었습니다.");
				management.logprint("제품삭제: " + code + "  " + "관리자 " + today);
			}
		}
	}

	void productRecall(String store, String code, int quantity) { // 매장 반품 수량 변경 함수 설계 - 오재근님
		// ----------------------------------- 매장 명을
		// 받는다.---------------------------------
		Date today = new Date();
		StoregeTeam6 management = new StoregeTeam6();
		Store sto = new Store();

//		Scanner scan = new Scanner(System.in);

		String fileCodeName = "";

		// ==========================코드가 메인창고에 있는 코드인지 비교===========================
		// ----------------------------------- 메인창고안 파일 수
		// 세기---------------------------------
		String path = "C:/" + store + "/";
		File f = new File(path);
		File[] files = f.listFiles();
		int count = 0;
		for (int i = 0; i < files.length; i++) {
			if (files[i].isFile())
				count++;
		}

		// -------------------------- 제품코드 스트링배열에 파일명
		// 저장하기---------------------------------
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
		// -----------------해당 제품코드의 파일을 불러와 배열에 저장시키고 입고신청 수량과 비교---------------------

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
				System.out.println("매장창고의 수량이 부족합니다");
				System.out.println("매장창고 " + code + "제품의 수량 " + temp + "개");
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

		// ==========================코드가 매장창고에 있는 코드인지 비교===========================
		// ----------------------------------- 매장창고안 파일 수
		// 세기---------------------------------
		try {
			String path2 = "C:/Productinfo/";

			File f2 = new File(path2);
			File[] files2 = f2.listFiles();
			int count2 = 0;
			for (i = 0; i < files2.length; i++) {
				if (files2[i].isFile())
					count2++;
			}

			// -------------------------- 제품코드 스트링배열에 파일명
			// 저장하기---------------------------------
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
		// -----------------해당 제품코드의 파일을 불러와 배열에 저장시키고 입고신청 수량과 비교---------------------

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
			System.out.println(store + "메인창고로 " + code + " " + quantity + "개 반품 처리가 완료되었습니다.");
			management.logprint(store + "에서 제품반품: " + code + "  반품 :" + quantity + "  " + "매장관리자 " + today);
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
				System.out.println(store + "매장으로 " + code + "  출고 :" + quantity + "개 반품 처리가 완료되었습니다.");

			} catch (Exception ee) {
				ee.getStackTrace();
			}
		}
	}

	void productRelease() {// 메인 창고 제품 출고 함수 - 오재근님
		Date today = new Date();
		StoregeTeam6 management = new StoregeTeam6();
		// ----------------------------------- 매장 명을
		// 받는다.---------------------------------
		Store sto = new Store();
		String store = "";
		int status = 0;
		Scanner scan = new Scanner(System.in);
		boolean aadd = true;
		while (aadd) {
		System.out.println("매장출고입니다. 매장 이름을 입력해주세요.");
		store = scan.next();
		Store sto333 = new Store();
		for(int i =0 ; i <sto333.sname.length; i++) {
			if(store.equals(sto333.sname[i])) { 
				aadd = false ; 
			break;
		}else if(i==sto333.sname.length-1) {
				System.out.println("지점명을 잘못 입력하셨습니다.");
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
					System.out.println(sto.sname[zz] + "매장을 선택하셨습니다.");
				}
			}

			// ==========================코드가 메인창고에 있는 코드인지 비교===========================
			// ----------------------------------- 메인창고안 파일 수
			// 세기---------------------------------
			String path = "C:/Productinfo/";
			File f = new File(path);
			File[] files = f.listFiles();
			int count = 0;
			for (int i = 0; i < files.length; i++) {
				if (files[i].isFile())
					count++;
			}

			// -------------------------- 제품코드 스트링배열에 파일명
			// 저장하기---------------------------------
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
			// -----------------해당 제품코드의 파일을 불러와 배열에 저장시키고 입고신청 수량과 비교---------------------

			System.out.println("제품코드를 입력해주세요.");
			code = scan.next();
			fileCodeName = code + ".txt";
			status = 0;

			for (i = 0; i < fileName.length; i++) { // 제품코드 파일명 검색
				if (fileCodeName.equals(fileName[i])) // 제품코드의 파일명이 있으면 반복문 종료
					break;
				else if (i == fileName.length - 1) { // 제품코드의 파일명이 없으면 오류구문 추가
					System.out.println("해당 제품코드 정보가 없습니다.");
					status = -1; // 파일이 없으면 상태 변수 -1로 초기화
				}
			}
			if (status == -1)
				continue;

			System.out.println("수량을 입력해주세요.");
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
					System.out.println("메인창고의 수량이 부족합니다");
					System.out.println("메인창고 " + code + "제품의 수량 " + temp + "개");
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

			// ==========================코드가 매장창고에 있는 코드인지 비교===========================
			// ----------------------------------- 매장창고안 파일 수
			// 세기---------------------------------
			try {
				String path2 = "C:/"+store+"/";

				File f2 = new File(path2);
				File[] files2 = f2.listFiles();
				int count2 = 0;
				for (i = 0; i < files2.length; i++) {
					if (files2[i].isFile())
						count2++;
				}

				// -------------------------- 제품코드 스트링배열에 파일명
				// 저장하기---------------------------------
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
			// -----------------해당 제품코드의 파일을 불러와 배열에 저장시키고 입고신청 수량과 비교---------------------

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
				System.out.println(store + "매장으로 " + code + " " + quantity + "개 출고가 완료되었습니다.");
				management.logprint("제품출고: " + store + "  " + code + " " + quantity + "  " + "관리자 " + today);
				file_r2.close();

			} catch (Exception e) {
				System.out.println("매장에 해당 제품이 없습니다. 출고데이터를 새로 전송하겠습니다.");
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
					System.out.println(store + "매장으로 " + code + " " + quantity + "개 출고가 완료되었습니다.");
					management.logprint("제품출고: " + store + "  " + code + " " + quantity + "  " + "관리자 " + today);

				} catch (Exception ee) {
					ee.getStackTrace();
				}
			}
			break;
			
		}
		
	}

	void logprint(String txt) throws IOException {// 로그기능(로그2번,3번기능 조합 : 열기 + 저장하기) 함수 설계 - 오재근님
		String text = txt;
		String abb = "\r\n" + text;
		String acc = StoregeTeam6.getInstance().logprint2() + abb;
		StoregeTeam6.getInstance().logprint3(acc);

	}

	String logprint2() throws IOException {// 로그기능(불러오기) 함수 설계 - 오재근님
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

	void logprint3(String quan) throws IOException {// 로그기능(저장하기) 함수 설계 - 오재근님
		OutputStream log = new FileOutputStream("C:/Log/log.txt");
		try {
			byte[] text = quan.getBytes();
			log.write(text);
			log.close();
		} catch (Exception e) {
		}
	}

}
