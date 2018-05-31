
    W bazie danych przechowywane są obiekty CarPlate:
   
    CarPlate
     Long id;
     LocalDateTime localDateTime;
     String plate;

    API:

    **POST

        **Zapisywanie placeholdera do bazy:**

            POST /plates 
    
            Body: LocalDateTime w formie stringa
            Result: id zapisanego placeholdera

    **PUT
        **Zapisywanie Numeru tablicy (po odczycie):**

        PUT /plates/{id}
        gdzie id to id przetrzymywanego placeholdera
    
            Body: numer tablicy jaki chcemy zapisać dla
                danego id
            Result: id nadpisanej encji
            
    **GET
        **Pobieranie wszystkich tablic z bazy:**
        
         GET /plates
         zwraca wszystkie obiekty CarPlate z bazy
        
        **Pobieranie obiektu o danym id**
         GET /plates/{id}
         
         pobiera obiekt CarPlate o id = id z bazy
 ``