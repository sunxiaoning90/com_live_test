//package com.live.test.javase.core.juc.forkJoin;
//
//import java.util.concurrent.ForkJoinPool;
//import java.util.concurrent.RecursiveAction;
//
///**
// */
//public class FkDemo {
//
//    private static class MyTask extends RecursiveAction {
//
//        @Override
//        protected void compute() {
//            if(任务规模满足某个条件){
//                //做自己的业务工作
//
//            }else{
//                MyTask subTask1 = new MyTask();
//                MyTask subTask2 = new MyTask();
//                invokeAll(subTask1,subTask1);
//                result = subTask1.join()+subTask2.join();
//            }
//        }
//    }
//
//    public static void main(String[] args) {
//        ForkJoinPool pool = new ForkJoinPool();//框架类
//        MyTask myTask = new MyTask();//总任务
//        pool.invoke(myTask);
//        myTask.join();
//
//    }
//
//}
