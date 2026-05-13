public class BayramIndirim implements IindirimStratejisi {
    @Override
    public double indirimUygula(double fiyat) {
        return fiyat * 0.8;
    }
}