
public class KombineIndirim implements IindirimStratejisi {
    private IindirimStratejisi temelIndirim;
    private IindirimStratejisi ekstraIndirim;

    public KombineIndirim(IindirimStratejisi temelIndirim, IindirimStratejisi ekstraIndirim) {
        this.temelIndirim = temelIndirim;
        this.ekstraIndirim = ekstraIndirim;
    }
    
    @Override
    public double indirimUygula(double fiyat) {
        double ilkFiyat = temelIndirim.indirimUygula(fiyat);
        return ekstraIndirim.indirimUygula(ilkFiyat);
    }
}