package menu;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class main extends Application {
    
    public boolean arquivoExistente() {
        Path currentRelativePath = Paths.get("");
        String s = currentRelativePath.toAbsolutePath().toString();
        String caminhoArquivo = s+"\\masterLogin.txt";
        File file = new File(caminhoArquivo);
        if(file.exists())
            return true;
        return false;            
    }
    
    @Override
    public void start(Stage stage) throws Exception {
        
        if(arquivoExistente()) {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/controlegeral/FXMLDocument.fxml"));
            Parent root = loader.load();

            Scene scene = new Scene(root);

            Image icone = new Image(getClass().getResourceAsStream("/imagens/icon-clientes.png"));
            stage.getIcons().add(icone);
            stage.setTitle("Oficinas de vendas");
            stage.setResizable(false);
            stage.setScene(scene);
            stage.show();  
        }
        
        else {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("FXMLDocument.fxml"));
            Parent root = loader.load();

            Scene scene = new Scene(root);

            Image icone = new Image(getClass().getResourceAsStream("/imagens/icon-clientes.png"));
            stage.getIcons().add(icone);
            stage.setTitle("Oficinas de vendas");
            stage.setResizable(false);
            stage.setScene(scene);
            stage.show();   
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
    
}
