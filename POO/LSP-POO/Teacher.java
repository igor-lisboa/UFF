public abstract class Teacher {
    private String name;

    public void teach() {
        System.out.println("To dando aula!");
    }

    public void setName(String name) {
        this.name=name;
    }

    public String getName() {
        return this.name;
    }

    private void makeAnnoucement() {
        System.out.println("OU");
    }

    private void takeAtendeces() {
        System.out.println("To aqui!");
    }
    private void collectPaperWork() {
        System.out.println("Da esse papel aqui!");
    }
    private void conductHallwayDuties() {
        System.out.println("Mostra isso ai");
    }
    public void peformOtherResponsibilities() {
        System.out.println("To fazendo varias coisas");
    }

}