package Team_Six;

public class Gangnam extends Store {// 클래스 설계 - 송종원

	String id = "강남";

	@Override
	void storeInput(String gangnam) { // 제품 등록 코드 메소드 오버라이딩 - 송종원님
		super.storeInput(gangnam);
	}
	
	@Override
	void storeupdate(String gangnam) { // 제품 갱신 코드 메소드 오버라이딩 - 송종원님
		super.storeupdate(gangnam);
	}
	
	@Override
	void storeSearch(String gangnam) { // 제품 검색 코드 메소드 오버라이딩 - 송종원님
		super.storeSearch(gangnam);
	}

	@Override
	void storedelete(String store) {// 제품 삭제 코드 메소드 오버라이딩 - 송종원님
		super.storedelete(store);
	}

}
