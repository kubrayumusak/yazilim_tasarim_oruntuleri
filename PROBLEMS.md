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


