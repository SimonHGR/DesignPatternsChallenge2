package builder;

import java.util.ArrayList;
import java.util.List;

public class MyClass {
  private StringBuilder a;
  private List<String> b;
  private int c;

  public StringBuilder getA() {
    return new StringBuilder(a);
  }

  public List<String> getB() {
    return List.copyOf(b);
  }

  public int getC() {
    return c;
  }

  private MyClass() {
  }
}
