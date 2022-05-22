
输出 可见, 如果是不加锁的话 两次累加了之后 只能到10266
加锁了之后 可以到20000 符合预期

```shell
incrementWithoutLock
10000
incrementWithoutLock
incrementWithoutLock
10266
10266
try to lock
increment with lock
increment with lock
19932
20000
```