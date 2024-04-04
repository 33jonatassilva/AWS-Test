import java.util.Scanner;

// Felipe Gyotoku Koike - 10409640
// Jônatas de Brito Silva - 10403674




public class Main {
    public static void main(String[] args) {
        BinarySearchTree bst = new BinarySearchTree();
        Scanner sc = new Scanner(System.in);
        Scanner scStr = new Scanner(System.in);



        while (true) {
            System.out.println("-=-=-=-=-=-=--=-=-=-=- MENU -=-=-=-=-=-=--=-=-=-=-");
            System.out.println("1. Insert element");
            System.out.println("2. Remove element");
            System.out.println("3. Search element");
            System.out.println("5. Maxim element");
            System.out.println("6. Minim element");
            System.out.println("7. Print Preorder");
            System.out.println("8. Print Inorder");
            System.out.println("9. Print Postorder");
            System.out.println("10. Successor");
            System.out.println("11. Predecessor");
            System.out.println("0. Exit\n");

            System.out.print("Type a valid option: ");
            int option = sc.nextInt();


            if (option == 1) {
                System.out.print("Type a String to insert: ");
                bst.insert(scStr.nextLine());
            } else if (option == 2) {
                System.out.print("Type a String to remove: ");
                bst.remove(scStr.nextLine());
            } else if (option == 3) {
                System.out.print("Type aa String: ");
                bst.search(scStr.nextLine());
            } else if (option == 5) {
                System.out.println(bst.findMax());
            } else if (option == 6) {
                System.out.println(bst.findMin());
            } else if (option == 7) {
                bst.preorder();
                System.out.println();
            } else if (option == 8) {
                bst.inorder();
            } else if (option == 9) {
                bst.postorder();
            } else if (option == 10) {
                System.out.print("Type a String: ");
                System.out.println(bst.findSuccessor(scStr.nextLine()));
            } else if (option == 11) {
                System.out.print("Type a String: ");
                System.out.println(bst.findPredecessor(scStr.nextLine()));
            } else if (option == 0) {
                System.out.print("Program close!");
                bst.clear();
                break;
            }
        }


    }
}