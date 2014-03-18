package LightCycles;

public class BikeTrail {

	private TrailSegment head, tail, iter;

	public BikeTrail() {head = tail = iter = null;}

	public void addToHead( TrailSegment p, boolean isLeavingTrail ) {
		if (tail != null){
			if (!isLeavingTrail) {tail.hide();}
			tail.setNext(p);
			tail = p;
		}
		else{
			tail = head = p;
		}
	}
	
	public TrailSegment rmTail(){ 
		TrailSegment X = head;
		head = head.getNext();
		return X;
	}
	
	public void hideHead() {
		head.hide();
	}

	public boolean empty() {
		if (head == null){return true;}
		return false;
	}
	public TrailSegment getHead() {return tail;}


	public void start() {iter = head;}

	public TrailSegment nextElement() {
		TrailSegment ws = iter;
		iter = iter.getNext();
		return ws;
	}

	public boolean moreElements() {return iter != null;}

}
