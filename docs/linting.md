# Документація з налаштування лінтера та статичного аналізу коду

## Використані інструменти

У цьому проєкті використовується **Checkstyle** для статичного аналізу коду Java.

Додатково рекомендовано використовувати **SpotBugs** для аналізу типів та пошуку потенційних помилок.

---

## Налаштування Checkstyle

### Додавання залежності

У файл `pom.xml` додано залежність:

```xml
<dependency>
    <groupId>com.puppycrawl.tools</groupId>
    <artifactId>checkstyle</artifactId>
    <version>8.41</version>
    <scope>provided</scope>
</dependency>
