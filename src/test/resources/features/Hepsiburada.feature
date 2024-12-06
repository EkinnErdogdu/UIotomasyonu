Feature: Hepsiburada'da Apple 13,2 inç tablet ekleme

  Scenario: En yüksek fiyatlı Apple tabletin sepete eklenmesi ve fiyat doğrulaması
    Given Kullanıcı "https://www.hepsiburada.com/" adresine gider
    When Kullanıcı "Tüm Kategoriler -> Elektronik -> Tablet" kategorisine gider
    And Kullanıcı "Marka -> Apple" ve "Ekran Boyutu -> 13,2 inç" filtrelerini seçer
    And Kullanıcı çıkan sonuçlardan en yüksek fiyatlı ürüne tıklar
    And Kullanıcı "Sepete Ekle" butonuna tıklar
    Then Ürün sepete eklenir ve fiyatının ürün detay sayfasıyla aynı olduğunu doğrular

