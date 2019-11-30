http://www.ic.uff.br/~fseixas/index.php?title=Redes_de_Computadores_2

MAC Address
48bits
identificar fisicamente interface de rede na LAN
ethernet / wifi (facilita handoof de rede)


pooling
se tem mts slaves, nao � eficiente

token passing
se n tem nd p transmitir, passa token

upstream
feito em timeslot(TDM)


meio multi acesso
FDM
TDM
CSMA-CD
taking turns

random access
aloha
s-aloha
csma
csma/cd

taking turns
polling
token passing
bletooth


app
traduz ip em mac address
mac n depende da REDE


tabela de roteamento (usando ip) = tabela mac (usando mac)

ethernet
nao orientado a conexao

SWITCH
guarda mac address conectados

MPLS
garantia de caminho
cria uma nova label p ip

> 06/09
* MANET
    * comunicacao entre dispositivos
    * nao necessario acesso a internet
* VANET
    * comunicacao de cidades inteligentes
* wireless link
    * relacao sinal-ruido
    * ou aumenta o sinal ou diminui o ruido

# psk
* imita portadora

# bpsk

# cdma
* envia cod q representa o bit
* cada fatia do codigo chama-se de chipping

# wifi
* 802.11
* dsss
    * cdma (fixo)
* csma/ca
* n evita colisao mas trata

> 01/11
# latencia - atraso p pacote chegar no seu destino
# gitter - latencia
# throw put - taxa de transferencia
# CBR - constant bit rate

# Di = (1-ALFA) . Dj-i + ALFA(Ri -Ti)

# Di=>delay estimado
# ALFA=>small constant: 0.1
# Ri=> time received


...


# compressao de video

## aproveitar da redundancia espacial
* qtd de frames iguais => n mandar tds

## aproveitar da redundancia temporal
* se aproveitar das poucas altercoes dos frames de video => mandar apenas do q foi alterado de um frame para outro

---------------------

# quantizar

* pegar o log de 2 dos niveis

* ex: 1024 niveis = palavra de comprimento de 10 bits

---------------------

## qtd de bits por quadro

* multiplicar linhas, colunas e comprimento em bits

## taxa

* qtd de bits por quadro multiplicado pelo numero de quadros

---------------------

# categorias de aplicacoes multimidea

## fluxo de audio e video armazenado

* dou play
* pega no servidor
* reproduz

## voz e video sobre ip interativo

## fluxo de audio e video continuo ao vivo

* youtube

---------------------

# categorias de fluxo continuo

## udp de fluxo continuo

## http de fluxo de video

## dash

* fazer stream de qualidades diferentes onde a capacidade da rede define qual qualidade sera usada

---------------------

# buffer de recepcao tcp

* janela deslizante

# buffer de aplicacao

* exibe midea e carrega buffer

---------------------

t = Q/R

---------------------

tamanho do buffer - B
taxa - x

---------------------

r = onde consome
t = onde gera
v = atraso
---------------------

# audio e video usam o mesmo ssrc?
* n, eles podem ser atribuitos para diferentes valores ssrc

---------------------

RTP e RTCP sao diferenciados pois usam portas distintas

---------------------

funcao do SIP  = acompanhar usuarios e seus enderecos de ip correspondentes, encaminha mensagens de invite p endereco de ip certo

---------------------

preemptivo => parar transmissao e emitir pacote com prioridade (utopico)

---------------------

FIFO => fila do starbucks
PRIORIDADE ROUND ROBIN => mesclar o trafego (um da frente outro do outro lado dps)
