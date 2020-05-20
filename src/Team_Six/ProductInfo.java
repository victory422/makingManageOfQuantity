package Team_Six;

public class ProductInfo { // 클래스 설계 - 팀 공통
	
	String type = "";
	String design = "";
	String color = "";
	String size =  "";
	String quantity = "";
	
	String code = codefunc();
	
	String codefunc() {
		return this.code = this.type + this.design + this.color + this.size;
	}

}
