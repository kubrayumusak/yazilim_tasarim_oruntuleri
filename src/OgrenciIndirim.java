public class OgrenciIndirim implements IindirimStratejisi {
    @Override
    public double indirimUygula(double fiyat) {
        return fiyat * 0.85;
    }
}