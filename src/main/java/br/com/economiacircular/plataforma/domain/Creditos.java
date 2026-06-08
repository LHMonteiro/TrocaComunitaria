package br.com.economiacircular.plataforma.domain;


import java.util.Objects;

public final class Creditos {

    private final int verificarValor; 

    public Creditos(int valor){

        if (valor <= 0) {

            throw new IllegalArgumentException("Valor para crédito deve ser positivo");

        }

        this.verificarValor = valor;


    }

    public int getValor(){
        return this.verificarValor;
    }

    public Creditos somar(Creditos outros){
        return new Creditos(this.verificarValor + outros.verificarValor);
    }

    public Creditos subtrair(Creditos outros){
        if(outros.verificarValor > this.verificarValor){
            throw new IllegalArgumentException("Saldo insuficiente para realizar a troca");
        }

        return new Creditos(this.verificarValor - outros.verificarValor);
    }

    public boolean suficiente(Creditos outros){
        return this.verificarValor >= outros.verificarValor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Creditos)) return false;
        Creditos creditos = (Creditos) o;
        return verificarValor == creditos.verificarValor;
    }

    @Override
    public int hashCode() {
        return Objects.hash(verificarValor);
    }

    @Override
    public String toString() {
        return verificarValor + " créditos";
    }
}