public abstract class SepetSarmalayici implements ISepet {
    protected ISepet sarmalananSepet;

    public SepetSarmalayici(ISepet sepet) {
        this.sarmalananSepet = sepet;
    }

    @Override
    public double maliyetHesapla() {
        return sarmalananSepet.maliyetHesapla();
    }

    @Override
    public String aciklama() {
        return sarmalananSepet.aciklama();
    }
}