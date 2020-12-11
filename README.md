## Dynamic queries in Quill

This simple programs shows runtime overhead for dynamic queries in [quill](https://github.com/getquill/quill). Executing the same query as dynamic one can be 15x longer in Scala 2.13 and 45x longer in Scala 2.12 (comparing to static query).

Program executes simple [akka-http](https://github.com/akka/akka-http) server with the two endpoints - one invoking static query and second one invocing the same query as dynamically (aka dynamic query). [Sttp](https://github.com/softwaremill/sttp) is used to invoke endpoints.

## Run

Using sbt:
```
$ sbt run
```

## Results

### Scala 2.12
```
time of request with static query 1: 552ms
time of request with static query 2: 19ms
time of request with static query 3: 18ms
time of request with static query 4: 15ms
time of request with static query 5: 17ms
time of request with static query 6: 16ms
time of request with static query 7: 16ms
time of request with static query 8: 16ms
time of request with static query 9: 16ms
time of request with dynamic query 1: 1552ms
time of request with dynamic query 2: 930ms
time of request with dynamic query 3: 885ms
time of request with dynamic query 4: 782ms
time of request with dynamic query 5: 750ms
time of request with dynamic query 6: 743ms
time of request with dynamic query 7: 744ms
time of request with dynamic query 8: 746ms
time of request with dynamic query 9: 736ms
```

### Scala 2.13

```
time of request with static query 1: 509ms
time of request with static query 2: 17ms
time of request with static query 3: 16ms
time of request with static query 4: 15ms
time of request with static query 5: 15ms
time of request with static query 6: 13ms
time of request with static query 7: 16ms
time of request with static query 8: 16ms
time of request with static query 9: 15ms
time of request with dynamic query 1: 813ms
time of request with dynamic query 2: 382ms
time of request with dynamic query 3: 284ms
time of request with dynamic query 4: 263ms
time of request with dynamic query 5: 276ms
time of request with dynamic query 6: 281ms
time of request with dynamic query 7: 305ms
time of request with dynamic query 8: 229ms
time of request with dynamic query 9: 197ms
```