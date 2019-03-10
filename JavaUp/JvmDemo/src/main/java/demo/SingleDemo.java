package demo;

public class SingleDemo{
  private SingleDemo(){

  }

  private static class SingleHolder{
    private static SingleDemo instance = new SingleDemo();
  }

  public static SingleDemo getInstance(){
    return SingleHolder.instance;
  }
}

//
//public class MyThread extends Thread {
//　　public void run() {
//　　 System.out.println("MyThread.run()");
//　　}
//}
//
//MyThread myThread1 = new MyThread();
//MyThread myThread2 = new MyThread();
//myThread1.start();
//myThread2.start();
