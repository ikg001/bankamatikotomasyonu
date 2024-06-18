import java.text.DecimalFormat;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Hesap {
    // variables
    private int customerNumber;
    private int pinNumber;
    private double checkingBalance = 0;
    private double savingBalance = 0;

    Scanner input = new Scanner(System.in);
    DecimalFormat moneyFormat = new DecimalFormat("'$'###,##0.00");

    public Hesap() {
    }

    public Hesap(int customerNumber, int pinNumber) {
        this.customerNumber = customerNumber;
        this.pinNumber = pinNumber;
    }

    public Hesap(int customerNumber, int pinNumber, double checkingBalance, double savingBalance) {
        this.customerNumber = customerNumber;
        this.pinNumber = pinNumber;
        this.checkingBalance = checkingBalance;
        this.savingBalance = savingBalance;
    }

    public int setCustomerNumber(int customerNumber) {
        this.customerNumber = customerNumber;
        return customerNumber;
    }

    public int getCustomerNumber() {
        return customerNumber;
    }

    public int setPinNumber(int pinNumber) {
        this.pinNumber = pinNumber;
        return pinNumber;
    }

    public int getPinNumber() {
        return pinNumber;
    }

    public double getCheckingBalance() {
        return checkingBalance;
    }

    public double getSavingBalance() {
        return savingBalance;
    }

    public double calcCheckingWithdraw(double amount) {
        checkingBalance = (checkingBalance - amount);
        return checkingBalance;
    }

    public double calcSavingWithdraw(double amount) {
        savingBalance = (savingBalance - amount);
        return savingBalance;
    }

    public double calcCheckingDeposit(double amount) {
        checkingBalance = (checkingBalance + amount);
        return checkingBalance;
    }

    public double calcSavingDeposit(double amount) {
        savingBalance = (savingBalance + amount);
        return savingBalance;
    }

    public void calcCheckTransfer(double amount) {
        checkingBalance = checkingBalance - amount;
        savingBalance = savingBalance + amount;
    }

    public void calcSavingTransfer(double amount) {
        savingBalance = savingBalance - amount;
        checkingBalance = checkingBalance + amount;
    }

    public void getCheckingWithdrawInput() {
        boolean end = false;
        while (!end) {
            try {
                System.out.println("\ngeçerli vadesiz hesap  bakiye " + moneyFormat.format(checkingBalance));
                System.out.print("\nVadesiz Hesaptan çekmek istediğiniz tutar: ");
                double amount = input.nextDouble();
                if ((checkingBalance - amount) >= 0 && amount >= 0) {
                    calcCheckingWithdraw(amount);
                    System.out.println("\ngeçerli vadesiz hesap bakiyesi " + moneyFormat.format(checkingBalance));
                    end = true;
                } else {
                    System.out.println("\n Bakiye ekside olamaz");
                }
            } catch (InputMismatchException e) {
                System.out.println("\ngeçersiz tercih.");
                input.next();
            }
        }
    }

    public void getsavingWithdrawInput() {
        boolean end = false;
        while (!end) {
            try {
                System.out.println("\nCari Birikim Hesabı Bakiyesi:" + moneyFormat.format(savingBalance));
                System.out.print("\nBirikim Hesabından çekmek istediğiniz tutar: ");
                double amount = input.nextDouble();
                if ((savingBalance - amount) >= 0 && amount >= 0) {
                    calcSavingWithdraw(amount);
                    System.out.println("\nCari birikim hesabı bakiyesi " + moneyFormat.format(savingBalance));
                    end = true;
                } else {
                    System.out.println("\nbakiye negatif olamaz");
                }
            } catch (InputMismatchException e) {
                System.out.println("\ngeçersiz seçim");
                input.next();
            }
        }
    }

    public void getCheckingDepositInput() {
        boolean end = false;
        while (!end) {
            try {
                System.out.println("\nCari vadesiz Hesabı Bakiyesi: " + moneyFormat.format(checkingBalance));
                System.out.print("\nVadesiz Hesaptan yatırmak istediğiniz tutar: ");
                double amount = input.nextDouble();
                if ((checkingBalance + amount) >= 0 && amount >= 0) {
                    calcCheckingDeposit(amount);
                    System.out.println("\ncari vadesiz hesap bakiyesi " + moneyFormat.format(checkingBalance));
                    end = true;
                } else {
                    System.out.println("\nbakiye negatif olamaz.");
                }
            } catch (InputMismatchException e) {
                System.out.println("\ngeçersiz seçim");
                input.next();
            }
        }
    }

    public void getSavingDepositInput() {
        boolean end = false;
        while (!end) {
            try {
                System.out.println("\nCari birikim Hesabı Bakiyesi: " + moneyFormat.format(savingBalance));
                System.out.print("\nbirikim hesabına yatırmak istediğiniz tutar ");
                double amount = input.nextDouble();

                if ((savingBalance + amount) >= 0 && amount >= 0) {
                    calcSavingDeposit(amount);
                    System.out.println("\ncari birikim hesabı bakiyesi " + moneyFormat.format(savingBalance));
                    end = true;
                } else {
                    System.out.println("\nbakiye negatif olamaz.");
                }
            } catch (InputMismatchException e) {
                System.out.println("\ngeçrsiz seçim");
                input.next();
            }
        }
    }

    public void getTransferInput(String accType) {
        boolean end = false;
        while (!end) {
            try {
                if (accType.equals("Vadesiz")) {
                    System.out.println("\nPara aktarmak istediğiniz hesabı seçin:");
                    System.out.println("1. Birikim");
                    System.out.println("2. Çıkış");
                    System.out.print("\nseçim : ");
                    int choice = input.nextInt();
                    switch (choice) {
                        case 1:
                            System.out.println("\ncari vadesiz hesab bakiyesi " + moneyFormat.format(checkingBalance));
                            System.out.print("\nbirikim hesabına aktarmak istediğiniz tutar ");
                            double amount = input.nextDouble();
                            if ((savingBalance + amount) >= 0 && (checkingBalance - amount) >= 0 && amount >= 0) {
                                calcCheckTransfer(amount);
                                System.out.println("\ncari birikim hesabı bakiyesi " + moneyFormat.format(savingBalance));
                                System.out.println(
                                        "\nCari vadesiz hesabı bakiyesi " + moneyFormat.format(checkingBalance));
                                end = true;
                            } else {
                                System.out.println("\nBakiye negatif olamaz");
                            }
                            break;
                        case 2:
                            return;
                        default:
                            System.out.println("\nGeçersiz seçim.");
                            break;
                    }
                } else if (accType.equals("Birikim")) {
                    System.out.println("\nparayı aktarmak istediğiniz hesabı seçiniz");
                    System.out.println("1. vadesiz");
                    System.out.println("2. çıkış");
                    System.out.print("\nseçim : ");
                    int choice = input.nextInt();
                    switch (choice) {
                        case 1:
                            System.out.println("\ncari birikim hesabı bakiyesi " + moneyFormat.format(savingBalance));
                            System.out.print("\nbirikim hesabına yatırmak istediğiniz tutar ");
                            double amount = input.nextDouble();
                            if ((checkingBalance + amount) >= 0 && (savingBalance - amount) >= 0 && amount >= 0) {
                                calcSavingTransfer(amount);
                                System.out.println("\ncari vadesiz hesap bakiyesi " + moneyFormat.format(checkingBalance));
                                System.out.println("\ncarı birikim hesabı bakiyesi " + moneyFormat.format(savingBalance));
                                end = true;
                            } else {
                                System.out.println("\nbakiye negatif olamaz" );


                            }
                            break;
                        case 2:
                            return;
                        default:
                            System.out.println("\ngeçersiz seçim");
                            break;
                    }
                }
            } catch (InputMismatchException e) {
                System.out.println("\ngeçersiz seçim");
                input.next();
            }
        }
    }
}

