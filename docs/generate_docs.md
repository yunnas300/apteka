# Лабораторна робота №5: Документування коду

**Виконав:** Некроєнко Олександр  
**Група:** ІН-13

## Мета роботи

Ознайомитись із сучасними підходами та інструментами документування програмного коду, оволодіти практичними навичками створення якісної документації для власного проєкту з урахуванням особливостей Java та Spring Boot.

**Комміт із виконанням лабораторної:**  
https://github.com/yunnas300/apteka/commit/21e8253d6328c01ae6a5db5b8bffefd887f34993

## 1. Огляд стандартів документування для Java

**Стандарт:**  
Основним стандартом документування для Java є **Javadoc**. Він дозволяє документувати класи, методи, конструктори та змінні за допомогою спеціальних коментарів у форматі `/** ... */`.

**Оформлення:**
- Опис класу або методу
- Параметри `@param`
- Повернення значення `@return`
- Виключення `@throws`
- Інше: `@see`, `@author`

## 2. Інструменти для автоматичної генерації документації

- **Javadoc** — вбудований інструмент Java для генерації HTML-документації.
- **Swagger (SpringDoc OpenAPI)** — для документування REST API в Spring Boot.
- **Checkstyle** — перевірка якості стилю коду та коментарів.
- **AsciiDoc/Markdown** — для текстових інструкцій.

## 3. Документування коду в проєкті

### 3.1 Приклади документації трьох класів (src/main/java/com/apteka/service/)

**ProductService.java**
```java
/**
 * Сервіс для роботи з товарами.
 * Забезпечує CRUD-операції над сутністю Product.
 */
public class ProductService {

    /**
     * Повертає список усіх товарів.
     *
     * @return список обʼєктів Product
     */
    public List<Product> getAllProducts() { ... }

    /**
     * Створює новий товар.
     *
     * @param product обʼєкт Product для створення
     * @return створений Product
     */
    public Product createProduct(Product product) { ... }
}
