<h1 align="center">API для сохранения событий и получения статистики VK</a> 
<h2 align="center">Руководство по запуску публичного API и его тестирования</h2>
<h3>Задание</h3>
<p>  <strong>Сделать набор API-методов для сохранения событий и получения статистики.</strong>
  
  <br>Первый метод должен принимать в качестве входных параметров название события и статус пользователя (авторизован или нет). Затем сервер должен добавить вспомогательную информацию и сохранить событие. В качестве хранилища можно использовать mysql/mongo/postgre/sqlite/etc
        
  Второй метод должен позволять фильтровать (по дате и названию события обязательно) и получать агрегированную информацию (одна из трех агрегация за раз) в формате JSON:
</p>
<ul>
 <li>Счетчики конкретных событий</li>
 <li>Счетчики событий по пользователю</li>
 <li>Счетчик событий по статусу пользователя</li>
</ul>

<h4>Ссылка на рабочее публичное API:</h4>

<p><a href="https://eventmanager.cfapps.us10-001.hana.ondemand.com/events">event-mahager/events</a></p>
<p>Ссылка на сай выглядит так: </p>

`https://eventmanager.cfapps.us10-001.hana.ondemand.com`
<p>Если к ней добавить `/events`, то получим список всех событий :</p>

`https://eventmanager.cfapps.us10-001.hana.ondemand.com/events`
<p>Способ фильтрации событий по : 
<ul>
 <li>имени (в примере name = "open")</li>
  
 `https://eventmanager.cfapps.us10-001.hana.ondemand.com/events/filtered_by_name/open`
 <li>дате в формате 'YYYY-MM-DD'</li>
 
  `https://eventmanager.cfapps.us10-001.hana.ondemand.com/events/filtered_by_date/{date}`
</ul>
</p>

<p>Получение агрегированной информации: 
<ul>
 <li>счетик событий по IP</li>
  
 `https://eventmanager.cfapps.us10-001.hana.ondemand.com/events/count_by_ip/{ip}`
 <li>счетик событий по имени</li>
  
 `https://eventmanager.cfapps.us10-001.hana.ondemand.com/events/count_by_name/{name}`
  <li>счетик событий по статусу пользователя</li>
  
 `https://eventmanager.cfapps.us10-001.hana.ondemand.com/events/count_by_state/{state}`
</ul>
</p>

</p>Пример отображения данных на странице</p>
<img src="EventManagerSpring\img\Example.png">

</p>Пример сохранения нового события с помощью сервиса Postman</p>
<img src="EventManagerSpring\img\Post.png">
