package org.example;

import java.util.ArrayList;
import java.util.Optional;
import java.util.Scanner;

public class MenuService {
    private final Cart cart = new Cart();
    private final User user = new User("Настя", new ArrayList<>());
    private final Product product1;
    private final Product product2;
    private final Product product3;

    public MenuService() {

        Category electronics = new Category(1, "Електроніка");
        Category smartphones = new Category(2, "Смартфони");
        Category accessories = new Category(3, "Аксесуари");

        product1 = new Product(1, "Ноутбук", 19999.99, "Високопродуктивний ноутбук", electronics);
        product2 = new Product(2, "Смартфон", 12999.50, "Смартфон з великим екраном", smartphones);
        product3 = new Product(3, "Навушники", 2499.00, "Бездротові навушники", accessories);
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            printMenu();

            int choice = scanner.nextInt();
            switch (choice) {
                case 1 -> showProducts();
                case 2 -> addProduct(scanner);
                case 3 -> System.out.println(cart);
                case 4 -> makeOrder();
                case 5 -> removeProduct(scanner);
                case 6 -> search(scanner);
                case 7 -> showOrderHistory();
                case 0 -> {
                    System.out.println("Дякуємо, що використовували наш магазин!");
                    return;
                }
                default -> System.out.println("Невідома опція. Спробуйте ще раз.");
            }
        }
    }

    private void showProducts() {
        System.out.println(product1);
        System.out.println(product2);
        System.out.println(product3);
    }

    private void addProduct(Scanner scanner) {
        System.out.println("Введіть ID товару для додавання:");
        int id = scanner.nextInt();
        if (id == 1) cart.addProduct(product1);
        else if (id == 2) cart.addProduct(product2);
        else if (id == 3) cart.addProduct(product3);
        else System.out.println("Товар з таким ID не знайдено");
    }

    private void makeOrder() {
        if (cart.getProducts().isEmpty()) {
            System.out.println("Кошик порожній.");
        } else {
            Order order = new Order(cart);
            user.addOrder(order);
            System.out.println("Замовлення оформлено:");
            System.out.println(order);
            cart.clear();
        }
    }

    private void removeProduct(Scanner scanner) {
        System.out.println("Введіть ID товару для видалення:");
        int idRemove = scanner.nextInt();
        if (idRemove == 1) cart.removeProduct(product1);
        else if (idRemove == 2) cart.removeProduct(product2);
        else if (idRemove == 3) cart.removeProduct(product3);
        else System.out.println("Товар з таким ID відсутній у кошику.");
    }

    private void search(Scanner scanner) {
        System.out.println("Введіть назву товару або категорії:");
        scanner.nextLine(); // щоб «з’їсти» Enter після nextInt()
        String keyword = scanner.nextLine();

        Optional<Product> found = SearchService.searchProduct(cart, keyword);
        if (found.isPresent()) {
            System.out.println("Знайдено товар: " + found.get());
        } else {
            System.out.println("Нічого не знайдено за запитом: " + keyword);
        }
    }

    private void showOrderHistory() {
        if (user.getOrderHistory().isEmpty()) {
            System.out.println("У вас ще немає замовлень.");
        } else {
            System.out.println("Історія замовлень:");
            for (Order order : user.getOrderHistory()) {
                System.out.println(order);
                System.out.println("-------------------");
            }
        }
    }

    private void printMenu() {
        System.out.println("\nВиберіть опцію:");
        System.out.println("1 - Переглянути список товарів");
        System.out.println("2 - Додати товар до кошика");
        System.out.println("3 - Переглянути кошик");
        System.out.println("4 - Зробити замовлення");
        System.out.println("5 - Видалити товар з кошику");
        System.out.println("6 - Шукати товари");
        System.out.println("7 - Переглянути історію замовлень");
        System.out.println("0 - Вийти");
    }
}