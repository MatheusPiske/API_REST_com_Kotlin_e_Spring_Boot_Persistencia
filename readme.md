Alguns comandos para realizar as operações dentro da API:

=> DELETE: /topicos/1

=> PUT: /topicos

{
"id": 1,
"titulo": "ALTERADO!",
"mensagem" : "ALTERADA!"
}

=> POST: /topicos

{
"titulo": "Duvida",
"mensagem" : "Mensagem de duvida",
"idCurso": 1,
"idAutor": 1
}

=> GET (ordenacao): /topicos?sort=id,desc&sort=titulo

=> GET (paginacao): /topicos?size=5&page=1

=> GET (filtro por curso): /topicos?nomeCurso=Kotlin

=> GET (todos): /topicos 

Podemos acessar o console do h2 através do seguinte endereço:

=> /h2-console