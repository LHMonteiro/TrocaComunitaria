package br.com.economiacircular.plataforma.domain;    


import jakarta.persistence.*;

@Entity
@Table(name = "usuarios")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;   
    private String email;      
    private String senha;   
    private String contato;    
    private Integer saldoCreditos;
     

    //inicializar o saldo do usuario. 

    public void inicializarSaldo() {
        this.saldoCreditos = 100;
    }


    //verificar se a  senha está correta
    public boolean senhaCorreta(String senhaInformada) {
        return this.senha.equals(senhaInformada);
    }



    //execeção caso o usuario esteja com saldo insufucinete
    public void creditarSaldo(int verificarValor) {
        if (verificarValor <= 0) {

            throw new IllegalArgumentException("Valor para crédito deve ser positivo");

        }

        this.saldoCreditos += verificarValor;
    }



    public void debitarSaldo(int verificarValor) {
        if (verificarValor <= 0) {
            throw new IllegalArgumentException("Valor para débito deve ser positivo");
        }
        if (this.saldoCreditos < verificarValor) {
            throw new IllegalArgumentException("Saldo insuficiente para realizar a troca");
        }
        this.saldoCreditos -= verificarValor;
    }

    public Long getId() {    
        return id;
     }    
     public void setId(Long id) { 
          this.id = id; }

    public String getNome() { 
        return nome; 
    }

    public void setNome(String nome) { 
        this.nome = nome; 
    }  
    public String getEmail() 
    { 
        return email; 
    }

     public void setEmail(String email) { 
        this.email = email; 
    }     
    public String getSenha() {
         return senha; 
        }

    public void setSenha(String senha) { 
        this.senha = senha;
     }

                   
    public String getContato() { 
        return contato; 
    }    
                 
    public void setContato(String contato) { 
        this.contato = contato;
     }   
       
    public Integer getSaldoCreditos() {
         return saldoCreditos; 
        }
          
          
    public void setSaldoCreditos(Integer saldoCreditos) { 
        this.saldoCreditos = saldoCreditos; 
    
    }    
}