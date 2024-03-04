
public class No {
	  int info;
	  No esquerda;
	  No direita;
	  No pai;
	  int fb;

	  public No(int info) {
	    this.info = info;
	    this.esquerda = null;
	    this.direita = null;
	    this.pai = null;
	    this.fb = 0;
	  }

	  public int getInfo() {
	    return info;
	  }

	  public void setInfo(int info) {
	    this.info = info;
	  }

	  public No getEsquerda() {
	    return esquerda;
	  }

	  public void setEsquerda(No esquerda) {
	    this.esquerda = esquerda;
	  }

	  public No getDireita() {
	    return direita;
	  }

	  public void setDireita(No direita) {
	    this.direita = direita;
	  }

	  public No getPai() {
	    return pai;
	  }

	  public void setPai(No pai) {
	    this.pai = pai;
	  }

	  public int getFb() {
	    return fb;
	  }

	  public void setFb(int fb) {
	    this.fb = fb;
	  }

	  public void buscarSucessor(int info) {
	    if (info == this.info && this.direita != null) {
	      searchMin(this.direita);
	    } else if (info < this.info) {
	      if (this.esquerda != null) {
	        this.esquerda.buscarSucessor(info);
	      } else {
	        System.out.println("NÚMERO NÃO ENCONTRADO!");
	      }
	    } else {
	      if (this.direita != null) {
	        this.direita.buscarSucessor(info);
	      } else {
	        System.out.println("NÚMERO NÃO ENCONTRADO!");
	      }
	    }
	  }

	  public void buscarAntecessor(int info) {
	    if (info == this.info && this.esquerda != null) {
	      searchMax(this.esquerda);
	    } else if (info < this.info) {
	      if (this.esquerda != null) {
	        this.esquerda.buscarAntecessor(info);
	      } else {
	        System.out.println("NÚMERO NÃO ENCONTRADO!");
	      }
	    } else {
	      if (this.direita != null) {
	        this.direita.buscarAntecessor(info);
	      } else {
	        System.out.println("NÚMERO NÃO ENCONTRADO!");
	      }
	    }
	  }

	  public int altura(No no) {
	    if (no == null) {
	      return -1;
	    } else {
	      int esq = altura(no.esquerda);
	      int dir = altura(no.direita);
	      if (esq > dir) {
	        return esq + 1;
	      } else {
	        return dir + 1;
	      }
	    }
	  }

	  public No searchMin(No atual) {
		    if (atual == null) {
		        return null; // Subárvore vazia, retorno nulo.
		    }
		    No current = atual;
		    while (current.esquerda != null) {
		        current = current.esquerda; // Desça pela esquerda até encontrar o nó mais à esquerda.
		    }
		    return current;
	 }

	  public No searchMax(No atual) {
		    if (atual == null) {
		        return null; // Subárvore vazia, retorno nulo.
		    }
		    No current = atual;
		    while (current.direita != null) {
		        current = current.direita; // Desça pela esquerda até encontrar o nó mais à esquerda.
		    }
		    return current;
	 }

	  public String preOrdem(String str) {
	    str = str.concat(this.getInfo() + "(" + this.getFb() + ") ");
	    if (this.getEsquerda() != null)
	      str = this.getEsquerda().preOrdem(str);
	    if (this.getDireita() != null)
	      str = this.getDireita().preOrdem(str);
	    return str;
	  }
}
