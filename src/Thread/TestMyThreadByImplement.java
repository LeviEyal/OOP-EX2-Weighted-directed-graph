package Thread;

public class TestMyThreadByImplement {
    public static void main(String[] args) {
        MyThread2 mt1 = new MyThread2("Th1");
        MyThread2 mt2 = new MyThread2("Th2");
        // mt1 (mt2) - the object whose run method is called.
        // name - the name of the new thread.
        Thread t1 = new Thread(mt1);
        Thread t2 = new Thread(mt2);
        t1.start();
        t2.start();
    }

}
class MyThread2 implements Runnable{
    String name;
    MyThread2(String name){
        this.name = name;
    }
    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            System.out.println("i = " + i + ", Thread name = " + this.name);
            int r = (int)(Math.random()*1000);
            try{
                Thread.sleep(1000);
            }
            catch(InterruptedException ex){}
        }
        System.out.println("Done");
    }

}