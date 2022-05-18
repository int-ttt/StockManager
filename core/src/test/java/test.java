public class test {
    public static void main(String[] args) {
        System.out.println(String.format("INSERT INTO Users(id, pwd, data, money) VALUES ( %s, %s, '{}', 0)", "id", "pwd"));
    }
}