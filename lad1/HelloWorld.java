import by.belstu.it.zhuk.TextFunction;

public class Main {
 public static void main(String[] args){
 }

 public void OnCreate(){
  for (int count = 0; count < 10; count++) {
   System.out.println("Counter " + count);
  }

 }
 private static String getString(TextFunction fun) {
  return fun.getValue();
 }
}
