package Team_Six;

import java.io.IOException;
import java.util.Date;
import java.util.Scanner;

public class Project_Team_Six_Main {

	public static void main(String[] args) throws IOException { // 메인 설계 - 송종원님
		Date today = new Date();
		StoregeTeam6 management = StoregeTeam6.getInstance();
		WarehousingRecall sWR = new WarehousingRecall();
		MyQueue queueMessage = new MyQueue();
		Store call = new Store();

		while (true) {

			System.out.println("1. 관리자 로그인 \t 2. 매장 로그인");
//			Scanner scan = new Scanner(System.in);
			int inputNum1 = management.scanInt();
					
			if (inputNum1 == 1) {
				int login = management.managerLogin();
				management.logprint("관리자 로그인 " +today);
				
				if(login == -1)
					continue;
				
				else if(login != -1)
				queueMessage.frameMakerWR();

				while (true) {

					System.out.println("1.제품관리 \t 2.제품검색 \t 3.입출고관리 \t 4.로그아웃");

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
						System.out.println("로그아웃");
						management.logprint("관리자 로그아웃 " +today);
						break;
					}
				}
			}

			if (inputNum1 == 2) {// 매장 기능 플레이

				System.out.println("매장 관리자 로그인을 위해 매장명을 입력하세요!");
				String franchiseName = management.scanStr();
				management.franchiseLogin(franchiseName);

				while (franchiseName.equals("강남")) {
					System.out.println("강남 지점\n");
					System.out.println("1.제품관리 \t 2.제품검색 \t 3.입고 반품 처리 \t 4.로그아웃  ");

					int inputNum3 = management.scanInt();

					if (inputNum3 == 1) {
						call.productManage(franchiseName);
					}

					if (inputNum3 == 2) {
						System.out.println("제품 검색");
						call.storeSearch("강남");
					}
					if (inputNum3 == 3) 
						sWR.storeWR(queueMessage, franchiseName);
					
					if (inputNum3 == 4) {
						System.out.println("로그아웃");
						break;
					}
				}

				while (franchiseName.equals("서초")) {
					System.out.println("서초 지점\n");
					System.out.println("1.제품관리 \t 2.제품검색 \t 3.입고 반품 관리 \t 4.로그아웃");

					int inputNum4 = management.scanInt();

					if (inputNum4 == 1) {
					
						call.productManage(franchiseName);
					}

					if (inputNum4 == 2) {
						System.out.println("제품 검색");
						call.storeSearch("서초");
					}

					if (inputNum4 == 3) {
						sWR.storeWR(queueMessage, franchiseName);
					}

					if (inputNum4 == 4) {
						System.out.println("로그아웃");
						break;
					}
				}

				while (franchiseName.equals("목포")) {
					System.out.println("목포 지점\n");
					System.out.println("1.제품관리 \t 2.제품검색 \t 3.입고 반품 관리 \t 4.로그아웃");

					int inputNum6 = management.scanInt();

					if (inputNum6 == 1) {
						call.productManage(franchiseName);
					}

					if (inputNum6 == 2) {
						System.out.println("제품 검색");
						call.storeSearch("목포");
					}
					if (inputNum6 == 3) 
						sWR.storeWR(queueMessage, franchiseName);

					if (inputNum6 == 4) {
						System.out.println("로그아웃");
						break;
					}

				}
				
				management.wrongSearch(franchiseName); // 찾는 매장 없는 오류구문 출력
				
			}

		}

	}

}
