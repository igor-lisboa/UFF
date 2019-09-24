class SubstituteTeacher extends Teacher {

    @override
    public void teach() {
        //exemplo de substituir EnglishTeacher
        EnglishTeacher profIngles = new EnglishTeacher();
        profIngles.teach();
        profIngles=null;
    }

}