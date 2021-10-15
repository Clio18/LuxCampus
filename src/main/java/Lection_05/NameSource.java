package Lection_05;

public class NameSource {
    private String [] male;
    private String [] female;
    private String [] other;

    public NameSource() {
        this.male = new String[]{"Liam", "Noah", "Oliver", "Elijah", "William",
                "James", "Benjamin", "Lucas", "Henry", "Alexander", "Mason", "Michael",
                "Ethan", "Daniel", "Jacob", "Logan", "Jackson", "Levi", "Sebastian", "Mateo"};
        this.female = new String[]{"Olivia", "Emma", "Ava", "Charlotte", "Sophia",
                "Amelia", "Isabella", "Mia", "Evelyn", "Harper", "Camila", "Gianna",
                "Abigail", "Luna", "Ella", "Elizabeth", "Sofia", "Emily", "Avery", "Mila"};
        this.other = new String[]{"Emmanuel", "Jayce", "Lorenzo", "Ivan", "Jude",
                "August", "Kevin", "Malachi", "Elliott", "Rhett", "Archer", "Karter",
                "Arthur", "Luka", "Elliot", "Thiago", "Brandon", "Camden", "Justin",
                "Jesus", "Maddox"};
    }

    public String[] getMale() {
        return male;
    }

    public String[] getFemale() {
        return female;
    }

    public String[] getOther() {
        return other;
    }
}
