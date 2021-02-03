//DIP - PRINCIPIO DA INVERSÃO DA DEPENDENCIA
/*Grupo:
IGOR LISBOA
CAIO BARROS
RAFAEL GOUVEIA
RAMILSON SILVA
GIOVANI HENRIQUES*/
public class Main {
    public static void main(String arg[]) {
        SmartTv tv = new SmartTv();
        Laptop notebook =  new Laptop();
        ProcessoFabricaGeral teste = new ProcessoFabricaGeral();
        teste.fabrica(tv);
        teste.fabrica(notebook);
    }
}