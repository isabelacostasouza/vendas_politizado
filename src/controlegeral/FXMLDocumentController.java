package controlegeral;

import controledeestoque.CarregarArquivo;
import controledevendas.CarregarVenda;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class FXMLDocumentController implements Initializable {
    
    @FXML
    private Label estoque;
    
    @FXML
    private Button vendasButton;
    
    @FXML
    private Button modoDireita;

    @FXML
    private ImageView switchButton;

    @FXML
    private CheckBox modoPolitico;
   
    @FXML
    private ImageView imagemPrincipal;

    @FXML
    private AnchorPane tableColumnNome;
    
    public int toggleClicks = 0;

    @FXML
    void getLogin(ActionEvent event) {
       Parent root;
       try {
           root = FXMLLoader.load(getClass().getResource("/controledelogin/FXMLDocument.fxml"));
           Scene scene = new Scene(root);
           Stage stage = new Stage();
           Image icone = new Image(getClass().getResourceAsStream("/imagens/icon-clientes.png"));
           stage.getIcons().add(icone);
           stage.setTitle("Login");
           stage.setScene(scene);
           stage.show();
           stage.setOnCloseRequest( e -> {
                Parent root2;
                try {
                     root2 = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
                     Scene scene2 = new Scene(root2);
                     Stage stage2 = new Stage();
                     stage2.setResizable(false);
                     Image icone2 = new Image(getClass().getResourceAsStream("/imagens/icon-clientes.png"));
                     stage2.getIcons().add(icone2);
                     stage2.setTitle("Oficina de vendas");
                     stage2.setScene(scene2);
                     stage2.show();
                } catch (IOException ex) {
                     Logger.getLogger(controlegeral.FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
                }
           });
           Stage stage3 = (Stage) tableColumnNome.getScene().getWindow();
           stage3.close();
       } catch (IOException ex) {
           Logger.getLogger(controlegeral.FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
       }
    }
    
    @FXML
    public void mudancaModo(javafx.scene.input.MouseEvent event){
        if(toggleClicks%2 == 0) {
            Image on = new Image(getClass().getResourceAsStream("/imagens/on.png"));
            switchButton.setImage(on);
            modoDireita();
        }
        else {
            Image off = new Image(getClass().getResourceAsStream("/imagens/off.png"));
            switchButton.setImage(off);
            modoEsquerda();
        }
        toggleClicks++;
    }
    
    public void modoNormal() {
        Image logoTradicional = new Image(getClass().getResourceAsStream("/imagens/venda.png"));
        imagemPrincipal.setImage(logoTradicional);
        vendasButton.setStyle("-fx-background-color: #cfcfcf; -fx-text-fill: black;");
    }
    
    public void modoDireita() {
        Image bolsonadoLogo = new Image(getClass().getResourceAsStream("/imagens/ModoDireita/rango-logo.png"));
        imagemPrincipal.setImage(bolsonadoLogo);
        vendasButton.setStyle("-fx-background-color: yellow; -fx-text-fill: black;");
    }
    
    public void modoEsquerda() {
        Image lulaLogo = new Image(getClass().getResourceAsStream("/imagens/ModoEsquerda/roubo-logo.png"));
        imagemPrincipal.setImage(lulaLogo);
        vendasButton.setStyle("-fx-background-color: #ff1a1a; -fx-text-fill: #cfcfcf;");
    }
    
    @FXML
    void iniciarVenda(ActionEvent event) {
        CarregarArquivo arquivoEstoque = new CarregarArquivo();
        CarregarVenda arquivoVenda = new CarregarVenda();
        if(arquivoVenda.getEstoque() != null) {
            arquivoEstoque.editarQuantidadeProduto(arquivoVenda.getEstoque());
            arquivoVenda.limparArquivo();
        }
       Parent root;
       try {
           root = FXMLLoader.load(getClass().getResource("/controledevendas/FXMLDocument.fxml"));
           Scene scene = new Scene(root);
           Stage stage = new Stage();
           Image icone = new Image(getClass().getResourceAsStream("/imagens/icon-clientes.png"));
           stage.getIcons().add(icone);
           stage.setTitle("Venda");
           stage.setScene(scene);
           stage.show();
           stage.setOnCloseRequest( e -> {
                Parent root2;
                try {
                     root2 = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
                     Scene scene2 = new Scene(root2);
                     Stage stage2 = new Stage();
                     stage2.setResizable(false);
                     Image icone2 = new Image(getClass().getResourceAsStream("/imagens/icon-clientes.png"));
                     stage2.getIcons().add(icone2);
                     stage2.setTitle("Oficina de vendas");
                     stage2.setScene(scene2);
                     stage2.show();
                } catch (IOException ex) {
                     Logger.getLogger(controlegeral.FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
                }
           });
           stage.show();
           Stage stage3 = (Stage) tableColumnNome.getScene().getWindow();
           stage3.close();
       } catch (IOException ex) {
           Logger.getLogger(controlegeral.FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
       }
    }
    
   @FXML
   public void ativarModoPolitico(ActionEvent event) {
        if(modoPolitico.isSelected() ){
            modoDireita.setVisible(true);
            switchButton.setVisible(true); 
            modoEsquerda();
        } else{
            modoDireita.setVisible(false);
            switchButton.setVisible(false);
            modoNormal();
        }
   }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        modoDireita.setVisible(false);
        switchButton.setVisible(false);
    }
}
