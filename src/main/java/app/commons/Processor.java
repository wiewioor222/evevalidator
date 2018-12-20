package app.commons;

public interface Processor<T extends AbstractNameEntity,T2> {
	T2 process(T t);
	boolean accept(T t);
}
