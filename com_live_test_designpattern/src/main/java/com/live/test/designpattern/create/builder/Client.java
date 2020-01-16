package com.live.test.designpattern.create.builder;

public class Client {

	/**
	 * @methodName: main
	 * @description:
	 * @param @param args 
	 * @return void
	 * @throws 
	 # @eg:
	 */
	public static void main(String[] args) {
		PersonDirector pd = new PersonDirector();
		
		//构建一个人
		PersonBuilder pb = new HumanBuilder();
		Person person = pd.ConstructPerson(pb);
		System.out.println(person.getHead() +","+ person.getBody()+"," + person.getFoot());
		
		//构建一个变形金刚
		PersonBuilder pb2 = new TransformerBuilder();
		Person person2 = pd.ConstructPerson(pb2);
		System.out.println(person2.getHead() +","+ person2.getBody()+"," + person2.getFoot());
	}

}
