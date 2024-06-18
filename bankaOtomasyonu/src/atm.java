import java.io.IOException;

 public class atm {

    public void main(String[] args) throws IOException {
        Seçenekler optionMenu = new Seçenekler();
        introduction();
        Seçenekler.mainMenu();
    }

    public static void introduction() {
        System.out.println("ATM PROJESİNE HOŞ GELDİSNZŞ!");
    }
}