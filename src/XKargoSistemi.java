// Bu sınıf dışarıdan hazır geldi, ismini değiştiremiyoruz.
public class XKargoSistemi {
    public String gonderimiBaslat(String adres, double agirlik) {
    	String takipNo = "XK-" + (int)(Math.random() * 10000);
        System.out.println("X-Kargo API: " + adres + " için " + takipNo + " nolu gönderi oluşturuldu.");
        return takipNo;
    }
}