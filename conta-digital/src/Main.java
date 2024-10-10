import java.util.ArrayList;
import java.util.List;

abstract class Conta {
    protected String numero;
    protected String titular;
    protected double saldo;

    public Conta(String numero, String titular) {
        this.numero = numero;
        this.titular = titular;
        this.saldo = 0.0;
    }

    public void depositar(double valor) {
        if (valor > 0) {
            saldo += valor;
            System.out.println("Deposito de " + valor + " realizado com sucesso.");
        } else {
            System.out.println("Valor de deposito deve ser positivo.");
        }
    }

    public void sacar(double valor) {
        if (valor > 0 && valor <= saldo) {
            saldo -= valor;
            System.out.println("Saque de " + valor + " realizado com sucesso.");
        } else {
            System.out.println("Saque não realizado. Saldo insuficiente ou valor inválido.");
        }
    }

    public void transferir(double valor, Conta contaDestino) {
        if (valor > 0 && valor <= saldo) {
            this.sacar(valor);
            contaDestino.depositar(valor);
            System.out.println("Transferencia de " + valor + " realizada com sucesso para a conta " + contaDestino.numero);
        } else {
            System.out.println("Transferencia não realizada. Saldo insuficiente ou valor inválido.");
        }
    }

    public double getSaldo() {
        return saldo;
    }

    public String getNumero() {
        return numero;
    }

    public String getTitular() {
        return titular;
    }
}

class ContaCorrente extends Conta {
    public ContaCorrente(String numero, String titular) {
        super(numero, titular);
    }

    // Funcionalidades específicas podem ser adicionadas aqui
}

class ContaPoupanca extends Conta {
    public ContaPoupanca(String numero, String titular) {
        super(numero, titular);
    }

    // Funcionalidades específicas podem ser adicionadas aqui
}

class Banco {
    private List<Conta> contas;

    public Banco() {
        contas = new ArrayList<>();
    }

    public void adicionarConta(Conta conta) {
        contas.add(conta);
        System.out.println("Conta " + conta.getNumero() + " adicionada com sucesso.");
    }

    public Conta buscarConta(String numero) {
        for (Conta conta : contas) {
            if (conta.getNumero().equals(numero)) {
                return conta;
            }
        }
        return null;
    }
}

// Exemplo de uso
public class Main {
    public static void main(String[] args) {
        Banco banco = new Banco();
        
        ContaCorrente contaCorrente = new ContaCorrente("12345", "João");
        ContaPoupanca contaPoupanca = new ContaPoupanca("54321", "Maria");
        
        banco.adicionarConta(contaCorrente);
        banco.adicionarConta(contaPoupanca);
        
        contaCorrente.depositar(1000);
        contaCorrente.sacar(200);
        contaCorrente.transferir(300, contaPoupanca);
        
        System.out.println("Saldo Conta Corrente: " + contaCorrente.getSaldo());
        System.out.println("Saldo Conta Poupança: " + contaPoupanca.getSaldo());
    }
}

