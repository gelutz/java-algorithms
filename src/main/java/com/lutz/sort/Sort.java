package com.lutz.sort;

public class Sort {
    public static Object[] sortObject(Object[] obj, String field) {
        return Sort._sortObjectRecursive(obj, field, 0, 0);
    }

    // sempre ordena asc
    private static Object[] _sortObjectRecursive(Object[] array, String field, int a, int b) {
        if (b > array.length) {
            return array;
        }

        if (b >= array.length) {
            _sortObjectRecursive(array, field, a + 1, a + 2);
            return array;
        }

        Object sA = array[a];
        Object sB = array[b];
        Integer comparacao = null;

        if (sA instanceof String && sB instanceof String) {
            comparacao = ((String) sA).compareTo((String) sB);
        }

        if (sA instanceof Integer && sB instanceof Integer) {
            comparacao = ((Integer) sA).compareTo((Integer) sB);
        }

        if (comparacao == null) {
            throw new RuntimeException("Something went wrong converting inside sort");
        }

        if (comparacao <= 0) {
            _sortObjectRecursive(array, field, a, b + 1);
        }
        if (comparacao > 0) {
            Object temp = array[a];
            array[a] = array[b];
            array[b] = temp;
            _sortObjectRecursive(array, field, a, b + 1);
        }

        return null;
    }
}
