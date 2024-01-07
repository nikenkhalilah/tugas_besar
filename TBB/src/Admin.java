import java.util.Scanner;

public class Admin implements Login {
    String password = "admin";
    String username = "nikenkasir";
    String captcha = "RN1012";
    String inputCaptcha;
    String inputPassword;

    //method login
    public void login(){
        Scanner scanner = new Scanner(System.in);

        System.out.println("========== LOGIN =========");

        //Username
        System.err.println("Username\t: "+username);

        //Captcha
        System.out.println("Kode captcha = " + captcha);
        System.out.println("Masukkan captcha");
        inputCaptcha = scanner.next();

        while(!inputCaptcha.equalsIgnoreCase(captcha)){
            System.out.println("Captcha Salah\n");
            System.out.println("Masukkan captcha");
            inputCaptcha = scanner.next();
        }

        System.out.println("Login Berhasil\n\n");

        //Password
        System.out.println("Input Password");
        inputPassword = scanner.next();

        while(!inputPassword.equals(password)){
            System.out.println("Password Salah\n");
            System.out.println("Masukkan password : ");
            inputPassword = scanner.next();
        }
        
        System.out.println("Login Berhasil\n\n");

        
    }

}


