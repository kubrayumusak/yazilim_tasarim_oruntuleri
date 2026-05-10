
public class IndirimYap {
    public static IindirimStratejisi indirimOlustur(String indirimTipi) {
    	
        if (indirimTipi.equalsIgnoreCase("ogrenci")) {
            return new OgrenciIndirim();
        } else if (indirimTipi.equalsIgnoreCase("emekli")) {
            return new EmekliIndirim();
        } else if (indirimTipi.equalsIgnoreCase("bayram")) {
        		return new BayramIndirim();
        }

        return fiyat -> fiyat; 
    }
}