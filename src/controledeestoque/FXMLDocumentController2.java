package controledeestoque;

import UTIL.ManipularImagem;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.channels.FileChannel;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.List;
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
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

public class FXMLDocumentController2 implements Initializable {

    @FXML
    private TextField txtNome;
    
    @FXML
    private TextField txtValor;

    @FXML
    private Button botaoSalvar;
    
    @FXML
    private Button selecaoImagem;

    @FXML
    private Button botaoCancelar;

    @FXML
    void btnCancelar(ActionEvent event) {
        Parent root;
        try {
            root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            Image icone = new Image(getClass().getResourceAsStream("/imagens/icon-clientes.png"));
            stage.getIcons().add(icone);
            stage.setTitle("Estoque");
            stage.setOnCloseRequest( e -> {
                Parent root2;
                try {
                    root2 = FXMLLoader.load(getClass().getResource("/controlegeral/FXMLDocument.fxml"));
                    Scene scene3 = new Scene(root2);
                    Stage stage3 = new Stage();
                    stage3.setResizable(false);
                    stage3.getIcons().add(icone);
                    stage3.setTitle("Oficinas de vendas");
                    stage3.setScene(scene3);
                    stage3.show();
               } catch (IOException ex) {
                    Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
               }
            });
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
         Stage stage = (Stage) botaoCancelar.getScene().getWindow(); 
         stage.close();
    }

    public void salvar() {
        if(txtNome.equals("") || txtValor.equals("")) {
            JOptionPane.showMessageDialog(null, "Complete todos os campos!");
        }
        
        else { 
            Produto produto = new Produto();
            produto.setNome(txtNome.getText().toString());

            NumberFormat nf = NumberFormat.getNumberInstance();
            try {
                double number = nf.parse(txtValor.getText().toString()).doubleValue();
                produto.setValor(number);
            } catch (ParseException ex) {
                Logger.getLogger(FXMLDocumentController2.class.getName()).log(Level.SEVERE, null, ex);
            }

            CarregarArquivo arquivo = new CarregarArquivo();
            arquivo.adicionarProdutoArquivo(produto);

            Stage stage = (Stage) botaoSalvar.getScene().getWindow();
            stage.close();

            Parent root;
            try {
                root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
                Scene scene = new Scene(root);
                Stage stage2 = new Stage();
                Image icone = new Image(getClass().getResourceAsStream("/imagens/icon-clientes.png"));
                stage2.getIcons().add(icone);
                stage2.setTitle("Estoque");
                stage.setOnCloseRequest( e -> {
                    Parent root2;
                    try {
                        root2 = FXMLLoader.load(getClass().getResource("/controlegeral/FXMLDocument.fxml"));
                        Scene scene3 = new Scene(root2);
                        Stage stage3 = new Stage();
                        stage3.setResizable(false);
                        stage3.getIcons().add(icone);
                        stage3.setTitle("Oficinas de vendas");
                        stage3.setScene(scene3);
                        stage3.show();
                   } catch (IOException ex) {
                        Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
                   }
                });
                stage2.setScene(scene);
                stage2.show();
            } catch (IOException ex) {
                Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    @FXML
    void btnSalvar(ActionEvent event) {
        salvar();
    }

    BufferedImage imagem;

    @FXML
    void selecionarImagem(ActionEvent event) {
       JFileChooser fc = new JFileChooser();
        int res = fc.showOpenDialog(null);

        if (res == JFileChooser.APPROVE_OPTION) {
            File arquivo = fc.getSelectedFile();
            
            try {
                imagem = ManipularImagem.setImagemDimensao(arquivo.getAbsolutePath(), 30, 30);
            } catch (Exception ex) {}
            selecaoImagem.setText("Imagem selecionada :)");
            selecaoImagem.setDisable(true);
            
            FileInputStream origem; 
            FileOutputStream destino;
            FileChannel fcOrigem;
            FileChannel fcDestino;

            try {
                 CarregarArquivo arquivoArray = new CarregarArquivo();
                 List<Produto> arrayProdutos = arquivoArray.getEstoque();
                 int numeroProdutos = arrayProdutos.size();

                 Path currentRelativePath = Paths.get("");
                 String s = currentRelativePath.toAbsolutePath().toString();

                 origem = new FileInputStream(arquivo.getPath());
                 destino = new FileOutputStream(s+"/src/imagensProdutos/" + String.valueOf(numeroProdutos) + ".jpg");
                 fcOrigem = origem.getChannel();
                 fcDestino = destino.getChannel();
                 try {
                     fcOrigem.transferTo(0, fcOrigem.size(), fcDestino);
                     origem.close();
                     destino.close();
                 } catch (IOException ex) {
                     Logger.getLogger(FXMLDocumentController2.class.getName()).log(Level.SEVERE, null, ex);
                 }
            } catch (FileNotFoundException ex) {
                Logger.getLogger(FXMLDocumentController2.class.getName()).log(Level.SEVERE, null, ex);
            }
        
            System.out.println(arquivo.getPath());
        } else {
            JOptionPane.showMessageDialog(null, "Voce nao selecionou nenhum arquivo.");
        }
    }
    
    
    @FXML
    void conferirEnter(KeyEvent event) {
       if (event.getCode() == KeyCode.ENTER || event.getCode() == KeyCode.TAB){
            txtValor.requestFocus();
       }
    }
    
    @FXML
    void conferirEnter2(KeyEvent event) {

    }


    @Override
    public void initialize(URL url, ResourceBundle rb) {
            
    }  

}
