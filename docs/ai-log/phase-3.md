### FAZ-3

## 1. Ne Tartıştık?
Oturumun başında, Faz 3 behavioral örüntü gereksinimlerini karşılamak adına ilk adım olarak sisteme yeni bir indirim kategorisi (stratejisi) eklemek istediğimi söyledim. Önce bu indirim yapısını kurup sistemi görmek, ardından diğer adımları sırayla yapmak istedim.

Ancak AI sırayla yapmak istediğimi anlamayarak ödev gereksinimlerini hatırlattı, tek başına yeni bir indirim eklemenin sadece tek bir örüntüyü (Strategy) besleyeceğini, hocanın ise "en az 2 Behavioral örüntü" istediğini söyledi. 

Yaptığımız ilk planlama tartışmasında ikinci örüntü olarak Observer (Gözlemci) kalıbını eklemeyi düşündük. Ancak kodlama aşamasında Observer yapılarının (SmsGonder, StokYonet) sadece ekrana basit loglar (`System.out.println`) basmaktan öteye geçmediğini, gerçek bir veri tabanı olmadığı için sistemde çok yapay durduğunu fark ettim ve bu yapaylığı ödevimde bulundurmak istemedim.

AI ile yaptığım ikinci beyin fırtınası sonucunda Observer kalıbını sistemden tamamen çıkarmaya ve yerine e-ticaret fatura süreçlerine tam oturan, içinde gerçek matematiksel hesaplamalar barındıran **Template Method Pattern (Şablon Metot Örüntüsü)** yapısına geçmeye karar verdim.

Yaptığımız bu planlama tartışması sonucunda şu iki aşamalı yol haritasına karar verdik:
1. **Strategy Pattern:** Mevcut `Sepet` sınıfındaki `String indirimTipi` bağımlılığını gevşetip, doğrudan arayüz (`IindirimStratejisi`) tabanlı nesne enjeksiyonuna geçeceğiz. Böylece sepet, yeni indirim sınıflarını hiç bilmeden dinamik olarak çalışabilecek. İlk olarak sadece bu indirim stratejisinin eklenmesini tamamlayacağız.
2. **Template Method Pattern:** Fatura hazırlama algoritmasının iskeletini `FaturaSablonu` adında soyut bir sınıfta sabitleyip; `BireyselFatura` ve `KurumsalFatura` alt sınıflarında bu adımların içeriğini gerçek string manipülasyonlarıyla doldurduk.

Ayrıca oturumun sonuna doğru sisteme aynı anda birden fazla indirim türü uygulanabilme özelliği eklemek istediğimi söyledim. Bu gereksinim için Structural faza uyguladığımız Decorator örüntüsünün indirimler için de genişletilebileceğini veya Strategy kalıbının yanına bir Composite Pattern eklenerek birden fazla indirim stratejisinin bir liste halinde sırayla çalıştırılabileceğini teorik olarak analiz ettik. Geliştirme sürecinin sonraki adımlarında bu esnekliği de ekleyeceğim.

## 2. Nasıl İlerledik?
* Kodlamaya geçmeden önce `Sepet` sınıfının mevcut yapısı paylaştım ve teorik olarak nasıl yazacağımızı düşündük.
* İlk olarak sadece yeni bir indirim kategorisi eklemenin mimariyi nasıl etkileyeceği, sepetin nesne üretiminden nasıl soyutlanacağını planladık.
* Kodlama aşamasına geçmeden önce, dökümantasyon düzenini ve mantıksal akışı bu şekilde netleştirmiş olduk.
* Observer kalıbını eklemeye başladım ve yetersiz geldiğini fark ettim ve projede bulundurmamaya karar verdim. Yerine AI önerisiyle Template Method örüntüsünü eklemeye karar verdim.
* Kodlama aşamasında Eclipse üzerinde oluşan constructor eşleşme hatalarını tespit edip parantez ve sınıf hiyerarşisi düzenlemeleriyle kodu başarıyla oluşturduk.

## 3. Kritik Değerlendirme ve Sorular

### AI olmadan bu faz ne kadar sürerdi?
AI olmadan sonraki adımları planlamak, Observer'ın yapaylığını fark edip hızlıca Template Method gibi daha ağır ve kod içeren başka bir behavioral örüntüye geçiş mimarisini tek başıma kurgulamak yaklaşık 3-4 saatlik bir döküman analizi ve refactoring süresi alırdı. AI ile pair programming mantığıyla konuşmak, esnek alternatifleri anında görmemi sağladı.

### AI sizi nerede yanılttı?
AI, benim adım adım ilerleme ve "önce sadece indirim stratejisini ekleyip sonrasına bakarız" şeklindeki kontrollü planımı atlayarak, bir anda tüm fazın kodlarını ve ikinci örüntüyü tek seferde bana yazdırmaya çalıştı. Benim müdahalem ve uyarım sayesinde süreci tekrar benim istediğim gibi planlama aşamasına çektik. 
AI, başlangıçta sadece konsola basit loglar basan Observer tabanlı bir yapı kurmayı önerdi. Ancak projenin mimari gerçekçiliğini ve kalitesini korumak adına bu yapıyı yetersiz buldum ve AI'ın bu yönlendirmesini reddettim. Süreç adımlarını tamamen soyutlayan ve koda gerçek bir işlevsellik katan Template Method tasarımına geçeceğimizi söyledim.
Ayrıca AI, geçmiş hafıza sınırından dolayı Faz 2'deki Pull Request yorumlarımızı yapmadığımızı söyledi ama ben Pull Request yorumlarımı yapmıştım.
Bu durumlar, AI araçlarının mimari fikir vermede harika olduğunu ancak proje takibinde son kontrolün her zaman geliştiricide olması gerektiğini gösterdi.
