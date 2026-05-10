import java.util.Scanner;

public class Sepet{
	
	public static double odenecekTutarHesapla(String indirimTipi, double fiyat){
		double odenecekFiyat=fiyat;
		
		switch (indirimTipi) {
		case "ogrenci":
			odenecekFiyat=fiyat*0.85;
			
			if(odenecekFiyat<600) {
				odenecekFiyat=odenecekFiyat+10;
			}else if(odenecekFiyat<300) {
				odenecekFiyat=odenecekFiyat+25;
			}else {
				odenecekFiyat=odenecekFiyat+55;
			}
			break;
		case "emekli":
			odenecekFiyat=fiyat*0.9;
			
			if(odenecekFiyat<600) {
				odenecekFiyat=odenecekFiyat+10;
			}else if(odenecekFiyat<300) {
				odenecekFiyat=odenecekFiyat+25;
			}else {
				odenecekFiyat=odenecekFiyat+55;
			}
			break;
		case "bayram":
			odenecekFiyat=fiyat*0.8;
			
			if(odenecekFiyat<600) {
				odenecekFiyat=odenecekFiyat+10;
			}else if(odenecekFiyat<300) {
				odenecekFiyat=odenecekFiyat+25;
			}else {
				odenecekFiyat=odenecekFiyat+55;
			}
			break;
		default:
			System.out.println("Geçersiz tür!");
		}
			
		return odenecekFiyat;
	}
	
	public static void main(String[] args) {
		
		try (Scanner scanner = new Scanner(System.in)) {
			System.out.println("İndirim tipini giriniz:");
			String indirimTipi = scanner.nextLine();
			
			System.out.println("Toplam tutarı giriniz:");
			double odenecekFiyat = scanner.nextDouble();
			
			double odenecekTutar = 0;
			try {
				odenecekTutar = odenecekTutarHesapla(indirimTipi, odenecekFiyat);
			} catch (Exception e) {
				e.printStackTrace();
			}
			odenecekTutar=(int)(odenecekTutar*100);
			odenecekTutar=odenecekTutar/100.0;
			
			System.out.println("Ödeyeceğiniz ücret (kargo dahil) "+odenecekTutar+" liradır.");
		}
		
	}
	
}