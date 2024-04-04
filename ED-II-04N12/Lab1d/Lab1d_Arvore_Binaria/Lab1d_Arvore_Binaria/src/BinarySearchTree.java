import java.util.Objects;

public class BinarySearchTree {
    private Node root;

    private boolean isEmpty(){
        return this.root == null;
    }



    public void insert(String data){
        if(isEmpty()) root = new Node(data);
        else insert(root, data);
    }


    private void insert(Node node, String data){
        Node newNode = new Node(data);
        if(data.compareTo(node.getData()) < 0) {
            if (node.getLeft() == null) {
                node.setLeft(newNode);
                newNode.setParent(node);
                return;
            }
            insert(node.getLeft(), data);
        } else {
            if(node.getRight() == null){
                node.setRight(newNode);
                newNode.setParent(node);
                return;
            }
            insert(node.getRight(), data);
        }
    }


    public void remove(String data) {
        if (isEmpty()) {
            System.out.println("Tree Empty");
            return;
        } else if (search(data) == null) {
            System.out.println("Sorry (" + data + ") is not present");
            return;
        }
        root = remove(root, data);
        System.out.println(data + " deleted from the tree");
    }


    private Node remove(Node root, String data) {
        Node p, p2, n;
        if (root.getData().compareTo(data) == 0) {
            Node lT, rT;
            lT = root.getLeft();
            rT = root.getRight();
            if (lT == null && rT == null)
                return null;
            else if (lT == null) {
                p = rT;
                return p;
            } else if (rT == null) {
                p = lT;
                return p;
            } else {
                p2 = rT;
                p = rT;
                while (p.getLeft() != null)
                    p = p.getLeft();
                p.setLeft(lT);
                return p2;
            }
        }
        if (data.compareTo(root.getData()) < 0) {
            n = remove(root.getLeft(), data);
            root.setLeft(n);
        } else {
            n = remove(root.getRight(), data);
            root.setRight(n);
        }
        return root;
    }



    public Node search(String data){ return search(root, data); }


    private Node search(Node node, String data){
        if(node == null) return null;
        if(Objects.equals(data, node.getData())) return node;
        if(data.compareTo(node.getData()) < 0) return search(node.getLeft(), data);
        return search(node.getRight(), data);
    }


    public Node findMin(){
        if(isEmpty()) return null;
        return findMin(root);
    }

    private Node findMin(Node node){
        if(node.getLeft() == null) return node;
        else return findMin(node.getLeft());
    }


    public Node findMax(){
        if(isEmpty()) return null;
        return findMax(root);
    }


    private Node findMax(Node node){
        if(node.getRight() == null) return node;
        return findMax(node.getRight());
    }


    public Node findSuccessor(String data){
        if(isEmpty()) return null;
        Node node = root;

        if (node != null) return findMin(node.getRight());

        else {
            Node aux = search(data).getParent();

            while (aux != null && data.compareTo(aux.getData()) > 0)
                aux = aux.getParent();

            return aux;
        }
    }

    public Node findPredecessor(String data){
        if(isEmpty()) return null;
        Node node = root;

        if (node != null) return findMax(node.getLeft());

        else {
            Node aux = search(data).getParent();

            while (aux != null && data.compareTo(aux.getData()) < 0)
                aux = aux.getParent();

            return aux;

        }

    }

    public void clear(){
        clear(root);
        root = null;
    }

    private void clear(Node root){
        if(root == null) return;

        clear(root.getLeft());
        clear(root.getRight());

        root.setRight(null);
        root.setLeft(null);
        root.setParent(null);
    }

    public void preorder(){
        preorder(root);
    }


    private void preorder(Node node){
        if(node == null) return;
        System.out.print(node.getData() + " ");
        preorder(node.getLeft());
        preorder(node.getRight());
    }


    public void inorder(){
        inorder(root);
    }

    private void inorder(Node node){
        if(node == null) return;
        inorder(node.getLeft());
        System.out.print(node.getData() + " ");
        inorder(node.getRight());
    }

    public void postorder(){
        postorder(root);
    }

    private void postorder(Node node){
        if(node == null) return;
        postorder(node.getLeft());
        postorder(node.getRight());
        System.out.print(node.getData() + " ");
    }
}
