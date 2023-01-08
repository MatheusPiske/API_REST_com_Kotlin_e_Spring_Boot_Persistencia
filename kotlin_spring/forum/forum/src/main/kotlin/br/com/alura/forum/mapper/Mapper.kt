package br.com.alura.forum.mapper

// Mapeando um objeto de um tipo "T" para um tipo "U"
interface Mapper<T, U> {

    fun map(t: T): U

}
