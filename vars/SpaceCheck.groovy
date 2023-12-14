def call() {
     sh """#!/bin/bash
    df | awk '/\\/dev\\/disk*/ && ! /\\/private\\/var\\/vm/ {
        s_byte = \$2 * 512            # convert blocks to bytes
        a_byte = \$4 * 512
        ts_byte += s_byte            # add bytes for each device
        ta_byte += a_byte            # to their total
    }

    END {
        ts_byte = ts_byte / 1000000000   # convert bytes to GB
        ta_byte = ta_byte / 1000000000

        print \"CAPACITY:\", ts_byte, \"G     AVAILABLE:\", ta_byte, \"G\"
    }'
    """
}
