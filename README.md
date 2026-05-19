# yazilim_tasarim_oruntuleri
SEÇİMİM: D
SEÇME NEDENİM: İndirim hesaplamaları gibi işlemleri açık/kapalı prensibine uygulamanın daha gözle görünür olacağını düşündüm. Yeni bir özellik eklerken mevcut sistemi bozmadan bunu yapabilmeyi görmek istedim.
Ayrıca e-ticaret sitelerindeki dinamik indirim yönetiminin nasıl işlediğini öğrenmek, bu indirimlerin arkasındaki algoritmaları yazmak-kullanmak istedim.

---

## Kullanılan Tasarım Örüntüleri

### 1. Creational (Yaratımsal) Örüntüler
* **Factory Method Pattern (Fabrika Yöntemi):** İndirim stratejilerinin nesne üretim süreçlerini sepet sınıfının dışına taşımak ve üretimi tek bir noktadan yönetmek için kullanılmıştır (`IndirimYap` sınıfı).

### 2. Structural (Yapısal) Örüntüler
* **Decorator Pattern:** Sepet tutarına ek kargo maliyetleri gibi özellikleri, mevcut temel sipariş mantığını bozmadan çalışma zamanında dinamik olarak eklemek için uygulanmıştır (`KargoMaliyetiSarmalayici`).
* **Adapter Pattern:** Sisteme entegre edilmek istenen üçüncü parti dış `XKargoSistemi` kütüphanesini, projenin kendi içindeki `IKargoServisi` arayüzüne uyumlu hale getirmek için kullanılmıştır (`XKargoAdapter`).

### 3. Behavioral (Davranışsal) Örüntüler
* **Strategy Pattern (Strateji Örüntüsü):** Sepet sınıfının indirim hesaplama algoritmalarına olan katı bağımlılığını gevşetmek amacıyla kullanılmıştır. İndirimler `IindirimStratejisi` arayüzü üzerinden sepete dışarıdan enjekte edilmektedir.
* **Template Method Pattern (Şablon Metot Örüntüsü):** Fatura oluşturma algoritmasının genel iskeletini `FaturaSablonu` soyut sınıfında sabitleyip; `BireyselFatura` ve `KurumsalFatura` somut sınıflarında faturaya özgü metinsel detayları doldurmak için uygulanmıştır.

---

## Mimari Prensipler (SOLID)
* **Open/Closed Principle (OCP - Açık/Kapalı Prensibi):** Faz 3 kapsamında uygulanan Strategy ve Template Method kalıpları sayesinde; mevcut koda tek bir satır dahi dokunmadan sisteme yepyeni indirim stratejileri (`HaftaSonuIndirimi`) ve yeni fatura türleri kolayca eklenebilir hale getirilmiştir.
* **Dependency Inversion Principle (DIP - Bağımlılıkların Tersine Çevrilmesi):** Sınıflar somut implementasyonlar veya fabrikalar yerine tamamen soyut arayüzlere (Interface) bağımlı kılınarak gevşek bağlı bir yapı elde edilmiştir.

---

## Projeyi Çalıştırma (How to Run)

1. Komut satırından kaynak kodların bulunduğu dizine gidin:
   cd src

2. Tüm Java dosyalarını derleyin:
    javac *.java

3. Ana programı başlatın:
    java Sepet

