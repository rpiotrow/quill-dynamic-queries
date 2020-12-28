## Dynamic queries in Quill

This simple programs shows runtime overhead for dynamic queries in [quill](https://github.com/getquill/quill). Executing the same query as dynamic one can be 170x longer comparing to static query.

Program executes two identical queries using H2 in-memory database, as static query, and as dynamic query.

## Run

Using sbt:
```
$ sbt run
```

## Results

### Scala 2.12
```
[info] time of static query executed for 1 time: 71ms
[info] time of static query executed for 2 time: 0ms
[info] time of static query executed for 3 time: 1ms
[info] time of static query executed for 4 time: 1ms
[info] time of static query executed for 5 time: 1ms
[info] time of static query executed for 6 time: 1ms
[info] time of static query executed for 7 time: 0ms
[info] time of static query executed for 8 time: 2ms
[info] time of static query executed for 9 time: 1ms
[info] time of dynamic query executed for 1 time: 1213ms
[info] time of dynamic query executed for 2 time: 428ms
[info] time of dynamic query executed for 3 time: 301ms
[info] time of dynamic query executed for 4 time: 295ms
[info] time of dynamic query executed for 5 time: 242ms
[info] time of dynamic query executed for 6 time: 235ms
[info] time of dynamic query executed for 7 time: 264ms
[info] time of dynamic query executed for 8 time: 225ms
[info] time of dynamic query executed for 9 time: 219ms
```

### Scala 2.13
```
[info] time of static query executed for 1 time: 89ms
[info] time of static query executed for 2 time: 1ms
[info] time of static query executed for 3 time: 1ms
[info] time of static query executed for 4 time: 0ms
[info] time of static query executed for 5 time: 0ms
[info] time of static query executed for 6 time: 1ms
[info] time of static query executed for 7 time: 1ms
[info] time of static query executed for 8 time: 1ms
[info] time of static query executed for 9 time: 1ms
[info] time of dynamic query executed for 1 time: 1080ms
[info] time of dynamic query executed for 2 time: 377ms
[info] time of dynamic query executed for 3 time: 305ms
[info] time of dynamic query executed for 4 time: 247ms
[info] time of dynamic query executed for 5 time: 205ms
[info] time of dynamic query executed for 6 time: 187ms
[info] time of dynamic query executed for 7 time: 183ms
[info] time of dynamic query executed for 8 time: 184ms
[info] time of dynamic query executed for 9 time: 170ms
```
