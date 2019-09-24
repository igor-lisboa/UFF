import java.util.ArrayList;

public class Database {
    private ArrayList<Employee> employees;

    public Database() {
        this.employees = new ArrayList<Employee>();
    }

    public ArrayList<Employee> getEmployees() {
        return this.employees;
    }

    public void saveEmployeeToDatabase(Employee e) {
        this.employees.add(e);
    }

    public void terminateEmployee(Employee e) {
        this.employees.remove(e);
    }

    public Employee selectEmployee(Employee e) {
        for (Employee employee : employees) {
            if (employee.getName().equals(e.getName()) && employee.getId() == e.getId()) {
                return employee;
            }
        }
        return null;
    }
}