import java.util.Calendar;
import java.util.Date;

public class main {

    public static void main(String[] args) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        int dayVal = calendar.get(Calendar.DAY_OF_MONTH);
        int monthVal = calendar.get(Calendar.MONTH) + 1;
        int yearVal = calendar.get(Calendar.YEAR);
        String fileName = "统计数据" + monthVal + "" + dayVal;
        System.out.println(fileName);
    }
}
