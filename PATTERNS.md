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


###Faz-2	

###3. Decorator Pattern (Structural / Yapısal)
- **Nerede ve Nasıl Uygulandı?**
- **Sınıf:** SepetSarmalayici ve ondan türeyen KargoMaliyetiSarmalayici sınıfları ile uygulandı.
- **İşlev:** Temel Sepet nesnesine, kodunu değiştirmeden dinamik olarak "kargo ücreti hesaplama" yeteneği kazandırır.
- **Neden Uygulandı?**
Kargo maliyeti gibi opsiyonel özellikleri sepetin ana sınıfına gömmek yerine, nesneyi çalışma zamanında (runtime) sarmalayarak esneklik sağlamak için tercih edildi.
Sağladığı Kazançlar
- **Modülerlik:** Kargo kuralları (bedava kargo limiti vb.) ana fiyatlandırma mantığından tamamen izole edildi.
- **Genişletilebilirlik:** İleride "Hediye Paketi" veya "Sigorta" gibi ek özellikler gelirse, mevcut kodu bozmadan yeni bir Decorator eklenebilir.

##4.Adapter Pattern (Structural / Yapısal) Nerede ve Nasıl Uygulandı?
- **Sınıf:** XKargoAdapter ve IKargoServisi arayüzü ile uygulandı.
- **İşlev:** Bizim sistemimize uyumlu olmayan dış bir kütüphaneyi (XKargoSistemi), sistemimizin beklediği dile çevirerek entegre etti.
- **Neden Uygulandı?**
Dışarıdan gelen kargo API'sinin metod isimleri bizim standartlarımıza uymuyordu. Dış kodun içine müdahale edemediğimiz için bu köprüyü kurduk.
Sağladığı Kazançlar
- **Uyumsuz Sistem Entegrasyonu:** Kod karmaşası yaratmadan farklı kargo firmalarıyla çalışma kapısı açıldı. 


## FAZ 3: BEHAVIORAL (DAVRANIŞSAL) ÖRÜNTÜLER

### 1. Strategy Pattern (Strateji Örüntüsü)
- **Nerede Kullanıldı:** `Sepet` sınıfı ile indirim hesaplama mekanizmaları arasında kullanıldı.
- **Neden Kullanıldı:** Sepet sınıfının içindeki katı fabrika bağımlılığını koparmak ve indirim mantığını dışarıdan enjekte edilebilir (Dependency Injection) hale getirmek için tercih edildi.
- **Ne Kazandırdı:** Açık/Kapalı Prensibi (Open/Closed Principle - OCP) tam anlamıyla sağlandı. Mevcut `Sepet` koduna tek bir satır dahi dokunmadan sisteme `HaftaSonuIndirimi` gibi yepyeni bir strateji eklenebilir hale geldi.

### 2. Template Method Pattern (Şablon Metot Örüntüsü)
- **Nerede Kullanıldı:** `FaturaSablonu`, `BireyselFatura` ve `KurumsalFatura` sınıfları hiyerarşisinde kullanıldı.
- **Neden Kullanıldı:** Fatura oluşturma algoritmasının iskeletini (başlık, içerik, dipnot) tek bir soyut sınıfta sabitlemek ve alt sınıfların sadece kendilerine özgü metinsel detayları (bireysel/kurumsal farkları) doldurmasını sağlamak için seçildi.
- **Ne Kazandırdı:** Kod tekrarı önlendi, fatura süreçleri standartlaştırıldı ve esnek string manipülasyonlarına dayalı gerçekçi bir mimari elde edildi.

