HtmlAnalyzer.java�����������������������������������������������������������������������������������000666 �000000 �000000 �00000005267 14410172561 012520� 0����������������������������������������������������������������������������������������������������ustar�00����������������������������������������������������������������000000 �000000 ������������������������������������������������������������������������������������������������������������������������������������������������������������������������//bibliotecas do JDK utilizadas//
import java.io.IOException;
import java.net.URL;
import java.util.Scanner;


//classe que lê o a URL/HTML//
public class HtmlAnalyzer {

    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Uso: java HtmlAnalyzer <url>");
            //utilizado para passar a url desejada//
            //Dependendo do Site tem que colocar a Url entre ""//
            return;
        }

        String urlStr = args[0];
        String htmlContent = ConteudoUrl(urlStr);
        if (htmlContent == null) {
            System.out.println("Problemas para se conectar com a URL");
            //caso tenha algum problema com a url informa que está com problema para se conectar com a URL//
            return;
        }

        String textoProfundo = encontrarTextoProfundo(htmlContent);
        if (textoProfundo == null) {
            System.out.println(" Malformated HTML");
            //caso o site tenha algum HTML mal formatado informa malformated HTML//
            return;
        }

        System.out.println(textoProfundo);
        //retorna o texto/HTML mais profundo do site//
    }

    private static String ConteudoUrl(String urlStr) {
        try (Scanner scanner = new Scanner(new URL(urlStr).openStream(), "UTF-8")) {
            scanner.useDelimiter("\\A");
            return scanner.hasNext() ? scanner.next() : "";
        } catch (IOException e) {
            return null;
        }
    }

    private static String encontrarTextoProfundo(String htmlContent) {
        int nivelProfundo = -1;
        String textoProfundo = null;

        for (String linha : htmlContent.split("\\r?\\n")) {
            //loop entre as linhas do conteudo do HTML//
            //verifica se tem espaços ou se são linhas de comentarios//
            String semEspacos = linha.trim();
            int nivelAtual = linha.length() - semEspacos.length(); 

            if (semEspacos.isEmpty() || semEspacos.startsWith("<!")) {
                continue;
            }

            boolean tagDeFechamento = semEspacos.startsWith("</");
            boolean Texto = !semEspacos.startsWith("<");

            if (tagDeFechamento) {
                nivelAtual--;
            }

            if (Texto && nivelAtual >= nivelProfundo) {
                textoProfundo = semEspacos;
                nivelProfundo = nivelAtual;
            }

            if (!tagDeFechamento) {
                nivelAtual++;
            }

            if (nivelAtual < 0) {
                return null;
            }
        }

        return textoProfundo;
        //retorna o texto mais profundo//
    }
}���������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������
