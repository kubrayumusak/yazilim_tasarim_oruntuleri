
public class BireyselFatura extends FaturaSablonu {
    @Override
    protected String faturaBasligi() {
        return "\tBİREYSEL MÜŞTERİ FATURASI";
    }

    @Override
    protected String faturaDipnotu() {
        return "Not: 14 gün içinde koşulsuz iade hakkınız mevcuttur.";
    }
}