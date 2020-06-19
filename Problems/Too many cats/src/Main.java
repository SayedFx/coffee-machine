class Cat {

    String name;
    int age;
    static int counter;

    public Cat(String name, int age) {
        this.name = name;
        this.age = age;
        Cat.counter++;
        if (Cat.counter > 5) {
            System.out.println("You have too many cats");
        }
    }


    public static int getNumberOfCats() {
        return Cat.counter;
    }
}