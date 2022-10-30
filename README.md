# Pathfinding API

Greetings! This API allows to find the shortest path between two points.

It uses the A* algorithm.

At this point the application support pathfinding on 2d map. You can also specify walls and see how the shortest path will be changed.

The UI for this backend application you can find [here](https://github.com/aonufrei/pathfinding-ui).

## How to run the app

After cloning the repository, navigate into the root folder and execute following command:
```
./mvnw spring-boot:run
```

The application is now live and can be accessed [http://localhost:8080/](http://localhost:8080/).

The Swagger url: [http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html)

You can also run the UI react application and see how it works visually ([link to the repository](https://github.com/aonufrei/pathfinding-ui)).