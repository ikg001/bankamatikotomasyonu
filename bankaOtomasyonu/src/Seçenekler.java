import java.io.IOException;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;

public class Seçenekler {
    Scanner menuInput = new Scanner(System.in);
    DecimalFormat moneyFormat = new DecimalFormat("'$'###,##0.00");
    HashMap<Integer, Hesap> data = new HashMap<Integer, Hesap>();

    public void getLogin() throws IOException {
        boolean end = false;
        int customerNumber = 0;
        int pinNumber = 0;
        while (!end) {
            try {
                System.out.print("\nMüşteri Numaranızı Giriniz : ");
                customerNumber = menuInput.nextInt();
                System.out.print("\nŞifrenizi Giriniz : ");
                pinNumber = menuInput.nextInt();
                Iterator it = data.entrySet().iterator();
                while (it.hasNext()) {
                    Map.Entry pair = (Map.Entry) it.next();
                    Hesap acc = (Hesap) pair.getValue();
                    if (data.containsKey(customerNumber) && pinNumber == acc.getPinNumber()) {
                        getHesapType(acc);
                        end = true;
                        break;
                    }
                }
                if (!end) {
                    System.out.println("\nYanlış şire ");
                }
            } catch (InputMismatchException e) {
                System.out.println("\nSadece numerik ifadeler girebilirsiniz");
            }
        }
    }

    public void getHesapType(Hesap acc) {
        boolean end = false;
        while (!end) {
            try {
                System.out.println("\n Erişmek İstediğiniz hesabın çeşidini giriniz ");
                System.out.println(" Tip 1 - Vadesiz Hesap");
                System.out.println(" Tip 2 - Birikim Hesabı");
                System.out.println(" Çıkış");
                System.out.print("\nChoice: ");

                int selection = menuInput.nextInt();

                switch (selection) {
                    case 1:
                        getChecking(acc);
                        break;
                    case 2:
                        getSaving(acc);
                        break;
                    case 3:
                        end = true;
                        break;
                    default:
                        System.out.println("\nGeçersiz seçim ");
                }
            } catch (InputMismatchException e) {
                System.out.println("\n Geçersiz seçim");
                menuInput.next();
            }
        }
    }

    public void getChecking(Hesap acc) {
        boolean end = false;
        while (!end) {
            try {
                System.out.println("\nVadesiz hesap ");
                System.out.println("Seçenek  1 - Bakiyeyi görüntüle");
                System.out.println("Seçenek  2 - Para çekme");
                System.out.println("Seçenek  3 - Para yatıram");
                System.out.println("Seçenek  4 - Para transferi");
                System.out.println("Seçenek  5 - Çıkış");
                System.out.print("\nSeçim :  ");

                int selection = menuInput.nextInt();

                switch (selection) {
                    case 1:
                        System.out.println("\n Vadesiz hesap bakiyesi: " + moneyFormat.format(acc.getCheckingBalance()));
                        break;
                    case 2:
                        acc.getCheckingWithdrawInput();
                        break;
                    case 3:
                        acc.getCheckingDepositInput();
                        break;

                    case 4:
                        acc.getTransferInput("para değeri :");
                        break;
                    case 5:
                        end = true;
                        break;
                    default:
                        System.out.println("\n geçersiz seçim");
                }
            } catch (InputMismatchException e) {
                System.out.println("\nGeçersiz seçim");
                menuInput.next();
            }
        }
    }

    public void getSaving(Hesap acc) {
        boolean end = false;
        while (!end) {
            try {
                System.out.println("\n Birikim Hesabı ");
                System.out.println(" Type 1 - Bakşyeyi görüntüle");
                System.out.println(" Type 2 - Para çek");
                System.out.println(" Type 3 - Para yatır");
                System.out.println(" Type 4 - Para transferi ");
                System.out.println(" Type 5 - Çıkış");
                System.out.print("seçim : ");
                int selection = menuInput.nextInt();
                switch (selection) {
                    case 1:
                        System.out.println("\nBirikim hesabı bakiyesi: " + moneyFormat.format(acc.getSavingBalance()));
                        break;
                    case 2:
                        acc.getsavingWithdrawInput();
                        break;
                    case 3:
                        acc.getSavingDepositInput();
                        break;
                    case 4:
                        acc.getTransferInput("BİRİKİM DEĞERİ");
                        break;
                    case 5:
                        end = true;
                        break;
                    default:
                        System.out.println("\n Geçersiz Seçim");
                }
            } catch (InputMismatchException e) {
                System.out.println("\nGeçersiz Hesap");
                menuInput.next();
            }
        }
    }

    public void createAccount() throws IOException {
        int cst_no = 0;
        boolean end = false;
        while (!end) {
            try {
                System.out.println("\nMüstei numaranızı giriniz ");
                cst_no = menuInput.nextInt();
                Iterator it = data.entrySet().iterator();
                while (it.hasNext()) {
                    Map.Entry pair = (Map.Entry) it.next();
                    if (!data.containsKey(cst_no)) {
                        end = true;
                    }
                }
                if (!end) {
                    System.out.println("\nBu müşteri numarası zaten kayıtlı");
                }
            } catch (InputMismatchException e) {
                System.out.println("\nGeçersiz seçim");
                menuInput.next();
            }
        }
        System.out.println("\nKaydedilecek şifreyi lütfen giriniz");
        int pin = menuInput.nextInt();
        data.put(cst_no, new Hesap(cst_no, pin));
        System.out.println("\nyeni hesabınız başarıyla kaydedildi");
        System.out.println("\ngiriş ekranına yönlendiriliyorsunuz");
        getLogin();
    }

    public void mainMenu() throws IOException {
        data.put(952141, new Hesap(952141, 191904, 1000, 5000));
        data.put(123, new Hesap(123, 123, 20000, 50000));
        boolean end = false;
        while (!end) {
            try {
                System.out.println("\n seçenek 1  - giriş");
                System.out.println(" seçenek 2  - Create Hesap oluştur");
                System.out.print("\nseçim : ");
                int choice = menuInput.nextInt();
                switch (choice) {
                    case 1:
                        getLogin();
                        end = true;
                        break;
                    case 2:
                        createAccount();
                        end = true;
                        break;
                    default:
                        System.out.println("\ngeçersiz seçim.");
                }
            } catch (InputMismatchException e) {
                System.out.println("\ngeçersiz seçim");
                menuInput.next();
            }
        }
        System.out.println("\nBu atm'yi kullandığınız için teşekkürler\n");
        menuInput.close();
        System.exit(0);
    }
}

