//
//package com.live.test.mongodb;
//
//import java.util.Arrays;
//
//import org.bson.Document;
//import org.bson.conversions.Bson;
//import org.junit.Before;
//import org.junit.Test;
//
//import com.mongodb.MongoClient;
//import com.mongodb.client.FindIterable;
//import com.mongodb.client.MongoCollection;
//import com.mongodb.client.MongoCursor;
//import com.mongodb.client.MongoDatabase;
//import com.mongodb.client.model.Filters;
//
//public class MongoDBDemo2 {
//
//    private MongoClient client = null;
//
//    private MongoDatabase database = null;
//
//    @Before
//    public void init() {
//        // 连接mongodb服务器(ip、port)
//        client = new MongoClient("127.0.0.1", 27017);
//        // 服务器--->库---->集合--->Document
//        database = client.getDatabase("dt55");
//    }
//
//    /**
//     * 
//     * Description: 添加一条数据<br/>
//     *
//     * @author 
//     */
//    @Test
//    public void insertOne() {
//        // 获取集合
//        MongoCollection<Document> bookCollection = database.getCollection("books");
//        // 开始往books集合中添加数据
//        // Document document1 = new Document();// {}
//        Document document1 = new Document("bookName", "Mybatis从入门到精通");// {"bookName":"Mybatis从入门到精通"}
//        // document1.append("bookName", "Mybatis从入门到精通");
//        document1.append("price", 29.9);
//
//        bookCollection.insertOne(document1);
//        // 最后关闭连接
//        client.close();
//
//    }
//
//    /**
//     * 
//     * Description: 测试MongoDB的效率<br/>
//     *
//     * @author 
//     */
//    @Test
//    public void insertOne2() {
//        long startTime = System.currentTimeMillis();// 起始时间
//        // 获取集合
//        MongoCollection<Document> bookCollection = database.getCollection("books");
//        // 开始往books集合中添加数据
//        // Document document1 = new Document();// {}
//
//        for (int i = 0; i < 1000000; i++) {
//            Document document1 = new Document("bookName", "Mybatis从入门到精通");// {"bookName":"Mybatis从入门到精通"}
//            // document1.append("bookName", "Mybatis从入门到精通");
//            document1.append("price", 29.9);
//            bookCollection.insertOne(document1);
//        }
//        // 最后关闭连接
//        client.close();
//        long endTime = System.currentTimeMillis();// 起始时间
//        System.out.println("共花费" + (endTime - startTime) / 1000 + "s");
//
//    }
//
//    /**
//     * 
//     * Description: 同时往某一个集合中添加多条数据(Document)<br/>
//     *
//     * @author 
//     */
//    @Test
//    public void insertMany() {
//        MongoCollection<Document> bookCollection = database.getCollection("books");
//        Document document1 = new Document("bookName", "1");
//        document1.append("price", 39.9);
//
//        Document document2 = new Document("bookName", "2");
//        document2.append("price", 19.9);
//
//        Document document3 = new Document("bookName", "3");
//        document3.append("price", 99.9);
//
//        bookCollection.insertMany(Arrays.asList(document1, document2, document3));
//
//        // 关闭资源
//        client.close();
//    }
//
//    /**
//     * 
//     * Description: 带单个条件的删除<br/>
//     *
//     * @author 
//     */
//    @Test
//    public void delete1() {
//        // 首先获取要删除数据的集合
//        MongoCollection<Document> bookCollection = database.getCollection("books");
//        // 执行删除方法
//        // price=29.9
//        Bson bson1 = Filters.eq("price", 29.9);// 构建条件
//        bookCollection.deleteMany(bson1);
//        // 关闭资源
//        client.close();
//    }
//
//    /**
//     * 
//     * Description: 带多个条件的删除<br/>
//     *
//     * @author 
//     */
//    @Test
//    public void delete2() {
//        // 首先获取要删除数据的集合
//        MongoCollection<Document> bookCollection = database.getCollection("books");
//        // delete from books where price>=19.9 and price<=99.9
//        Bson bson1 = Filters.gte("price", 19.9);// price>=19.9
//        Bson bson2 = Filters.lte("price", 99.9);// price<=99.9
//        Bson bson3 = Filters.and(bson1, bson2);
//        bookCollection.deleteMany(bson3);
//
//        // 关闭资源
//        client.close();
//    }
//
//    // --------------------------------查询与修改----------------------------------------
//    @Test
//    public void getAll() {
//        MongoCollection<Document> bookCollection = database.getCollection("books");
//        // 查询操作
//        // 查询所有数据
//        FindIterable<Document> documentList = bookCollection.find();// List<Document>
//        for (Document document : documentList) {
//            System.out.println(document);
//        } // ssh--->ssm--->springboot
//          // 关闭资源
//        client.close();// IT-->安全 效率 操作简洁
//    }
//
//    /**
//     * 
//     * Description: 迭代器<br/>
//     *
//     * @author 
//     */
//    @Test
//    public void getAll2() {
//        MongoCollection<Document> bookCollection = database.getCollection("books");
//        // 查询操作
//        // 查询所有数据
//        FindIterable<Document> documentList = bookCollection.find();// List<Document>
//        MongoCursor<Document> documentCursor = documentList.iterator();
//        while (documentCursor.hasNext()) {
//            Document document = documentCursor.next();
//            System.out.println(document);
//        }
//        // 关闭资源
//        client.close();
//    }
//
//    /**
//     * 
//     * Description: 带条件的查询<br/>
//     *
//     * @author 
//     */
//    @Test
//    public void get3() {
//        MongoCollection<Document> bookCollection = database.getCollection("books");
//        // 查询操作
//        // 查询所有数据
//        //// select * from books where price>=39.9 and price<199.9
//        Bson bson1 = Filters.and(Filters.gte("price", 39.9), Filters.lt("price", 199.9));
//        FindIterable<Document> documentList = bookCollection.find(bson1);// List<Document>
//        for (Document document : documentList) {
//            String bookName = (String) document.get("bookName");
//            Double price = (Double) document.get("price");// 39.900
//            // 数值格式化 Date yyyy-MM-dd
//            System.out.println(bookName + "的价格为:" + String.format("%.2f", price));
//        }
//        // 关闭资源
//        client.close();
//    }
//
//    /**
//     * 
//     * Description: 分页查询<br/>
//     *
//     * @author 
//     */
//    @Test
//    public void get4() {
//        MongoCollection<Document> bookCollection = database.getCollection("books");
//        // 查询操作
//        // 查询所有数据
//        FindIterable<Document> documentList = bookCollection.find();// List<Document>
//        // limit()、skip()方法是FindIterable中
//        FindIterable<Document> resultList = documentList.skip(2).limit(2);
//        for (Document document : resultList) {
//            System.out.println(document);
//        }
//        // 关闭资源
//        client.close();
//    }
//
//    // ----------------------------修改---------------------------------
//    /**
//     * 
//     * Description: 修改操作<br/>
//     *
//     * @author 
//     */
//    @Test
//    public void update1() {
//        // 1、获取集合
//        MongoCollection<Document> bookCollection = database.getCollection("books");
//        // 2.执行修改操作
//        Bson bson1 = Filters.eq("bookName", "Python从入门到精通");// 条件
//        // {$set:{"price":99.9}}
//        Document document1 = new Document("price", 99.9);// value
//        Document document2 = new Document("$set", document1);// key-value
//
//        bookCollection.updateMany(bson1, document2);
//
//        // 关闭资源
//        client.close();
//    }
//
//    @Test
//    public void update2() {
//        // 1、获取集合
//        MongoCollection<Document> bookCollection = database.getCollection("books");
//        // 2.执行修改操作
//        Bson bson1 = Filters.eq("bookName", "Python从入门到精通");// 条件
//        // {$set:{"price":99.9}}
//        Document document1 = new Document("price", 99.9);// value
//        Document document2 = new Document("$set", document1);// key-value
//
//        bookCollection.updateMany(bson1, document2);
//
//        // 关闭资源
//        client.close();
//    }
//
//}
