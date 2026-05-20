
public class KurumsalFatura extends FaturaSablonu {
    @Override
    protected String faturaBasligi() {
        return "\tKURUMSAL ŞİRKET FATURASI";
    }

    @Override
    protected String faturaDipnotu() {
        return "Not: Ticari faturadır, gider olarak gösterilebilir.";
    }
}