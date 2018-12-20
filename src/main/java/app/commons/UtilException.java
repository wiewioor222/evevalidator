package app.commons;

public final class UtilException {

	@FunctionalInterface
	public interface Supplier_WithExceptions<T, E extends Exception> {
		T get() throws E;
	}

	public static <R, E extends Exception> R uncheck(Supplier_WithExceptions<R, E> supplier)
	{
		try { return supplier.get(); }
		catch (Exception exception) { throwAsUnchecked(exception); return null; }
	}

	@SuppressWarnings ("unchecked")
	private static <E extends Throwable> void throwAsUnchecked(Exception exception) throws E { throw (E)exception; }

}
