package com;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.Spliterator;
import java.util.function.Consumer;
import java.util.function.IntFunction;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class LoggableCollection<T> implements Collection<T> {

    private Collection<T> decorableCollection;
    OutputStream out;

    private void log()
    {
        if (out == null)
            return;

        int align = 15; // параметр выравнивания

        StackTraceElement[] ste = Thread.currentThread().getStackTrace();

        String methodName = ste[ste.length - 2].getMethodName();

        Date d = new Date();
        try {
            var charset = StandardCharsets.UTF_8;

            out.write(d.toString().getBytes(charset));
            out.write((" : ").getBytes(charset));
            out.write(methodName.getBytes(charset));
            out.write((". ").getBytes(charset));
            if (methodName.length() <= align) {
                out.write((" ").repeat(align - methodName.length()).getBytes(charset));
            }
            out.write(("hash code (before operation) = ").getBytes(charset));
            out.write(Integer.toString(decorableCollection.hashCode()).getBytes(charset));
            out.write(("\n").getBytes(charset));
        }
        catch(IOException exception)
        {
            exception.printStackTrace();
        }
    }


    public LoggableCollection(Collection<T> decorableCollection, OutputStream out)
    {
        this.decorableCollection = decorableCollection;
        this.out = out;
    }

    @Override
    public int size() {
        log();
        return decorableCollection.size();
    }

    @Override
    public boolean isEmpty() {
        log();
        return decorableCollection.isEmpty();
    }

    @Override
    public boolean contains(Object o) {
        log();
        return decorableCollection.contains(o);
    }

    @Override
    public Iterator<T> iterator() {
        log();
        return decorableCollection.iterator();
    }

    @Override
    public void forEach(Consumer<? super T> action) {
        log();
        decorableCollection.forEach(action);
    }

    @Override
    public Object[] toArray() {
        log();
        return decorableCollection.toArray();
    }

    @Override
    public <T1> T1[] toArray(T1[] a) {
        log();
        return decorableCollection.toArray(a);
    }

    @Override
    public <T1> T1[] toArray(IntFunction<T1[]> generator) {
        log();
        return decorableCollection.toArray(generator);
    }

    @Override
    public boolean add(T t) {
        log();
        return decorableCollection.add(t);
    }

    @Override
    public boolean remove(Object o) {
        log();
        return decorableCollection.remove(o);
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        log();
        return decorableCollection.containsAll(c);
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        log();
        return decorableCollection.addAll(c);
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        log();
        return decorableCollection.removeAll(c);
    }

    @Override
    public boolean removeIf(Predicate<? super T> filter) {
        log();
        return decorableCollection.removeIf(filter);
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        log();
        return decorableCollection.retainAll(c);
    }

    @Override
    public void clear() {
        log();
        decorableCollection.clear();
    }

    @Override
    public Spliterator<T> spliterator() {
        log();
        return decorableCollection.spliterator();
    }

    @Override
    public Stream<T> stream() {
        log();
        return decorableCollection.stream();
    }

    @Override
    public Stream<T> parallelStream() {
        log();
        return decorableCollection.parallelStream();
    }
}
