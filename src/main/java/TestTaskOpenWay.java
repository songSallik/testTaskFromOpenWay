import java.time.Year;
import java.time.YearMonth;
import java.util.Calendar;

public class TestTaskOpenWay {
    public static void main(String[] args) {

        System.out.println("Test task: ");
        Deposit myDeposite = new Deposit();
        // Сумма вклада в рублях
        myDeposite.setDepositAmount(1);
        // Срок вклада в месяцах
        myDeposite.setDepositTerm(1);
        // Процентная ставка (% годовых)
        myDeposite.setInterestRate(9.2);

        //Возвращает сумму вклада по депозиту(вклад + накопленный доход) и распечатывает в консоль
        System.out.println(myDeposite.getDepositAmountOnDeposit());
        //Возвращает кол-во месяцев при которой сумма вклада удвоится и распечатывает в консоль
        System.out.println(myDeposite.getCountMonthsForDoubleDeposit());


    }
}

class Deposit{
    String errCode;
    // Процентная ставка (% годовых)
    private double interestRate;
    // Сумма вклада в рублях
    private double depositAmount;
    // Срок вклада в месяцах
    private int depositTerm;

    public double getInterestRate(){ return interestRate;}
    public void setInterestRate(double interestRate){ this.interestRate=interestRate;}
    public double getDepositAmount(){ return depositAmount;}
    public void setDepositAmount(double depositAmount){
        try{
            if (!(depositAmount>0)){
                throw new IllegalArgumentException();
            }else{errCode ="OK";};

        }catch (IllegalArgumentException e){
            errCode = "Введено некорректное значение. Сумма вклада в рублях должна быть положительной и больше 0";
            System.out.println(errCode);
        }
        this.depositAmount=depositAmount;
    }

    public int getDepositTerm(){ return depositTerm;}
    public void setDepositTerm(int depositTerm){ this.depositTerm=depositTerm;}
    //Возвращает сумму вклада по депозиту(вклад + накопленный доход)
    public double getDepositAmountOnDeposit(){
        if(errCode.length() > 2){
            System.exit(0);
        }
        // Определяем текущий год и кол-во дней в году.
        Calendar calendar = Calendar.getInstance();
        // Текущий год
        int currentYear = calendar.get(Calendar.YEAR);
        // Кол-во дней в году
        int daysInYear = (Year.isLeap(currentYear)) ? 366 : 365;
        // Кол-во дней в месяце
        int currentMonth;
        int daysInMonth;
        currentMonth = Calendar.getInstance().get(Calendar.MONTH)+1;

        double profitInMonth = 0;
        double profit = 0;
        // расчет суммы вклада по депозиту
        for(int i=1; i<=depositTerm; i++){
            if (currentMonth>12){
                currentMonth = 1;
            }
            YearMonth yearMonthObject = YearMonth.of(currentYear, currentMonth);
            daysInMonth = yearMonthObject.lengthOfMonth();
            profitInMonth= depositAmount /100*interestRate / daysInYear * daysInMonth;
            setDepositAmount(depositAmount+profitInMonth);
            profit = profit + profitInMonth;
            //System.out.println("Вклад: "+this.depositAmount+ " ставка: "+ this.interestRate + " дней в году:"+ daysInYear +" Текущий месяц "+currentMonth+" дней в месяце "+ daysInMonth);
            //System.out.println("Доход в месяц: "+ profitInMonth);
            //System.out.println("Доход : "+ profit);
            ++currentMonth;
        }
        double res = Math.round(this.depositAmount*100)/100.00;
        return res;
    }
    //Возвращает кол-во месяцев при которой сумма вклада удвоится
    public int getCountMonthsForDoubleDeposit() {
        if(errCode.length() > 2){
            System.exit(0);
        }
        // Определяем текущий год и кол-во дней в году.
        Calendar calendar = Calendar.getInstance();
        // Текущий год
        int currentYear = calendar.get(Calendar.YEAR);
        // Кол-во дней в году
        int daysInYear = (Year.isLeap(currentYear)) ? 366 : 365;
        // Кол-во дней в месяце
        int currentMonth;
        int daysInMonth;
        currentMonth = Calendar.getInstance().get(Calendar.MONTH)+1;

        double profitInMonth = 0;
        double profit = 0;
        double needAmount = depositAmount* 2;

        int counter= 0;
        while (this.depositAmount <= needAmount) {
            if (currentMonth>12){
                currentMonth = 1;
            }
            YearMonth yearMonthObject = YearMonth.of(currentYear, currentMonth);
            daysInMonth = yearMonthObject.lengthOfMonth();
            profitInMonth= depositAmount /100*interestRate / daysInYear * daysInMonth;
            setDepositAmount(depositAmount+profitInMonth);
            profit = profit + profitInMonth;
            //System.out.println("Вклад: "+this.depositAmount+ " ставка: "+ this.interestRate + " дней в году:"+ daysInYear +" Текущий месяц "+currentMonth+" дней в месяце "+ daysInMonth);
            //System.out.println("Доход в месяц: "+ profitInMonth);
            //System.out.println("Доход : "+ profit);
            //System.out.println("Кол-во мес для удвоения : "+ counter);
            ++currentMonth;
            ++counter;
            double res = Math.round(depositAmount*100)/100.00;
        }
        return counter;
    }
}


