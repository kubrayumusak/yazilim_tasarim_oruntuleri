Faz 0 adımında yazmış olduğum koddaki tasarım örüntülerine uymayan kısımlar şunlardır:

1. İndirim hesaplamasında open/closed prensibine aykırılık: Yeni indirim türü eklenmek istendiğinde switch-case bloğunun düzenlenmesi gerekecek.

2. İndirim tipi girişinde kırılganlık: Kullanıcıya indirim türünü giriniz diyoruz ama kullanıcı indirim türlerinin ne olduğunu bilmiyor ogrenci gibi bir indirim türünün bulunduğunu tahmin edip yazsa bile nasıl yazması gerektiğini bilmiyor (harfler büyük-küçük, ilk harf büyük kalanlar küçük, türkçe karakte bulunabilir-bulunamaz gibi) bu bağlamda kullanıcıya türlerin neler olduğunun görüntülenmesi isabetli olur.

3. Sepet sınıfında tek sorumluluk prensibine aykırılık: Sepet sınıfında indirimli fiyat hesaplama kodunun ve kargo ücreti ekleme kodunun bulunması kodu tek sorumluluk ilkesine uymaktan uzaklaştırıyor, God Class olmaya yaklaştırıyor. Mevcut durumda yeni indirim türleri eklenmek istendiğinde classı güncellememiz gerekecek.

4. Kargo ücretlendirilmesinde open/closed prensibine aykırılık: Kargo ücretlendirilmesini if-else bloklarıyla yapmak kodu sert bir hale büründürüyor, bu düzeltilmesi gereken bir sorundur.

5. Tekrarlanan kod: Kargo ücreti hesaplama kodunu içeren if blokları tekrar ediyor bu işlemi switch bloğunun dışında ayrı olarak yapmalıyız çünkü şu durumda kargo ücreti hesaplama şartları değiştiğinde kodun bakımı çok zor. Bu durum da SRP, OCP ihlali ve kırılganlık oluşturuyor.

6. OOP'ye aykırılık: Sepet classının içindeki odenecekTutarHesapla metotdunu statik tanımladım ve mainde bir nesne üzerinden  çağırmadım. Fonksiyonu statik tanımlamış olmam OOP ilkesine aykırılık oluşturuyor (override edilemez). Java gibi nesne merkezli bir dil kullanmışken OOP'nin kullanılması yerinde olur bu haliyle kod daha katı kalıyor.

+. Ayrıca çıktıların kullanıcı bilgilendirilmesi açısından daha ayrıntılı olması gerekiyor mesela müşteri aldığı ürüne ne kadar indirim uygulandı ya da ne kadar kargo ücreti ödeyecek bilmiyor.


-Benim bulduğum bu hatalara ek olarak yapay zekanın bulduğu hatalar da şöyle:

+1.: "Magic Numbers" (Sihirli Sayılar) Kullanımı
Sorun: Kodun içinde 0.85, 0.9, 0.8 gibi doğrudan yazılmış sayılar var.
Neden: Bu sayıların neyi temsil ettiği kodun içinde açıkça belirtilmemiş (hard-coded). Oranlar değişmek istendiğinde bu sayıları tüm kodun içinde arayıp bulmak hata payını artırır.

+2.: Esneklik Eksikliği (Rigidity) - Sadece Tek İndirim Uygulanabilmesi
Sorun: Tasarım, bir ürüne sadece bir tane indirim uygulanmasına izin veriyor.
Neden: İleride "Öğrenci olup aynı zamanda Bayram indiriminden yararlananlar" gibi bir senaryo gelirse bu switch-case yapısı tamamen çöker; çünkü her durumda sadece bir case çalışır.


## Faz 1: Yaratımsal ve Davranışsal Başlangıç Problemleri

### 1. Sepet Sınıfında Katı Nesne Bağımlılığı ve Sıkı Bağlılık (Tight Coupling)
* **Mevcut Sorun (Code Smell):** Projenin ilk çiğ halinde `Sepet` sınıfı, hangi indirim türünün (`OgrenciIndirim`, `EmekliIndirim`, `BayramIndirim`) uygulanacağına doğrudan kendi karar veriyor ve nesne üretimini `new` anahtar kelimesiyle kendi gövdesi içinde yapıyordu.
* **Teknik Analiz:** **Single Responsibility (Tek Sorumluluk)** ilkesi ağır şekilde ihlal edilmişti. `Sepet` sınıfı hem sepet işlemlerini (ürün yönetimi, fiyatlandırma) yürütüyor hem de nesne yaratma mantığını barındırarak bir "God Class" (Aşırı Yüklü Sınıf) olmaya yaklaşıyordu. Sisteme yeni bir indirim türü eklemek, mevcut `Sepet` kodunun değiştirilmesini gerektiriyordu.
* **Hedef Çözüm (Factory Method):** Nesne yaratma sorumluluğu `IndirimYap` adında merkezi bir fabrika sınıfı içerisindeki `indirimOlustur(tip: String)` metoduna devredilmiştir. `Sepet` sınıfı artık somut sınıfların nasıl örneklendiğini bilmek zorunda kalmadan, sıkı bağımlılıktan kurtulmuştur.

### 2. İndirim Algoritmalarındaki İf-Else / Switch-Case Dağınıklığı
* **Mevcut Sorun:** Farklı indirim oranlarının hesaplanması ve yönetilmesi, sepetin ana fiyat hesaplama fonksiyonu içinde iç içe geçmiş katı `switch-case` bloklarıyla çözülmeye çalışılıyordu.
* **Teknik Analiz:** **Open/Closed (Açık/Kapalı)** prensibi ihlal ediliyordu. Gelecekte yeni bir indirim kuralı geldiğinde (örneğin Faz 3'te eklediğimiz `HaftaSonuIndirim`) mevcut çalışan fiyat hesaplama koduna doğrudan müdahale edilmesi gerekiyor, bu da sistemin kırılganlığını artırıyordu.
* **Hedef Çözüm (Strategy Pattern):** `IindirimStratejisi` arayüzü tanımlanarak tüm indirim mantığı soyutlanmıştır. Her indirim türü kendi hesaplama algoritmasını kendi somut sınıfı içinde (`OgrenciIndirim.java`, `EmekliIndirim.java`, `BayramIndirim.java`) bağımsız birer strateji olarak implemente etmiştir.

### 3. Kullanıcı Girdilerindeki Kırılganlık ve Küçük-Büyük Harf Duyarlılığı
* **Mevcut Sorun:** Kullanıcı terminalden indirim türünü yazarken (`Ogrenci`, `ogrenci`, `OGRENCI` veya Türkçe karakterlerle) girdinin tam eşleşmemesi durumunda sistem hata veriyor ya da indirimi tamamen yok sayarak %0 uyguluyordu.
* **Teknik Analiz:** Girdi doğrulama ve string manipülasyon standartları zayıftı, bu da kullanıcı deneyimini (UX) ve sistem kararlılığını olumsuz etkiliyordu.
* **Hedef Çözüm:** Fabrika sınıfına (`IndirimYap`) gelen metinsel girdiler `toLowerCase(Locale.ENGLISH)` ve `trim()` süzgeçlerinden geçirilerek standardize edilmiştir. Böylece kullanıcının yazım hatalarından kaynaklı sistem kırılganlığı tamamen ortadan kaldırılmıştır.