import java.util.*;
import java.util.Scanner;

public class Main {
    
    public static class ATM {
        double balance;
        int pin;
        String AccountNumber;
        boolean checked=false;
        
        
        public ATM(double balance, int pin, String AccountNumber) {
            this.balance = balance;
            this.pin = pin;
            this.AccountNumber = AccountNumber;
        }
        
        public boolean verifyPin(int pin, String AccountNumber){
            if(this.AccountNumber.equals(AccountNumber) && this.pin==pin){
                this.checked=true;
                return true;
                
            }
            else{
                return false;
            }
            
            
        }
        public double returnbalance() {
            if (this.checked) {
                return this.balance;
            } else {
                throw new IllegalStateException("Error: No ha verificado su PIN.");
            }
        }

        public double withdrawcash(double amount) {
            if (!this.checked) {
                System.out.println("Error: No ha verificado su PIN.");
                return this.balance;
            }
            
            if (amount > this.balance) {
                System.out.println("Error: Fondos insuficientes.");
                return this.balance;
            }
            if (amount < 0) {
                System.out.println("Error: Debes ser mayor a 0");
                return this.balance;
            }
            
            this.balance -= amount;
            return this.balance;
        }
        
        public double makedeposit(double amount) {
            if (!this.checked) {
                throw new IllegalStateException("Error: No ha verificado su PIN.");
            }
            
            this.balance += amount;
            return this.balance;
        }
      
    }
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ATM client1=new ATM(50000,4321,"1234-5678");
        int intentos=3;
        while(intentos>0){
            
            System.out.println("Ingrese su número de cuenta: ");
            String cuentaIngresada = scanner.nextLine();
            
            System.out.println("Ingrese su PIN: ");
            int pinIngresado = scanner.nextInt();
            scanner.nextLine();
            
            boolean verification=client1.verifyPin(pinIngresado,cuentaIngresada);
            if(verification){
                break;
            }else{
               intentos--; 
            }
            
        }
        if(client1.checked){
            int opcion;
            do 
           {
            System.out.println("Bienvenido a su cajero");
            System.out.println("1. Consultar saldo");
            System.out.println("2. Retirar saldo");
            System.out.println("3. Depositar monto");
            System.out.println("4. Salir");
            System.out.println("Seleccionar opción: ");
            opcion = scanner.nextInt();
    
            // Manejo de opciones
            switch (opcion) 
            {
                case 1:
                    System.out.println("Tienes en tu cuenta $: " + client1.returnbalance());
                    break;
    
                case 2:
                    System.out.println("¿Cuánto dinero deseas sacar?");
                    double retiro = scanner.nextDouble();
                    double result=client1.withdrawcash(retiro);
                    break;
    
                case 3:
                    System.out.println("¿Cuánto dinero deseas depositar?");
                    double deposito = scanner.nextDouble();
                    double resultDeposit = client1.makedeposit(deposito);
                    break;
    
                case 4:
                    System.out.println("¡Gracias por ingresar, vuelve pronto!");
                    break;
    
                default:
                    System.out.println("La opción ingresada no es válida.");
                    break;
            }
    
           } while (opcion != 4);
        }
        
        
        scanner.close();
        
        
        
        
    }
}
