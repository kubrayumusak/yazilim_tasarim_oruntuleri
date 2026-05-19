
public class IndirimYap {
    public static IindirimStratejisi indirimOlustur(String indirimTipi) {
        if (indirimTipi == null || indirimTipi.trim().isEmpty()) {
            return fiyat -> fiyat;
        }
        
        String temizTip = indirimTipi.toLowerCase().trim();
        
        if (temizTip.contains("+")) {
        	
        	String[] parcalar = temizTip.split("\\+");
            
            IindirimStratejisi nihaiIndirim = tekliIndirimUret(parcalar[0].trim());
            
            for (int i = 1; i < parcalar.length; i++) {
                IindirimStratejisi sonrakiIndirim = tekliIndirimUret(parcalar[i].trim());
                nihaiIndirim = new KombineIndirim(nihaiIndirim, sonrakiIndirim);
            }
            return nihaiIndirim;
        }
        return tekliIndirimUret(temizTip);
    }
    
    private static IindirimStratejisi tekliIndirimUret(String tip) {
        if (tip.equals("ogrenci")) return new OgrenciIndirim();
        if (tip.equals("emekli")) return new EmekliIndirim();
        if (tip.equals("bayram")) return new BayramIndirim();
        if (tip.equals("haftasonu")) return new HaftaSonuIndirimi();
        
        return fiyat -> fiyat;
    }
}