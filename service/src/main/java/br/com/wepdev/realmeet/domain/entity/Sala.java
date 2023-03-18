package br.com.wepdev.realmeet.domain.entity;

import java.util.Objects;
import javax.persistence.*;

/*
Classe imutavel(depois de criada não e possivel alterar o valor dela), desta forma se evita a questão da concorrencia(multiplas requisições simultaneamente)
 */
//@SuppressWarnings("SpellCheckingInspection") // Anotaçao q retira o sublinhado de comentarios
@Entity
@Table(name = "sala")
public class Sala {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome", nullable = false)
    private String nome;

    @Column(name = "lugares", nullable = false)
    private Integer lugares;

    @Column(name = "ativa", nullable = false)
    private Boolean ativa; // Soft delete, as salas não serão deletadas

    /*
A JPA precisa de pelo ao menos um construtor vazio
 */
    public Sala() {}

    /*
    Nao sera possivel criar objetos Sala de fora da classe
     */
    private Sala(Long id, String nome, Integer lugares, Boolean ativa) {
        this.id = id;
        this.nome = nome;
        this.lugares = lugares;
        this.ativa = ativa;
    }

    /*
    Se a Sala nao tiver o ativo setado, ele sera por padrão true(ativo).
    Metodo chamado pela JPA antes do objeto ser persistido no banco de dados
     */
    @PrePersist
    public void prePersist() {
        if (Objects.isNull(ativa)) {
            ativa = true;
        }
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public Integer getLugares() {
        return lugares;
    }

    public Boolean getAtiva() {
        return ativa;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Sala sala = (Sala) o;
        return (
            Objects.equals(id, sala.id) &&
            Objects.equals(nome, sala.nome) &&
            Objects.equals(lugares, sala.lugares) &&
            Objects.equals(ativa, sala.ativa)
        );
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nome, lugares, ativa);
    }

    @Override
    public String toString() {
        return ("Sala{" + "id=" + id + ", nome='" + nome + '\'' + ", lugares=" + lugares + ", ativa=" + ativa + '}');
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    /*
    Com classe builder e possivel instanciar uma nova Sala
     */
    public static final class Builder {
        private Long id;
        private String nome;
        private Integer lugares;
        private Boolean ativa;

        private Builder() {}

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder nome(String nome) {
            this.nome = nome;
            return this;
        }

        public Builder lugares(Integer lugares) {
            this.lugares = lugares;
            return this;
        }

        public Builder ativa(Boolean ativa) {
            this.ativa = ativa;
            return this;
        }

        public Sala build() {
            return new Sala(id, nome, lugares, ativa);
        }
    }
}
