package Lection_04;

public class EmployeeService {

    Employee[] employees;

    public EmployeeService(Employee[] employees) {
        this.employees = employees;
    }

    //void printEmployees() -> вывод на экран информации о сотрудниках
    public void printEmployees() {
        for (Employee employee : employees) {
            System.out.println(employee);
        }
    }

    //double calculateSalaryAndBonus() -> возвращает количество денег
    // необходимое для выплаты зарплат для всех сотрудников в этом месяце
    // (пробегаем по всем сотрудникам, суммируем зарплату каждого с бонусом каждого)
    public double calculateSalaryAndBonus() {
        double payments = 0;
        for (Employee employee : employees) {
            payments = payments + employee.earn();
        }
        return (Math.round(payments * 100)) / 100.0;
    }

    //Employee getById(long) -> возвращает сотрудника по заданному id
    public Employee getById(long id) {
        Employee target = null;
        for (Employee employee : employees) {
            if (employee.getId() == id) {
                target = employee;
            }
        }
        return target;
    }

    //Employee[] getByName(String) -> возвращает сотрудника по заданному имени
    public Employee[] getByName(String name) {
        int counter = 0;
        for (Employee employee : employees) {
            if (employee.getName().equals(name)) {
                counter++;
            }
        }
        Employee[] target = new Employee[counter];
        for (int i = 0; i < employees.length; i++) {
            if (employees[i].getName().equals(name)) {
                target[counter - 1] = employees[i];
                --counter;
            }
        }
        return target;
    }

    //Employee[] sortByName()
    public Employee[] sortByName() {
        int n = employees.length;
        for (int i = 0; i < n - 1; i++)
            for (int j = 0; j < n - i - 1; j++)
                if (employees[j].getName().compareTo(employees[j + 1].getName()) > 0) {
                    Employee temp = employees[j];
                    employees[j] = employees[j + 1];
                    employees[j + 1] = temp;
                }
        return employees;
    }


    //Employee[] sortByNameAndSalary() -> возвращают отсортированный массив с сотрудниками по критерию
    public Employee[] sortByNameAndSalary() {
        int n = employees.length;
        for (int i = 0; i < n - 1; i++)
            for (int j = 0; j < n - i - 1; j++)
                if (employees[j].getName().compareTo(employees[j + 1].getName()) > 0) {
                    Employee temp = employees[j];
                    employees[j] = employees[j + 1];
                    employees[j + 1] = temp;
                } else if (employees[j].getName().equals(employees[j + 1].getName())) {
                    if (employees[j].getSalary() < employees[j + 1].getSalary()) {
                        Employee temp = employees[j];
                        employees[j] = employees[j + 1];
                        employees[j + 1] = temp;
                    }
                }
        return employees;
    }

    //Employee edit(Employee) -> находит сотрудника по id, и подменяет информацию о нем на новую.
    // Старую версию сотрудника метод возвращает.
    public Employee edit(Employee newEmployee) {
        Employee old = getById(newEmployee.getId());
        Employee oldCopy = new Employee(old.getId(), old.getName(), old.getAge(),
                old.getSalary(), old.getGender(), old.getFixedBugs());
        oldCopy.setId(newEmployee.getId());
        old.setName(newEmployee.getName());
        old.setAge(newEmployee.getAge());
        old.setSalary(newEmployee.getSalary());
        old.setGender(newEmployee.getGender());
        old.setFixedBugs(newEmployee.getFixedBugs());
        return oldCopy;
    }

    //Employee remove(long id) -> находит сотрудника по id, и удаляет его из массива.
    //Возвращает ссылку на удаленного сотрудника.
    public Employee remove(long id) {
        Employee old = getById(id);
        int n = employees.length - 1;
        Employee newArray[] = new Employee[n];
        int counter = 0;
        for (Employee employee : employees) {
            if (employee.getId() == id) {
                continue;
            } else {
                newArray[counter] = employee;
                counter++;
            }
        }
        this.employees = newArray;
        return old;
    }


}
