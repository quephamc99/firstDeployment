import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

public class test {
    @Test
    public void test1(){
        System.out.println("Running Test 1!");
        Assertions.assertEquals("Honda", "Honda","Expected vehicle make match actual vehicle make");
    }

    @Test
    public void test2(){
        System.out.println("Running Test 2!");
        Assertions.assertEquals("Honda", "Honda","Expected vehicle make match actual vehicle make");
    }
}
