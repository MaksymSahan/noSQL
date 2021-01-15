# Покрокова інструкція для виконання лабораторних робіт №8-9

1. В Azure створюємо нову ресурс групу або обираємо вже наявну
2. Створюємо DataBricks та обираємо free tier
![alt text](https://github.com/MaksymSahan/noSQL/blob/main/lab8-9/screens/Screenshot_9.png)
3. Натискаємо Launch та створюємо кластер
![alt text](https://github.com/MaksymSahan/noSQL/blob/main/lab8-9/screens/Screenshot_1.png)
4. Коли кластер зараниться, відкриваємо його, натискаємо Libraries та докачуємо бібліотеку
![alt text](https://github.com/MaksymSahan/noSQL/blob/main/lab8-9/screens/Screenshot_2.png)
5. Переходимо в ажур та ствоюємо або обираємо наявний івент хаб. Для нього створюємо полісі
![alt text](https://github.com/MaksymSahan/noSQL/blob/main/lab8-9/screens/Screenshot_11.png)
6. Наступним кроком буде створення Azure Storage Account. Для цього в полі пошуку ажур порталу Azure Storage Account. Переходимо по ньому.
Натискаємо на Create. Заповнюємо поля. В вкладці Advanced опускаємось до Data Lake Storage Gen2 та клікаємо на Enabled навпроти опції Hierarchical namespace. Після цього підтверджуємо створення.
Переходимо в створенний сторедж аккаунт, в боковому меню опускаємось до Data Lake Storage > Containers. Натискаємо на кнопку створення контейнера, даємо йому назву, а в Public Access Level обираємо Container.
![alt text](https://github.com/MaksymSahan/noSQL/blob/main/lab8-9/screens/Screenshot_12.png)
7. Заходимо в Azure AD > App registration > кнопка New Registation > даємо назву. Після цього нас перекине на новостворенний ресурс. В боковому меню спускаємось до Certificates & Secrets > в області Client Secrets > New client secret > даємо йому назву. 
![alt text](https://github.com/MaksymSahan/noSQL/blob/main/lab8-9/screens/Screenshot_13.png)
8. Переходимо на домашню сторінку датабрікс, натискоємо new notebook та створюємо 2 файли для пайтона і скали
![alt text](https://github.com/MaksymSahan/noSQL/blob/main/lab8-9/screens/Screenshot_3.png)
![alt text](https://github.com/MaksymSahan/noSQL/blob/main/lab8-9/screens/Screenshot_4.png)
![alt text](https://github.com/MaksymSahan/noSQL/blob/main/lab8-9/screens/Screenshot_10.png)
9. Завантажуємо Microsoft Azure Storage Explorer та логуємось у свій акаунт
![alt text](https://github.com/MaksymSahan/noSQL/blob/main/lab8-9/screens/Screenshot_5.png)
![alt text](https://github.com/MaksymSahan/noSQL/blob/main/lab8-9/screens/Screenshot_6.png)
![alt text](https://github.com/MaksymSahan/noSQL/blob/main/lab8-9/screens/Screenshot_7.png)
10. Запускамо скалівський код, одночасно з цим потрібно відправити пост запит на 5000 порт за допомогою postman, curl або чогось схожого:
![alt text](https://github.com/MaksymSahan/noSQL/blob/main/lab8-9/screens/Screenshot_8.png)


