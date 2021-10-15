package Lection_05;

public class Designer extends Employee {
   private double rate;
   public int workedDays;

    public Designer(long id, String name, int age, double salary,
                    Gender gender, double rate, int workedDays) {
        super(id, name, age, salary, gender);
        this.rate = rate;
        this.workedDays = workedDays;
    }
    @Override
    public double earn(){
        //ставка + rate * workedDays
        return salary + rate * workedDays;
    }

    @Override
    public  String toString(){
        return getClass().getSimpleName() + ": " +
                "id=" + super.getId() +
                ", name='" + super.getName() + '\'' +
                ", age=" + super.getAge() +
                ", salary=" + salary +
                ", gender=" + super.getGender() +
                ", rate=" + rate +
                ", workedDays=" + workedDays;
    }
}
