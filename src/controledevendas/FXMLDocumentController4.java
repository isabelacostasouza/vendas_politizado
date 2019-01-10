package controledevendas;

import controledeestoque.CarregarArquivo;
import controledeestoque.Produto;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.scene.control.cell.PropertyValueFactory;

public class FXMLDocumentController4 implements Initializable {
    
    @FXML
    private TableView<Produto> tabela;

    @FXML
    private TableColumn<Produto, String> colunaProduto;

    @FXML
    private TableColumn<Produto, String> colunaQuantidade;

    @FXML
    private TableColumn<Produto, String> colunaValorUnitario;

    @FXML
    private TableColumn<Produto, String> colunaTotal;

    @FXML
    private Label numeroSenha;
    
    @FXML
    private Label lableValorTotal;
    
    @FXML
    public void concluir(ActionEvent event) {
        Parent root;
        try {
            root = FXMLLoader.load(getClass().getResource("/controlegeral/FXMLDocument.fxml"));
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setResizable(false);
            Image icone = new Image(getClass().getResourceAsStream("/imagens/icon-clientes.png"));
            stage.getIcons().add(icone);
            stage.setTitle("Oficina de vendas");
             
            Stage stage2 = (Stage) tabela.getScene().getWindow();
            stage2.close();
            
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
             Logger.getLogger(controledeestoque.FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
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
    
    private static ArrayList<Produto> arrayProdutos = new ArrayList();
    private ObservableList<Produto> observableListProdutos;
    private double valorTotal;

    public void carregarProdutos() {
        if(arrayProdutos != null) {
            for(int i = 0; i < arrayProdutos.size(); i++) {
                arrayProdutos.get(i).setValorLiquido((arrayProdutos.get(i).getQuantidadeDesejada()*arrayProdutos.get(i).getValor()));
                this.valorTotal = this.valorTotal + arrayProdutos.get(i).getValorLiquido();
            }
            
            colunaProduto.setCellValueFactory(new PropertyValueFactory<>("nome"));
            colunaValorUnitario.setCellValueFactory(new PropertyValueFactory<>("valor"));
            colunaQuantidade.setCellValueFactory(new PropertyValueFactory<>("quantidadeDesejada"));
            colunaTotal.setCellValueFactory(new PropertyValueFactory<>("valorLiquido"));

            tabela.getItems().clear();
            observableListProdutos = FXCollections.observableArrayList(arrayProdutos);
            tabela.setItems(observableListProdutos);
        }    
    }
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        tabela.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        
        CarregarVenda arquivo = new CarregarVenda();
        arrayProdutos = arquivo.getEstoque(); 
        carregarProdutos();

        this.lableValorTotal.setText("Valor total: R$" + String.valueOf(this.valorTotal));
        
        int senha = 0;
        
        Path currentRelativePath = Paths.get("");
        String s = currentRelativePath.toAbsolutePath().toString();
        String caminhoArquivo = s+"\\senha.txt";
        
        FileReader f;
        try {
            f = new FileReader(caminhoArquivo);
            BufferedReader readerf = new BufferedReader(f);
            String linha;
            try {
                linha = readerf.readLine();
                ArrayList<String> linhas = new ArrayList();
                while (linha != null) {
                    linhas.add(linha);
                    linha = readerf.readLine();
                }    
                
                senha = Integer.valueOf(linhas.get(linhas.size()-1));
                senha++;
                
                this.numeroSenha.setText(String.valueOf(senha));
                
                if(linhas.size() == 1) {
                    escreverArquivo("", caminhoArquivo);
                    escreverArquivo(String.valueOf(senha), caminhoArquivo);
                }
                else {
                    escreverArquivo(String.valueOf(senha), caminhoArquivo);
                }
                
                readerf.close();
            } catch (IOException ex) {
                Logger.getLogger(CarregarArquivo.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(CarregarArquivo.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

}
