# Parking-Service Server


## Using Lombok with IntelliJ

IntelliJ does not support Project Lombok by default. You have to install following plugin to your IDE: Lombok Plugin.
If you want to run application from Intellij or run debug, you have to do following steps:
Go to File>Settings>Build, Execution, Deployment>Compiler>Annotation Processors and check Enable annotation
processing box.
Rebuild project: Build>Rebuild Project

## API:

    **POST

        **Upload image in order to process**

            POST /plates/upload
                Body: MultipartFile
                Result: id of saved entity

    **PUT

        **Save date of car exit**

            PUT /plates/{id}/exit
                Param: id of entity
                Body: LocalDateTime as string
                Result: id of updated entity

    **GET

        **Fetch all plates**

             GET /plates
                Result: CarPlate[]

        **Fetch single plate**

            GET /plates/{id}
                Result: CarPlate
