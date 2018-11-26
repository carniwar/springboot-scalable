package com.github.carniwar.springboot.scalable.core.util;

/**
 * Class of constants for holding values to be used on bean validation annotations.
 */
public interface ValidationConstraints {

    int DIFF_ID_SIZE_MIN = 1;
    int DIFF_ID_SIZE_MAX = 100;

    int DIFF_DATA_SIZE_MIN = 1;
    int DIFF_DATA_SIZE_MAX = 2000;

}
