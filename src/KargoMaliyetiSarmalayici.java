public class KargoMaliyetiSarmalayici extends SepetSarmalayici {
    public double KARGO_UCRETI;
    
    private static final double BEDAVA_LIMIT = 1000.0;
    private static final double ORTA_LIMIT = 500.0;
    private static final double ALT_LIMIT = 200.0;
        
    public KargoMaliyetiSarmalayici(ISepet sepet) {
        super(sepet);
    }
    
    @Override
    public double maliyetHesapla() {
    	double mevcutFiyat = sarmalananSepet.maliyetHesapla();
        
    		if (mevcutFiyat >= BEDAVA_LIMIT) {
    			KARGO_UCRETI=0;
    		}
        else if(mevcutFiyat>=ORTA_LIMIT){
         	KARGO_UCRETI=10;
        }
        else if(mevcutFiyat>ALT_LIMIT) {
        		KARGO_UCRETI=25;
        }else {
        		KARGO_UCRETI=30;
        }
    		mevcutFiyat += KARGO_UCRETI;
        return mevcutFiyat;
    }
    
    @Override
    public String aciklama() {
        double araToplam = sarmalananSepet.maliyetHesapla();
        String oncekiAciklama = sarmalananSepet.aciklama();
        
        double kargoBedeli = (araToplam >= BEDAVA_LIMIT) ? 0.0 : 
                             (araToplam >= ORTA_LIMIT) ? 10.0 : 
                             (araToplam > ALT_LIMIT ? 25.0 : 30.0);

        if (araToplam >= BEDAVA_LIMIT) {
            return oncekiAciklama + 
                   String.format("\n[KARGO] %.2f TL Üzeri Alışveriş İçin: ÜCRETSİZ!", BEDAVA_LIMIT);
        } else {
            double kalan = BEDAVA_LIMIT - araToplam;
            return oncekiAciklama + 
                   String.format("\n[KARGO] Kargo Ücreti: +%.2f TL", kargoBedeli) +
                   String.format("\n[BİLGİ] Bedava kargo için %.2f TL daha ürün ekleyebilirsiniz.", kalan);
        }
    }
    
    
}