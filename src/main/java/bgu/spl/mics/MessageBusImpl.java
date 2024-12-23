package bgu.spl.mics;

/**
 * The {@link MessageBusImpl class is the implementation of the MessageBus interface.
 * Write your implementation here!
 * Only one public method (in addition to getters which can be public solely for unit testing) may be added to this class
 * All other methods and members you add the class must be private.
 */
public class MessageBusImpl implements MessageBus {
	
	private static MessageBusImpl instance=null; //singleton

	private final ConcurrentHashMap<Class<? extends Event<?>>, Queue<MicroService>> eventSubscribers = new ConcurrentHashMap<>(); // events map
	private final ConcurrentHashMap<Class<? extends Broadcast<?>>, Queue<MicroService>> broadcastSubscribers = new ConcurrentHashMap<>(); // broadcast map
	
	private MessageBusImpl() {}

	public static MessageBusImpl getInstance(){ //singleton
        if (instance == null)
            instance = new MessageBusImpl();
        return instance;
    }
	//fff

	@Override
	public <T> void subscribeEvent(Class<? extends Event<T>> type, MicroService m) {
		// TODO Auto-generated method stub
		eventSubscribers.computeIfAbsent(type, k -> new LinkedList<>()).add(m); //if event is already exist, add the microService to it list, else create new event type in map and add the microService to it list

	}

	@Override
	public void subscribeBroadcast(Class<? extends Broadcast> type, MicroService m) {
		// TODO Auto-generated method stub
		broadcastSubscribers.computeIfAbsent(type, k -> new LinkedList<>()).add(m); //if broadcast is already exist, add the microService to it list, else create new broadcast type in map and add the microService to it list

	}

	@Override
	public <T> void complete(Event<T> e, T result) {
		// TODO Auto-generated method stub

	}

	@Override
	public void sendBroadcast(Broadcast b) {
		// TODO Auto-generated method stub

	}

	
	@Override
	public <T> Future<T> sendEvent(Event<T> e) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void register(MicroService m) {
		// TODO Auto-generated method stub

	}

	@Override
	public void unregister(MicroService m) {
		// TODO Auto-generated method stub

	}

	@Override
	public Message awaitMessage(MicroService m) throws InterruptedException {
		// TODO Auto-generated method stub
		return null;
	}

	

}
