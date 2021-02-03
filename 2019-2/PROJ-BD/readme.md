> 21/08
## schema
estrutura relacional entre tabelas e colunas


## instancia
bd em um dado instante de tempo com dados
a cada alteracao de dados gera-se uma nova instancia


## indice
caminho de acesso p dado q ele ta indexando
sempre ordenado


## indice denso
uma entrada de indice para cada valor de chave de busca


## indice esparso
entrada de indice apenas para alguns valores

### ex ordenado:
joga apenas valores de cada bloco de disco

aron
.
.
.
joao

se nome for helio, ele vai estar no primeiro bloco(do aron)


## criacao de indice
caso comum de consulta? perda ao criar indice? o que vale mais?


## CONTAS
tamanho do registro R=100bytes
tamanho do bloco B=1024bytes
r=30000 registros

Bfr (blocking factor) quantos registros cabem no bloco?
### floor(B/R)
1024/100 = 10 registros por bloco

quantos blocos sao necessarios por arquivo?
### ceil(r/Bfr)
30000/10 = 3000 blocos

quantos acessos sao necessarios para buscar um determinado valor?
### ceil(log2 B)
log2 3000 = 12 acessos a bloco


## indice secundario
pode ter varios
nao esta baseando na ordenacao fisica da tabela
pode criar em campo chave e campo nao chave
tem que ser denso
todos os valores diferentes,e onde estao

## indice secundario
+melhora desempenho da consulta
-impoe sobrecarga significativa na atualizacao do BD

### euristicas >>> criacao de indice OK
freq de consultas > freq de atualizacoes sobre a table
colunas frequentemente mencionadas nas clausulas where das colunas
chaves estrangeiras para tabelas c cardinalidade grande

# TODA CHAVE ESTRANGEIRA TEM QUE TER INDICE CRIADO

> 28/08
# indice criado automaticamente
* em cada relacao o oracle cria arvore B nao clusterizada para a chave primaria
    * as arvores b se mantem balanceadas sempre que uma nova chave e inserida

# indice composto
* ordenado lexicograficamente de acordo com a ordem dos atributos
    * (NOME,IDADE)
        * ana,32
        * beatriz,16
        * carlos, 2
        * daniel, 29
        * eric, 17
    * (IDADE,NOME)
        * carlos, 2
        * beatriz,16
        * eric, 17
        * daniel, 29
        * ana,32

# chave estrangeira
* criacao de indices

# exemplos
* CREATE INDEX [nome]
    * cria um index ordenado sobre a tabela explicitada
    * nao se pode criar indice sobre as views
    * nao pode criar indice caso a tabela esteja em uso
    * pode ser demorado
* Drop INDEX [NOME]

# overhead vs desempenho
* indice ocupa espaco em disco

# qnd criar indice secundario?
* para toda chave estrangeira
* adicionar indice para um atributo qualquer q eh usado frequentemente em buscas
* adicionar indice para atributos usados em GROUP BY, ORDER BY, MIN, MAX, AVG
# qnd NAO eh recomendado criar indice?
* qnd a tabela eh pequena
* se a tabela eh frequentemente alterada
* se a atualizacao eh mais frequente que as consultas

# index organized tables
* registro inteiro dentro do index

# indice bitmap
* cada entrada (valor diferente) => todo registro que atende esse valor
* se tem mt atualizacao na tabela -> nao eh positivo
* alguns sgbd deixam criar em cima de condicoes de juncao


# p forcar o uso do index
* logo apos o select inserir comentario
* inserir + logo apos abertura do comentario
* ex:
    * SELECT /*+ index(i idx_invoice_customer) */
> 04/09
# visao
* flexibilidade na apresentacao de dados
* guardar calculo, regra, apresentacao
* nao acessar diretamente a table
* uso de visoes nao tira desempenho da consulta
* permite salvar mesmo com erros de privilegio ou consulta
    * force
## constraints
* check option
    * so permite atualizar fonte se ao atualizar o registro continue visivel na visao
* read only
* contraint
* ex: CREATE [OR REPLACE] [ FORCE | NOFORCE (padrao) ] VIEW < view name > [(coluna alias)] AS < query > [ WITH CHECK OPTION ] [READ ONLY] [ CONSTRAINT ];
# sinonimos
* fornece um apelido (atalho)
* é um alias
* direciona objetos do banco
* podendo redireciona-los
* evita gabiarras
* ex
    * CREATE [ PUBLIC ] SYNONYM < synonym_name > FOR < object_name >;
## restricao
* sinonimos podem ser
    * private
    * public
        * exige privilegios especificos
        * nao permite RENAME
# sequence
* increment by
* start with
* maxvalue
* nomaxvalue
* minvalue
* cache
* nocache
* ex
    * CREATE SEQUENCE < sequence_name > [ OPTIONS ]
## currval
* retorna ultimo valor fornecido para mim [ sessao/usuario ] ( NAO ULTIMO VALOR PARA A SEQUENCE )
>11/09
# chave artificial
* nao existe no dominio
    * id... normalmente nao existe no dominio
# chave natural
* existe no dominio
    * cpf... exite no dominio
# Sequence
* existe independe de tabelas
* CURVAL
    * ultimo valor que a sequence retornou para minha sessao
    * nao necessariamente eh realmente o valor atual
    * podem haver outras sessoes
# check constraint
* tem que dar sempre TRUE
# dominio
* pode ser usado como tipo de colunas em tabelas
* seta check constraint
* seta tamanho
* seta tipo
# permissoes e privilegios
* recomendado n usar usuario dono de td para acesso em aplicacoes
# GRANT
* da privilegio
    * grant <privilegies> on <object> to <users> [with grant option]
* tira privilegio
    * revoke <privileges> on <object> from <user>
    * ao remover privilegio de um usuario
        * usuarios que receberam privilegios desse usuario perdem o privilegio tbm
...
# PROCESSAMENTO DE TRANSACOES
## ATOMICIDADE
* transacao indivisivel
* nao pode ter resultados parciais
* ou acontece por completo ou nada eh executado
* se algum der erro, rollback
## ITEM DE DADO
* lendo ou escrevendo registro
* itens de dados lidos
    * read set
* itens de dados escritos
    * write set
> 18/09
# LOG
* pode ser usado para fins de auditoria
* guarda valores anteriores, novos valores e o que foi feito
* write ahead log
    * qnd log for gravado em disco a execucao de uma alteracao eh liberada para executar
# transacoes RECUPERACAO
* falhas em disco
* falha de check constraint ou foreign key
* SGBD pode interromper transacoes pelo controle de concorrencia
# leitura suja
* ler dado nao commitado
# leitura nao repetida
* ler o mesmo dado mais de uma vez na mesma transacao e nao ter certeza se o dado sera retornado igual
# leitura fantasma
* causada por novos registros q sao inseridos e commitados por transacao em paralelo
    * registros atendem ao criterio da consulta
# tipos de isolamento
* ISOLATION LEVEL   | DIRTY READ    | NONREPEATABLE READ    | PHANTOM READ    
* READ UNCOMMITTED  | permitted     | permitted             | permitted
* READ COMMITTED    | -             | permitted             | permitted
* REPEATABLE READ   | -             | -                     | permitted
* SERIALIZABLE      | -             | -                     | -
# SERIALIZABLE
* garante que execucao da transacao retorne o mesmo conteudo mesmo executando em paralelo com outras transacoes em diferentes ordens de execucao
    * garante resultado com  base no snapshot do inicio da transacao
# FOR UPDATE
* bloqueia p n pegar id q possivelmente vem repetido
