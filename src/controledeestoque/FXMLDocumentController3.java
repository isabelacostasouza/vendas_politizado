package controledeestoque;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;


public class FXMLDocumentController3 implements Initializable {

    @FXML
    private Label nomeProduto;
    
    @FXML
    private Label valorProduto;

    @FXML
    private Button btnConcluido;

    @FXML
    void concluir(ActionEvent event) {
        Stage stage = (Stage) btnConcluido.getScene().getWindow();
        stage.close();
    }
  
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        FXMLDocumentController c = new FXMLDocumentController();
        Produto produto = c.getProdutoClicado();
        
        this.nomeProduto.setText(produto.getNome());
        this.valorProduto.setText(String.valueOf(produto.getValor()));
                
    }    

}
