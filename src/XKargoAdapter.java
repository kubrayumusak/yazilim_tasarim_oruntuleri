
public class XKargoAdapter implements IKargoServisi {
    private XKargoSistemi xKargo;

    public XKargoAdapter(XKargoSistemi xKargo) {
        this.xKargo = xKargo;
    }

    @Override
    public String kargoGonder(String adres) {
        return xKargo.gonderimiBaslat(adres, 1.0); 
    }

}