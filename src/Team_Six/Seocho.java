package Team_Six;

public class Seocho extends Store{// 클래스 설계 - 송종원님
	
	String id = "서초";

	@Override
	void storeInput(String seocho) { // 제품 등록 코드 메소드 오버라이딩 - 송종원님
		super.storeInput(seocho);
	}
	
	@Override
	void storeupdate(String seocho) { // 제품 갱신 코드 메소드 오버라이딩 - 송종원님
		super.storeupdate(seocho);
	}
	
	@Override
	void storeSearch(String seocho) { // 제품 검색 코드 메소드 오버라이딩 - 송종원님
		super.storeSearch(seocho);
	}
	
	@Override
	void storedelete(String seocho) { // 제품 삭제 코드 메소드 오버라이딩 - 송종원님
		super.storedelete(seocho);
	}

}
