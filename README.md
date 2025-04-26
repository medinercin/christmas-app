Proje, Android platformunda Java dili kullanılarak geliştirilmiştir. Uygulamanın temel işlevleri, yılbaşı 
geri sayımı, Advent Takvimi ve yapılacaklar listesi olmak üzere üç ana modülden oluşmaktadır.  
2.1. Kullanılan Teknolojiler 
Android Studio: Geliştirme ortamı olarak kullanılmıştır. Aktif olarak kullanılan son sürüm 
kullanılmıştır. Kullanılan sürüm LadyBug 1.2.2024’ dür. Sanal zihaz emülatörü olarak ise “Medium 
Phone API 35” kullanılmıştır . Android Studio’ nun sağlamış olduğu Java dili kullanılarak kodlar 
yazılmıştır. 
Veritabanı: Veritabanı olarak Android Studio ile bağlantılı kullanılabilen SQLite kullanılmıştır. SQLite, 
proje kapsamında yer alan “Yapılacaklar Listesi” kapsamındaki aktivite işlemleri için kullanılmıştır. 
XML: Arayüzde karşımıza çıkan sayfaların tasarımlar .xml uzantılı kaynak dosyaları ile sağlanmıştır. 
Uygulama boyunca hem sürükl - bırak yöntemiyle hem de layout kodları yazılarak hazırlanmıştır. 
2.2. Uygulama Modülleri 
Uygulama kapsamında toplamda 4 aktivite oluşturulmuştur aşağıda her aktivitenin adı ve işlevleri yer 
almaktadır. 
2.2.1 MainActivity 
MainActivity kapsamında temel olarak sayfaya hoş geldin minvalinde görsel ve yazılar kullanılmıştır. 
Ayrıca yılbaşı temasına uyması açısından 1 Ocak 2025 yılına bir geri sayı eklenmiştir. Diğer tasarlanan 
aktiviteler arasında bağlantı kurması açısından bizi 2 tane buton karşılamaktadır. Bunlardan biri bizi 
advent calender aktivitesine diğeri ise yapılacaklar listesine götürecektir. Bu sebeple MainActivity 
sınıfımız ile activity_main.xml dosyalarımız ile atanan id’ lerle iletişim sağlanıp uygulama arası geçişler 
(intent) sağlanmış olur. 
2.2.2 AdventCalenderActivity 
Bu aktivite ile kullanıcı günlerin olduğu butonlara tıkladığında gün resmi kaybolup aynı yerde hediye 
resmi ile karşılaşmaktadır.Anasayfadan basılan buton yardımıyla gelinen bu aktivitemizle 1 Aralık 
tarihinden 25 Aralık (christmas) tarihine kadar 25 tane ImageButton yerleştirilmiştir. Yerleştirme 
yapılırken activity_advent_calender.xml dosyasında tableLayout kullanılarak tasarlanmıştır. 
Uygulamanın dinamik olması ve yerden tasarruf sağlaması açısından ImageButton kullanılmıştır. 
Örneğin kullanıcı 5. Gündeki hediyesini görmek istediğinde üzerinde 5 yazan fotoğrafa tıkladığında 
aynı butonda hediye resmi çıkmaktadır. Butona tekrardan bastığında ise 5. Gün resmi ekrana gelip 
hediye gizlenmektedir. 
2.2.3 DatabaseHelper 
Uyglamamda kullanıcıların yapacakları görevleri oluşturmak, silmek ve listelemek amacıyla SQLite 
veri tabanı kullanılmıştır. Bu işlemleri kolaylaştırmak ve tekrar eden kodlardan kaçınmak için bir 
DatabaseHelper sınıfı geliştirilmiştir.Bu sınıf, Android uygulamalarında SQLite ile çalışmayı sağlayan 
bir yardımcı sınıftır. Bu sınıf sayesinde, SQLite veritabanı üzerinde işlem yapma süreçleri 
basitleştirilmiştir 
Yılbaşı uygulamasında, CRUD işlemleri kullanıcıların görevlerini kolayca yönetebileceği şekilde 
düzenlenmiştir. Bu işlemler arasında ekleme, okuma ve silme bulunmaktadır. Kullanıcı, yeni bir görev 
ekleyebilir, mevcut görevleri liste hâlinde görüntüleyebilir ve üzerine tıklayarak bir görevi silebilir. 
Ekleme (Create) işlemi, kullanıcının yazdığı görevlerin INSERT sorgusu kullanılarak SQLite 
veritabanına eklenmesini sağlar. Kullanıcı, görevini yazdıktan sonra "Ekle" butonuna tıklayarak görevi 
veri tabanına ekler. 
Okuma (Read) işlemi, kayıtlı tüm görevlerin SELECT sorgusu ile okunmasını içerir. Bu görevler bir 
ListView üzerinde kullanıcıya sunulur. Kullanıcı, eklediği tüm görevleri bu liste üzerinden görebilir.  
2 
Silme (Delete) işlemi, kullanıcının ListView üzerindeki bir göreve tıklayarak ilgili görevi veri 
tabanından kaldırmasını sağlar. Bu işlem sırasında, seçilen görev DELETE sorgusu ile veri tabanından 
silinir ve görev anında listeden kaldırılır.Bu işlemleri gerçekleştirmek için  
DatabaseHelper sınıfında çeşitli metotlar tasarlanmıştır. Örneğin, addTask(String task) metodu, yeni bir 
görev eklemek için kullanılırken, getAllTasks() metodu kayıtlı tüm görevlerin okunmasını sağlar. 
deleteTask(int id) metodu ise kullanıcının seçtiği bir görevi veri tabanından siler. Yılbaşı 
uygulamasında, görev yönetimi işlemleri ListView bileşeni üzerinden kolayca gerçekleştirilmektedir. 
2.2.4 ToDoActivity 
Bu activite ile menü kullanımı uygulamadaa gösterilmek istenmiştir. ActionBar üzerinden artı ikonuna 
tıklanarak yeni bir aktiviteye geçiş yapmak hedeflenmiştir. Sayfa tasarımı oldukça sadedir, yalnızca 
ekleme yapılacak sayfaya geçiş yapmaktadır. Ve bu işlemi res klasöründe eklenen menü klasörünün 
altındaki menü_main.xml dosyası ile ToDoActivity sınıfının onCreateOptionsMenu() ve 
onOptionsItemSelected() metotlarının kullanılmasıyla sağlanmaktadır. 
2.2.5 AddTaskActivity 
Bu aktivite ile veritabanı işlemleri kullanılmaktadır. Oldukça sade bir tasarım tercih edilmiştir. Edittext, 
Button ve ListView kullanılmıştır. Edittext içine yazılan görevler ekle butonuna tıklandıktan sonra 
ListView içinde gösterilmektedir. ListView’ a eklenen yılbaşı görevlerinin üzerine tıklanınca ise bizi 
güncelleme ve silme işlemlerinin olduğu options menü karşılıyor. Bu sayede AlertDialog yardımıyla 
eklenen görevler düzenlenir ve options menü yardımıyla çıkan sil özelliği ile de eklenen görevler 
veritabanından silinmektedir.
 
