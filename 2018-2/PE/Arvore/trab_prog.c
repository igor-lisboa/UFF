//Declaração de bibliotecas
#include <stdio.h>
#include <stdlib.h>
#include <locale.h>
#include <time.h> 

//Declaração da estrutura
typedef struct arvore{
    int val;
    struct arvore* dir;
    struct arvore* esq;
} arv;

//Aloca um nó e atribui um valor
arv *cria_no(int valor){
  arv *novo_no = (arv *)malloc(sizeof(arv));
  novo_no->val = valor;
  novo_no->esq = NULL;
  novo_no->dir = NULL;
  return novo_no;
}

//Função que busca valor na árvore e caso esse valor não seja encontrado ele é inserido
arv *busca_ou_insere(arv* pt,int valor,int nivel){
  //Verifica se a árvore é vazia
  if(pt==NULL){
    pt=cria_no(valor);
    printf("Um nó de valor %d foi adicionado na árvore, no nível %d!\n",pt->val,nivel);
  }else{
    //Verifica se o valor desejado é igual ao valor da árvore informada
    if(valor==pt->val){
      printf("Valor procurado: %d / valor encontrado: %d!\nO valor desejado já encontra-se na árvore, no nível %d.\n",valor,pt->val,nivel);
    }else{
      //Verifica se o valor desejado é menor que o valor da árvore informada
      if(valor<pt->val){
        //Verifica se a árvore esquerda da árvore informada existe
        if(pt->esq==NULL){
          //Se não existir cria nó e insere na árvore esquerda
          pt->esq=cria_no(valor);
          printf("Um nó de valor %d foi adicionado na árvore, no nível %d!\n",pt->esq->val,nivel+1);
        }else{
          //busca a árvore esquerda da árvore informada
          busca_ou_insere(pt->esq,valor,nivel+1);
        }
      }else{
        //Verifica se a árvore direita da árvore informada existe
        if(pt->dir==NULL){
          //Se não existir cria nó e insere na árvore direita
          pt->dir=cria_no(valor);
          printf("Um nó de valor %d foi adicionado na árvore, no nível %d!\n",pt->dir->val,nivel+1);
        }else{
          //busca a árvore direita da árvore informada
          busca_ou_insere(pt->dir,valor,nivel+1);
        }
      }
    }
  }
  return pt;
}

//imprime árvore Em Ordem (arv->esq / raiz / arv->dir)
void EmOrdem(arv * arvore, int nivel){
    if(arvore != NULL){
        EmOrdem(arvore->esq,nivel+1);
        printf("\n%d no nível %d", arvore->val,nivel);
        EmOrdem(arvore->dir,nivel+1);
    }
}

//troca inteiros 
void troca(int *a, int *b){ 
    int temp = *a; 
    *a = *b; 
    *b = temp; 
}

//Função para gerar uma permutação randomica de um array 
void randomiza( int arr[], int n) {
    // Use um valor de entrada diferente para não retornar o mesmo resultado
    srand(time(NULL)); 
    // Inicia a troca do último elemento e vai até o inicio
    for (int i = n-1; i > 0; i--){
        // Pega um index aleatório de 0 até i
        int j = rand() % (i+1); 
        // troca o array da posicao i com o elemento do index aleatório
        troca(&arr[i], &arr[j]); 
    } 
}

int main(){
  //permite o uso de acentos e ç
  setlocale(LC_ALL, "Portuguese");
  //declara árvore vazia
  arv *arvore = NULL;
  //número máximo de itens definido como padrão = 40 pela questão
  int num_max_itens=40;
  //declara array de valores
  int valores[num_max_itens];
  //declara index de início
  int index_valores=0;
  char resposta[1]="";
  printf("Olá, seja bem vindo.\n");
  printf("Esse programa tem o objetivo de montar uma árvore binária de busca!\n");
  printf("Para gerar a árvore usando uma sequencia aleatória, digite qualquer letra.\n");
  scanf("%s", &resposta[0]);
  //preenche valores de forma automática
  for (int i = 0; i < num_max_itens; i++) {
    valores[i] = i+1;
    index_valores+=1;
  }
  //embaralha valores
  int tam_valores = sizeof(valores)/ sizeof(valores[0]); 
  randomiza(valores,tam_valores);
  printf("Finalizando seleção e embaralhamento de valores a serem colocados na árvore...\n");
  printf("Iniciando montagem da árvore...\n");
  for(int i=0;i<index_valores;i++){
    arvore=busca_ou_insere(arvore, valores[i],1);
  }
  printf("\nÁrvore impressa Em Ordem(arv->esq/raiz/arv->dir) abaixo:\n");
  EmOrdem(arvore,1);
  printf("\nPara buscar ou inserir um valor na arvore digite 'I'(com o CAPS LOCK ativado).\n");
  printf("Para limpar a árvore digite 'D'(com o CAPS LOCK ativado).\n");
  printf("Para visualizar a árvore digite 'V'(com o CAPS LOCK ativado).\n");
  printf("Para finalizar o programa digite 'F'(com o CAPS LOCK ativado).\n");
  scanf("%s", &resposta[0]);
  while(resposta[0]!='F'){
    if(resposta[0]=='I'){
      int dado;
      printf("\nDigite o valor que deseja inserir ou buscar na árvore.\n");
      scanf("%d", &dado);
      arvore=busca_ou_insere(arvore, dado,1);
    }else if(resposta[0]=='D'){
      arvore = NULL;
      printf("A árvore foi esvaziada.\n");
    }else if(resposta[0]=='V'){
      printf("\nÁrvore impressa Em Ordem(arv->esq/raiz/arv->dir) abaixo:\n");
      EmOrdem(arvore,1);
    }else{
      printf("\nDigite um comando válido.\n");
    }
    printf("\nPara buscar ou inserir um valor na arvore digite 'I'(com o CAPS LOCK ativado).\n");
    printf("Para limpar a árvore digite 'D'(com o CAPS LOCK ativado).\n");
    printf("Para visualizar a árvore digite 'V'(com o CAPS LOCK ativado).\n");
    printf("Para finalizar o programa digite 'F'(com o CAPS LOCK ativado).\n");
    scanf("%s", &resposta[0]);
  }
  printf("\nFinalizando programa...\n");
  return 0;
}