# LiterAlura_ONE

LiterAlura é um desafio proposto pela equipe da Alura em parceria com a Oracle no projeto Oracle Next Education(ONE). Esse projeto consiste em uma aplicação SpringBoot, que faz requisições para a API Gutendex, que disponibiliza dados sobre livros de forma gratuita. A API se encontra no seguinte endereço: https://gutendex.com 

# O que é o LiterAlura?
LiterAlura é uma aplicação de linha de comando feita usando o framework SpringBoot, que pesquisa informações sobre livros e os guarda em um banco de dados PostgreSQL. 

![image](https://github.com/levimaciell/literAlura_ONE/assets/93170652/60077960-d3be-401a-9a86-e260564026f0)

# Funcionalidades:

Essas informações podem ser consultadas por meio da interface de linha de comando criada, possuindo as seguintes opções: 

- Buscar um livro pelo título -> Ao indicar um título de um livro na interface de linha de comando (por exemplo: Dom casmurro), será exibido se o livro foi encontrado na API do Gutendex e irá guardar esse livro no banco de dados.
- Listar livros registrados -> Lista todos os livros presentes no banco de dados.
- Listar autores registrados -> Lista todos os autores que possuem livros presentes no banco de dados.
- Listar autores vivos em um determinado ano -> Ao informar um ano (Por exemplo, 1900), irá listar os autores vivos naquele ano.
- Listar livros em um determinado idioma -> Com base na sigla de um idioma (Por exemplo, pt de português), irá retornar os livros com tal idioma

![image](https://github.com/levimaciell/literAlura_ONE/assets/93170652/5af1c7db-72fb-4107-81b0-d0ac49429535)
