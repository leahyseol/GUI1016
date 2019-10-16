package application;

import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import javafx.beans.property.Property;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;

public class SampleController implements Initializable {

	@FXML
	private TextField tfNo, tfName, tfEmail,tfTel;
	@FXML
	private Button btnTotal, btnAdd, btnDel;
	@FXML
	private TableView<PersonVO> tableView;
	
	@FXML
	private TableColumn tcNo, tcName, tcEmail, tcTel;
	
	private PersonDAO personDAO = new PersonDAO();
	
	//Initialize()는 @FXML로 객체의 주입이 다끝나면 자동호출 됨.
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		tcNo.setCellValueFactory(new PropertyValueFactory("no"));
		tcName.setCellValueFactory(new PropertyValueFactory("name")); 
		tcEmail.setCellValueFactory(new PropertyValueFactory("email"));
		tcTel.setCellValueFactory(new PropertyValueFactory("tel"));
		
		// 버튼이벤트 연결
		btnTotal.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				total();
			}
		});
		 //람다표현식 {} 말고 () 사용 가능
		
		btnAdd.setOnAction(event -> add());
		btnDel.setOnAction(event -> del());
		
	}//initialize method
	
	
	public void total() {
		// DB에서 전체 person 데이터를 List로 가져오기
		List<PersonVO> personList = personDAO.getPersons();
		// List를 
		ObservableList<PersonVO> obsPersonList = FXCollections.observableArrayList(personList);
		// ObservableList를 TableView에 설정
		tableView.setItems(obsPersonList);
	} // total method
	
		

	private void add() {
		//텍스트상자 데이터 가져오기
		String strNo = tfNo.getText().trim();
		String name = tfName.getText().trim();
		String email = tfEmail.getText().trim();
		String tel = tfTel.getText().trim();
		
		//입력값 검증. 글번호 또는 이름이 비어있으면 입력제어
		if(strNo.length() == 0 || name.length()==0) {
			Alert alert = new Alert(AlertType.WARNING, "번호와 이름은 필수 입력사항입니다. ", ButtonType.CLOSE);
			alert.showAndWait(); //alert.show();
			return;
		}
		
		
		
		//insert 
		int no = Integer.parseInt(strNo);
		PersonVO personVO=new PersonVO(no, name, email, tel);
		int count = personDAO.insert(personVO);
		if(count > 0 ) {
			Alert alert = new Alert(AlertType.INFORMATION, "회원가입 성공 ", ButtonType.CLOSE);
			alert.showAndWait(); //alert.show();
		}else {
			Alert alert = new Alert(AlertType.ERROR, "회원가입 실패 ", ButtonType.CLOSE);
			alert.showAndWait(); //alert.show();
		}
		
		//텍스트상자 비우기
		tfNo.setText("");
		tfName.setText("");
		tfEmail.setText("");
		tfTel.setText("");
		
		tfNo.requestFocus();
	}//add method
	
	public void del() {
//		int selectedIndex = tableView.getSelectionModel().getSelectedIndex();
		PersonVO personVO = tableView.getSelectionModel().getSelectedItem();
		//선택안하고 선택아이템 구하면 null return
		if(personVO == null) {
			return;
		}
		
		//삭제확인 대화상자 띄우기
		Alert confirmDialog = new Alert(AlertType.CONFIRMATION);
		confirmDialog.setHeaderText(personVO.getNo() + "번" + personVO.getName() + "님의 회원정보를 삭제합니다.");
		confirmDialog.setContentText("한번삭제하면 되돌릴 수 없습니다 \n정말 삭제하시겠습니까?");
		
		Optional<ButtonType> result = confirmDialog.showAndWait();
		if(result.get() == ButtonType.CANCEL) { //삭제취소
			return;
		}
		
		//삭제수행
		int count =personDAO.deleteByNo(personVO.getNo());
		if(count >0) {
			Alert alert = new Alert(AlertType.INFORMATION,"회원삭제 성공", ButtonType.CLOSE);
			alert.showAndWait();
		}else {
			Alert alert = new Alert(AlertType.INFORMATION,"회원삭제 실패", ButtonType.CLOSE);
			alert.showAndWait();
		}
		
	}//del method
	
}
