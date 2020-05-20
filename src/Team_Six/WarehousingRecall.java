package Team_Six;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class WarehousingRecall { // 클래스 설계 - 선영우님

	StoregeTeam6 management = StoregeTeam6.getInstance();

	void storeWR(MyQueue queueMessage, String franchiseName) { // 입고 신청 반품 신청 메인 함수 설계 - 선영우님

		while (true) {

			System.out.println("1.입고 신청 \t 2.반품 관리 \t 3.취소");
//			Scanner scan = new Scanner(System.in);
			int num = management.scanInt();
			if (num == 1) {

//----------------------------------- 폴더안 파일 수 세기---------------------------------
				String path = "C:/ProductInfo/";
				File f = new File(path);
				File[] files = f.listFiles();

				int count = 0;
				for (int i = 0; i < files.length; i++) {
					if (files[i].isFile())
						count++;
				}

//-------------------------- 제품코드 스트링배열에 파일명 하나 하나 저장하기---------------------------------

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

//-----------------------해당 제품코드의 파일을 불러와 배열에 저장시키고 입고신청 수량과 비교 ---------------------------------

				System.out.println("제품코드를 입력해주세요");
				String code = management.scanStr();

				String fileCodeName = code + ".txt";

				int status = 0;

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
				int quantity = management.scanInt();
				int temp = 0;
				if (status != -1) { // 제품코드와 맞는 파일이 있으면 해당파일의 내용 불러오기
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
							System.out.println("메인창고의 수량이 부족합니다");
							System.out.println("메인창고 " + code + "제품의 수량 " + temp + "개");
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
				System.out.println("제품코드:" + code + " 수량:" + quantity + "개 입고 신청이 완료 되었습니다.");
			}
//--------------------------------------반품 신청---------------------------------------------

			if (num == 2) {
//----------------------------------- 폴더안 파일 수 세기---------------------------------
				String path = "C:/강남/";
				File f = new File(path);
				File[] files = f.listFiles();

				int count = 0;
				for (int i = 0; i < files.length; i++) {
					if (files[i].isFile())
						count++; // 폴더 안 파일 수
				}

//-------------------------- 제품코드 스트링배열에 파일명 하나 하나 저장하기---------------------------------

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

//-----------------------해당 제품코드의 파일을 불러와 배열에 저장시키고 입고신청 수량과 비교 ---------------------------------

				System.out.println("제품코드를 입력해주세요");
				String code = management.scanStr();

				String fileCodeName = code + ".txt";

				int status = 0;

				for (i = 0; i < fileName.length; i++) { // 제품코드 파일명 검색
					if (fileCodeName.equals(fileName[i])) // 제품코드의 파일명이 있으면 반복문 종료
						break;
					else if (i == fileName.length - 1) { // 제품코드의 파일명이 없으면 오류구문 추가
						System.out.println("해당 제품코드 정보가 없습니다.");
						status = -1; // 파일이 없으면 상태 변수 -1로 초기화
						break;
					}

				}

				if (status == -1)
					continue;

				System.out.println("수량을 입력해주세요.");
				int quantity = management.scanInt();

				queueMessage.snadMessageR(franchiseName, code, quantity);
				queueMessage.loginMessageSaveR(franchiseName, code, quantity);

//				System.out.println("제품코드:" + code + " 수량:" + quantity + "개 반품 처리가 완료 되었습니다.");
				
				management.productRecall(franchiseName, code, quantity);
				
			}

			if (num == 3)
				break;
		}
		
		

	}

	void managementWR(MyQueue queueMessage) {// 큐안에 들어있는 메시지를 출력하는 함수 설계 - 선영우님

		System.out.println("1.출고 관리\t2.매장 메시지 확인");
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
