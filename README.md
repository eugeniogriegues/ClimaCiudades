# ClimaCiudades

Android con KOTLIN – Examen Final realizado en Abril del año 2021 con el cual obtuve mi certificacion

Vamos a generar una aplicación que consume un servicio del Clima y nos va a permitir agregar ciudades a un listado. 
Cuando el usuario hace tap sobre el listado, aparecerá un Toast con la temperatura de este lugar.

1) Crear una aplicación en cuyo MainActivity agregar un ListView.

2) Agregar a esa Activity un menú con una opción llamada “Agregar Ciudad”.

3) Agregar otra Activity llamada NuevaCiudad que tendrá un EditText y un botón.

4) En el OnClickItemListener del ListView, vamos a llamar a una clase AsyncTask que consultará el servicio de OpenWeather. 
Esa misma clase realizará la lectura del JSON del clima y generar un Toast en pantalla con la temperatura.

5) Cada vez que el usuario agregue una ciudad la misma aparecerá en el listado. 
Es decir, que el usuario presiona en el menú “Agregar Ciudad”, se abre entonces la actividad 
que le presenta el campo para agregar el nombre de la ciudad y el botón de guardado.
Cuando se retorna de esa actividad, la ciudad aparecerá agregada en el listado.

6) Para que no se pierda la información de las ciudades ingresadas cuando se cierre la aplicación, 
utilizar SharedPreferences para persistir las ciudades agregadas. 
Significa que cuando la aplicación se inicia, debe cargar el ListView con las ciudades ingresadas.
