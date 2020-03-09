package com.live.test.db.mongodb.example;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
/**
 * 
 * @author live
 */
public class MongoDBDemo {

	/**
	 * 连接MongoDB -> db -> collention ->document
	 * @param args
	 */
	public static void main(String[] args) {
	String mongoIP = "x";
	int mongoPort = 27017;
		
//	1)Connect to MongoDB
//	如果 Mongo 不需要密码
//		MongoClient mongoClient = new MongoClient();
//		MongoClient mongoClient = new MongoClient("127.0.0.1");//指定host
//		MongoClient mongoClient = new MongoClient("127.0.0.1",27017);//指定host和port
//		MongoClient mongoClient = new MongoClient(new MongoClientURI("mongodb://127.0.0.1:27017"));
		
//	如果　Mongo 需要验证用户名及密码
		MongoCredential credential = MongoCredential.createScramSha1Credential("xx", "xx", "xx".toCharArray());//三个参数分别为 用户名 数据库名称 密码  
		List<MongoCredential> credentialsList = new ArrayList<MongoCredential>();  
		credentialsList.add(credential); 
        
        //连接到MongoDB服务 如果是远程连接可以替换“localhost”为服务器所在IP地址  
        //ServerAddress()两个参数分别为 服务器地址 和 端口  
        ServerAddress serverAddress = new ServerAddress(mongoIP,mongoPort);  
        List<ServerAddress> addrs = new ArrayList<ServerAddress>();  
        addrs.add(serverAddress);  
		MongoClient mongoClient = new MongoClient(addrs, credentialsList);
		
        System.out.println("mongoClient:" + mongoClient);
        
//	2)Databases and Collections
//	2.1)Access a Database
		MongoDatabase db = mongoClient.getDatabase("spIm");//MongoDatabase instances are immutable.
		System.out.println("db:" +db);
		for (String name : db.listCollectionNames()) {
			System.out.println("\t"+name);
		}
		
//	2.2)Access a Collection
//	创建集合
//		db.createCollection("test1");
		System.out.println("集合创建成功");
		
//	use集合
		MongoCollection<Document> collection = db.getCollection("talk");
		System.out.println("use集合:" + collection);
		
//	增，文档
		Document document = new Document("title", "MongoDB").  
	    append("公司电话?", "010-123456").  
	    append("code", 100);  
		collection.insertOne(document);
		//db.talk.insert("公司电话多少啊", "010-123456");
		System.out.println("插入一条文档：" + document.toJson());
		
//   改，文档
//      updateMay()是更新全部
//		updateOne()只更新匹配到的第一条
        collection.updateMany(Filters.eq("code", 100), new Document("$set",new Document("code",200)));  
        
        
//   删，文档　
//      deleteOne():除符合条件的第一个文档  
        collection.deleteOne(Filters.eq("code", 200));  
//      deleteMany():删除所有符合条件的文档  
        collection.deleteMany (Filters.eq("code", 200));  
        
//   检索所有文档  
//   db.talk.find();
        /** 
        * 1. 获取迭代器FindIterable<Document> 
        * 2. 获取游标MongoCursor<Document> 
        * 3. 通过游标遍历检索出的文档集合 
        * */  
        FindIterable<Document> findIterable = collection.find();  
        MongoCursor<Document> mongoCursor = findIterable.iterator();
        System.out.println("查询");
        while(mongoCursor.hasNext()){  
           System.out.println(mongoCursor.next());  
        }  
	}
}
