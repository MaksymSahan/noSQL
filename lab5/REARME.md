# Покрокова інструкція для виконання лабораторної роботи №5

1. Необхідно створити:
  **акаунт на Azure**,
  **ресурс групу**,
  **Azure Redis for Cache**,
  **Event hub**
2. Заходимо у наш redis у якому нам потрібно взнати Primary key і Primary connection string для запису стратегією redis
![alt text](https://github.com/MaksymSahan/noSQL/blob/main/lab5/screens/Screenshot_4.png)
3. Створюємо Evens Space, обираємо групу у якій буде наш сервіс, називаємо Namespace name, обираємо найближчий регіон, Pircing tier Стандартний -> Review + Create
![alt text](https://github.com/MaksymSahan/noSQL/blob/main/lab5/screens/Screenshot_5.png)
4. Зайшовши в наш Event Hub потрібно нам створити policy - це можна зробити в лівому меню в розділі Settings -> Shared access policies -> + Add
![alt text](https://github.com/MaksymSahan/noSQL/blob/main/lab5/screens/Screenshot_1.png)
5. У новому вікні обираєм назву policy та усі права натиснувши на Manage. Клікнувши на наш policy ми можемо отримати Primary key, Secondary key а також конекшени для них, які пригодяться нам для запису по стратегії eventHub
6. Запустивши код програми написаний мовою Java потрібно записати дані з dataset`y, що ми зробимо за допомогою Postman
![alt text](https://github.com/MaksymSahan/noSQL/blob/main/lab5/screens/Screenshot_6.png)
7. У програмі Postman ми створюємо новий POST запит прописавши url'у у якій прописуємо localhost, сервер порт який можна дізнатись з файлу application.properties у дерикторії resources, і /url для виконання коду у файлі URLController @POSTMapping, для запису нам потрібно також у Headers створити новий Header зі значеннями Key = "Contetn-Type" Value = "application/json", а у розділі Body прописуєм запит raw, у якому url`y датасету і нашу стратегію на вибір які ми прописали у LogService класі
8.Щоб перевірити чи значення були отримані:
Event Hub > Entities > Event hubs > ваш Event Hub > Features > Proccess Data > Explore ( можливо буде необхідно почекати 1-2 хв).

Redis Cache > Console > введіть команду hgetAll ConsoleLog

