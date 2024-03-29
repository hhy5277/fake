# 连接池的作用

## 资源重用
 
由于数据库连接得到重用，避免了频繁创建、释放连接引起的大量性能开销。在减少系统消耗的基础上，
另一方面也增进了系统运行环境的平稳性（减少内存碎片以及数据库临时进程/线程的数量）。 

## 更快的系统响应速度
 
数据库连接池在初始化过程中，往往已经创建了若干数据库连接置于池中备用。此时连接的初始化工作均已完成。
对于业务请求处理而言，直接利用现有可用连接，避免了数据库连接初始化和释放过程的时间开销，从而缩减了系统整体响应时间。
 
## 新的资源分配手段
 
对于多应用共享同一数据库的系统而言，可在应用层通过数据库连接的配置，使用数据库连接池技术。
设置某一应用最大可用数据库连接数，避免某一应用独占所有数据库资源。 

## 统一的连接管理，避免数据库连接泄漏 

在较为完备的数据库连接池实现中，可根据预先设定的连接占用超时时间，强制收回被超时占用的连接。
从而避免了常规数据库连接操作中可能出现的资源泄漏（当程序存在缺陷时，申请的连接忘记关闭，这时候，就存在连接泄漏了）。

# 参考资料

https://my.oschina.net/lgscofield/blog/471187

https://www.codetd.com/article/571174

https://blog.csdn.net/leonardc/article/details/79377021

https://blog.csdn.net/yingfengjia520/article/details/78235843

https://blog.csdn.net/qq_16038125/article/details/80180941