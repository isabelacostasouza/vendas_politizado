package controledeestoque;

import java.io.BufferedReader;
import java.io.BufferedWriter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CarregarArquivo {
    
    private ArrayList<Produto> estoque = new ArrayList();
    private int quantidadeLinhas;
    
    public ArrayList<Produto> getEstoque() {
        setEstoque();
        for(int i = 0; i < this.estoque.size(); i++)
            this.estoque.get(i).setValorLiquido((this.estoque.get(i).getQuantidadeDesejada()*this.estoque.get(i).getValor()));
        return this.estoque;
    }
    
    public void removerProduto(Produto produto) {
        limparArquivo();
        for(int i = 0; i < this.estoque.size(); i++) {
            if(this.estoque.get(i).getNome().equals(produto.getNome()) && this.estoque.get(i).getQuantidade() == produto.getQuantidade())
                this.estoque.remove(i);
            else
                adicionarProdutoArquivo(this.estoque.get(i));
        }
    }
    
    public int tamanho() {
        return quantidadeLinhas;
    }
    
    private static ArrayList<Produto> estoqueAux;
    
    public void editarQuantidadeProduto(Produto produto) {
        limparArquivo();
        for(int i = 0; i < estoqueAux.size(); i++) {
            if(estoqueAux.get(i).getNome().equals(produto.getNome())) 
                estoqueAux.get(i).setQuantidade(estoqueAux.get(i).getQuantidade() - produto.getQuantidadeDesejada());
        }
        for(int i = 0; i < estoqueAux.size(); i++) {
            adicionarProdutoArquivo(estoqueAux.get(i));
        }
    }
    
    public void editarQuantidadeProduto(ArrayList<Produto> produto) {
        estoqueAux = this.getEstoque();
        limparArquivo();
        
        Path currentRelativePath = Paths.get("");
        String s = currentRelativePath.toAbsolutePath().toString();
        String caminhoArquivo = s+"\\estoque.txt";
        
        for(int i = 0; i < estoqueAux.size(); i++) {
            boolean auxiliar = false;
            for(int j = 0; j < produto.size(); j++) {
                if(estoqueAux.get(i).getNome().equals(produto.get(j).getNome()) && auxiliar == false) {
                    escreverArquivo(produto.get(j).getNome(), caminhoArquivo);
                    escreverArquivo(String.valueOf(produto.get(j).getValor()), caminhoArquivo);
                    escreverArquivo(String.valueOf(produto.get(j).getQuantidade() - produto.get(j).getQuantidadeDesejada()), caminhoArquivo);
                    escreverArquivo(String.valueOf(0), caminhoArquivo);
                    escreverArquivo(String.valueOf(0.0), caminhoArquivo);
                    escreverArquivo("", caminhoArquivo);
                    auxiliar = true;
                }
            }
            if(auxiliar == false)
                adicionarProdutoArquivo(estoqueAux.get(i));
        }
    }
    
    public ArrayList<Produto> procurarProduto(String procurado) {
        ArrayList<Produto> produtoProcurado = new ArrayList();
        setEstoque();
        
        for(int i = 0; i < this.estoque.size(); i++) {
            if(this.estoque.get(i).getNome().toLowerCase().indexOf(procurado.toLowerCase()) >= 0)
                produtoProcurado.add(this.estoque.get(i));
        }
        
        return produtoProcurado;
    }
    
    public void alterarArquivo() {
        limparArquivo();
        for(int i = 0; i < this.estoque.size(); i++)
            adicionarProdutoArquivo(estoque.get(i));
    }
    
    public void setEstoque() {
        Path currentRelativePath = Paths.get("");
        String s = currentRelativePath.toAbsolutePath().toString();
        String caminhoArquivo = s+"\\estoque.txt";
        
        FileReader f;
        quantidadeLinhas = 0;
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
                int contador = 0;

                for(int i = 0; i < linhas.size(); i++) {
                    Produto e = new Produto();
                    if(contador > 5) 
                        contador = 0;
                    else if(contador == 1) {
                        e.setNome(linhas.get(i-1));
                        e.setValor(Double.parseDouble(linhas.get(i)));
                        e.setQuantidade(Integer.parseInt(linhas.get(i+1)));
                        if(linhas.get(i+2).equals("null")){}
                        else
                            e.setQuantidadeDesejada(Integer.parseInt(linhas.get(i+2)));
                        if(linhas.get(i+3).equals("null")){}
                        else
                            e.setValorLiquido(Double.parseDouble(linhas.get(i+3)));
                        this.estoque.add(e);
                        quantidadeLinhas++;
                    }
                    contador++;
                }
                readerf.close();
            } catch (IOException ex) {
                Logger.getLogger(CarregarArquivo.class.getName()).log(Level.SEVERE, null, ex);
            }

        } catch (FileNotFoundException ex) {
            Logger.getLogger(CarregarArquivo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void adicionarProdutoArquivo(Produto produto) {
        Path currentRelativePath = Paths.get("");
        String s = currentRelativePath.toAbsolutePath().toString();
        String caminhoArquivo = s+"\\estoque.txt";

        escreverArquivo(produto.getNome(), caminhoArquivo);
        escreverArquivo(String.valueOf(produto.getValor()), caminhoArquivo);
        escreverArquivo(String.valueOf(produto.getQuantidade()), caminhoArquivo);
        escreverArquivo(String.valueOf(produto.getQuantidadeDesejada()), caminhoArquivo);
        escreverArquivo(String.valueOf(produto.getValorLiquido()), caminhoArquivo);
        escreverArquivo("", caminhoArquivo);
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
    
    public void limparArquivo(){
        Path currentRelativePath = Paths.get("");
        String s = currentRelativePath.toAbsolutePath().toString();
        String caminhoArquivo = s+"\\estoque.txt";
        
        File file = new File(caminhoArquivo);
        file.delete();
        File f = new File(caminhoArquivo);
        try {
            f.createNewFile();
        } catch (IOException ex) {
            Logger.getLogger(CarregarArquivo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
