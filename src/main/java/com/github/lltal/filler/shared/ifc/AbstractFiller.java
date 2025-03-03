package com.github.lltal.filler.shared.ifc;


public interface AbstractFiller {
    /**
     * Заполнить текущее поле объекта.
     * <p> При вызове не из колбека поведение программы окажется не предсказуемым.
     *
     */
    <V> void fill(Countable object, V value);
}
