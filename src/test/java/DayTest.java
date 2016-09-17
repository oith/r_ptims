
import java.util.Date;
import static org.apache.commons.lang.time.DateUtils.addDays;

public class DayTest {

    public static void main(String[] args) {

        Date curDate = new Date();

        Date curDate7daysNew = addDays(curDate, 7);

        System.out.println("curDate        : " + curDate);
        System.out.println("curDate7daysNew: " + curDate7daysNew);
    }
}
