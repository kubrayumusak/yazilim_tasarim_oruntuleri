###AI’a Sorulan Prompt

Bu kodda hangi tasarım sorunlarını görüyorsun?
İndirim hesaplama işlemleri için hangi tasarım örüntüsü uygun olur?
Kodun esnekliğini artırmak için nasıl bir yapı kurulabilir?


###AI’ın Verdiği Yanıtın Özeti

AI aşağıdaki problemleri tespit etti:

Sepet sınıfının çok fazla sorumluluk taşıdığını belirtti.
İndirim hesaplama işlemlerinin switch-case yapısıyla yapılmasının Open/Closed Principle’a aykırı olduğunu söyledi.
Yeni indirim türü eklemek için mevcut kodun değiştirilmesi gerektiğini ve indirim algoritmalarının birbirine bağımlı olduğunu belirtti.
Kod tekrarlarının maintainability açısından problem oluşturduğu ifade edildi.

AI ayrıca Strategy Pattern ve Factory Method yaklaşımının birlikte kullanılabileceğini önerdi. Her indirim tipi için ayrı sınıflar oluşturulabileceğini söyledi.


###Benim Uyguladığım Çözüm

Bu fazda Factory Method yaklaşımını uyguladım.

İndirim nesnelerinin oluşturulmasını `IndirimYap` sınıfına taşıyarak nesne oluşturma sorumluluğunu merkezi hale getirdim.

Ayrıca indirim algoritmalarını ayrı sınıflara ayırdım:

* `OgrenciIndirim`
* `EmekliIndirim`
* `BayramIndirim`

Bu sınıflar ortak bir `IndirimStratejisi` interface’ini implement etmektedir.

Bu yapı sayesinde:

* `Sepet` sınıfının sorumluluğu azaldı
* yeni indirim türü eklemek kolaylaştı
* nesne oluşturma işlemleri merkezi hale geldi
* kod daha genişletilebilir bir yapıya dönüştü

Factory Method yaklaşımı ana çözüm olarak kullanılırken, Strategy mantığından da yararlanmış oldum.



### AI ile Benim Çözümüm Arasındaki Farklar

AI hem Strategy Pattern hem de Factory Method yaklaşımını önerdi. Ancak ben proje yapısını daha sade tutmak istediğim için ağırlıklı olarak Factory Method yaklaşımını uygulamayı tercih ettim.

İndirim algoritmalarını ayrı sınıflara ayırarak kodun genişletilebilirliğini artırdım. Ortak bir interface kullanılması sonucu Strategy Pattern benzeri bir yapı da oluşmuş oldu. Ancak temel amacım davranış değiştirmekten çok nesne oluşturma sorumluluğunu `Sepet` sınıfından ayırmaktı.

AI daha kapsamlı bir refactor önerse de ben fazın kapsamına uygun olacak şekilde minimum ama okunabilir bir dönüşüm yapmayı tercih ettim. Özellikle mevcut kod yapısını tamamen değiştirmeden ilerlemeye çalıştım.



