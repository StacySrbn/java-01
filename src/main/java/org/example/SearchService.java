package org.example;

import java.util.Optional;

public class SearchService {

    public static Optional<Product> searchProduct(Cart cart, String keyword) {
        return cart.getProducts().stream().filter(product -> product.getName().equalsIgnoreCase(keyword)
                        || product.getCategory().getName().equalsIgnoreCase(keyword))
                .findFirst();
    }
    //Optional може містити або знайдений Product, або бути порожнім Optional.empty(), якщо товар не знайдено
    //Це допомагає уникати NullPointerException

    //.stream() Перетворює список у потік Stream<Product>.
    //Дає змогу зручно обробляти колекції фільтрувати
    //.findFirst() знаходить перший елемент у потоці, який пройшов filter()
}
