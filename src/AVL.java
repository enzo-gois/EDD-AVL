
public class AVL {
	private No refraiz;

	  public AVL() {
	    this.refraiz = null;
	  }

	  public AVL(int info) {
	    No refraiz = new No(info);
	    this.refraiz = refraiz;
	  }

	  public No getRefraiz() {
	    return refraiz;
	  }

	  public void setRefraiz(No refraiz) {
	    this.refraiz = refraiz;
	  }

	  public boolean isEmpty() {
	    return refraiz == null;
	  }

	    public void atualizaFb(No no) {
	        if (no != null) {
	            int alturaEsquerda = no.altura(no.getEsquerda());
	            int alturaDireita = no.altura(no.getDireita());
	            no.setFb(alturaEsquerda - alturaDireita);
	            atualizaFb(no.getEsquerda());
	            atualizaFb(no.getDireita());
	        }
	    }

	  public void verificaBalanceamento(No no) {
		  
		  if(no != null) {
			  if (no.getFb() > 1 || no.getFb() < -1) {
				  if (no.getFb() > 1) {
					  if (no.getEsquerda().getFb() == -1) {
						  rotacaoDuplaDireita(no);
					  } else {
						  rotacaoDireita(no);
					  }
				  } else {
					  if (no.getDireita().getFb() == 1) {
						  rotacaoDuplaEsquerda(no);
					  } else {
						  rotacaoEsquerda(no);
					  }
				  }
			  }
			  atualizaFb(no);
			  
			  if (no.getPai() != null) {
				  verificaBalanceamento(no.getPai());
			  } else {
				  this.refraiz = no;
			  }			  
		  } else {
			  return;
		  }
		  
	  }

	  public void insere(int info) {
	    No novo = new No(info);
	    if (refraiz == null) {
	      refraiz = novo;
	    } else {
	      No atual = refraiz;
	      while (true) {
	        if (info <= atual.getInfo()) {
	          if (atual.getEsquerda() != null) {
	            novo.setPai(atual.getEsquerda());
	            atual = atual.getEsquerda();
	          } else {
	            novo.setPai(atual);
	            atual.setEsquerda(novo);
	            break;
	          }
	        } else {
	          if (atual.getDireita() != null) {
	            novo.setPai(atual.getDireita());
	            atual = atual.getDireita();
	          } else {
	            novo.setPai(atual);
	            atual.setDireita(novo);
	            break;
	          }
	        }
	      }
	    }
	    atualizaFb(refraiz);
	    verificaBalanceamento(novo);
	  }

	  public void remove(int info) {
	        No atual = this.refraiz;
	        No paiAtual = null;
	        while(atual != null){
	            if (atual.getInfo() == info){
	                break;                
	            }else if (info < atual.getInfo()){ //valor procurado é menor que o atual 
	                paiAtual = atual;
	                atual = atual.getEsquerda();
	            }else{
	                paiAtual = atual;
	                atual = atual.getDireita();
	            }
	        }
	        //verifica se existe o elemento
	        if (atual != null){
	            
	            //elemento tem 2 filhos ou elemento tem somente filho à direita
	            if (atual.getDireita() != null){
	                
	                No substituto = atual.getDireita();
	                No paiSubstituto = atual;
	                while(substituto.getEsquerda() != null){
	                    paiSubstituto = substituto;
	                    substituto = substituto.getEsquerda();
	                    paiSubstituto.setEsquerda(substituto.getDireita());
	                    substituto.setDireita(paiSubstituto);
	                }
	                substituto.setEsquerda(atual.getEsquerda());
	                if (paiAtual != null){
	                    if (atual.getInfo() < paiAtual.getInfo()){ //atual < paiAtual
	                        paiAtual.setEsquerda(substituto);
	                    }else{
	                        paiAtual.setDireita(substituto);
	                    }
	                }else{ //se não tem paiAtual, então é a raiz
	                	No novaRaiz = substituto;
	                	atual.setPai(substituto);
	                	paiSubstituto.setPai(novaRaiz);
	                	this.refraiz.getEsquerda().setPai(substituto);
	                    if(atual.getEsquerda() != null) {
	                    	this.refraiz.setEsquerda(atual.getEsquerda());	                    	
	                    } else {
	                    	this.refraiz.setEsquerda(null);
	                    }
	                    if(atual.getDireita()!= null) {
	                    	this.refraiz.setDireita(null);	                    	
	                    } else {
	                    	this.refraiz.setDireita(null);
	                    }
	                    this.refraiz = novaRaiz;
	                    novaRaiz.setPai(null);
	                }
	                
	                //removeu o elemento da árvore
	                if (substituto.getInfo() > paiSubstituto.getInfo()){ //substituto < paiSubstituto	                		           
	                    paiSubstituto.setDireita(null);
	                }
	                
	            }else if (atual.getEsquerda() != null){ //tem filho só à esquerda
	                No substituto = atual.getEsquerda();
	                No paiSubstituto = atual;
	                while(substituto.getDireita() != null){
	                    paiSubstituto = substituto;
	                    substituto = substituto.getDireita();
	                }
	                if (paiAtual != null){
	                    if (atual.getInfo() < paiAtual.getInfo()){ //atual < paiAtual
	                        paiAtual.setEsquerda(substituto);
	                    }else{
	                        paiAtual.setDireita(substituto);
	                    }
	                }else{ //se for a raiz
	                    this.refraiz = substituto;
	                }
	                
	                //removeu o elemento da árvore
	                if (substituto.getInfo() < paiSubstituto.getInfo()){ //substituto < paiSubstituto
	                    paiSubstituto.setEsquerda(null);
	                }else{
	                    paiSubstituto.setDireita(null);
	                }
	            }else{ //não tem filho
	                if (paiAtual != null){
	                	atual.setPai(paiAtual);
	                    if (atual.getInfo() < paiAtual.getInfo()){ //atual < paiAtual
	                        paiAtual.setEsquerda(null);
	                    }else{
	                        paiAtual.setDireita(null);
	                    }
	                }else{ //é a raiz
	                    this.refraiz = null;
	                }
	            }
	      atualizaFb(refraiz);
	      verificaBalanceamento(atual.getPai());
	       }
	  }

	  public void rotacaoDireita(No no) {
		    No filhoEsq = no.getEsquerda();
		    no.setEsquerda(filhoEsq.getDireita());
		    if (no.getEsquerda() != null) {
		        no.getEsquerda().setPai(no);
		    }
		    filhoEsq.setPai(no.getPai());
		    if (no.getPai() == null) {
		        this.setRefraiz(filhoEsq);
		    } else if (no == no.getPai().getDireita()) {
		        no.getPai().setDireita(filhoEsq);
		    } else {
		        no.getPai().setEsquerda(filhoEsq);
		    }
		    filhoEsq.setDireita(no);
		    no.setPai(filhoEsq);
		    atualizaFb(refraiz);
		}

	  public No rotacaoEsquerda(No no) {
		    No filhoDir = no.getDireita();
		    no.setDireita(filhoDir.getEsquerda());
		    if (no.getDireita() != null) {
		        no.getDireita().setPai(no);
		    }
		    filhoDir.setPai(no.getPai());
		    if (no.getPai() == null) {
		        this.setRefraiz(filhoDir);
		    } else if (no == no.getPai().getEsquerda()) {
		        no.getPai().setEsquerda(filhoDir);
		    } else {
		        no.getPai().setDireita(filhoDir);
		    }
		    filhoDir.setEsquerda(no);
		    no.setPai(filhoDir);
		    atualizaFb(refraiz);
		    return filhoDir;
		}

	  public void rotacaoDuplaDireita(No no) {
			No noEsq = no.getEsquerda();
			No novaRaiz = noEsq.getDireita();
			No newEsq = novaRaiz.getEsquerda();
			
			no.setEsquerda(novaRaiz);
			novaRaiz.setPai(no);
			novaRaiz.setEsquerda(noEsq);
			noEsq.setPai(novaRaiz);
			
			if (novaRaiz.getEsquerda() != null) {
				noEsq.setDireita(newEsq);
				if (newEsq != null) {
					newEsq.setPai(noEsq);
				}
			}
			rotacaoDireita(no);
		    atualizaFb(refraiz);
		}

		public void rotacaoDuplaEsquerda(No no) {
			No noDir = no.getDireita();
			No novaRaiz = noDir.getEsquerda();
			No newDir = novaRaiz.getDireita();
			
			no.setDireita(novaRaiz);
			novaRaiz.setPai(no);
			novaRaiz.setDireita(noDir);
			noDir.setPai(novaRaiz);
			
			if (novaRaiz.getDireita() != null) {
				noDir.setEsquerda(newDir);
				if (newDir != null) {
					newDir.setPai(noDir);				
				}
			}
			rotacaoEsquerda(no);
			atualizaFb(refraiz);
		}

	  public String preOrdem() {
	    String str = "";
	    if (this.refraiz == null) {
	      System.out.println();
	      System.out.println("Árvore vazia!");
	      return str;
	    } else {
	      System.out.println();
	      return this.refraiz.preOrdem(str);
	    }
	  }
}
