/**
 * CS2030S Lab 5
 * AY21/22 Semester 2
 *
 * @author Tan Zong Zhi, Shaun (Group 16A)
 */
package cs2030s.fp;
import java.util.NoSuchElementException;

public abstract class Maybe<T> {
  private static class None extends Maybe<Object> {
    private static final Maybe<?> NONE = new None();

    @Override
    public String toString() {
      return "[]";
    }

    @Override
    public boolean equals(Object obj) {
      return obj instanceof None;
    }

    @Override
    protected Object get() throws NoSuchElementException {
      throw new NoSuchElementException();
    }
  }

  private static class Some<T> extends Maybe<T> {
    private final T value;

    protected Some(T value) {
      this.value = value;
    }

    @Override
    public String toString() {
      return String.format("[%s]", this.value);
    }

    @Override
    public boolean equals(Object obj) {
      if (!(obj instanceof Some<?>)) {
        return false;
      }

      Some<?> someObj = (Some<?>) obj;
      return someObj.value == null || this.value == null
          // Avoids `(null).equals` error, when either `value` is null
          ? someObj.value == this.value
          : someObj.value.equals(this.value);
    }

    @Override
    protected T get() {
      return this.value;
    }
  }

  public static <T> Maybe<T> none() {
    @SuppressWarnings("unchecked")
    Maybe<T> output = (Maybe<T>) None.NONE;
    return output;
  }

  public static <T> Maybe<T> some(T t) {
    return new Some<T>(t);
  }

  public static <T> Maybe<T> of(T value) {
    return value == null
        ? Maybe.none()
        : Maybe.some(value);
  }

  protected abstract T get();
}
