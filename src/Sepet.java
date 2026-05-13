import java.util.Scanner;

public class Sepet{
	
	public static double odenecekTutarHesapla(String indirimTipi, double fiyat){
		
		IindirimStratejisi strateji = IndirimYap.indirimOlustur(indirimTipi);
		fiyat = strateji.indirimUygula(fiyat);
			
		return fiyat;
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