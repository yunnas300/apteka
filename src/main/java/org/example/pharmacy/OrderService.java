/**
 * Сервіс для роботи з замовленнями.
 * Відповідає за бізнес-логіку замовлень.
 */
public class OrderService {

    /**
     * Повертає замовлення за ідентифікатором.
     *
     * @param id унікальний ідентифікатор замовлення
     * @return обʼєкт Order
     * @throws OrderNotFoundException якщо замовлення не знайдено
     */
    public Order getOrderById(Long id) throws OrderNotFoundException { ... }
}
