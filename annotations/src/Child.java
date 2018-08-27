

@Description("i am class annotation")
public class Child implements People {

    @Override
    @Description("i an method annotation")
    public String name() {
        return null;
    }

    @Override
    public int age() {
        return 0;
    }

    @Override
    public void work() {

    }
}
