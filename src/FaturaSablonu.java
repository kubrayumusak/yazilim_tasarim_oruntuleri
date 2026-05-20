
public abstract class FaturaSablonu {

    public final String faturaHazirla(double hamFiyat, double indirimliFiyat, long indirimYuzdesi) {
        StringBuilder sb = new StringBuilder();
        sb.append("==================================\n");
        sb.append(faturaBasligi()).append("\n");
        sb.append("==================================\n");
        sb.append(String.format("Ürün Tutarı: %.2f TL\n", hamFiyat));
        
        sb.append(String.format("Uygulanan İndirim (%%%d): -%.2f TL\n", indirimYuzdesi, (hamFiyat - indirimliFiyat)));
        
        sb.append("----------------------------------\n");
        sb.append(String.format("ÜRÜN ARA TOPLAMI: %.2f TL\n", indirimliFiyat));
        sb.append(faturaDipnotu()).append("\n");
        sb.append("==================================");
        return sb.toString();
    }

    protected abstract String faturaBasligi();
    protected abstract String faturaDipnotu();
}