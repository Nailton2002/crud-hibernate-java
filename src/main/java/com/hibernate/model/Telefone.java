package com.hibernate.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Telefone {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(nullable = false)
    private String tipo;
    @Column(nullable = false)
    private String numero;
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Pessoa pessoa;

    public Telefone() {}

    public Long getId() {return id;}

    public void setId(Long id) {this.id = id;}

    public String getTipo() {return tipo;}

    public void setTipo(String tipo) {this.tipo = tipo;}

    public String getNumero() {return numero;}

    public void setNumero(String numero) {this.numero = numero;}

    public Pessoa getPessoa() {return pessoa;}

    public void setPessoa(Pessoa pessoa) {this.pessoa = pessoa;}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Telefone)) return false;
        Telefone telefone = (Telefone) o;
        return Objects.equals(getId(), telefone.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

    @Override
    public String toString() {
        return "Telefone{" +
                "id=" + id +
                ", tipo='" + tipo + '\'' +
                ", numero='" + numero + '\'' +
                ", pessoa=" + pessoa +
                '}';
    }
}
