package Team_Six;

public class ProductInfo { // Ŭ���� ���� - �� ����
	
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
