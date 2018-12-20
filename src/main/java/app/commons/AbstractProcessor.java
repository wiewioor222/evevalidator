package app.commons;


public abstract class AbstractProcessor<T extends AbstractNameEntity,T2> implements Processor<T,T2> {

	protected abstract Converter<T,T2> getConverter();

	public T2 process(T t){
		T2 converted = getConverter().convert(t);
		doAdditional(converted);
		return converted;
	}

	protected void doAdditional(T2 converted){}

}
