# Kotlin Retrofit2

Treinando o uso do retrofit.

### 1° commit

- RecyclerView dando GET e trazendo informações(apenas textos).

### 2° commit

- Criar um arquivo com o nome de Constantes.kt e nela terá a chave da sua api:``` const val KEY = "SUA_API_KEY"```

- Reutilização do código do [Kotlin Recycler View With Image](https://github.com/Pancitopenico/KotlinRecyclerViewWithImage
), onde foi aprendido como utilizar renderizar imagens usando o glide.

- Exemplo de como consumir uma API com nested child; para cada nested child do meu JSON eu devo criar uma classe nova .kt; dentro do meu Model/Post.kt eu tenho ``` val hits : ArrayList<Hits>?=null``` que é a referenciação do meu outro MODEL que é o arquivo Hits.kt e nele eu digo qual valor do meu nested child eu vou utilizar ```val previewURL: String```
