# Synchronized锁在Spring事务管理下，为啥还线程不安全？

https://mp.weixin.qq.com/s/kvbBqYlnPbdFMH-FpeZi9Q

## 开启10000个线程，每个线程给员工表的money字段【初始值是0】加1，没有使用悲观锁和乐观锁，但是在业务层方法上加了synchronized关键字，问题是代码执行完毕后数据库中的money 字段不是10000，而是小于10000 问题出在哪里？
   
发现并不是同步执行的，于是我就怀疑synchronized关键字和Spring肯定有点冲突。于是根据这两个关键字搜了一下，找到了问题所在。

我们知道Spring事务的底层是Spring AOP，而Spring AOP的底层是动态代理技术。跟大家一起回顾一下动态代理：

```java
   public static void main(String[] args) {

        // 目标对象
        Object target ;

        Proxy.newProxyInstance(ClassLoader.getSystemClassLoader(), Main.class, new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

                // 但凡带有@Transcational注解的方法都会被拦截

                // 1... 开启事务

                method.invoke(target);

                // 2... 提交事务

                return null;
            }

        });
    }
```

(详细请参考我之前写过的动态代理：给女朋友讲解什么是代理模式)

实际上Spring做的处理跟以上的思路是一样的，我们可以看一下TransactionAspectSupport类中invokeWithinTransaction()：

![](https://ws2.sinaimg.cn/large/006tKfTcly1g1czpcmouvj30u00esdip.jpg)

调用方法前开启事务，调用方法后提交事务

![](https://ws3.sinaimg.cn/large/006tKfTcly1g1czpumy32j30tr0hbabw.jpg)

在多线程环境下，就可能会出现：方法执行完了(synchronized代码块执行完了)，事务还没提交，别的线程可以进入被synchronized修饰的方法，再读取的时候，读到的是还没提交事务的数据，这个数据不是最新的，所以就出现了这个问题。


![](https://ws4.sinaimg.cn/large/006tKfTcly1g1czq5bydqj30u00f640i.jpg)

三、解决问题

从上面我们可以发现，问题所在是因为@Transcational注解和synchronized一起使用了，加锁的范围没有包括到整个事务。所以我们可以这样做：

新建一个名叫SynchronizedService类，让其去调用addEmployee()方法，整个代码如下：

@RestController
public class EmployeeController {

    @Autowired
    private SynchronizedService synchronizedService ;

    @RequestMapping("/add")
    public void addEmployee() {
        for (int i = 0; i < 1000; i++) {
            new Thread(() -> synchronizedService.synchronizedAddEmployee()).start();
        }
    }
}

// 新建的Service类
@Service
public class SynchronizedService {

    @Autowired
    private EmployeeService employeeService ;

    // 同步
    public synchronized void synchronizedAddEmployee() {
        employeeService.addEmployee();

    }
}

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;


    @Transactional
    public void addEmployee() {

        // 查出ID为8的记录，然后每次将年龄增加一
        Employee employee = employeeRepository.getOne(8);
        System.out.println(Thread.currentThread().getName() + employee);
        Integer age = employee.getAge();
        employee.setAge(age + 1);

        employeeRepository.save(employee);

    }
}

我们将synchronized锁的范围包含到整个Spring事务上，这就不会出现线程安全的问题了。在测试的时候，我们可以发现1000个线程跑起来比之前要慢得多，当然我们的数据是正确的：

![](https://ws1.sinaimg.cn/large/006tKfTcly1g1czqu55w8j309z02pt8o.jpg)

四、留下疑问

现在我们知道为啥会出现线程安全问题了，也知道如何解决了。在我写文章的时候，我也从中发现一些问题，细心的你不知道注意到了没有。我测试的代码中synchronized是修饰在方法上的，按我的推断：应该是synchronized锁释放后，事务提交前这时间间隔内才会出现线程安全问题(别的线程偷偷跑进去了)。

但从上面测试打印的SQL来看，并不完全是这样：

![](https://ws1.sinaimg.cn/large/006tKfTcly1g1czqu55w8j309z02pt8o.jpg)

应该不会出现一连串的查询，而是查询-更新,查询-更新,查询-更新这种情况才对的。

总体来看，我认为思路是没有问题的，但出现上面的结果是我没考虑到的，如果知道为什么会出现这种情况的同学不妨在评论区留言告诉我。







