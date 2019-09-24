public class Main {
    public static void main (String[] args) {
        Object[][] jogo = criaJogo();
        realizaJogada(1,jogo,selecionaJogada(jogo));
        printaJogo(jogo);
    }

    public static int[] retornaLinhaColuna(String posSel){
        String[] parts = posSel.split("|");
        int linha = Integer.parseInt(parts[0]); // linha
        int coluna = Integer.parseInt(parts[2]); // coluna
        int[] posicao = new int[] {linha,coluna};
        return posicao;
    }

    public static boolean realizaJogada(int Jogador,Object[][] jogo,String posSel){
        int[] pos = retornaLinhaColuna(posSel);
        if(jogo[pos[0]][pos[1]]==null){
            jogo[pos[0]][pos[1]]=Jogador;
            return true;
        } else {
            System.out.println("Jogada inválida");
            return false;
        }
        
    }

    public static String selecionaJogada(Object[][] jogo){
        int l = 0;
        int c=0;
        String ret="";
        String[] pos = new String[3];
        String posSel="";
        //verifica linhas
        for(l=0;l<jogo.length;l++){
            c=0;
            for(c=0;c<jogo[l].length;c++){
                ret+=(jogo[l][c]==null?" ":jogo[l][c]);
                pos[c]=l+"|"+c;
            }
            posSel=verificaSequencia(ret,pos);
            ret="";
            if(posSel!=""){
                return posSel;
            }
        }
        ret="";
        pos= new String[3];
        //verifica colunas
        for(c=0;c<jogo.length;c++){
            l=0;
            for(l=0;l<jogo[l].length;l++){
                ret+=(jogo[l][c]==null?" ":jogo[l][c]);
                pos[c]=l+"|"+c;
            }
            posSel=verificaSequencia(ret,pos);
            ret="";
            if(posSel!=""){
                return posSel;
            }
        }
        ret="";
        pos= new String[3];
        //verifica X1
        String[] x1= new String[]{"0|0","4|1","6|2"};
        for (String x: x1) {
            int[] posicao = retornaLinhaColuna(x);
            ret+=(jogo[posicao[0]][posicao[1]]==null?" ":jogo[posicao[0]][posicao[1]]);
            pos[c]=x;
        }
        posSel=verificaSequencia(ret,pos);
        ret="";
        if(posSel!=""){
            return posSel;
        }
        //verifica X2
        String[] x2= new String[]{"6|0","4|1","0|6"};
        for (String x: x2) {
            int[] posicao = retornaLinhaColuna(x);
            ret+=(jogo[posicao[0]][posicao[1]]==null?" ":jogo[posicao[0]][posicao[1]]);
            pos[c]=x;
        }
        posSel=verificaSequencia(ret,pos);
        ret="";
        if(posSel!=""){
            return posSel;
        }
        return "";
    }

    public static String verificaSequencia(String ret,String[] posicoes){
        String c1=String.valueOf(ret.charAt(0));
        String c2=String.valueOf(ret.charAt(1));
        String c3=String.valueOf(ret.charAt(2));
        if(c1.equals(c2) && !c1.equals(" ") && c3.equals(" ")){
            return posicoes[2];
        }
        if(c2.equals(c3) && !c2.equals(" ") && c1.equals(" ")){
            return posicoes[0];
        }
        if(c1.equals(c3) && !c3.equals(" ") && c2.equals(" ")){
            return posicoes[1];
        }
        return "";
        
    }


    public static Object[][] criaJogo(){
        //object p permitir null (se nao iria iniciar td c 0 ja)
        Object[] linha1= new Object[] {1,0,1};
        Object[] linha2= new Object[] {0,null,0};
        Object[] linha3= new Object[] {null,0,1};
        Object[][] jogo = new Object[][] {linha1,linha2,linha3};
        return jogo;
    }

    static public void printaJogo(Object[][] jogo){
        int l=0;
        for(l=0;l<jogo.length;l++){
            int c=0;
            System.out.println("");
            for(c=0;c<jogo[l].length;c++){
                System.out.print(jogo[l][c] + "(posição: linha->" + l + " coluna->" + c + ") - \r");
            }
        }
    }
}