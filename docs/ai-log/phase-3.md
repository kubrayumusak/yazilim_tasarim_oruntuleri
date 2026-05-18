### FAZ-3

## 1. Ne Tartıştık?
Oturumun başında, Faz 3 behavioral örüntü gereksinimlerini karşılamak adına ilk adım olarak sisteme sadece yeni bir indirim kategorisi (stratejisi) eklemek istediğimi söyledim. Önce bu indirim yapısını kurup sistemi görmek, ardından diğer adımları sırayla yapmak istedim.

Ancak AI ödev gereksinimlerini hatırlatarak tek başına yeni bir indirim eklemenin sadece tek bir örüntüyü (Strategy) besleyeceğini, hocanın ise "en az 2 Behavioral örüntü" istediğini söyledi. 

Yaptığımız bu planlama tartışması sonucunda şu iki aşamalı yol haritasına karar verdik:
1. **Strategy Pattern:** Mevcut `Sepet` sınıfındaki `String indirimTipi` bağımlılığını gevşetip, doğrudan arayüz (`IIndirimStratejisi`) tabanlı nesne enjeksiyonuna geçeceğiz. Böylece sepet, yeni indirim sınıflarını hiç bilmeden dinamik olarak çalışabilecek. İlk olarak sadece bu indirim stratejisinin eklenmesini tamamlayacağız.
2. **Observer Pattern:** İndirim yapısını başarıyla çözdükten sonra, ikinci adım olarak sepet onaylandığında kargo, SMS ve stok sistemlerine haber verecek bir gözlemci mekanizmasını ayrıca tartışıp ekleyeceğiz.

Ayrıca oturumun sonuna doğru sisteme aynı anda birden fazla indirim türü uygulanabilme özelliği eklemek istediğimi söyledim. Bu gereksinim için Structural faza uyguladığımız Decorator örüntüsünün indirimler için de genişletilebileceğini veya Strategy kalıbının yanına bir Composite Pattern eklenerek birden fazla indirim stratejisinin bir liste halinde sırayla çalıştırılabileceğini teorik olarak analiz ettik. Geliştirme sürecinin sonraki adımlarında bu esnekliği de ekleyeceğim.

## 2. Nasıl İlerledik?
* Kodlamaya geçmeden önce `Sepet` sınıfının mevcut yapısı paylaştım ve teorik olarak nasıl yazacağımızı düşündük.
* İlk olarak sadece yeni bir indirim kategorisi eklemenin mimariyi nasıl etkileyeceği, sepetin nesne üretiminden nasıl soyutlanacağını planladık.
* Kodlama aşamasına geçmeden önce, dökümantasyon düzenini ve mantıksal akışı bu şekilde netleştirmiş olduk.

## 3. Kritik Değerlendirme ve Sorular

### AI olmadan bu faz ne kadar sürerdi?
AI olmadan sonraki adımları planlamak, ödev dökümanındaki tüm behavioral gereksinimleri tek başıma süzüp doğru bir kombinasyon (Strategy + Observer) seçmek yaklaşık 1-2 saatlik bir döküman analizi ve mimari kurgu süresi alırdı. AI ile pair programming mantığıyla konuşmak, neyi hangi sırayla yapmam gerektiği konusunda bana hızlı bir yol haritası çıkardı.

### AI sizi nerede yanılttı?
AI, benim adım adım ilerleme ve "önce sadece indirim stratejisini ekleyip sonrasına bakarız" şeklindeki kontrollü planımı atlayarak, bir anda tüm fazın kodlarını ve ikinci örüntüyü tek seferde bana yazdırmaya çalıştı. Benim müdahalem ve uyarım sayesinde süreci tekrar benim istediğim gibi planlama aşamasına çektik. Ayrıca AI, geçmiş hafıza sınırından dolayı Faz 2'deki Pull Request yorumlarımızı yapmadığımızı söyledi ama ben Pull Request yorumlarımı yapmıştım. Bu durum, AI araçlarının mimari fikir vermede harika olduğunu ancak proje takibinde son kontrolün her zaman geliştiricide (bende) olması gerektiğini gösterdi.

