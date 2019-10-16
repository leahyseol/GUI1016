package application;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class PersonVO {
	private IntegerProperty no;
	private StringProperty name;
	private StringProperty email;
	private StringProperty tel;
	
	public PersonVO() {
		no=new SimpleIntegerProperty();
		name = new SimpleStringProperty();
		email = new SimpleStringProperty();
		tel = new SimpleStringProperty();
	}
	public PersonVO(int no, String name, String email, String tel) {
		this.no = new SimpleIntegerProperty(no);
		this.name = new SimpleStringProperty(name);
		this.email = new SimpleStringProperty(email);
		this.tel = new SimpleStringProperty(tel);
	}
	public int getNo() {
		return no.get();
	}
	public void setNo(int no) {
		this.no.set(no);
	}
	public String getName() {
		return name.get();
	}
	public void setName(String name) {
		this.name.set(name);
	}
	public String getEmail() {
		return email.get();
	}
	public void setEmail(String email) {
		this.email.set(email);
	}
	public String getTel() {
		return tel.get();
	}
	public void setTel(String tel) {
		this.tel.set(tel);
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("PersonVO [no=");
		builder.append(no.get());
		builder.append(", name=");
		builder.append(name.get());
		builder.append(", email=");
		builder.append(email.get());
		builder.append(", tel=");
		builder.append(tel.get());
		builder.append("]");
		return builder.toString();
	}
	
	
}
