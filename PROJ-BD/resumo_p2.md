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
* flush p disco NÃO pode ser feito antes do commit

# steal
* DO BEGIN AO FIM
* flush p disco pode ser feito antes do commit

# force
* DO BEGIN AO COMMIT
* flush feito IMEDIATAMENTE no commit

# no-force
* DO BEGIN AO END
* flush pode ser feito tempos após o commit

## read
* nao aparece no log qnd a leitura suja n esta habilitada

# UNDO
* tem que ser feito antes do REDO
  * feito de cima p bx, refazendo escritas
* TRANSACOES ATIVAS
* recupera BFIMs para o disco

# REDO
* feito de bx p cima
  * olha operacoes de escrita e recupera o before image
* TRANSACOES COMMITADAS
* recupera AFIMs para o disco

# tipos de flush
* Steal/No-Force
  * Undo/Redo
* Steal/Force
  * Undo/No-redo
* No-Steal/No-Force
  * Redo/No-undo
* No-Steal/Force
  * No-undo/No-redo

# Deferred Update
* No Undo/Redo

# ROLLBACK
* UNDO

# checkpoing
* nao coloca informacoes commitadas pré checkpoing

# posicao inicial
* valor da before imagem de todos os acessos
