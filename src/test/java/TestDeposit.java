import io.qameta.allure.Description;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.assertEquals;



public class TestDeposit {

    // Тест провалится так как конечная сумма расчитана от текущего периода 01.2021... если запустить тест 02.2021 значения не совпадут
    @Test
    @Description("Проверка при корректных значениях Вклада, Срока, Процентной ставки" )
    @DisplayName("correctAllValue")
    public void correctAllValue() {
        Deposit testDeposit = new Deposit();
        // Сумма вклада в рублях
        testDeposit.setDepositAmount(100000);
        // Срок вклада в месяцах
        testDeposit.setDepositTerm(6);
        // Процентная ставка (% годовых)
        testDeposit.setInterestRate(4);
        double result = testDeposit.getDepositAmountOnDeposit();

        assertEquals(102000.02, result);
    }

    @Test
    @Description("Проверка граничных значений - отрицательное значение Вклада" )
    @DisplayName("incorrectDepositAmountNegativeValue")
    public void incorrectDepositAmountNegativeValue() {
        Deposit testDeposit = new Deposit();
        // Сумма вклада в рублях
        testDeposit.setDepositAmount(-0.1);
        // Срок вклада в месяцах
        testDeposit.setDepositTerm(6);
        // Процентная ставка (% годовых)
        testDeposit.setInterestRate(4);

        assertEquals("Введено некорректное значение. Сумма вклада в рублях должна быть положительной и больше 0", testDeposit.errCode);
    }
    @Test
    @Description("Проверка граничных значений - 0 Вклада" )
    @DisplayName("incorrectDepositAmountBoundaryValue")
    public void incorrectDepositAmountBoundaryValue() {
        Deposit testDeposit = new Deposit();
        // Сумма вклада в рублях
        testDeposit.setDepositAmount(0);
        // Срок вклада в месяцах
        testDeposit.setDepositTerm(6);
        // Процентная ставка (% годовых)
        testDeposit.setInterestRate(4);

        assertEquals("Введено некорректное значение. Сумма вклада в рублях должна быть положительной и больше 0", testDeposit.errCode);
    }

    @Test
    @Description("Проверка граничных значений - отрицательное значение Срока" )
    @DisplayName("incorrectDepositTermNegativeValue")
    public void incorrectDepositTermNegativeValue() {
        Deposit testDeposit = new Deposit();
        // Сумма вклада в рублях
        testDeposit.setDepositAmount(100000);
        // Срок вклада в месяцах
        testDeposit.setDepositTerm(-1);
        // Процентная ставка (% годовых)
        testDeposit.setInterestRate(4);
        assertEquals("Срок вклада должен быть положительным", testDeposit.errCode);
    }
    @Test
    @Description("Проверка граничных значений - 0 Срок" )
    @DisplayName("incorrectDepositTermBoundaryValue")
    public void incorrectDepositTermBoundaryValue() {
        Deposit testDeposit = new Deposit();
        // Сумма вклада в рублях
        testDeposit.setDepositAmount(100000);
        // Срок вклада в месяцах
        testDeposit.setDepositTerm(0);
        // Процентная ставка (% годовых)
        testDeposit.setInterestRate(4);
        assertEquals("Срок вклада должен быть положительным", testDeposit.errCode);
    }
    @Test
    @Description("Проверка граничных значений - отрицательная Процентная ставка" )
    @DisplayName("incorrectInterestRateNegativeValue")
    public void incorrectInterestRateNegativeValue() {
        Deposit testDeposit = new Deposit();
        // Сумма вклада в рублях
        testDeposit.setDepositAmount(100000);
        // Срок вклада в месяцах
        testDeposit.setDepositTerm(6);
        // Процентная ставка (% годовых)
        testDeposit.setInterestRate(-0.1);
        assertEquals("Процентная ставка должена быть положительной", testDeposit.errCode);
    }
    @Test
    @Description("Проверка граничных значений - 0 Процентная ставка" )
    @DisplayName("incorrectInterestRateBoundaryValue")
    public void incorrectInterestRateBoundaryValue() {
        Deposit testDeposit = new Deposit();
        // Сумма вклада в рублях
        testDeposit.setDepositAmount(100000);
        // Срок вклада в месяцах
        testDeposit.setDepositTerm(6);
        // Процентная ставка (% годовых)
        testDeposit.setInterestRate(0.0);
        assertEquals("Процентная ставка должена быть положительной", testDeposit.errCode);
    }

    // Тест провалится так как конечная сумма расчитана от текущего периода 01.2021... если запустить тест 02.2021 значения не совпадут
    @Test
    @Description("Проверка если на срок вклада превысит один год" )
    @DisplayName("incorrectInterestRateBoundaryValue")
    public void crossingYear() {
        Deposit testDeposit = new Deposit();
        // Сумма вклада в рублях
        testDeposit.setDepositAmount(100000);
        // Срок вклада в месяцах
        testDeposit.setDepositTerm(13);
        // Процентная ставка (% годовых)
        testDeposit.setInterestRate(5.2);
        double result = testDeposit.getDepositAmountOnDeposit();

        assertEquals(105790.9, result);
    }

}
