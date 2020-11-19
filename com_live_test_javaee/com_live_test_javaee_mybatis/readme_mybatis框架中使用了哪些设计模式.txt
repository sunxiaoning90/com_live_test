 Mybatis 框架中用到了哪些设计模式
 
  1、工厂模式
  SqlSessionFactory sqlSessionFactory
  
  2、建造器模式
  SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(in);
  
  SqlSessionFactoryBuilder

public SqlSessionFactory build(Reader reader) {
    return build(reader, null, null);
  }

  public SqlSessionFactory build(Reader reader, String environment) {
    return build(reader, environment, null);
  }

  public SqlSessionFactory build(Reader reader, Properties properties) {
    return build(reader, null, properties);
  }

public SqlSessionFactory build(InputStream inputStream) {
    return build(inputStream, null, null);
  }

  public SqlSessionFactory build(InputStream inputStream, String environment) {
    return build(inputStream, environment, null);
  }

  public SqlSessionFactory build(InputStream inputStream, Properties properties) {
    return build(inputStream, null, properties);
  }

//...

  3、单例模式
  
  在Mybatis中有两个地方用到单例模式，ErrorContext和LogFactory，其中ErrorContext是用在每个线程范围内的单例，用于记录该线程的执行环境错误信息

而LogFactory则是提供给整个Mybatis使用的日志工厂，用于获得针对项目配置好的日志对象。

ErrorContext的单例实现代码：
public class ErrorContext {

  private static final ThreadLocal<ErrorContext> LOCAL = new ThreadLocal<ErrorContext>();

  private ErrorContext() {
  }

  public static ErrorContext instance() {
    ErrorContext context = LOCAL.get();
    if (context == null) {
      context = new ErrorContext();
      LOCAL.set(context);
    }
    return context;
  }
  
}

构造函数是private修饰，具有一个static的局部instance变量和一个获取instance变量的方法，在获取实例的方法中，先判断是否为空如果是的话就先创建，然后返回构造好的对象。

只是这里有个有趣的地方是，LOCAL的静态实例变量使用了ThreadLocal修饰，也就是说它属于每个线程各自的数据，而在instance()方法中，先获取本线程的该实例，如果没有就创建该线程独有的ErrorContext。
  
思考：
1、SqlSessionFactory 没有采用单例模式，为什么？
考虑到多数据源

  4、适配器模式（Mybatis日志模块，使用了适配器模式）
1、Mybatis日志模块，使用了适配器模式，抽象了顶级的日志接口Log，使用适配器模式分别适配了多种框架：sl4j、CommonsLogging、log4j2、JdkLogging...

Log层级关系：
|-- Log
	|-- Slf4jLoggerImpl
	|-- JakartaCommonsLoggingImpl
	|-- Log4j2LoggerImpl
	|-- Jdk14LoggingImpl
	|-- StdOutImpl
	
3、Mybatis 日志模块 是如何确定使用哪种日志方案的？
1）尝试逐个匹配，如果匹配到就采用该日志方案，顺序如下：
Slf4j --> CommonsLogging  --> Log4J2 -->  JdkLogging --> NoLogging
2）源码


  5、代理模式
   mapperProxyFactory.newInstance(sqlSession);
  
   protected T newInstance(MapperProxy<T> mapperProxy) {
    return (T) Proxy.newProxyInstance(mapperInterface.getClassLoader(), new Class[] { mapperInterface }, mapperProxy);
  }
  
  org.apache.ibatis.binding

MapperProxyFactory.class
@SuppressWarnings("unchecked")
  protected T newInstance(MapperProxy<T> mapperProxy) {
    return (T) Proxy.newProxyInstance(mapperInterface.getClassLoader(), new Class[] { mapperInterface }, mapperProxy);
  }

  public T newInstance(SqlSession sqlSession) {
    final MapperProxy<T> mapperProxy = new MapperProxy<T>(sqlSession, mapperInterface, methodCache);
    return newInstance(mapperProxy);
  }

MapperProxy


MapperRegistry.class
public <T> T getMapper(Class<T> type, SqlSession sqlSession) {
    final MapperProxyFactory<T> mapperProxyFactory = (MapperProxyFactory<T>) knownMappers.get(type);
    if (mapperProxyFactory == null) {
      throw new BindingException("Type " + type + " is not known to the MapperRegistry.");
    }
    try {
      return mapperProxyFactory.newInstance(sqlSession);
    } catch (Exception e) {
      throw new BindingException("Error getting mapper instance. Cause: " + e, e);
    }
  }
  
  
  6、模板方法模式
  sqlSessionFacory -> sqlSession (包含一个Executor实现类) -> Executor执行CRUD
  
  
  1)Executor
  
  public interface Executor {

		//数据库更新操作（增加、修改、删除）
	  int update(MappedStatement ms, Object parameter) throws SQLException;
	  
	  //数据库 查询操作
	  <E> List<E> query(MappedStatement ms, Object parameter, RowBounds rowBounds, ResultHandler resultHandler, CacheKey cacheKey, BoundSql boundSql) throws SQLException;
	  <E> List<E> query(MappedStatement ms, Object parameter, RowBounds rowBounds, ResultHandler resultHandler) throws SQLException;
	  <E> Cursor<E> queryCursor(MappedStatement ms, Object parameter, RowBounds rowBounds) throws SQLException;
	  //。。。
  }
  
  2）BaseExecutor
  
  以update（）为例，使用了模板方法，调用了抽象方法doUpdate();
  
  @Override
  public int update(MappedStatement ms, Object parameter) throws SQLException {
    ErrorContext.instance().resource(ms.getResource()).activity("executing an update").object(ms.getId());
    if (closed) {
      throw new ExecutorException("Executor was closed.");
    }
    clearLocalCache();
    return doUpdate(ms, parameter);
  }
  
  protected abstract int doUpdate(MappedStatement ms, Object parameter) throws SQLException;

  protected abstract List<BatchResult> doFlushStatements(boolean isRollback) throws SQLException;

  protected abstract <E> List<E> doQuery(MappedStatement ms, Object parameter, RowBounds rowBounds, ResultHandler resultHandler, BoundSql boundSql) throws SQLException;

  protected abstract <E> Cursor<E> doQueryCursor(MappedStatement ms, Object parameter, RowBounds rowBounds, BoundSql boundSql) throws SQLException;
  
  3）SimpleExecutor
  public class SimpleExecutor extends BaseExecutor {

  @Override
  public int doUpdate(MappedStatement ms, Object parameter) throws SQLException {
    Statement stmt = null;
    try {
      Configuration configuration = ms.getConfiguration();
      StatementHandler handler = configuration.newStatementHandler(this, ms, parameter, RowBounds.DEFAULT, null, null);
      stmt = prepareStatement(handler, ms.getStatementLog());
      return handler.update(stmt);
    } finally {
      closeStatement(stmt);
    }
  }
  }
  
  7、装饰器模式
    Cache
Cache 层级关系
|-- Cache
	|-- LruCache
	|-- FifoCache
	|-- BlockingCache
	|-- LoggingCache
	|-- ScheduledCache
	|-- SerializedCache
	|-- SynchronizedCache
	|-- TransactionalCache
	|-- SoftCache
	|-- WeakCache
 
 8、策略模式
 
 MyBatis核心类Configuration类似于策略模式中的Context，区别于Context就是：Context维系是的传入的策略对象；Configuration是根据传入的策略对象类型，生产相应的策略对象，代码如下：

// 配置文件中setting属性为defaultExecutorType的对应Configuration属性
protected ExecutorType defaultExecutorType = ExecutorType.SIMPLE;
// 根据默认的执行器类型，生成相应的执行器对象
public Executor newExecutor(Transaction transaction) {
    return newExecutor(transaction, defaultExecutorType);
}
// 可指定执行器类型，生成相应的执行器对象
public Executor newExecutor(Transaction transaction, ExecutorType executorType) {
    executorType = executorType == null ? defaultExecutorType : executorType;
    executorType = executorType == null ? ExecutorType.SIMPLE : executorType;
    Executor executor;
    if (ExecutorType.BATCH == executorType) {
        executor = new BatchExecutor(this, transaction);
    } else if (ExecutorType.REUSE == executorType) {
        executor = new ReuseExecutor(this, transaction);
    } else {
        executor = new SimpleExecutor(this, transaction);
    }
    if (cacheEnabled) {
        executor = new CachingExecutor(executor);
    }
    executor = (Executor) interceptorChain.pluginAll(executor);
    return executor;
}

 