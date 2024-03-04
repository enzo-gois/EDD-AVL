
public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		AVL avl = new AVL(10);
		avl.insere(5);
		avl.insere(16);
		avl.insere(1);
		avl.insere(7);
		avl.insere(12);
		avl.insere(20);
		avl.insere(8);
		avl.insere(11);
		avl.insere(14);
		avl.insere(17);
		avl.insere(22);
		avl.insere(15);
		avl.remove(8);
		avl.remove(12);
		avl.remove(15);
		System.out.println(avl.preOrdem());
	}
}