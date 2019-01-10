package menu;

import controledeestoque.CarregarArquivo;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

public class FXMLDocumentController {

    @FXML
    private AnchorPane tableColumnNome;

    @FXML
    private TextField user;

    @FXML
    private PasswordField password;

    @FXML
    void enviar(ActionEvent event) { 
        enviar();
    }

    public void enviar() {
        if(user.getText().equals("") || password.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Informe todos os campos!");
        }
        else {
            Path currentRelativePath = Paths.get("");
            String s = currentRelativePath.toAbsolutePath().toString();
            String caminhoArquivo = s+"\\masterLogin.txt";
            
            File file = new File(caminhoArquivo);
            try {
                file.createNewFile();
            } catch (IOException ex) {
                Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            escreverArquivo(user.getText(), caminhoArquivo);
            escreverArquivo(password.getText(), caminhoArquivo);
            
            JOptionPane.showMessageDialog(null, "Usuário cadastrado com sucesso!");
            
            Parent root;
            try {
                root = FXMLLoader.load(getClass().getResource("/controlegeral/FXMLDocument.fxml"));
                Scene scene = new Scene(root);
                Stage stage = new Stage();
                Image icone = new Image(getClass().getResourceAsStream("/imagens/icon-clientes.png"));
                stage.getIcons().add(icone);
                stage.setTitle("Oficina de vendas");
                stage.setScene(scene);
                stage.show();

                Stage stage3 = (Stage) tableColumnNome.getScene().getWindow();
                stage3.close();
            } catch (IOException ex) {
                Logger.getLogger(controlegeral.FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    int contador = 0;
    
    @FXML
    void conferirEnter(KeyEvent event) {
       if (event.getCode() == KeyCode.ENTER){
           if(contador > 0)
                enviar();
           contador++;
         }
    }
    
    @FXML
    void conferirEnter2(KeyEvent event) {
       if (event.getCode() == KeyCode.ENTER || event.getCode() == KeyCode.TAB){
            password.requestFocus();
         }
    }
    
    public void escreverArquivo(String linha, String caminhoArquivo) {
        FileWriter fw;
        try {
            fw = new FileWriter(caminhoArquivo, true);
            BufferedWriter conexao = new BufferedWriter(fw);
            conexao.write(linha);
            conexao.newLine();
            conexao.close();
        } catch (IOException ex) {
            Logger.getLogger(CarregarArquivo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
