public class Main {
    public static void main(String[] args) {
        Employee e1 = new Employee(1, "Caio");
        Employee e2 = new Employee(2, "Igor");
        Employee e3 = new Employee(3, "Ramilson");
        Employee e4 = new Employee(4, "Rafael");
        e1.setDepartment("HR");
        e2.setDepartment("Comercial");
        e3.setDepartment("Sales");
        e4.setDepartment("HR");
        e1.setWorking(false);
        e2.setWorking(true);
        e3.setWorking(true);
        e4.setWorking(true);
        Database db = new Database();
        db.saveEmployeeToDatabase(e1);
        db.saveEmployeeToDatabase(e2);
        db.saveEmployeeToDatabase(e3);
        db.saveEmployeeToDatabase(e4);
        Report rp = new Report();
        rp.printEmployeeDetailReportXML(db,e1);
        rp.printEmployeeDetailReportCSV(db,e2);
        rp.printEmployeeDetailReportCSV(db,e3);
        rp.printEmployeeDetailReportXML(db,e4);
        rp.printEmployeeDetailReportXML(db, null);
        db.terminateEmployee(e1);
        rp.printEmployeeDetailReportCSV(db, null);
    }
}