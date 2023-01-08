package br.com.alura.forum.model

import jakarta.persistence.*
import java.time.LocalDateTime

// As classes de domínio irão representar os recursos dentro da API

@Entity // -> vai representar uma tabela no banco de dados
// Com o @Entity indicamos que a classe Topico é uma entidade da JPA
data class Topico (

    // O var permite mudanças
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) // @GeneratedValue -> não é a aplicação que vai gerenciar os ID's e sim o banco de dados
    var id: Long? = null,
    var titulo: String,
    var mensagem: String,
    val dataCriacao:LocalDateTime= LocalDateTime.now(),
    @ManyToOne // -> Um curso pode ter vários tópicos associados
    val curso: Curso,
    @ManyToOne // -> Um usuário pode ter vários tópicos associados
    val autor: Usuario,
    @Enumerated(value = EnumType.STRING) // -> vai salvar no banco de dados o nome da constante: NAO_RESPONDIDO, FINALIZADO...
    val status: StatusTopico = StatusTopico.NAO_RESPONDIDO,
    @OneToMany(mappedBy = "topico") // -> um tópico pode ter várias respotas
    val respostas: List<Respostas> = ArrayList()
)