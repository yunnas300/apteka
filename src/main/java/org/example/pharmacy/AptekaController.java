/**
 * REST-контролер для керування аптекою.
 * Реалізує REST API для клієнтів.
 */
@RestController
@RequestMapping("/api/apteka")
public class AptekaController {

    /**
     * Повертає список доступних медикаментів.
     *
     * @return список обʼєктів Medicine
     */
    @GetMapping("/available")
    public List<Medicine> getAvailableMedicines() { ... }
}
