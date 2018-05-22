
    W bazie danych przechowywane są obiekty CarPlate:
   
    CarPlate
     Long id;
     LocalDateTime localDateTime;
     String plate;

    API:

    **POST

        **Zapisywanie placeholdera do bazy:**

            http://localhost:8080/savePlaceholder
    
            Body: LocalDateTime w formie stringa
            Result: id zapisanego placeholdera

        **Zapisywanie Numeru tablicy (po odczycie):**

        http://localhost:8080/savePlate/{id}
        gdzie id to id przetrzymywanego placeholdera
    
            Body: numer tablicy jaki chcemy zapisać dla
                danego id
            Result: id nadpisanej encji
            
    **GET
        **Pobieranie wszystkich tablic z bazy:**
        
        http://localhost:8080/all
        zwraca wszystkie obiekty CarPlate z bazy
        
        **Pobieranie obiektu o danym id**
         http://localhost:8080/{id}
         
         pobiera obiekt CarPlate o id = id z bazy
    