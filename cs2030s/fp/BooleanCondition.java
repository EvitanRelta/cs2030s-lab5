/**
 * A conditional statement that returns either true of false.
 * CS2030S Lab 4
 * AY21/22 Semester 2
 *
 * @author Tan Zong Zhi, Shaun (Group 16A)
 */

public interface BooleanCondition<T> {
  boolean test(T obj);
}
