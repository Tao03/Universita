public class TestDynamicStack {
    public static void main(String[] args) {
        DynamicStack list = new DynamicStack();
        list.push(1);
        list.push(1);
        list.push(1);
        DynamicStack list1 = new DynamicStack();
        list1.push(1);
        list1.push(1);
        list1.push(1);
        
        System.out.println(DynamicStack.included(list.getTop(),list1.getTop()));
    }
}
