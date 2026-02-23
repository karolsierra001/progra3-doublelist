package co.edu.uptc.structures;

import java.lang.reflect.Array;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.NoSuchElementException;

public class DoubleList<T> implements List<T> {
	private Node<T> head;
	private Node<T> tail;
	private int size;

	public DoubleList() {
		head = null;
		tail = null;
		size = 0;
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean isEmpty() {
		return size==0;
	}

	@Override
	public boolean contains(Object o) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Iterator<T> iterator() {
		Iterator<T> iterator = new Iterator<T>(){
			int index = 0;
			@Override
			public boolean hasNext() {
				  return index < size();
			}
			@Override
			public T next() {
				 if (!hasNext()) {
                    throw new NoSuchElementException();
                  }
				  T data = get(index);
                  index++;
                  return data;
            }
		}; 
		return iterator;
	}

	@Override
	public Object[] toArray() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> T[] toArray(T[] a) {
		// TODO Auto-generated method stub
		return null;
	}
@Override
public boolean add(T e) {
    Node<T> newNode = new Node<>(e);  
    if (head == null) {
        head = newNode;
        tail = newNode;
    } else {
        tail.setNext(newNode);
        newNode.setPrevious(tail);
        tail = newNode;
    }
	size++;
    return true; 
}

    @Override
    public boolean equals(Object o){
    if (this == o) { 
        return true;
    }
    if (!(o instanceof DoubleList)) { 
		return false;
    }
    DoubleList<?> other = (DoubleList<?>) o;
    if (this.size() != other.size()) {
        return false;
    }
		return equalsAux(o, other);
	}

	public boolean equalsAux(Object o, DoubleList<?> other){
	Node<T> currentThis = this.head;
    Node<?> currentOther = (Node<?>) other.head;
    while (currentThis != null) {
        T dataThis = currentThis.getValue();
        Object dataOther = currentOther.getValue();
        if (dataThis == null) {
            if (dataOther != null) return false;
        } else if (!dataThis.equals(dataOther)) {
            return false;
        }
        currentThis = currentThis.getNext();
        currentOther = currentOther.getNext();
    }
		return true;
	}

	@Override
	public boolean remove(Object o) {
		Node<T> aux = head;
		if (head == null) {
			return false;
		}
		while (aux != null) {
			if (aux.getValue().equals(o)) {
				if (aux == head && head.getNext() == null) {
					head = null;
					tail = null;
				}else if (aux == head) {
					head = head.getNext();
					head.setPrevious(null);
				}else if (aux == tail) {
					tail = tail.getPrevious();
					tail.setNext(null);
				}else{
					aux.getPrevious().setNext(aux.getNext());
					aux.getNext().setPrevious(aux.getPrevious());
				}
				size--;
				return true;	
			}
			aux = aux.getNext();
		}
		return false;
	}

	@Override
	public boolean containsAll(Collection<?> c) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean addAll(Collection<? extends T> c) {
		boolean modified = false;
        if ( c.isEmpty()) {
            modified = false; 
        }else{ 
                for (T dataCollection : c) {
                add(dataCollection); 
                modified = true;
            }
            }
        return modified; 
	}

	@Override
public boolean addAll(int index, Collection<? extends T> c) {
    if (c == null)
        throw new NullPointerException("Collection cannot be null");
    if (index < 0 || index > size)
        throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
    if (c.isEmpty())
        return false;

    for (T element : c) {
        if (element == null)
            throw new NullPointerException("Collection contains a null element");
    }

    Node<T> next = null;
    Node<T> previous = null;
    if (index == size) {
        previous = tail;
    } else {
        next = head;
        for (int i = 0; i < index; i++) {
            next = next.getNext();
        }
        previous = next.getPrevious();
    }

    for (T element : c) {
        Node<T> newNode = new Node<>(element);
        newNode.setPrevious(previous);
        newNode.setNext(next);
        if (previous == null) {
            head = newNode;
        } else {
            previous.setNext(newNode);
        }
        if (next == null) {
            tail = newNode;
        } else {
            next.setPrevious(newNode);
        }
        previous = newNode;
        size++;
    }

    return true;
}
	@Override
	public boolean removeAll(Collection<?> c) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean retainAll(Collection<?> c) {
		boolean hasChanged = false;
		Iterator<T> it = this.iterator();

		while (it.hasNext()) {
			T element = it.next();
			if (!c.contains(element)) {
				it.remove();
				hasChanged = true;
			}
		}

		return hasChanged;
	}

	@Override
	public void clear() {
		head = null;
		tail = null;
		size = 0;
	}

	@Override
	public T get(int index) {
		if(index<0||index>=size()){
			throw new  IndexOutOfBoundsException();
		}
		int current=0;
		Node <T> aux=head;
		while(aux!=null){
			if(current==index){
				return aux.getValue();
			}
			aux=aux.getNext();
			current++;
		}
		return null;
	}

	@Override
	public T set(int index, T element) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void add(int index, T element) {
		Node <T> newNode = new Node<T>(element);
        if(index< 0 || index > size){
            throw new IndexOutOfBoundsException();  
        }
        
        if(element == null){
            throw  new NullPointerException();
        }
        //SI ESTA VACIO
        if(size == 0 ){
            head = newNode;
            tail = newNode;
        }
        //INSERTAR AL PRINCIPIO
        if(index  == 0){
            newNode.setNext(head);
            head.setPrevious(newNode);
            head = newNode; 
            
            //INSERTAR AL FINAL
        }else if(index ==  size){
            newNode.setPrevious(tail);
            tail.setNext(newNode);
            tail = newNode;
            //INSERTAR EN MEDIO
        }else {
            Node <T> currentNode = head;
            for(int i = 0 ; i < index ; i++){
                currentNode= currentNode.getNext();
            }
            Node <T> previusNode =currentNode.getPrevious();
            previusNode.setNext(newNode);
            newNode.setPrevious(previusNode);
            newNode.setNext(currentNode);
            currentNode.setPrevious(newNode);
        }
        size++;
	}

	@Override
	public T remove(int index) {
		Node <T> aux = head;
        int i = 0;

        if(index < 0 || index >= size){
            throw new IndexOutOfBoundsException();
        }
        while (i < index ) {
            i++;
            aux = aux.getNext();
        }
        if (aux == head) {
            if (aux.getNext()==null) {
                head = null;
            }else{
                head = head.getNext();
                head.setPrevious(null);
            }
        }
        if(aux.getNext()!=null){
            aux.getNext().setPrevious(aux.getPrevious());
        }
        if (aux != head) {
            aux.getPrevious().setNext(aux.getNext());
        }
        if(aux.getNext()== null){
            tail = aux.getPrevious();
        }

        size --;
        return aux.getValue();
	}

	@Override
	public int indexOf(Object o) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int lastIndexOf(Object o) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public ListIterator<T> listIterator() {
	    return new ListIterator<T>() {
	        private Node<T> current = head;
	        private Node<T> lastReturned = null;
	        private int index = 0;

	        @Override
	        public boolean hasNext() {
	            return index < size;
	        }

	        @Override
	        public T next() {
	            if (!hasNext()) throw new NoSuchElementException();
	            lastReturned = current;
	            current = current.getNext();
	            index++;
	            return lastReturned.getValue();
	        }

	        @Override
	        public boolean hasPrevious() {
	            return index > 0;
	        }

	        @Override
	        public T previous() {
	            if (!hasPrevious()) throw new NoSuchElementException();
	            if (current == null) {
	                current = tail;
	            } else {
	                current = current.getPrevious();
	            }
	            lastReturned = current;
	            index--;
	            return lastReturned.getValue();
	        }

	        @Override
	        public int nextIndex() {
	            return index;
	        }

	        @Override
	        public int previousIndex() {
	            return index - 1;
	        }

	        @Override
	        public void remove() {
	            if (lastReturned == null) throw new IllegalStateException();
	            Node<T> prevNode = lastReturned.getPrevious();
	            Node<T> nextNode = lastReturned.getNext();
	            if (prevNode != null) {
	                prevNode.setNext(nextNode);
	            } else {
	                head = nextNode;
	            }
	            if (nextNode != null) {
	                nextNode.setPrevious(prevNode);
	            } else {
	                tail = prevNode;
	            }
	            if (lastReturned == current) {
	                current = nextNode;
	            } else {
	                index--;
	            }
	            size--;
	            lastReturned = null;
	        }

	        @Override
	        public void set(T t) {
	            if (lastReturned == null) throw new IllegalStateException();
	            lastReturned.setValue(t);
	        }

	        @Override
	        public void add(T t) {
	            Node<T> newNode = new Node<>(t);
	            if (current == null) {
	                if (tail != null) {
	                    tail.setNext(newNode);
	                    newNode.setPrevious(tail);
	                    tail = newNode;
	                } else {
	                    head = tail = newNode;
	                }
	            } else {
	                Node<T> prevNode = current.getPrevious();
	                newNode.setNext(current);
	                newNode.setPrevious(prevNode);
	                current.setPrevious(newNode);
	                if (prevNode != null) {
	                    prevNode.setNext(newNode);
	                } else {
	                    head = newNode;
	                }
	            }
	            size++;
	            index++;
	            lastReturned = null;
	        }
	    };
	}

	@Override
	public ListIterator<T> listIterator(int index) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<T> subList(int fromIndex, int toIndex) {
		// TODO Auto-generated method stub
		return null;
	}

}
