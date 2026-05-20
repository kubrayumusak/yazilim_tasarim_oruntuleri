public class BayramIndirim implements IindirimStratejisi {
    @Override
    public double indirimUygula(double fiyat) {
    		double indirimliFiyat = fiyat * 0.8;
        return indirimliFiyat;
    }
}