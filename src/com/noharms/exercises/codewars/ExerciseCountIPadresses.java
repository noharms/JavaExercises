package com.noharms.exercises.codewars;

public class ExerciseCountIPadresses {

    /**
     * task: count number of ip-adresses that lie in between
     * the two passed adresses ip1 and ip2 (including ip1, excluding ip2)
     *
     * assumptions: all input is valid;
     *              ip2 > ip2
     *
     * example:   "0.0.0.0", "0.0.0.10"  --> 10
     *            "0.0.255.255" , "0.1.0.0" --> 1
     *            "0.0.0.0". "255.255.255.255" --> 2^32 = Integer.MAX
     *
     * @param ip1
     * @param ip2
     * @return
     */
    public static long countIPAdresses(String ip1, String ip2) {
        return ipv4AdressToLong(ip2) - ipv4AdressToLong(ip1);
    }

    private static long ipv4AdressToLong(String ip) {
        String[] bytes = ip.split("\\.", -1);
        return    (long)Integer.parseInt(bytes[3])
                + (long)(Math.pow(2, 8) * Integer.parseInt(bytes[2]))
                + (long)(Math.pow(2, 16) *Integer.parseInt(bytes[1]))
                + (long)(Math.pow(2, 24) *Integer.parseInt(bytes[0]));
    }
}
