package Team_Six;

public class InputInfo { // 클래스 설계 - 선영우

	private String franchise;

	private String code;

	private int quantity;

	InputInfo() {
		franchise = "";

		code = "";

		quantity = 0;
	}

	public void setFranchise(String franchise) {
		this.franchise = franchise;
	}

	public String getFranchise() {
		return this.franchise;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getCode() {
		return this.code;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public int getQuantity() {
		return this.quantity;
	}


}
