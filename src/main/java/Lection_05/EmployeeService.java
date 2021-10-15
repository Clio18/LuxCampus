package Lection_05;

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
        return payments;
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
        Employee[] copy = copy(employees);
        int n = employees.length;
        for (int i = 0; i < n - 1; i++)
            for (int j = 0; j < n - i - 1; j++)
                if (copy[j].getName().compareTo(copy[j + 1].getName()) > 0) {
                    Employee temp = copy[j];
                    copy[j] = copy[j + 1];
                    copy[j + 1] = temp;
                }
        return copy;
    }


    //Employee[] sortByNameAndSalary() -> возвращают отсортированный массив с сотрудниками по критерию
    public Employee[] sortByNameAndSalary() {
        Employee[] copy = copy(employees);
        int n = copy.length;
        for (int i = 0; i < n - 1; i++)
            for (int j = 0; j < n - i - 1; j++)
                if (copy[j].getName().compareTo(copy[j + 1].getName()) > 0) {
                    Employee temp = copy[j];
                    copy[j] = copy[j + 1];
                    copy[j + 1] = temp;
                } else if (copy[j].getName().equals(copy[j + 1].getName())) {
                    if (copy[j].getSalary() < copy[j + 1].getSalary()) {
                        Employee temp = copy[j];
                        copy[j] = copy[j + 1];
                        copy[j + 1] = temp;
                    }
                }
        return copy;
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
        if (old != null) {
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
        }
        return old;
    }

    //void add(Employee) -> Добавляет нового сотрудника. Массив увеличивается на 1.
    public void add(Employee employee) {
        Employee[] employees = new Employee[this.employees.length + 1];
        for (int i = 0; i < this.employees.length; i++) {
            employees[i] = this.employees[i];
        }
        employees[this.employees.length] = employee;
        this.employees = employees;
    }

    //copy array
    public Employee[] copy(Employee[] employees) {
        Employee[] copy = new Employee[employees.length];
        for (int i = 0; i < employees.length; i++) {
            copy[i] = employees[i];
        }
        return copy;
    }


}
