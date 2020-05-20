package Team_Six;

import java.util.LinkedList;
import java.util.Queue;

import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

public class MyQueue { // Ŭ���� ���� - �������
	
	Queue<InputInfo> messageSaveW = new LinkedList<InputInfo>();
	Queue<InputInfo> messageSaveR = new LinkedList<InputInfo>();
	Queue<InputInfo> loginMessageSaveW = new LinkedList<InputInfo>();
	Queue<InputInfo> loginMessageSaveR = new LinkedList<InputInfo>();
	InputInfo tempWR = new InputInfo();

	void snadMessageW(String franchise, String code, int quantity) { // �԰��û �޽����� ť�� �����ϴ� �Լ� ���� - �������
		InputInfo temp = new InputInfo();

		temp.setFranchise(franchise);
		temp.setCode(code);
		temp.setQuantity(quantity);

		messageSaveW.offer(temp);
	}
	
	void loginMessageSaveW(String franchise, String code, int quantity) { // �԰��û �޽����� ť�� �����ϴ� �Լ� ���� - �������
		InputInfo temp = new InputInfo();

		temp.setFranchise(franchise);
		temp.setCode(code);
		temp.setQuantity(quantity);

		loginMessageSaveW.offer(temp);
	}

	void printMessageW() { // �԰��û �޽��� ����ϴ� ����Ʈ �Լ� ���� - �������

		if (messageSaveW.size() >= 1) {
			int count = messageSaveW.size();
			for (int i = 0; i < count; i++) {
				InputInfo temp = new InputInfo();
				temp = messageSaveW.poll();
				System.out.println("-(�˸� " + temp.getFranchise() + ") �ڵ�:" + temp.getCode() + "��ǰ " + temp.getQuantity()
						+ "���� �԰� ��û�� �ֽ��ϴ�.-");
			}
		} else
			System.out.println("�԰� ��û �˸��޽����� �����ϴ�.");
	}

	void snadMessageR(String franchise, String code, int quantity) { // ��ǰ��û �޽��� ť�� �����ϴ� �Լ� ���� - �������
		InputInfo temp = new InputInfo();

		temp.setFranchise(franchise);
		temp.setCode(code);
		temp.setQuantity(quantity);

		messageSaveR.offer(temp);
	}

	void printMessageR() { // ��ǰ��û �޽��� ����ϴ� ����Ʈ �Լ� ���� - �������

		if (messageSaveR.size() >= 1) {
			int count = messageSaveR.size();
			for (int i = 0; i < count; i++) {
				InputInfo temp = new InputInfo();
				temp = messageSaveR.poll();
				System.out.println("-(�˸� " + temp.getFranchise() + ") �ڵ�:" + temp.getCode() + "��ǰ " + temp.getQuantity()
						+ "���� ��ǰ ��û�� �ֽ��ϴ�.-");
			}
		} else
			System.out.println("��ǰ ��û �˸��޽����� �����ϴ�.");
	}

	void loginMessageSaveR(String franchise, String code, int quantity) { // �԰��û �޽����� ť�� �����ϴ� �Լ� ���� - �������
		InputInfo temp = new InputInfo();

		temp.setFranchise(franchise);
		temp.setCode(code);
		temp.setQuantity(quantity);

		loginMessageSaveR.offer(temp);
	}
	
	void frameMakerWR() { // �������˾� â ���� �Լ� ���� - �������, ����ٴ�
		Runnable w = () -> {
			int count = this.loginMessageSaveW.size();
			if (count >= 1) {
				for (int i = 0; i < count; i++) {
					InputInfo temp2 = new InputInfo();
					temp2 = loginMessageSaveW.poll();
					String html = "<html><body width='%1s'><h2>" + temp2.getFranchise() + "���忡�� �޼����� �ֽ��ϴ�.</h2>"
							+ "<p>" + temp2.getCode() + " : " + temp2.getQuantity() + "�� �԰� ��û\n";
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
					String html = "<html><body width='%1s'><h2>" + temp2.getFranchise() + "���忡�� �޼����� �ֽ��ϴ�.</h2>"
							+ "<p>" + temp2.getCode() + " : " + temp2.getQuantity() + "�� ��ǰ ���Խ��ϴ�.\n";
					int width = 330; // change to alter the width
					JOptionPane.showMessageDialog(null, String.format(html, width, width));
				}
			}
		};
		SwingUtilities.invokeLater(r);
	}

}
