package tdd.part1;

public interface Expression {
  Money reduce(Bank bank, String to);
}