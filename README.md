Встановлення проєкту
Клонуйте репозиторій:
git clone https://github.com/yunnas300/apteka.git

Встановіть необхідні залежності та інструменти:
Java 21+


Maven 3.9+


PostgreSQL


(за потреби) Docker


Налаштуйте файл application.properties або application.yml відповідно до ваших параметрів БД.
Зберіть та запустіть проєкт:
./mvnw spring-boot:run

Основні команди Git:
git pull        # отримати останні зміни
git add .       # додати всі зміни до коміту
git commit -m "Опис змін"  # зафіксувати зміни
git push        # відправити зміни на сервер

3. Документація DevOps
   Розгортання у production (docs/deployment.md)
   Вимоги до серверного середовища:
   ОС: Ubuntu Server / Windows Server


Мінімальні характеристики: 4 CPU, 8GB RAM, 20GB SSD


Встановлені: Java 21, Maven, PostgreSQL, Docker (за потреби)


Розгортання додатка
Зібрати білд:
./mvnw clean package

Передати .jar файл на сервер через SCP:
scp target/apteka.jar user@server:/path/to/app

Запустити застосунок:
java -jar /path/to/app/apteka.jar

Налаштувати права доступу та перевірити логування.

Оновлення системи (docs/update.md)
Створити резервну копію поточної версії.


Перевірити сумісність нового білда.


Зупинити роботу застосунку:


pkill -f apteka.jar

Завантажити новий білд і замінити старий.


Перезапустити застосунок.


Перевірити логи на відсутність помилок:


tail -f /var/log/apteka.log


Резервне копіювання (docs/backup.md)
Резервне копіювання коду:
git clone --mirror https://github.com/yunnas300/apteka.git

Бекап бази даних:
pg_dump -U postgres apteka > backup_$(date +%F).sql

Автоматизація процесу через скрипти (backup.sh):
#!/bin/bash
tar -czvf backup_$(date +%F).tar.gz /path/to/app/
pg_dump -U postgres apteka > db_backup_$(date +%F).sql

Скрипт автоматичного розгортання (deploy.sh):
#!/bin/bash
scp target/apteka.jar user@server:/path/to/app/
ssh user@server 'pkill -f apteka.jar && java -jar /path/to/app/apteka.jar &'
