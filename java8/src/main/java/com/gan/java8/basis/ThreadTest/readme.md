1.无论是使用synchronized还是reentrantLock都是一样，都是用于在某个地方加锁。这个都无所谓
他们的区别无非是：
sychronized:是直接加锁执行完则自动释放了。synchronized(this){}执行完自动释放。
reentrantLock: 是需要在lock.lock()，最后同步代码的结尾是lock.unlock()。
2.我应该明白的是在在哪个位置加锁才有用？
1.比如题目：三个线程分别打印A，B，C,循环十次。那么锁应该加在哪里？
仔细分析下条件，他们分别是在循环中被抢占执行的，所以锁肯定要加在循环中。
在锁中执行完代码，状态标志改变，且唤醒其他线程，并释放锁。
2.比如题目：三个线程分别打印10次A,B,C，循环20次。这里锁加哪里？
竞争条件还是在20次循环中，只不过竞争成功后，执行10次罢了。

3.注意：使用reentrantLock的条件时可以使用三个不同的condition条件。
所以他唤醒时只需使用对应condition唤醒，conditionA.signal(),唤醒单个condition。
但是sychronized不一样，他锁的是同一个对象，只能用this.wait(),这样的话唤醒时不能向上面一样
他必须使用this.notifyAll(),唤醒所有。
当然不是说reentrantLock也必须使用不同条件，其实同理，他也可以使用一个condition，但是唤醒时也是signifAll
所以区别是：synchronize必须notifyAll()，而reentrantLock也可以，但是它可以更细粒度。

4.无论是reentrantLock还是sychronized都必须确保是同一个锁对象。
如：reentrantLock确保三个线程是同一个lock。sychronized确保方法调用时是同一个对象。