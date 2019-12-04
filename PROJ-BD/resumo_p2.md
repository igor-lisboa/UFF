# leitura suja
* ler dado nao commitado

# leitura nao repetida
* ler o mesmo dado mais de uma vez na mesma transacao e nao ter certeza se o dado sera retornado igual

# leitura fantasma
* causada por novos registros q sao inseridos, atualizados, abortados ou commitados por transacao em paralelo o que faz com que valor recuperado antes, nunca tenha existido

# RECUPERAÇÃO

## no-steal/force
* só teorico pois nao é possivel garantir atualização em disco apenas no momento do COMMIT

# no-steal
* nao precisa de UNDO
* DO COMMIT AO FIM

# steal
* DO BEGIN AO FIM

# force
* DO BEGIN AO COMMIT

# no-force
* DO BEGIN AO END

## read
* nao aparece no log qnd a leitura suja n esta habilitada

# UNDO
* tem que ser feito antes do REDO
** feito de cima p bx, refazendo escritas
* TRANSACOES ATIVAS

# REDO
* feito de bx p cima
** olha operacoes de escrita e recupera o before image
* TRANSACOES COMMITADAS


# ROLLBACK
* UNDO

# checkpoing
* nao coloca informacoes commitadas pré checkpoing

# posicao inicial
* valor da before imagem de todos os acessos


