import java.time.DayOfWeek;
import java.time.LocalDate;

public class HaftaSonuIndirimi implements IindirimStratejisi {

    @Override
    public double indirimUygula(double fiyat) {

        DayOfWeek bugun = LocalDate.now().getDayOfWeek();
        
        if (bugun == DayOfWeek.SATURDAY || bugun == DayOfWeek.SUNDAY) {
            return fiyat * 0.80;
        }
        
        return fiyat;
    }
}