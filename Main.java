class Main {
    public static void main(String[] args) {
        CuentaBancaria cuenta = new CuentaBancaria(1000);
        System.out.println("Saldo inicial: " + cuenta.getSaldo());
        
        Transferencia t1 = new Transferencia(cuenta, 200);
        Transferencia t2 = new Transferencia(cuenta, 300);
        Transferencia t3 = new Transferencia(cuenta, 500);
        
        t1.start();
        t2.start();
        t3.start();
        
        try {
            t1.join();
            t2.join();
            t3.join();
        } catch (InterruptedException e) {
            System.out.println(e);
        }
        System.out.println("Saldo final: " + cuenta.getSaldo());
    }
}
class Transferencia extends Thread {
    private CuentaBancaria cuenta;
    private int cantidad;

    public Transferencia(CuentaBancaria cuenta, int cantidad) {
        this.cuenta = cuenta;
        this.cantidad = cantidad;
    }

    @Override
    public void run() {
        cuenta.depositar(cantidad);
        System.out.println("Depositados: " + cantidad);
    }
}
class CuentaBancaria {
    private int saldo;

    public CuentaBancaria(int saldo) {
        this.saldo = saldo;
    }

    public void depositar(int cantidad) {
        this.saldo = this.saldo + cantidad;
    }

    public int getSaldo() {
        return this.saldo;
    }
}
