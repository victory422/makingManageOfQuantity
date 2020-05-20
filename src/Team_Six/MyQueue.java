package Team_Six;

import java.util.LinkedList;
import java.util.Queue;

import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

public class MyQueue { // 클래스 설계 - 선영우님
	
	Queue<InputInfo> messageSaveW = new LinkedList<InputInfo>();
	Queue<InputInfo> messageSaveR = new LinkedList<InputInfo>();
	Queue<InputInfo> loginMessageSaveW = new LinkedList<InputInfo>();
	Queue<InputInfo> loginMessageSaveR = new LinkedList<InputInfo>();
	InputInfo tempWR = new InputInfo();

	void snadMessageW(String franchise, String code, int quantity) { // 입고신청 메시지를 큐에 저장하는 함수 설계 - 선영우님
		InputInfo temp = new InputInfo();

		temp.setFranchise(franchise);
		temp.setCode(code);
		temp.setQuantity(quantity);

		messageSaveW.offer(temp);
	}
	
	void loginMessageSaveW(String franchise, String code, int quantity) { // 입고신청 메시지를 큐에 저장하는 함수 설계 - 선영우님
		InputInfo temp = new InputInfo();

		temp.setFranchise(franchise);
		temp.setCode(code);
		temp.setQuantity(quantity);

		loginMessageSaveW.offer(temp);
	}

	void printMessageW() { // 입고신청 메시지 출력하는 프린트 함수 설계 - 선영우님

		if (messageSaveW.size() >= 1) {
			int count = messageSaveW.size();
			for (int i = 0; i < count; i++) {
				InputInfo temp = new InputInfo();
				temp = messageSaveW.poll();
				System.out.println("-(알림 " + temp.getFranchise() + ") 코드:" + temp.getCode() + "제품 " + temp.getQuantity()
						+ "개의 입고 신청이 있습니다.-");
			}
		} else
			System.out.println("입고 신청 알림메시지가 없습니다.");
	}

	void snadMessageR(String franchise, String code, int quantity) { // 반품신청 메시지 큐에 저장하는 함수 설계 - 선영우님
		InputInfo temp = new InputInfo();

		temp.setFranchise(franchise);
		temp.setCode(code);
		temp.setQuantity(quantity);

		messageSaveR.offer(temp);
	}

	void printMessageR() { // 반품신청 메시지 출력하는 프린트 함수 설계 - 선영우님

		if (messageSaveR.size() >= 1) {
			int count = messageSaveR.size();
			for (int i = 0; i < count; i++) {
				InputInfo temp = new InputInfo();
				temp = messageSaveR.poll();
				System.out.println("-(알림 " + temp.getFranchise() + ") 코드:" + temp.getCode() + "제품 " + temp.getQuantity()
						+ "개의 반품 신청이 있습니다.-");
			}
		} else
			System.out.println("반품 신청 알림메시지가 없습니다.");
	}

	void loginMessageSaveR(String franchise, String code, int quantity) { // 입고신청 메시지를 큐에 저장하는 함수 설계 - 선영우님
		InputInfo temp = new InputInfo();

		temp.setFranchise(franchise);
		temp.setCode(code);
		temp.setQuantity(quantity);

		loginMessageSaveR.offer(temp);
	}
	
	void frameMakerWR() { // 프레임팝업 창 띄우기 함수 설계 - 선영우님, 오재근님
		Runnable w = () -> {
			int count = this.loginMessageSaveW.size();
			if (count >= 1) {
				for (int i = 0; i < count; i++) {
					InputInfo temp2 = new InputInfo();
					temp2 = loginMessageSaveW.poll();
					String html = "<html><body width='%1s'><h2>" + temp2.getFranchise() + "매장에서 메세지가 있습니다.</h2>"
							+ "<p>" + temp2.getCode() + " : " + temp2.getQuantity() + "개 입고 신청\n";
					int width = 330; // change to alter the width
					JOptionPane.showMessageDialog(null, String.format(html, width, width));
				}
			}
		};
		SwingUtilities.invokeLater(w);
		
		Runnable r = () -> {
			int count = this.loginMessageSaveR.size();
			if (count >= 1) {
				for (int i = 0; i < count; i++) {
					InputInfo temp2 = new InputInfo();
					temp2 = loginMessageSaveR.poll();
					String html = "<html><body width='%1s'><h2>" + temp2.getFranchise() + "매장에서 메세지가 있습니다.</h2>"
							+ "<p>" + temp2.getCode() + " : " + temp2.getQuantity() + "개 반품 들어왔습니다.\n";
					int width = 330; // change to alter the width
					JOptionPane.showMessageDialog(null, String.format(html, width, width));
				}
			}
		};
		SwingUtilities.invokeLater(r);
	}

}
