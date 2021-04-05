package builder;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class TestBuilder {
  @Test
  public void builderCreatesValidTarget() {
    MyClass.MyBuilder mb = MyClass.builder()
        .a(new StringBuilder("Hello"))
        .b(Arrays.asList("X", "Y"))
        .c(99);
    MyClass c1 = mb.build();
    Assert.assertEquals("a should contain Hello", "Hello", c1.getA().toString());
    Assert.assertEquals("b should be [X, Y]", List.of("X", "Y"), c1.getB());
    Assert.assertEquals("c should be 99", 99, c1.getC());
  }

  @Test
  public void secondBuildMakesDifferentObject() {
    MyClass.MyBuilder mb = MyClass.builder()
        .a(new StringBuilder("Hello"))
        .b(Arrays.asList("X", "Y"))
        .c(99);
    MyClass c1 = mb.build();
    MyClass c2 = mb.build();
    Assert.assertNotSame("Two different objects expected", c1, c2);
  }

  @Test
  public void secondBuildHasDifferentObjects() {
    MyClass.MyBuilder mb = MyClass.builder()
        .a(new StringBuilder("Hello"))
        .b(Arrays.asList("X", "Y"))
        .c(99);
    MyClass c1 = mb.build();
    MyClass c2 = mb.build();
    Assert.assertNotSame("Objects for a should differ", c1.getA(), c2.getA());
    Assert.assertNotSame("Objects for b should differ", c1.getB(), c2.getB());
  }

  @Test
  public void secondBuildHasSamePropertyValues() {
    MyClass.MyBuilder mb = MyClass.builder()
        .a(new StringBuilder("Hello"))
        .b(Arrays.asList("X", "Y"))
        .c(99);
    MyClass c1 = mb.build();
    MyClass c2 = mb.build();
    Assert.assertEquals("Values of a should have same contents", c1.getA().toString(), c2.getA().toString());
    Assert.assertEquals("Values of b should have same contents", c1.getB(), c2.getB());
    Assert.assertEquals("Values of c should be same", c1.getC(), c2.getC());
  }

  @Test
  public void secondBuildCanHaveVariedPropertyValues() {
    MyClass.MyBuilder mb = MyClass.builder()
        .a(new StringBuilder("Hello"))
        .b(Arrays.asList("X", "Y"))
        .c(99);
    MyClass c1 = mb.build();
    MyClass c2 = mb.b(List.of("A")).build();
    Assert.assertEquals("Values of a should have same textual contents", c1.getA().toString(), c2.getA().toString());
    Assert.assertNotEquals("Values of b should have different contents", c1.getB(), c2.getB());
    Assert.assertEquals("Value of c2.b should be [A]", List.of("A"), c2.getB());
    Assert.assertEquals("Values of c should be same", c1.getC(), c2.getC());
  }
}
