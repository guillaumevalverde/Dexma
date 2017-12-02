package gve.dexma.exception;

public class MoneyException extends Exception {
    public MoneyException(String not_enough_money) {
        super(not_enough_money);
    }
}
