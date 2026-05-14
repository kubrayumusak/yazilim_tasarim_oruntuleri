import java.util.Scanner;

public class Sepet implements ISepet {
	
	private double hamFiyat;
    private String indirimTipi;
    
    public Sepet(double hamFiyat, String indirimTipi) {
        this.hamFiyat = hamFiyat;
        this.indirimTipi = indirimTipi;
        
    }

    @Override
    public double maliyetHesapla() {
        IindirimStratejisi strateji = IndirimYap.indirimOlustur(indirimTipi);
        return strateji.indirimUygula(hamFiyat);
    }
    
    @Override
    public String aciklama() {
        double indirimliFiyat = maliyetHesapla();
        double indirimMiktari = hamFiyat - indirimliFiyat;

        String indirimBilgisi;
        if (indirimMiktari > 0) {
            long indirimYuzdesi = Math.round((indirimMiktari / hamFiyat) * 100);
            indirimBilgisi = String.format("[İNDİRİM] %s: -%.2f TL (%%%d İndirim)", 
                              indirimTipi, indirimMiktari, indirimYuzdesi);
        } else {
            indirimBilgisi = "[İNDİRİM] Uygulanan indirim bulunmamaktadır.";
        }

        return String.format("Sipariş Özeti:\n" +
               "[ÜRÜN] Ham Tutar: %.2f TL\n" +
               "%s\n" +
               "[ARA TOPLAM] Net Tutar: %.2f TL", 
               hamFiyat, indirimBilgisi, indirimliFiyat);
    }
    
	public static void main(String[] args) {
		
		Scanner scanner = new Scanner(System.in);
        
        System.out.println("İndirim tipini giriniz (Örn: Bayram, Ogrenci, Emekli):");
        String tip = scanner.nextLine();
        
        System.out.println("Toplam ürün tutarını giriniz:");
        double fiyat = scanner.nextDouble();

        ISepet sepet = new Sepet(fiyat, tip);

        sepet = new KargoMaliyetiSarmalayici(sepet);
        
        System.out.println("\n\tMÜŞTERİ FATURASI");

        System.out.println(sepet.aciklama());
        System.out.printf("\nÖDENECEK TOPLAM TUTAR: %.2f TL\n", sepet.maliyetHesapla());
        
        System.out.println("\n[LOJİSTİK SİSTEMİ BAĞLANTISI]");
        XKargoSistemi disSistem = new XKargoSistemi();
        IKargoServisi kargoServisi = new XKargoAdapter(disSistem);
        
        String takipKodu = kargoServisi.kargoGonder("Konya/Selçuklu");
        System.out.println("Sistem Kaydı: Kargo takip numaranız: " + takipKodu);
        
        scanner.close();		
        
	}

}