/**
 * Сервіс для роботи з медикаментами.
 * Забезпечує CRUD-операції над сутністю Medicine.
 */
public class MedicineService {

    /**
     * Повертає список усіх медикаментів.
     *
     * @return список обʼєктів Medicine
     */
    public List<Medicine> getAllMedicines() { ... }

    /**
     * Додає новий медикамент.
     *
     * @param medicine обʼєкт Medicine для створення
     * @return створений Medicine
     */
    public Medicine createMedicine(Medicine medicine) { ... }
}
