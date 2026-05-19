import java.util.Scanner;

public class Sepet implements ISepet {
	
    private double hamFiyat;
    private IindirimStratejisi indirimStratejisi; 
    private FaturaSablonu faturaSablonu;
    
    public Sepet(double hamFiyat, IindirimStratejisi indirimStratejisi, FaturaSablonu faturaSablonu) {
        this.hamFiyat = hamFiyat;
        this.indirimStratejisi = indirimStratejisi;
        this.faturaSablonu = faturaSablonu;
    }
    
    // Strategy Pattern: Çalışma Zamanı Değiştirici (Setter)
    public void setIndirimStratejisi(IindirimStratejisi indirimStratejisi) {
        this.indirimStratejisi = indirimStratejisi;
    }
    
    // Template Method: Çalışma zamanında fatura tipini değiştirebilmek için (Setter)
    public void setFaturaSablonu(FaturaSablonu faturaSablonu) {
        this.faturaSablonu = faturaSablonu;
    }

    @Override
    public double maliyetHesapla() {
        return indirimStratejisi.indirimUygula(hamFiyat);
    }
    
    @Override
    public String aciklama() {
        double indirimliFiyat = maliyetHesapla(); 
        double indirimMiktari = hamFiyat - indirimliFiyat;
        
        long indirimYuzdesi = 0;
        if (indirimMiktari > 0) {
            indirimYuzdesi = Math.round((indirimMiktari / hamFiyat) * 100);
        }

        return faturaSablonu.faturaHazirla(hamFiyat, indirimliFiyat, indirimYuzdesi);
    }
    

    public static void main(String[] args) {
        
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("İndirim tipini giriniz (Ogrenci, Emekli, Bayram, HaftaSonu):");
        String tip = scanner.nextLine();
        
        System.out.println("Toplam ürün tutarını giriniz:");
        double fiyat = scanner.nextDouble();

        // 1. TEMPLATE METHOD SEÇİMİ: Fatura şablonu belirlenecek
        System.out.println("Fatura türünü seçiniz (1: Bireysel, 2: Kurumsal):");
        int secim = scanner.nextInt();
        FaturaSablonu secilenFatura = (secim == 2) ? new KurumsalFatura() : new BireyselFatura();

        // Strateji üretiliyor
        IindirimStratejisi secilenStrateji = IndirimYap.indirimOlustur(tip);
        
        // Constructor Injection: Strateji ve Fatura Şablonu nesneleri Sepet sınıfına enjekte ediliyor
        ISepet sepet = new Sepet(fiyat, secilenStrateji, secilenFatura);

        // Decorator Pattern: Sepet nesnesi, kargo maliyeti hesaplaması için dinamik olarak sarmalanıyor.
        sepet = new KargoMaliyetiSarmalayici(sepet);
        
        System.out.println("\n\tSİSTEM ÇIKTISI");

        System.out.println(sepet.aciklama());
        
        System.out.println("==================================");
        System.out.printf("SON ÖDENECEK TOPLAM TUTAR: %.2f TL\n", sepet.maliyetHesapla());
        System.out.println("==================================");
        
        System.out.println("\n[LOJİSTİK SİSTEMİ BAĞLANTISI]");
        XKargoSistemi disSistem = new XKargoSistemi();
        IKargoServisi kargoServisi = new XKargoAdapter(disSistem);
        
        String takipKodu = kargoServisi.kargoGonder("Konya/Selçuklu");
        System.out.println("Sistem Kaydı: Kargo takip numaranız: " + takipKodu);
        
        scanner.close();		
    }
}
