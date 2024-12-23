package bgu.spl.mics;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.ConcurrentHashMap;

/**
 * The {@link MessageBusImpl class is the implementation of the MessageBus interface.
 * Write your implementation here!
 * Only one public method (in addition to getters which can be public solely for unit testing) may be added to this class
 * All other methods and members you add the class must be private.
 */
public class MessageBusImpl implements MessageBus {
	
	private static MessageBusImpl instance=null; //singleton

	private final ConcurrentHashMap<Class<? extends Event<?>>, Queue<MicroService>> eventSubscribers = new ConcurrentHashMap<>(); // events map
	private final ConcurrentHashMap<Class<? extends Broadcast>, Queue<MicroService>> broadcastSubscribers = new ConcurrentHashMap<>(); // broadcast map
	private final ConcurrentHashMap<MicroService, Queue<Message>> microServiceQueues= new ConcurrentHashMap<>(); // micro service map
	
	private MessageBusImpl() {}

	public static MessageBusImpl getInstance(){ //singleton
        if (instance == null)
            instance = new MessageBusImpl();
        return instance;
    }

	@Override
	public synchronized <T> void subscribeEvent(Class<? extends Event<T>> type, MicroService m) {
		// TODO Auto-generated method stub
		eventSubscribers.computeIfAbsent(type, k -> new LinkedList<>()).add(m); //if event is already exist, add the microService to it list, else create new event type in map and add the microService to it list

	}

	@Override
	public synchronized void subscribeBroadcast(Class<? extends Broadcast> type, MicroService m) {
		// TODO Auto-generated method stub
		broadcastSubscribers.computeIfAbsent(type, k -> new LinkedList<>()).add(m); //if broadcast is already exist, add the microService to it list, else create new broadcast type in map and add the microService to it list

	}

	@Override
	public <T> void complete(Event<T> e, T result) {
		// TODO Auto-generated method stub

	}

	@Override
	public synchronized void sendBroadcast(Broadcast b) {
		// TODO Auto-generated method stub
		for(MicroService ms : broadcastSubscribers.get(b.getClass())){
			microServiceQueues.computeIfAbsent(ms, k -> new LinkedList<>()).add(b);
		}
	}

	
	@Override
	public synchronized <T> Future<T> sendEvent(Event<T> e) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public synchronized void register(MicroService m) {
		// TODO Auto-generated method stub
		microServiceQueues.putIfAbsent(m,new LinkedList<>());

	}

	@Override
	public synchronized void unregister(MicroService m) {
		// TODO Auto-generated method stub

		microServiceQueues.remove(m);
		for (Queue<MicroService> subscribers : eventSubscribers.values()) {
   			 subscribers.remove(m);
		}
		for (Queue<MicroService> subscribers : broadcastSubscribers.values()) {
   			subscribers.remove(m);
		}


	}

	@Override
	public synchronized Message awaitMessage(MicroService m) throws InterruptedException {
		// TODO Auto-generated method stub
		return null;
	}

	

}
