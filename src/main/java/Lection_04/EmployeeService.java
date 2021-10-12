package Lection_04;

public class EmployeeService {

    private EmployeeFactory employeeFactory = new EmployeeFactory(5);
    private Employee[] employees = employeeFactory.getEmployees();

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
            payments = payments + employee.getSalary() +
                    (employee.getFixedBugs() * Employee.defaultBugRate);
        }
        return (Math.round(payments * 100)) / 100.0;
    }

    //Employee getById(long) -> возвращает сотрудника по заданному id
    public Employee getById(long id) {
        Employee target = new Employee("INVALID");
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
//    public Employee[] sortByName() {
//        for (int i = 0; i < employees.length - 1; i++) {
//            //[.xxxx]
//            for (int j = i + 1; j < employees.length; j++) {
//                if (employees[i].getName().toCharArray()[0] > employees[j].getName().toCharArray()[0]) {
//                    //replace
//                    Employee x = employees[i];
//                    employees[i] = employees[j];
//                    employees[j] = x;
//                }
//            }
//        }
//        return employees;
//    }

//    int position (String one, String two){
//        int position = 0;
//        if (one.equals(two)){
//            return position;
//        } else if (one.toCharArray().length==two.toCharArray().length){
//            for (int i = 0; i < one.length(); i++) {
//                if(one.toCharArray()[i]==two.toCharArray()[i]){
//                    position ++;
//                }
//            }
//            return position;
//        } else
//    }

//    //Employee[] sortByNameAndSalary() -> возвращают отсортированный массив с сотрудниками по критерию
//    public Employee[] sortByNameAndSalary() {
//       sortByName();
//       return null;
//
//    }

//Employee edit(Employee) -> находит сотрудника по id, и подменяет информацию о нем на новую.
// Старую версию сотрудника метод возвращает.
//    Employee edit(Employee newEmployee) {
//        Employee old = getById(newEmployee.getId());
//        Employee oldCopy = new Employee(old.getName(), old.getAge(),
//                old.getSalary(), old.getGender(), old.getFixedBugs());
//        oldCopy.setId(old.getId());
//        old.setName(newEmployee.getName());
//        old.setAge(newEmployee.getAge());
//        old.setSalary(newEmployee.getSalary());
//        old.setGender(newEmployee.getGender());
//        old.setFixedBugs(newEmployee.getFixedBugs());
//        return oldCopy;
//    }

//Employee remove(long id) -> находит сотрудника по id, и удаляет его из массива.
// Возвращает ссылку на удаленного сотрудника.
//    Employee remove (long id) {
//        Employee old = getById(id);
//        Employee oldCopy = new Employee(old.getName(), old.getAge(),
//                old.getSalary(), old.getGender(), old.getFixedBugs());
//        oldCopy.setId(old.getId());
//
//        Employee [] newEmployees = new Employee[employees.length-1];
//        int count = 0;
//        for (int i = 0; i < newEmployees.length; i++) {
//            if(employees[i].getName().equals(old.getName())){
//                continue;
//            }else {
//                newEmployees[count++] = employees[i];
//            }
//        }
//        employeeFactory.setEmployees(newEmployees);
//        return oldCopy;
//    }


}
