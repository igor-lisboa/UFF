import java.util.ArrayList;

public class Report {
    
    public void printEmployeeDetailReportXML (Database db, Employee e) {
        if (e == null) {
          ArrayList<Employee> aux = db.getEmployees();
          System.out.println("Report from all employees from the database in XML:\n");
          System.out.print("<?xml version= '1.0'?>\n<database>");
          for(int i = 0; i < aux.size(); i++){
            System.out.print("\n  <employees>\n    <id>"+ (aux.get(i)).getId() +"</id>\n    <name>"+ (aux.get(i)).getName() +"</name>\n    <department>"+ (aux.get(i)).getDepartment() +"</department>\n    <working>"+ (aux.get(i)).getWorking() +"</working>\n  </employees>");
          }
          System.out.println("\n</database>\n");
        }else{
          Employee selected = db.selectEmployee(e);
          System.out.println("Report from employee "+ selected.getName() +" in XML:\n");
          System.out.println("<?xml version= '1.0'?>\n<database>\n  <employees>\n    <id>"+ selected.getId() +"</id>\n    <name>"+ selected.getName() +"</name>\n    <department>"+ selected.getDepartment() +"</department>\n    <working>"+ selected.getWorking() +"</working>\n  </employees>\n</database>\n");
        }
    }

    public void printEmployeeDetailReportCSV (Database db, Employee e) {
        if (e == null) {
          ArrayList<Employee> aux = db.getEmployees();
          System.out.println("Report from all employees from the database in CSV:\n");
          System.out.println("id, name, department, working");
          for(int i = 0; i < aux.size(); i++){
            System.out.print((aux.get(i)).getId() +", "+ (aux.get(i)).getName() +", "+ (aux.get(i)).getDepartment() +", "+ (aux.get(i)).getWorking() +"\n");
          }
          System.out.println("");
        }else{
          Employee selected = db.selectEmployee(e);
          System.out.println("Report from employee "+ selected.getName() +" in CSV:\n");
          System.out.println("id, name, department, working\n"+ selected.getId() +", "+ selected.getName() +", "+ selected.getDepartment() +", "+ selected.getWorking() +"\n");
        }
    }

}