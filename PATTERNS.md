# Tasarım Örüntüleri Uygulama Raporu - Faz 1

Bu fazda, projenin esnekliğini artırmak ve nesne oluşturma süreçlerini modernize etmek amacıyla iki ana tasarım örüntüsü birlikte uygulanmıştır.

## 1. Factory Method (Creational / Yaratımsal)

### Nerede ve Nasıl Uygulandı?
- **Sınıf:** `IndirimYap` sınıfı içerisindeki `indirimOlustur` metodu ile uygulandı.
- **İşlev:** Kullanıcıdan gelen indirim tipine (`ogrenci`, `emekli`, `bayram`) göre uygun indirim nesnesini üretme sorumluluğunu üstlenir.

### Neden Uygulandı?
Eski yapıda `Sepet` sınıfı (God Class), hem sepet işlemlerini yürütüyor hem de hangi indirim nesnesinin yaratılacağına karar veriyordu. Bu durum, nesne yaratma mantığını `Sepet` sınıfına gömülü hale getirerek bağımlılığı artırıyordu.

### Sağladığı Kazançlar
- **Nesne Yaratma Soyutlaması:** `Sepet` sınıfı artık hangi somut sınıfın (`OgrenciIndirimi` vb.) örneklendiğini bilmek zorunda değildir.
- **Merkezi Kontrol:** Yeni bir indirim türü eklendiğinde sadece Factory (IndirimYap) sınıfında değişiklik yapılır.


## 2. Strategy Pattern (Behavioral / Davranışsal)

### Nerede ve Nasıl Uygulandı?
- **Yapı:** `IindirimStratejisi` arayüzü ve bu arayüzü implemente eden `OgrenciIndirimi`, `EmekliIndirimi`, `BayramIndirimi` sınıfları ile uygulandı.
- **İşlev:** Farklı indirim hesaplama algoritmalarını birbirinden bağımsız sınıflara ayırarak çalışma zamanında dinamik olarak değiştirilebilmesini sağlar.

### Neden Uygulandı?
Eski kodda bulunan karmaşık `if-else` veya `switch-case` blokları, yeni bir kural eklendiğinde mevcut çalışan kodun bozulma riskini (Open/Closed Principle ihlali) artırıyordu.

### Sağladığı Kazançlar
- **Open/Closed Principle:** Mevcut kodlara dokunmadan sisteme yeni indirim stratejileri eklenebilir.
- **Single Responsibility:** Her indirim sınıfı sadece kendi hesaplama mantığından sorumlu hale getirilerek kod temizlendi.