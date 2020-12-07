package Thread;

public class TestMyThreadByExtend {
    public static void main(String[] args) {
        MyThread t1 = new MyThread("T1");
        MyThread t2 = new MyThread("T2");
        t1.start();
        t2.start();
    }
}
class MyThread extends Thread{
    MyThread(String name){
        super(name);
    }
    public void run(){

        for (int i = 0; i < 10; i++) {
            System.out.println("i = " + i + ", Thread name = " + this.getName());
            int r = (int)(Math.random()*1000);
            try{
                sleep(1000);
            }
            catch(InterruptedException ex){
                ex.printStackTrace();
            }
        }
        System.out.println("Done");
    }
}