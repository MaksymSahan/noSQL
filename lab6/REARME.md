# Покрокова інструкція для виконання лабораторної роботи №6

1. Необхідно створити: акаунт на GCP та машину в Compute Engine
![alt text](https://github.com/MaksymSahan/noSQL/blob/main/lab6/screens/Screenshot_12.png)
2. Заходимо в VPC network та прописуємо Firewall elasic на порті tcp:9200 та kibana tcp:5601
3. Підключаємось до машини та вводимо наступні команди
![alt text](https://github.com/MaksymSahan/noSQL/blob/main/lab6/screens/Screenshot_2.png)
встановлюємо джаву
```
 sudo apt-get install default-jre
 ```
встановлення elastic і запуск
```
sudo apt update
sudo apt install apt-transport-https
wget -qO - https://artifacts.elastic.co/GPG-KEY-elasticsearch | sudo apt-key add -
sudo sh -c 'echo "deb https://artifacts.elastic.co/packages/7.x/apt stable main" > /etc/apt/sources.list.d/elastic-7.x.list'
sudo apt update
sudo apt install elasticsearch
sudo service elasticsearch status
sudo systemctl enable elasticsearch.service
sudo systemctl start elasticsearch.service
```
змінюємо нетворк хост на 0.0.0.0 та діскавері
```
sudo nano /etc/elasticsearch/elasticsearch.yml
```
після цього рестартуємо еластік
```
sudo service elasticsearch restart
```
![alt text](https://github.com/MaksymSahan/noSQL/blob/main/lab6/screens/Screenshot_7.png)
після цього встановлюєм логстеш та кібану
```
sudo apt-get install apt-transport-https
echo "deb https://artifacts.elastic.co/packages/5.x/apt stable main" | sudo tee -a /etc/apt/sources.list.d/elastic-5.x.list
sudo apt-get update
sudo apt-get install logstash
sudo service logstash start
echo "deb http://packages.elastic.co/kibana/7.0/debian stable main" | sudo tee -a /etc/apt/sources.list.d/kibana-7.0.x.list
sudo wget --directory-prefix=/opt/ https://artifacts.elastic.co/downloads/kibana/kibana-7.6.1-amd64.deb
sudo dpkg -i /opt/kibana*.deb
sudo apt-get update
sudo apt-get install kibana
```
редагуємо файл, пропусуємо server.host "0.0.0.0" а також розкоментовуємо порт 5601 для кібани
```
sudo nano /etc/kibana/kibana.yml
```
запускажмо та перевіряємо готовність кібани
```
sudo service kibana start
sudo service kibana status
```
4. Тепер потрібно створити Logic app у Azure та створити connect у Logic app designer та заранити
![alt text](https://github.com/MaksymSahan/noSQL/blob/main/lab6/screens/Screenshot_9.png)
![alt text](https://github.com/MaksymSahan/noSQL/blob/main/lab6/screens/Screenshot_10.png)
5.Після усіх виконаних команд потрібно перейти по екстернал айпі http://34.105.128.129:5601 і http://34.105.128.129:9200
![alt text](https://github.com/MaksymSahan/noSQL/blob/main/lab6/screens/Screenshot_6.png)
![alt text](https://github.com/MaksymSahan/noSQL/blob/main/lab6/screens/Screenshot_11.png)
