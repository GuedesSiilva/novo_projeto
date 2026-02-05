import java.util.Scanner;

public class Leitura {
    public static String dados(String mensagem){
        Scanner scan = new Scanner(System.in);
        System.out.println(mensagem);
        return scan.nextLine();
    }
}
