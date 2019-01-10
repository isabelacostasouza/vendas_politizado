package controledeestoque;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class FXMLDocumentController implements Initializable {
    
    @FXML
    private TextField txtPesquisa;

    @FXML
    private Button btnAddProduto;

    @FXML
    private TableView<Produto> tableEstoque;

    @FXML
    private TableColumn<Produto, String> tableColumnProduto;

    @FXML
    private TableColumn<Produto, String> tableColumnValor;
    
    @FXML
    private TableColumn<Produto, Image> colunaImagem;
    
    @FXML
    public void botaoAddProduto(ActionEvent event) {
       Parent root;
       try {
           root = FXMLLoader.load(getClass().getResource("FXMLDocument2.fxml"));
           Scene scene = new Scene(root);
           Stage stage = new Stage();
           stage.setResizable(false);
           Image icone = new Image(getClass().getResourceAsStream("/imagens/icon-clientes.png"));
           stage.getIcons().add(icone);
           stage.setTitle("Adicionar produto");
           stage.setScene(scene);
           stage.show();
           stage.setOnCloseRequest( e -> {
                Parent root2;
                try {
                     root2 = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
                     Scene scene2 = new Scene(root2);
                     Stage stage2 = new Stage();
                     stage2.getIcons().add(icone);
                     stage2.setTitle("Estoque");
                     stage2.setOnCloseRequest( f -> {
                        Parent root3;
                        try {
                             root3 = FXMLLoader.load(getClass().getResource("/controlegeral/FXMLDocument.fxml"));
                             Scene scene3 = new Scene(root3);
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
                     stage2.setScene(scene2);
                     stage2.show();
                } catch (IOException ex) {
                     Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
                }
           });

           Stage stage2 = (Stage) tableEstoque.getScene().getWindow();
           stage2.close();
       } catch (IOException ex) {
           Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
       }
    }
    
    @FXML
    void botaoVoltar(ActionEvent event) {
       Parent root;
       try {
           FXMLLoader loader = new FXMLLoader();
           loader.setLocation(getClass().getResource("/controledelogin/FXMLDocument2.fxml"));
           root = loader.load();
           Scene scene = new Scene(root);
           Stage stage = new Stage();
           stage.setResizable(false);
           Image icone = new Image(getClass().getResourceAsStream("/imagens/icon-clientes.png"));
           stage.getIcons().add(icone);
           stage.setTitle("Oficinas de vendas");
           stage.setScene(scene);
           stage.show();
           Stage stage2 = (Stage) tableEstoque.getScene().getWindow();
           stage2.close();
       } catch (IOException ex) {
           Logger.getLogger(controlegeral.FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
       }
    }
    
    @FXML
    void botaoVoltar2(MouseEvent event) {
       Parent root;
       try {
           FXMLLoader loader = new FXMLLoader();
           loader.setLocation(getClass().getResource("/controledelogin/FXMLDocument2.fxml"));
           root = loader.load();
           Scene scene = new Scene(root);
           Stage stage = new Stage();
           stage.setResizable(false);
           Image icone = new Image(getClass().getResourceAsStream("/imagens/icon-clientes.png"));
           stage.getIcons().add(icone);
           stage.setTitle("Oficinas de vendas");
           stage.setScene(scene);
           stage.show();
           Stage stage2 = (Stage) tableEstoque.getScene().getWindow();
           stage2.close();
       } catch (IOException ex) {
           Logger.getLogger(controlegeral.FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
       }
    }
    
    @FXML
    public void botaoExcluirProduto(ActionEvent event) {
       CarregarArquivo arquivo = new CarregarArquivo();
       Produto produto = arquivo.procurarProduto(tableEstoque.getSelectionModel().getSelectedItem().getNome()).get(0);
       arquivo.removerProduto(produto);
       
       tableEstoque.getItems().removeAll(tableEstoque.getSelectionModel().getSelectedItem());
    }  

    @FXML
    void botaoEditarProduto(ActionEvent event) {
        CarregarArquivo arquivo = new CarregarArquivo();
        Produto produto = arquivo.procurarProduto(tableEstoque.getSelectionModel().getSelectedItem().getNome()).get(0);
        setProdutoClicado(produto);
        Parent root;
        try {
            root = FXMLLoader.load(getClass().getResource("FXMLDocument4.fxml"));
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setResizable(false);
            Image icone = new Image(getClass().getResourceAsStream("/imagens/icon-clientes.png"));
            stage.getIcons().add(icone);
            stage.setTitle("Editar informações");
            stage.setScene(scene);
            stage.setOnCloseRequest( e -> {
                Parent root2;
                try {
                     root2 = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
                     Scene scene2 = new Scene(root2);
                     Stage stage2 = new Stage();
                     stage2.getIcons().add(icone);
                     stage2.setTitle("Estoque");
                     stage2.setOnCloseRequest( f -> {
                        Parent root3;
                        try {
                             root3 = FXMLLoader.load(getClass().getResource("/controlegeral/FXMLDocument.fxml"));
                             Scene scene3 = new Scene(root3);
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
                     stage2.setScene(scene2);
                     stage2.show();
                } catch (IOException ex) {
                     Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
                }
           });
            stage.show();
            Stage stage2 = (Stage) tableEstoque.getScene().getWindow();
            stage2.close();
        } catch (IOException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private List<Produto> arrayProdutos = new ArrayList();
    private ObservableList<Produto> observableListProdutos;
    private static Produto produtoClicado;

    public void carregarProdutos() {
        tableColumnProduto.setCellValueFactory(new PropertyValueFactory<>("nome"));
        tableColumnValor.setCellValueFactory(new PropertyValueFactory<>("valor"));
        colunaImagem.setCellValueFactory(new PropertyValueFactory<>("imagem"));
        
        CarregarArquivo arquivo = new CarregarArquivo();
        arrayProdutos = arquivo.getEstoque();    
        for(int i = 0; i < arrayProdutos.size(); i++) {
            Image url = new Image("/imagensProdutos/" + String.valueOf(i) + ".jpg");
            arrayProdutos.get(i).setImagem(url);            
        }
        tableEstoque.getItems().clear();
        observableListProdutos = FXCollections.observableArrayList(arrayProdutos);
        tableEstoque.setItems(observableListProdutos);
    }

    public void pesquisar() {
        CarregarArquivo arquivo = new CarregarArquivo();
        observableListProdutos = FXCollections.observableArrayList(arquivo.procurarProduto(txtPesquisa.getText().toString()));
        tableEstoque.setItems(observableListProdutos);
    }      
    
    public Produto getProdutoClicado(){
        return this.produtoClicado;
    } 
    
    public void setProdutoClicado(Produto produto){
        this.produtoClicado = produto;
    } 
 
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        carregarProdutos();
        tableEstoque.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);   
        tableEstoque.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if(event.getButton().equals(MouseButton.PRIMARY)){
                    if(event.getClickCount() == 2){
                        CarregarArquivo arquivo = new CarregarArquivo();
                        Produto produto = arquivo.procurarProduto(tableEstoque.getSelectionModel().getSelectedItem().getNome()).get(0);
                        setProdutoClicado(produto);
                        
                        Parent root;
                        try {
                            root = FXMLLoader.load(getClass().getResource("FXMLDocument3.fxml"));
                            Scene scene = new Scene(root);
                            Stage stage = new Stage();
                            stage.setScene(scene);
                            Image icone = new Image(getClass().getResourceAsStream("/imagens/icon-clientes.png"));
                            stage.getIcons().add(icone);
                            stage.setTitle("Painel do produto");
                            stage.setResizable(false);
                            stage.show();
                        } catch (IOException ex) {
                            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }
            }
        });
        
        tableEstoque.setPlaceholder(new Label("Não há produtos cadastrados"));
        
        txtPesquisa.setOnKeyReleased(
        e -> {
            pesquisar();	
        });
        
        txtPesquisa.setOnKeyTyped(e -> {
            pesquisar();	
        });
        
        txtPesquisa.setOnKeyPressed(e -> {
            pesquisar();	
        });

    }
}
