#1. AI'a Sorulan Sorular (Promptlar)
"Sepete kargo ücreti ve hediye paketi gibi opsiyonel ekstralar eklemek için Decorator pattern mı kullanmalıyım yoksa Facade mı? Bu iki yapısal örüntü arasındaki fark nedir?"

"Sisteme Ödeme işlemleri eklemek istiyorum ve bunu Decorator olarak entegre etmenin daha mantıklı olduğunu düşünüyorum sen ne dersin?"


#2. Yapay Zekanın Yanıt Özeti
Örüntü Karşılaştırması: AI, Facade'ın karmaşık sistemleri basitleştirmek için, Decorator'ın ise nesneye dinamik sorumluluk eklemek için kullanıldığını söyledi.

Öneri: E-ticaret sepetindeki kargo, sigorta ve ödeme onayı gibi işlemlerin çalışma zamanında (runtime) seçilebilmesi nedeniyle Decorator Pattern'ın Open/Closed prensibine en uygun çözüm olduğunu söyledi.

Adapter Rolü: Farklı kargo firması API'leri gibi dış sistemlerin metot uyumsuzluklarını gidermek için Adapter Pattern'ın bir köprü görevi göreceğini açıkladı.

#3. Uygulanan Çözüm ve Farklılıklar
*ödeme Özelliğini ekleme*

   Karar: Başlangıçta ödeme süreçleri için sadece Adapter düşünülmüş olsa da, süreci bir Decorator olarak kurgulama fikrimi AI ile tartıştım ve bu yöntemi uygulamaya karar verdim.
	
   Nedeni: Ödemeyi bir dekoratör olarak eklemek, sepet nesnesine sadece bir "fiyat" değil, aynı zamanda dinamik bir "davranış" (ödeme yeteneği) kazandırmaktadır. Bu sayede sepeti kargo ücretiyle sarmaladığımız gibi, ödeme onay katmanıyla da sarmalayarak yapısal esnekliği en üst düzeye çıkardık.
   
   Farklılık: Yapay zeka ile tartiştığım üzere, bu yöntemin Single Responsibility prensibini korumak adına çok daha temiz bir mimari sunduğunu teyit ettim. AI'ın sadece sunduğu öneriyi kabul etmek yerine, kendi tasarım fikrimi teknik bir temele oturtarak projeye dahil ettim. 



*kargo ücreti ekleme*

Karar: Başlangıçta Faz 1'de sistemden çıkarılan kargo hesaplama mantığını ve yeni eklenecek ödeme süreçlerini Decorator Pattern kullanarak yapısal birer katmana dönüştürmeye karar verdim.

Nedeni: Kargo ücretini ve ödemeyi birer dekoratör olarak kurgulamak, temel sepet nesnesine hem maliyet (fiyat) hem de dinamik bir "davranış" (sorumluluk) kazandıracaktır. Özellikle kargonun Faz 1'deki if-else yapısından kurtarılıp bir dekoratör haline getirilmesi, sistemi yeni ek masraflara (sigorta, hızlı teslimat vb.) karşı tamamen esnek hale getirecektir.

Farklılık: Yapay zeka başlangıçta banka entegrasyonu için sadece Adapter üzerinde durmuştu. Ancak kargo ücretlendirmesindeki "katılığı" gidermek için düşündüğüm Decorator çözümünü ödeme sürecindede uygulamak istedim. Bu sayede sadece dış sistemleri bağlamakla kalmayıp (Adapter), bu sistemlerin getirdiği sorumlulukları da sepeti sarmalayarak (Decorator) yönetmeye karar verdim. AI'ın standart önerisini, projenin özel ihtiyaçlarına göre genişleterek kendi mimari çözümümü oluşturdum.




